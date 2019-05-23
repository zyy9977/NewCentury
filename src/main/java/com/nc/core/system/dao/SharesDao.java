package com.nc.core.system.dao;

import com.nc.core.system.pojo.Shares;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2018/9/18 16:54
 */
@Repository
public interface SharesDao {

    List<Shares> selectShares(@Param("page")int page, @Param("pageSize") int pageSize);

    int selectSharesTotal();

    void addShares(Shares shares);
}
