/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animals.eneities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Haozhe Ma
 */
@Entity
@Table(name = "Animal_Type")
@NamedQueries({
    @NamedQuery(name = AnimalType.GET_ALL_QUERY_NAME, query = "SELECT a FROM AnimalType a")})

@XmlRootElement
public class AnimalType implements Serializable {

    public static final String GET_ALL_QUERY_NAME = "AnimalType.getAll";

    private long animalTypeId;
    private String type;

    private Set<Animal> animals;

    public AnimalType() {
    }

    public AnimalType(long animalTypeId, String type) {
        this.animalTypeId = animalTypeId;
        this.type = type;
    }

    @Id
    @GeneratedValue
    @Column(name = "animal_type_id")
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

    @OneToMany(mappedBy = "animalType")
    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.animalTypeId ^ (this.animalTypeId >>> 32));
        hash = 67 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AnimalType other = (AnimalType) obj;
        if (this.animalTypeId != other.animalTypeId) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return type;
    }

}
