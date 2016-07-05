package ru.epam.university_portal.model.entity;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Владос on 04.05.2016.
 */
@Entity
@Table(name = "group_and_teacher")
public class GroupAndTeacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "idGroup", nullable = false)
    private int idGroup;
    @Column(name = "idTeacher", nullable = false)
    private int idTeacher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }
}
