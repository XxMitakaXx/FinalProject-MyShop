package org.example.finalprojectmyshop.order.scheduling;

import org.example.finalprojectmyshop.order.service.SalesReportService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SalesReportScheduler {

    private final SalesReportService salesReportService;

    public SalesReportScheduler(SalesReportService salesReportService) {
        this.salesReportService = salesReportService;
    }

    @Scheduled(cron = "00 12 */1 * *")
    public void processSalesReport() {
        this.salesReportService.makeSalesReport();
    }
}
