/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animal.controllers;

import assignment.animals.eneities.Area;
import assignment.animals.eneities.AnimalType;
import java.io.Serializable;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Haozhe Ma
 */
@RequestScoped
@Named(value = "animal")
public class Animal implements Serializable{
    private long animalId;
    @NotNull(message = "Name can't be empty")
    private String name;
    @NotNull(message = "Description can't be empty")
    private String description;
    
    private String eatingHabits;
    
    private Area area;
    
    private AnimalType animalType;
    
    private double longtitude;
    private double latitude;
    private String livingEnvironment;
    
    private long animalTypeId;
    private String type;
    
    private Set<Animal> animals;

    public Animal(long animalId, String name, String description, String eatingHabits, Area area, AnimalType animalType) {
        this.animalId = animalId;
        this.name = name;
        this.description = description;
        this.eatingHabits = eatingHabits;
        this.area = area;
        this.animalType = animalType;
    }

    

    public Animal() {
    }

    public long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(long animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEatingHabits() {
        return eatingHabits;
    }

    public void setEatingHabits(String eatingHabits) {
        this.eatingHabits = eatingHabits;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public long getAnimalTypeId() {
        return animalTypeId;
    }

    public void setAnimalTypeId(long animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLivingEnvironment() {
        return livingEnvironment;
    }

    public void setLivingEnvironment(String livingEnvironment) {
        this.livingEnvironment = livingEnvironment;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }
    
    
    
    
    
}
