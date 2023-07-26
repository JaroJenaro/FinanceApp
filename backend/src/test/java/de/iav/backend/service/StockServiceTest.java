//package de.iav.backend.service;
//
//import de.iav.backend.exception.UserNotFoundException;
//import de.iav.backend.model.Stock;
//import de.iav.backend.model.User;
//import de.iav.backend.repository.StockRepository;
//import de.iav.backend.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class StockServiceTest {
//    StockRepository stockRepository = mock(StockRepository.class);
//    StockService stockService = new StockService(stockRepository);
//    @Test
//    void getStockById_whenExistingId_thenReturnStock() {
//        //GIVEN
//        String expectedStockId = "US0231351067";
//        Stock expectedStock = new Stock(expectedStockId, "906866", "AMZN", "Amazon.com, Inc.");
//        when(stockRepository.findById(expectedStockId)).thenReturn(Optional.of(expectedStock));
//
//
//        //WHEN
//        Optional<Stock> actualStock = stockService.getStockById(expectedStockId);
//
//        //THEN
//        assertEquals(expectedStock, actualStock.get());
//        // Sicherstellen, dass getProductById auch WIRKLICH aufgerufen wurde
//        verify(stockRepository).findById(any());
//
//    }
//    @Test
//    void listStocks_whenNoStocksExist_thenReturnEmptyList() {
//        //GIVEN
//        List<Stock> expectedStocks = new ArrayList<>();
//        when(stockRepository.findAll()).thenReturn(expectedStocks);
//
//        //WHEN
//        List<Stock> actualStocks= stockService.getAllStocks();
//
//        //THEN
//        assertEquals(expectedStocks, actualStocks);
//        verify(stockRepository).findAll();
//    }
//
//}