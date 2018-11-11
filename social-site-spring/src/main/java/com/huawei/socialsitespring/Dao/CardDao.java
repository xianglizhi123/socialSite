package com.huawei.socialsitespring.Dao;



import com.huawei.socialsitespring.Entity.Card;

import java.util.List;

public interface CardDao {
    int publishInfor(Card card);
    List<String> selfNews(String userId);
    List<Card> selfCards(String userId);
    List<Card> getFriendsCards(List<String> friends);
}
