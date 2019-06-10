package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.NamingSecurityException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class RootController implements Initializable {
	public Stage primaryStage;

	@FXML
	private TextField textID;
	@FXML
	private PasswordField textPassword;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnClose;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		textPassword.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				handleBtnLoginAction();
			} else if (event.getCode().equals(KeyCode.ESCAPE)) {
				handleBtnCloseAction();
			}
		});
		textID.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ESCAPE)) {
				handleBtnCloseAction();
			}
		});

		btnLogin.setOnAction(event -> {
			handleBtnLoginAction();
		});
		btnClose.setOnAction(event -> {
			handleBtnCloseAction();
		});

	}

	private void handleBtnCloseAction() {
		primaryStage.close();

	}

	private void handleBtnLoginAction() {
		if (!(textID.getText().equals("asdf") && textPassword.getText().equals("1"))) {
			callAlert("�α��� ����:���̵�, �н����带 Ȯ���ϼ���");
			textID.clear();
			textPassword.clear();
			return;
		}
		try {
			Stage mainStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/phone.fxml"));
			mainStage.setTitle("�޴��� ����� ���α׷�");
			Parent root = loader.load();

			MainController mainController = loader.getController();
			mainController.mainStage = mainStage;

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/main.css").toString());
			mainStage.setScene(scene);
			primaryStage.close();
			mainStage.show();

			//callAlert("ȭ����ȯ����:����ȭ������ ��ȯ�Ǿ����ϴ�");

		} catch (Exception e) {
			callAlert("ȭ����ȯ����:����ȭ������ ��ȯ����");
			e.printStackTrace();
		}

	}

// ��Ÿ: �˸�â(�߰��� �ݷ��� ������.  ����: "��������: ���� ����� �Է����ּ���"
	private void callAlert(String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("�˸�â");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":") + 1));
		alert.showAndWait();
	}

}
