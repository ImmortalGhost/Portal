package ru.epam.university_portal.core.service.interface_service;

import ru.epam.university_portal.model.entity.GroupAndTeacher;

import java.util.List;

/**
 * Created by maksim on 12.05.16.
 */
public interface IGroupAndTeacherService {

    void createRelation(String nameTeacher, String lastNameTeacher, String nameGroup)throws Exception;
    void removeRelation(String nameTeacher, String lastNameTeacher, String nameGroup)throws Exception ;
    GroupAndTeacher getRelation(String nameTeacher, String lastNameTeacher, String nameGroup) throws Exception;
    List<GroupAndTeacher> getAll()throws Exception;


}
