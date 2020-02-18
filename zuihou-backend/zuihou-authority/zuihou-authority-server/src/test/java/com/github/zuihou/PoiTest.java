package com.github.zuihou;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.enumeration.auth.Sex;
import com.github.zuihou.model.RemoteData;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PoiTest {
    public static final String OUTPUT_PATH = "/Users/tangyh/Downloads/testPoi/";

    private static List<User> USER_LIST = new ArrayList<>();

    static {
        for (int i = 0; i < 50; i++) {
            User user = User.builder()
                    .orgId(new RemoteData<>(Long.valueOf(i), Org.builder().label("org" + i).build()))
                    .stationId(new RemoteData<>(Long.valueOf(i), "stataiin" + i))
                    .account("账号" + i)
                    .name("名字" + i)
                    .mobile("15218869970")
                    .sex(i % 2 == 0 ? Sex.M : Sex.M)
                    .nation(new RemoteData<>("HELLO" + i))
                    .education(new RemoteData<>("xueli" + i, "本科"))
                    .workDescribe("工作描述工作描述工作描述工作描述工作描述工作描述工作描述." + i)
                    .status(true)
                    .createTime(LocalDateTime.now())
                    .lastLoginTime(LocalDateTime.now())
                    .build();

            if (i == 0) {
                user.setSex(null);
            }
            if (i == 1) {
                user.setSex(Sex.N);
            }
            USER_LIST.add(user);
        }
    }


    @Test
    @SneakyThrows
    public void testExport() {
        ExportParams params = new ExportParams(null, "测试", ExcelType.XSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, User.class, USER_LIST);
        File savefile = new File(OUTPUT_PATH);
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(OUTPUT_PATH + "User.xlsx");
        workbook.write(fos);
        fos.close();
    }

    @Test
    public void testGet() {
        User user = USER_LIST.get(0);

        System.out.println(user.getOrg());
    }


    @Test
    public void testImport() {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        List<User> list = ExcelImportUtil.importExcel(new File(OUTPUT_PATH + "User.xlsx"), User.class, params);

        System.out.println(list.size());

    }
}
