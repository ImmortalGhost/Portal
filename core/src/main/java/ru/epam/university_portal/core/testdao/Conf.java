package ru.epam.university_portal.core.testdao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Владос on 18.06.2016.
 */
@Configuration
public class Conf {
    @Bean
    Dev dev(){
        Dev d = new Dev();
        d.name = "!!!! TEST";
        return d;
    }
    @Bean
    Test test() {
        return new Test();
    }
}
