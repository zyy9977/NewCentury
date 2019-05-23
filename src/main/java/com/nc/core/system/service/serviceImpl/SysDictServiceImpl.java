package com.nc.core.system.service.serviceImpl;

import com.nc.core.system.service.SysDictService;
import com.nc.core.system.dao.SysDictDao;
import com.nc.core.system.pojo.SysDict;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2019/3/4 14:17
 */
@Service
public class SysDictServiceImpl implements SysDictService {

    private static final Logger logger = LogManager.getLogger(SysDictServiceImpl.class);

    @Autowired
    private SysDictDao sys_dictDao;

    @Override
    @Cacheable(value = "cacheDict", key = "#dictitem")
    public List<SysDict> getDictByDictItem(String dictitem) {
        logger.debug("-------getDictByDictItem-------");
        return sys_dictDao.getDictByDictItem(dictitem);
    }

    @Override
    @CachePut("cacheDict")
    public List<SysDict> getDictForDist() {
        return sys_dictDao.getDictForDist();
    }
}
