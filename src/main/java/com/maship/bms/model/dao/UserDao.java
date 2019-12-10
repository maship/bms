package com.maship.bms.model.dao;

import com.maship.bms.model.entity.Permission;
import com.maship.bms.model.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Clint
 * Date: 2019-12-09 16:41
 * Description:
 */
@Repository
public interface UserDao {

  @Select("select * from `user` where username = #{username} limit 1")
  User query(@Param("username") String username);

  @Select("select p.* from permission p join user_permission up on p.id = up.permission_id where up.user_id = #{userId}")
  List<Permission> listPermission(@Param("userId") Long userId);
}
