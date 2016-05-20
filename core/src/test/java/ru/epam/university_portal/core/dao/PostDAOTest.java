package ru.epam.university_portal.core.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.epam.university_portal.core.dao.implementation_dao.GroupDAO;
import ru.epam.university_portal.core.dao.implementation_dao.PostDAO;
import ru.epam.university_portal.core.dao.interface_dao.IGroupDAO;
import ru.epam.university_portal.core.dao.interface_dao.IPostDAO;
import ru.epam.university_portal.core.hibernate_util.HibernateUtilTest;
import ru.epam.university_portal.model.entity.Group;
import ru.epam.university_portal.model.entity.Post;

/**
 * Created by maksim on 20.05.16.
 */
public class PostDAOTest {

    private IPostDAO postDAOTest;
    private Post post;
    @Before
    public void setUp()throws Exception{
        postDAOTest =
                new PostDAO(HibernateUtilTest.getSessionFactory());
        postDAOTest.create("Post1");
        post=new Post(6,"Post1");
    }
    @Test
    public void testGetPostByName()throws Exception{

        Assert.assertEquals(post,postDAOTest.get("Post1"));

    }
    @After
    public void freeMemory(){
        post=null;
        postDAOTest=null;
    }
}
