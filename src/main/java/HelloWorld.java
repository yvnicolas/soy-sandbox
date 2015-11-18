import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.data.SoyMapData;
import com.google.template.soy.tofu.SoyTofu;

public class HelloWorld {

	public static void main(String[] args) {
		// Bundle the Soy files for your project into a SoyFileSet.
	    SoyFileSet sfs = SoyFileSet.builder().add(new File("src/main/resources/simple.soy")).build();

	    // Compile the template into a SoyTofu object.
	    // SoyTofu's newRenderer method returns an object that can render any template in the file set.
	    SoyTofu tofu = sfs.compileToTofu();

	    // Render the template with no data.
	    System.out.println(tofu.newRenderer("examples.simple.helloWorld").render());
	    
	    
	    Map<String, String> parameterMap = new HashMap<String, String>();
	    parameterMap.put("name", "Yves");
//	    parameterMap.put("greetingWord", "Bonjour");
	    
	    // Render template with name
	    System.out.println(tofu.newRenderer("examples.simple.helloName")
	    		.setData(new SoyMapData(parameterMap)).render());
	  }

	

}
