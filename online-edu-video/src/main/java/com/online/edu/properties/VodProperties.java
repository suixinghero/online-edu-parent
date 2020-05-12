package com.online.edu.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xujin
 * @createtime 2020-04-17 14:08
 * @description
 */
@ConfigurationProperties(prefix = "aliyun.vod.file")
public class VodProperties {
    private String keyid;
    private String keysecret;

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getKeysecret() {
        return keysecret;
    }

    public void setKeysecret(String keysecret) {
        this.keysecret = keysecret;
    }
}
