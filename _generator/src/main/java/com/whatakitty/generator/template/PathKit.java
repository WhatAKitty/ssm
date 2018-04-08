package com.whatakitty.generator.template;

import java.io.File;

/**
 * 路径工具
 *
 * @author xuqiang
 * @date 2018/03/08
 * @description
 **/
public class PathKit {

    /**
     * 获取当前项目路径
     *
     * @return 项目路径
     */
    public static String getProjectPath() {
        String classPath = Class.class.getClass().getResource("/").getPath();
        try {
            return new File(classPath).getParentFile().getParentFile().getCanonicalPath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PathKit() {
    }

}
