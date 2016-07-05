package ru.epam.university_portal.model.entity;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Владос on 04.05.2016.
 */
@Entity
@Table(name = "messages_from_news")
public class MessagesFromNews implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "idFrom", nullable = false)
    private int idFrom;
    @Column(name = "idNews", nullable = false)
    private int idNews;
    @Column(name = "message")
    private String message;

    public int getId() {
        return id;
    }

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
