/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animals.users;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Haozhe Ma
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Users.GET_ALL_QUERY_NAME, query = "SELECT u FROM Users u order by u.userId desc")})

public class Users implements Serializable{
    
    public static final String GET_ALL_QUERY_NAME = "Users.getAll";
    
    private int userId;
    private String username;
    private String password;
    private String usertype;
    private String email;
    
    @Size(min=10, max=10, message = "phone should be 10 numbers")
    private String phone;
    private Date registerdate;

    public Users() {
    }

    public Users(int userId, String username, String password, String usertype, String email, String phone, Date registerdate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
        this.email = email;
        this.phone = phone;
        this.registerdate = registerdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
   @Temporal(TemporalType.DATE)
    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    
    
}
