package com.huawei.socialsitespring.Mapper;


import com.huawei.socialsitespring.Entity.Likes;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LikesMapper {
    @Results(
            {
                    @Result(property ="cardId",column="cardId"),
                    @Result(property ="userId",column="userId"),
            }
    )
    @Select("select * from likes where cardId=#{cardId}")
    List<Likes> getLikes(@Param("cardId") int cardId);
    @Insert("insert into likes (cardId,userId) values(#{cardId},#{userId}) ")
    @Results(
            {
                    @Result(property ="cardId",column="cardId"),
                    @Result(property ="userId",column="userId"),
            }
    )
    int addLikes(Likes likes);

}
