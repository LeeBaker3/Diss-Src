package com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation;

import com.mycompany.atomicinformationconfigurationmanager.entities.base.BaseController;
import com.mycompany.atomicinformationconfigurationmanager.entities.project.ProjectController;
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
 *  AtomicinformationController Class. This class inherits from the base class BaseController. The primary purpose
 *  is to act as the Controller part of the MVC pattern for the Atomicinformation Entity MVC. 
 *  
 *  The class is based on the NetBeans Controller template and modified extensively for this project 
 * 
 *  @author Lee Baker
 *  @version 1.0
 */
//START IDE GENERATED CODE
@Named("atomicinformationController")
@SessionScoped
public class AtomicinformationController extends BaseController implements Serializable {
    private static final long serialVersionUID = 1L;

    private Atomicinformation current;// Current Artefactatomicinformation entity that has been selected/viewed/edited/created  
    private DataModel items = null;// This is affectively the Model part of the MVC pattern
    @EJB
    private com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.AtomicinformationSaveRetrieve ejbSaveRetrieve;
    private PaginationHelper pagination;
    private int selectedItemIndex;// Selected entity Index in the DataModel items
    //END IDE GENERATED CODE
    
    //START LEE BAKER GENERATED CODE
    private Atomicinformation old;// Used to hold a reference to the old version of an Atomicinformation entity when the entity is being updated
    private boolean updating = false;//Set to true when making changes that update the version of an Atomicinformation entity
    private boolean  itemSelected = false; //Set to true when an item is selected in the List DataTable
    
    @Inject
    private ProjectController projectController;

    public AtomicinformationController() {
    }

    public Atomicinformation getCurrent() {
        return current;
    }

    public void setCurrent(Atomicinformation current) {
        this.current = current;
    }

    public Atomicinformation getOld() {
        return old;
    }

    public void setOld(Atomicinformation old) {
        this.old = old;
    }

    public boolean isUpdating() {
        return updating;
    }

    public void setUpdating(boolean updating) {
        this.updating = updating;
    }
    
    /**
     * getSelected method. get the currently selected Artefact entity in the model.
     * If one has been selected then a new Artefact entity instance is created
     * 
     * @return the currently selected Artefact entity in the Model (MVC)
     */
    public Atomicinformation getSelected() {
        if (current == null) {
            current = new Atomicinformation();
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
        current = (Atomicinformation) getItems().getRowData();
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
                    current = (Atomicinformation) getItems().getRowData();
                }
                itemSelected = false;
                selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
                return jsfPage;
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("NoRecordSelectedError"));
            return null;
        }
    }
    //END LEE BAKER GENERATED CODE

    public AtomicinformationSaveRetrieve getSaveRetrieve() {
        return ejbSaveRetrieve;
    }
    
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
                *   08/08/14 @Lee Baker
                *   IDE Code modified to with if else statement to return atomic infromation for selected project 
                */
                @Override
                public int getItemsCount() {
                    int localCount;
                    if (projectController.getCurrent()!=null){
                        localCount = getSaveRetrieve().countEntityActiveAndProjectIDAndIsCurrentVersion(true, projectController.getCurrent(), true);
                    }
                    else{
                        localCount = getSaveRetrieve().countEntityActiveIsCurrentVersion(true, true);
                    }
                    return localCount;
                }
                
                /* 
                *   08/08/14 @Lee Baker
                *   IDE Code modified to with if else statement to return atomic infromation for selected project 
                */
                @Override
                public DataModel createPageDataModel() {
                    
                    if (projectController.getCurrent() != null){
                        return  new  ListDataModel(getSaveRetrieve().findRangeEntityActiveAndProjectIDAndISCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true, projectController.getCurrent(), true));
                    }
                    else{
                      return new ListDataModel(getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true, true));  
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
        return "/Faces/atomicinformation/List";
    }
    
    /**
     * prepareView method. The purpose of this method is to extract the selected
     * entity from the model (MVC) so it can be viewed
     * 
     * @return a string that is used to navigate to the JSF View page
     */
    public String prepareView() {
        if (itemSelected == false)
        {
            current = (Atomicinformation) getItems().getRowData();
        }
        itemSelected = false;
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "/Faces/atomicinformation/View";
    }
    
    /**
     * prepareCreate method. The purpose of this method is to prepare the Model to
     * have a new entity created and inserted. Create a new entity instance
     * 
     * @return a string that is used to navigate to the JSF Create page
     */
    public String prepareCreate() {
        current = new Atomicinformation();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        return "/Faces/atomicinformation/Create";
    }
    //END OF IDE MODIFIED CODE BY LEE BAKER 
    
    /*  @Lee baker
    *   10/08/2014 Added for navigation from  artefactatomicinformation/Create/xhtml
    */
    //START LEE BAKER GENERATED CODE
    /**
     * prepareCreate method. The purpose of this method is to prepare the Model to
     * have a new entity created and inserted. Create a new entity instance. This method
     * only differs from the prepareCreate() method because it navigates back to the
     * CreateFromArtefactAtomicInformation JSF page
     * 
     * @return a string that is used to navigate to the JSF Create page
     */
    public String prepareCreateFormArtefactAtomicInformation() {
        current = new Atomicinformation();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        return "/Faces/atomicinformation/CreateFromArtefactAtomicInformation";
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
            *   02/08/14 @Lee Baker
            *   If a project has been selected then create new AtomicInfromation with a reference selected Project
            */
            if(projectController.getCurrent() !=null){
                current.setProjectID(projectController.getCurrent());
            }
            setEntityActive(current);
            getSaveRetrieve().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtomicinformationCreated"));
            
            /*
            *   13/08/14 @Lee Baker
            *   Code modified to return from multiple calling xHtml pages
            */
            if ("prepareCreate".equals(returnMethod)){
                return prepareCreate();
            }
            if ("prepareCreateFormArtefactAtomicInformation".equals(returnMethod)){
                return prepareCreateFormArtefactAtomicInformation();
            }
            else{
               return prepareCreate(); 
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
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
        return prepareSelected("/Faces/atomicinformation/Edit");
    }
    //END OF IDE MODIFIED CODE BY LEE BAKER
    
    /*
    *   24/08/14 @Lee Baker
    *   When recreationg a new atomicinformation copies details of current atomicinformation to a new
    *   new atomicinformation and set the old one to not current.
    */
    //START LEE BAKER GENERATED CODE
    /**
     * prepareUpdateVersion method. The purpose of this method is to get the entity from 
     * the model (MVC) so that the version can be updated. A new entity is created in 
     * that will be the new version, the details are copied from the old to the new using
     * the copy method. The Edit JSF page is used to for entering the
     * changes in the new Version of the entity.
     * 
     * @return returns a String that is used to navigates to the JSF Edit page
     */
     public String prepareUpdateVersion(){
        updating = true;
        old = current;
        prepareVersion(old, ejbSaveRetrieve);
        current = new Atomicinformation();
        copy(old, current);
        return "/Faces/atomicinformation/Edit";
    }
     
     /**
     * copy method. The purpose of this methods is to copy the attributes of one entity
     * to another
     * 
     * @param oldAtomicinformation
     * @param newAtomicinformation
     * @return the new entity with the attributes copied.
     */
    public Atomicinformation copy(Atomicinformation oldAtomicinformation, Atomicinformation newAtomicinformation){
        newAtomicinformation.setContent(oldAtomicinformation.getContent());
        newAtomicinformation.setTypeOfAtomicInformationID(oldAtomicinformation.getTypeOfAtomicInformationID());
        newAtomicinformation.setProjectID(oldAtomicinformation.getProjectID());
        return newAtomicinformation;
    }     
    
    /**
     * update method. The purpose of this methods is to persist the newly created version entity
     * to the database. The method also sets the Project attribute of the new entity to
     * the currently selected project and the EntityActive attribute to true so that it 
     * can be retrieved from the database in future queries and manages the setting of the previousVersion
     * attribute and version number of of the new entity by calling the manageVersion method.
     * If an error occurs during the process an error message is displayed and the entity isn't stored
     * the rollBackVersion method is called to make the old Entity the current version.
     * 
     * @return returns a String, if the process succeeds then the View page is displayed.
     * If the process fails then the errors message is displayed and the page remains the 
     * same
     */
    public String update() {
        try {
            if(projectController.getCurrent() != null){
                current.setProjectID(projectController.getCurrent());
            }
            
            if (updating == true){
                manageVersion(old, current);
                getSaveRetrieve().create(current);
                updating = false;
            }
            else{
                getSaveRetrieve().edit(current);
            }
            
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtomicinformationUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
             if (updating == true){
                rollBackVersion(old, ejbSaveRetrieve);
                updating = false;
                current = old;
            }           
            return null;
        }
    }
    //END LEE BAKER GENERATED CODE

    /*  
    *   08/08/14 @Lee Baker
    *   Code added to disable entity instead of destroying it
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AtomicinformationDeleted"));
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
        int count;
        if (projectController.getCurrent() != null){
             count = getSaveRetrieve().countEntityActiveAndProjectIDAndIsCurrentVersion(true, projectController.getCurrent(), true);
         }
         else {
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
            if (projectController.getCurrent() != null){
                current = getSaveRetrieve().findRangeEntityActiveAndProjectIDAndISCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true, projectController.getCurrent(), true).get(0);
            }
            else {
                current = getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true, true).get(0);
            }
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
    public Atomicinformation getAtomicinformation(java.lang.Integer id) {
        return ejbSaveRetrieve.find(id);
    }
    
    /**
     * ArtefactControllerConverter class.
     * the details of the methods can be found at the following url;
     * https://javaserverfaces.java.net/nonav/docs/2.0/javadocs/javax/faces/convert/FacesConverter.html
     * 
     */
    @FacesConverter(forClass = Atomicinformation.class)
    public static class AtomicinformationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AtomicinformationController controller = (AtomicinformationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "atomicinformationController");
            return controller.getAtomicinformation(getKey(value));
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
            if (object instanceof Atomicinformation) {
                Atomicinformation o = (Atomicinformation) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Atomicinformation.class.getName());
            }
        }

    }
}
 //START IDE GENERATED CODE