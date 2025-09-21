package stepdefs;

import Pages.FiltrationLogic;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FiltrationStepDef {
    FiltrationLogic filtrationLogic = new FiltrationLogic();

    public FiltrationStepDef() throws FileNotFoundException {
    }

    @Given("I Open the NIMBIA website")
    public void iOpenTheNIMBIAWebsite() throws InterruptedException, IOException {
        filtrationLogic.iOpenTheNIMBIAWebsite();
    }

    @Then("I select Date from option")
    public void ISelectDateFromOption(){
        filtrationLogic.validateSelectDateFrom();
    }

    @Then("I select Date to option")
    public void ISelectDateToOption(){
        filtrationLogic.validateSelectDateTo();
    }

    @Then("I select Adults option")
    public void iSelectAdultsOption() {
        filtrationLogic.iSelectAdultsOption();
    }

    @Then("I select Childerns Option")
    public void iSelectChildernsOption() {
        filtrationLogic.iSelectChildernsOption();
    }

    @Then("I click on search BTN validate the search results")
    public void iClickOnSearchBTNValidateTheSearchResults() {
        filtrationLogic.iClickOnSearchBTNValidateTheSearchResults();
    }
}
