package com.sccbv.system.office;

import com.google.common.collect.ImmutableMap;
import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.service.BusinessService;
import com.whatakitty.ssm.utils.OrderByUtils;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * 部门业务层
 *
 * @date 2018/04/29
 * @description
 **/
@Service
public class OfficeService extends BusinessService<Office, OfficeDTO> {

    /**
     * 初始化基础业务组件
     *
     * @param mapper 数据库操作实例
     */
    @Autowired
    public OfficeService(OfficeMapper mapper) {
        super(mapper, Office.class);
    }

    /**
     * 检查部门是否有效
     *
     * @param officeDTO 部门信息
     * @param excludeId 排除的部门
     */
    public void valid(OfficeDTO officeDTO, Long excludeId) {
        Example example = Example.builder(Office.class).build();
        example.and().andEqualTo("name", officeDTO.getName());

        if (excludeId != null) {
            example.and().andNotEqualTo("id", excludeId);
        }
        Asserts.isFalse(existsByExample(example, false), 400, "已经存在相同的部门");
    }

    /**
     * 查询部门
     *
     * @param pageable  分页信息
     * @param officeDTO 条件载体
     * @param isPage    是否允许分页
     * @param force     是否包含被软删除的记录
     * @return 查询结果
     */
    public Object search(Pageable pageable, OfficeDTO officeDTO, boolean isPage, boolean force) {
        Example example = new Example(Office.class);
        if (StringUtils.isNotBlank(officeDTO.getName())) {
            example.and().andLike("name", officeDTO.getName() + "%");
        }
        String orderBy = OrderByUtils.getOrderBy(pageable.getSort(), ImmutableMap.of(
            "id", "id",
            "name", "convert(name using gbk)",
            "parentId", "parentId"
        ));
        if (StringUtils.isNotBlank(orderBy)) {
            example.setOrderByClause(orderBy);
        }
        return super.pageByExample(pageable, example, isPage, force);
    }

    /**
     * 创建部门
     *
     * @param officeDTO 部门信息
     * @return 创建的部门
     */
    public Office create(OfficeDTO officeDTO) {
        valid(officeDTO, null);
        return super.create(officeDTO, new Date());
    }

    /**
     * 更新部门
     *
     * @param officeId  部门编号
     * @param officeDTO 部门信息
     * @return 更新后的部门
     */
    public Office update(Long officeId, OfficeDTO officeDTO) {
        valid(officeDTO, officeId);
        return super.update(officeId, officeDTO, false);
    }

    /**
     * 删除部门
     *
     * @param officeId 部门编号
     * @return 删除的部门
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Office delete(Long officeId) {
        // 判断是否存在下属部门
        Example example = Example.builder(Office.class).build();
        example.and().andEqualTo("parentId", officeId);
        Asserts.isFalse(super.existsByExample(example, false), 400, "存在下属部门");

        // 删除
        return super.destroy(officeId, false);
    }

}
