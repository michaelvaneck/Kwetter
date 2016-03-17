/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package batch;

import java.util.List;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import model.Kweet;
import service.KwetterService;

/**
 *
 * @author Michael van Eck
 */
@Dependent
@Named
public class kweetwriter extends AbstractItemWriter {

    @Inject
    KwetterService kwetterService;

    @Override
    public void writeItems(List<Object> list) throws Exception {
        // HIER KOMT DE LIJST BINNEN VAN DE DOMIJN KWEETS
        if (list != null) {
            for (Object o : list) {
                System.out.println("--> PERSISTING KWEET TO DB <--");
                SendObject j = (SendObject) o;
                kwetterService.createKweet(j.getGebruiker(), j.getKweet());
            }
        }

    }
}
