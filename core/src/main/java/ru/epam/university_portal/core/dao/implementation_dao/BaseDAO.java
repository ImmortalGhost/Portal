package ru.epam.university_portal.core.dao.implementation_dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Владос on 09.05.2016.
 */
public class BaseDAO {
    Session session;
   /*/ Configuration configuration;
    ServiceRegistry registry;
    SessionFactory factory;

    public BaseDAO() {
       configuration = new Configuration();
        configuration.configure();
        registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(registry);
    }
/*/
}