[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]roleAdd[/@layout.put]
    [@layout.put block="title"]角色列表[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>角色列表页面</h1>
    </div>

    <div id="roleList-toolbar" class="super-list-toolbar">
        <div class="col-10">
            <form id="roleSearchForm" action="${root}/system/roles" method="get" class="form-horizontal">

                <div class="form-item">
                    <label for="code" class="label-top">编码：</label>
                    <input id="code" type="text" class="easyui-textbox" name="code" data-options=""/>
                </div>
                <div class="form-item">
                    <label for="name" class="label-top">名称：</label>
                    <input id="name" type="text" class="easyui-textbox" name="name" data-options=""/>
                </div>

                <div class="form-item">
                    <a href="javascript:;" onclick="searchForm()" class="easyui-linkbutton default" data-options="iconCls:'fa fa-search'">搜索</a>
                    <a href="javascript:;" onclick="resetForm()" type="reset" class="easyui-linkbutton default" data-options="iconCls:'fa fa-search'">重置</a>
                </div>
            </form>
        </div>
        <div class="col-2 content-right">
            <a id="addBtn" href="${root}/system/roles/add/view" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
        </div>
    </div>
    <div class="index-l">
        <table
            id="roleList"
            class="easyui-datagrid"
            style="width: 100%"
            data-options="pagination:true,fitColumns:true,rownumbers:true,url:'${root}/system/roles',method:'get'"
        >
            <thead>
                <tr>
                    <th data-options="field:'ck',checkbox:true"></th>
                    <th data-options="field:'code',width:100,sortable:true">编码</th>
                    <th data-options="field:'name',width:100,sortable:true">名称</th>
                    <th data-options="field:'remark',width:100,sortable:true">备注</th>
                    <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    [/@layout.put]
    [@layout.put block="script"]
        <script>
            function searchForm() {
                var searchObj = $('#roleSearchForm').serializeObject();
                $('#roleList').datagrid('load', searchObj);
            }
            function resetForm() {
                $('#roleSearchForm').form('reset');
                $('#roleList').datagrid('load', {});
            }

            function formatOper(val,row,index){
                return '<a href="${root}/system/roles/view/' + row.id + '">查看</a>' +
                        '&nbsp;|&nbsp;' +
                        '<a href="${root}/system/roles/edit/view/' + row.id + '">修改</a>' +
                        '&nbsp;|&nbsp;' +
                        '<a href="javascript:;" onclick="deleteRow(' + row.id + ', ' + index + ')">删除</a>';
            }
            function deleteRow(id, index) {
                $.showRemoveDialog('${root}/system/roles/' + id, function() {
                    $('#roleList').datagrid('deleteRow', index)
                });
            }
        </script>
    [/@layout.put]
[/@layout.extends]
