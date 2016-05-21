package ru.epam.university_portal.core.dao;

/**
 * Created by maksim on 21.05.16.
 */



import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GroupDAOTest.class,
        PostDAOTest.class,
        RoleDAOTest.class
})
public class DAOTests {
}
