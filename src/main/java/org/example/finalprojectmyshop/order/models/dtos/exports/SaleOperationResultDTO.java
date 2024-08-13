package org.example.finalprojectmyshop.order.models.dtos.exports;

public class SaleOperationResultDTO {
    private boolean isSuccessfully;
    private String message;

    public SaleOperationResultDTO() {}

    public SaleOperationResultDTO(boolean isSuccessfully, String message) {
        this.isSuccessfully = isSuccessfully;
        this.message = message;
    }

    public boolean isSuccessfully() {
        return this.isSuccessfully;
    }

    public void setSuccessfully(boolean successfully) {
        isSuccessfully = successfully;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
