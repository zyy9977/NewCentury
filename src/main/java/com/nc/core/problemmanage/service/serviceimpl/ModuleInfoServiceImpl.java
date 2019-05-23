package com.nc.core.problemmanage.service.serviceimpl;

import com.nc.common.exception.CustomException;
import com.nc.core.problemmanage.dao.ModuleInfoDao;
import com.nc.core.problemmanage.pojo.ModuleInfo;
import com.nc.core.problemmanage.service.ModuleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2019/2/28 11:32
 */
@Service
public class ModuleInfoServiceImpl implements ModuleInfoService {

    @Autowired
    private ModuleInfoDao moduleInfoDao;

    @Override
    public List<ModuleInfo> getModuleInfoListDao(int page, int pageSize) {
        int currPage = (page - 1) * pageSize;
        return moduleInfoDao.getModuleInfoListDao(currPage, pageSize);
    }

    @Override
    public int selectModuleInfoTotal() {
        return moduleInfoDao.selectModuleInfoTotal();
    }

    @Override
    public void oprModuleInfoService(ModuleInfo moduleInfo, String action) throws CustomException {
        switch (action) {
            case "A":
                moduleInfoDao.addModuleInfoDao(moduleInfo);
                break;
            case "U":
                moduleInfoDao.updModuleInfoDao(moduleInfo);
                break;
            case "D":
                moduleInfoDao.delModuleInfoDao(moduleInfo);
                break;
                default:
                    throw new CustomException("参数“action="+ action +"”错误，请检查！");
        }
    }
}
