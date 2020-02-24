package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	public Stage globalStage;

	public Stage getGlobalStage() {
		return globalStage;
	}

	public void setGlobalStage(Stage globalStage) {
		this.globalStage = globalStage;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage1.fxml"));
            Parent root = loader.load();
            setGlobalStage(primaryStage);
			Scene scene = new Scene(root, 700, 400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
