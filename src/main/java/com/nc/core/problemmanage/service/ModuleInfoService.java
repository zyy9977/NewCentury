package com.nc.core.problemmanage.service;

import com.nc.common.exception.CustomException;
import com.nc.core.problemmanage.pojo.ModuleInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2019/2/28 11:30
 */
@Service
public interface ModuleInfoService {
    List<ModuleInfo> getModuleInfoListDao(int page, int pageSize);

    int selectModuleInfoTotal();

    void oprModuleInfoService(ModuleInfo moduleInfo, String action) throws CustomException;

}

