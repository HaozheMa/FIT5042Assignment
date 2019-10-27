/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animal.controllers;

import assignment.animal.mbeans.AnimalManagedBean;
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
@Named("addAnimal")
public class AddAnimal {
    
    @ManagedProperty(value="#{animalManagedBean}") 
    AnimalManagedBean animalManagedBean;
    
    private boolean showForm = true;
    
    private Animal animal;
    
    AnimalApplication app;

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    public boolean isShowForm() {
        return showForm;
    }
    
    public AddAnimal() 
    {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app  = (AnimalApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "animalApplication");
        
       
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        animalManagedBean = (AnimalManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "animalManagedBean");
    }
    
    public void addAnimal(Animal localAnimal) {
       try
       {
            animalManagedBean.addAnimal(localAnimal);

            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Animal has been added succesfully"));
          
       }
       catch (Exception ex)
       {
           
       }
        showForm = true;
    }
    
}
