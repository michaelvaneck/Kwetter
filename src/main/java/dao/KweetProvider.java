/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.KweetProviderException;
import java.util.List;
import model.Gebruiker;
import model.Kweet;

/**
 *
 * @author Michael van Eck
 */
public interface KweetProvider {
    void create(Kweet k) throws KweetProviderException;
    List<Kweet> findAll() throws KweetProviderException;
    List<Kweet> findBy(String username) throws KweetProviderException;
    List<Kweet> findFilter(String filter) throws KweetProviderException; 
}
