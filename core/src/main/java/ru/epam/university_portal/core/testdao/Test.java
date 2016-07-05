package ru.epam.university_portal.core.testdao;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.epam.university_portal.core.dao.StudentDAO;

/**
 * Created by Владос on 09.06.2016.
 */
public class Test{
    @Autowired
    public Dev d;

    public void testBegin() {
        System.out.println(d.name);
    }
}
