package com.huawei.socialsitespring.Impl;

import com.huawei.socialsitespring.Dao.CardDao;
import com.huawei.socialsitespring.Entity.Card;
import com.huawei.socialsitespring.Mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardImpl implements CardDao {
    @Autowired
    CardMapper cardMapper;
    public int publishInfor(Card card){
        return cardMapper.publishInfor(card);
    }
    public List<String> selfNews(String userId){return cardMapper.selfNews(userId);}
    public List<Card> selfCards(String userId){return cardMapper.selfCards(userId);}
    public List<Card> getFriendsCards(List<String> friends){return cardMapper.getFriendsCards(friends);}
}
