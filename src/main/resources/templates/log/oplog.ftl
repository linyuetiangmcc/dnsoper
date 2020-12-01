<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" class="form-inline" action="/dns/log/oplog">
                        <div class="input-append date form_datetime">
                            <label>查询时间：</label>
                            <input size="12" type="text" name="dateString" readonly>
                            <span class="add-on"><i class="icon-th"></i></span>
                            <button type="submit" class="btn btn-default">查询</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row clearfix">
                <div class="col-md-12 column" style="overflow:scroll;margin-right: 10px">
                    <table class="table" style="font-size:5px;" >
                        <thead>
                        <tr>
                            <th>操作ID</th>
                            <th>用户ID</th>
                            <th>操作类型</th>
                            <th>操作时间</th>
                            <th>记录类型</th>
                            <th>网元</th>
                            <th>操作</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list opLogs as opLog>
                            <tr>
                                <td>${opLog.opId}</td>
                                <td>${opLog.userId}</td>
                                <td>${opLog.operation}</td>
                                <td>${opLog.createTime}</td>
                                <td>${opLog.recordType}</td>
                                <td>${opLog.comment}</td>
                                <td>
                                        <a href="/dns/log/detail?id=${opLog.id}">详情</a>
                                </td>
                                <td></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="../js/bootstrap-datetimepicker.min.js"></script>
<script>
    $(".form_datetime").datetimepicker({
        forceParse: 0,//设置为0，时间不会跳转1899，会显示当前时间。
        language: 'zh-CN',//显示中文
        format: 'yyyy-mm-dd',//显示格式
        minView: "month",//设置只显示到月份
        initialDate: new Date(),//初始化当前日期
        autoclose: true,//选中自动关闭
        todayBtn: true//显示今日按钮
    });
</script>
</body>
</html>


