[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]officeAdd[/@layout.put]
    [@layout.put block="title"]部门列表[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>部门列表页面</h1>
    </div>

    <div id="officeList-toolbar" class="super-list-toolbar super-table-search-wrapper">
        <div class="col-10">
        </div>
        <div class="col-2 content-right">
            <a id="addBtn" href="${root}/system/offices-view/add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
        </div>
    </div>
    <div class="index-l">
        <table
            id="officeList"
            class="easyui-treegrid"
            style="width: 100%"
            data-options="fitColumns:true,url:'${root}/system/offices',method:'get',idField:'id',treeField:'name',parentField:'parentId'"
        >
            <thead>
                <tr>
                    <th data-options="field:'name',width:100">公司/部门名</th>
                    <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    [/@layout.put]
    [@layout.put block="script"]
        <script>
            function formatOper(val,row,index){
                return '<a href="${root}/system/offices-view/' + row.id + '">查看</a>' +
                        '&nbsp;|&nbsp;' +
                        '<a href="${root}/system/offices-view/edit/' + row.id + '">修改</a>' +
                        '&nbsp;|&nbsp;' +
                        '<a href="javascript:;" onclick="deleteRow(' + row.id + ', ' + index + ')">删除</a>';
            }
            function deleteRow(id, index) {
                $.showRemoveDialog('${root}/system/offices/' + id, function() {
                    $('#officeList').datagrid('deleteRow', index)
                });
            }
        </script>
    [/@layout.put]
[/@layout.extends]
