<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form">
                        <div class="form-group">
                            <label for="exampleInputEmail1" style="color: blue">id：</label><label for="exampleInputEmail1">${opLog.id}</label>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1" style="color: blue">操作id：</label><label for="exampleInputEmail1">${opLog.opId}</label>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1" style="color: blue">用户名：</label><label for="exampleInputEmail1">${userinfo.username}</label>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1" style="color: blue">操作类型：</label><label for="exampleInputEmail1">${opLog.operation}</label>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1" style="color: blue">记录Raw：</label><p>${opLog.records}</p>
                            <label for="exampleInputEmail1" style="color: blue">操作时间：</label><label for="exampleInputEmail1">${opLog.createTime}</label>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1" style="color: blue">记录类型：</label><label for="exampleInputEmail1">${opLog.recordType}</label>
                        </div><div class="form-group">
                            <label for="exampleInputEmail1" style="color: blue">网元：</label><label for="exampleInputEmail1">${opLog.comment}</label>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="../js/bootstrap-datetimepicker.min.js"></script>
<script>
</script>
</body>
</html>


