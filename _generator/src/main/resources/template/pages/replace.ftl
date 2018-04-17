[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]${uncapitalizedClassName}[#if ${uncapitalizedClassName}??]Edit[#else]Add[/#if][/@layout.put]
    [@layout.put block="title"]${funcName}[#if ${uncapitalizedClassName}??]编辑[#else]增加[/#if]页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>${funcName}[#if ${uncapitalizedClassName}??]编辑[#else]增加[/#if]页面</h1>
    </div>

    <form id="${uncapitalizedClassName}[#if ${uncapitalizedClassName}??]Edit[#else]Add[/#if]Form" action="${'$'}{root}/${moduleName}/${classNames}[#if ${uncapitalizedClassName}??]/${'$'}{${uncapitalizedClassName}.id}[/#if]" method="[#if ${uncapitalizedClassName}??]put[#else]post[/#if]" class="super-form-page">
        <#list schema.getKeys() as key>
            <#assign col = schema.get(key) />
            <#if !["id", "createDate", "modifyDate", "isDel"]?seq_contains(col.getCamelColumnName())>
        <div class="form-item" style="<#if col.isLongText()>width:100%;</#if>">
            <label for="${col.getCamelColumnName()}" class="label-top">${col.getCOLUMN_COMMENT()}：</label>
            <input id="${col.getCamelColumnName()}"
                   name="${col.getCamelColumnName()}" class="easyui-validatebox <#if col.getDATA_TYPE() == 'Boolean'>easyui-switchbutton<#else>easyui-textbox</#if>"
                   <#if col.getDATA_TYPE() != 'Boolean'>
                   prompt="请输入${col.getCOLUMN_COMMENT()}"
                   </#if>
                   <#if col.isLongText()>
                   multiline="true"
                   style="width:80%;height:120px"
                   </#if>
                   <#if col.getDATA_TYPE() == 'Boolean'>
                   [#if ${uncapitalizedClassName}??]
                   [#if ${uncapitalizedClassName}.${col.getCamelColumnName()}]
                   checked
                   [/#if]
                   [#else]
                   checked
                   [/#if]
                   <#else>
                   [#if ${uncapitalizedClassName}??]
                   value="${'$'}{${uncapitalizedClassName}.${col.getCamelColumnName()}<#if col.isNullable()>!""</#if>}"
                   [/#if]
                   </#if>
                   data-options="<#if !col.isNullable()>required:true</#if><#if col.getDATA_TYPE() == 'Boolean'>,onText:'开启',offText:'关闭',value:'[#if ${uncapitalizedClassName}??]${'$'}{${uncapitalizedClassName}.${col.getCamelColumnName()}?string('true', 'false')}[#else]true[/#if]'</#if>">
        </div>
            </#if>
        </#list>

        <div class="super-form-toolbar">
            <a href="javascript:;" disabled class="easyui-linkbutton btn-submit" data-options="iconCls:'icon-save'">[#if ${uncapitalizedClassName}??]修改[#else]增加[/#if]</a>
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"]
    <script>
        $(function() {
            $('#${uncapitalizedClassName}[#if ${uncapitalizedClassName}??]Edit[#else]Add[/#if]Form').ajaxForm('[#if ${uncapitalizedClassName}??]PUT[#else]POST[/#if]', function() {
                window.location.href = "${'$'}{root}/${moduleName}/${classNames}/view";
            });
        });
    </script>
    [/@layout.put]
[/@layout.extends]
