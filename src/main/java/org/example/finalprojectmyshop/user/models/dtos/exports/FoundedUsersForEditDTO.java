package org.example.finalprojectmyshop.user.models.dtos.exports;

import org.example.finalprojectmyshop.user.models.dtos.imports.SearchUserForEditDTO;

import java.util.ArrayList;
import java.util.List;

public class FoundedUsersForEditDTO {
    private List<FoundedUserForEditDTO> foundedUsersForEditDTOS;

    public FoundedUsersForEditDTO() {
        this.foundedUsersForEditDTOS = new ArrayList<>();
    }

    public List<FoundedUserForEditDTO> getFoundedUsersForEditDTOS() {
        return this.foundedUsersForEditDTOS;
    }

    public void setFoundedUsersForEditDTOS(List<FoundedUserForEditDTO> foundedUsersForEditDTOS) {
        this.foundedUsersForEditDTOS = foundedUsersForEditDTOS;
    }
}
