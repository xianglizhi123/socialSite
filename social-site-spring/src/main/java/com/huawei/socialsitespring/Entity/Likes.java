package com.huawei.socialsitespring.Entity;

public class Likes {
    private String userId;
    private int cardId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
    @Override
    public String toString(){
        return this.userId+" "+this.cardId;
    }
}
