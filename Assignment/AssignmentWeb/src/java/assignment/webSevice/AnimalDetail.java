/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.webSevice;

import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Haozhe Ma
 */
@Path("animal")
public class AnimalDetail {

    @Context
    private UriInfo context;

    @EJB
    private NameStorageBean nameStorage;

    /**
     * Get the value of nameStorage
     *
     * @return the value of nameStorage
     */
    public NameStorageBean getNameStorage() {
        return nameStorage;
    }

    /**
     * Set the value of nameStorage
     *
     * @param nameStorage new value of nameStorage
     */
    public void setNameStorage(NameStorageBean nameStorage) {
        this.nameStorage = nameStorage;
    }

    /**
     * Creates a new instance of AnimalDetail
     */
    public AnimalDetail() {
    }

    /**
     * Retrieves representation of an instance of assignment.webSevice.AnimalDetail
     * @return an instance of java.lang.String
     */
   @GET
    @Produces("text/html")
    public String getHtml() {
//TODO return proper representation object
         if (nameStorage.getName() != null){
             if (nameStorage.searchByname() != null){
                 String name = nameStorage.searchByname().getName();
                 String desc = nameStorage.searchByname().getDescription();
                 String eating = nameStorage.searchByname().getEatingHabits();
                 String livingEnvironment = nameStorage.searchByname().getArea().getLivingEnvironment();
            return "<html><body><h1>Animal name:  " + name + "</h1><p>Description: "+ desc +"</p><p>Eating habits: "+ eating +"</p><p>Living Environment : "+ livingEnvironment +"</p></body></html>";
             }
             else
                 return "<html><body><h1>no matching animals</h1></body></html>";
         }
         return "<html><body><h1>animals dont exist!</h1></body></html>";
//throw new UnsupportedOperationException();
    }


    /**
     * PUT method for updating or creating an instance of AnimalDetail
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public void setPostName(@FormParam("name") String content) {
        nameStorage.setName(content);
    }
}
