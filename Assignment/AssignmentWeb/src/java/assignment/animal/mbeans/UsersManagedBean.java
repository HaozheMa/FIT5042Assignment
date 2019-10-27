/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animal.mbeans;

import assignment.animals.AnimalRepository;
import assignment.animals.eneities.Animal;
import assignment.animals.eneities.Area;
import assignment.animals.users.Users;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;

/**
 *
 * @author Haozhe Ma
 */
@ManagedBean(name = "usersManagedBean")
@SessionScoped
public class UsersManagedBean implements Serializable {

    @EJB
    AnimalRepository animalRepository;

    public UsersManagedBean() {
    }

    public UsersManagedBean(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Users> getAllUsers() {
        try {
            List<Users> users = animalRepository.getAllUsers();
            return users;
        } catch (Exception ex) {
            Logger.getLogger(UsersManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addUsers(Users users) {
        try {
            animalRepository.addUser(users);
        } catch (Exception ex) {
            Logger.getLogger(UsersManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Users searchUsersById(int id) {
        try {
            return animalRepository.searchUserById(id);
        } catch (Exception ex) {
            Logger.getLogger(UsersManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void removeUser(int userId) {
        try {
            animalRepository.removeUser(userId);
        } catch (Exception ex) {
            Logger.getLogger(UsersManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editUser(Users user) {
        try {
            String email = user.getEmail();
            String phone = user.getPhone();
            if (isEmail(email) && isPhone(phone)) {
                animalRepository.editUser(user);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been updated succesfully"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User update failed"));

            }

        } catch (Exception ex) {
            Logger.getLogger(UsersManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isEmail(String email) {
        if (email.trim().length() == 0) {
            return false;
        }
        String regEx = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx);
        m = p.matcher(email);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPhone(String phone) {
        try {
            if (phone.length() == 10) {
                int phoneNum = Integer.parseInt(phone);

            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String toHexString(String input) {
        StringBuilder hexString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // digest() method called  
            // to calculate message digest of an input  
            // and return array of byte 
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            // Convert byte array into signum representation  
            BigInteger number = new BigInteger(1, hash);
            hexString = new StringBuilder(number.toString(16));
        } catch (Exception e) {

        }
        // Pad with leading zeros 
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public void addUser(assignment.animal.controllers.Users localUsers) {
        //convert this newProperty which is the local property to entity property
        Users user = convertUserToEntity(localUsers);
        String rawPassword = localUsers.getPassword();
        user.setPassword(toHexString(rawPassword));
        try {
            String email = localUsers.getEmail();
            String phone = localUsers.getPhone();
            if (isEmail(email)) {
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();

                Set<ConstraintViolation<Users>> constraintViolations = validator.validate(user);

                if (constraintViolations.size() > 0) {
                    System.out.println("Constraint Violations occurred..");
                    for (ConstraintViolation<Users> contraints : constraintViolations) {
                        System.out.println(contraints.getRootBeanClass().getSimpleName()
                                + "." + contraints.getPropertyPath() + " " + contraints.getMessage());
                         FacesContext.getCurrentInstance().addMessage("myForm:phone", new FacesMessage("Error: Your phone is NOT correct."));

                    }
                }else{
                animalRepository.addUser(user);
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User update failed: email is not correct."));
            }
        } catch (ValidationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User update failed: phone number is not correct."));

        } catch (Exception ex) {
            Logger.getLogger(AnimalManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Users convertUserToEntity(assignment.animal.controllers.Users localUsers) {
        try {
            Users user = new Users();
            String email = localUsers.getEmail();
            String phone = localUsers.getPhone();
            String password = localUsers.getPassword();
            String username = localUsers.getUsername();
            String userType = localUsers.getUsertype();
            Date register = new Date();
            int id = localUsers.getUserId();
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            user.setUsername(username);
            user.setUsertype(userType);
            user.setRegisterdate(register);
            return user;
        } catch (Exception ex) {

        }
        return null;
    }

    public void changePassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            try {
                String username = FacesContext.getCurrentInstance()
                        .getExternalContext().getRemoteUser();
                int id = animalRepository.searchUserIdByUsername(username);
                Users user = animalRepository.searchUserById(id);
                user.setUserId(id);
                user.setPassword(toHexString(password));

                animalRepository.editUser(user);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been updated succesfully"));

            } catch (Exception ex) {
                Logger.getLogger(UsersManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User password updated failed"));

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("password and confirm paaword are different"));

        }
    }

    public void updateInformation(String email, String phone) {
        try {
            String username = FacesContext.getCurrentInstance()
                    .getExternalContext().getRemoteUser();
            int id = animalRepository.searchUserIdByUsername(username);
            Users user = animalRepository.searchUserById(id);

            if (isEmail(email) && isPhone(phone)) {
                user.setEmail(email);
                user.setPhone(phone);
                animalRepository.editUser(user);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User has been updated succesfully"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User update failed"));

            }

        } catch (Exception ex) {
            Logger.getLogger(UsersManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() throws IOException {
        FacesContext.getCurrentInstance()
                .getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("http://localhost:57068/AssignmentWeb/");
    }

}
