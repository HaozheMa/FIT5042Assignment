/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animal.mbeans;

import assignment.animals.AnimalRepository;
import assignment.animals.eneities.Animal;
import assignment.animals.eneities.AnimalType;
import assignment.animals.eneities.Area;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Haozhe Ma
 */
@ManagedBean(name = "animalManagedBean")
@SessionScoped
public class AnimalManagedBean implements Serializable {

    @EJB
    AnimalRepository animalRepository;

    public AnimalManagedBean() {
    }

    public List<Animal> getAllAnimals() {
        try {
            List<Animal> animals = animalRepository.getAllAnimals();
            return animals;
        } catch (Exception ex) {
            Logger.getLogger(AnimalManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addAnimal(Animal animal) {
        try {
            animalRepository.addAnimal(animal);
        } catch (Exception ex) {
            Logger.getLogger(AnimalManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Search a animal by Id
     */
    public Animal searchAnimalById(long id) {
        try {
            return animalRepository.searchAnimalById(id);
        } catch (Exception ex) {
            Logger.getLogger(AnimalManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void removeAnimal(long animalId) {
        try {
            animalRepository.removeAnimal(animalId);
        } catch (Exception ex) {
            Logger.getLogger(AnimalManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editAnimal(Animal animal) {
        try {
            String s = animal.getArea().getLivingEnvironment();
            Area area = animal.getArea();
            area.setLivingEnvironment(s);
            animal.setArea(area);

            animalRepository.editAnimal(animal);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Property has been updated succesfully"));
        } catch (Exception ex) {
            Logger.getLogger(AnimalManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addAnimal(assignment.animal.controllers.Animal localAnimal) {
        Animal animal = convertAnimalToEntity(localAnimal);

        try {
            animalRepository.addAnimal(animal);
        } catch (Exception ex) {
            Logger.getLogger(AnimalManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Set<Animal> searchAnimalByAnimalTypeId(long animalTypeId) {
        try {
            for (AnimalType animalType : animalRepository.getAllAnimalType()) {
                if (animalType.getAnimalTypeId() == animalTypeId) {
                    return animalRepository.searchAnimalByAnimalType(animalType);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(AnimalManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<AnimalType> getAllAnimalType() throws Exception {
        try {
            return animalRepository.getAllAnimalType();
        } catch (Exception ex) {
            Logger.getLogger(AnimalManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private Animal convertAnimalToEntity(assignment.animal.controllers.Animal localAnimal) {
        try {
            Animal animal = new Animal(); //entity
            double longtitude = localAnimal.getLongtitude();
            double latitude = localAnimal.getLatitude();
            String livingEnvironment = localAnimal.getLivingEnvironment();

            Area area = new Area(longtitude, latitude, livingEnvironment);
            animal.setArea(area);
            animal.setAnimalId(localAnimal.getAnimalId());
            animal.setName(localAnimal.getName());
            animal.setDescription(localAnimal.getDescription());
            animal.setEatingHabits(localAnimal.getEatingHabits());
            animal.setAnimalType(localAnimal.getAnimalType());
            return animal;
        } catch (Exception ex) {

        }
        return null;
    }

    public List<Animal> searchAnimalByEatingHabitAndLivingEnvironment(String eatingHabit, String livingEnvironment) throws Exception {
        try {
            return animalRepository.searchAnimalByEatingHabitAndLivingEnvironment(eatingHabit, livingEnvironment);
        } catch (Exception ex) {
            Logger.getLogger(AnimalManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
