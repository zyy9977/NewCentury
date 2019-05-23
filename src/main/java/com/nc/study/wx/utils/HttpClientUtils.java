package com.nc.study.wx.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author zhangyangyang
 * @date 2018/10/10 13:24
 */
public class HttpClientUtils {

    public static JSONObject doGet(String url) {
        // 指定get请求
        HttpGet httpGet = new HttpGet(url);
        // 创建httpclient
        HttpClient httpClient = HttpClientBuilder.create().build();
        // 发送请求
        HttpResponse httpResponse;
        //返回的json
        JSONObject jsonObject = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            // 验证请求是否成功
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 得到请求响应信息
                String resp = EntityUtils.toString(httpResponse.getEntity(),	"utf-8");
                // 返回json
                jsonObject = JSONObject.parseObject(resp);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject doPost(String url, JSONObject jsonData) {
        // 指定Post请求
        HttpPost httpPost = new HttpPost(url);
        // 创建httpclient
        HttpClient httpClient = HttpClientBuilder.create().build();
        // 发送请求
        HttpResponse httpResponse;
        // 返回的json
        JSONObject jsonObject = null;
        // 封装post请求数据
        StringEntity entity = new StringEntity(jsonData.toString(), "utf-8");
        httpPost.setEntity(entity);
        try {
            // 发送请求
            httpResponse = httpClient.execute(httpPost);
            // 判断请求是否成功
            if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
                // 得到请求响应信息
                String resp = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                // 返回json
                jsonObject = JSONObject.parseObject(resp);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

}
