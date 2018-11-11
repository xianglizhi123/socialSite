package com.huawei.socialsitespring.Mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface FriendsMapper {

    @Insert("insert into friends (userId,friendId) values(#{userId},#{friendId})")
    int addFriend(@Param("userId") String userId, @Param("friendId") String friendId);
    @Select("select friendId from friends where userId=#{userId}")
    @ResultType(String.class)
    List<String> getFriends(@Param("userId") String userId);

}
