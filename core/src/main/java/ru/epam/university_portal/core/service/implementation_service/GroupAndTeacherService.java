package ru.epam.university_portal.core.service.implementation_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.epam.university_portal.core.dao.interface_dao.IGroupAndTeacherDAO;
import ru.epam.university_portal.core.service.interface_service.IGroupAndTeacherService;
import ru.epam.university_portal.model.entity.GroupAndTeacher;

import java.util.List;

/**
 * Created by maksim on 13.05.16.
 */
@Service
public class GroupAndTeacherService implements IGroupAndTeacherService{

    private IGroupAndTeacherDAO groupAndTeacherDAO;

    @Autowired
    public GroupAndTeacherService (IGroupAndTeacherDAO groupAndTeacherDAO){

        this.groupAndTeacherDAO=groupAndTeacherDAO;
    }

  public   void createRelation(String nameTeacher, String lastNameTeacher, String nameGroup)throws Exception
    {
     groupAndTeacherDAO.createRelation(nameTeacher,lastNameTeacher,nameGroup);
    }
  public  void removeRelation(String nameTeacher, String lastNameTeacher, String nameGroup)throws Exception {
      groupAndTeacherDAO.removeRelation(nameTeacher,lastNameTeacher,nameGroup);
  }
  public  GroupAndTeacher getRelation(String nameTeacher, String lastNameTeacher, String nameGroup) throws Exception{
      return groupAndTeacherDAO.getRelation(nameTeacher,lastNameTeacher,nameGroup);
  }
  public   List<GroupAndTeacher> getAll()throws Exception{


   return    groupAndTeacherDAO.getAll();
  }


}
