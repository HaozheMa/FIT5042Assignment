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
 * @author beelzebub
 */
@RequestScoped
@Named("searchUser")
public class SearchUser {
    
    private boolean showForm = true;

    private Users user;
    
    UsersApplication app;
    
    private int searchByInt;

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public UsersApplication getApp() {
        return app;
    }

    public void setApp(UsersApplication app) {
        this.app = app;
    }

    public int getSearchByInt() {
        return searchByInt;
    }

    public void setSearchByInt(int searchByInt) {
        this.searchByInt = searchByInt;
    }
    
    public SearchUser() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (UsersApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "usersApplication");
        
        app.updateUsersList();
                
    }
     
     public void searchUserByInt(int userId) 
    {
       try
       {
            //search this property then refresh the list in PropertyApplication bean
            app.searchUsersById(userId);
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;

    }
     
     public void searchAll() 
    {
       try
       {
            //return all properties from db via EJB
            app.searchAll();
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;
    }
    
}
