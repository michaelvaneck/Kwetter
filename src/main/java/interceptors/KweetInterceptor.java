/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptors;

import exception.KweetInterceptorException;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import model.Kweet;

/**
 *
 * @author Michael van Eck
 */
@KweetCheck
@Interceptor
public class KweetInterceptor {
    @AroundInvoke
	public Object test(InvocationContext invocationContext) throws Exception {
		Object[] parameters = invocationContext.getParameters();
                // PARAMETERS [0] = GEBRUIKER
                // PARAMETERS [1] = KWEET
                // DUS EFFE CHECKEN OF DAT HET DAADWERKELIJK EEN KWEET OBJECT IS
		if (parameters.length > 0 && parameters[1] instanceof Kweet) {
			Kweet tweet = (Kweet) parameters[1];
			String content = tweet.getText();
			content = content.replace("vet", "dik");
			content = content.replace("cool", "hard");
			tweet.setText(content);
		}		
		return invocationContext.proceed();
	}
}
