/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animal.controllers;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author haozhe Ma
 */
@Named(value = "titleController")
@RequestScoped
public class TitleController {
    private String pageTitle;
    
    public TitleController() {
        // Set the page title 
        pageTitle = "NGO - Animal";
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }
}
