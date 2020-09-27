package com.simple4j.system.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import com.simple4j.system.response.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author hyc
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class JwtDto implements UserDetails {

	private UserInfo userInfo;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userInfo.getPermissions().stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return userInfo.getPassword();
	}

	@Override
	public String getUsername() {
		return userInfo.getAccount();
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
		return true;
	}
}
