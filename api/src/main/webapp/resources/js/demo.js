$(function () {
    $("#myWin-btn").on('click', function () {
        $('#myWin').window({
            width: 500,
            height: 300,
            modal: true,
            constrain: true,
            toolbar: [{
                text: '编辑',
                iconCls: 'fa fa-cubes',
                handler: function () {
                    alert('edit')
                }
            }, {
                text: '帮助',
                iconCls: 'fa fa-envelope',
                handler: function () {
                    alert('help')
                }
            }],
            buttons: [{
                text: '保存',
                handler: function () {
                }
            }, {
                text: '关闭',
                handler: function () {
                }
            }]
        });
    });
    $("#myWin-btn2").on('click', function () {
        $.messager.confirm('确认', '您确认想要删除记录吗？', function (r) {
            if (r) {
                alert('确认删除');
            }
        });
    });
    $("#myWin-btn3").on('click', function () {
        //error,question,info,warning
        $.messager.alert('警告', '警告消息', 'warning', function () {
            $.messager.alert('警告', '警告消息2', 'error');
        });
    });
    $("#myWin-btn5").on('click', function () {
        $.messager.prompt('提示信息', '请输入姓名：', function (r) {
            if (r) {
                alert('你的姓名是：' + r);
            }
        });
    });
    $("#myWin-btn4").on('click', function () {
        $.messager.show({
            title: '我的消息',
            msg: '消息将在3秒后关闭。',
            timeout: 3000,
            showType: 'slide'
        });
    });
    $('#dg').datagrid({
        url: window.root + '/demo/datagrid_data',
        pagination: true,
        fitColumns: true,
        height: 400,
        columns: [
            [{
                field: 'productid',
                title: 'productid',
                width: 100,
                sortable: true
            }, {
                field: 'productname',
                title: 'productname',
                width: 100,
                sortable: true
            }, {
                field: 'unitcost',
                title: 'unitcost',
                width: 100,
                align: 'right',
                sortable: true
            }, {
                field: 'status',
                title: 'status',
                width: 100,
                align: 'right'
            }, {
                field: 'listprice',
                title: 'listprice',
                width: 100,
                align: 'right'
            }, {
                field: 'attr1',
                title: 'attr1',
                width: 100,
                align: 'right'
            }, {
                field: 'itemid',
                title: 'itemid',
                width: 100,
                align: 'right'
            }]
        ]
    });
    $(function () {
        /*平滑数据*/
        var tree_data2 = [
            {"id": "1", "text": "A"},
            {"id": "11", "text": "A1", "parentId": "1"},
            {"id": "12", "text": "A2", "parentId": "1"},
            {"id": "13", "text": "A3", "parentId": "1"},
            {"id": "2", "text": "B"},
            {"id": "3", "text": "C"}
        ];
        $('#tree').tree({
            //data: tree_data2,
            url: window.root + '/demo/tree_data',
            parentField: 'parentId',
            checkbox: true,
            animate: true
        });
        /*树形表格*/
        $('#ttg').treegrid({
            url: window.root + '/demo/tree_grid_data',
            idField: 'id',
            treeField: 'name',
            columns: [[{
                field: 'name',
                title: 'name'
            }, {
                field: 'size',
                title: 'size',
                width: 100
            }, {
                field: 'date',
                title: 'date',
                width: 100
            }]]
        });
        /*属性表格*/
        $('#pg').propertygrid({
            url: window.root + '/demo/pg_data',
            showGroup: true,
            scrollbarSize: 0
        });
    });
});