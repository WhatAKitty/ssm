[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]permission[#if permission??]Edit[#else]Add[/#if][/@layout.put]
    [@layout.put block="title"]权限[#if permission??]编辑[#else]增加[/#if]页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>权限[#if permission??]编辑[#else]增加[/#if]页面</h1>
    </div>

    <form id="permission[#if permission??]Edit[#else]Add[/#if]Form" action="${root}/system/permissions[#if permission??]/${permission.id}[/#if]" method="[#if permission??]put[#else]post[/#if]" class="super-form-page">
        <div class="form-item" style="">
            <label for="code" class="label-top">权限编号：</label>
            <input id="code"
                   name="code" class="easyui-validatebox easyui-textbox"
                   prompt="请输入权限编号"
                   [#if permission??]
                   value="${permission.code}"
                   [/#if]
                   data-options="required:true">
        </div>
        <div class="form-item" style="">
            <label for="name" class="label-top">权限名称：</label>
            <input id="name"
                   name="name" class="easyui-validatebox easyui-textbox"
                   prompt="请输入权限名称"
                   [#if permission??]
                   value="${permission.name}"
                   [/#if]
                   data-options="required:true">
        </div>
        <div class="form-item" style="">
            <label for="category" class="label-top">权限分类：</label>
            <input id="category"
                   name="category" class="easyui-validatebox easyui-textbox"
                   prompt="请输入权限分类"
                   [#if permission??]
                   value="${permission.category}"
                   [/#if]
                   data-options="required:true">
        </div>
        <div class="form-item" style="width:100%;">
            <label for="remark" class="label-top">备注：</label>
            <input id="remark"
                   name="remark" class="easyui-validatebox easyui-textbox"
                   prompt="请输入备注"
                   multiline="true"
                   style="width:80%;height:120px"
                   [#if permission??]
                   value="${permission.remark!""}"
                   [/#if]
                   data-options="">
        </div>

        <div class="super-form-toolbar">
            <a href="javascript:;" disabled class="easyui-linkbutton btn-submit" data-options="iconCls:'icon-save'">[#if permission??]修改[#else]增加[/#if]</a>
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"]
    <script>
        $(function() {
            $('#permission[#if permission??]Edit[#else]Add[/#if]Form').ajaxForm('[#if permission??]PUT[#else]POST[/#if]', function() {
                window.location.href = "${root}/system/permissions-view";
            });
        });
    </script>
    [/@layout.put]
[/@layout.extends]
