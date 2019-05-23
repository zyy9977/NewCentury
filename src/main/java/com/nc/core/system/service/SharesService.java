package com.nc.core.system.service;

import com.nc.core.system.pojo.Shares;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2018/9/18 17:36
 */

@Service
public interface SharesService {

    List<Shares> sharesService(int page, int pageSize);

    int selectSharesTotalService();

    void addShares(Shares shares);
}
