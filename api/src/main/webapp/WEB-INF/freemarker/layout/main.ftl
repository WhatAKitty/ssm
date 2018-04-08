[#include "./common/spring-security.ftl"]
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    [#include "./common/meta.ftl"]
    <title>[@layout.block name="title"][/@layout.block]</title>
    [@layout.block name="css"]
        [#include "./common/theme.ftl"]
    [/@layout.block]
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" class="super-north" style="height:50px;">
[#include "component/header.ftl"]
</div>
<div id="easyui-layout-west" data-options="region:'west',title:'菜单',border:false">
    <div class="easyui-accordion" data-options="border:false,fit:true,selected:true">
        [#include "component/menu.ftl"]
    </div>
</div>
<div data-options="region:'center'" style="padding-top:2px;">
[@layout.block name="content"]
    正在开发中...
[/@layout.block]
</div>
[@layout.block name="script"]
    [#include "./common/theme.js.ftl"]
<script>
    $(function () {
        /*退出系统*/
        $("#logout").on('click', function () {
            $.messager.confirm('提示', '确定退出系统？', function (r) {
                if (r) {
                    $.post('/logout', function (data) {
                        window.location.href = data.redirect;
                    });
                }
            });
        });
    });
</script>
[/@layout.block]
</body>
</html>