<!--顶部-->
<div class="super-navigation">
    <div class="super-navigation-title">[@layout.block name="logo"][/@layout.block]</div>
    <div class="super-navigation-main">
        <div class="super-setting-left">
            <ul>
                [#if conf.insiteMessager]
                <li><i class="fa fa-commenting-o"></i></li>
                [/#if]
                [#if conf.insiteEmail]
                <li><i class="fa fa-envelope-o"></i></li>
                [/#if]
                [#if conf.alert]
                <li><i class="fa fa-bell-o"></i></li>
                [/#if]
            </ul>
        </div>
        <div class="super-setting-right">
            <ul>
                <li class="user">
                    <span class="user-icon"><img src="${root}/resources/images/favicon.png"/></span>
                    <span>管理员</span>
                </li>
                <li>
                    <div class="super-setting-icon">
                        <i class="fa fa-gears"></i>
                    </div>
                    <div id="mm" class="easyui-menu">
                        <div>个人中心</div>
                        <div id="themeSetting">主题</div>
                        <div class="menu-sep"></div>
                        <div id="logout">退出</div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>