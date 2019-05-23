package com.nc.aop;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.nc.common.model.ReturnModel;
import com.nc.common.model.ReturnStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author zhangyangyang
 * 使用配置式aop处理异常
 * @date 2018/12/19 11:43
 */

public class ExceptionAspect {

    private static final Logger logger = LogManager.getLogger(ExceptionAspect.class);

    private Boolean showException;

    public void setShowException(Boolean showException) {
        this.showException = showException;
    }

    /**
     * 将内容输出到浏览器
     */
    private void writeContent(Exception ex) {
        String errorMsg = StringUtils.isEmpty(ex.getMessage()) ? "系统异常" : ex.getMessage();
        logger.info(errorMsg);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.reset();
        response.setContentType("text/html;charset=gbk");
        response.setCharacterEncoding("gbk");
        response.setHeader("Cache-Control", "no-cache");
        ReturnModel<Map<String, Object>> returnModel = new ReturnModel();
        if (showException) {
            returnModel.setCode(ReturnStatus.EXCEPTION.getCode());
            returnModel.setMsg(errorMsg);
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.print(JSONArray.toJSONString(returnModel));
        writer.flush();
        writer.close();
    }

}
