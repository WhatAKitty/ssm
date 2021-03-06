[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]${uncapitalizedClassName}Add[/@layout.put]
    [@layout.put block="title"]${funcName}列表[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>${funcName}列表页面</h1>
    </div>

    <div id="${uncapitalizedClassName}List-toolbar" class="super-list-toolbar super-table-search-wrapper">
        <div class="col-10">
            <form id="${uncapitalizedClassName}SearchForm" action="${'$'}{root}/${moduleName}/${classNames}" method="get" class="form-horizontal">
                <div id="all" class="super-table-search">
                    <#list schema.getKeys() as key>
                    <#assign col = schema.get(key) />
                    <#if !["id", "createDate", "modifyDate", "isDel", "remark"]?seq_contains(col.getCamelColumnName())>
                    <div class="form-item">
                        <label for="${col.getCamelColumnName()}" class="label-top">${col.getCOLUMN_COMMENT()}：</label>
                        <input id="${col.getCamelColumnName()}" type="text" class="easyui-textbox" name="${col.getCamelColumnName()}" data-options=""/>
                    </div>
                    </#if>
                    </#list>
                </div>
                <div class="form-item">
                    <a href="javascript:;" onclick="toggleMore()" id="more" class="easyui-linkbutton default" data-options="iconCls:'fa fa-compress'">展开</a>
                    <a href="javascript:;" onclick="searchForm()" class="easyui-linkbutton default" data-options="iconCls:'fa fa-search'">搜索</a>
                    <a href="javascript:;" onclick="resetForm()" type="reset" class="easyui-linkbutton default" data-options="iconCls:'fa fa-undo'">重置</a>
                </div>
            </form>
        </div>
        <div class="col-2 content-right">
            <a id="addBtn" href="${'$'}{root}/${moduleName}/${classNames}-view/add" class="easyui-linkbutton" data-options="iconCls:'icon-add'">增加</a>
        </div>
    </div>
    <div class="index-l">
        <table
            id="${uncapitalizedClassName}List"
            class="easyui-datagrid"
            style="width: 100%"
            data-options="pagination:true,fitColumns:true,rownumbers:true,url:'${'$'}{root}/${moduleName}/${classNames}',method:'get'"
        >
            <thead>
                <tr>
                    <th data-options="field:'ck',checkbox:true"></th>
                    <#list schema.getKeys() as key>
                    <#assign col = schema.get(key) />
                    <#if !["id", "createDate", "modifyDate", "isDel", "remark"]?seq_contains(col.getCamelColumnName())>
                    <th data-options="field:'${col.getCamelColumnName()}',width:100,sortable:true">${col.getCOLUMN_COMMENT()}</th>
                    </#if>
                    </#list>
                    <th data-options="field:'_operate',width:80,align:'center',formatter:formatOper">操作</th>
                </tr>
            </thead>
        </table>
    </div>
    [/@layout.put]
    [@layout.put block="script"]
        <script>
            function searchForm() {
                var searchObj = $('#${uncapitalizedClassName}SearchForm').serializeObject();
                $('#${uncapitalizedClassName}List').datagrid('load', searchObj);
            }
            function resetForm() {
                $('#${uncapitalizedClassName}SearchForm').form('reset');
                $('#${uncapitalizedClassName}List').datagrid('load', {});
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
                return '<a href="${'$'}{root}/${moduleName}/${classNames}-view/' + row.id + '">查看</a>' +
                        '&nbsp;|&nbsp;' +
                        '<a href="${'$'}{root}/${moduleName}/${classNames}-view/edit/' + row.id + '">修改</a>' +
                        '&nbsp;|&nbsp;' +
                        '<a href="javascript:;" onclick="deleteRow(' + row.id + ', ' + index + ')">删除</a>';
            }
            function deleteRow(id, index) {
                $.showRemoveDialog('${'$'}{root}/${moduleName}/${classNames}/' + id, function() {
                    $('#${uncapitalizedClassName}List').datagrid('deleteRow', index)
                });
            }
        </script>
    [/@layout.put]
[/@layout.extends]
