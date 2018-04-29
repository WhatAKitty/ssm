/*easyui样式初始化*/
$.fn.tabs.defaults.tabHeight = 32; //tab标签条高度
$.fn.linkbutton.defaults.height = 32; //按钮高度
$.fn.menu.defaults.itemHeight = 28; //menu高度

$.fn.validatebox.defaults.height = 32;
$.fn.textbox.defaults.height = 32;
$.fn.textbox.defaults.iconWidth = 24;
$.fn.datebox.defaults.height = 32;
$.fn.numberspinner.defaults.height = 32;
$.fn.timespinner.defaults.height = 32;
$.fn.numberbox.defaults.height = 32;
$.fn.combobox.defaults.height = 32;
$.fn.passwordbox.defaults.height = 32;
$.fn.filebox.defaults.height = 32;

$.fn.menu.defaults.noline = true;
$.fn.progressbar.defaults.height = 18; //进度条

/**
 * easyui default reset
 *
 * 重置datagrid loadFilter行为，不兼容返回类型为list的数据，需要返回page对象
 */
$.fn.datagrid.defaults.loadFilter = function (data) {
    if (data.rows && data.total) {
        // raw data
        return data;
    } else if (data.list && data.totalRow) {
        // spring mvc page response
        return {
            total: data.totalRow,
            rows: data.list
        }
    } else {
        console.error('不支持该格式的返回数据', data);
        return {
            total: 0,
            rows: []
        };
    }
};
/**
 * 重置请求发送
 *
 * @param param
 * @param success
 * @param error
 * @returns {boolean}
 */
$.fn.datagrid.defaults.loader = function (param, success, error) {
    var opts = $(this).datagrid('options');
    if (!opts.url) {
        return false;
    }

    $.ajax({
        type: opts.method,
        url: opts.url,
        data: $.extend({}, param, {
            size: param.rows
        }),
        dataType: 'json',
        success: function (data) {
            success(data);
        },
        error: function () {
            error.apply(this, arguments);
        }
    })
};

/**
 * 支持TreeGrid/ComboTree/ComboTreeGrid树结构
 *
 * @param data
 * @param parentId
 * @returns {*}
 */
function supportSimpleTree(opts, data, parentId) {

    if (!opts.parentField) {
        return data;
    }

    var idField = opts.idField || 'id';
    var treeField = opts.treeField || 'text';
    var parentField = opts.parentField;

    var i,
        l,
        treeData = [],
        tmpMap = [];

    for (i = 0, l = data.length; i < l; i++) {
        tmpMap[data[i][idField]] = data[i];
    }

    for (i = 0, l = data.length; i < l; i++) {
        if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
            if (!tmpMap[data[i][parentField]]['children'])
                tmpMap[data[i][parentField]]['children'] = [];
            data[i]['text'] = data[i][treeField];
            tmpMap[data[i][parentField]]['children'].push(data[i]);
        } else {
            data[i]['text'] = data[i][treeField];
            treeData.push(data[i]);
        }
    }
    return treeData;
};
$.fn.treegrid.defaults.loadFilter = function (data, parentId) {
    var opts = $(this).treegrid('options');
    return supportSimpleTree(opts, data, parentId);
};
$.fn.combotree.defaults.loadFilter = function (data, parentId) {
    var opts = $(this).tree('options');
    return supportSimpleTree(opts, data, parentId);
};
$.fn.combotreegrid.defaults.loadFilter = function (data, parentId) {
    var opts = $(this).combotreegrid('options');
    return supportSimpleTree(opts, data, parentId);
};

$.parser.onComplete = function () {
    $("#index").css('opacity', 1);
};

$(function () {
    /*左侧导航分类选中样式*/
    $(".panel-body .accordion-body>ul>li").on('click', function () {
        $(this).siblings().removeClass('super-accordion-selected');
        $(this).addClass('super-accordion-selected');

        //新增一个选项卡
        var tabUrl = $(this).data('url');
        var tabTitle = $(this).text();
        //tab是否存在
        if ($("#tt").tabs('exists', tabTitle)) {
            $("#tt").tabs('select', tabTitle);
        } else {
            if (tabUrl) {
                content = '<iframe frameborder="0"  src="' + tabUrl + '" style="width:100%;height:99%;"></iframe>';
            } else {
                content = '未实现';
            }
            $('#tt').tabs('add', {
                title: tabTitle,
                closable: true,
                content: content
            });
        }
    });

    /*设置按钮的下拉菜单*/
    $('.super-setting-icon').on('click', function () {
        $('#mm').menu('show', {
            top: 50,
            left: document.body.scrollWidth - 130
        });
    });

    /*修改主题*/
    $('#themeSetting').on('click', function () {
        var themeWin = $('#win').dialog({
            width: 460,
            height: 260,
            modal: true,
            title: '主题设置',
            buttons: [{
                text: '保存',
                id: 'btn-sure',
                handler: function () {
                    themeWin.panel('close');
                }
            }, {
                text: '关闭',
                handler: function () {
                    themeWin.panel('close');
                }
            }],
            onOpen: function () {
                $(".themeItem").show();
            }
        });
    });
    $(".themeItem ul li").on('click', function () {
        $(".themeItem ul li").removeClass('themeActive');
        $(this).addClass('themeActive');
    });
});

