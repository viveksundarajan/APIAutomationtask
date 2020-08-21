package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.*;;

@RunWith(Cucumber.class)
@CucumberOptions(features="src//test//java//features", glue= "stepDefinations",
plugin = { "pretty", "json:target/jsonReports/cucumber-reports.json" }, monochrome=true)
public class TestRunner{
	
	

}
