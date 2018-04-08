<!DOCTYPE html>
<html lang="zh-CN">
<head>
    [#include "./common/meta.ftl"]
    <title>[@layout.block name="title"][/@layout.block]</title>
    [@layout.block name="css"]
        [#include "./common/theme.ftl"]
    <link rel="stylesheet" type="text/css" href="${root}/resources/css/animate.min.css">
    <link rel="stylesheet" type="text/css" href="${root}/resources/css/login.css">
    [/@layout.block]
</head>
<body>
<div id="login-body" class="animated fadeInDown">
    <div class="project-icon-div">
        <span class="fa fa-leaf"></span>
    </div>
    <div class="welcome-div">
        <span class="welcome-tip"></span>
    </div>
    <div class="login-form-div">
        <form id="form" action="[@layout.block name="actionUrl"][/@layout.block]" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <p>
                <input type="text" name="username" class="easyui-textbox" data-options="prompt:'用户名'"/>
            </p>
            <p>
                <input type="text" name="password" class="easyui-passwordbox" data-options="prompt:'密码'"/>
            </p>
            <p>
                <label class="remember_me">
                    <input name="rememberMe" type="checkbox"/> 记住我?
                </label>
            </p>
            <p>
                <button type="submit" class="easyui-linkbutton login-btn">登录系统</button>
            </p>
        </form>
        [#if RequestParameters.error??]
        <div class="error-tip">
            <i class="fa fa-info-circle"></i> 用户名或密码错误.
        </div>
        [/#if]
    </div>
    <span class="copyright-text">
        &copy; 2018 <a href="https://xuqiang.me">xuqiang.me</a>
    </span>
</div>
[@layout.block name="script"]
    [#include "./common/theme.js.ftl"]
    <script type="text/javascript">
        $(function () {
            /*登录系统*/
            $('#form').submit(function () {
                if ($('input[name=username]').val() != 'admin') {
                    $('.error-tip').fadeIn(200);
                    return false;
                }
                return true;
            });
        });
    </script>
[/@layout.block]
</body>
</html>