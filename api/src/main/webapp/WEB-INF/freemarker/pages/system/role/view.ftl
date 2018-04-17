[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]roleView[/@layout.put]
    [@layout.put block="title"]角色查看页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>角色查看页面</h1>
    </div>

    <form id="roleViewForm" class="super-form-page">
        <div class="form-item">
            <label for="code" class="label-top">编码：</label>
            ${role.code}
        </div>
        <div class="form-item">
            <label for="name" class="label-top">名称：</label>
            ${role.name}
        </div>
        <div class="form-item">
            <label for="remark" class="label-top">备注：</label>
            ${role.remark!"-"}
        </div>

        <div class="super-form-toolbar">
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"][/@layout.put]
[/@layout.extends]
