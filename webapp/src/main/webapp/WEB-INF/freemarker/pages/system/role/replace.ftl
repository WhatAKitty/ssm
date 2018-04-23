[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]role[#if role??]Edit[#else]Add[/#if][/@layout.put]
    [@layout.put block="title"]角色[#if role??]编辑[#else]增加[/#if]页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>角色[#if role??]编辑[#else]增加[/#if]页面</h1>
    </div>

    <form id="role[#if role??]Edit[#else]Add[/#if]Form" action="${root}/system/roles[#if role??]/${role.id}[/#if]" method="[#if role??]put[#else]post[/#if]" class="super-form-page">
        <div class="form-item" style="">
            <label for="code" class="label-top">编码：</label>
            <input id="code"
                   name="code" class="easyui-validatebox easyui-textbox"
                   prompt="请输入编码"
                   [#if role??]
                   value="${role.code}"
                   [/#if]
                   data-options="required:true">
        </div>
        <div class="form-item" style="">
            <label for="name" class="label-top">名称：</label>
            <input id="name"
                   name="name" class="easyui-validatebox easyui-textbox"
                   prompt="请输入名称"
                   [#if role??]
                   value="${role.name}"
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
                   [#if role??]
                   value="${role.remark!""}"
                   [/#if]
                   data-options="">
        </div>

        <div class="super-form-toolbar">
            <a href="javascript:;" disabled class="easyui-linkbutton btn-submit" data-options="iconCls:'icon-save'">[#if role??]修改[#else]增加[/#if]</a>
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"]
    <script>
        $(function() {
            $('#role[#if role??]Edit[#else]Add[/#if]Form').ajaxForm('[#if role??]PUT[#else]POST[/#if]', function() {
                window.location.href = "${root}/system/roles-view";
            });
        });
    </script>
    [/@layout.put]
[/@layout.extends]
