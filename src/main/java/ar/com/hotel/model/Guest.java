package ar.com.hotel.model;

import java.sql.Date;

public class Guest {

    private Integer id;
    private String name;
    private String lastname;
    private Date birthdate;
    private String nationality;
    private String phone;
    private Integer reservationID;

    public Guest(String name, String lastname, Date birthdate, String nationality, String phone) {
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.phone = phone;
    }

    public Guest(Integer id, String name, String lastname, Date birthdate, String nationality, String phone) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    @Override
    public String toString() {
        return "Guest{" + "id=" + id + ", name=" + name + ", lastname=" + lastname + ", birthdate=" + birthdate + ", nationality=" + nationality + ", telephone=" + phone + '}';
    }

}
