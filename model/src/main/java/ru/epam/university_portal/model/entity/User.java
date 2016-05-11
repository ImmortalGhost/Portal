package ru.epam.university_portal.model.entity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.*;
import java.util.Date;
/**
 * Created by Владос on 04.05.2016.
 */
@Entity
@Table(name = ("user"))
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("id"))
    private int id;
    @Column(name = ("name"))
    private String name;
    @Column(name = ("lastName"))
    private String lastName;
    @Column(name = ("age"))
    private java.util.Date age;
    @Column(name = ("login"), nullable = false)
    private String login;
    @Column(name = ("password"), nullable = false)
    private String password;
    @Column(name = ("idRole"), nullable = false)
    private int idRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public java.util.Date getAge() {
        return age;
    }

    public void setAge(java.util.Date age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
