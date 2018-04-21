$(function () {
    var $document = $(document);

    /**
     * ajax全局设置
     */
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        if (options.type.toLowerCase() === "post" ||
            options.type.toLowerCase() === "put" ||
            options.type.toLowerCase() === "delete") {
            var csrf_token = $('meta[csrf-token]').attr('csrf-token');
            // var csrf_token_name = $("meta[csrf-token-name]").attr('csrf-token-name');
            options.headers = options.headers || {};
            options.headers['X-CSRF-TOKEN'] = csrf_token;
        }
    });

    // 序列化
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        var $radio = $('input[type=radio],input[type=checkbox]', this);
        $.each($radio, function () {
            if (!o.hasOwnProperty(this.name)) {
                // o[this.name] = this.value || '';
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            } else if (!o[this.name].push) {
                console.log('存在原有值', o[this.name]);
                // 存在原有值，并且非数组
                o[this.name] = [o[this.name]];
            }
        });
        return o;
    };

    // 定义通用跳转
    $.redirect = function (url, callback) {
        var $contentWrapper = $("#tt");

        if (!callback || 'function' !== typeof callback) {
            callback = function () {
            };
        }

        // push state to history
        locationBar.update(url);
        // load page
        $contentWrapper.load(url, function (response, status, xhr) {
            if (status == 'error') {
                $contentWrapper.html(response);
            }
            callback(response, status, xhr);
        });
    };


    //设置返回按钮
    $document.on('click', '.btn-back', function () {
        history.back()
    });
    $('.easyui-switchbutton').switchbutton({
        'onChange': function (checked) {
            $(this).switchbutton('setValue', checked);
        }
    });

    $.showRemoveDialog = function (url, success, fail) {
        $.messager.confirm({
            title: '确认',
            msg: '是否确认删除？',
            fn: function (r) {
                if (!r) {
                    return;
                }

                $.ajax({
                    url: url,
                    type: 'DELETE',
                }).done(function (data) {
                    $.message('信息', '删除成功！');
                    if (success && 'function' === typeof success) success(data);
                }).fail(function (err) {
                    $.message('警告', err.responseText);
                    if (fail && 'function' === typeof fail) fail(err);
                });
            }
        })
    };

    $.message = function (title, msg) {
        $.messager.show({
            title: title,
            msg: msg,
            timeout: 3000,
            showType: 'slide'
        });
    }

    // AJAX表单初始化提交
    $.fn.ajaxForm = function (type, success, fail, handleData) {
        var $form = $(this),
            $btn = $form.find('.btn-submit');

        $btn.linkbutton('enable');

        $form.on('click', '.btn-submit', function (e) {
            e.preventDefault();
            $btn.linkbutton('disable');

            var action = $form.attr("action");
            var data = $form.serializeJSON();

            for (var item in data) {
                if (data[item] === "" || "undefined" === typeof data[item]) delete data[item];
            }

            if (handleData && 'function' === typeof handleData) handleData(data);
            $.ajax({
                type: type,
                url: action,
                data: JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json'
            }).done(function (data) {
                if (type === 'PUT' || type === 'put') {
                    $.message('提示', '更新成功');
                } else if (type === 'POST' || type === 'post') {
                    $.message('提示', '添加成功');
                }

                if (success && 'function' === typeof success) success(data);
            }).fail(function (error) {
                var result;
                var message;
                try {
                    result = JSON.parse(error.responseText);
                    message = result.message;
                } catch (e) {
                    message = error.responseText
                }
                $.message('警告', message);
                if (fail && 'function' === typeof fail) fail(error);
            }).always(function () {
                $btn.linkbutton('enable');
            });
        });
    };

});