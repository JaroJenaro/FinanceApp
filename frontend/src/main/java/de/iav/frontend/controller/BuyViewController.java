package de.iav.frontend.controller;

import de.iav.frontend.model.*;
import de.iav.frontend.service.SceneSwitchService;
import de.iav.frontend.service.StockService;
import de.iav.frontend.service.TransactionService;
import de.iav.frontend.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.time.LocalDateTime;

public class BuyViewController {

    private final StockService stockService = StockService.getInstance();
    private final UserService userService = UserService.getInstance();
    private final TransactionService transactionService = TransactionService.getInstance();
    private final SceneSwitchService sceneSwitchService = SceneSwitchService.getInstance();
    @FXML
    public ListView<Stock> lv_stocks;

    @FXML
    public ComboBox<User> cb_users;

    private User user;
    @FXML
    public TextField tf_quantity;
    @FXML
    public TextField tf_price;
    @FXML
    public TextField tf_sum;
    @FXML
    public Button buyButton;
    @FXML
    public Button sellButton;

    int quantity;
    double price;

    //public FXCollections observableArrayList;


    public void initialize() {
        showAllStocks();
    }


    public void showAllStocks() {
        lv_stocks.getItems().clear();
        System.out.println("showAllStocks");
        cb_users.getItems().addAll(userService.getAllUsers());
        System.out.println("showAllStocks");
        lv_stocks.getItems().addAll(stockService.getAllStocks());
        System.out.println("showAllUsers"+stockService.getAllStocks());


        lv_stocks.getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, stock, t1) -> {
                    if (lv_stocks.getSelectionModel() != null) {
                        buyButton.setDisable(false);
                    }
                });
    }

/*    private PortfolioViewController portfolioViewController;

    // Inject the PortfolioViewController instance
    @FXML
    public void setPortfolioViewController(PortfolioViewController portfolioViewController) {
        this.portfolioViewController = portfolioViewController;}*/

    @FXML
    public void calculateSum(ActionEvent event) {
        System.out.println("calculateSum");
        quantity = Integer.parseInt(tf_quantity.getText());
        price = Double.parseDouble(tf_price.getText());
        double sum = quantity * price;
        tf_sum.setText(Double.toString(sum));
    }

    @FXML
    public void doBuyStockTransaction(ActionEvent event) {

        quantity = Integer.parseInt(tf_quantity.getText());
        price = Double.parseDouble(tf_price.getText());

        if (lv_stocks.getSelectionModel().getSelectedItem() != null &&
                cb_users.getSelectionModel().getSelectedItem() != null &&
                quantity > 0 &&
                price > 0
        ) {
            System.out.println("Bereit zum Kaufen User: " + cb_users.getSelectionModel().getSelectedItem());
            System.out.println("kauft " + quantity + " Aktien von " + lv_stocks.getSelectionModel().getSelectedItem() + " zum Preis von " + price);
            System.out.println(" für insgesamt " + tf_sum.getText());
            TransactionWithoutIdDTO transactionDTO = new TransactionWithoutIdDTO(TransactionType.BUY,
                    LocalDateTime.now().toString(), cb_users.getSelectionModel().getSelectedItem(), lv_stocks.getSelectionModel().getSelectedItem(), quantity, price);
            Transaction buyTransaction = transactionService.addTransaction(transactionDTO);

            System.out.println("buyTransaction: " + buyTransaction + "ausgeführt");
        } else {
            System.out.println("Transaktion nicht möglich da kein Stock is selected.");
        }
    }
    @FXML
    public void doSceneChange(ActionEvent event) throws IOException {
        sceneSwitchService.switchToSellViewController(event, cb_users.getSelectionModel().getSelectedItem());

    }

    public void backToPortfolioScene(ActionEvent event) throws IOException {

        //sceneSwitchService.switchToPortfolioScene(event, cb_users.getSelectionModel().getSelectedItem());
        sceneSwitchService.switchToPortfolioScene(event, user);
    }

    public void setUserForBuying(User user) {
        this.user = user;
    }

    public void stockChanged(MouseEvent mouseEvent) {
        System.out.println(lv_stocks.getSelectionModel().getSelectedItem());
        price = stockService.getStockPrice(lv_stocks.getSelectionModel().getSelectedItem().stockTicker());
        System.out.println("Price: " + price);
        tf_price.setText(String.valueOf(price));

    }

    public void stockChangedKey(KeyEvent keyEvent) {
        System.out.println("test");
        if (keyEvent.getCode() == KeyCode.getKeyCode("s"))
            System.out.println(lv_stocks.getSelectionModel().getSelectedItem());
    }
}