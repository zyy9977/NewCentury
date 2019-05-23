package com.nc.core.excel.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyangyang
 * @date 2019/1/28 18:18
 */
public class ExcelClass {

    public  Map<Integer,List<ExcelBean>> contentExcel(){
        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        //设置标题栏
        excel.add(new ExcelBean("序号","id",0));
        excel.add(new ExcelBean("上证指数","shzs",0));
        excel.add(new ExcelBean("深证成指","szzs",0));
        map.put(0, excel);
        return map;
    }
}
