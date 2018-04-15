[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]${uncapitalizedClassName}View[/@layout.put]
    [@layout.put block="title"]${funcName}查看页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>${funcName}查看页面</h1>
    </div>

    <form id="${uncapitalizedClassName}ViewForm" class="super-form-page">
        <#list schema.getKeys() as key>
        <#assign col = schema.get(key) />
        <#if !["id", "createDate", "modifyDate", "isDel"]?seq_contains(col.getCamelColumnName())>
        <div class="form-item">
            <label for="${col.getCamelColumnName()}" class="label-top">${col.getCOLUMN_COMMENT()}：</label>
            <#if col.getDATA_TYPE() == 'Boolean'>
            <input class="easyui-switchbutton" [#if ${uncapitalizedClassName}.${col.getCamelColumnName()}]checked[/#if] data-options="disabled:true,onText:'开启',offText:'关闭'">
            <#else>
            ${'$'}{${uncapitalizedClassName}.${col.getCamelColumnName()}}
            </#if>
        </div>
    </#if>
        </#list>

        <div class="super-form-toolbar">
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"][/@layout.put]
[/@layout.extends]
