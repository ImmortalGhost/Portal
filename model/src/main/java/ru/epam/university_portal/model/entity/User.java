package ru.epam.university_portal.model.entity;

/**
 * Created by maksim on 02.05.16.
 */

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name= "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    public String firstName="firstName";
    public String lastName="firstName";
    public String age="23";
    public String login;
    public String password;
    public String idRole;
    public User(String firstName,String lastName,String age,String login, String password,String idRole) {
        super();
        this.firstName=firstName;
        this.lastName=lastName;
        this.age=age;
        this.login = login;
        this.password = password;
        this.idRole=idRole;
    }
    public User(String login, String password) {
        super();
        this.login = login;
        this.password = password;
    }
    public User() {
    }
  /*/  public String getName() {
        return login;
    }
    public void setName(String login) {
        this.login = login;
    }/*/
}
