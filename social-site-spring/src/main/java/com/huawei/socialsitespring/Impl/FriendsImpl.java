package com.huawei.socialsitespring.Impl;

import com.huawei.socialsitespring.Dao.FriendsDao;
import com.huawei.socialsitespring.Mapper.FriendsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FriendsImpl implements FriendsDao {
    @Autowired
    FriendsMapper friendsMapper;
    public int addFriend(String userId,String friendId){
        return friendsMapper.addFriend(userId,friendId);
    }
    public List<String> getFriends(String userId){
        return friendsMapper.getFriends(userId);
    }
}
