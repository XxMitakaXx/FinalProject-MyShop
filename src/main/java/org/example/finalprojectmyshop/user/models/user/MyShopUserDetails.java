package org.example.finalprojectmyshop.user.models.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MyShopUserDetails extends User {

    private final String firstName;
    private final String lastName;

    public MyShopUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String firstName, String lastName) {
        super(username, password, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();

        if (this.firstName != null) {
            fullName.append(this.firstName);
        }

        if (this.lastName != null) {
            if (!fullName.isEmpty()) {
                fullName.append(" ");
            }

            fullName.append(this.lastName);
        }

        return fullName.toString();
    }
}
