package io.mailtrap.model.request;

import io.mailtrap.model.AbstractModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ManagePermissionsRequest extends AbstractModel {

    private List<Permission> permissions;

}
