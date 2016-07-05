package ru.epam.university_portal.model.entity;
import javax.persistence.*;
import java.io.*;
/**
 * Created by Владос on 04.05.2016.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "idUser", nullable = false)
    private int idUser;
    @Column(name = "idGroup", nullable = false)
    private int idGroup;
    @Column(name = "newMessagesIDs")
    private String newMessagesIDs;
    @Column(name = "interlocutorsIDs")
    private String interlocutorsIDs;

    public String getInterlocutorsIDs() {
        return interlocutorsIDs;
    }

    public void setInterlocutorsIDs(String interlocutorsIDs) {
        this.interlocutorsIDs = interlocutorsIDs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getNewMessagesIDs() {
        return newMessagesIDs;
    }

    public void setNewMessagesIDs(String newMessagesIDs) {
        this.newMessagesIDs = newMessagesIDs;
    }
}
