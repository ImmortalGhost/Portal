package ru.epam.university_portal.model.entity;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Владос on 04.05.2016.
 */
@Entity
@Table(name = ("group_and_teacher"))
public class GroupAndTeacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ("id"))
    private int id;
    @Column(name = ("idGroup"), nullable = false)
    private int idGroup;
    @Column(name = ("idTeacher"), nullable = false)
    private int idTeacher;


    public GroupAndTeacher(){}
    public GroupAndTeacher(int id,int idGroup,int idTeacher){
        this.id=id;
        this.idGroup=idGroup;
        this.idTeacher=idTeacher;
    }
   @Override
    public boolean equals(Object object){
        GroupAndTeacher other=(GroupAndTeacher)object;
        if(id!=other.id)
            return false;
        if(idGroup!=other.idGroup)
            return false;
        if(idTeacher!=other.idTeacher)
            return false;
        return true;
    }
/*/
   @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + idGroup;
        result = prime * result + idTeacher;
        return result;
    }/*/

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
