package com.nc.core.problemmanage.dao;

import com.nc.core.problemmanage.pojo.ModuleInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2019/2/28 11:22
 */
@Repository
public interface ModuleInfoDao {

    List<ModuleInfo> getModuleInfoListDao(@Param("page")int page, @Param("pageSize") int pageSize);

    int selectModuleInfoTotal();

    void addModuleInfoDao(ModuleInfo moduleInfo);

    void updModuleInfoDao(ModuleInfo moduleInfo);

    void delModuleInfoDao(ModuleInfo moduleInfo);
}
