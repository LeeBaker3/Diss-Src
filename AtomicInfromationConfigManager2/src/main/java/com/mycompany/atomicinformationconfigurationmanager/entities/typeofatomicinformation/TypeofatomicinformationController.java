package com.mycompany.atomicinformationconfigurationmanager.entities.typeofatomicinformation;

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
 *  TypeofatomicinformationController Class. This class inherits from the base class BaseController. The primary purpose
 *  is to act as the Controller part of the MVC pattern for the Typeofatomicinformation Entity MVC. 
 *  
 *  The class is based on the NetBeans Controller template and modified extensively for this project 
 * 
 *  @author Lee Baker
 *  @version 1.0
 */

//START IDE GENERATED CODE
@Named("typeofatomicinformationController")
@SessionScoped
public class TypeofatomicinformationController extends BaseController implements Serializable {

    private Typeofatomicinformation current;// Current Typeofatomicinformation entity that has been selected/viewed/edited/created
    private boolean  itemSelected = false; //Set to true when an item is selected in the List DataTable
    private DataModel items = null;// This is affectively the Model part of the MVC pattern
    @EJB
    private com.mycompany.atomicinformationconfigurationmanager.entities.typeofatomicinformation.TypeofatomicinformationSaveRetrieve ejbSaveRetrieve;
    private PaginationHelper pagination;
    private int selectedItemIndex;// Selected entity Index in the DataModel items

    public TypeofatomicinformationController() {
    }

    public Typeofatomicinformation getSelected() {
        if (current == null) {
            current = new Typeofatomicinformation();
            selectedItemIndex = -1;
        }
        return current;
    }
    //END IDE GENERATED CODE
    
    //START LEE BAKER GENERATED CODE
    public void setSelected(ValueChangeEvent event){
        current = (Typeofatomicinformation) getItems().getRowData();
        itemSelected = true;
    }
   

    private String prepareSelected(String jsfPage){
        try {
            if (itemSelected == false)
                {
                    current = (Typeofatomicinformation) getItems().getRowData();
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
    private TypeofatomicinformationSaveRetrieve getSaveRetrieve() {
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

    public String prepareView() {
        return prepareSelected("View");
    }

    public String prepareCreate() {
        current = new Typeofatomicinformation();
        current.setIsCurrentVersion(true);
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            /*
            *   09/08/14 @Lee Baker
            *   Set entityActive = true when created
            */
            setEntityActive(current);
            getSaveRetrieve().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TypeofatomicinformationCreated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TypeofatomicinformationUpdated"));
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TypeofatomicinformationDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    //END LEE BAKER GENERATED CODE
    
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
    /*
    *   End of modified IDE code
    */

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

    public Typeofatomicinformation getTypeofatomicinformation(java.lang.Integer id) {
        return ejbSaveRetrieve.find(id);
    }

    @FacesConverter(forClass = Typeofatomicinformation.class)
    public static class TypeofatomicinformationControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TypeofatomicinformationController controller = (TypeofatomicinformationController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "typeofatomicinformationController");
            return controller.getTypeofatomicinformation(getKey(value));
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
            if (object instanceof Typeofatomicinformation) {
                Typeofatomicinformation o = (Typeofatomicinformation) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Typeofatomicinformation.class.getName());
            }
        }

    }
}
 //START IDE GENERATED CODE