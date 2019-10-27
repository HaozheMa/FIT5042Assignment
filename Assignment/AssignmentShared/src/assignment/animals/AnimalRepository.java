/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animals;

import assignment.animals.eneities.Animal;
import assignment.animals.eneities.AnimalType;
import assignment.animals.users.Users;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author Haozhe Ma
 */
@Remote
public interface AnimalRepository {
    
    public void addAnimal(Animal animal) throws Exception;
    
    public Animal searchAnimalById(long id) throws Exception;
    
    public List<Animal> getAllAnimals() throws Exception;
    
    public void removeAnimal(long animalId) throws Exception;
    
    public void editAnimal(Animal animal) throws Exception;
    
    public List<AnimalType> getAllAnimalType() throws Exception;
    
    public void addAnimalType(AnimalType animalType) throws Exception;
    
    public AnimalType searchAnimalTypeById(long id) throws Exception;
    
    public void removeAnimalType(long animalTypeId) throws Exception;
    
    public void editAnimalType(AnimalType animalType) throws Exception;
    
    
    Set<Animal> searchAnimalByAnimalType(AnimalType animalType) throws Exception;
    
     public void addUser(Users user) throws Exception;
    
    public Users searchUserById(int userId) throws Exception;
    
    public List<Users> getAllUsers() throws Exception;
    
    public void removeUser(int userId) throws Exception;
    
    public void editUser(Users user) throws Exception;
    
    
    public int searchUserIdByUsername(String username) throws Exception;
    
    public List<Animal> searchAnimalByEatingHabitAndLivingEnvironment(String eatingHabit, String livingEnvironment) throws Exception;
    //public List<Animal> searchAnimalByBudget(double budget) throws Exception;
}
