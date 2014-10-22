package com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseController;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.PaginationHelper;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *  ArtefactdistributionController Class. This class inherits from the base class BaseController. The primary purpose
 *  is to act as the Controller part of the MVC pattern for the Artefactdistribution Entity MVC. 
 *  
 *  The class is based on the NetBeans Controller template and modified extensively for this project 
 * 
 *  @author Lee Baker
 *  @version 1.0
 */

//START IDE GENERATED CODE
@Named("artefactdistributionController")
@SessionScoped
public class ArtefactdistributionController extends BaseController implements Serializable {

    private Artefactdistribution current;;// Current Artefactdistribution entity that has been selected/viewed/edited/created  
    private DataModel items = null;// This is affectively the Model part of the MVC pattern
    @EJB
    private com.mycompany.atomicinformationconfigurationmanager.entities.artefactdistribution.ArtefactdistributionSaveRetrieve ejbSaveRetrieve;
    private PaginationHelper pagination;
    private int selectedItemIndex;// Selected entity Index in the DataModel items
    //END IDE GENERATED CODE
    
    //START LEE BAKER GENERATED CODE
    private boolean  itemSelected = false; //Set to true when an item is selected in the List DataTable
    private Boolean selectedFromArtefact = false;// This is set to true if created from the Artefact View to navigate back to the correct Artefact entity
    /*  @Lee Baker
    *   10/08/14
    *   Added to get current selected project
    */
    @Inject
    private ArtefactController artefactController;

    public ArtefactdistributionController() {
    }

    public Artefactdistribution getSelected() {
        if (current == null) {
            current = new Artefactdistribution();
            selectedItemIndex = -1;
        }
        return current;
    }
       
    /**
     * setSelected method. This method is used to set the 'current' property to reference 
     * Artefact entity that has been selected in the DataModel
     * 
     * @param event 
     */
    public void setSelected(ValueChangeEvent event){
        current = (Artefactdistribution) getItems().getRowData();
        itemSelected = true;
    }
    
    /**
     * prepareSelected method. The purpose of this method is to identify if a Entity has 
     * been selected in the DataModel and then return String that navigates to the correct 
     * JSF page
     * @param jsfPage to be returned if the method executes correctly 
     * @return jsfPage to be returned
     */
    private String prepareSelected(String jsfPage){
        try {
            if (itemSelected == false)
                {
                    current = (Artefactdistribution) getItems().getRowData();
                }
                itemSelected = false;
                selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
                return jsfPage;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("NoRecordSelectedError"));
            return null;
        }
    }

    private ArtefactdistributionSaveRetrieve getSaveRetrieve() {
        return ejbSaveRetrieve;
    }
    //END LEE BAKER GENERATED CODE
    
    //START OF IDE MODIFIED CODE BY LEE BAKER 
    /**
     * getPagination method. The purpose of this method is build a pagination of entities
     * that is the correct size to be displayed on a page. If the pagination doesn't exist 
     * then one is created. This methods overrides contains to methods of the PaginationHelper
     * class to get the number of items in the Model (MVC) and create the DataModel that
     * matches the correct page size (10)
     * 
     * @return the pagination of the entities to be displayed
     */
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                
                /* 
                *   10/08/14 @Lee Baker
                *   IDE Code modified to use countEntityActiveIsCurrentVersion() instead of count()
                */     
                @Override
                public int getItemsCount() {
                    int localCount;
                    if (artefactController.getCurrent() !=null){
                        localCount = getSaveRetrieve().countEntityActiveAndArtefactIDIAndsCurrentVersion(artefactController.getCurrent(), true, true);
                    }
                    else {
                        localCount = getSaveRetrieve().countEntityActiveIsCurrentVersion(true, true);
                    }
                    return localCount;
                }

                /* 
                *   10/08/14 @Lee Baker
                *   IDE Code modified to with if else statement to return artefacts for selected project 
                */
                @Override
                public DataModel createPageDataModel() {
                    if (artefactController.getCurrent() !=null){
                        return  new ListDataModel(getSaveRetrieve().findRangeEntityActiveAndArtefactIDAndIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true, artefactController.getCurrent(), true));
                    }
                    else {
                        return new ListDataModel(getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()},true, true));
                    }
                }
            };
        }
        return pagination;
    }
    
    /**
     * prepareList method. The purpose of this method is to prepare the model (MVC) so 
     * the entities can be accessed as a list. The method also resets the selected 
     * entity in the model to null
     * 
     * @return a string that is used to navigate to the JSF List page
     */
    public String prepareList() {
        recreateModel();
        itemSelected = false;
        return "/Faces/artefactdistribution/List";
    }
    
    /**
     * prepareView method. The purpose of this method is to extract the selected
     * entity from the model (MVC) so it can be viewed
     * 
     * @return a string that is used to navigate to the JSF View page
     */
    public String prepareView() {
        return prepareSelected("/Faces/artefactdistribution/View");
    }
    
    /**
     * prepareCreate method. The purpose of this method is to prepare the Model to
     * have a new entity created and inserted. Create a new entity instance
     * 
     * @return a string that is used to navigate to the JSF Create page
     */
    public String prepareCreate() {
        current = new Artefactdistribution();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        return "/Faces/artefactdistribution/Create";
    }
    //START OF IDE MODIFIED CODE BY LEE BAKER 
    
    //START LEE BAKER GENERATED CODE
    /**
     * prepareCreate method. The purpose of this method is to prepare the Model to
     * have a new entity created and inserted. Create a new entity instance. This method
     * only differs from the prepareCreate() method because it navigates back to the
     * CreateFromArtefact JSF page
     * 
     * @return a string that is used to navigate to the JSF Create page
     */
    public String prepareCreateFromArtefact() {
        current = new Artefactdistribution();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        selectedFromArtefact = true;
        return "/Faces/artefactdistribution/CreateFromArtefact";
    }
    //END LEE BAKER GENERATED CODE
    
    //START OF IDE MODIFIED CODE BY LEE BAKER
    /**
     * create method. The purpose of this methods is to persist the newly created entity
     * to the database. The method also sets the Project attribute of the new entity to
     * the currently selected project and the EntityActive attribute to true so that it 
     * can be retrieved from the database in future queries. If an error occurs during the 
     * process an error message is displayed and the entity isn't stored 
     * 
     * @return returns a String, if the process succeeds then the create page is displayed.
     * If the process fails then the errors message is displayed and the page remains the 
     * same
     * 
     */
    public String create(String returnMethod) {
        try {
            /*  
            *   10/08/14 @Lee Baker
            *   If a artefact has been selected then create new ArtefactDistribution with a reference to the selected Artefact
            *   and set entityActive when created
            */ 
            if(selectedFromArtefact==true){
                current.setArtefactID(artefactController.getCurrent());
                selectedFromArtefact=false;
            }
            setEntityActive(current);
            getSaveRetrieve().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactdistributionCreated"));
            
            /*
            *   13/08/14 @Lee Baker
            *   Code modified to return from multiple calling xhtml pages
            */
            if ("prepareCreate".equals(returnMethod)){
                return prepareCreate();
                
            }
            if ("prepareCreateFromArtefact".equals(returnMethod)){
                return prepareCreateFromArtefact();
            }
            else {
                return prepareCreate();
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            selectedFromArtefact=false;
            return null;
        }
    }
    
    /**
     * prepareEdit method. The purpose of this method is to get the entity from 
     * the model (MVC) so it can be edited
     * 
     * @return returns a String that is used to navigates to the JSF Edit page
     */
    public String prepareEdit() {
        return prepareSelected("Edit");
    }

    /**
     * update method. The purpose of this methods is to persist the UPDATED entity
     * to the database. If an error occurs during the process an error message is 
     * displayed and the entity isn't stored
     * 
     * @return returns a String, if the process succeeds then the View page is displayed.
     * If the process fails then the errors message is displayed and the page remains the 
     * same
     */
    public String update() {
        try {
            getSaveRetrieve().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactdistributionUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    //END OF IDE MODIFIED CODE BY LEE BAKER
    
    /*  
    *   10/08/14 @Lee Baker
    *   Code added to delete entity instead of destroying it
    */
    //START LEE BAKER GENERATED CODE
    /**
     * delete method. he purpose of this method is to delete an entity when it is selected from
     * from the JSF List page. The model is recreated and the List page is updated.
     * 
     * @return returns a String that is used to navigates to the JSF List page
     */
    public String delete() {
        String result;
        result = prepareSelected("List");
        if ("List".equals(result)){
            performDelete();
            recreatePagination();
            recreateModel();
        }
        return result;
    }
    
    /**
     * deleteAndView method. The purpose of this method is to delete an entity when it is being viewed
     * from the JSF View. When the entity is deleted the next entity in the 
     * model is displayed in the View page. If not entities are in the Model then an empty
     * List page is displayed
     * 
     * @return the String with the correct JSF page to be displayed.
     */
    public String deleteAndView() {
        performDelete();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }
    
    /**
     * performDelete method. This method 'deletes' by setting the entityActive attribute to false
     * and then recreated the DataModel so the remove the entity from the in memory model. When the 
     * delete is completed a success message is displayed. If the delete fails then an error message
     * is displayed.
     * 
     */
    private void performDelete() {
        setEntityInActive(current);
        try {
            getSaveRetrieve().entityInactive(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactdistributionDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    //END LEE BAKER GENERATED CODE
    
    //START OF IDE MODIFIED CODE BY LEE BAKER
    /**
     * updateCurrentItem method. The purpose of this method is to update the current selected entity in the model.
     * For example if an entity is deleted it can no longer be the current entity and therefore the next entity in the
     * model is selected.
     * 
     */
    private void updateCurrentItem() {
        int count = getSaveRetrieve().count();
        if (artefactController.getCurrent() !=null){
            count = getSaveRetrieve().countEntityActiveAndArtefactIDIAndsCurrentVersion(artefactController.getCurrent(), true, true);
        }
        else{
            count = getSaveRetrieve().countEntityActiveIsCurrentVersion(true, true);
        }
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            if(artefactController.getCurrent()!=null){
                current = getSaveRetrieve().findRangeEntityActiveAndArtefactIDAndIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1}, true, artefactController.getCurrent(), true).get(0);
            }
            current = getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true, true).get(0);
        }
    }
    //END OF IDE MODIFIED CODE BY LEE BAKER
    
    //START IDE GENERATED CODE
    /**
     * getItems method. The purpose of this methods is to returned a data model
     * of all the entities that are valid. If no data model exists then one is created
     * 
     * @return the data model of items
     */
    public DataModel getItems() {
        if (items == null){
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    
    /**
     * recreateModel method. 
     */
    public void recreateModel() {
        items = null;
    }
    
    /**
     * recreatePagination method. 
     */
    private void recreatePagination() {
        pagination = null;
    }
    
    /**
     * next method. The purpose of this method is to create the data model
     * that represent the next page in the Model of entities (MVC) to be displayed
     * to the user
     * 
     * @return the string to display the JSF List page
     */
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }
    
    /**
     * previous method. The purpose of this method is to create the data model
     * that represent the previous page in the Model of entities (MVC) to be displayed
     * to the user
     * 
     * @return the string to display the JSF List page
     */
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }
    
    /**
     * getItemsAvailableSelectMany method. the purpose of this method is to return a list of all
     * the current entities rather and a DataModel. The list can be used to populate a Dropdown control.
     * From the list returned the many entities can be selected
     * 
     * @return the list of entities that are the current version and active
     */
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbSaveRetrieve.findAllEntityActiveIsCurrentVersion(true, true), false);
    }
    
    /**
     * getItemsAvailableSelectOne method. the purpose of this method is to return a list of all
     * the current entities rather and a DataModel. The list can be used to populate a Dropdown control
     * From the list returned the only one entity can be selected
     * 
     * @return the list of entities that are the current version and active
     */
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbSaveRetrieve.findAllEntityActiveIsCurrentVersion(true, true), true);
    }
    
    /**
     * getArtefact method. The purpose of this method is to find a specific entity  by its
     * id attribute.
     * 
     * @param id
     * @return the entity  to be returned 
     */
    public Artefactdistribution getArtefactdistribution(java.lang.Integer id) {
        return ejbSaveRetrieve.find(id);
    }
    
    /**
     * ArtefactControllerConverter class.
     * the details of the methods can be found at the following url;
     * https://javaserverfaces.java.net/nonav/docs/2.0/javadocs/javax/faces/convert/FacesConverter.html
     * 
     */
    @FacesConverter(forClass = Artefactdistribution.class)
    public static class ArtefactdistributionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArtefactdistributionController controller = (ArtefactdistributionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "artefactdistributionController");
            return controller.getArtefactdistribution(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Artefactdistribution) {
                Artefactdistribution o = (Artefactdistribution) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Artefactdistribution.class.getName());
            }
        }
    }
}
//END IDE GENERATED CODE
