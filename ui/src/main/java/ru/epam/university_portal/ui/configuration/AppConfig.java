package ru.epam.university_portal.ui.configuration;

import org.hibernate.SessionFactory;
import  org.springframework.context.MessageSource;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.support.ResourceBundleMessageSource;
        import org.springframework.web.servlet.ViewResolver;
        import org.springframework.web.servlet.config.annotation.EnableWebMvc;
        import org.springframework.web.servlet.view.InternalResourceViewResolver;
        import org.springframework.web.servlet.view.JstlView;
import ru.epam.university_portal.core.dao.hibernate_util.HibernateUtil;
import ru.epam.university_portal.core.dao.impl.UserDAOImpl;
import ru.epam.university_portal.core.service.IUserService;
import ru.epam.university_portal.core.service.impl.UserServiceImpl;

@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = "ru.epam.university_portal.ui.controller")
@ComponentScan("ru.epam.university_portal.ui.controller" )

public class AppConfig {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

/*/@Bean
    public IUserService getUserService(){
    return new UserServiceImpl(null);
}/*/
/*/
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }/*/
}