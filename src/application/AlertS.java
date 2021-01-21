package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class AlertS {
	
	
		
		
		public AlertS(AlertType alertType) {
		// TODO Auto-generated constructor stub
	}	
		public static void showAlert(AlertType alertType,
										String title,
										String headerMessage,
										String contentMessage) {
			
				Alert alert = new Alert(alertType);
				alert.setTitle(title);
				alert.setHeaderText(headerMessage);
				alert.setContentText(contentMessage);
				alert.setResizable(true);
				alert.getDialogPane().setMinWidth(600);
				alert.show();
			
		}
		

}
