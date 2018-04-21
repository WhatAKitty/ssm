[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]permissionAdd[/@layout.put]
    [@layout.put block="title"]权限列表[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>权限列表页面</h1>
    </div>

    <div id="permissionList-toolbar" class="super-list-toolbar super-table-search-wrapper">
        <div class="col-10">
            <form id="permissionSearchForm" action="${root}/system/permissions" method="get" class="form-horizontal">
                <div id="all" class="super-table-search">
                    <div class="form-item">
                        <label for="code" class="label-top">权限编号：</label>
                        <input id="code" type="text" class="easyui-textbox" name="code" data-options=""/>
                    </div>
                    <div class="form-item">
                        <label for="name" class="label-top">权限名称：</label>
                        <input id="name" type="text" class="easyui-textbox" name="name" data-options=""/>
                    </div>
                    <div class="form-item">
                        <label for="category" class="label-top">权限分类：</label>
                        <input id="category" type="text" class="easyui-textbox" name="category" data-options=""/>
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
            <a id="addBtn" href="${root}/system/permissions-vew/add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
        </div>
    </div>
    <div class="index-l">
        <table
            id="permissionList"
            class="easyui-datagrid"
            style="width: 100%"
            data-options="pagination:true,fitColumns:true,rownumbers:true,url:'${root}/system/permissions',method:'get'"
        >
            <thead>
                <tr>
                    <th data-options="field:'ck',checkbox:true"></th>
                    <th data-options="field:'code',width:100,sortable:true">权限编号</th>
                    <th data-options="field:'name',width:100,sortable:true">权限名称</th>
                    <th data-options="field:'category',width:100,sortable:true">权限分类</th>
                    <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    [/@layout.put]
    [@layout.put block="script"]
        <script>
            function searchForm() {
                var searchObj = $('#permissionSearchForm').serializeObject();
                $('#permissionList').datagrid('load', searchObj);
            }
            function resetForm() {
                $('#permissionSearchForm').form('reset');
                $('#permissionList').datagrid('load', {});
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
                return '<a href="${root}/system/permissions-view/' + row.id + '">查看</a>' +
                        '&nbsp;|&nbsp;' +
                        '<a href="${root}/system/permissions-view/edit/' + row.id + '">修改</a>' +
                        '&nbsp;|&nbsp;' +
                        '<a href="javascript:;" onclick="deleteRow(' + row.id + ', ' + index + ')">删除</a>';
            }
            function deleteRow(id, index) {
                $.showRemoveDialog('${root}/system/permissions/' + id, function() {
                    $('#permissionList').datagrid('deleteRow', index)
                });
            }
        </script>
    [/@layout.put]
[/@layout.extends]
