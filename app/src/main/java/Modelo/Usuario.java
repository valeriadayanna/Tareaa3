package Modelo;

import java.io.Serializable;

public class Usuario implements Serializable {

    String user, password, photo, roleID;

    public Usuario() {
    }

    public Usuario(String user, String password, String photo, String roleID) {
        this.user = user;
        this.password = password;
        this.photo = photo;
        this.roleID = roleID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
    

}
