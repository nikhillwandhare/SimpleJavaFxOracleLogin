package com;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

public class MainRootController implements Initializable {

	@FXML
	StackPane mainRoot;

	private HostServices hostServices;

	public HostServices getHostServices() {
		return hostServices;
	}

	public void setHostServices(HostServices hostServices) {
		this.hostServices = hostServices;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			mainRoot.getChildren().add(FXMLLoader.load(getClass().getResource("LoginPage1.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
