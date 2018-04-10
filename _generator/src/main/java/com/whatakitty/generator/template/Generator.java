package com.whatakitty.generator.template;

import com.whatakitty.generator.storage.LocalStorage;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.atteo.evo.inflector.English;

/**
 * 生成代码
 *
 * @author xuqiang
 * @date 2018/03/07
 * @description
 **/
@Slf4j
public class Generator {

    private final LocalStorage localStorage;
    private final DatabaseKit databaseKit;

    private final String templatePath;
    private final String outputPath;

    /**
     * 代码生成器
     *
     * @param localStorage 本地存储实例
     */
    public Generator(LocalStorage localStorage) {
        this.localStorage = localStorage;
        this.databaseKit = new DatabaseKit(this.localStorage);

        templatePath = localStorage.get("project") + "/src/main/resources/template".replace("/", File.separator);
        outputPath = localStorage.get("target");
    }

    /**
     * 生成目标文件
     *
     * @return 是否成功
     */
    public boolean generate() {
        // init templatekit
        TemplateKit.init();
        TemplateKit.setTmplateDirPath(templatePath);

        try {
            Schema schema = this.databaseKit.schema(localStorage.get("dbName"), localStorage.get("tableName"));

            // 定义模板变量
            Map<String, Object> model = new HashMap<>();
            model.put("schema", schema);

            model.putAll(localStorage.getAll());
            model.put("uncapitalizedClassName", StringUtils.uncapitalize(localStorage.get("className")));
            model.put("pkName", schema.getPri().getCOLUMN_NAME());
            model.put("date", DateFormatUtils.format(new Date(), "yyyy/MM/dd"));
            model.put("classNames", English.plural(StringUtils.uncapitalize(localStorage.get("className"))));

            File templateDir = new File(templatePath);
            File[] javaFiles = templateDir.listFiles(File::isFile);
            for (File tempFile : javaFiles) {
                String baseName = FilenameUtils.getBaseName(tempFile.getName());
                generateTemps(baseName, model, ".java");
            }
            File pagesDir = new File(templatePath + File.separator + "pages");
            if (pagesDir.exists() && pagesDir.isDirectory()) {
                File[] pageFiles = pagesDir.listFiles(File::isFile);
                for (File pageFile : pageFiles) {
                    String baseName = FilenameUtils.getBaseName(pageFile.getName());
                    generateTemps("pages" + File.separator + baseName, model, ".ftl");
                }
            }


        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    private void generateTemps(String fileName, Map<String, Object> model, String targetExtension) {
        final String className = (String) model.get("className");
        final String name = fileName.replace("Model", className);

        final String filePath = outputPath
            + File.separator
            + model.get("moduleName")
            + File.separator
            + name
            + targetExtension;

        TemplateKit.process(fileName + ".ftl", filePath, model);

        if (log.isInfoEnabled()) {
            log.info(model.get("moduleName") + ": {}", filePath);
        }
    }

}
