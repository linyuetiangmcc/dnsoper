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
                            <label for="exampleInputEmail1" style="color: blue">用户id：</label><label for="exampleInputEmail1">${userinfo.userId}</label>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1" style="color: blue">用户名：</label><label for="exampleInputEmail1">${userinfo.username}</label>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1" style="color: blue">邮箱：</label><label for="exampleInputEmail1">${userinfo.email}</label>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1" style="color: blue">手机号码：</label><label for="exampleInputEmail1">${userinfo.tel}</label>
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


