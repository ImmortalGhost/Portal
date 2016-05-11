package ru.epam.university_portal.model.entity;
import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by Владос on 04.05.2016.
 */
@Entity
@Table(name = ("messages_1_to_1"))
public class Messages1To1 implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("id"))
    private int id;
    @Column(name = ("idFrom"), nullable = false)
    private int idFrom;
    @Column(name = ("idTo"), nullable = false)
    private int idTo;
    @Column(name = ("message"))
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(int idFrom) {
        this.idFrom = idFrom;
    }

    public int getIdTo() {
        return idTo;
    }

    public void setIdTo(int idTo) {
        this.idTo = idTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
