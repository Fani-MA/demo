package me.fani.michael.persistence.entity;

//permission - разрешение
//с помощью Permission будем разграничивать доступ на основе разрешений, а не ролей
public enum Permission {
    USER_READ("user:read"),
    USER_WRITE("user:write");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
