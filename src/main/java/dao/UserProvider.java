/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.UserProviderException;
import java.util.List;
import model.Gebruiker;

/**
 *
 * @author Michael van Eck
 */
public interface UserProvider {

    /**
     * Aan de hand van een username kan het gebruiker object returned worden
     *
     * @param username
     * @return
     * @throws exception.UserProviderException
     */
    Gebruiker findGebruikerByName(String screenName) throws UserProviderException;
    
    /**
     * Methode om met een deel van een naam een user te vinden
     * @param filter
     * @return
     * @throws UserProviderException 
     */
    List<Gebruiker> findGebruikerByNameFilter(String filter) throws UserProviderException;

    /**
     * Creëerd een nieuwe user object
     *
     * @param user
     * @throws exception.UserProviderException
     */
    void createUser(Gebruiker user) throws UserProviderException;

    /**
     * Past het gebruiker object aan
     *
     * @param user
     * @throws exception.UserProviderException
     */
    void editUser(Gebruiker user) throws UserProviderException;

    /**
     * Verwijderd het gebruiker object
     *
     * @param user
     * @throws exception.UserProviderException
     */
    void removeUser(Gebruiker user) throws UserProviderException;

    /**
     * Returned een lijst van ALLE users in de Kwetter applicatie
     *
     * @return
     * @throws exception.UserProviderException
     */
    List<Gebruiker> findAllUsers() throws UserProviderException;

    /**
     * Aan de hand van een username kunnen alle mensen returned worden die de
     * desbetreffende gebruiker VOLGT
     *
     * @param username
     * @return
     * @throws exception.UserProviderException
     */
    List<Gebruiker> findAllFollowing(String username) throws UserProviderException;

    /**
     * Returned een int van de hoeveelheid volgers die een gebruiker heeft en
     * deze word gezocht aan de hand van de username
     *
     * @param username
     * @return
     * @throws exception.UserProviderException
     */
    int countFollowers(Gebruiker user) throws UserProviderException;

    /**
     * Returned een int van de hoeveelheid mensen die de gebruiker VOLGT en deze
     * word identified aan de hand van de username
     *
     * @param username
     * @return
     * @throws exception.UserProviderException
     */
    int countFollowing(Gebruiker user) throws UserProviderException;
    
    boolean addFollower(Gebruiker g, Gebruiker follower) throws UserProviderException;
}
