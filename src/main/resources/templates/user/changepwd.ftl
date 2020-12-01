<html>
<#include "../common/header.ftl">
<body>
<br>
<br>
<div id="wrapper" class="toggled">
    <#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <label style="color: red"><strong>${msg!""}</strong></label>
            <div class="row clearfix">
                <div class="col-md-4 column">
                </div>
                <div class="col-md-4 column">
                    <form role="form" title="修改密码" method="post" action="/dns/user/changepwdAction">
                        <div class="form-group">
                            <label>旧密码</label><input type="password" class="form-control" name="oldpassword"/>
                        </div>
                        <div class="form-group">
                            <label>新密码</label><input type="password" class="form-control" name="newpassword"/>
                        </div><div class="form-group">
                            <label>新密码确认</label><input type="password" class="form-control" name="newpasswordconfirm"/>
                        </div>
                        <button type="submit" class="btn btn-default">确认修改</button>
                    </form>
                </div>
                <div class="col-md-4 column">
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>