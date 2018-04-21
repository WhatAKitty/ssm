[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]user[#if user??]Edit[#else]Add[/#if][/@layout.put]
    [@layout.put block="title"]用户[#if user??]编辑[#else]增加[/#if]页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>用户[#if user??]编辑[#else]增加[/#if]页面</h1>
    </div>

    <form id="user[#if user??]Edit[#else]Add[/#if]Form" action="${root}/system/users[#if user??]/${user.id}[/#if]" method="[#if user??]put[#else]post[/#if]" class="super-form-page">
        <div class="form-item">
            <label for="username" class="label-top">用户名：</label>
            <input id="username" name="username" class="easyui-validatebox easyui-textbox"
                   [#if user??]
                   readonly
                   [/#if]
                   prompt="请输入用户名"
                   [#if user??]
                   value="${user.username}"
                   [/#if]
                   data-options="required:true">
        </div>
        <div class="form-item">
            <label for="password" class="label-top">密码：</label>
            <input id="password" name="password" class="easyui-validatebox easyui-textbox"
                   prompt="请输入密码"
                   [#if user??]
                   value="${user.password}"
                   [/#if]
                   data-options="required:true">
        </div>
        <div class="form-item">
            <label for="name" class="label-top">姓名：</label>
            <input id="name" name="name" class="easyui-validatebox easyui-textbox"
                   prompt="请输入姓名"
                   [#if user??]
                   value="${user.name}"
                   [/#if]
                   data-options="required:true">
        </div>
        <div class="form-item">
            <label for="isEnabled" class="label-top">是否启用：</label>
            <input id="isEnabled" name="isEnabled" class="easyui-validatebox easyui-switchbutton"
                   [#if user??]
                   [#if user.isEnabled]
                   checked
                   [/#if]
                   [#else]
                   checked
                   [/#if]
                   data-options="required:true,onText:'启用',offText:'禁用',value:'[#if user??]${user.isEnabled?string('true', 'false')}[#else]true[/#if]'">
        </div>

        <fieldset>
            <legend>用户角色选择</legend>
            <ul class="checkbox-group">
                [#list roles as role]
                <li><label><input type="checkbox" name="roles" value="${role.id}" [#if userRoles??][#if userRoles?seq_contains(role.id)]checked[/#if][/#if] />${role.name}</label></li>
                [/#list]
            </ul>
        </fieldset>

        <div class="super-form-toolbar">
            <a href="javascript:;" disabled class="easyui-linkbutton btn-submit" data-options="iconCls:'icon-save'">[#if user??]修改[#else]增加[/#if]</a>
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"]
    <script>
        $(function() {
            $('#user[#if user??]Edit[#else]Add[/#if]Form').ajaxForm('[#if user??]PUT[#else]POST[/#if]', function() {
                window.location.href = "${root}/system/users/view";
            }, null, function(data) {
                // 如果解析为单个，则转化为数组
                if (!data.roles.push) {
                    data.roles = [data.roles];
                }
                return data;
            });
        });
    </script>
    [/@layout.put]
[/@layout.extends]
