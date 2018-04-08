package com.sccbv.system.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * 菜单视图工具类。
 * 用于将菜单数据同步转为页面元素
 *
 * @author xuqiang
 * @date 2018/04/08
 * @description
 **/
public class MenuViewUtils {

    private static final String HAS_CHILD_MENU_TEMPLATE =
        "<div title=\"%s\" data-options=\"%s\" style=\"padding-left: 10px;\">" +
            "<div id=\"%s\" class=\"easyui-accordion\" data-options=\"border:false,fit:true\">" +
            "%s</div></div>";

    private static final String DEPTH_ONE_TEMPLATE =
        "<div title=\"%s\" data-options=\"%s\" style=\"\">" +
            "<ul id=\"%s\">%s</ul></div>";

    private static final String ITEM_TEMPLATE = "<li data-url='%s'>%s</li>";


    public static String transform(JSONObject item) {
        boolean hasChildren = item.getJSONArray("children") != null;
        Integer depth = item.getInteger("depth");
        boolean hasChildMenu = depth != null && depth > 2;

        final Long id = item.getLong("id");
        final String title = item.getString("title");
        final String icon = icon(item.getString("icon"));
        if (hasChildMenu) {
            final JSONArray children = item.getJSONArray("children");
            return String.format(HAS_CHILD_MENU_TEMPLATE,
                title,
                icon,
                id,
                transformArray(children)
            );
        } else if (hasChildren) {
            final JSONArray children = item.getJSONArray("children");
            return String.format(
                DEPTH_ONE_TEMPLATE,
                title,
                icon,
                id,
                transformArray(children)
            );
        }

        return String.format(
            ITEM_TEMPLATE,
            item.getString("url"),
            title
        );
    }

    public static String transformArray(JSONArray items) {
        return StringUtils.join(items.toArray(new String[items.size()]), "");
    }

    private static String icon(String icon) {
        if (StringUtils.isBlank(icon)) {
            return "";
        }

        return String.format("iconCls:'fa fa-%s'", icon);
    }

    private MenuViewUtils() {
    }

}
