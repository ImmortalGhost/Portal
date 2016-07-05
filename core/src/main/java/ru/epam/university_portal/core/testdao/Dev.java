package ru.epam.university_portal.core.testdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.epam.university_portal.core.dao.StudentDAO;

/**
 * Created by maksim on 01.05.16.
 */
public class Dev {
    public String name;
    public Dev() {
        this.name = "!!!! ЕЕЕЕЕЕЕ";
    }
}
