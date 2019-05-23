package com.nc.core.excel.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2018/9/18 17:36
 */

@Service
public interface ExcelService {

    void insertObjectListService(List list, String tableName, String userName) throws Exception;
}
