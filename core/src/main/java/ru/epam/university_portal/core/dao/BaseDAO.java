package ru.epam.university_portal.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Владос on 09.05.2016.
 */
public class BaseDAO {
    Configuration configuration;
    ServiceRegistry registry;
    SessionFactory factory;
    Session session;
    public BaseDAO() {
        configuration = new Configuration();
        configuration.configure();
        registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(registry);
    }
}
