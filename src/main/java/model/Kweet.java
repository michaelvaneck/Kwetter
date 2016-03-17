/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author Michael van Eck
 */
@NamedQuery(name="Kweet.findAll", query="SELECT k from Kweet k")
@Entity
public class Kweet implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String kweet;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date postDate;
    private String postedFrom;
    
    @ManyToOne(optional = false)
    private Gebruiker gebruiker;

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }
    
    public Kweet() {    
    } 
        
    public String getText() {
        return kweet;
    }

    public void setText(String _text) {
        this.kweet = _text;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date _postDate) {
        this.postDate = _postDate;
    }

    public String getPostedFrom() {
        return postedFrom;
    }

    public void setPostedFrom(String _postedFrom) {
        this.postedFrom = _postedFrom;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long _id) {
        this.id = _id;
    }
}
