/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animal.controllers;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Haozhe Ma
 */
@Named(value = "usersController")
@RequestScoped
public class UsersController {
    
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    private assignment.animals.users.Users user;
    
    public UsersController() {
        
        
            userId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("usersID"));
        
        user = getUser();
    }
    
    public assignment.animals.users.Users getUser() {
        if (user == null) {
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            UsersApplication app
                    = (UsersApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "usersApplication");
           return app.getUsers().get(--userId); 
        }
        return user;
    }
    
   
    
}
