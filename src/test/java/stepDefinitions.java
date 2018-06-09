import cucumber.api.java.en.*;
import io.cucumber.datatable.DataTable;

import java.lang.*;
import java.util.Map;

import static java.lang.String.format;

public class stepDefinitions {

    @Given("I have a cucumber step")
    public void i_have_a_cucumber_step() throws Throwable {
        System.out.println("Step definition exact match");
    }

    @Given("^I have (\\d+) (.*) in my basket$")
    public void i_have_in_my_basket(int number, String veg) throws Throwable {
        System.out.println(format("I have {0} {1} in my basket", number, veg));
    }

    @Given("^I have a (?:tasty|nasty|rusty) cucumber step$")
    public void i_have_a_X_cucumber_step() throws Throwable {
        System.out.println("Step definition with a non-capturing group");
    }

    @Given("^There (?:is|are) (\\d+) (?:cats?|ox|oxen) fed by (\\d+) (?:persons?|people)$")
    public void animals_fed_by_people(int animals, int persons) throws Throwable {
        System.out.println(
                format("{0} animal(s) fed by {1} person(s)", animals, persons));
    }

    @Given ("^I have the following order$")
    public void i_have_the_following_order (DataTable table) throws Throwable {
        for (Map<Object, Object> map : table.asMaps(String.class, String.class)) {
            Object vegetable = map.get("vegetable");
            Object amount = map.get("amount");
            Object cost = map.get("cost");
            System.out.println(
                    format("Order of {0} {1}s at the cost of {2}",
                            amount, vegetable, cost));
        }
    }


}


