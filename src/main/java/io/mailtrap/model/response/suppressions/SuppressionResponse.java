package io.mailtrap.model.response.suppressions;

import lombok.Data;

/**
 * Single suppression wrapped as {@code data}.
 */
@Data
public class SuppressionResponse {

    private SuppressionsResponse data;

}
