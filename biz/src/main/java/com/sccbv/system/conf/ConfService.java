package com.sccbv.system.conf;

import com.whatakitty.ssm.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统配置业务层
 *
 * @date 2018/04/15
 * @description
 **/
@Service
public class ConfService extends BusinessService<Conf, ConfDTO> {

    /**
     * 初始化基础业务组件
     *
     * @param mapper 数据库操作实例
     */
    @Autowired
    public ConfService(ConfMapper mapper) {
        super(mapper, Conf.class);
    }

}
