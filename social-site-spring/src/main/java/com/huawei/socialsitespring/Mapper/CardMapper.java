package com.huawei.socialsitespring.Mapper;


import com.huawei.socialsitespring.Entity.Card;
import com.huawei.socialsitespring.Tools.SimpleSelectInExtendedLanguageDriver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CardMapper {
    @Results( id="card", value=
            {
                    @Result(property ="cardId",column="cardId"),
                    @Result(property ="userId",column="userId"),
                    @Result(property="content",column="content"),
                    @Result(property="publishTime",column="time"),
            }
    )
    @Insert("insert into card (userId,content,time) values(#{userId},#{content},#{publishTime})")
    @Options(useGeneratedKeys = true, keyProperty = "cardId", keyColumn = "cardId")
    int publishInfor(Card card);
    @Select("select content from card where userId=#{userId}")
    @ResultType(String.class)
    List<String> selfNews(@Param("userId") String userId);
    @Select("select cardId,userId,content,time from card where userId=#{userId} order by time+0 desc")
    @Results(
            {
                    @Result(property ="cardId",column="cardId"),
                    @Result(property ="userId",column="userId"),
                    @Result(property="content",column="content"),
                    @Result(property="publishTime",column="time"),
            }
    )
    List<Card> selfCards(@Param("userId") String userId);
    @Lang(SimpleSelectInExtendedLanguageDriver.class)
    @Select("select cardId,userId,content,time from card where userId in (#{friends})")
    @Results(
            {
                    @Result(property ="cardId",column="cardId"),
                    @Result(property ="userId",column="userId"),
                    @Result(property="content",column="content"),
                    @Result(property="publishTime",column="time"),
            }
    )
    List<Card> getFriendsCards(@Param("friends") List<String> friends);

}
