package com.soysandbox;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.tofu.SoyTofu;

public class HelloWorld {

	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(HelloWorld.class);
		
		DynMailSMSServiceImpl mailService = new DynMailSMSServiceImpl();
		
		// Bundle the Soy files for your project into a SoyFileSet.
	    SoyFileSet sfs = SoyFileSet.builder().add(new File("src/main/resources/mailtemp.soy")).build();

	    // Compile the template into a SoyTofu object.
	    // SoyTofu's newRenderer method returns an object that can render any template in the file set.
	    SoyTofu tofu = sfs.compileToTofu();

//	   Prepare the template data 
	    
	    Map<String, String> parameterMap = new HashMap<String, String>();
	    parameterMap.put("name", "Yves Nicolas");
	    parameterMap.put("texte", "Vous venez de souscrire un abonnement Dynamease et vous avez bien raison");
//	    parameterMap.put("greetingWord", "Bonjour");
	    
	    // Render template with name
	   String message = tofu.newRenderer("mail.simple.helloName")
	    		.setData(new SoyMapData(parameterMap)).render();
	   
	   mailService.sendMail("yves.nicolas@yvni.com", "Bienvenue chez Dynamease", message);
	   
	   logger.debug("finished. Message produced : ");
	   logger.debug("{}", message);
	  }

	

}
