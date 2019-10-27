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
@Named("removeUser")
public class RemoveUser {
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
    
    
     public RemoveUser() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (UsersApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "usersApplication");
        
        app.updateUsersList();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        usersManagedBean = (UsersManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "usersManagedBean");
    }
    
      public void removeUser(int userId) {
       try
       {
            usersManagedBean.removeUser(userId);

            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been deleted succesfully"));     
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;

    }
}
