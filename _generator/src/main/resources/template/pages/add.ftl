[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]${uncapitalizedClassName}Add[/@layout.put]
    [@layout.put block="title"]${funcName}增加页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>${funcName}增加页面</h1>
    </div>

    <form id="${uncapitalizedClassName}AddForm" action="${'$'}{root}/${moduleName}/${classNames}" method="post" class="super-form-page">
        <#list schema.getKeys() as key>
        <#assign col = schema.get(key) />
        <#if !["id", "createDate", "modifyDate", "isDel"]?seq_contains(col.getCamelColumnName())>
        <div class="form-item">
            <label for="${col.getCamelColumnName()}" class="label-top">${col.getCOLUMN_COMMENT()}：</label>
            <input id="${col.getCamelColumnName()}" name="${col.getCamelColumnName()}" class="easyui-validatebox easyui-textbox" prompt="请输入${col.getCOLUMN_COMMENT()}"
                   data-options="required:true">
        </div>
        </#if>
        </#list>

        <div class="super-form-toolbar">
            <a href="javascript:;" disabled class="easyui-linkbutton btn-submit" data-options="iconCls:'icon-save'">提交</a>
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"]
    <script>
        $(function() {
            $('#${uncapitalizedClassName}AddForm').ajaxForm('POST', function() {
                window.location.href = "${'$'}{root}/${moduleName}/${classNames}/view";
            });
        });
    </script>
    [/@layout.put]
[/@layout.extends]
