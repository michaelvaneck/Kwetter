/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

/**
 *
 * @author Michael van Eck
 */
public class RawKweet {
    private String screenName;
    private String date;
    private String location;
    private String text;
    
    public RawKweet(String screenName, String date, String location, String text){
        this.screenName = screenName;
        this.date = date;
        this.location = location;
        this.text = text;
    } 
    
    public RawKweet(){}
    
    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
