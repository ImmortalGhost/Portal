package ru.epam.university_portal.core.dao.hibernate_util;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.File;

@SuppressWarnings("deprecation")
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
// create the SessionFactory from hibernate.cfg.xml
            File file = new File("/home/maksim/IdeaProjects/university_portal/core/src/hibernate.cfg.xml");
          // sessionFactory = new Configuration().configure(file).buildSessionFactory();
          // sessionFactory = new Configuration().configure().buildSessionFactory();
            sessionFactory=configureSessionFactory();
           // sessionFactory=configureSessionFactory();
        } catch (Throwable e) {
// make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    private static SessionFactory configureSessionFactory() throws HibernateException{

        Configuration configuration =new Configuration()
                .setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/UniversityPortal")
                .setProperty("hibernate.connection.username","root")
                .setProperty("hibernate.connection.password","*")
                .setProperty("hibernate.connection.pool_size","2")
                .setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.current_session_context_class","thread")
                .setProperty("hibernate.hbm2ddl.auto","update")
                .setProperty("hibernate.show_sql","true")
                .addPackage("ru")
                .addAnnotatedClass(ru.epam.university_portal.model.entity.User.class);

//ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()
//).buildServiceRegistry();
return configuration.buildSessionFactory();

    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}