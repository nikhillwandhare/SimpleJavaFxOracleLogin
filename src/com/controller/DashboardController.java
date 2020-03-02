package com.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.tree.TreeNode;

import org.json.JSONArray;
import org.json.JSONObject;

import com.service.UserInformationService;

import OracleDatabase.OracleConnection;
import OracleDatabase.OracleProcess;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
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
    private ImageView profileImage;

    @FXML
    private Label cName;

    @FXML
    private Label cAddress;

    @FXML
    private Button logoutBtn;

    @FXML
    private TreeView<String> tree;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
		OracleProcess process = new OracleProcess();
		
			
			  String TreeQuery =
			  "select md.admm_module_id as id,md.admm_module_name as name,null as source,0 as parent,md.seq_no as seq "
			  +"from ad_module_mst md " +"where md.admm_status = 'E' " +"union  "
			  +"select ob.admo_object_id as id,ob.admo_object_name as name,ob.admo_source as source,ob.admo_object_module as parent,ob.admo_dis_srno as seq "
			  +"from ad_module_object ob " +"where ob.admo_status = 'E' "
			  +"order by parent,seq ";
			  
			  JSONArray treeObjects = process.executeQuery(TreeQuery);
			  System.out.println(treeObjects); 
			  
			  TreeItem<String> root = new TreeItem<String>("Forms"); 
			  Map<Integer,JSONObject> data = new HashMap<Integer,JSONObject>();
			  
			  
			  for(int i=0;i<treeObjects.length();i++) 
			  {
			  data.put(i,treeObjects.getJSONObject(i)); 
			  }
			  
			  for(int i = 0; i < data.size(); i++) 
			  { 
				  JSONObject obj = data.get(i);
				  if(obj.get("PARENT").toString().equals("0")) 
				  { 
					  TreeItem menu = new TreeItem((String)obj.get("NAME"));
					  root.getChildren().add(menu);
			  
					  for(int k = 0; k < data.size(); k++) 
					  { 
						  JSONObject obj2 = data.get(k);
						  if(obj2.get("PARENT").equals(obj.get("ID"))) 
						  {
							  TreeItem form = new TreeItem((String)obj2.get("NAME"));
							  menu.getChildren().add(form);
						  }
					  }
				  }
			  }
			  tree.setShowRoot(false);
			  tree.setRoot(root);
		
		}catch(Exception e) {e.printStackTrace();}
		
		
		logoutBtn.setOnAction(e -> {
			try {
				Stage stage1 = (Stage)logoutBtn.getScene().getWindow();
				stage1.hide();
				
				Parent root = FXMLLoader.load(getClass().getResource("/com/LoginPage1.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.show();
			} catch (IOException v) {
				v.printStackTrace();
			}
		});
		
		
	}

}
