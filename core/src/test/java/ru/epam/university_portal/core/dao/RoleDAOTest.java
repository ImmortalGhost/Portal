package ru.epam.university_portal.core.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.epam.university_portal.core.dao.implementation_dao.PostDAO;
import ru.epam.university_portal.core.dao.implementation_dao.RoleDAO;
import ru.epam.university_portal.core.dao.interface_dao.IPostDAO;
import ru.epam.university_portal.core.dao.interface_dao.IRoleDAO;
import ru.epam.university_portal.core.hibernate_util.HibernateUtilTest;
import ru.epam.university_portal.model.entity.Post;
import ru.epam.university_portal.model.entity.Role;

/**
 * Created by maksim on 20.05.16.
 */
public class RoleDAOTest {
    private IRoleDAO roleDAOTest;
    private Role role;
    @Before
    public void setUp()throws Exception{
        roleDAOTest =
                new RoleDAO(HibernateUtilTest.getSessionFactory());
        roleDAOTest.create("Role1");
        role=new Role(4,"Role1");
    }
    @Test
    public void testGetPostByName()throws Exception{
        System.out.println("ROLEDAOTEST");
        Assert.assertEquals(role,roleDAOTest.get("Role1"));

    }
    @After
    public void freeMemory(){
        role=null;
        roleDAOTest=null;
    }
}
