[#include "./common/spring-security.ftl"]
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    [#include "./common/meta.ftl"]
    <title>[@layout.block name="title"][/@layout.block]</title>
    [@layout.block name="css"]
        [#include "./common/theme.ftl"]
    [/@layout.block]
    <script>
        // 防止嵌套
        if (self !== top) {
            top.location.href = self.location.href;
        }
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false" class="super-north" style="height:50px;">
[#include "component/header.ftl"]
</div>
<div id="easyui-layout-west" data-options="region:'west',title:'菜单',border:false">
    <div id="aaa" class="easyui-accordion" data-options="border:false,fit:true,selected:true">
    ${menu}
    </div>
</div>
<div data-options="region:'center'" style="padding-top:2px;">
[@layout.block name="content"]
    正在开发中...
[/@layout.block]
</div>
<div id="win">
    <div class="themeItem">
        <ul>
            <li [#if conf.theme == "TURQUOISE"]class="themeActive"[/#if]>
                <div class="TURQUOISE">TURQUOISE</div>
            </li>
            <li [#if conf.theme == "EMERALD"]class="themeActive"[/#if]>
                <div class="EMERALD">EMERALD</div>
            </li>
            <li [#if conf.theme == "PETER-RIVER"]class="themeActive"[/#if]>
                <div class="PETER-RIVER">PETER RIVER</div>
            </li>
            <li [#if conf.theme == "AMETHYST"]class="themeActive"[/#if]>
                <div class="AMETHYST">AMETHYST</div>
            </li>
            <li [#if conf.theme == "WET-ASPHALT"]class="themeActive"[/#if]>
                <div class="WET-ASPHALT">WET ASPHALT</div>
            </li>
            <li [#if conf.theme == "SUN-FLOWER"]class="themeActive"[/#if]>
                <div class="SUN-FLOWER">SUN FLOWER</div>
            </li>
            <li [#if conf.theme == "CARROT"]class="themeActive"[/#if]>
                <div class="CARROT">CARROT</div>
            </li>
            <li [#if conf.theme == "ALIZARIN"]class="themeActive"[/#if]>
                <div class="ALIZARIN">ALIZARIN</div>
            </li>
        </ul>
    </div>
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

    function messager() {
        return $.messager;
    }
</script>
[/@layout.block]
</body>
</html>