package com.onlin.edu;

/**
 * @author xujin
 * @createtime 2020-05-04 16:39
 * @description
 */

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * 视频点播OpenAPI调用示例
 * 以GetVideoPlayAuth接口为例，其他接口请替换相应接口名称及私有参数
 */
public class Main {
    //账号AccessKey信息请填写(必选)
    private static String access_key_id = "";
    //账号AccessKey信息请填写(必选)
    private static String access_key_secret = "";
    //STS临时授权方式访问时该参数为必选，使用主账号AccessKey和RAM子账号AccessKey不需要填写
    private static String security_token = "";
    //以下参数不需要修改
    private final static String VOD_DOMAIN = "http://vod.cn-shanghai.aliyuncs.com";
    private final static String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final static String HTTP_METHOD = "GET";
    private final static String HMAC_SHA1_ALGORITHM = "HmacSHA1";//加密
    private final static String UTF_8 = "utf-8";
    private final static Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        //生成私有参数，不同API需要修改
        Map<String, String> privateParams = generatePrivateParamters();
        //生成公共参数，不需要修改
        Map<String, String> publicParams = generatePublicParamters();
        //生成OpenAPI地址，不需要修改
        String URL = generateOpenAPIURL(publicParams, privateParams);
        //发送HTTP GET 请求
        httpGet(URL);
    }
    /**
     * 生成视频点播OpenAPI私有参数
     * 不同API需要修改此方法中的参数
     * @return
     */
    private static Map<String, String> generatePrivateParamters() {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<>();
        // 视频ID
        privateParams.put("VideoId","6bb7e05dc0db4714a7c20583342b205b");
        // API名称
        privateParams.put("Action", "GetVideoPlayAuth");
        return privateParams;
    }
    /**
     * 生成视频点播OpenAPI公共参数
     * 不需要修改
     * @return
     */
    private static Map<String, String> generatePublicParamters() {
        Map<String, String> publicParams = new HashMap<>();
        publicParams.put("Format", "JSON");
        publicParams.put("Version", "2017-03-21");
        publicParams.put("AccessKeyId", access_key_id);
        publicParams.put("SignatureMethod", "HMAC-SHA1");
        publicParams.put("Timestamp", generateTimestamp());
        publicParams.put("SignatureVersion", "1.0");
        publicParams.put("SignatureNonce", generateRandom());
        if (security_token != null && security_token.length() > 0) {
            publicParams.put("SecurityToken", security_token);
        }
        return publicParams;
    }

    /**
     * 生成OpenAPI地址
     * @param privateParams
     * @return
     * @throws Exception
     */
    private static String generateOpenAPIURL(Map<String, String> publicParams, Map<String, String> privateParams) {
        return generateURL(VOD_DOMAIN, HTTP_METHOD, publicParams, privateParams);
    }
    /**
     * @param domain        请求地址
     * @param httpMethod    HTTP请求方式GET，POST等
     * @param publicParams  公共参数
     * @param privateParams 接口的私有参数
     * @return 最后的url
     */
    private static String generateURL(String domain, String httpMethod, Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> allEncodeParams = getAllParams(publicParams, privateParams);
        String cqsString = getCQS(allEncodeParams);
        out("CanonicalizedQueryString = " + cqsString);
        String stringToSign = httpMethod + "&" + percentEncode("/") + "&" + percentEncode(cqsString);
        out("StringtoSign = " + stringToSign);
        String signature = hmacSHA1Signature(access_key_secret, stringToSign);
        out("Signature = " + signature);
        return domain + "?" + cqsString + "&" + percentEncode("Signature") + "=" + percentEncode(signature);
    }

    private static List<String> getAllParams(Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> encodeParams = new ArrayList<String>();
        if (publicParams != null) {
            for (String key : publicParams.keySet()) {
                String value = publicParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        if (privateParams != null) {
            for (String key : privateParams.keySet()) {
                String value = privateParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        return encodeParams;
    }
    /**
     * 参数urlEncode
     *
     * @param value
     * @return
     */
    private static String percentEncode(String value) {
        try {
            String urlEncodeOrignStr = URLEncoder.encode(value, "UTF-8");
            String plusReplaced = urlEncodeOrignStr.replace("+", "%20");
            String starReplaced = plusReplaced.replace("*", "%2A");
            String waveReplaced = starReplaced.replace("%7E", "~");
            return waveReplaced;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取CQS 的字符串
     *
     * @param allParams
     * @return
     */
    private static String getCQS(List<String> allParams) {
        ParamsComparator paramsComparator = new ParamsComparator();
        Collections.sort(allParams, paramsComparator);
        String cqString = "";
        for (int i = 0; i < allParams.size(); i++) {
            cqString += allParams.get(i);
            if (i != allParams.size() - 1) {
                cqString += "&";
            }
        }
        return cqString;
    }

    private static class ParamsComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }

    private static String hmacSHA1Signature(String accessKeySecret, String stringtoSign) {
        try {
            String key = accessKeySecret + "&";
            try {
                SecretKeySpec signKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
                Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
                mac.init(signKey);
                byte[] rawHmac = mac.doFinal(stringtoSign.getBytes());
                //按照Base64 编码规则把上面的 HMAC 值编码成字符串，即得到签名值（Signature）
                return new String(new BASE64Encoder().encode(rawHmac));
            } catch (Exception e) {
                throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
            }
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成随机数
     *
     * @return
     */
    private static String generateRandom() {
        String signatureNonce = UUID.randomUUID().toString();
        return signatureNonce;
    }

    /**
     * 生成当前UTC时间戳
     *
     * @return
     */
    public static String generateTimestamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    private static String httpGet(String url) throws Exception {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet();
            httpGet.setURI(new URI(url));
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(3000)
                    .setSocketTimeout(3000)
                    .build();
            httpGet.setConfig(requestConfig);
            HttpResponse result = httpClient.execute(httpGet);

            String str;
            try {
                /**读取服务器返回过来的json字符串数据**/
                str = EntityUtils.toString(result.getEntity());
                EntityUtils.consume(result.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
            out(str);
            // 这里可以解析视频云点播服务端的响应结果
            return str;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw e;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (httpClient != null)
                    httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    private static void out(String newLine) {
        System.out.println(newLine);
        LOG.info(newLine);
    }
}