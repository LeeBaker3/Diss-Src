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

/**
 *
 * @author Lee Baker
 */
@Stateless
public class CreateArtefactAtomicInfoRecords {

    public CreateArtefactAtomicInfoRecords() {
    }

    public boolean CreateInfoRecords(List<Atomicinformation> listAtomicInfo, Artefact artefact) {
        boolean result = false;
        
        return result;
    }

}
