/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animal;

import assignment.animals.AnimalRepository;
import assignment.animals.eneities.Animal;
import assignment.animals.eneities.AnimalType;
import assignment.animals.users.Users;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static java.util.stream.DoubleStream.builder;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Haozhe Ma
 */
@Stateless
public class JPAAnimalRepositoryImpl implements AnimalRepository {

    @PersistenceContext(unitName = "AssignmentEJBPU")
    private EntityManager entityManager;

    @Override
    public void addAnimal(Animal animal) throws Exception {
        List<Animal> animals = entityManager.createNamedQuery(Animal.GET_ALL_QUERY_NAME).getResultList();
        if (animals.size() != 0) {
            animal.setAnimalId(animals.get(0).getAnimalId() + 1);
        } else {
            animal.setAnimalId(1);
        }
        entityManager.persist(animal);
    }

    @Override
    public Animal searchAnimalById(long id) throws Exception {
        Animal animal = entityManager.find(Animal.class, id);
        //animal.getTags();
        return animal;
    }

    @Override
    public List<Animal> getAllAnimals() throws Exception {
        return entityManager.createNamedQuery(Animal.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public void removeAnimal(long animalId) throws Exception {
        Animal animal = this.searchAnimalById(animalId);

        if (animal != null) {
            entityManager.remove(animal);
        }
    }

    @Override
    public void editAnimal(Animal animal) throws Exception {
        try {
            entityManager.merge(animal);
        } catch (Exception ex) {

        }
    }

    @Override
    public List<AnimalType> getAllAnimalType() throws Exception {
        return entityManager.createNamedQuery(AnimalType.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public Set<Animal> searchAnimalByAnimalType(AnimalType animalType) throws Exception {
        animalType = entityManager.find(AnimalType.class, animalType.getAnimalTypeId());
        animalType.getAnimals().size();
        entityManager.refresh(animalType);

        return animalType.getAnimals();
    }

    @Override
    public void addAnimalType(AnimalType animalType) throws Exception {
        List<AnimalType> animalTypes = entityManager.createNamedQuery(AnimalType.GET_ALL_QUERY_NAME).getResultList();
        if (animalTypes.size() != 0) {
            animalType.setAnimalTypeId(animalTypes.get(0).getAnimalTypeId() + 1);
        } else {
            animalType.setAnimalTypeId(1);
        }
        entityManager.persist(animalType);
    }

    @Override
    public AnimalType searchAnimalTypeById(long id) throws Exception {
        AnimalType animalType = entityManager.find(AnimalType.class, id);
        //animal.getTags();
        return animalType;
    }

    @Override
    public void removeAnimalType(long animalTypeId) throws Exception {
        AnimalType animalType = this.searchAnimalTypeById(animalTypeId);
        if (animalType != null) {
            if (searchAnimalByAnimalType(animalType) == null) {
                entityManager.remove(animalType);
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("remove animal type failed, this type has been used"));

            }
        }
    }

    @Override
    public void editAnimalType(AnimalType animalType) throws Exception {
        try {
            entityManager.merge(animalType);
        } catch (Exception ex) {

        }
    }

    @Override
    public void addUser(Users user) throws Exception {
        List<Users> users = getAllUsers();
        if (users.size() != 0) {
            user.setUserId(users.get(0).getUserId() + 1);
        } else {
            user.setUserId(1);
        }
        boolean check = true;
        for (Users u : users) {
            if (user.getUsername().equals(u.getUsername())) {
               check = false;
            } 
        }
        if (check)
        {
            entityManager.persist(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been added succesfully!"));
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User name cannot be repeat!"));
        
        }
    }

    @Override
    public Users searchUserById(int userId) throws Exception {
        Users user = entityManager.find(Users.class, userId);
        //animal.getTags();
        return user;
    }

    @Override
    public List<Users> getAllUsers() throws Exception {
        return entityManager.createNamedQuery(Users.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public void removeUser(int userId) throws Exception {
        Users user = this.searchUserById(userId);

        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public void editUser(Users user) throws Exception {
        try {

            entityManager.merge(user);
        } catch (Exception ex) {

        }
    }

    @Override
    public int searchUserIdByUsername(String username) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Users> q = cb.createQuery(Users.class);
        Root<Users> c = q.from(Users.class);
        q.select(c);
        Predicate predicate = cb.equal(c.get("username").as(String.class), username);
        q.where(predicate);
        TypedQuery tQuery = entityManager.createQuery(q);
        List<Users> users = tQuery.getResultList();
        int id = 0;
        for (Users u : users) {
            if (u.getUsername().equals(username)) {
                id = u.getUserId();
            }
        }
        return id;
    }

    @Override
    public List<Animal> searchAnimalByEatingHabitAndLivingEnvironment(String eatingHabit, String livingEnvironment) throws Exception {

        List<Animal> animalList = new ArrayList<Animal>();
        List<Animal> animals = getAllAnimals();
        for (Animal a : animals) {
            if (a.getEatingHabits().equals(eatingHabit) && a.getArea().getLivingEnvironment().equals(livingEnvironment)) {
                animalList.add(a);
            }
        }
        return animalList;
    }
    
     

}
