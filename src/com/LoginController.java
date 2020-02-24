package com;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import org.json.JSONArray;

import OracleDatabase.OracleConnection;
import OracleDatabase.OracleProcess;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	public Stage globalStage;

	public Stage getGlobalStage() {
		return globalStage;
	}

	public void setGlobalStage(Stage globalStage) {
		this.globalStage = globalStage;
	}
	
	private UserInformationService loggedUser;
	
	public UserInformationService getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(UserInformationService loggedUser) {
		this.loggedUser = loggedUser;
	}

	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	private Button loginBtn;

	@FXML
	private Button clearBtn;

	@FXML
	private Text msgtext;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		OracleConnection cc = new OracleConnection();
		clearBtn.setOnAction(e -> {
			userName.setText("");
			password.setText("");
			msgtext.setText("");
		});

		loginBtn.setOnAction(e -> {
			
			try {
			OracleProcess ora = new OracleProcess();
			/*JSONArray arr= ora.executeQuery("select u.adum_user_id,u.adum_user_code,u.adum_user_name from ad_user_mst u where u.adum_status = 'E'");
			System.out.println("HashMap>>"+arr);*/
			UserInformationService info= ora.checkUserLogin(userName.getText(),password.getText());
			setLoggedUser(info);
			
			if (info.getADUM_USER_ID() > 0) {
				
				System.out.println("Name>>"+info.getADCM_NAME());
				msgtext.setText("Welcome " + info.getADCM_NAME());
				
				Stage stage = (Stage)loginBtn.getScene().getWindow();
				stage.hide();
				loadStage("/com/Dashboard.fxml");

			} else {
				msgtext.setText("Invalid User Name or Password....");
			}
			}catch(Exception s) {s.printStackTrace();};
		});
	}
	
    private void loadStage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            //stage.getIcons().add(new Image("/home/icons/icon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
