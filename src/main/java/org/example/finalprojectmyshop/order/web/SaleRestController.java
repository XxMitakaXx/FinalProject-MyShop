package org.example.finalprojectmyshop.order.web;

import org.example.finalprojectmyshop.order.models.dtos.exports.SaleInfoDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.SaleOperationResultDTO;
import org.example.finalprojectmyshop.order.models.dtos.exports.SalesInfoDTO;
import org.example.finalprojectmyshop.order.models.entities.Sale;
import org.example.finalprojectmyshop.order.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales-api")
public class SaleRestController {

    private final String SALE_DELETE_SUCCESSFULLY = "Sale with id: %d. Deleted successfully.";
    private final String SALE_DELETE_UNSUCCESSFULLY = "Sale with id: %d. Deleted unsuccessfully.";

    private final SaleService saleService;

    public SaleRestController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<SalesInfoDTO> salesInfo() {
        SalesInfoDTO salesInfo = this.saleService.findSalesInfo();


        return ResponseEntity.ok(salesInfo);
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<SaleOperationResultDTO> deleteSale(@PathVariable long id) {
//        boolean isDeleted = this.saleService.deleteSale(id);
//
//        if (isDeleted) {
//            String message = String.format(SALE_DELETE_SUCCESSFULLY, id);
//
//            return ResponseEntity.ok(new SaleOperationResultDTO(true, message));
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//    }
}
