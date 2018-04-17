[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]userAdd[/@layout.put]
    [@layout.put block="title"]用户列表[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>用户列表页面</h1>
    </div>

    <div id="roleList-toolbar" class="super-list-toolbar super-table-search-wrapper">
        <div class="col-10">
            <form id="userSearchForm" action="${root}/system/users" method="get" class="form-horizontal">
                <div id="all" class="super-table-search">
                    <div class="form-item">
                        <label for="username" class="label-top">用户名：</label>
                        <input id="username" type="text" class="easyui-textbox" name="username" data-options=""/>
                    </div>
                    <div class="form-item">
                        <label for="name" class="label-top">姓名：</label>
                        <input id="name" type="text" class="easyui-textbox" name="name" data-options=""/>
                    </div>
                </div>
                <div class="form-item">
                    <a href="javascript:;" onclick="toggleMore()" id="more" class="easyui-linkbutton default" data-options="iconCls:'fa fa-compress'">展开</a>
                    <a href="javascript:;" onclick="searchForm()" class="easyui-linkbutton default" data-options="iconCls:'fa fa-search'">搜索</a>
                    <a href="javascript:;" onclick="resetForm()" type="reset" class="easyui-linkbutton default" data-options="iconCls:'fa fa-undo'">重置</a>
                </div>
            </form>
        </div>
        <div class="col-2 content-right">
            <a id="addBtn" href="${root}/system/users/add/view" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
        </div>
    </div>
    <div class="index-l">
        <table
            id="userList"
            class="easyui-datagrid"
            style="width: 100%"
            data-options="pagination:true,fitColumns:true,rownumbers:true,url:'${root}/system/users',method:'get'"
        >
            <thead>
                <tr>
                    <th data-options="field:'ck',checkbox:true"></th>
                    <th data-options="field:'username',width:100,sortable:true">用户名</th>
                    <th data-options="field:'password',width:100,sortable:true">密码</th>
                    <th data-options="field:'name',width:100,sortable:true">姓名</th>
                    <th data-options="field:'isExpired',width:100,sortable:true">是否过期</th>
                    <th data-options="field:'isLocked',width:100,sortable:true">是否被锁定</th>
                    <th data-options="field:'isEnabled',width:100,sortable:true">是否启用</th>
                    <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    [/@layout.put]
    [@layout.put block="script"]
        <script>
            function searchForm() {
                var searchObj = $('#userSearchForm').serializeObject();
                console.log(searchObj)
                $('#userList').datagrid('load', searchObj);
            }
            function resetForm() {
                $('#userSearchForm').form('reset');
                $('#userList').datagrid('load', {});
            }
            function toggleMore() {
                var $all = $('#all'),
                        $more = $('#more'),
                        $text = $more.find('.l-btn-text'),
                        $icon = $more.find('.l-btn-icon.fa');
                if ($all.hasClass("opened")) {
                    $all.removeClass("opened");
                    $text.text('展开');
                    $icon.removeClass("fa-expand");
                    $icon.addClass("fa-compress");
                } else {
                    $all.addClass("opened");
                    $text.text('收缩');
                    $icon.removeClass("fa-compress");
                    $icon.addClass("fa-expand");
                }
            }

            function formatOper(val,row,index){
                return '<a href="${root}/system/users/view/' + row.id + '">查看</a>' +
                        '&nbsp;|&nbsp;' +
                        '<a href="${root}/system/users/edit/view/' + row.id + '">修改</a>' +
                        '&nbsp;|&nbsp;' +
                        '<a href="javascript:;" onclick="deleteRow(' + row.id + ', ' + index + ')">删除</a>';
            }
            function deleteRow(id, index) {
                $.showRemoveDialog('${root}/system/users/' + id, function() {
                    $('#userList').datagrid('deleteRow', index)
                });
            }
        </script>
    [/@layout.put]
[/@layout.extends]
