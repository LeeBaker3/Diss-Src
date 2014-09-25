package com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation;

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
 *  ArtefactatomicinformationController Class. This class inherits from the base class BaseController. The primary purpose
 *  is to act as the Controller part of the MVC pattern for the Artefactatomicinformation Entity MVC. 
 *  
 *  The class is based on the NetBeans Controller template and modified extensively for this project 
 * 
 *  @author Lee Baker
 *  @version 1.0
 */

//START IDE GENERATED CODE
@Named("artefactatomicinformationController")
@SessionScoped
public class ArtefactatomicinformationController extends BaseController implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Artefactatomicinformation current;// Current Artefactatomicinformation entity that has been selected/viewed/edited/created  

    private DataModel items = null;// This is affectively the Model part of the MVC pattern
    @EJB
    private com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.ArtefactatomicinformationSaveRetrieve ejbSaveRetrieve;
    private PaginationHelper pagination;
    private int selectedItemIndex;// Selected entity Index in the DataModel items
    //END IDE GENERATED CODE
    
    //START LEE BAKER GENERATED CODE
    private Artefactatomicinformation old;// Used to hold a reference to the old version of an Artefactatomicinformation entity when the entity is being updated
    private boolean updating = false;//Set to true when making changes that update the version of an Artefactatomicinformation entity
    private boolean itemSelected = false; //Set to true when an item is selected in the List DataTable
    private Boolean selectedFromArtefact = false;// This is set to true if created from the Artefactatomicinformation View to navigate back to the correct Artefact entity
    
    @Inject
    private ArtefactController artefactController; 
    
    public ArtefactatomicinformationController() {
    }  
    
    public Artefactatomicinformation getCurrent() {
        return current;
    }

    public void setCurrent(Artefactatomicinformation current) {
        this.current = current;
    }    
    
    public boolean isUpdating() {
        return updating;
    }

    public void setUpdating(boolean updating) {
        this.updating = updating;
    }
    
    public void setSelected(ValueChangeEvent event){
        current = (Artefactatomicinformation) getItems().getRowData();
        itemSelected = true;
    }
    //END LEE BAKER GENERATED CODE
    
    //START IDE GENERATED CODE
    public Artefactatomicinformation getSelected() {
        if (current == null) {
            current = new Artefactatomicinformation();
            selectedItemIndex = -1;
        }
        return current;
    }
    //END IDE GENERATED CODE
    
    //START LEE BAKER GENERATED CODE
    private String prepareSelected(String jsfPage){
        try {
            if (itemSelected == false)
                {
                    current = (Artefactatomicinformation) getItems().getRowData();
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
    
    public ArtefactatomicinformationSaveRetrieve getSaveRetrieve() {
        return ejbSaveRetrieve;
    }
    
    //START OF IDE MODIFIED CODE BY LEE BAKER 
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                
                /* 
                *   03/08/14 @Lee Baker
                *   IDE Code modified to use countEntityActiveIsCurrentVersion() instead of count()
                */ 
                @Override
                public int getItemsCount() {
                    int localCount;
                    if (artefactController.getCurrent()!=null){
                        localCount = getSaveRetrieve().countEntityActiveAndArtefactIDAndIsCurrentVersion(true, artefactController.getCurrent(), true);
                    }
                    else{
                        localCount = getSaveRetrieve().countEntityActiveIsCurrentVersion(true, true);
                    }
                    return localCount;
                }

                /* 
                *   09/08/14 @Lee Baker
                *   IDE Code modified to with if else statement to return artefacts for selected project 
                */                
                @Override
                public DataModel createPageDataModel() {
                    if(artefactController.getCurrent() !=null){
                        return new ListDataModel(getSaveRetrieve().findRangeEntityActiveAndArtefactIDAndIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, true, artefactController.getCurrent(), true));
                    }
                    else{
                        return new ListDataModel(getSaveRetrieve().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    }
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        itemSelected = false;
        return "List";
    }
    
    public String prepareView() {
        return prepareSelected("View");
    }

    public String prepareCreate() {
        current = new Artefactatomicinformation();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        return "/Faces/artefactatomicinformation/Create";
    }
    //END OF IDE MODIFIED CODE BY LEE BAKER
    
    //START LEE BAKER GENERATED CODE
    public String prepareCreateFromArtefact(){
        current = new Artefactatomicinformation();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        selectedFromArtefact = true;
        return "/Faces/artefactatomicinformation/CreateFromArtefact";
    }
    //END LEE BAKER GENERATED CODE
    
    //START OF IDE MODIFIED CODE BY LEE BAKER 
    public String create(String returnMethod) {
        try {
            /*  
            *   02/08/14 @Lee Baker
            *   If a Artefact has been selected then create new Artefactatomicinformation with a reference selected Artefact
            *   and set entityActive when created
            */
            if(selectedFromArtefact==true){
                current.setArtefactID(artefactController.getCurrent());
                selectedFromArtefact=false;
            }

            setEntityActive(current);
            getSaveRetrieve().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactatomicinformationCreated"));
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
    //END OF IDE MODIFIED CODE BY LEE BAKER 
    
    public String prepareEdit() {
        return prepareSelected("Edit");
    }

    /*
    *   24/08/14 @Lee Baker
    *   When recreationg a new artefact copies details of current artefact to a new
    *   new artefact and set the old one to not current.
    */
    //START LEE BAKER GENERATED CODE
    public String prepareUpdateVersion(){
        updating = true;
        old = current;
        prepareVersion(old, ejbSaveRetrieve);
        current = new Artefactatomicinformation();
        copy(old, current);
        return "Edit";
    }  
    
    public Artefactatomicinformation copy(Artefactatomicinformation oldArtefactatomicinformation, Artefactatomicinformation newArtefactatomicinformation){
        newArtefactatomicinformation.setAtomicInformationID(oldArtefactatomicinformation.getAtomicInformationID());
        newArtefactatomicinformation.setArtefactID(oldArtefactatomicinformation.getArtefactID());
        return newArtefactatomicinformation;
    }
     
    public String update() {
        try {
            if (updating == true){
                manageVersion(old, current);
                getSaveRetrieve().create(current);
                updating = false;
            }
            else{
                getSaveRetrieve().edit(current);
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactatomicinformationUpdated"));
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
    
    /*  
    *   09/08/14 @Lee Baker
    *   Code added to delete entity instead of destroying it
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

    private void performDelete() {
        setEntityInActive(current);
        try {
            getSaveRetrieve().entityInactive(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ArtefactatomicinformationDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    //END LEE BAKER GENERATED CODE
    
   //START OF IDE MODIFIED CODE BY LEE BAKER 
    private void updateCurrentItem() {
        int count;
        if(artefactController.getCurrent() != null){
            count = getSaveRetrieve().countEntityActiveAndArtefactIDAndIsCurrentVersion(true, artefactController.getCurrent(), true);
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
            if(artefactController.getCurrent() !=null){
                current = getSaveRetrieve().findRangeEntityActiveAndArtefactIDAndIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1}, true, artefactController.getCurrent(), true).get(0);
            }
            current = getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true, true).get(0);
        }
    }
    //END OF IDE MODIFIED CODE BY LEE BAKER 
    
    //START IDE GENERATED CODE
    public DataModel getItems() {
        if (items == null){
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    
    public void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbSaveRetrieve.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbSaveRetrieve.findAll(), true);
    }

    public Artefactatomicinformation getArtefactatomicinformation(java.lang.Integer id) {
        return ejbSaveRetrieve.find(id);
    }

    @FacesConverter(forClass = Artefactatomicinformation.class)
    public static class ArtefactatomicinformationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArtefactatomicinformationController controller = (ArtefactatomicinformationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "artefactatomicinformationController");
            return controller.getArtefactatomicinformation(getKey(value));
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
            if (object instanceof Artefactatomicinformation) {
                Artefactatomicinformation o = (Artefactatomicinformation) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Artefactatomicinformation.class.getName());
            }
        }

    }
}
//END IDE GENERATED CODE
