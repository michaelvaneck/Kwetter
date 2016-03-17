/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.batch.api.chunk.AbstractItemReader;

/**
 *
 * @author Michael van Eck
 */
@Dependent
@Named
public class kweetreader extends AbstractItemReader {

    private InputStream fis;
    private JsonParser jsonParser;
    private Event event;

    @Override
    public void open(Serializable srlzbl) throws Exception {
        System.out.println("--> OPENING FILE INPUT STREAM <--");
        //InputStream fis = new FileInputStream("C:\\Users\\Michael van Eck\\Dropbox\\Met Merijn\\JEA6\\Weblog Michael\\Opdracht2Kwetter\\src\\main\\resources\\resources\\kwetterinput.json");
        fis = new FileInputStream("C:\\Users\\Michael van Eck\\Downloads\\Opdracht2Kwetter\\src\\main\\resources\\resources\\kwetterinput.json");
        jsonParser = Json.createParser(fis);
        System.out.println("--> FILE READ IN STREAM <--");
        // Over de array heen gaan
        jsonParser.next();
    }

    @Override
    public Object readItem() throws Exception {
        System.out.println("--> READING FILE ENTRY <--");
        if (jsonParser.hasNext()) {
            event = jsonParser.next();
            if (event == event.END_ARRAY) {
                System.out.println("--> END OF JSON ARRAY <--");
                return null;
            }
            event = jsonParser.next();
            event = jsonParser.next();
            RawKweet source = new RawKweet();
            System.out.println("--> CREATED RAW KWEET OBJECT <--");
            source.setScreenName(jsonParser.getString());
            System.out.println("--> SET NAME: " + source.getScreenName() + " <--");
            event = jsonParser.next();
            event = jsonParser.next();
            // STRING <- TEXT
            source.setText(jsonParser.getString());
            System.out.println("--> SET TEXT: " + source.getText() + " <--");
            event = jsonParser.next();
            event = jsonParser.next();
            // STRING <- Location
            source.setLocation(jsonParser.getString());
            System.out.println("--> SET LOCATON: " + source.getLocation() + " <--");
            event = jsonParser.next();
            event = jsonParser.next();
            // STRING <- Location
            source.setDate(jsonParser.getString());
            System.out.println("--> SET DATE: " + source.getDate() + " <--");
            
            event = jsonParser.next();
            return source;
        }

        return null;
    }
}
