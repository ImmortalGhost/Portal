package ru.epam.university_portal.core.test;

import ru.epam.university_portal.core.dao.GroupAndTeacherDAOTest;
import ru.epam.university_portal.core.dao.GroupDAOTest;

/**
 * Created by maksim on 20.05.16.
 */
public class Test {

    public static void main(String []argc)throws Exception{

       /*/ GroupAndTeacherDAOTest groupAndTeacherDAOTest=new GroupAndTeacherDAOTest();
        groupAndTeacherDAOTest.setUp();
        groupAndTeacherDAOTest.testGetRelation();
        groupAndTeacherDAOTest.freeMemory();
        /*/
        GroupDAOTest groupDAOTest=new GroupDAOTest();
        groupDAOTest.setUp();
        groupDAOTest.testGetGroupByName();
        groupDAOTest.freeMemory();

    }
}
