/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scan;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.text.Paragraph;

/**
 *
 * @author Lee Baker
 */
@Stateless
public class ScanOdt extends Scan{

    @Override
    public List<Atomicinformation> scan (Artefact artefact, List<Atomicinformation> projectAtomicinfo){
        List<Atomicinformation> itemsFound;
        int fileLength;
        TextDocument odtDocument;
        fileLength = artefact.getArtefactFile().length;
        byte[] file = new byte[fileLength];
        
        try {
            odtDocument = TextDocument.newTextDocument();
            InputStream inputStream = new ByteArrayInputStream(file);
            odtDocument = TextDocument.loadDocument(inputStream);
            
            Iterator<Paragraph> iterator = odtDocument.getParagraphIterator();
            
            return itemsFound;
        } catch (Exception e) {
            return null;
        }
        
        
    }
            
}
