package com.maship.bms.chain;

import com.maship.bms.model.entity.Permission;
import com.maship.bms.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Clint
 * Date: 2019-12-10 16:18
 * Description:
 */
public class BmsUserDetails implements UserDetails {

  private User user;
  private List<Permission> permissions;

  public BmsUserDetails(User user, List<Permission> permissions) {
    this.user = user;
    this.permissions = permissions;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return permissions.stream()
        .filter(p -> p.getValue() != null)
        .map(p -> new SimpleGrantedAuthority(p.getValue()))
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return !user.getDisable();
  }
}
