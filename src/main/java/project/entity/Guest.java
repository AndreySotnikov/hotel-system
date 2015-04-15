package project.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by andrey on 15.04.15.
 */
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idGuest;
    private String fio;
    private String phone;
    private String email;
    private int tenantId;

    @OneToMany(mappedBy = "guest")
    private transient List<TimeTable> timeTables;

    public Guest() {
    }

    public Guest(String fio, String phone, String email, int tenantId) {
        this.fio = fio;
        this.phone = phone;
        this.email = email;
        this.tenantId=tenantId;
    }

    public int getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(int idGuest) {
        this.idGuest = idGuest;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    public List<TimeTable> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(List<TimeTable> timeTables) {
        this.timeTables = timeTables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guest guest = (Guest) o;

        if (idGuest != guest.idGuest) return false;
        if (email != null ? !email.equals(guest.email) : guest.email != null) return false;
        if (fio != null ? !fio.equals(guest.fio) : guest.fio != null) return false;
        if (phone != null ? !phone.equals(guest.phone) : guest.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idGuest;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "idGuest=" + idGuest +
                ", fio='" + fio + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", tenantId=" + tenantId +
                '}';
    }
}

