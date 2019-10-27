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
 * @author beelzebub
 */
@RequestScoped
@Named("removeAnimal")
public class RemoveAnimal {
    @ManagedProperty(value="#{animalManagedBean}") 
    AnimalManagedBean animalManagedBean;
    
    private boolean showForm = true;
    private Animal animal;
    AnimalApplication app;

    public boolean isShowForm() {
        return showForm;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    public RemoveAnimal() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (AnimalApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "animalApplication");
        
        app.updateAnimalList();
        
        //instantiate propertyManagedBean
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        animalManagedBean = (AnimalManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "animalManagedBean");
    }
    
    public void removeAnimal(long animalId) {
       try
       {
            //remove this property from db via EJB
            animalManagedBean.removeAnimal(animalId);

            //refresh the list in PropertyApplication bean
            app.searchAll();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Animal has been deleted succesfully"));     
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;

    }
}
