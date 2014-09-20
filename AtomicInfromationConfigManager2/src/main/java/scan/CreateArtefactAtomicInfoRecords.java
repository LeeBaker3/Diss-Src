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
import java.util.ArrayList;
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
    
    private List<Atomicinformation> foundItems;
    private Map<Atomicinformation, Boolean> checked = new HashMap<>();
    private List<Atomicinformation> newItems;
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

    public Map<Atomicinformation, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<Atomicinformation, Boolean> checked) {
        this.checked = checked;
    }
    
    public List<Atomicinformation> getFoundItems() {
        return foundItems;
    }

    public void setFoundItems(List<Atomicinformation> foundItems) {
        this.foundItems = foundItems;
    }

    public List<Atomicinformation> getNewItems() {
        return newItems;
    }

    public void setNewItems(List<Atomicinformation> newItems) {
        this.newItems = newItems;
    }
    
    
    public String createInfoRecords(List<Atomicinformation> foundItems, Artefact artefact) { 
        setFoundItems(foundItems);
        newItemsFound();
        setSize(newItems.size());
       
        return "/Faces/artefactatomicinformation/ScanList";
    }
    
    private String newItemsFound (){
        List<Atomicinformation> items = getFoundItems();
        newItems = new ArrayList<Atomicinformation>();
        
        try{
            List<Artefactatomicinformation> currentItems = artefactatomicinformationController.getSaveRetrieve().findByEntityActiveAndArtefactIDAndIsCurrentVersion(true, artefactController.getCurrent(), true);
            if ( currentItems != null || currentItems.isEmpty() != true){
                for (Atomicinformation atomic: items){
                    if (currentItems.contains(atomic) == false){
                           newItems.add(atomic);
                    }
                }
                return null;
            }
            else
            {
                newItems.addAll(items);
                return null;
            }
        }
        catch(Exception e)
        {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ScanListArtefactatomicinformationFail"));
        }
        return returnToJSFPage.returnToArtefactView();
    }
    
    public String create(){
        try{
            for(Atomicinformation item: newItems){
                if(checked.get(item.getId()) !=null){
                    if(checked.get(item.getId())){
                        artefactatomicinformationController.prepareCreateFromArtefact();
                        artefactatomicinformationController.getCurrent().setAtomicInformationID(item);
                        artefactatomicinformationController.update();
                    }
                }
            }
        }
        catch (Exception e){
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ScanListArtefactatomicinformationFail"));
        }
        return returnToJSFPage.returnToArtefactView();
    }
}
