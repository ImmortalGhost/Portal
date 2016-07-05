package ru.epam.university_portal.model.entity;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
/**
 * Created by Владос on 04.05.2016.
 */
@Entity
@Table(name = ("groups"))
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("id"))
    private int id;
    @Column(name = ("name"), nullable = false)
    private String name;

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
}
