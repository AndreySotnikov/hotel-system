package project.entity.other;

import javax.persistence.*;
import java.util.Set;

/**
* Created by andrey on 23.03.15.
*/
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String username;
    private String password;
    private Integer enabled;
    @OneToMany(mappedBy = "users")
    private Set<User_roles> user_roles;

    @PrePersist
    public void init() {
        enabled = 1;
    }

    public Users() {
    }

    public Users(String username, String password, Integer enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Set<User_roles> getUser_roles() {
        return user_roles;
    }

    public void setUser_roles(Set<User_roles> user_roles) {
        this.user_roles = user_roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (enabled != null ? !enabled.equals(users.enabled) : users.enabled != null) return false;
        if (password != null ? !password.equals(users.password) : users.password != null) return false;
        if (username != null ? !username.equals(users.username) : users.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}