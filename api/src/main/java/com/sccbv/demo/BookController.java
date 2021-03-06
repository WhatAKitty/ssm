package com.sccbv.demo;

import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.wrapper.RestPageWrapper;
import com.whatakitty.ssm.wrapper.RestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 演示图书管理
 *
 * @author xuqiang
 * @date 2018/04/06
 * @description
 **/
@Controller
@RequestMapping("/demo/books")
public class BookController {

    private final BookService bookService;
    private final RestWrapper restWrapper;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
        this.restWrapper = RestWrapper
            .create("id", "name", "number")
            .addHandler("id", id -> String.valueOf(id));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Object list(Pageable pageable,
                       BookDTO bookDTO,
                       @RequestParam(defaultValue = "true") Boolean isPage) {
        return RestPageWrapper.wrap(bookService.search(pageable, bookDTO, isPage, false), restWrapper);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Book save(@Validated({BookDTO.BookCreateGroup.class}) @RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{bookId}", method = RequestMethod.PUT)
    public Book update(
        @PathVariable("bookId") Long bookId,
        @Validated({BookDTO.BookUpdateGroup.class}) @RequestBody BookDTO bookDTO
    ) {
        return bookService.update(bookId, bookDTO);
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public Book delete(@PathVariable("bookId") Long bookId) {
        return bookService.destroy(bookId, false);
    }

}
