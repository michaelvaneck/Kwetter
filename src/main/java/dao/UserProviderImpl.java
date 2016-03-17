/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.UserProviderException;
import model.Gebruiker;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Michael van Eck
 */
@Stateless
public class UserProviderImpl implements UserProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Gebruiker findGebruikerByName(String screenName) throws UserProviderException {
        try {
            Query query = em.createNamedQuery("findGebruikerByName").setParameter("screenName", screenName);
            if (query.getResultList().isEmpty()) {
                return null;
            } else {
                return (Gebruiker) query.getSingleResult();
            }
        } catch (Exception e) {
            throw new UserProviderException(e);
        }
    }

    @Override
    public void createUser(Gebruiker user) throws UserProviderException {
        em.persist(user);
    }

    @Override
    public void editUser(Gebruiker user) throws UserProviderException {
        em.merge(user);
    }

    @Override
    public void removeUser(Gebruiker user) throws UserProviderException {
        em.remove(user);
    }

    @Override
    public List<Gebruiker> findAllUsers() throws UserProviderException {
        return (List<Gebruiker>) em.createNamedQuery("Gebruiker.findAll", Gebruiker.class).getResultList();
    }

    @Override
    public List<Gebruiker> findAllFollowing(String username) throws UserProviderException {
        Gebruiker source = this.findGebruikerByName(username);
        return source.getFollowing();
    }

    /**
     * countFollowers returned een int aan de hand van de hoeveelheid mensen die
     * de gebruiker VOLGEN
     *
     * @param user
     * @return
     * @throws UserProviderException
     */
    @Override
    public int countFollowers(Gebruiker user) throws UserProviderException {
        return user.getFollowedBy().size();
    }

    /**
     * countFollowing returned een int aan de hand van de hoeveelheid mensen die
     * de gebruiker VOLGT
     *
     * @param user
     * @return
     * @throws UserProviderException
     */
    @Override
    public int countFollowing(Gebruiker user) throws UserProviderException {
        return user.getFollowing().size();
    }

    @Override
    public List<Gebruiker> findGebruikerByNameFilter(String filter) throws UserProviderException {
        try {
            Query query = em.createNamedQuery("findGebruikersByFilter").setParameter("filter", filter);
            return (List<Gebruiker>) query.getResultList();
        } catch (Exception e) {
            throw new UserProviderException(e);
        }
    }

    @Override
    public boolean addFollower(Gebruiker user, Gebruiker follower) throws UserProviderException {
        for (Gebruiker follow : user.getFollowedBy()) {
            if (follow.getScreenName().equals(follower.getScreenName())) {
                return false;
            }
        }
        user.addFollower(follower);
        return true;
    }
}
