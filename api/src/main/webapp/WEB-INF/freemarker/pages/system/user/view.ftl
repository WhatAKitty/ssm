[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]userView[/@layout.put]
    [@layout.put block="title"]用户查看页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>用户查看页面</h1>
    </div>

    <form id="userViewForm" class="super-form-page">
        <div class="form-item">
            <label for="username" class="label-top">用户名：</label>
            ${user.username}
        </div>
        <div class="form-item">
            <label for="password" class="label-top">密码：</label>
            ${user.password}
        </div>
        <div class="form-item">
            <label for="name" class="label-top">姓名：</label>
            ${user.name}
        </div>
        <div class="form-item">
            <label for="isExpired" class="label-top">是否过期：</label>
            <input class="easyui-switchbutton" [#if user.isExpired]checked[/#if] data-options="disabled:true,onText:'已过期',offText:'未过期'">
        </div>
        <div class="form-item">
            <label for="isLocked" class="label-top">是否被锁定：</label>
            <input class="easyui-switchbutton" [#if user.isLocked]checked[/#if] data-options="disabled:true,onText:'已锁定',offText:'未锁定'">
        </div>
        <div class="form-item">
            <label for="isEnabled" class="label-top">是否启用：</label>
            <input class="easyui-switchbutton" [#if user.isEnabled]checked[/#if] data-options="disabled:true,onText:'已启用',offText:'未启用'">
        </div>

        <div class="super-form-toolbar">
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"][/@layout.put]
[/@layout.extends]
