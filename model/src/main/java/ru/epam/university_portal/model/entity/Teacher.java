package ru.epam.university_portal.model.entity;
import javax.persistence.*;
import java.io.*;
/**
 * Created by Владос on 04.05.2016.
 */
@Entity
@Table(name = ("teacher"))
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("id"))
    private int id;
    @Column(name = ("idPost"), nullable = false)
    private int idPost;
    @Column(name = ("idUser"), nullable = false)
    private int idUser;
    @Column(name = ("newMessagesIDs"))
    private String newMessagesIDs;

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
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

    public String getNewMessagesIDs() {
        return newMessagesIDs;
    }

    public void setNewMessagesIDs(String newMessagesIDs) {
        this.newMessagesIDs = newMessagesIDs;
    }
}
