package com.twovtwok.backend.filter;

import com.twovtwok.backend.dao.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@ToString
public final class UserPrincipal implements AuthenticatedPrincipal {

    private final Long userId;
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    public UserPrincipal(User user) {
        Assert.notNull(user.getId(), "User ID must not be null");
        this.userId = user.getId();
    }

    @Override
    public String getName() {
        return userId.toString();
    }

    public Long getUserId() {
        return userId;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

}