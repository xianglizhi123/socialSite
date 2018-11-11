package com.huawei.socialsitespring.Dao;

import java.util.List;

public interface FriendsDao {
    int addFriend(String userId, String friendId);
    List<String> getFriends(String userId);
}
