package com.nc.core.system.service.serviceImpl;

import com.nc.core.system.dao.SharesDao;
import com.nc.core.system.pojo.Shares;
import com.nc.core.system.service.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2018/9/18 17:39
 */
@Service
public class SharesServiceImpl implements SharesService {

    @Autowired
    private SharesDao sharesDao;

    @Override
    public List<Shares> sharesService(int page, int pageSize) {
        int currPage = (page - 1) * pageSize;
        return sharesDao.selectShares(currPage, pageSize);
    }

    @Override
    public int selectSharesTotalService() {
        return sharesDao.selectSharesTotal();
    }

    @Override
    public void addShares(Shares shares) {
        sharesDao.addShares(shares);
    }

}
