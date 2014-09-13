/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filemanagement;

import java.io.IOException;
import java.io.InputStream;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.odftoolkit.simple.TextDocument;

/**
 *
 * @author Lee Baker
 */
@Stateless
@Named("downloadfile")
public class DownloadFile {

    private Part file;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public String uploadODF() throws IOException{
        try {
            TextDocument oldDocument = TextDocument.newTextDocument();
            InputStream inputStream = file.getInputStream();
            oldDocument = TextDocument.loadDocument(inputStream);
            oldDocument.addParagraph("New text added");
            oldDocument.save("C:\\Users\\Lee Baker\\Desktop\\TestFileUpdate.odt");
            // TO Be completed out
        } catch (Exception e) {
            
        }
        return "Success";
    }
}
