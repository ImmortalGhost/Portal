package ru.epam.university_portal.core.test_dao;
import ru.epam.university_portal.core.dao.*;

import ru.epam.university_portal.model.entity.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by maksim on 01.05.16.
 */
public class App {

    public static void main(String [] args) throws  Exception{
        GroupAndTeacherDAO dao = new GroupAndTeacherDAO();
        dao.createRelation("Admin1", "Admin2", "Group");
        GroupAndTeacher groupAndTeacher = dao.getRelation("Admin1", "Admin2", "Group");
        System.out.println(groupAndTeacher.getIdTeacher() + " " + groupAndTeacher.getIdGroup());
       // dao.removeRelation("Admin1", "Admin2", "Group");
        List<GroupAndTeacher> l = dao.getAll();
        for (GroupAndTeacher g : l) {
            System.out.println(g.getIdGroup());
        }
//        TeacherDAO dao = new TeacherDAO();
//        dao.remove("log", "pas");
//        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//        java.util.Date date = (java.util.Date) formatter.parse("20000419");
//        dao.createOrUpdate("qwe", "rty", "1", "Admin1", "Admin2", date);
//        Teacher t1 = dao.get("Admin1", "Admin2");
//        Teacher t2 = dao.getByLoginAndPassword("qwe", "rty");
//        System.out.println(t1.getIdUser() + " " + t2.getIdUser());
//
//        MessagesFromNewsDAO dao = new MessagesFromNewsDAO();
//        dao.create("Вася", "Пупкин", "One", "Получилось, бляяяяядь", "Здарова !");
       // dao.removeAllByGroupAndTitle("Получилось, бляяяяядь", "One");
//        dao.remove("Вася", "Пупкин", "Test1", "One", "Всем привет !");
//        StudentDAO dao1 = new StudentDAO();
//        Student s1 = dao1.get("Вася", "Пупкин");
//        Student s2 = dao1.getByLoginAndPassword("login1", "password");
//        System.out.println(s1.getIdUser() + " " + s2.getIdUser() + "    " + s1.getId() + " " + s2.getId());
        //dao1.addNewMessage("Не Петя", "Не Васечкин", "Вася", "Пупкин", "Нормальное сообщение");

//        for (MessagesFromNews m : l) {
//            System.out.println(m.getMessage());
//        }
//        NewsDAO dao = new NewsDAO();
//        dao.create("Получилось, бляяяяядь", "One");
//        dao.create("Получилось, бляяяяядь", "Two");
//        dao.create("Получилось, бляяяяядь", "Three");
//        dao.create("Получилось, бляяяяядь", "Four");
//        dao.create("Test1", "One");
//        dao.removeNewsByGroupAndTitle("Получилось, бляяяяядь", "Three");
//        List<News> l = dao.getAllByGroup("Получилось, бляяяяядь");
//        for (News n : l) {
//            System.out.println(n.getName());
//        }
//        Messages1To1DAO dao = new Messages1To1DAO();
//        dao.create("Вася", "Пупкин", "Не Петя", "Не Васечкин", "Го в доту, я создал !");
//        dao.create("Вася", "Пупкин", "Не Петя", "Не Васечкин", "Где же ты ?");
//        dao.create("Вася", "Пупкин", "Не Петя", "Не Васечкин", "хзцузцйщуй");
//        dao.create("Вася", "Пупкин", "Не Петя", "Не Васечкин", "Бла бла бла бла");
//        dao.remove("Вася", "Пупкин", "Не Петя", "Не Васечкин", "Где же ты ?");
//        List<Messages1To1> l = dao.getAll("Вася", "Пупкин", "Не Петя", "Не Васечкин");
//        for (Messages1To1 m : l) {
//
//            System.out.println(m.getMessage());
//        }
//        System.out.println("after");
//        RoleDAO dao = new RoleDAO();
//        dao.create("Teacher");
//        dao.create("Student");
//        dao.create("Manager");
//        dao.create("Moderator");
//        List<Role> l = dao.getAll();
//        for (Role g : l) {
//            System.out.println(g.getName());
//        }
//        System.out.println("---------------------------------");
//        dao.remove("Moderator");
//        l = dao.getAll();
//        for (Role g : l) {
//            System.out.println(g.getName());
//        }
//        Role p = dao.get("Manager");
//        System.out.println(p.getId() + "    " + p.getName());

//        StudentDAO dao = new StudentDAO();
//        try {
//            DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//            java.util.Date date = (java.util.Date) formatter.parse("20000419");
//            dao.createOrUpdate("login", "password2", "Group", "Не Петя", "Не Васечкин", date);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e);
//        }
    }
}
