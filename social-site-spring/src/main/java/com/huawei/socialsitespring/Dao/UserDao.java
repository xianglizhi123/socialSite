package com.huawei.socialsitespring.Dao;

import com.huawei.socialsitespring.Entity.User;

public interface UserDao {
    Boolean hasUser(String userId);
    String getPwd(String userId);
    void signUp(User user);
    User userDetail(String userId);
    void updateUser(User user);
}
