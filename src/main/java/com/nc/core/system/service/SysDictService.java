package com.nc.core.system.service;

import com.nc.core.system.pojo.SysDict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2019/3/4 14:16
 */
@Service
public interface SysDictService {

    List<SysDict> getDictByDictItem(String dictitem);

    List<SysDict> getDictForDist();
}
