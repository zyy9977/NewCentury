package com.nc.core.system.dao;

import com.nc.core.system.pojo.SysDict;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2019/3/4 14:10
 */

@Repository
public interface SysDictDao {

    List<SysDict> getDictByDictItem(@Param("dictitem") String dictitem);

    List<SysDict> getDictForDist();
}
