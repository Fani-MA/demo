package me.fani.michael.persistence.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

//создаем перечисление для разграничения доступа на основе ролей(пользователь и администратор)
public enum Role {
    //задаем каждой роли разрешения
    USER(Set.of(Permission.USER_READ)),
    ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    //т.к. GrantedAuthority(права доступа) не простой(не возвращает String)
    //метод будет возвращать SimpleGrantedAuthority
    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
