package com.sccbv.demo;

import com.whatakitty.ssm.service.BusinessService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class BookService extends BusinessService<Book, BookDTO> {

    /**
     * 初始化基础业务组件
     */
    @Autowired
    public BookService(BookMapper bookMapper) {
        super(bookMapper, Book.class);
    }

}
