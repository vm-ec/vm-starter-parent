package com.vm.ec.starter.config;

import com.vm.ec.starter.exception.ApiException;
import com.vm.ec.starter.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RestClientTemplate {

    private final RestClient restClient;

    public <T> T get(String url, Map<String, ?> uriVars, ParameterizedTypeReference<T> responseType) {
        return executeRequest(url, HttpMethod.GET, null, uriVars, responseType);
    }

    public <T, B> T post(String url, B body, ParameterizedTypeReference<T> responseType) {
        return executeRequest(url, HttpMethod.POST, body, null, responseType);
    }

    public <T, B> T put(String url, B body, ParameterizedTypeReference<T> responseType) {
        return executeRequest(url, HttpMethod.PUT, body, null, responseType);
    }

    public <T, B> T patch(String url, B body, ParameterizedTypeReference<T> responseType) {
        return executeRequest(url, HttpMethod.PATCH, body, null, responseType);
    }

    public <T> T delete(String url, Map<String, ?> uriVars, ParameterizedTypeReference<T> responseType) {
        return executeRequest(url, HttpMethod.DELETE, null, uriVars, responseType);
    }

    private <T, B> T executeRequest(String url,
                                    HttpMethod method,
                                    B body,
                                    Map<String, ?> uriVars,
                                    ParameterizedTypeReference<T> responseType) {
        try {
            RestClient.RequestBodyUriSpec request = restClient.method(method);

            RestClient.ResponseSpec responseSpec = (body != null)
                    ? request.body(body).retrieve()
                    : request.retrieve();

            return responseSpec.body(responseType);
        } catch (HttpStatusCodeException e) {
            handleHttpException(e);
            return null;
        } catch (Exception e) {
            throw new ApiException(ErrorCode.THIRD_PARTY_API_ERROR);
        }
    }

    private void handleHttpException(HttpStatusCodeException e) {
        HttpStatusCode status = e.getStatusCode();
        switch (status) {
            case HttpStatus.NOT_FOUND -> throw new ApiException(ErrorCode.NOT_FOUND);
            case HttpStatus.UNAUTHORIZED -> throw new ApiException(ErrorCode.UNAUTHORIZED);
            case HttpStatus.BAD_REQUEST -> throw new ApiException(ErrorCode.BAD_REQUEST);
            default -> throw new ApiException(ErrorCode.THIRD_PARTY_API_ERROR);
        }
    }
}
