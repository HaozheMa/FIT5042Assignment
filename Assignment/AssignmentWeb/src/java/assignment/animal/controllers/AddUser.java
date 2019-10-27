/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animal.controllers;

import assignment.animal.mbeans.AnimalManagedBean;
import assignment.animal.mbeans.UsersManagedBean;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Haozhe Ma
 */
@RequestScoped
@Named("addUser")
public class AddUser {
    @ManagedProperty(value="#{usersManagedBean}") 
    UsersManagedBean usersManagedBean;
    
    private boolean showForm = true;
    
    private Users user;
    
    UsersApplication app;

    public boolean isShowForm() {
        return showForm;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public AddUser() 
    {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app  = (UsersApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "usersApplication");
        
    
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        usersManagedBean = (UsersManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "usersManagedBean");
    }
    
    public void addUser(Users localUser, String confirm) {
        if (localUser.getPassword().equals(confirm))
        {
            try
       {
            
            usersManagedBean.addUser(localUser);
           app.searchAll();
       }
       catch (Exception ex)
       {
           
       }
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("password and confirm paaword are different"));
          
        }
        showForm = true;
    }
    
    
}
