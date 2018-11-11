package com.huawei.socialsitespring.Entity;

import com.alibaba.fastjson.JSONObject;

public class User {
    private String userId;
    private String userPwd;
    private String school;
    private String phone;
    private String address;
    private String image;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    @Override
    public String toString(){
        return this.userId+" "+this.userPwd;
    }
    public JSONObject toJson(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userId",this.userId);
        jsonObject.put("userPwd",this.userPwd);
        return jsonObject;
    }
}
