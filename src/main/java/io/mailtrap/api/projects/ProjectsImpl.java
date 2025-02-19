package io.mailtrap.api.projects;

import io.mailtrap.Constants;
import io.mailtrap.CustomValidator;
import io.mailtrap.api.apiresource.ApiResourceWithValidation;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.http.RequestData;
import io.mailtrap.model.request.projects.CreateUpdateProjectRequest;
import io.mailtrap.model.response.projects.DeleteProjectResponse;
import io.mailtrap.model.response.projects.ProjectsResponse;

import java.util.List;

public class ProjectsImpl extends ApiResourceWithValidation implements Projects {

    public ProjectsImpl(MailtrapConfig config, CustomValidator customValidator) {
        super(config, customValidator);
        this.apiHost = Constants.GENERAL_HOST;
    }

    @Override
    public ProjectsResponse createProject(long accountId, CreateUpdateProjectRequest request) {

        validateRequestBodyAndThrowException(request);

        return httpClient.post(
                String.format(apiHost + "/api/accounts/%s/projects", accountId),
                request,
                new RequestData(),
                ProjectsResponse.class
        );
    }

    @Override
    public List<ProjectsResponse> getProjects(long accountId) {
        return httpClient.getList(
                String.format(apiHost + "/api/accounts/%s/projects", accountId),
                new RequestData(),
                ProjectsResponse.class
        );
    }

    @Override
    public ProjectsResponse getProject(long accountId, long projectId) {
        return httpClient.get(
                String.format(apiHost + "/api/accounts/%s/projects/%s", accountId, projectId),
                new RequestData(),
                ProjectsResponse.class
        );
    }

    @Override
    public ProjectsResponse updateProject(long accountId, long projectId, CreateUpdateProjectRequest updateRequest) {

        validateRequestBodyAndThrowException(updateRequest);

        return httpClient.patch(
                String.format(apiHost + "/api/accounts/%s/projects/%s", accountId, projectId),
                updateRequest,
                new RequestData(),
                ProjectsResponse.class
        );
    }

    @Override
    public DeleteProjectResponse deleteProject(long accountId, long projectId) {
        return httpClient.delete(
                String.format(apiHost + "/api/accounts/%s/projects/%s", accountId, projectId),
                new RequestData(),
                DeleteProjectResponse.class
        );
    }

}
