package stepdefenitions;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Todos {

    public RequestSpecification httpRequest;
    public String StatusCode;
    public Response response;
    public ResponseBody body;
    public JsonObject requestParams;
    public String Id;



    @Step("я использую url {0}")
    @Given("^я использую url \"([^\"]*)\"")
    public void useApiUrl(String url) {
        RestAssured.baseURI = url;
    }

    @Step("я вызываю метод GET с path {0} ")
    @When("^я вызываю метод GET с path \"([^\"]*)\"")
    public void userCallGetApiEndpoint(String path) {
        httpRequest = RestAssured.given();
        response = httpRequest.get(path);
    }

    @Step("я вызываю метод GET с path {0} по id {1} ")
    @When("^я вызываю метод GET с path \"([^\"]*)\" по id \"([^\"]*)\"")
    public void userCallGetIdApiEndpoint(String path, String id) {
        httpRequest = RestAssured.given();
        String Path = path + "/" + id;
        response = httpRequest.get(Path);
    }

    @Step("я вызываю метод POST с path {0}")
    @When("^я вызываю метод POST с path \"([^\"]*)\"")
    public void userCallPostApiEndpoint(String path) {
        httpRequest = RestAssured.given();
        httpRequest.body(requestParams.toString());
        response = httpRequest.post(path);
        body = response.getBody();
        System.out.println(response.getBody());
        System.out.println(body.asString());
    }

    @When("я вызываю метод PUT с path {string} по id {string}")
    public void userCallPutIdApiEndpoint(String path, String id) {
        httpRequest = RestAssured.given();
        httpRequest.body(requestParams.toString());
        String Path = path + "/" + id;
        response = httpRequest.put(Path);
        body = response.getBody();
    }


    @Step("я получаю код ответа {0}")
    @Then("^я получаю код ответа \"([^\"]*)\"")
    public void responseCodeShouldBe(String code) {
        StatusCode = String.valueOf(response.getStatusCode());
        System.out.println(StatusCode);
        assertThat(StatusCode, is(equalTo(code)));
}

    @Step("тело запроса будет со следующими параметрами: {0} {1} {2}")
    @And("тело запроса будет со следующими параметрами: {} {} {}")

    public void bodyWillBe(String arg0, String arg1, String arg2) {
        requestParams = new JsonObject();
        requestParams.set("title", arg0);
        requestParams.set("completed", arg1);
        requestParams.set("userId", arg2);
        System.out.println(requestParams);
    }
    @And("в теле ответа будет id")
    public void bodyResponseWithId() {
        JsonPath jsonPath = response.jsonPath();
        Id = jsonPath.getJsonObject("id").toString();
        assertNotNull(Id);
        System.out.println(jsonPath);
    }

    @And("тело ответа соответствует JSON схеме {string}")
    public void bodyResponseWithEqual(String shema) {
//String Shema = shema;

    }
}

