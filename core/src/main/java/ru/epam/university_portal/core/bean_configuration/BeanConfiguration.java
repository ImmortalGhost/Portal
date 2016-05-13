package ru.epam.university_portal.core.bean_configuration;

/**
 * Created by maksim on 02.05.16.
 */
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import ru.epam.university_portal.core.dao.hibernate_util.HibernateUtil;
import ru.epam.university_portal.core.logic.LoginLogic;

@ComponentScan("ru.epam.university_portal.core.logic && ru.epam.university_portal.core.dao.implementation_dao. && ru.epam.university_portal.core.service.implementation_service")
public class BeanConfiguration {
   @Bean
    SessionFactory sessionFactory(){
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
