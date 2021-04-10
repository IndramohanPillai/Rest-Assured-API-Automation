package com.petstore.api;

import api.common.ApiClient;
import api.common.ApiRequest;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import com.google.gson.GsonBuilder;
import com.petstore.api.modal.Pet;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

public class PetsApiClient extends ApiClient {

    public PetsApiClient(String baseUrl, String basePathOrders) {
        super(baseUrl, basePathOrders);

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));

    }

    public PetsApiClient(String baseUrl, String basePathOrders, String parameterName, String parameterValue) {
        super(baseUrl, basePathOrders, parameterName, parameterValue);

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));

    }

    public Pet[] getPets() throws InvalidResponseException {

        ApiResponse<Pet[]> response = caller.executeRequest(getRequest(), Method.GET, Pet[].class);
        return response.getContent();

    }

    public Pet getPet() throws InvalidResponseException {

        ApiResponse<Pet> response = caller.executeRequest(getRequest(), Method.GET, Pet.class);
        return response.getContent();

    }



    public ApiResponse<Pet> deletePet() {
        ApiResponse<Pet> response = caller.executeRequest(getRequest(), Method.DELETE, Pet.class);
        return response;
    }

    public Pet createPet(Pet pet) throws InvalidResponseException {

        ApiRequest request = getRequest().withBody(pet).withHeader("Content-Type", "application/json");
        ApiResponse<Pet> response = caller.executeRequest(request, Method.POST, Pet.class);
        return response.getContent();
    }

    public Pet updatePet(Pet pet) throws InvalidResponseException {

        ApiRequest request = getRequest().withBody(pet).withHeader("Content-Type", "application/json");
        ApiResponse<Pet> response = caller.executeRequest(request, Method.PUT, Pet.class);
        return response.getContent();
    }
}
