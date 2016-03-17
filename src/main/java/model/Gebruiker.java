/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Michael van Eck
 */
@NamedQueries ({
    @NamedQuery(name="findGebruikersByFilter", query="SELECT g FROM Gebruiker g WHERE g.screenName LIKE '%:filter%'"),
    @NamedQuery(name="findAllGebruikers", query="SELECT g FROM Gebruiker g"),
    @NamedQuery(name="findGebruikerByName", query="SELECT g FROM Gebruiker g WHERE g.screenName = :screenName"),
})
@Entity
public class Gebruiker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String screenName;
    
    @OneToMany(mappedBy = "gebruiker")
    private List<Kweet> kweets;

    /**
     * Mensen die IK volg
     */
    @ManyToMany(mappedBy = "followedBy")
    private List<Gebruiker> following;

    /**
     * Mensen die MIJ volgen
     */
    @ManyToMany
    private List<Gebruiker> followedBy;

    private String location;
    private String website;
    private String biography;

    public Gebruiker() {
        
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public List<Kweet> getKweets() {
        return kweets;
    }

    public void setKweets(List<Kweet> kweefs) {
        this.kweets = kweefs;
    }

    public List<Gebruiker> getFollowing() {
        return following;
    }

    public void setFollowing(List<Gebruiker> following) {
        this.following = following;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String _location) {
        this.location = _location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String _website) {
        this.website = _website;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String _biography) {
        this.biography = _biography;
    }
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Gebruiker> getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(List<Gebruiker> followedBy) {
        this.followedBy = followedBy;
    }
    
    public boolean addFollowing(Gebruiker gebruiker)
    {
        this.following.add(gebruiker);
        return true;
    }
    
    public boolean removeFollowing(Gebruiker gebruiker)
    {
        this.following.remove(gebruiker);
        return true;
    }
    
    public void addFollower(Gebruiker gebruiker)
    {
        this.followedBy.add(gebruiker);
    }
    
    public void removeFollower(Gebruiker gebruiker)
    {
        this.followedBy.remove(gebruiker);
    }
    
    public void addKweet(Kweet k)
    {
        this.kweets.add(k);
    }
}
