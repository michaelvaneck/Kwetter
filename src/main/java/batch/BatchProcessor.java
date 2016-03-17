/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

import exception.KweetProviderException;
import exception.KwetterServiceException;
import exception.UserProviderException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Gebruiker;
import model.Kweet;
import service.KwetterService;

/**
 *
 * @author Michael van Eck
 */
@WebServlet(urlPatterns = {"/getrekt", "/demo", "/validate"})
public class BatchProcessor extends HttpServlet {

    @Inject
    KwetterService kwetterService;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>CSV to Database Kweets</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>CSV to Database Kweets Job</h1>");

            JobOperator jo = BatchRuntime.getJobOperator();
            Properties prop = new Properties();
            prop.setProperty("import-file", "kwetterinput.json");
            jo.start("kweetjob", prop);

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();

        if (userPath.equals("/getrekt")) {
            // START DE BATCH
            processRequest(request, response);
        } /**
         *
         */
        else if (userPath.equals("/demo")) {
            try {
                // TESTEN OF HET TOEVOEGEN VAN EEN GEBRUIKER WERKT
                Gebruiker testGebruiker1 = null;
                testGebruiker1 = new Gebruiker();
                testGebruiker1.setBiography("Dit is een test");
                testGebruiker1.setWebsite("www.jesperverschuren.nl");
                testGebruiker1.setScreenName("test");
                testGebruiker1.setLocation("Huis");
                testGebruiker1.setFollowedBy(new ArrayList<Gebruiker>());
                testGebruiker1.setFollowing(new ArrayList<Gebruiker>());
                testGebruiker1.setKweets(new ArrayList<Kweet>());
                kwetterService.createUser(testGebruiker1);

                // OPHALEN ZOJUIST AANGEMAAKTE GEBRUIKER
                Gebruiker ophalen = kwetterService.findGebruikerByName(testGebruiker1.getScreenName());

                // TESTEN VAN HET AANPASSEN VAN EEN GEBRUIKER
                ophalen.setScreenName("VERANDERD");
                kwetterService.editUser(ophalen);

                // KIJKEN OF DE AANPASSING HEEFT GEWERKT?
                Gebruiker hello = kwetterService.findGebruikerByName(ophalen.getScreenName());

                // AANMAKEN VAN EEN KWEET EN DIE INTERCEPTEN
                Kweet k = new Kweet();
                k.setPostDate(new Date());
                k.setPostedFrom("TEST");
                k.setText("vet");
                k.setGebruiker(testGebruiker1);
                kwetterService.createKweet(testGebruiker1, k);

                Gebruiker ditisweereentest = kwetterService.findGebruikerByName("test");

                // HET SETTEN VAN EEN VOLGER
                Gebruiker volger = null;
                volger = new Gebruiker();
                volger.setBiography("Dit is een test");
                volger.setWebsite("www.jesperverschuren.nl");
                volger.setScreenName("VOLGER");
                volger.setLocation("Huis");
                volger.setFollowedBy(new ArrayList<Gebruiker>());
                volger.setFollowing(new ArrayList<Gebruiker>());
                volger.setKweets(new ArrayList<Kweet>());
                kwetterService.createUser(volger);

                kwetterService.addFollower(hello, volger);

                Gebruiker ophalen2 = kwetterService.findGebruikerByName(hello.getScreenName());

                System.out.println("Ik ben klaar");

            } catch (KweetProviderException ex) {
                Logger.getLogger(BatchProcessor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UserProviderException ex) {
                Logger.getLogger(BatchProcessor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KwetterServiceException ex) {
                Logger.getLogger(BatchProcessor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
