package com.sccbv.system.office;

import com.whatakitty.ssm.dto.Pageable;
import com.whatakitty.ssm.wrapper.RestPageWrapper;
import com.whatakitty.ssm.wrapper.RestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 部门资源接口
 *
 * @date 2018/04/29
 * @description
 **/
@Controller
@RequestMapping("/system/offices")
public class OfficeController {

    private final OfficeService officeService;
    private final RestWrapper restWrapper;

    /**
     * 初始化部门Controller层
     *
     * @param officeService 部门服务
     */
    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
        this.restWrapper = RestWrapper
            .create("id", "name", "parentId", "remark")
            .addHandler("id", String::valueOf)
            .addHandler("parentId", String::valueOf);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public Object list(Pageable pageable,
                         OfficeDTO officeDTO,
                         @RequestParam(defaultValue = "true") Boolean isPage) {
        return restWrapper.wrap(officeService.search(pageable, officeDTO, isPage, false));
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{officeId}", method = RequestMethod.GET)
    public Object detail(@PathVariable("officeId") Long officeId) {
        return restWrapper.wrap(officeService.byPrimaryKey(officeId, false));
    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object create(@Validated({OfficeDTO.OfficeCreateGroup.class}) @RequestBody OfficeDTO officeDTO) {
        return restWrapper.wrap(officeService.create(officeDTO));
    }


    @ResponseBody
    @RequestMapping(value = "/{officeId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object update(@Validated({OfficeDTO.OfficeUpdateGroup.class}) @RequestBody OfficeDTO officeDTO,
                         @PathVariable Long officeId) {
        return restWrapper.wrap(officeService.update(officeId, officeDTO));
    }


    @ResponseBody
    @RequestMapping(value = "/{officeId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object delete(@PathVariable Long officeId) {
        return restWrapper.wrap(officeService.destroy(officeId));
    }

}
