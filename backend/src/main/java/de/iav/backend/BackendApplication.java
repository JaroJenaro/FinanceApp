package de.iav.backend;

import de.iav.backend.controller.CurrentStockPriceController;
import de.iav.backend.controller.StockController;
import de.iav.backend.service.CurrentStockPriceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@SpringBootApplication
public class BackendApplication {
    private CurrentStockPriceController currentStockPriceController;

    public static void main(String[] args) {

        SpringApplication.run(BackendApplication.class, args);

    }
}
