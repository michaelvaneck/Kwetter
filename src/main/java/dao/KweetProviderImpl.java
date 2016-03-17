/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import exception.KweetProviderException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Gebruiker;
import model.Kweet;

/**
 *
 * @author Michael van Eck
 */
@Stateless
public class KweetProviderImpl implements KweetProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Kweet k) throws KweetProviderException {
        em.persist(k);
    }

    @Override
    public List<Kweet> findAll() throws KweetProviderException {
        return (List<Kweet>) em.createNamedQuery("Kweet.findAll", Kweet.class).getResultList();

    }

    @Override
    public List<Kweet> findBy(String username) throws KweetProviderException {
        return (List<Kweet>) em.createQuery("select k from Kweet k WHERE k.gebruiker.screenName = :param").setParameter("param", username).getResultList();
    }

    @Override
    public List<Kweet> findFilter(String filter) throws KweetProviderException {
        return (List<Kweet>) em.createQuery("select k from Kweet k WHERE k.text LIKE %:param%").setParameter("param", filter);
    }
}
