package com.nc.core.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.nc.common.model.ReturnModel;
import com.nc.common.model.ReturnStatus;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * @author zhangyangyang
 * @date 2019/1/18 15:14
 */
@RestController
@RequestMapping("/file")
public class FileController {

    /**
     * 文件上传
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/fileUpLoad")
    public String testUpload(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) throws IOException {
        ServletContext servletContext = request.getServletContext();//获取ServletContext的对象 代表当前WEB应用
        //String realPath = servletContext.getRealPath("/uploads");//得到文件上传目的位置的真实路径
        String realPath = "f://uploads";
        File file1 = new File(realPath);
        if(!file1.exists()){
            file1.mkdir();   //如果该目录不存在，就创建此抽象路径名指定的目录。
        }
        String prefix = UUID.randomUUID().toString();
        prefix = prefix.replace("-","");
        String fileName = prefix+"_"+file.getOriginalFilename();//使用UUID加前缀命名文件，防止名字重复被覆盖
        InputStream in = file.getInputStream(); //声明输入输出流
        OutputStream out = new FileOutputStream(new File(realPath+"\\"+fileName));//指定输出流的位置;
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = in.read(buffer)) != -1){
            out.write(buffer, 0, len);
            out.flush();               //类似于文件复制，将文件存储到输入流，再通过输出流写入到上传位置
        }

        out.close();
        in.close();

        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        returnModel.setCode(ReturnStatus.OK.getCode());
        returnModel.setMsg("上传成功");

        return JSONArray.toJSONString(returnModel);
    }

    /**
     * 文件下载
     * @param filename 文件名称
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/fileDownload",method=RequestMethod.GET) //匹配的是href中的download请求
    public ResponseEntity<byte[]> download(@RequestParam("filename") String filename) throws IOException{
        String downloadFilePath="f://uploads";//从上传文件夹中去取
        File file = new File(downloadFilePath + File.separator + filename);//新建一个文件
        HttpHeaders headers = new HttpHeaders();// http头信息
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", filename);
        HttpStatus statusCode = HttpStatus.OK;//设置响应码
        ResponseEntity<byte[]> response = new ResponseEntity(FileUtils.readFileToByteArray(file), headers, statusCode);
        return response;
    }

}


