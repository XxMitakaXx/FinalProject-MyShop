package org.example.finalprojectmyshop.order.models.dtos.exports;

import java.util.HashSet;
import java.util.Set;

public class SalesInfoDTO {
    private Set<SaleInfoDTO> salesInfos;

    public SalesInfoDTO() {
        this.salesInfos = new HashSet<>();
    }

    public Set<SaleInfoDTO> getSalesInfos() {
        return this.salesInfos;
    }

    public void setSalesInfos(Set<SaleInfoDTO> salesInfos) {
        this.salesInfos = salesInfos;
    }
}
