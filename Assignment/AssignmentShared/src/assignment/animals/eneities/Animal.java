
package assignment.animals.eneities;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Haozhe Ma
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Animal.GET_ALL_QUERY_NAME, query = "SELECT a FROM Animal a order by a.animalId desc")})

@XmlRootElement
public class Animal implements Serializable{

    public static final String GET_ALL_QUERY_NAME = "Animal.getAll";
    
    private long animalId;
    private String name;
    private String description;
    private String eatingHabits;
    //private String animalType;
    
    private Area area;
    private AnimalType animalType;

    public Animal()
    {
        
    }

    public Animal(long animalId, String name, String description, String eatingHabits, Area area, AnimalType animalType) {
        this.animalId = animalId;
        this.name = name;
        this.description = description;
        this.eatingHabits = eatingHabits;
        this.area = area;
        this.animalType = animalType;
    }
    
    

    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "animal_id")
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

    @Column(name = "eating_habits")
    public String getEatingHabits() {
        return eatingHabits;
    }

    public void setEatingHabits(String eatingHabits) {
        this.eatingHabits = eatingHabits;
    }
    
    @ManyToOne
    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    

    @Embedded
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Animal{" + "animalId=" + animalId + ", name=" + name + ", description=" + description + ", eatingHabits=" + eatingHabits + ", animalType=" + animalType + ", area=" + area + '}';
    }
    
    
}
