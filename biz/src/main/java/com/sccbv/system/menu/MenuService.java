package com.sccbv.system.menu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.service.BusinessService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 菜单业务层
 *
 * @date 2018/04/08
 * @description
 **/
@Service
public class MenuService extends BusinessService<Menu, MenuDTO> {

    /**
     * 初始化基础业务组件
     *
     * @param mapper 数据库操作实例
     */
    @Autowired
    public MenuService(MenuMapper mapper) {
        super(mapper, Menu.class);
    }

    /**
     * 树形菜单
     *
     * @param callback 回调函数
     * @return JSON格式数组
     */
    public JSONObject treeMenuList(TreeMenuFilterCallbck callback) {
        List<Menu> menus = all(false);
        return treeMenuList(menus, 0L, callback);
    }

    /**
     * 检查菜单是否有效
     *
     * @param menuDTO   菜单信息
     * @param excludeId 排除的菜单
     */
    public void valid(MenuDTO menuDTO, Long excludeId) {
        Example example = Example.builder(Menu.class).build();
        // TODO 待完成的验证条件

        if (excludeId != null) {
            example.and().andNotEqualTo("id", excludeId);
        }
        Asserts.isFalse(existsByExample(example, false), 400, "已经存在相同的菜单");
    }

    /**
     * 创建菜单
     *
     * @param menuDTO 菜单信息
     * @return 创建的菜单
     */
    public Menu create(MenuDTO menuDTO) {
        valid(menuDTO, null);
        return super.create(menuDTO, new Date());
    }

    /**
     * 更新菜单
     *
     * @param menuId  菜单编号
     * @param menuDTO 菜单信息
     * @return 更新后的菜单
     */
    public Menu update(Long menuId, MenuDTO menuDTO) {
        valid(menuDTO, menuId);
        return super.update(menuId, menuDTO, false);
    }

    /**
     * 递归查询菜单
     *
     * @param menuList 菜单列表
     * @param parentId 顶层菜单编号
     * @param callbck  回调函数，用于菜单节点筛选
     * @return 菜单JSON格式列表
     */
    private JSONObject treeMenuList(List<Menu> menuList, long parentId, TreeMenuFilterCallbck callbck) {
        JSONObject obj = new JSONObject();
        JSONArray childMenu = new JSONArray();
        int maxDepth = 0;
        for (Menu menu : menuList) {
            if (parentId == menu.getParent()) {
                if (callbck.filter(menu)) {
                    continue;
                }
                final JSONObject childObj = treeMenuList(menuList, menu.getId(), callbck);
                final int depth = childObj.getInteger("depth");
                maxDepth = maxDepth > depth ? maxDepth : depth;

                childObj.put("id", menu.getId());
                childObj.put("parentId", menu.getParent());
                childObj.put("title", menu.getTitle());
                childObj.put("icon", menu.getIcon());
                childObj.put("url", menu.getUrl());

                childMenu.add(callbck.item(childObj));
            }
        }
        if (childMenu.size() > 0) {
            obj.put("children", childMenu);
        }
        obj.put("depth", maxDepth + 1);
        return obj;
    }


    public interface TreeMenuFilterCallbck {

        /**
         * 过滤节点数据
         *
         * @param menu 过滤的目标菜单节点
         * @return 是否过滤该菜单；返回True，则过滤；返回False，则正常
         */
        boolean filter(Menu menu);

        /**
         * 定义需要的返回数据
         *
         * @param menu 处理前的数据
         * @return 处理后的数据
         */
        Object item(JSONObject menu);

    }

}
