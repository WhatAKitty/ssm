package com.whatakitty.generator.storage;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * JSON本地化存储
 *
 * @author xuqiang
 * @date 2018/03/07
 * @description
 **/
@Component
@PropertySource("classpath:localstorage.properties")
public class JSONLocalStorage extends BaseLocalStorage {

    private final Charset charset = Charset.forName("UTF-8");

    /**
     * 创建本地存储
     *
     * @param path 本地存储路径
     */
    @Autowired
    public JSONLocalStorage(@Value("${localStorage}") String path) {
        super(path);
    }

    @Override
    protected void load(Map<String, Object> holder) throws Throwable {
        String data = FileUtils.readFileToString(getLocalFile(), charset);
        Map<String, Object> dataMap = JSON.parseObject(data, Map.class);
        holder.putAll(dataMap);
    }

    @Override
    protected boolean writeToFile(File localFile) {
        try {
            JSON.writeJSONString(new FileWriter(getLocalFile()), getHolder());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean put(String key, Object value) {
        getHolder().put(key, value);
        return writeToFile();
    }

    @Override
    public <T> T get(String key) {
        if (!getHolder().containsKey(key)) {
            return null;
        }

        return (T) getHolder().get(key);
    }

    @Override
    public boolean remove(String key) {
        if (!getHolder().containsKey(key)) {
            return true;
        }

        getHolder().remove(key);
        return writeToFile();
    }

    @Override
    public boolean clear() {
        getHolder().clear();
        return writeToFile();
    }
}
