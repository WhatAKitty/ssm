[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]book[/@layout.put]
    [@layout.put block="title"]首页[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>图书列表页面</h1>
    </div>

    <div id="bookList-toolbar" class="super-list-toolbar">
        <div class="col-6">
            <form id="bookSearchForm" action="${root}/demo/books" method="get" class="form-horizontal">
                <div class="form-item">
                    <label for="name" class="label-top">图书名：</label>
                    <input id="name" type="text" class="easyui-textbox" name="name" data-options=""/>
                </div>

                <div class="form-item">
                    <a href="javascript:;" onclick="searchForm()" class="easyui-linkbutton default" data-options="iconCls:'fa fa-search'">搜索</a>
                    <a href="javascript:;" onclick="resetForm()" type="reset" class="easyui-linkbutton default" data-options="iconCls:'fa fa-search'">重置</a>
                </div>
            </form>
        </div>
        <div class="col-6 content-right">
            <a id="addBtn" href="/demo/books-view/add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
        </div>
    </div>
    <div class="index-l">
        <table
            id="bookList"
            class="easyui-datagrid"
            style="width: 100%"
            data-options="pagination:true,fitColumns:true,rownumbers:true,url:'${root}/demo/books',method:'get'"
        >
            <thead>
                <tr>
                    <th data-options="field:'ck',checkbox:true"></th>
                    <th data-options="field:'name',width:100,sortable:true">图书名</th>
                    <th data-options="field:'number',width:100,sortable:true,align:'right'">馆藏数量</th>
                    <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    [/@layout.put]
    [@layout.put block="script"]
        <script>
            function searchForm() {
                var searchObj = $('#bookSearchForm').serializeObject();
                $('#bookList').datagrid('load', searchObj);
            }
            function resetForm() {
                $('#bookSearchForm').form('reset');
                $('#bookList').datagrid('load', {});
            }

            function formatOper(val,row,index){
                return '<a href="${root}/demo/books-view/edit/' + row.id + '">修改</a>' +
                        '&nbsp;|&nbsp;' +
                        '<a href="javascript:;" onclick="deleteRow(' + row.id + ', ' + index + ')">删除</a>';
            }
            function deleteRow(id, index) {
                $.showRemoveDialog('${root}/demo/books/' + id, function() {
                    $('#bookList').datagrid('deleteRow', index)
                });
            }
        </script>
    [/@layout.put]
[/@layout.extends]
