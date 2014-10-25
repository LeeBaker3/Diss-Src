/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scan;

import com.mycompany.atomicinformationconfigurationmanager.entities.Artefact.Artefact;
import com.mycompany.atomicinformationconfigurationmanager.entities.atomicinformation.Atomicinformation;
import java.io.ByteArrayInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import org.apache.commons.lang3.StringUtils;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.text.Paragraph;

/**
 *  ScanOdt Class. This Class inherits the Scan Class and adds the ability to scan
 *  an Open Office Format (ODF) Text Document using the file extension .odt. The class 
 *  uses a reference to an Artefact Entity and a List<> of Atomicinformation entities
 *  for a specific project and searches the Artefact file in the database in an attempt 
 *  to find matching strings in the Artefact.
 * 
 *  The class carries out some processing of the Atomicinformation text strings and the text in the 
 *  file before searching. Any matching Atomicinformation in the Artefact that is found is return
 *  in a further List<> of Atomicinformation entities.
 * 
 *  @author Lee Baker
 *  @version 1.0
 */
@Stateless
@Named("scanOdt")
public class ScanOdt extends Scan{
    
    /**
     * scan method. The scan method is the only method that is extend from the base class Scan. 
     * The method must be implemented and will be different for each document extension type.
     * 
     * @param artefact artefact entity that contains artefact file to be scanned
     * @param projectAtomicinfo a List<> of Atomicinformation for the selected project
     * @return a List<> of Atomicinformation entities found in the Artefact file. Return null
     * if an error is thrown
     */
    
    //START LEE BAKER GENERATED CODE
    @Override
    public List<Atomicinformation> scan (Artefact artefact, List<Atomicinformation> projectAtomicinfo){
        List<Atomicinformation> itemsFound = new LinkedList<>();// List<> to hold Atomicinformation found in artefact
        TextDocument odtDocument;// ODF text document to that the Artefact Byte[] will be loaded into
        byte[] file = artefact.getArtefactFile();
         
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(file);
            odtDocument = TextDocument.loadDocument(byteArrayInputStream);
            
            //Text in document will be scanned one paragraph at a time
            Iterator<Paragraph> iterator = odtDocument.getParagraphIterator();
            
            //Loop through each paragraph in the ODF text document 
            while (iterator.hasNext()){
                Paragraph paragraph; //Paragraph to be scanned
                String paragraphText;//Text extracted from the paragraph
                String paragraphTextClean;//Text extracted from the paragraph and cleaned (See Clean method)
                
                paragraph = iterator.next();
                paragraphText = paragraph.getTextContent();
                paragraphTextClean = cleanString(paragraphText);
                
                //Loop each Atomicinformation in the List<> and scan the clean paragraph text
                //If the clean paragraph contains the Atomicinformation then add a reference
                //to the Atomicinfromation entity in the List<> itemsFound
                for (Atomicinformation atomicinformation : projectAtomicinfo){
                    
                    String atomicinformationClean;//Text extracted from the Atomicinfromation content attribute and cleaned (See Clean method)
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
//END LEE BAKER GENERATED CODE
