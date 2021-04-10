package api.common;

import io.restassured.http.Method;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ApiCaller {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());       //captures logs (details) of an application

    ObjectMapper objectMapper;      //reading and writing JSON , either to and from basic POJOs

    public <T> ApiResponse<T> executeRequest(ApiRequest request, Method method, Class<T> responseType) {
        log.info("Request:");
        request.getRequest().log().uri().log().body(false);
        Response response = request.getRequest().request(method);
        log.info("Response:");
        response.then().log().status().log().body(false);
        return new CommonApiResponse<>(response, responseType, objectMapper);
    }

    public void withObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


}
