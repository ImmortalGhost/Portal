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

           sessionFactory = new Configuration().configure(file).buildSessionFactory();
           // sessionFactory = new Configuration().configure().buildSessionFactory();

          /*/  Configuration configuration =new Configuration()
                    .setProperty("hibernate.connection.driver_class",
                            "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url",
                            "jdbc:mysql://localhost:3306/UniversityPortal")
                    .setProperty("hibernate.connection.username",
                            "root")
                    .setProperty("hibernate.connection.password",
                            "lenin1")
                    .setProperty("hibernate.connection.pool_size",
                            "2")
                    .setProperty("hibernate.dialect",
                            "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.hibernate.current_session_context_class",
                            "thread")
                    .setProperty("hibernate.hibernate.hbm2ddl.auto",
                            "update")
                    .setProperty("hibernate.show_sql",
                            "true")
                    .addPackage("ru")
                    .addAnnotatedClass(ru.epam.university_portal.model.entity.User.class);/*/
        //    sessionFactory=configureSessionFactory();
           // sessionFactory=configureSessionFactory();
        } catch (Throwable e) {
// make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    /*/
     <!-- Database connection settings -->
        <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost:3306/UniversityPortal</property>
        <property name="connection.username">root</property>
        <property name="connection.password">lenin1</property>
        <!-- JDBC connection pool (use the built-in) -->
        <property name="connection.pool_size">2</property>
        <!-- SQL dialect -->
          <property name="dialect">org.hibernate.dialect.MySQLDialect</property>
        <!--<property name="dialect">org.hibernate.dialect.HSQLDialect</property>-->
          <!--
              Drop and re-create the database schema on start-up, also try with
              “update” to keep the previous values
          -->
        <property name="hibernate.current_session_context_class">thread</property>
        <property name="hibernate.hbm2ddl.auto">update</property>
        <!-- Echo all executed SQL to stdout -->
        <property name="show_sql">true</property>
        <mapping package="ru"/>
        <mapping class="ru.epam.university_portal.model.entity.User" />
     */

    private static SessionFactory configureSessionFactory() throws HibernateException{
        Configuration configuration =new Configuration()
                .setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/UniversityPortal")
                .setProperty("hibernate.connection.username","root")
                .setProperty("hibernate.connection.password","lenin1")
                .setProperty("hibernate.connection.pool_size","2")
                .setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.hibernate.current_session_context_class","thread")
                .setProperty("hibernate.hibernate.hbm2ddl.auto","update")
                .setProperty("hibernate.show_sql","true")
                .addPackage("ru")
                .addAnnotatedClass(ru.epam.university_portal.model.entity.User.class);
ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()
).buildServiceRegistry();
return configuration.buildSessionFactory(serviceRegistry);

    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}