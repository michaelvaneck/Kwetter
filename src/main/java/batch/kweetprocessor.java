/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import model.Gebruiker;
import model.Kweet;
import service.KwetterService;

/**
 *
 * @author Michael van Eck
 */
@Dependent
@Named
public class kweetprocessor implements ItemProcessor {

    @Inject
    KwetterService kwetterService;

    @Override
    public Object processItem(Object o) throws Exception {
        // RAW KWEET AKA JSON DATA
        RawKweet rawKweet = (RawKweet) o;
        System.out.println("--> RECEIVED RAW KWEET IN PROCESSOR <--");

        // STAPPEN TE ONDERNEMEN IN DE PROCESSOR
        // ----------------------------
        // X -> HET PROCESSEN/CONVERTEN VAN DE KWEET NAAR EEN DOMEIN MODEL KWEET
        // ----------------------------
        // X -> CHECKEN OFDAT DE SCREENNAME VAN DE RAW TWEET OVEREENKOMT MET EEN USER
        // ALS DIE USER NIET BESTAAT AANMAKEN EN DIE USER PERSISTEN
        // EN DAN DE TWEET TOEVOEGEN AAN DIE USER
        // ----------------------------
        Kweet processedKweet = new Kweet();

        // TEXT SETTEN
        processedKweet.setText(rawKweet.getText());
        // LOCATION SETTEN
        processedKweet.setPostedFrom(rawKweet.getLocation());
        // DATE SETTEN
        String dateString = rawKweet.getDate();
        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        processedKweet.setPostDate(date);
        // GEBRUIKER SETTEN
        // CHECKEN OF GEBRUIKER BESTAAT IN USERDAO
        Gebruiker test = kwetterService.findGebruikerByName(rawKweet.getScreenName());

        if (test == null) {
            System.out.println("--> USER NONEXISTENT CREATING NEW USER: " + rawKweet.getScreenName() + "<--");
            // GEBRUIKER BESTAAT NIET MOETEN WE DUS EERST AANMAKEN
            Gebruiker harrie = new Gebruiker();
            harrie.setScreenName(rawKweet.getScreenName());
            harrie.setFollowedBy(new ArrayList<Gebruiker>());
            harrie.setFollowing(new ArrayList<Gebruiker>());
            harrie.setKweets(new ArrayList<Kweet>());
            harrie.setBiography("UNTITLED");
            harrie.setLocation("UNTITLED");
            harrie.setWebsite("UNTITLED");
            processedKweet.setGebruiker(harrie);
            // PERSIST DE ZOJUIST GEMAAKTE PAPI
            kwetterService.createUser(harrie);         
            harrie = kwetterService.findGebruikerByName(rawKweet.getScreenName());
            //harrie.addKweet(processedKweet);           
            //kwetterService.editUser(harrie);
            
            // SEND OBJECT
            SendObject send = new SendObject();
            send.setGebruiker(harrie);
            send.setKweet(processedKweet);

            return send;
        } else {
            System.out.println("--> USER EXISTS SETTING KWEET IN USER LIST<--");
            // GEBRUIKER BESTAAT AL, ALLEEN DE KWEET TOEVOEGEN AAN DE LIJST
            processedKweet.setGebruiker(test);
            //test.addKweet(processedKweet);
            //kwetterService.editUser(test);           
            
            // SEND OBJECT
            SendObject send = new SendObject();
            send.setGebruiker(test);
            send.setKweet(processedKweet);

            return send;
        }
    }
}
