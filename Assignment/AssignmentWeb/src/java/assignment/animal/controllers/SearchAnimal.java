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
@RequestScoped
@Named("searchAnimal")
public class SearchAnimal {
    private boolean showForm = true;

    private Animal animal;
    
    AnimalApplication app;
    
    private long searchByLong;
    private long animalTypeId;
    
    private String eatingHabits;
    private String LivingEnvrionment;

    public boolean isShowForm() {
        return showForm;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public AnimalApplication getApp() {
        return app;
    }

    public long getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(long animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    public String getEatingHabits() {
        return eatingHabits;
    }

    public void setEatingHabits(String eatingHabits) {
        this.eatingHabits = eatingHabits;
    }

    public String getLivingEnvrionment() {
        return LivingEnvrionment;
    }

    public void setLivingEnvrionment(String LivingEnvrionment) {
        this.LivingEnvrionment = LivingEnvrionment;
    }

    
    public void setApp(AnimalApplication app) {
        this.app = app;
    }

    public long getSearchByLong() {
        return searchByLong;
    }

    public void setSearchByLong(long searchByLong) {
        this.searchByLong = searchByLong;
    }

    
    
     public SearchAnimal() {
        ELContext context
                = FacesContext.getCurrentInstance().getELContext();

        app = (AnimalApplication) FacesContext.getCurrentInstance()
                        .getApplication()
                        .getELResolver()
                        .getValue(context, null, "animalApplication");
        
        app.updateAnimalList();
                
    }
     
     public void searchAnimalByLong(long animalId) 
    {
       try
       {
            //search this property then refresh the list in PropertyApplication bean
            app.searchAnimalById(animalId);
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
     
      public void searchAnimalByAnimalTypeId(long animalTypeId) 
    {
       try
       {
            //search all properties by animal type from db via EJB 
            app.searchAnimalByAnimalTypeId(animalTypeId);
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;
    }
      
      public void searchAnimalByEatingHabitAndLivingEnvironment(String habits, String environment) 
    {
       try
       { 
            app.searchAnimalByEatingHabitAndLivingEnvironment(habits, environment);
       }
       catch (Exception ex)
       {
           
       }
       showForm = true;
    }
}
