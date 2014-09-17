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
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.text.Paragraph;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Lee Baker
 */
@Stateless
@Named("scanOdt")
public class ScanOdt extends Scan{

    @Override
    public List<Atomicinformation> scan (Artefact artefact, List<Atomicinformation> projectAtomicinfo){
        List<Atomicinformation> itemsFound = new LinkedList<>();
        TextDocument odtDocument;
        byte[] file = artefact.getArtefactFile();
         
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file);
            odtDocument = TextDocument.loadDocument(byteArrayInputStream);
            
            Iterator<Paragraph> iterator = odtDocument.getParagraphIterator();
            
            while (iterator.hasNext()){
                Paragraph paragraph;
                String paragraphText;
                String paragraphTextClean;
                
                paragraph = iterator.next();
                paragraphText = paragraph.getTextContent();
                paragraphTextClean = cleanString(paragraphText);
                
                for (Atomicinformation atomicinformation : projectAtomicinfo){
                    
                    String atomicinformationClean;
                    atomicinformationClean = cleanString(atomicinformation.getContent());
                    
                    if(StringUtils.contains(paragraphTextClean, atomicinformationClean)){
                        itemsFound.add(atomicinformation);
                    }
                }
            }
            byteArrayInputStream.close();
            return itemsFound;
        } catch (Exception e) {
            return null;
        } 
    }          
}
