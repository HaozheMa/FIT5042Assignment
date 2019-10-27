/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.webSevice;

import assignment.animals.AnimalRepository;
import assignment.animals.eneities.Animal;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

/**
 *
 * @author haozhe Ma
 */
@Singleton
public class NameStorageBean {

    private String name;

    @EJB
    private AnimalRepository animalRepository;
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public Animal searchByname()
    {
        try{
            for (Animal a : animalRepository.getAllAnimals()){
                if (a.getName().contains(name))
                    return a;
            }
        
        }catch(Exception e)
        {
            
        }
        return null;
    }
}
