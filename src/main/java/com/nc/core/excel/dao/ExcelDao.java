package com.nc.core.excel.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author zhangyangyang
 * @date 2018/9/18 16:54
 */
@Repository
public interface ExcelDao {
    int insertObjectList(Map<String, Object> map);
}
