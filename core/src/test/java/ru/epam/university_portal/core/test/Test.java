package ru.epam.university_portal.core.test;

import ru.epam.university_portal.core.dao.*;

/**
 * Created by maksim on 20.05.16.
 */
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
public class Test {

    public static void main(String []argc)throws Exception{

       /*/ GroupAndTeacherDAOTest groupAndTeacherDAOTest=new GroupAndTeacherDAOTest();
        groupAndTeacherDAOTest.setUp();
        groupAndTeacherDAOTest.testGetRelation();
        groupAndTeacherDAOTest.freeMemory();
        /*/
       /*/ GroupDAOTest groupDAOTest=new GroupDAOTest();
        groupDAOTest.setUp();
        groupDAOTest.testGetGroupByName();
        groupDAOTest.freeMemory();/*/
      /*/  PostDAOTest postDAOTest=new PostDAOTest();
        postDAOTest.setUp();
        postDAOTest.testGetPostByName();
        postDAOTest.freeMemory();/*/
       /*/ RoleDAOTest roleDAOTest=new RoleDAOTest();
        roleDAOTest.setUp();
        roleDAOTest.testGetPostByName();
        roleDAOTest.freeMemory();/*/
        Result result = JUnitCore.runClasses(DAOTests.class);
        for (Failure failure : result.getFailures()) {
            System.out.println("не пройден тест: " + failure.toString());
        }

    }
}
