package org.example.finalprojectmyshop.order.models.dtos.imports;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrderDetailsDTO {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String address;

    @NotNull
    @NotEmpty
    private String collectingPlace;


}
