package com.sccbv.demo;

import com.google.common.collect.ImmutableMap;
import com.whatakitty.ssm.asserts.Asserts;
import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.service.BusinessService;
import com.whatakitty.ssm.utils.OrderByUtils;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 图书业务层
 *
 * @date 2018/04/09
 * @description
 **/
@Service
public class BookService extends BusinessService<Book, BookDTO> {

    /**
     * 初始化基础业务组件
     *
     * @param mapper 数据库操作实例
     */
    @Autowired
    public BookService(BookMapper mapper) {
        super(mapper, Book.class);
    }

    /**
     * 检查图书是否有效
     *
     * @param bookDTO   图书信息
     * @param excludeId 排除的图书
     */
    public void valid(BookDTO bookDTO, Long excludeId) {
        Example example = Example.builder(Book.class).build();
        example.and().andEqualTo("name", bookDTO.getName());

        if (excludeId != null) {
            example.and().andNotEqualTo("id", excludeId);
        }
        Asserts.isFalse(existsByExample(example, false), 400, "已经存在相同的图书");
    }

    /**
     * 查询图书
     *
     * @param pageable 分页信息
     * @param bookDTO  条件载体
     * @param isPage   是否允许分页
     * @param force    是否包含被软删除的记录
     * @return 查询结果
     */
    public Object search(Pageable pageable, BookDTO bookDTO, boolean isPage, boolean force) {
        Example example = new Example(Book.class);
        if (StringUtils.isNotBlank(bookDTO.getName())) {
            example.and().andLike("name", bookDTO.getName() + "%");
        }
        String orderBy = OrderByUtils.getOrderBy(pageable.getSort(), ImmutableMap.of(
            "id", "id",
            "name", "convert(name using gbk)",
            "number", "number",
            "modifyDate", "modify_date"
        ));
        if (StringUtils.isNotBlank(orderBy)) {
            example.setOrderByClause(orderBy);
        }
        return super.pageByExample(pageable, example, isPage, force);
    }

    /**
     * 创建图书
     *
     * @param bookDTO 图书信息
     * @return 创建的图书
     */
    public Book create(BookDTO bookDTO) {
        valid(bookDTO, null);
        return super.create(bookDTO, new Date());
    }

    /**
     * 更新图书
     *
     * @param bookId  图书编号
     * @param bookDTO 图书信息
     * @return 更新后的图书
     */
    public Book update(Long bookId, BookDTO bookDTO) {
        valid(bookDTO, bookId);
        return super.update(bookId, bookDTO, false);
    }

}
