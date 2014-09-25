package com.mycompany.atomicinformationconfigurationmanager.entities.methodofdistribution;

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
import javax.inject.Named;

/**
 *  MethodofdistributionController Class. This class inherits from the base class BaseController. The primary purpose
 *  is to act as the Controller part of the MVC pattern for the Methodofdistribution Entity MVC. 
 *  
 *  The class is based on the NetBeans Controller template and modified extensively for this project 
 * 
 *  @author Lee Baker
 *  @version 1.0
 */

//START IDE GENERATED CODE
@Named("methodofdistributionController")
@SessionScoped
public class MethodofdistributionController extends BaseController implements Serializable {
    private static final long serialVersionUID = 1L;

    private Methodofdistribution current;// Current Methodofdistribution entity that has been selected/viewed/edited/created 
    private boolean  itemSelected = false; //Set to true when an item is selected in the List DataTable
    private DataModel items = null;// This is affectively the Model part of the MVC pattern
    @EJB
    private com.mycompany.atomicinformationconfigurationmanager.entities.methodofdistribution.MethodofdistributionSaveRetrieve ejbSaveRetrieve;
    private PaginationHelper pagination;
    private int selectedItemIndex;// Selected entity Index in the DataModel items

    public MethodofdistributionController() {
    }

    public Methodofdistribution getSelected() {
        if (current == null) {
            current = new Methodofdistribution();
            selectedItemIndex = -1;
        }
        return current;
    }
    //END IDE GENERATED CODE
    
    //START LEE BAKER GENERATED CODE
    public void setSelected(ValueChangeEvent event){
        current = (Methodofdistribution) getItems().getRowData();
        itemSelected = true;
    }
   
    private String prepareSelected(String jsfPage){
        try {
            if (itemSelected == false)
                {
                    current = (Methodofdistribution) getItems().getRowData();
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
    
    //START IDE GENERATED CODE
    private MethodofdistributionSaveRetrieve getSaveRetrieve() {
        return ejbSaveRetrieve;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getSaveRetrieve().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()},true, true));
                }
            };
        }
        return pagination;
    }
    //END IDE GENERATED CODE
    
    //START OF IDE MODIFIED CODE BY LEE BAKER 
    public String prepareList() {
        recreateModel();
        itemSelected = false;
        return "List";
    }
    //END OF IDE MODIFIED CODE BY LEE BAKER 

    public String prepareView() {
        return prepareSelected("View");
    }
    //START LEE BAKER GENERATED CODE
    public String prepareCreate() {
        current = new Methodofdistribution();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        return "Create";
    }
    //END LEE BAKER GENERATED CODE
    
    //START OF IDE MODIFIED CODE BY LEE BAKER 
    public String create() {
        try {
            /*  
            *   09/08/14 @Lee Baker
            *   Set entityActive = true when created
            */
            setEntityActive(current);
            getSaveRetrieve().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MethodofdistributionCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    //END OF IDE MODIFIED CODE BY LEE BAKER 
    
    //START IDE GENERATED CODE
    public String prepareEdit() {
        return prepareSelected("Edit");
    }

    public String update() {
        try {
            getSaveRetrieve().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MethodofdistributionUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    //END IDE GENERATED CODE

    /*  
    *   09/08/14 @Lee Baker
    *   Code added to delete entity instead of destroying it
    */
    //START LEE BAKER GENERATED CODE
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("MethodofdistributionDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    //START LEE BAKER GENERATED CODE
    
    //START IDE GENERATED CODE
    private void updateCurrentItem() {
        int count = getSaveRetrieve().countEntityActiveIsCurrentVersion(true, true);
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getSaveRetrieve().findRangeEntityActiveIsCurrentVersion(new int[]{selectedItemIndex, selectedItemIndex + 1},true, true).get(0);
        }
    }

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
        return JsfUtil.getSelectItems(ejbSaveRetrieve.findAllEntityActiveIsCurrentVersion(true, true), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbSaveRetrieve.findAllEntityActiveIsCurrentVersion(true, true), true);
    }

    public Methodofdistribution getMethodofdistribution(java.lang.Integer id) {
        return ejbSaveRetrieve.find(id);
    }

    @FacesConverter(forClass = Methodofdistribution.class)
    public static class MethodofdistributionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MethodofdistributionController controller = (MethodofdistributionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "methodofdistributionController");
            return controller.getMethodofdistribution(getKey(value));
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
            if (object instanceof Methodofdistribution) {
                Methodofdistribution o = (Methodofdistribution) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Methodofdistribution.class.getName());
            }
        }

    }
}
 //END IDE GENERATED CODE
