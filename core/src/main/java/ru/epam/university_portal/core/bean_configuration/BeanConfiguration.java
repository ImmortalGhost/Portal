package ru.epam.university_portal.core.bean_configuration;

/**
 * Created by maksim on 02.05.16.
 */
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.epam.university_portal.core.dao.hibernate_util.HibernateUtil;
import ru.epam.university_portal.core.logic.LoginLogic;
import ru.epam.university_portal.core.service.IUserService;
import ru.epam.university_portal.core.service.impl.UserServiceImpl;

import java.io.File;

//@Configuration
/*/@ComponentScan("ru.epam.university_portal.core.dao.impl &&" +
        " ru.epam.university_portal.core.service.impl && " +


)/*/
@ComponentScan("ru.epam.university_portal.core.logic && ru.epam.university_portal.core.dao.impl && ru.epam.university_portal.core.service.impl"      )
public class BeanConfiguration {
   @Bean
    SessionFactory sessionFactory(){
      // File file = new File("/home/maksim/IdeaProjects/university_portal/core/src/hibernate.cfg.xml");
     ///return new org.hibernate.cfg.Configuration().configure(file).buildSessionFactory();
        return HibernateUtil.getSessionFactory();
    }
    /*/@Bean
    public IUserService getUserService(){
        return new UserServiceImpl(null);
    }/*/
    @Bean
    LoginLogic loginLogic(){
        return new LoginLogic();
    }
}
