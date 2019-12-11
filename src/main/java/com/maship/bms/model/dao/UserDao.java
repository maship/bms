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

  @Select({
      "select p.* from user_role ur ",
      "join `role` r on ur.role_id = r.id ",
      "join role_permission rp on rp.role_id = r.id  ",
      "join permission p on p.id = rp.permission_id ",
      "where ur.user_id = #{userId}"
  })
  List<Permission> listPermission(@Param("userId") Long userId);
}
