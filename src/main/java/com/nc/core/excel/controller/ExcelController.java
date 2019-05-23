package com.nc.core.excel.controller;

import com.alibaba.fastjson.JSONArray;
import com.nc.common.model.ReturnModel;
import com.nc.common.model.ReturnStatus;
import com.nc.core.excel.service.ExcelService;
import com.nc.core.excel.util.ReadXML;
import com.nc.core.system.pojo.Shares;
import com.nc.core.system.service.SharesService;
import com.nc.core.excel.util.ExcelUtil;
import com.nc.core.excel.vo.ExcelBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.beans.IntrospectionException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;

/**
 * @author zhangyangyang
 * @date 2019/3/15 14:16
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    private static final Logger LOGGER = LogManager.getLogger(ExcelController.class);

    @Autowired
    private ExcelService excelService;

    @Autowired
    private SharesService sharesService;

    @Autowired
    private ReadXML readXML;
    /**
     * 表格导出
     * @param response
     * @throws IOException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws IntrospectionException
     * @throws ParseException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response) throws IOException, InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
        List<Shares> list = sharesService.sharesService(1, 10000000);
        String sheetName = "Sheet1";

        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        //设置标题栏
        excel.add(new ExcelBean("序号","id",0));
        excel.add(new ExcelBean("上证指数","shzs",0));
        excel.add(new ExcelBean("深证成指","szzs",0));
        map.put(0, excel);
        XSSFWorkbook xssfWorkbook = ExcelUtil.createExcelFile(Shares.class, list, map, sheetName);

        response.reset(); //清除buffer缓存
        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=ace.xlsx");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //导出Excel对象
        OutputStream output;
        try {
            output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            bufferedOutPut.flush();
            xssfWorkbook.write(bufferedOutPut);
            bufferedOutPut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * excel导入
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public String importExcel(@RequestParam("file") CommonsMultipartFile file, @RequestParam("tableName") String tableName, @RequestParam("userName") String userName) throws Exception {
        String fileName = file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        List list = ExcelUtil.getBankListByExcel(inputStream, fileName);
        excelService.insertObjectListService(list, tableName, userName);

        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setMsg("导入成功");
        return JSONArray.toJSONString(returnModel);
    }

    @RequestMapping(value = "/readXml", method = RequestMethod.GET)
    public String excel2Dbf() throws ParserConfigurationException, SAXException, IOException {
        Map<String, Object> map = readXML.recordConfig();
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            if (key.indexOf("List") >= 0) {
                List<Map<String, String>> list = (List<Map<String, String>>) entry.getValue();
                for (int i = 0, len = list.size(); i < len; i++) {
                    Map<String, String> colMap = list.get(i);
                    Iterator<Map.Entry<String, String>> iterator1 = colMap.entrySet().iterator();
                    while (iterator1.hasNext()) {
                        Map.Entry<String, String> keyCol = iterator1.next();
                        System.out.println(keyCol.getKey() + ":" + keyCol.getValue());
                    }
                }
            } else {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }

        return JSONArray.toJSONString(map);
    }
}


