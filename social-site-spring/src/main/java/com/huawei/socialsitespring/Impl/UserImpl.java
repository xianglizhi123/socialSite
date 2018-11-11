package com.huawei.socialsitespring.Impl;


import com.huawei.socialsitespring.Dao.UserDao;
import com.huawei.socialsitespring.Entity.User;
import com.huawei.socialsitespring.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserImpl implements UserDao {
    @Autowired
    UserMapper userMapper;
    public Boolean hasUser(String userId){
        int count=userMapper.hasUser(userId);
        if(count>0)
            return true;
        else
        return false;
    }
    public String getPwd(String userId){
        User user=userMapper.getUser(userId);
        return user.getUserPwd();
    }
    public void signUp(User user){
        userMapper.signUp(user);
    }
    public User userDetail(String userId){
        return userMapper.getUser(userId);
    }
    public void updateUser(User user){
        userMapper.updateUser(user);
    }
}
