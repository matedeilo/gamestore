package com.upc.bean;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DeliveryWS")
public class DeliveryWS {

    private String apkUrl;

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }
}
