package com.nc.study.wx.utils;

import com.nc.study.wx.common.AesException;
import com.nc.study.wx.common.SHA1;

/**
 * @author zhangyangyang
 * @date 2018/10/9 10:23
 */
public class WXPublicUtils {

    public static final String token = "NEW_CENTURY";

    /**
     * 验证Token
     * @param msgSignature 签名串，对应URL参数的signature
     * @param timeStamp 时间戳，对应URL参数的timestamp
     * @param nonce 随机串，对应URL参数的nonce
     *
     * @return 是否为安全签名
     * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public static boolean verifyUrl(String msgSignature, String timeStamp, String nonce) throws AesException {
        // 这里的 WXPublicConstants.TOKEN 填写你自己设置的Token就可以了
        String signature = SHA1.getSHA1(token, timeStamp, nonce);
        if (!signature.equals(msgSignature)) {
            throw new AesException(AesException.ValidateSignatureError);
        }
        return true;
    }

//    public JSONObject addMaterialEver(File file) throws Exception {
//        try {
//
//            //开始获取证书
//            String accessToken = AccessTokenUtil.getAccessToken().getAccess_token();
//
//            //上传图片素材
//            String path = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=" + accessToken;
//            String result = HttpsUtil.connectHttpsByPost(path, null, file);
//            result = result.replaceAll("[\\\\]", "");
//
//            JSONObject resultJSON = JSON.parseObject(result);
//            if (resultJSON != null) {
//                if (resultJSON.get("url") != null) {
//                    System.out.println("上传图文消息内的图片成功");
//                    return resultJSON;
//                } else {
//                    System.out.println("上传图文消息内的图片失败");
//                }
//            }
//            return null;
//        } catch (Exception e) {
//            System.out.println("程序异常");
//        } finally {
//            System.out.println("结束上传图文消息内的图片---------------------");
//        }
//    }



}
