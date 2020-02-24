package com;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DashboardController implements Initializable {

	private UserInformationService loggedUser;

	public UserInformationService getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(UserInformationService loggedUser) {
		this.loggedUser = loggedUser;
	}

	public Stage globalStage;

	public Stage getGlobalStage() {
		return globalStage;
	}

	public void setGlobalStage(Stage globalStage) {
		this.globalStage = globalStage;
	}

	@FXML
	private Font x1;

	@FXML
	private Color x2;

	@FXML
	private Button dashboardBtn;

	@FXML
	private Button logoutBtn;

	@FXML
	private Font x3;

	@FXML
	private Color x4;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		logoutBtn.setOnAction(e -> {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/com/LoginPage1.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				// stage.getIcons().add(new Image("/home/icons/icon.png"));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.show();
			} catch (IOException v) {
				v.printStackTrace();
			}
		});
	}

}
