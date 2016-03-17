/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.KweetProvider;
import dao.UserProvider;
import exception.KweetProviderException;
import exception.KwetterServiceException;
import exception.UserProviderException;
import interceptors.KweetInterceptor;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import model.Gebruiker;
import model.Kweet;

/**
 *
 * @author Michael van Eck
 */
@Stateless
public class KwetterService {

    @Inject
    private UserProvider userProvider;

    @Inject
    private KweetProvider kweetProvider;

    public KwetterService() {

    }

    /**
     * DEZE METHODE NOG EFFE CHECKEN EN MAKEN HIER MOET RETURNED WORDEN: EEN
     * LIJST VAN KWEETS VAN DE GEVRAAGDE GEBRUIKER AANGEVULD MET DE KWEETS VAN
     * DE MENSEN DIE HIJ VOLGT
     *
     * @param username
     * @return
     */
    public List<Kweet> getTimeLine(String username) {
        return null;
    }

    /**
     * Methode die een gebruiker object returned aan de hand van een
     * gebruikersnaam via de userProvider. Als de gebruiker niet gevonden kan
     * worden wordt er een exception gethrowed en in de KwetterServiceException
     * gezet
     *
     * @param username dit is de string die de username bevat voor de
     * zoekfunctie
     * @return de gebruiker die opgevraagd is
     * @throws exception.UserProviderException deze wordt gethrowed als er in de
     * UserImplementation iets fout gaat dit kan namelijk een SQL/Database
     * exception zijn
     * @throws exception.KwetterServiceException deze wordt gethrowed als er
     * geen gebruiker gevonden wordt
     */
    public Gebruiker findGebruikerByName(String username) throws UserProviderException, KwetterServiceException {
        return userProvider.findGebruikerByName(username);
    }

    public List<Gebruiker> findGebruikerByNameFilter(String filter) throws UserProviderException {
        return userProvider.findGebruikerByNameFilter(filter);
    }

    public List<Gebruiker> findAllUsers() throws KwetterServiceException {
        try {
            return userProvider.findAllUsers();
        } catch (UserProviderException e) {
            throw new KwetterServiceException("Error in UserImplementation: " + e);
        }
    }

    /**
     * Maakt een nieuwe gebruiker aan en geeft hem door aan de userProvider
     *
     * @param user dit is het gebruiker object wat gecreerd wordt
     * @throws exception.UserProviderException Deze wordt gethrowed op het
     * moment als er in de UserImplementation iets fout gaat en dan kan dat een
     * SQL/Database exception zijn (Bijvoorbeeld) de user bestaat al of de naam
     * komt al voor in het systeem
     * @throws exception.KwetterServiceException
     */
    public void createUser(Gebruiker user) throws UserProviderException, KwetterServiceException {
        if (user != null) {
            userProvider.createUser(user);
        }
    }

    /**
     * Voegt een gebruiker relatie toe zodat gebruiker b gebruiker a volgt
     *
     * @param a
     * @param b
     * @throws UserProviderException
     */
    public void addFollower(Gebruiker a, Gebruiker b) throws UserProviderException {
        userProvider.addFollower(a, b);
    }

    /**
     * Vind alle volgers van de desbetreffende gebruiker
     *
     * @param username
     * @return 
     * @throws UserProviderException
     */
    public List<Gebruiker> findAllFollowing(String username) throws UserProviderException {
        if (!username.equals("")) {
            return userProvider.findAllFollowing(username);
        }
        return null;
    }

    /**
     * als de gebruiker zijn gegevens aan wilt passen gaat dat via deze methode
     * eerst wordt er gecheckt of de gebruiker uberhaupt bestaat, als hij niet
     * bestaat wordt er een exception throwed
     *
     * @param user
     * @throws exception.UserProviderException
     * @throws exception.KwetterServiceException
     */
    public void editUser(Gebruiker user) throws UserProviderException, KwetterServiceException {
        if (user != null) {
            userProvider.editUser(user);
        }
    }

    /**
     * verwijdert de gebruiker uit de database en returnt true als dit gelukt is
     *
     * @param user
     * @return returnt true als de gebruiker succesvol verwijderd is
     * @throws exception.UserProviderException
     * @throws exception.KwetterServiceException
     */
    public boolean removeUser(Gebruiker user) throws UserProviderException, KwetterServiceException {
        try {
            userProvider.removeUser(user);
            return true;
        } catch (UserProviderException e) {
            throw new KwetterServiceException("Error in UserImplementation: " + e);
        }
    }

    /**
     * Methode om een kweet toe te voegen in het systeem. Aan de kweet wordt een
     * gebruiker gelinked en de kweet wordt ook toegevoegd aan de lijst van
     * kweets van de gebruiker checkt eerst ofdat de gebruiker daadwerkelijk
     * bestaat en als die bestaat wordt alles in werking gezet
     *
     * @param user
     * @param tweet
     * @return
     * @throws exception.KweetProviderException
     * @throws exception.UserProviderException
     */
    @Interceptors(KweetInterceptor.class)
    public Kweet createKweet(Gebruiker user, Kweet tweet) throws KweetProviderException, UserProviderException {
        // CHECKEN OF GEBRUIKER IN KWEET NULL IS
        if (tweet.getGebruiker() == null) {
            tweet.setGebruiker(user);
        } // ALS DE GEBRUIKERS NIET MATCHEN IN DE TWEET
        else if (tweet.getGebruiker() != user) {
            throw new IllegalArgumentException("Users do not match for this Kweet");
        }
        // ALS DE GEBRUIKER DE KWEET NOG NIET IN ZIJN LIJST HEEFT
        if (!user.getKweets().contains(tweet)) {
            user.addKweet(tweet);
            // NIET ZEKER OVER DEZE EDIT USER
            userProvider.editUser(user);
        }
        kweetProvider.create(tweet);
        return tweet;
    }

    /**
     * Zoekt alle tweets in het systeem die er bestaan en returned die
     *
     * @return @throws exception.KweetProviderException
     * @throws exception.KwetterServiceException
     */
    public List<Kweet> findAll() throws KweetProviderException, KwetterServiceException {
        try {
            return kweetProvider.findAll();
        } catch (KweetProviderException e) {
            throw new KwetterServiceException("Error in KweetProviderException: " + e);
        }
    }

    /**
     * Returned een lijst van alle kweets van een specifieke gebruiker
     *
     * @param username
     * @return
     * @throws exception.KweetProviderException
     */
    public List<Kweet> findBy(String username) throws KweetProviderException {
        return kweetProvider.findBy(username);
    }

    /**
     *
     * @param filter
     * @return
     */
    public List<Kweet> findKweetsByFilter(String filter) throws KweetProviderException {
        return kweetProvider.findFilter(filter);
    }

    /**
     * WAAROM!?
     *
     * @return
     */
    public UserProvider getUserProvider() {
        return userProvider;
    }

    public void setUserProvider(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    public KweetProvider getKweetProvider() {
        return kweetProvider;
    }

    public void setKweetProvider(KweetProvider kweetProvider) {
        this.kweetProvider = kweetProvider;
    }
}
