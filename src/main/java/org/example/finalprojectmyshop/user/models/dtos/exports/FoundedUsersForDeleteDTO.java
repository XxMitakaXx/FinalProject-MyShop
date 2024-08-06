package org.example.finalprojectmyshop.user.models.dtos.exports;

import java.util.ArrayList;
import java.util.List;

public class FoundedUsersForDeleteDTO {
    private List<FoundedUserForDeleteDTO> foundedUsersForDeleteDTOList;

    public FoundedUsersForDeleteDTO() {
        this.foundedUsersForDeleteDTOList = new ArrayList<>();
    }

    public List<FoundedUserForDeleteDTO> getFoundedUsersForDeleteDTO() {
        return this.foundedUsersForDeleteDTOList;
    }

    public void setFoundedUsersForDeleteDTOList(List<FoundedUserForDeleteDTO> foundedUsersForDeleteDTOList) {
        this.foundedUsersForDeleteDTOList = foundedUsersForDeleteDTOList;
    }
}
