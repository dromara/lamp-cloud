<h1>系统状态</h1>
<div class="row">
    <div class="col-md-6">
        <table id='instances' class="table table-condensed table-striped table-hover">
            <#if amazonInfo??>
                <tr>
                    <td>EUREKA 服务器</td>
                    <td>AMI: ${amiId!}</td>
                </tr>
                <tr>
                    <td>区域</td>
                    <td>${availabilityZone!}</td>
                </tr>
                <tr>
                    <td>实例id</td>
                    <td>${instanceId!}</td>
                </tr>
            </#if>
            <tr>
                <td>环境</td>
                <td>${environment!}</td>
            </tr>
            <tr>
                <td>数据中心</td>
                <td>${datacenter!}</td>
            </tr>
        </table>
    </div>
    <div class="col-md-6">
        <table id='instances' class="table table-condensed table-striped table-hover">
            <tr>
                <td>当前时间</td>
                <td>${currentTime}</td>
            </tr>
            <tr>
                <td>启动时长</td>
                <td>${upTime}</td>
            </tr>
            <tr>
                <td>租约到期后启用</td>
                <td>${registry.leaseExpirationEnabled?c}</td>
            </tr>
            <tr>
                <td>更新阈值</td>
                <td>${registry.numOfRenewsPerMinThreshold}</td>
            </tr>
            <tr>
                <td>更新 (最后一分钟)</td>
                <td>${registry.numOfRenewsInLastMin}</td>
            </tr>
        </table>
    </div>
</div>

<#if isBelowRenewThresold>
    <#if !registry.selfPreservationModeEnabled>
        <h4 id="uptime"><font size="+1" color="red"><b>续约低于阀值. 自我保护模式关闭. 对于网络/其他问题，这可能无法保护实例过期.</b></font></h4>
    <#else>
        <h4 id="uptime"><font size="+1" color="red"><b>注意! EUREKA 可能对未启动的实例声称为启动状态. 续约低于阀值并且因此，为了安全，实例不会过期.</b></font>
        </h4>
    </#if>
<#elseif !registry.selfPreservationModeEnabled>
    <h4 id="uptime"><font size="+1" color="red"><b>自我保护模式关闭.对于网络/其他问题，这可能无法保护实例过期.</b></font></h4>
</#if>

<h1>DS 副本</h1>
<ul class="list-group">
    <#list replicas as replica>
        <li class="list-group-item"><a href="${replica.value}">${replica.key}</a></li>
    </#list>
</ul>