package com.huawei.socialsitespring.Mapper;


import com.huawei.socialsitespring.Entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {
    @Select("select userId,cardId,content,time from comments where cardId=#{cardId} order by time+0 desc")
    @Results(
            {
                    @Result(property ="userId",column="userId"),
                    @Result(property="cardId",column="cardId"),
                    @Result(property="content",column="content"),
                    @Result(property="time",column="time"),
            }
    )
    List<Comment> getComments(@Param("cardId") int cardId);
    @Insert("insert into comments (userId,cardId,content,time) values(#{comment.userId},#{comment.cardId},#{comment.content},#{comment.time})")
    @Results(
            {
                    @Result(property ="userId",column="userId"),
                    @Result(property="cardId",column="cardId"),
                    @Result(property="content",column="content"),
                    @Result(property="time",column="time"),
            }
    )
    void addComments(@Param("comment") Comment comment);
}
