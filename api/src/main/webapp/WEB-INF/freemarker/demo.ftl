[@layout.extends name="./layout/content.ftl"]
[@layout.put block="id"]demo[/@layout.put]
[@layout.put block="title"]首页[/@layout.put]
[@layout.put block="css"]
<style type="text/css">
    #index {
        padding: 15px;
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
        opacity: 0;
        font-size: 12px;
    }
    #index .demoIndex {
        margin-right: 15px;
    }
    #index .demoIndex:last-child {
        margin-right: 0;
    }
    #index fieldset {
        padding: 15px;
        border: 1px solid #ccc;
    }
    #index legend {
        padding: 0 10px;
    }
    #index fieldset h3 {
        margin: 10px 0;
    }
    .index-l {
        padding: 10px;
        width: 100%;
        overflow: auto;
    }
    .form-item {
        margin-bottom: 10px;
        width: 50%;
        float: left;
    }
    .form-item > label {
        min-width: 72px;
        display: inline-block;
    }
    .form-item input,
    select {
        width: 170px;
    }
    .contentWrapper {
        padding: 10px;
    }
</style>
[/@layout.put]
[@layout.put block="content" type="replace"]
<!--表格-->
<div class="index-l">
    <table id="dg"></table>
</div>
<!--表单-->
<div class="index-l">
    <div id="p" class="easyui-panel" title="panel"
         data-options="iconCls:'fa fa-tags',closable:true,collapsible:true,minimizable:true,maximizable:true">
        <div class="contentWrapper">
            <form id="ff" method="post">
                <div class="form-item">
                    <label for="" class="label-top">用户名：</label>
                    <input id="username" class="easyui-validatebox easyui-textbox" prompt="请输入用户名"
                           data-options="required:true,validType:'length[3,10]'">
                </div>
                <div class="form-item">
                    <label for="" class="label-top">文本输入框：</label>
                    <input class="easyui-textbox" data-options="iconCls:'fa fa-user',iconAlign:'left'"
                           prompt="请输入文本">
                </div>
                <div class="form-item">
                    <label for="" class="label-top">密码输入框：</label>
                    <input class="easyui-passwordbox" prompt="Password" iconWidth="28">
                </div>
                <div class="form-item">
                    <label for="" class="label-top">下拉框：</label>
                    <select id="ec" class="easyui-combobox" data-options="editable:false,panelHeight:null"
                            name="dept">
                        <option value="aa">选项1</option>
                        <option>选项2</option>
                        <option>伤害</option>
                        <option>电风扇</option>
                        <option>共担风险</option>
                    </select>
                </div>
                <div class="form-item">
                    <label for="" class="label-top">数值输入框：</label>
                    <input type="text" class="easyui-numberbox" value="100" data-options="min:0,precision:2"/>
                </div>

                <div class="form-item">
                    <label for="" class="label-top">日历：</label>
                    <input id="dd" type="text" class="easyui-datebox" required="required"/>
                </div>
                <div class="form-item">
                    <label for="" class="label-top">数字微调：</label>
                    <input id="ss" class="easyui-numberspinner" required="required"
                           data-options="min:10,max:100,editable:false">
                </div>
                <div class="form-item">
                    <label for="" class="label-top">文件选择：</label>
                    <input class="easyui-filebox" data-options="buttonText:'上传头像',buttonIcon:'fa fa-upload'">
                </div>
                <div class="form-item">
                    <input class="easyui-slider" value="12" style="width:300px" data-options="showTip:true"/>
                </div>
            </form>
        </div>
    </div>
</div>
<!--按钮-->
<div class="index-l">
    <a href="#" title="This is the tooltip message." class="easyui-tooltip"
       data-options="position:'right'">Hoverme</a>
    <a href="javascript:void(0)" id="mb" class="easyui-menubutton"
       data-options="menu:'#mm',noline:true,iconCls:'fa fa-tags'">Edit</a>
    <div id="mm">
        <div>Undo</div>
        <div>
            <span>Open</span>
            <div style="width:150px;">
                <div><b>Word</b></div>
                <div>
                    <span>Open</span>
                    <div style="width:150px;">
                        <div><b>Word</b></div>
                        <div>Excel</div>
                        <div>PowerPoint</div>
                    </div>
                </div>
                <div>PowerPoint</div>
            </div>
        </div>
        <div>Cut</div>
        <div>Copy</div>
        <div>Paste</div>
        <div class="menu-sep"></div>
        <div>Delete</div>
        <div>Select All</div>
    </div>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'fa fa-tags'">我是按钮</a>
    <a href="javascript:void(0)" class="easyui-linkbutton default">我也是按钮</a>
    <a href="javascript:void(0)" class="easyui-linkbutton primary" data-options="iconCls:'fa fa-area-chart'"></a>
    <a href="javascript:void(0)" class="easyui-linkbutton error" data-options="iconCls:'fa fa-gears'"></a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="disabled:true">禁用按钮</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true">简洁按钮</a>

    <h3>开关</h3>
    <input class="easyui-switchbutton" checked>
    <input class="easyui-switchbutton" data-options="onText:'开',offText:'关'">
    <input class="easyui-switchbutton" data-options="disabled:true">
    <a id="myWin-btn" href="javascript:void(0)" class="easyui-linkbutton primary">对话窗口</a>
    <div id="myWin" style="display: none;">对话框窗口内容。</div>
    <a id="myWin-btn2" href="javascript:void(0)" class="easyui-linkbutton info">选择窗口</a>
    <a id="myWin-btn3" href="javascript:void(0)" class="easyui-linkbutton success">警告窗口</a>
    <a id="myWin-btn5" href="javascript:void(0)" class="easyui-linkbutton warning">提示窗口</a>
    <a id="myWin-btn4" href="javascript:void(0)" class="easyui-linkbutton error">显示消息</a>
</div>
<!--tab-->
<div class="index-l">
    <div id="tt" class="easyui-tabs" data-options="tabHeight:31" style="width:100%;height:100px;">
        <div title="Tab1" style="padding:20px;display:none;">
            tab1
        </div>
        <div title="Tab2" style="overflow:auto;padding:20px;display:none;">
            tab2
        </div>
        <div title="Tab3" data-options="iconCls:'fa fa-user',closable:true" style="padding:20px;display:none;">
            tab3
        </div>
    </div>
</div>
<!--accordion和树形-->
<div class="index-l">
    <div id="aa" class="easyui-accordion" style="width:300px;height:200px;">
        <div title="Title1" data-options="iconCls:'fa fa-save',selected:true">
            <ul id="tree"></ul>
        </div>
        <div title="Title2" data-options="iconCls:'fa fa-tasks'">
            看我
        </div>
        <div title="Title3" data-options="iconCls:'fa fa-star-half-full'">
            你在害怕什么
        </div>
    </div>
</div>

<!--布局-->
<div class="index-l">
    <div id="cc" class="easyui-layout" style="width:100%;height:400px;">
        <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
        <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>
        <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true"
             style="width:100px;"></div>
        <div data-options="region:'west',title:'West',split:true" style="width:100px;"></div>
        <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;"></div>
    </div>
</div>


<!--树形表格-->
<div class="index-l">
    <table id="ttg" style="width:600px;height:400px"></table>
</div>
<div class="index-l">
    <table id="pg" style="width:300px"></table>
</div>
[/@layout.put]
[@layout.put block="script"]
<script type="application/javascript" src="${root}/resources/js/demo.js" ></script>
[/@layout.put]
[/@layout.extends]
