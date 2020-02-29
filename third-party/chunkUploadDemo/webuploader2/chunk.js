var userInfo = {md5: ""};   //用户会话信息
var chunkSize = 5 * 1000 * 1024;        //分块大小
var uniqueFileName = null;          //文件唯一标识符
var folderId = -1; //文件夹id 根据当前所属文件夹来赋值
function getUrl(uri) {   //测试用，根据不同类型的后端返回对应的请求地址
                         // return "http://127.0.0.1:11008/file/" + uri;
    return _GATE_URL + "chunk/" + uri;
}

function getHeaders() {
    return {
        token: "test"
    };
}

WebUploader.Uploader.register({
    "before-send-file": "beforeSendFile"  //时间点1：所有分块进行上传之前调用此函数 ，检查文件存不存在
    , "before-send": "beforeSend"         //时间点2：如果有分块上传，则每个分块上传之前调用此函数  ，判断分块存不存在
    , "after-send-file": "afterSendFile"  //时间点3：分片上传完成后，通知后台合成分片
}, {
    beforeSendFile: function (file) {
        console.log(file);
        //秒传验证
        var task = new $.Deferred();
        var start = new Date().getTime();
        (new WebUploader.Uploader()).md5File(file, 0, 10 * 1024 * 1024).progress(function (percentage) {
            console.log(percentage);
        }).then(function (val) {
            console.log("总耗时: " + ((new Date().getTime()) - start) / 1000);
            // md5Mark = val;
            userInfo.md5 = val;
            uploader.options.formData["md5"] = val;
            uploader.options.formData["ext"] = file.ext;
            $.ajax({
                type: "POST"
                , url: getUrl('md5')
                , data: {
                    md5: val
                    , folderId: folderId
                }
                , headers: getHeaders()
                , async: false  // 同步
                , cache: false
                , dataType: "json"
            }).then(function (data, textStatus, jqXHR) {
                console.log(data);
                if (data.isSuccess) {
                    if (data.data) {   //若存在，这返回失败给WebUploader，表明该文件不需要上传
                        task.reject();   //分片存在，跳过
                        uploader.skipFile(file);
                        UploadComlate(file);
                    } else {
                        task.resolve();   //分块不存在或不完整，重新发送该分块内容
                        //拿到上传文件的唯一名称，用于断点续传  需要确保前后端的md5算法和参数一致
                        uniqueFileName = md5('' + file.name + file.type + file.lastModifiedDate + file.size);
                    }
                } else {
                    task.reject();   //报错， 跳过
                    alert(data.msg);
                }

            }, function (jqXHR, textStatus, errorThrown) {    //任何形式的验证失败，都触发重新上传
                task.resolve();
                //拿到上传文件的唯一名称，用于断点续传
                uniqueFileName = md5('' + file.name + file.type + file.lastModifiedDate + file.size);
            });
        });
        return $.when(task);
    }
    , beforeSend: function (block) {
        //分片验证是否已传过，用于断点续传
        var task = new $.Deferred();
        $.ajax({
            type: "POST"
            , url: getUrl('check')
            , data: JSON.stringify({
                name: uniqueFileName
                , chunkIndex: block.chunk
                , size: block.end - block.start
            })
            , headers: getHeaders()
            , cache: false
            , dataType: "json"
            , contentType: "application/json",
        }).then(function (data, textStatus, jqXHR) {
            if (data.isSuccess) {
                if (data.data) {   //若存在，返回失败给WebUploader，表明该分块不需要上传
                    task.reject();
                } else {
                    task.resolve();
                }
            } else {
                task.reject();   //报错， 跳过
                alert(data.msg);
            }
        }, function (jqXHR, textStatus, errorThrown) {    //任何形式的验证失败，都触发重新上传
            task.resolve();
        });
        return $.when(task);
    }
    , afterSendFile: function (file) {
        var chunksTotal = 0;
        if ((chunksTotal = Math.ceil(file.size / chunkSize)) > 1) {
            //合并请求
            var task = new $.Deferred();
            $.ajax({
                type: "POST"
                , url: getUrl('merge')
                , data: JSON.stringify({
                    name: uniqueFileName
                    , chunks: chunksTotal
                    , ext: file.ext
                    , md5: userInfo.md5
                    , submittedFileName: file.name
                    , contextType: file.type
                    , size: file.size
                    , folderId: folderId
                })
                , headers: getHeaders()
                , cache: false
                , async: false  // 同步
                , dataType: "json"
                , contentType: "application/json",
            }).then(function (data, textStatus, jqXHR) {
                if (data.isSuccess) {
                    task.resolve();
                    UploadComlate(file);
                } else {
                    task.reject();   //报错， 跳过
                    alert(data.msg);
                }
            }, function (jqXHR, textStatus, errorThrown) {
                task.reject();
            });
            return $.when(task);
        } else {
            UploadComlate(file);
        }
    }
});
var uploader = WebUploader.create({
    swf: "Uploader.swf"
    , server: getUrl('upload')
    , pick: "#picker"
    , resize: false
    , dnd: "#theList"
    , paste: document.body
    , disableGlobalDnd: true
    , thumb: {
        width: 100
        , height: 100
        , quality: 70
        , allowMagnify: true
        , crop: true
    }
    , compress: false
    , prepareNextFile: true
    , chunked: true
    , chunkSize: chunkSize
    , threads: true
    , formData: $.extend(true, {
        folderId: folderId
    }, userInfo)
    // , formData: function(){return $.extend(true, {userId: userId}, userInfo);}
    , headers: getHeaders()
    , fileNumLimit: 1
    , fileSingleSizeLimit: 1000 * 1024 * 1024
    , duplicate: true
});
uploader.on("fileQueued", function (file) {

    $("#theList").append('<li id="' + file.id + '">' +
        '<img /><span>' + file.name + '</span><span class="itemUpload">上传</span><span class="itemStop">暂停</span><span class="itemDel">删除</span>' +
        '<div class="percentage"></div>' +
        '</li>');

    var $img = $("#" + file.id).find("img");

    uploader.makeThumb(file, function (error, src) {
        if (error) {
            $img.replaceWith("<span>不能预览</span>");
        }
        $img.attr("src", src);
    });

});

$("#theList").on("click", ".itemUpload", function () {
    uploader.upload();
    //"上传"-->"暂停"
    $(this).hide();
    $(".itemStop").show();
});
$("#theList").on("click", ".itemStop", function () {
    uploader.stop(true);
    //"暂停"-->"上传"
    $(this).hide();
    $(".itemUpload").show();
});
// 如果要删除的文件正在上传（包括暂停），则需要发送给后端一个请求用来清除服务器端的缓存文件
$("#theList").on("click", ".itemDel", function () {
    uploader.removeFile($(this).parent().attr("id"));	//从上传文件列表中删除
    $(this).parent().remove();	//从上传列表dom中删除
});

uploader.on("uploadProgress", function (file, percentage) {
    $("#" + file.id + " .percentage").text(percentage * 100 + "%");
});

function UploadComlate(file) {
    console.log(file);
    $("#" + file.id + " .percentage").text("上传完毕");
    $(".itemStop").hide();
    $(".itemUpload").hide();
    $(".itemDel").hide();
}
