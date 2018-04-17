[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]permissionView[/@layout.put]
    [@layout.put block="title"]权限查看页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>权限查看页面</h1>
    </div>

    <form id="permissionViewForm" class="super-form-page">
        <div class="form-item">
            <label for="code" class="label-top">权限编号：</label>
            ${permission.code}
        </div>
        <div class="form-item">
            <label for="name" class="label-top">权限名称：</label>
            ${permission.name}
        </div>
        <div class="form-item">
            <label for="category" class="label-top">权限分类：</label>
            ${permission.category}
        </div>
        <div class="form-item">
            <label for="remark" class="label-top">备注：</label>
            ${permission.remark!"-"}
        </div>

        <div class="super-form-toolbar">
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"][/@layout.put]
[/@layout.extends]
