package com.huawei.socialsitespring.Dao;


import com.huawei.socialsitespring.Entity.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> getComments(int cardId);
    void addComments(Comment comment);
}
