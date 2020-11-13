<html>
<#include "../common/header.ftl">
<body>
<br>
<br>
<h1>
    <strong>${msg!""}</strong>
</h1>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-4 column">
            <form role="form" title="登录信息" method="post" action="/dns/user/loginAction">
                <div class="form-group">
                    <label>用户名</label><input type="text" class="form-control" name="username"/>
                </div>
                <div class="form-group">
                    <label>密码</label><input type="password" class="form-control" name="password"/>
                </div>
                <button type="submit" class="btn btn-default">登录</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>