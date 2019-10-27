/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.webSevice;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Haozhe Ma
 */
@Named(value = "webServiceBean")
@SessionScoped
public class WebServiceBean implements Serializable {

    private String name;

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates a new instance of WebServiceBean
     */
    public WebServiceBean() {
    }
    
    AnimalDetailWebClient myWS;

    public void setNameWebservice() {
        myWS = new AnimalDetailWebClient();
        myWS.setPostName2(getName());
    }

    static class AnimalDetailWebClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:57068/AssignmentWeb/webresources";

        public AnimalDetailWebClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("animal");
        }

        public void setPostName2(String name) throws ClientErrorException { //create a form and add to this form information of a user
            Form form = new Form();
            form.param("name", name);
            webTarget.request(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
//webTarget.queryParam("name",name).request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(null);
        }
        
        public String getHtml() throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
        }

        public void putHtml(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.TEXT_HTML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_HTML));
        }

        public void close() {
            client.close();
        }
    }
    
    
    
}
