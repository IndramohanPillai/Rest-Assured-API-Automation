package com.petstore.specs;

import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import com.petstore.api.PetsApiClient;
import com.petstore.api.modal.Category;
import com.petstore.api.modal.Pet;
import com.petstore.api.modal.Tags;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class PetSpecs {

    static String apiUrl;
    private String id;
     SoftAssertions softly = new SoftAssertions();

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

    @Test
    public void cRUDOperationsOnPets() throws InvalidResponseException {

        getPets_withStatusAvailable();
        createPet_withStatusAvailable();
        updatePet_WeCreated();
        deletePet_WeCreated();

    }


    //fetch the Pets with status as available
    public void getPets_withStatusAvailable() throws InvalidResponseException {

        PetsApiClient clientRequest = new PetsApiClient(apiUrl,"pet/findByStatus", "status", "available");
        Pet[] pets = clientRequest.getPets();

        softly.assertThat(pets.length).as("Checking there are pets present with status available").isGreaterThan(0);
        for(int i = 0; i< pets.length ; i++) {
            softly.assertThat(pets[i].getStatus()).as("Verifying the status of all the pets fetched is available").isEqualTo("available");
            if (softly.assertThat(pets[i].getId()) != null) {
                softly.wasSuccess();
            }else{
                softly.fail("id is null");
            }
            softly.assertAll();
        }
    }

    //Creating an available pet
    public void createPet_withStatusAvailable() throws InvalidResponseException {

        PetsApiClient client = new PetsApiClient(apiUrl,"pet/");
        Pet createdPet = client.createPet(Pet.builder().
                name("CreatedPet")
                .status("available").
                        tags(Collections.singletonList(Tags.builder()
                        .name("TagName")
                        .id(12)
                        .build()))
                .category(Category.builder()
                        .name("CategoryName")
                        .id(21)
                        .build())
                .photoUrls(Collections.singletonList("https://photos.net"))
                .build());
        id = createdPet.getId().toString();
        softly.assertThat(createdPet.getName()).as("Verify the Pet name for the created pet").isEqualTo("CreatedPet");
        softly.assertThat(createdPet.getStatus()).as("Verify the Status for the created pet").isEqualTo("available");
        softly.assertThat(createdPet.getCategory().getId()).isEqualTo(21);
        softly.assertThat(createdPet.getCategory().getName()).isEqualTo("CategoryName");
        softly.assertThat(createdPet.getTags().get(0).getId()).isEqualTo(12);
        softly.assertThat(createdPet.getTags().get(0).getName()).isEqualTo("TagName");
        softly.assertThat(createdPet.getPhotoUrls().get(0)).isEqualTo("https://photos.net");
        softly.assertAll();
        id = createdPet.getId().toString();
    }


    //Updating the status of added pet to sold
    public void updatePet_WeCreated() throws InvalidResponseException {

        System.out.println(id);
        Pet pet = getPetById(id);
        pet.setStatus("sold");
        PetsApiClient client = new PetsApiClient(apiUrl,"pet/");
        Pet createdPet = client.updatePet(pet);
        softly.assertThat(createdPet.getName()).as("Verify the Pet name for the created pet").isEqualTo("CreatedPet");
        softly.assertThat(createdPet.getStatus()).as("Verify the Status for the created pet").isEqualTo("sold");
        softly.assertThat(createdPet.getCategory().getId()).isEqualTo(21);
        softly.assertThat(createdPet.getCategory().getName()).isEqualTo("CategoryName");
        softly.assertThat(createdPet.getTags().get(0).getId()).isEqualTo(12);
        softly.assertThat(createdPet.getTags().get(0).getName()).isEqualTo("TagName");
        softly.assertThat(createdPet.getPhotoUrls().get(0)).isEqualTo("https://photos.net");
        softly.assertAll();
    }

    //Deleting the created pet
    public void deletePet_WeCreated() throws InvalidResponseException {

        PetsApiClient clientRequest = new PetsApiClient(apiUrl, "pet/"+id);
        ApiResponse<Pet> petDeleted = clientRequest.deletePet();

        softly.assertThat(petDeleted.getHttpStatusCode()).isEqualTo(200);
        softly.assertAll();
    }

    //Fetch the Pet by its ID.
    public Pet getPetById(String id) throws InvalidResponseException {
        PetsApiClient clientRequest = new PetsApiClient(apiUrl,"pet/"+id);
        Pet pet = clientRequest.getPet();
        return pet;
    }

}
