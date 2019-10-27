/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.animal.controllers;

/**
 *
 * @author beelzebub
 */
import assignment.animal.mbeans.AnimalManagedBean;
 import java.util.ArrayList;
 import java.util.List;
import javax.el.ELContext;

 import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
 import javax.faces.component.UIComponent;
 import javax.faces.context.FacesContext;
 import javax.faces.convert.Converter;
 import javax.faces.convert.ConverterException;
 import javax.faces.convert.FacesConverter;

 @FacesConverter(forClass = assignment.animals.eneities.AnimalType.class,value="animalType")
 
 public class AnimalTypeConverter implements Converter{
    @ManagedProperty(value="#{animalManagedBean}") 
    AnimalManagedBean animalManagedBean;
    
    public List<assignment.animals.eneities.AnimalType> animalTypeDB; //= propertyManagedBean.getAllContactPeople();

    public AnimalTypeConverter() 
    {
        try
        {
            //instantiate propertyManagedBean
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            animalManagedBean = (AnimalManagedBean) FacesContext.getCurrentInstance().getApplication()
            .getELResolver().getValue(elContext, null, "animalManagedBean");

            animalTypeDB = animalManagedBean.getAllAnimalType();
        }
        catch(Exception ex)
        {
            
        }
    }
                       
    //this method is for converting the submittedValue (here it means the contact person id) to the contact person object
    //the reason for using this method is, the dropdown box in the xhtml does not capture the contact person object, but the id.
    public assignment.animals.eneities.AnimalType getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                long number = Long.parseLong(submittedValue);

                for (assignment.animals.eneities.AnimalType a : animalTypeDB) {
                    if (a.getAnimalTypeId() == number) {
                        return a;
                    }
                }

            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid animal type"));
            }
        }

        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((assignment.animals.eneities.AnimalType) value).getAnimalTypeId());
        }
    }
}

