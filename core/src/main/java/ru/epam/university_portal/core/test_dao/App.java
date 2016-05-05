package ru.epam.university_portal.core.test_dao;
import org.hibernate.SessionFactory;
import ru.epam.university_portal.core.dao.IUserDAO;
import ru.epam.university_portal.core.dao.hibernate_util.HibernateUtil;
import ru.epam.university_portal.core.dao.impl.UserDAOImpl;
import ru.epam.university_portal.core.service.IUserService;
import ru.epam.university_portal.core.service.impl.UserServiceImpl;
import ru.epam.university_portal.model.entity.User;

import ru.epam.university_portal.core.bean_configuration.BeanConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by maksim on 01.05.16.
 */
public class App {

    public static void main(String []argc){
        /*/SessionFactory sessionFactory=        HibernateUtil.getSessionFactory();
        IUserDAO iUserDAO=new UserDAOImpl(sessionFactory);
        IUserService iUserService=new UserServiceImpl(iUserDAO);
        User user =new User("usernane","username1","23","userl","userp","idUser");
        iUserService.addUser(user);/*/

        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeanConfiguration.class);
        UserServiceImpl userService=
                (UserServiceImpl) context.getBean(UserServiceImpl.class) ;
        User user =new User("usernane1245","username1","23","userl","userp","idUser");
        //userService.addUser(user);
      //  User user1=userService.getUser("admin");
        //if (user1!=null)System.out.print(user1.firstName);
        System.out.print("hello");

    }

}
