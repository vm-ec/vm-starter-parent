package com.vm.ec.starter.config;

import com.vm.ec.starter.exception.ApiException;
import com.vm.ec.starter.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RestClientTemplate {

    private final RestClient restClient;

    public <T> T get(String url, Map<String, ?> uriVariables, ParameterizedTypeReference<T> responseType) {
        try {
            return restClient.get()
                    .uri(url, uriVariables)
                    .retrieve()
                    .body(responseType);
        } catch (HttpStatusCodeException e) {
            handleHttpException(e);
            return null; // unreachable, but required
        } catch (Exception e) {
            throw new ApiException(ErrorCode.THIRD_PARTY_API_ERROR);
        }
    }

    public <T, B> T post(String url, B requestBody, ParameterizedTypeReference<T> responseType) {
        try {
            return restClient.post()
                    .uri(url)
                    .body(requestBody)
                    .retrieve()
                    .body(responseType);
        } catch (HttpStatusCodeException e) {
            handleHttpException(e);
            return null;
        } catch (Exception e) {
            throw new ApiException(ErrorCode.THIRD_PARTY_API_ERROR);
        }
    }

    private void handleHttpException(HttpStatusCodeException e) {
        if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ApiException(ErrorCode.NOT_FOUND);
        } else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            throw new ApiException(ErrorCode.UNAUTHORIZED);
        } else {
            throw new ApiException(ErrorCode.THIRD_PARTY_API_ERROR);
        }
    }
}
