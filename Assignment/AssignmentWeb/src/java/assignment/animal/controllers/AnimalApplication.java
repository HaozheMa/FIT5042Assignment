/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animal.controllers;

import assignment.animal.mbeans.AnimalManagedBean;
import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import assignment.animals.eneities.Animal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Haozhe Ma
 */
@Named(value = "animalApplication")
@ApplicationScoped
public class AnimalApplication {
     @ManagedProperty(value="#{animalManagedBean}") 
    AnimalManagedBean animalManagedBean;
    
    private ArrayList<Animal> animals;

    private boolean showForm = true;
    
    public boolean isShowForm() {
        return showForm;
    }
    
    public AnimalApplication() throws Exception {       
        animals = new ArrayList<>();
        
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        animalManagedBean = (AnimalManagedBean) FacesContext.getCurrentInstance().getApplication()
        .getELResolver().getValue(elContext, null, "animalManagedBean");
        
        //get animals from db 
        updateAnimalList();
    }
    
    public ArrayList<Animal> getAnimals() {
        return animals;
    }
    
    private void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }
    
    public void updateAnimalList()
    {
        if (animals != null && animals.size() > 0)
        {
            
        }
        else
        {
            animals.clear();

            if (animalManagedBean.getAllAnimals() != null)
            {
                for (assignment.animals.eneities.Animal animal : animalManagedBean.getAllAnimals())
                {
                  animals.add(animal);
                }

                setAnimals(animals);
            }
        }
    }
    
    public void searchAnimalById(long animalId)
    {
        animals.clear();
        
        animals.add(animalManagedBean.searchAnimalById(animalId));
    }
    
    public void searchAll()
    {
        animals.clear();
        
        for (assignment.animals.eneities.Animal animal : animalManagedBean.getAllAnimals())
        {
            animals.add(animal);
        }
        
        setAnimals(animals);
    }
    public void searchAnimalByAnimalTypeId(long animalTypeId)
    {
        animals.clear();
        
        for (assignment.animals.eneities.Animal animal : animalManagedBean.searchAnimalByAnimalTypeId(animalTypeId))
        {
            animals.add(animal);
        }
        
        setAnimals(animals);
    }
    
    public void searchAnimalByEatingHabitAndLivingEnvironment(String eatingHabit, String livingEnvironment) throws Exception {
        animals.clear();
        
        for (assignment.animals.eneities.Animal animal : animalManagedBean.searchAnimalByEatingHabitAndLivingEnvironment(eatingHabit, livingEnvironment))
        {
            animals.add(animal);
        }
        
        setAnimals(animals);
    }
}
