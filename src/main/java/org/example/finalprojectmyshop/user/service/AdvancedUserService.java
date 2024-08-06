package org.example.finalprojectmyshop.user.service;

import org.example.finalprojectmyshop.user.models.dtos.exports.FoundedUsersForDeleteDTO;
import org.example.finalprojectmyshop.user.models.dtos.exports.FoundedUsersForEditDTO;

public interface AdvancedUserService {
    FoundedUsersForDeleteDTO findUsersForDelete(String email);
    FoundedUsersForEditDTO findUsersForEdit(String email);
    void deleteUser(long id);
}
