[@layout.extends name="./layout/main.ftl"]
[@layout.put block="title"]首页[/@layout.put]
[@layout.put block="logo"]非普公司管理系统[/@layout.put]
[@layout.put block="content" type="replace"]
<!--主要内容-->
<div id="tt" class="easyui-tabs" data-options="border:false,fit:true">
    <div title="首页" data-options="iconCls:'fa fa-home'">
        Welcome, anyone!
    </div>
</div>
[/@layout.put]
[/@layout.extends]