package com.whatakitty.generator.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.whatakitty.generator.storage.LocalStorage;
import com.whatakitty.generator.template.Generator;
import com.whatakitty.generator.template.PathKit;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/**
 * 命令执行
 *
 * @author xuqiang
 * @date 2018/03/07
 * @description
 **/
public class Commandor {

    private final LocalStorage localStorage;

    public Commandor(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    /**
     * 执行命令
     *
     * @throws Exception 异常
     */
    public void execute() throws Exception {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 第一步 显示所有的配置
        boolean modify = false;
        if (localStorage.getSize() == 0) {
            System.out.println("之前不存在任何配置，请先配置程序");
            modify = true;
        } else if (!checkValid("project", "target", "author", "jdbc", "jdbc_username", "jdbc_password", "dbName")) {
            System.out.println("参数不完整，需要重新配置");
            modify = true;
        } else {
            System.out.println("现有的配置如下：");

            printGlobalConfig();

            final String answer = readItemFromOptions(br, "是否修正配置", null, "Y", Arrays.asList(
                "Y", "n"
            ));

            if (modify = "Y".equalsIgnoreCase(answer)) {
                System.out.println("重新修改配置");
            } else {
                System.out.println("使用之前的配置");
            }
        }

        if (modify) {
            // 第二步 输入配置参数
            readItem(br, "项目地址", "project", PathKit.getProjectPath(), ((key, value) -> {
                File dir = new File(value);
                return dir.exists() && dir.isDirectory() && dir.canRead();
            }));
            readItem(br, "输出路径", "target", localStorage.get("target"), (key, value) -> {
                File file = new File(value);
                return file.exists() && file.isDirectory() && file.canRead();
            });
            readItem(br, "作者", "author",
                localStorage.get("author") == null ? System.getProperty("user.name") : localStorage.get("author"),
                null);
            readItem(br, "数据库链接", "jdbc", localStorage.get("jdbc"), null);
            readItem(br, "数据访问用户名", "jdbc_username",
                localStorage.get("jdbc_username") == null ? System.getProperty("user.name") : localStorage.get("jdbc_username"),
                null);
            readItem(br, "数据库访问密码", "jdbc_password",
                localStorage.get("jdbc_password") == null ? "" : localStorage.get("jdbc_password"),
                null);
            readItem(br, "数据库名", "dbName", localStorage.get("dbName"), null);

            System.out.println("完成配置\r\n\r\n");
        }

        // 第三步 完成全局配置，配置单表文件生成
        System.out.println("配置生成目标代码相关信息\r\n");

        readItem(br, "数据表名", "tableName", localStorage.get("tableName"), null);
        readItem(br, "功能中文名", "funcName", localStorage.get("funcName"), null);
        readItem(br, "包名", "packageName",
            localStorage.get("packageName") == null ? "com.gnet" : localStorage.get("packageName"),
            null);
        readItem(br, "模块名称", "moduleName", localStorage.get("moduleName"), null);
        readItem(br, "目标类名", "className", localStorage.get("className"), null);

        final String target = localStorage.get("target");
        System.out.println(String.format("完成目标代码相关配置，开始生成目标文件到输出路径：%s\r\n", target));

        // 第四步 开始生成目标代码
        if (new Generator(localStorage).generate()) {
            final String moduleName = localStorage.get("moduleName");
            System.out.println(String.format("生成模板文件成功，请在 %s 文件夹下查看", target + File.separator + moduleName));

            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(target + File.separator + moduleName));
        } else {
            System.out.println("生成模板文件失败，请重试");
        }
    }

    private boolean checkValid(String... keys) {
        for (String key : keys) {
            if (!localStorage.exists(key)) {
                return false;
            }
        }

        return true;
    }

    private void printGlobalConfig() {
        Map<String, Object> all = localStorage.getAll();

        Map<String, Object> filtered = all
            .entrySet()
            .parallelStream()
            .filter(stringObjectEntry -> Arrays.asList(
                "target", "author", "jdbc", "jdbc_username", "jdbc_password", "project"
            ).contains(stringObjectEntry.getKey()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(JSON.toJSONString(filtered, SerializerFeature.PrettyFormat));
    }

    private String readItemFromOptions(BufferedReader bufferedReader, String message, String key, String defaultValue, List<String> options) throws IOException {
        String msg = String.format("%s （%s）", message, StringUtils.join(options, "/"));
        return readItem(bufferedReader, msg, key, defaultValue,
            ((label, value) -> options
                .parallelStream()
                .map(option -> StringUtils.trim(option).toLowerCase())
                .collect(Collectors.toList())
                .contains(value)),
            false
        );
    }

    private String readItem(BufferedReader bufferedReader, String message, String key, String defaultValue, CheckFun checkFun) throws IOException {
        return readItem(bufferedReader, message, key, defaultValue, checkFun, true);
    }

    private String readItem(BufferedReader bufferedReader, String message, String key, String defaultValue, CheckFun checkFun, boolean parasPrint) throws IOException {
        String line;
        boolean valid = true;
        printItemTip(message, defaultValue, parasPrint);
        while (
            StringUtils.isBlank(line = bufferedReader.readLine())
                || checkFun != null && !(valid = checkFun.valid(key, line.trim()))) {
            if (StringUtils.isNotBlank(defaultValue)) {
                if (StringUtils.isNotBlank(key)) {
                    localStorage.put(key, defaultValue);
                    return line.trim();
                } else {
                    return defaultValue;
                }
            }
            if (!valid) {
                System.out.println("不合法的值，请重新输入");
            }
            printItemTip(message, defaultValue, parasPrint);
        }

        if (StringUtils.isNotBlank(key)) {
            localStorage.put(key, line.trim());
        }
        return line.trim();
    }

    private void printItemTip(String message, String defaultValue, boolean parasPrint) {
        if (StringUtils.isBlank(defaultValue) || !parasPrint) {
            System.out.println(String.format("%s", message));
        } else {
            System.out.println(String.format("%s （%s）", message, defaultValue));
        }
    }

    /**
     * @author xuqiang
     * @date 2018/03/07
     * @description
     */
    @FunctionalInterface
    interface CheckFun {

        boolean valid(String key, String value);

    }

}
