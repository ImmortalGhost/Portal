package ru.epam.university_portal.core.bean_configuration;

/**
 * Created by maksim on 02.05.16.
 */
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ru.epam.university_portal.core.dao.hibernate_util.HibernateUtil;
@Configuration
@ComponentScan("ru.epam.university_portal.core.dao.impl &&" +
        " ru.epam.university_portal.core.service.impl")
public class BeanConfiguration {
    @Bean
    SessionFactory sessionFactory(){

        return HibernateUtil.getSessionFactory();
    }
}
