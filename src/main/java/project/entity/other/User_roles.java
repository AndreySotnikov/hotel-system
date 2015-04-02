package project.entity.other;

import javax.persistence.*;

/**
 * Created by andrey on 24.03.15.
 */
@Entity
public class User_roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_role_id")
    private Integer user_roleId;
    private String ROLE;
    @ManyToOne
    //@JoinColumn(name = "username")
    private Users users;

    public User_roles() {
    }

    public User_roles(String ROLE, Users users) {
        this.ROLE = ROLE;
        this.users = users;
    }

    public Integer getUser_roleId() {
        return user_roleId;
    }

    public void setUser_roleId(Integer user_roleId) {
        this.user_roleId = user_roleId;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User_roles that = (User_roles) o;

        if (ROLE != null ? !ROLE.equals(that.ROLE) : that.ROLE != null) return false;
        if (user_roleId != null ? !user_roleId.equals(that.user_roleId) : that.user_roleId != null) return false;
        if (users != null ? !users.equals(that.users) : that.users != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user_roleId != null ? user_roleId.hashCode() : 0;
        result = 31 * result + (ROLE != null ? ROLE.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User_roles{" +
                "user_roleId=" + user_roleId +
                ", ROLE='" + ROLE + '\'' +
                ", users=" + users +
                '}';
    }
}
