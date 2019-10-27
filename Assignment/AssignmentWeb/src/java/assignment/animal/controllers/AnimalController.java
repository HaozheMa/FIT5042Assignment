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
@Named(value = "animalController")
@RequestScoped
public class AnimalController {
    private int animalId;

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }
    
    private assignment.animals.eneities.Animal animal;
    
    public AnimalController() {
        // Assign property identifier via GET param 
        //this propertyID is the index, don't confuse with the Property Id
        animalId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("animalID"));
        // Assign property based on the id provided 
        animal = getAnimal();
    }
    
    public assignment.animals.eneities.Animal getAnimal() {
        if (animal == null) {
            // Get application context bean AnimalApplication 
            ELContext context
                    = FacesContext.getCurrentInstance().getELContext();
            AnimalApplication app
                    = (AnimalApplication) FacesContext.getCurrentInstance()
                            .getApplication()
                            .getELResolver()
                            .getValue(context, null, "animalApplication");
            // -1 to aanimalId since we +1 in JSF (to always have positive property id!) 
            return app.getAnimals().get(--animalId); //this animalId is the index, don't confuse with the Property Id
        }
        return animal;
    }
}
