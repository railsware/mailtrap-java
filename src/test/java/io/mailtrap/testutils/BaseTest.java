package io.mailtrap.testutils;

import java.net.URLEncoder;
import java.nio.charset.Charset;

public class BaseTest {
    protected final Long accountId = 1L;
    protected final Long anotherAccountId = 2L;
    protected final long inboxId = 2;
    protected final Long projectId = 2L;
    protected final Long anotherProjectId = 2L;
    protected final Long messageId = 3L;
    protected final Long attachmentId = 4L;
    protected final Long accountAccessId = 5L;
    protected final Long sendingDomainId = 6L;
    protected final String email = "email@mailtrap.io";
    protected final String emailEncoded = URLEncoder.encode(email, Charset.defaultCharset());
    protected final String contactUUID = "018dd5e3-f6d2-7c00-8f9b-e5c3f2d8a132";
    protected final String contactUUIDEncoded = URLEncoder.encode(contactUUID, Charset.defaultCharset());
    protected final long importId = 1L;
}
