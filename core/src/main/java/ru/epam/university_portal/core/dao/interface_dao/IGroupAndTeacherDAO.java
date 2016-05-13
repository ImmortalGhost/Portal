package ru.epam.university_portal.core.dao.interface_dao;

import org.hibernate.Transaction;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.GroupAndTeacher;
import ru.epam.university_portal.model.entity.Teacher;
import ru.epam.university_portal.model.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface IGroupAndTeacherDAO {


     void createRelation(String nameTeacher, String lastNameTeacher, String nameGroup)throws Exception;
     void removeRelation(String nameTeacher, String lastNameTeacher, String nameGroup)throws Exception ;
     GroupAndTeacher getRelation(String nameTeacher, String lastNameTeacher, String nameGroup) throws Exception;
     List<GroupAndTeacher> getAll()throws Exception;
}
