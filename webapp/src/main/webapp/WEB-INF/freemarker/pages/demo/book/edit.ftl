[@layout.extends name="./layout/content.ftl"]
    [@layout.put block="id"]bookEdit[/@layout.put]
    [@layout.put block="title"]图书编辑页面[/@layout.put]
    [@layout.put block="css"][/@layout.put]
    [@layout.put block="content" type="replace"]
    <div class="super-list-title-wrapper">
        <h1>图书编辑页面</h1>
    </div>

    <form id="bookAddForm" action="${root}/demo/books" method="put" class="super-form-page">
        <div class="form-item">
            <label for="name" class="label-top">图书名称：</label>
            <input id="name" name="name" class="easyui-validatebox easyui-textbox" prompt="请输入图书名"
                   value="${book.name}"
                   data-options="required:true,validType:'length[1,20]'">
        </div>
        <div class="form-item">
            <label for="number" class="label-top">馆藏数量：</label>
            <input id="number" name="number" class="easyui-numberspinner" required="required"
                   value="${book.number}"
                   data-options="min:1,max:100">
        </div>

        <div class="super-form-toolbar">
            <a href="javascript:;" disabled class="easyui-linkbutton btn-submit" data-options="iconCls:'icon-save'">修改</a>
            <a href="javascript:;" class="easyui-linkbutton btn-back" data-options="iconCls:'icon-back'">返回</a>
        </div>
    </form>
    [/@layout.put]
    [@layout.put block="script"]
    <script>
        $(function() {
            $('#bookAddForm').ajaxForm('POST', function() {
                window.location.href = "${root}/demo/books/view";
            });
        });
    </script>
    [/@layout.put]
[/@layout.extends]
