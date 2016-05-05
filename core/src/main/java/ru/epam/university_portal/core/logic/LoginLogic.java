package ru.epam.university_portal.core.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.epam.university_portal.core.service.IUserService;

/**
 * Created by maksim on 05.05.16.
 */
@Component
public class LoginLogic {

private IUserService userService;
    @Autowired
   public  void setUserService(IUserService _userService){
        userService=_userService;
    }
    public boolean checkLogin(String login,String password){
    return userService.getUser(login,password)!=null?true:false;
    }
}
