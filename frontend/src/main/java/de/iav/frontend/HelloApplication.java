package de.iav.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/de/iav/frontend/controller/startPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Finance App!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.ALL);

        //logger.severe("Schwerwiegender Fehler");
        //logger.warning("Warnung");
        //logger.info("Information");
        logger.config("Konfigurationshinweis");
        logger.fine("Fein");
        logger.finer("Feiner");
        logger.finest("Am feinsten");

        launch();
    }
}