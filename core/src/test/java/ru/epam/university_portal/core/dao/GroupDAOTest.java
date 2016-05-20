package ru.epam.university_portal.core.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.epam.university_portal.core.dao.implementation_dao.GroupAndTeacherDAO;
import ru.epam.university_portal.core.dao.implementation_dao.GroupDAO;
import ru.epam.university_portal.core.dao.interface_dao.IGroupAndTeacherDAO;
import ru.epam.university_portal.core.dao.interface_dao.IGroupDAO;
import ru.epam.university_portal.core.hibernate_util.HibernateUtilTest;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.GroupAndTeacher;

/**
 * Created by maksim on 20.05.16.
 */
public class GroupDAOTest {
    private IGroupDAO groupDAOTest;
    private Group group;
    @Before
    public void setUp()throws Exception{
        groupDAOTest =
                new GroupDAO(HibernateUtilTest.getSessionFactory());
        groupDAOTest.create("Group1");
        group=new Group(16,"Group1");
    }
    @Test
    public void testGetGroupByName()throws Exception{

        Assert.assertEquals(group,groupDAOTest.get("Group1"));

    }
    @After
    public void freeMemory(){
        groupDAOTest=null;
        group=null;
    }

}
