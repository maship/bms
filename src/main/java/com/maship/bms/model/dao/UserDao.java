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

  /**
   * 用户角色关联的权限 减去 用户直接关联的权限(非增加)
   * union all 用户直接关联的权限(增加)
   */
  @Select({
      "select p.* from user_role ur ",
      "join `role` r on ur.role_id = r.id ",
      "join role_permission rp on rp.role_id = r.id ",
      "join permission p on p.id = rp.permission_id ",
      "where ur.user_id = #{userId} ",
      "and p.id not in (select up.permission_id from user_permission up where up.user_id = #{userId} and up.plus = 0) ",
      "union all ",
      "select p.* from user_permission up ",
      "join permission p on p.id = up.permission_id ",
      "where up.user_id = #{userId} and up.plus = 1"
  })
  List<Permission> listPermission(@Param("userId") Long userId);

}
