package com.huawei.socialsitespring.Dao;


import com.huawei.socialsitespring.Entity.Likes;

import java.util.List;

public interface LikesDao {
    List<Likes> getLikes(int cardId);
    int addLikes(Likes likes);
}
