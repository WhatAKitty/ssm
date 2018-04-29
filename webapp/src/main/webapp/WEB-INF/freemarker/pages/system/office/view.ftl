[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]officeView[/@layout.put]
    [@layout.put block="title"]部门查看页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>部门查看页面</h1>
    </div>

    <form id="officeViewForm" class="super-form-page">
        <div class="form-item">
            <label for="name" class="label-top">公司/部门名：</label>
            ${office.name}
        </div>
        <div class="form-item">
            <label for="parentId" class="label-top">上级公司/部门编号：</label>
            ${office.parentId}
        </div>
        <div class="form-item">
            <label for="remark" class="label-top">备注：</label>
            ${office.remark!"-"}
        </div>

        <div class="super-form-toolbar">
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"][/@layout.put]
[/@layout.extends]
