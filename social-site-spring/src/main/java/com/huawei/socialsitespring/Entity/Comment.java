package com.huawei.socialsitespring.Entity;

public class Comment {
    private int cardId;//the cardId response to
    private String userId; //the user who publish this comment
    private String content;
    private long time; // the time when the comment get published
    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    @Override
    public String toString(){
        return this.getUserId()+" "+this.getCardId()+" "+this.getTime()+" "+this.getContent();
    }
}
