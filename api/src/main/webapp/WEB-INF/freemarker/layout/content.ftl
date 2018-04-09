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
<body>
<div id="[@layout.block name="id"][/@layout.block]" style="padding: 10px 20px">
[@layout.block name="content"]
    正在开发中...
[/@layout.block]
</div>
[@layout.block name="script"]
    [#include "./common/theme.js.ftl"]
[/@layout.block]
</body>