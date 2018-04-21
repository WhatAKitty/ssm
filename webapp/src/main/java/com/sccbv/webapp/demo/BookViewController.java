package com.sccbv.webapp.demo;

import com.sccbv.demo.Book;
import com.sccbv.demo.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 演示图书管理
 *
 * @author xuqiang
 * @date 2018/04/06
 * @description
 **/
@Controller
@RequestMapping("/demo/books-view")
public class BookViewController {

    private final BookService bookService;

    @Autowired
    public BookViewController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexView() {
        return "pages/demo/book/list";
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addView() {
        return "pages/demo/book/add";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editView(
        @PathVariable("id") Long id,
        ModelAndView modelAndView
    ) {
        Book book = bookService.byPrimaryKey(id, false);

        modelAndView.setViewName("/pages/demo/book/edit");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

}
