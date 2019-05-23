package com.nc.core.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangyangyang
 * @date 2018/11/9 10:06
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping(value = "/newCentury/{pageName}", method = RequestMethod.GET)
    public String newCentury(@PathVariable String pageName) {
        return pageName;
    }
}
