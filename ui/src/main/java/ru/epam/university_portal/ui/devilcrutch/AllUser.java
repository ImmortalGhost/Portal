package ru.epam.university_portal.ui.devilcrutch;

/**
 * Created by Владос on 13.06.2016.
 */
public class AllUser {
    private String name;
    private String lastName;
    private String login;
    private String password;
    private String selectedGroup;
    private String selectedPost;
    private String selectedRole;

    public String getSelectedPost() {
        return selectedPost;
    }

    public void setSelectedPost(String selectedPost) {
        this.selectedPost = selectedPost;
    }

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }

    private String age;

    public String getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(String selectedGroup) {
        this.selectedGroup = selectedGroup;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
