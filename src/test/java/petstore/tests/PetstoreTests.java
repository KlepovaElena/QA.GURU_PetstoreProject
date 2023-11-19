package petstore.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import petstore.data.TestData;
import petstore.models.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static petstore.specs.Specs.*;

@Owner("Klepova Elena")
@Feature("Pets management")
@Tags({@Tag("api"), @Tag("pet")})

public class PetstoreTests extends TestBase{

    TestData testData = new TestData();

    @Test
    @DisplayName("Testing of successful pet creation")
    @Story("Pet creation")
    @Severity(SeverityLevel.CRITICAL)
    void successfulAddPetTest() {

        CreatePetModel pet = new CreatePetModel();
        pet.setId(55);
        pet.setName(testData.name);
        pet.setStatus(testData.status);

        CreatePetResponseModel response = step("Create a new pet", () ->
        given(requestSpec)
                .body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(createPetResponse200Spec)
                .extract().as(CreatePetResponseModel.class));

        step("Verify response", () -> {
            assertThat(response.getId()).isEqualTo(55);
            assertThat(response.getName()).isEqualTo(testData.name);
            assertThat(response.getStatus()).isEqualTo(testData.status);
        });
    }

    @Test
    @DisplayName("Testing of successful pet finding")
    @Story("Pet finding")
    @Severity(SeverityLevel.CRITICAL)
    void successfulGetPetTest() {

        successfulAddPetTest();

        CreatePetResponseModel response = step("Get an existing pet", () ->
        given(requestSpec)
                .when()
                .get("/pet/55")
                .then()
                .spec(createPetResponse200Spec)
                .extract().as(CreatePetResponseModel.class));

        step("Verify response", () -> {
            assertThat(response.getId()).isEqualTo(55);
            assertThat(response.getName()).isEqualTo(testData.name);
            assertThat(response.getStatus()).isEqualTo(testData.status);
        });
    }

    @Test
    @DisplayName("Testing of successful pet deletion")
    @Story("Pet deletion")
    @Severity(SeverityLevel.CRITICAL)
    void successfulDeletePetTest() {

        successfulAddPetTest();


        DeletePetResponseModel response = step("Delete an existing pet", () ->
        given(requestSpec)
                .when()
                .delete("/pet/55")
                .then()
                .spec(createPetResponse200Spec)
                .extract().as(DeletePetResponseModel.class));

        step("Verify response", () -> {
            assertThat(response.getCode()).isEqualTo(200);
            assertThat(response.getMessage()).isEqualTo("55");
            assertThat(response.getType()).isEqualTo("unknown");
        });
    }

    @Test
    @DisplayName("Testing of unsuccessful pet deletion when pet does not exist")
    @Story("Pet deletion")
    @Severity(SeverityLevel.NORMAL)
    void unsuccessfulDeletePetTest() {

        step("Delete a pet that does not exist", () ->
        given(requestSpec)
                .when()
                .delete("/pet/550")
                .then()
                .spec(response404Spec));
    }

    @Test
    @DisplayName("Testing of unsuccessful pet finding when pet does not exist")
    @Story("Pet finding")
    @Severity(SeverityLevel.NORMAL)
    void unsuccessfulGetPetTest() {

        GetPetResponseModel response = step("Get a pet that does not exist", () ->
        given(requestSpec)
                .when()
                .get("pet/404")
                .then()
                .spec(response404Spec)
                .extract().as(GetPetResponseModel.class));

        step("Verify response", () -> {
            assertThat(response.getCode()).isEqualTo(1);
            assertThat(response.getMessage()).isEqualTo("Pet not found");
            assertThat(response.getType()).isEqualTo("error");
        });
    }
}