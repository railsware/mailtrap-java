package io.mailtrap.api.companyinfo;

import io.mailtrap.model.request.companyinfo.CreateCompanyInfoRequest;
import io.mailtrap.model.request.companyinfo.UpdateCompanyInfoRequest;
import io.mailtrap.model.response.companyinfo.CompanyInfoResponse;

public interface CompanyInfo {

    /**
     * Get the company info associated with a sending domain
     *
     * @param sendingDomainId unique domain ID
     * @return company info of the sending domain
     */
    CompanyInfoResponse getCompanyInfo(long sendingDomainId);

    /**
     * Create the company info for a sending domain. Company info is required for
     * domain compliance verification.
     *
     * @param sendingDomainId unique domain ID
     * @param request         request data
     * @return created company info
     */
    CompanyInfoResponse createCompanyInfo(long sendingDomainId, CreateCompanyInfoRequest request);

    /**
     * Update the company info for a sending domain
     *
     * @param sendingDomainId unique domain ID
     * @param request         request data, only the fields set are sent
     * @return updated company info
     */
    CompanyInfoResponse updateCompanyInfo(long sendingDomainId, UpdateCompanyInfoRequest request);
}
