package com.nc.common.init;

import com.nc.core.system.service.SysDictService;
import com.nc.core.system.service.serviceImpl.SysDictServiceImpl;
import com.nc.utils.SpringTool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author zhangyangyang
 * @date 2019/3/4 16:16
 */
public class InitCacheList {

    private static final Logger logger = LogManager.getLogger(InitCacheList.class);

  /*  private SysDictServiceImpl sysDictService;

    public SysDictService getSysDictService() {
        return sysDictService;
    }

    public void setSysDictService(SysDictServiceImpl sysDictService) {
        this.sysDictService = sysDictService;
    }*/

    //@Cacheable(value = "cacheDict", key = "#dictitem")
    public void init () {
        logger.info("-------查询缓存-----");
//        SysDictService sysDictService = (SysDictService)SpringTool.getBean("sysDictServiceImpl");
//        sysDictService.getDictByDictItem("");
    }

}
