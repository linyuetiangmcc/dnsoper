<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                DNS Oper
            </a>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 业务状态 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">查看</li>
                <li><a href="/dns/sgw/check">SGW业务状态</a></li>
                <li><a href="/dns/pwg/check">PGW业务状态</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 隔离业务 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">隔离</li>
                <li><a href="/sell/seller/category/list">SGW隔离</a></li>
                <li><a href="/sell/seller/category/index">PGW隔离</a></li>
            </ul>
        </li>

        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-fw fa-plus"></i> 回割业务 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">回割</li>
                <li><a href="/sell/seller/category/list">SGW回隔</a></li>
                <li><a href="/sell/seller/category/index">PGW回隔</a></li>
            </ul>
        </li>

        <li>
            <a href="/dns/user/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>