package com.sccbv.webapp.system.office;

import com.sccbv.system.office.Office;
import com.sccbv.system.office.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 部门视图
 *
 * @author WhatAKitty
 * @date 2018/04/29
 * @description
 **/
@Controller
@RequestMapping("/system/offices-view")
public class OfficeViewController {

    private final OfficeService officeService;

    /**
     * 初始化权限Controller层
     *
     * @param officeService 部门服务
     */
    @Autowired
    public OfficeViewController(OfficeService officeService) {
        this.officeService = officeService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String indexView() {
        return "pages/system/office/list";
    }


    @RequestMapping(value = "/{officeId}", method = RequestMethod.GET)
    public ModelAndView itemView(
        @PathVariable("officeId") Long officeId,
        ModelAndView modelAndView
    ) {
        Office office = officeService.byPrimaryKey(officeId, false);
        Office parentOffice = office.getParentId() == 0 ?
            null : officeService.byPrimaryKey(office.getParentId(), false);

        modelAndView.setViewName("/pages/system/office/view");
        modelAndView.addObject("office", office);
        modelAndView.addObject("parentOffice", parentOffice);
        return modelAndView;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addView() {
        return "pages/system/office/replace";
    }


    @RequestMapping(value = "/edit/{officeId}", method = RequestMethod.GET)
    public ModelAndView editView(
        @PathVariable("officeId") Long officeId,
        ModelAndView modelAndView
    ) {
        Office office = officeService.byPrimaryKey(officeId, false);

        modelAndView.setViewName("/pages/system/office/replace");
        modelAndView.addObject("office", office);
        return modelAndView;
    }

}
