/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animal.controllers;

import assignment.animal.mbeans.AnimalManagedBean;
import assignment.animal.mbeans.UsersManagedBean;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;

import assignment.animals.users.Users;
import javax.el.ELContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Haozhe Ma
 */
@Named(value = "usersApplication")
@ApplicationScoped
public class UsersApplication {
    @ManagedProperty(value="#{usersManagedBean}") 
    UsersManagedBean usersManagedBean;
    
    private ArrayList<Users> users;

    private boolean showForm = true;
    
    public boolean isShowForm() {
        return showForm;
    }
    
    public UsersApplication() throws Exception {       
        users = new ArrayList<>();
        
        //instantiate propertyManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        usersManagedBean = (UsersManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "usersManagedBean");
        
        //get users from db 
        updateUsersList();
    }

    public UsersManagedBean getUsersManagedBean() {
        return usersManagedBean;
    }

    public void setUsersManagedBean(UsersManagedBean usersManagedBean) {
        this.usersManagedBean = usersManagedBean;
    }

    public ArrayList<Users> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }
    
    
    
    public void updateUsersList()
    {
            users.clear();

            if (usersManagedBean.getAllUsers() != null)
            {
                for (assignment.animals.users.Users user : usersManagedBean.getAllUsers())
                {
                  users.add(user);
                }

                setUsers(users);
            }
        
    }
    
    public void searchUsersById(int id)
    {
        users.clear();
        
        users.add(usersManagedBean.searchUsersById(id));
    }
    
    public void searchAll()
    {
        users.clear();
        
        for (assignment.animals.users.Users user : usersManagedBean.getAllUsers())
        {
            users.add(user);
        }
        
        setUsers(users);
    }
    
}
