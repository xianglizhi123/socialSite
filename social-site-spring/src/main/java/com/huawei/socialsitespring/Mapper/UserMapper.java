package com.huawei.socialsitespring.Mapper;



import com.huawei.socialsitespring.Entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    @Results( id="userResult", value=
            {
                    @Result(property ="userId",column="userId"),
                    @Result(property="userPwd",column="userPwd"),
                    @Result(property="school",column="school"),
                    @Result(property="phone",column="phone"),
                    @Result(property="address",column="address"),
            }
    )
    @Select("Select userId,userPwd,school,phone,address from users where userId=#{userId}")
    User getUser(String userId);
    @Select("select count(*) from users where userId=#{userId}")
    int hasUser(String userId);
    @Insert("insert into users (userId,userPwd,school,phone,address) values(#{user.userId},#{user.userPwd},#{user.school},#{user.phone},#{user.address})")
    @Results(
            {
                    @Result(property ="userId",column="userId"),
                    @Result(property="userPwd",column="userPwd"),
                    @Result(property="school",column="school"),
                    @Result(property="phone",column="phone"),
                    @Result(property="address",column="address"),
            }
    )
    void signUp(@Param("user") User user);
    @Update("update users set school=#{user.school},phone=#{user.phone},address=#{user.address},image=#{user.image} where userId=#{user.userId}")
    @Results(
            {
                    @Result(property ="userId",column="userId"),
                    @Result(property="userPwd",column="userPwd"),
                    @Result(property="school",column="school"),
                    @Result(property="phone",column="phone"),
                    @Result(property="address",column="address"),
                    @Result(property="image",column="image"),
            }
    )
    void updateUser(@Param("user") User user);
}
