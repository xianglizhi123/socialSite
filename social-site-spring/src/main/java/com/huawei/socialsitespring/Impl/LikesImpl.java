package com.huawei.socialsitespring.Impl;


import com.huawei.socialsitespring.Dao.LikesDao;
import com.huawei.socialsitespring.Entity.Likes;
import com.huawei.socialsitespring.Mapper.LikesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LikesImpl implements LikesDao {
    @Autowired
    LikesMapper likesMapper;
    public List<Likes> getLikes(int cardId){
        return likesMapper.getLikes(cardId);
    }
    public int addLikes(Likes likes){
        return likesMapper.addLikes(likes);
    }

}
