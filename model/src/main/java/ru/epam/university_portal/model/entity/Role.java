package ru.epam.university_portal.model.entity;
import javax.persistence.*;
import java.io.Serializable;
/**
 * Created by Владос on 04.05.2016.
 */
@Entity
@Table(name = ("role"))
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("id"))
    private int id;
    @Column(name = ("name"), nullable = false)
    private String name;

    public Role(){}
    public Role(int id,String name){
        this.id=id;
        this.name=name;
    }
@Override
public boolean equals(Object object){
    Role other=(Role)object;
    return id== other.id&&name.equals(other.name);

}
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
