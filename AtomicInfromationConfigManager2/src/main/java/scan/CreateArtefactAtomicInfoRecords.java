/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scan;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import javax.ejb.Stateless;
import java.util.List;
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
    
    private List<Atomicinformation> listAtomicInformation;

    public List<Atomicinformation> getListAtomicInformation() {
        return listAtomicInformation;
    }

    public void setListAtomicInformation(List<Atomicinformation> listAtomicInformation) {
        this.listAtomicInformation = listAtomicInformation;
    }
    
    public String CreateInfoRecords(List<Atomicinformation> listAtomicInfo, Artefact artefact) { 
        setListAtomicInformation(listAtomicInfo);
        
        return "/Faces/artefactatomicinformation/ScanList";
    }

}
