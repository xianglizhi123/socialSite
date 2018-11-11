package com.huawei.socialsitespring.Impl;


import com.huawei.socialsitespring.Dao.CommentDao;
import com.huawei.socialsitespring.Entity.Comment;
import com.huawei.socialsitespring.Mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentImpl implements CommentDao {
    @Autowired
    CommentMapper commentMapper;
    public List<Comment> getComments(int cardId){
        return commentMapper.getComments(cardId);
    }
    public void addComments(Comment comment){
        commentMapper.addComments(comment);
    }
}
