/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scan;

import com.mycompany.atomicinformationconfigurationmanager.businessrules.ReturnToJSFPage;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.ArtefactController;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.Artefactatomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.Artefactatomicinformation.ArtefactatomicinformationController;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import com.mycompany.atomicinformationconfigurationmanager.entities.util.JsfUtil;
import java.util.HashMap;
import java.util.Iterator;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Lee Baker
 */
@Stateless
@Named("createArtefactAtomicInfoRecords")
public class CreateArtefactAtomicInfoRecords {

    public CreateArtefactAtomicInfoRecords() {
    }
    
    private List<Atomicinformation> listItems;
    private Map<Atomicinformation, Boolean> newItems = new HashMap<>();
    private int size;
    @Inject
    ArtefactatomicinformationController artefactatomicinformationController;
    @Inject
    ArtefactController artefactController;
    @Inject
    ReturnToJSFPage returnToJSFPage;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Atomicinformation, Boolean> getNewItems() {
        return newItems;
    }

    public void setNewItems(Map<Atomicinformation, Boolean> newItems) {
        this.newItems = newItems;
    }
    
    public List<Atomicinformation> getListItems() {
        return listItems;
    }

    public void setListItems(List<Atomicinformation> listItems) {
        this.listItems = listItems;
    }
    
    
    public String CreateInfoRecords(List<Atomicinformation> listAtomicInfo, Artefact artefact) { 
        setListItems(listAtomicInfo);
        newItemsFound(listAtomicInfo);
        setSize(newItems.size());
        
        return "/Faces/artefactatomicinformation/ScanList";
    }
    
    private String newItemsFound (List<Atomicinformation> foundItems){
        try{
            List<Artefactatomicinformation> currentItems = artefactatomicinformationController.getSaveRetrieve().findByEntityActiveAndArtefactIDAndIsCurrentVersion(true, artefactController.getCurrent(), true);
            if ( currentItems != null || currentItems.isEmpty() != true){
                for (Atomicinformation item : foundItems) {
                    if (currentItems.contains(item) != false){
                        newItems.put(item, Boolean.FALSE);
                    }
                }
            }
        }
        catch(Exception e)
        {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ScanListArtefactatomicinformationFail"));
        }
        return returnToJSFPage.returnToArtefactView();
    }
    
    public void create(){
        for (Entry<Atomicinformation, Boolean> entry : newItems.entrySet()) {
            if (entry.getValue()) {
                artefactatomicinformationController.prepareCreateFromArtefact();
                artefactatomicinformationController.getCurrent().setAtomicInformationID(entry.getKey());
                artefactatomicinformationController.update();
            }
        }
    }
}
