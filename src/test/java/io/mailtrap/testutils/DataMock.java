package io.mailtrap.testutils;

import lombok.Data;
import lombok.NonNull;

import java.util.Collections;
import java.util.Map;

@Data
public class DataMock {

    @NonNull
    private final String url;

    @NonNull
    private final String methodName;

    private final String requestJson;
    private final String responseJson;

    @NonNull
    private final Map<String, ?> queryParams;

    public static DataMock build(String url, String methodName, String requestJson, String responseJson) {
        return new DataMock(
                url,
                methodName,
                requestJson,
                responseJson,
                Collections.emptyMap()
        );
    }

    public static DataMock build(String url, String methodName, String requestJson, String responseJson, Map<String, ?> queryParams) {
        return new DataMock(
                url,
                methodName,
                requestJson,
                responseJson,
                queryParams
        );
    }
}