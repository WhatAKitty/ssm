package com.whatakitty.generator.storage;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * 基础本地存储
 *
 * @author xuqiang
 * @date 2018/03/07
 * @description
 **/
@Slf4j
public abstract class BaseLocalStorage implements LocalStorage {

    @Getter(AccessLevel.PROTECTED)
    private final File localFile;
    @Getter(AccessLevel.PROTECTED)
    private final Map<String, Object> holder = new ConcurrentHashMap<>();

    /**
     * 创建本地存储
     *
     * @param path 本地存储路径
     */
    protected BaseLocalStorage(String path) {
        localFile = new File(path);
        if (localFile.exists()) {
            loadWrapper();
            return;
        }

        if (log.isInfoEnabled()) {
            log.info(String.format("无法找到目标文件 %s 的位置，将会创建一个新的配置文件", path));
        }

        try {
            localFile.getParentFile().mkdirs();
            Assert.isTrue(
                localFile.createNewFile(),
                String.format("创建 %s 文件失败，请重试", path)
            );
        } catch (IOException e) {
            throw new RuntimeException(String.format("创建 %s 文件失败，请重试", path), e);
        }

        if (log.isInfoEnabled()) {
            log.info(String.format("创建配置文件至 %s 位置成功", localFile.getAbsolutePath()));
            log.info("如果你不希望在上述路径创建文件，则需要在application.yml文件内修改localStorage参数为你想要的存放地址，并再次启动即可");
        }

        // 初始化文件内容
        writeToFile();

        loadWrapper();
    }

    /**
     * 获取内容大小
     *
     * @return 大小
     */
    @Override
    public int getSize() {
        return holder.size();
    }

    /**
     * 获取所有
     *
     * @return 所有内容
     */
    @Override
    public Map<String, Object> getAll() {
        return holder;
    }

    /**
     * 是否存在某个键值
     *
     * @param key 键
     * @return 是否存在
     */
    @Override
    public boolean exists(String key) {
        return holder.containsKey(key);
    }

    /**
     * 加载数据
     */
    protected abstract void load(Map<String, Object> holder) throws Throwable;

    /**
     * 写入数据
     */
    protected boolean writeToFile() {
        synchronized (localFile) {
            return writeToFile(localFile);
        }
    }

    ;

    /**
     * 写入数据
     */
    protected abstract boolean writeToFile(File localFile);

    private void loadWrapper() {
        valid();
        try {
            load(holder);
        } catch (Throwable t) {
            throw new RuntimeException(String.format("加载 %s 文件失败，请重试", localFile.getAbsolutePath()), t);
        }
    }

    private void valid() {
        Assert.isTrue(
            localFile.exists() && localFile.canRead(),
            String.format(
                "本地存储 %s 不是有效的文件，请检查文件是否存在或者是否有可读权限",
                localFile.getAbsolutePath()
            )
        );
    }

}
