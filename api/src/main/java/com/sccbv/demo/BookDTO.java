package com.sccbv.demo;

import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 图书数据传输载体
 *
 * @date 2018/04/09
 * @description
 **/
@Data
public class BookDTO {

    private Long id;

    @NotEmpty(
        groups = {BookCreateGroup.class, BookUpdateGroup.class},
        message = "{error.message.book.name.empty}"
    )
    private String name;

    @NotNull(
        groups = {BookCreateGroup.class, BookUpdateGroup.class},
        message = "{error.message.book.number.null}"
    )
    private Integer number;


    /**
     * 图书创建验证组
     */
    public interface BookCreateGroup {}

    /**
     * 图书更新验证组
     */
    public interface BookUpdateGroup {}

}
