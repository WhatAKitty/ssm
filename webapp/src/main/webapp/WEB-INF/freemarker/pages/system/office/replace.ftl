[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]office[#if office??]Edit[#else]Add[/#if][/@layout.put]
    [@layout.put block="title"]部门[#if office??]编辑[#else]增加[/#if]页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>部门[#if office??]编辑[#else]增加[/#if]页面</h1>
    </div>

    <form id="office[#if office??]Edit[#else]Add[/#if]Form" action="${root}/system/offices[#if office??]/${office.id}[/#if]" method="[#if office??]put[#else]post[/#if]" class="super-form-page">
        <div class="form-item">
            <label for="name" class="label-top">公司/部门名：</label>
            <input id="name"
                   name="name" class="easyui-validatebox easyui-textbox"
                   prompt="请输入公司/部门名"
                   [#if office??]
                   value="${office.name}"
                   [/#if]
                   data-options="required:true">
        </div>
        [#if office??]
        <input type="hidden" name="parentId" value="${office.parentId}" />
        [#else]
        <div class="form-item" style="">
            <label for="parentId" class="label-top">上级公司/部门：</label>
            <input id="parentId"
                   name="parentId" class="easyui-validatebox easyui-combotree"
                   prompt="请选择上级公司/部门"
                   [#if office??]
                   value="${office.parentId}"
                   [/#if]
                   data-options="
                   required:true,
                   url:'${root}/system/offices',
                   method:'get',
                   textField:'name',
                   formatter: officeFormatter,

                   parentField:'parentId'
                   "
            >
        </div>
        [/#if]
        <div class="form-item" style="width:100%;">
            <label for="remark" class="label-top">备注：</label>
            <input id="remark"
                   name="remark" class="easyui-validatebox easyui-textbox"
                   prompt="请输入备注"
                   multiline="true"
                   style="width:80%;height:120px"
                   [#if office??]
                   value="${office.remark!""}"
                   [/#if]
                   data-options="">
        </div>

        <div class="super-form-toolbar">
            <a href="javascript:;" disabled class="easyui-linkbutton btn-submit" data-options="iconCls:'icon-save'">[#if office??]修改[#else]增加[/#if]</a>
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"]
    <script>
        function officeFormatter(node) {
            return node.name;
        }

        $(function() {
            $('#office[#if office??]Edit[#else]Add[/#if]Form').ajaxForm('[#if office??]PUT[#else]POST[/#if]', function() {
                window.location.href = "${root}/system/offices-view";
            });
        });
    </script>
    [/@layout.put]
[/@layout.extends]
