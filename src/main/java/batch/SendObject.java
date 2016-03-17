/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

import model.Gebruiker;
import model.Kweet;

/**
 *
 * @author Michael van Eck
 */
public class SendObject {
    private Kweet kweet;
    private Gebruiker gebruiker;

    public Kweet getKweet() {
        return kweet;
    }

    public void setKweet(Kweet kweet) {
        this.kweet = kweet;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }
    
    public SendObject(){}
    
    
}
