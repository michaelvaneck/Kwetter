/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.KweetProvider;
import dao.UserProvider;
import exception.KweetProviderException;
import exception.KwetterServiceException;
import exception.UserProviderException;
import java.util.ArrayList;
import java.util.Date;
import model.Gebruiker;
import model.Kweet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import service.KwetterService;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Michael van Eck
 */
@RunWith(MockitoJUnitRunner.class)
public class KwetterServiceTest {

    KwetterService service;

    @Mock
    UserProvider userProvider;

    @Mock
    KweetProvider kweetProvider;

    Gebruiker testGebruiker1;
    Gebruiker testGebruiker2;

    Kweet testKweetGebruiker1;
    Kweet testKweetGebruiker2;

    public KwetterServiceTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        service = new KwetterService();
        service.setUserProvider(userProvider);
        service.setKweetProvider(kweetProvider);

        testGebruiker1 = new Gebruiker();
        testGebruiker1.setBiography("Dit is een test");
        testGebruiker1.setWebsite("www.jesperverschuren.nl");
        testGebruiker1.setScreenName("test");
        testGebruiker1.setLocation("Huis");
        testGebruiker1.setId(1l);
        testGebruiker1.setFollowedBy(new ArrayList<Gebruiker>());
        testGebruiker1.setFollowing(new ArrayList<Gebruiker>());
        testGebruiker1.setKweets(new ArrayList<Kweet>());

        testGebruiker2 = new Gebruiker();
        testGebruiker2.setBiography("Dit is een test");
        testGebruiker2.setWebsite("www.jesperverschuren.nl");
        testGebruiker2.setScreenName("Jesper Harry Broodhoofd");
        testGebruiker2.setLocation("Huis");
        testGebruiker2.setId(2l);
        testGebruiker2.setFollowedBy(new ArrayList<Gebruiker>());
        testGebruiker2.setFollowing(new ArrayList<Gebruiker>());
        testGebruiker2.setKweets(new ArrayList<Kweet>());

        testKweetGebruiker1 = new Kweet();
        testKweetGebruiker1.setId(1l);
        testKweetGebruiker1.setPostDate(new Date());
        testKweetGebruiker1.setPostedFrom("Computer");
        testKweetGebruiker1.setText("blablabla");
        testKweetGebruiker1.setGebruiker(testGebruiker1);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAddUser() throws KwetterServiceException, UserProviderException {
        service.createUser(testGebruiker1);
        verify(userProvider, Mockito.times(1)).createUser(testGebruiker1);
    }

    @Test
    public void testAddKweet() throws KwetterServiceException, KweetProviderException, UserProviderException {
        service.createKweet(testGebruiker1, testKweetGebruiker1);
        verify(kweetProvider, Mockito.times(1)).create(testKweetGebruiker1);
    }

    @Test
    public void getAllUsers() throws KwetterServiceException, UserProviderException {
        service.findAllUsers();
        verify(userProvider, Mockito.times(1)).findAllUsers();
    }
    
    @Test
    public void getAllKweets() throws KweetProviderException, KwetterServiceException
    {
        service.findAll();
        verify(kweetProvider, Mockito.times(1)).findAll();
    }
    
    @Test
    public void testFindGebruikerByName() throws UserProviderException, KwetterServiceException{
        service.createUser(testGebruiker1);
        service.findGebruikerByName("test");
        verify(userProvider, Mockito.times(1)).findGebruikerByName("test");
    }
    
    @Test
    public void testFindKweetsByName() throws KweetProviderException, UserProviderException, KwetterServiceException
    {
        service.createUser(testGebruiker1);
        service.createKweet(testGebruiker1, testKweetGebruiker1);
        service.findBy("test");
        verify(kweetProvider, Mockito.times(1)).findBy("test");
    }
    
    @Test
    public void testFindUserPartOfName()throws UserProviderException, KwetterServiceException{
        service.createUser(testGebruiker1);
        service.findGebruikerByNameFilter("te");
        verify(userProvider, Mockito.times(1)).findGebruikerByNameFilter("te");
    }
    
    @Test
    public void testAllFollower() throws KweetProviderException, UserProviderException, KwetterServiceException
    {
        service.createUser(testGebruiker1);
        service.createUser(testGebruiker2);
        service.addFollower(testGebruiker1, testGebruiker2);
        verify(userProvider, Mockito.times(1)).addFollower(testGebruiker1, testGebruiker2);
    }
    
    @Test
    public void testGetAllFollowers() throws UserProviderException, KwetterServiceException
    {
        service.createUser(testGebruiker1);
        service.createUser(testGebruiker2);
        service.addFollower(testGebruiker1, testGebruiker2);
        service.findAllFollowing(testGebruiker1.getScreenName());
        verify(userProvider, Mockito.times(1)).findAllFollowing(testGebruiker1.getScreenName());
    }
}
