package com.whatakitty.generator.template;

import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.*;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * 模板工具
 *
 * @author xuqiang
 * @date 2018/03/07
 * @description
 **/
public class TemplateKit {

    private static final transient Configuration config = new Configuration(Configuration.VERSION_2_3_27);

    /**
     * 设置属性
     *
     * @param properties 属性
     */
    public static void setProperties(Properties properties) {
        try {
            config.setSettings(properties);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置模板路径
     *
     * @param file 模板路径
     */
    public static void setTmplateDirPath(File file) {
        try {
            config.setDirectoryForTemplateLoading(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置模板文件夹路径
     *
     * @param filePath 模板文件夹路径
     */
    public static void setTmplateDirPath(String filePath) {
        try {
            config.setDirectoryForTemplateLoading(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始化FreeMarker
     */
    public static void init() {
        config.setClassicCompatible(true);
        config.setTemplateUpdateDelayMilliseconds(0);
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        //  自动 AUTO_DETECT_TAG_SYNTAX  
        //  尖括号 ANGLE_BRACKET_TAG_SYNTAX  
        //  方括号 SQUARE_BRACKET_TAG_SYNTAX
        config.setTagSyntax(Configuration.ANGLE_BRACKET_TAG_SYNTAX);//使用 <>
        config.setObjectWrapper(new BeansWrapperBuilder(Configuration.VERSION_2_3_27).build());
        config.setDefaultEncoding("UTF-8");
        config.setOutputEncoding("UTF-8");
        config.setLocale(Locale.CHINA);       // config.setLocale(Locale.US);
        config.setLocalizedLookup(false);
        config.setNumberFormat("#0.#####");
    }

    /**
     * 处理
     *
     * @param templetFilename 模板文件名
     * @param rootMap         参数
     * @return 处理结果
     */
    public static String process(String templetFilename, Map rootMap) {
        Writer out = null;
        try {
            Template temp = config.getTemplate(templetFilename);
            out = new StringWriter();
            temp.process(rootMap, out);
            String string = out.toString();
            return string;
        } catch (IOException e) {
            throw new RuntimeException("not found template file :" + templetFilename, e);
        } catch (TemplateException e) {
            throw new RuntimeException("Problem pasre template file :" + templetFilename, e);
        } finally {
            closeWriter(out);
        }
    }

    /**
     * 处理
     *
     * @param templetFilename 模板文件名
     * @param outputFilename  输出文件名
     * @param rootMap         参数
     */
    public static void process(String templetFilename, String outputFilename, Map rootMap) {
        Writer out = null;
        try {
            Template temp = config.getTemplate(templetFilename);
            File file = new File(StringUtils.substringBeforeLast(outputFilename, File.separator));
            if (!file.exists()) {
                file.mkdirs();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFilename), "UTF-8"));
            temp.process(rootMap, out);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Problem writer output  file:" + outputFilename, e);
        } catch (IOException e) {
            throw new RuntimeException("not found template file :" + templetFilename, e);
        } catch (TemplateException e) {
            throw new RuntimeException("Problem pasre template file :" + templetFilename, e);
        } finally {
            closeWriter(out);
        }
    }

    private static void closeWriter(Writer out) {
        try {
            if (out != null) {
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private TemplateKit() {
    }

}