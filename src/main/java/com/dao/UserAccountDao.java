package com.dao;

import com.pojo.UserAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserAccountDao {
    @Select("select user_id as userId,user_name as userName,money from user_account where user_id =#{userId}")
    UserAccount selectByPrimaryKey(UserAccount userAccount);

    @Update("update user_account set money=#{money} where user_id=#{userId}")
    void updateSelective(UserAccount userAccount);

    @Insert("insert user_account(user_id,user_name,money) values(#{userId},#{userName},#{money})")
    void insert(UserAccount userAccount);
}
