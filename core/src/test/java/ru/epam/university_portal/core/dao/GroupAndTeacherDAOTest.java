package ru.epam.university_portal.core.dao;

import org.junit.*;
import ru.epam.university_portal.core.dao.implementation_dao.GroupAndTeacherDAO;
import ru.epam.university_portal.core.dao.interface_dao.IGroupAndTeacherDAO;
import ru.epam.university_portal.core.hibernate_util.HibernateUtilTest;
import ru.epam.university_portal.model.entity.GroupAndTeacher;

/**
 * Created by maksim on 20.05.16.
 */
public class GroupAndTeacherDAOTest {


    private IGroupAndTeacherDAO groupAndTeacherDAOTest;
    private GroupAndTeacher groupAndTeacher;

    @Before
    public void setUp()throws Exception{
    groupAndTeacherDAOTest =
    new GroupAndTeacherDAO(HibernateUtilTest.getSessionFactory());
    groupAndTeacherDAOTest.createRelation("Admin1", "Admin2", "Group");
    groupAndTeacher=new GroupAndTeacher(2,14,2);
    }
    @Test
    public void testGetRelation()throws Exception{
       Assert.assertEquals(groupAndTeacher,groupAndTeacherDAOTest.
               getRelation("Admin1", "Admin2", "Group"));
    }
    @After
    public void freeMemory(){
    groupAndTeacherDAOTest=null;
    groupAndTeacher=null;
    }
}
