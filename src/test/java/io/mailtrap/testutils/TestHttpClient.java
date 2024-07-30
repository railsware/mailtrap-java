package io.mailtrap.testutils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.mailtrap.Mapper;
import io.mailtrap.exception.http.HttpException;
import io.mailtrap.http.CustomHttpClient;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.AbstractModel;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestHttpClient implements CustomHttpClient {

    private final Map<String, List<DataMock>> mocks;

    public TestHttpClient(List<DataMock> mocks) {
        this.mocks = mocks.stream().collect(
                Collectors.groupingBy(
                        mock -> getRequestIdentifier(mock.getUrl(), mock.getMethodName())
                )
        );
    }

    @Override
    public <T> T get(String url, RequestData requestData, Class<T> responseType) throws HttpException {
        return this.request(url, "GET", null, requestData, responseType);
    }

    @Override
    public <T> List<T> getList(String url, RequestData requestData, Class<T> responseClassType) throws HttpException {
        JavaType responseType = TypeFactory.defaultInstance().constructCollectionType(List.class, responseClassType);
        return this.request(url, "GET", null, requestData, responseType);
    }

    @Override
    public <T> T delete(String url, RequestData requestData, Class<T> responseType) throws HttpException {
        return this.request(url, "DELETE", null, requestData, responseType);
    }

    @Override
    public <T> T head(String url, RequestData requestData, Class<T> responseType) throws HttpException {
        return this.request(url, "HEAD", null, requestData, responseType);
    }

    @Override
    public <T, V extends AbstractModel> T post(String url, V data, RequestData requestData, Class<T> responseType) throws HttpException {
        return this.request(url, "POST", data, requestData, responseType);
    }

    @Override
    public <T, V extends AbstractModel> T put(String url, V data, RequestData requestData, Class<T> responseType) throws HttpException {
        return this.request(url, "PUT", data, requestData, responseType);
    }

    @Override
    public <T, V extends AbstractModel> T patch(String url, V data, RequestData requestData, Class<T> responseType) throws HttpException {
        return this.request(url, "PATCH", data, requestData, responseType);
    }

    private <T, V extends AbstractModel> T request(String url, String methodName, V requestBody, RequestData requestData, Class<T> responseType) throws HttpException {
        return requestInternal(url, methodName, requestBody, requestData, responseType, null);
    }

    private <T, V extends AbstractModel> T request(String url, String methodName, V requestBody, RequestData requestData, JavaType responseType) throws HttpException {
        return requestInternal(url, methodName, requestBody, requestData, null, responseType);
    }

    private <T, V extends AbstractModel> T requestInternal(String url, String methodName, V requestBody, RequestData requestData, Class<T> responseClassType, JavaType responseJavaType) throws HttpException {
        try {
            String requestIdentifier = this.getRequestIdentifier(url, methodName);

            if (!this.mocks.containsKey(requestIdentifier)) {
                throw new AssertionError("No mock data for request: " + requestIdentifier);
            }

            List<DataMock> dataMocks = this.mocks.get(requestIdentifier);

            for (int i = 0; i < dataMocks.size(); i++) {
                var dataMock = dataMocks.get(i);

                Map<String, Object> urlParams = requestData.getQueryParams().entrySet().stream()
                        .filter(entry -> entry.getValue().isPresent())
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get()));
                if (!urlParams.equals(dataMock.getQueryParams())) {
                    throw new AssertionError("No match for url query parameters : " + requestIdentifier);
                }

                // request
                if (requestBody != null) {
                    if (StringUtils.isEmpty(dataMock.getRequestJson())) {
                        throw new AssertionError("No mock request body provided: " + requestIdentifier);
                    }

                    boolean sameRequests = dataMock.getRequestJson().equals(requestBody.toJson());

                    if (!sameRequests && i == dataMocks.size() - 1) {
                        throw new AssertionError("No match for request payload " + requestIdentifier);
                    } else if (!sameRequests && i < dataMocks.size() - 1) {
                        continue;
                    }

                    if (!sameRequests) {
                        throw new AssertionError("No match for request payload: " + requestIdentifier);
                    }
                }

                // handle response
                // not interested in response at all
                if (Void.class.equals(responseClassType)) {
                    return null;
                }
                if (StringUtils.isEmpty(dataMock.getResponseJson())) {
                    throw new AssertionError("No mock response body provided: " + requestIdentifier);
                }

                if (responseClassType != null) {
                    return Mapper.get().readValue(dataMock.getResponseJson(), responseClassType);
                } else if (responseJavaType != null) {
                    return Mapper.get().readValue(dataMock.getResponseJson(), responseJavaType);
                } else {
                    throw new IllegalArgumentException("Both responseType and typeReference are null");
                }
            }
        } catch (Exception e) {
            throw new AssertionError("Failed to execute mocked request", e);
        }
        return null;
    }

    private String getRequestIdentifier(String url, String methodName) {
        return methodName + "_" + url;
    }

}
