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
        int fileLength;
        TextDocument odtDocument;
        fileLength = artefact.getArtefactFile().length;
        byte[] file = new byte[fileLength];
       
        
        try {
            odtDocument = TextDocument.newTextDocument();
            InputStream inputStream = new ByteArrayInputStream(file);
            odtDocument = TextDocument.loadDocument(inputStream);
            
            Iterator<Paragraph> iterator = odtDocument.getParagraphIterator();
            
            while (iterator.hasNext()){
                Paragraph paragraph;
                String paragraphText;
                
                paragraph = iterator.next();
                paragraphText = paragraph.getTextContent();
                
                for (Atomicinformation atomicinformation : projectAtomicinfo){
                    String paragraphTextClean;
                    String atomicinformationClean;
                    
                    paragraphTextClean = cleanString(paragraphText);
                    atomicinformationClean = cleanString(atomicinformation.getContent());
                    
                    if(StringUtils.contains(paragraphText, atomicinformationClean)){
                        itemsFound.add(atomicinformation);
                    }
                }
            }
            return itemsFound;
        } catch (Exception e) {
            return null;
        } 
    }          
}
