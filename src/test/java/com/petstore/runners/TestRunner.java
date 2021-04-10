package com.petstore.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "C:\\Users\\3739739\\Documents\\PetclinicRestAssured-master\\PetStoreRestAssured\\src\\test\\resources\\Functional\\End2End_Test.feature"
)
public class TestRunner {

}
