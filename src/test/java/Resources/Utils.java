package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;

	public  RequestSpecification ReqBodyspecification() throws IOException {

		// Creating object of print stream class to pass it into logfilter
		// providing condition as in log file results are being replaced with new one so
		// providing condition will run the step once only & will use the same one for
		// next test
		if (req == null) {

			PrintStream log = new PrintStream((new FileOutputStream("logging.txt")));
			req = new RequestSpecBuilder().setBaseUri(Getglobalvalue("baseURI")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

			return req;
		}

		return req; //if condition not met then will return the original req 

	}
//utility to get the values from global.properties
	public static String Getglobalvalue(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\Resources\\Global.properties");
		prop.load(fis);

		return prop.getProperty(key);
	}
	
	//utility to get the values from respone by providing key
	public static String Getjson(Response response,String key) {
		
		 String responsestring=response.asString();
		  JsonPath js=new JsonPath(responsestring);
		  
		  return js.get(key).toString();
		
		
	}
}
