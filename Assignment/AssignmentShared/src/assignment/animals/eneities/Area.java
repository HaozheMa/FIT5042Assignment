
package assignment.animals.eneities;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
/**
 *
 * @author Haozhe Ma
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class Area implements Serializable{
    
    private double longtitude;
    private double latitude;
    private String livingEnvironment;
    
    public Area()
    {
    }

    public Area(double longtitude, double latitude, String livingEnvironment) {
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.livingEnvironment = livingEnvironment;
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
    @Column(name = "living_environment")
    public String getLivingEnvironment() {
        return livingEnvironment;
    }

    public void setLivingEnvironment(String livingEnvironment) {
        this.livingEnvironment = livingEnvironment;
    }
    
    
}
