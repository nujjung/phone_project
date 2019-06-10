package Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import Model.Client;
import Model.OutPhone;
import Model.Phone;
import Model.RegPhone;
import Model.Stock;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {

//**********************ù��° ȭ��***********************

//*******************************************************	

	@FXML	private Tab tabStock;
	@FXML	private Tab tabIn;
	@FXML	private Tab tabOut;
	@FXML	private Tab tabModel;
	@FXML	private Tab tabClient;
	@FXML	private Tab tabChart;
	@FXML	private TableView<Stock> stockTableView;
	@FXML	private Button inBtnReg;
	@FXML	private ComboBox<String> inCmbCompany;
	@FXML	private ComboBox<String> inCmbModel;
	@FXML	private ComboBox<String> inCmbColor;
	@FXML	private DatePicker inDatePicker;
	@FXML	private TextField inTextSerial;
	@FXML	private TextField inTextMemo;
	@FXML	private TableView<RegPhone> inTableView;
	@FXML	private ComboBox<String> outCmbClient;
	@FXML	private ComboBox<String> outCmbCompany;
	@FXML	private ComboBox<String> outCmbModel;
	@FXML	private ComboBox<String> outCmbColor;
	@FXML	private ComboBox<String> outCmbSerial;
	@FXML	private DatePicker outDatePicker;
	@FXML	private TextField outTextMemo;
	@FXML	private Button outBtnOut;
	@FXML	private TableView<OutPhone> outTableView;
	@FXML	private Button clientBtnReg;
	@FXML	private Button clientBtnEdit;
	@FXML	private Button clientBtnDel;
	@FXML	private TableView<Client> clientTableView;
	@FXML	private TextField regTextCompany;
	@FXML	private TextField regTextModel;
	@FXML	private TextField regTextColor;
	@FXML	private TextField regTextPrice;
	@FXML	private DatePicker regDatePicker;
	@FXML	private Button regBtnReg;
	@FXML	private Button regBtnDel;
	@FXML	private Button regBtnImage;
	@FXML	private ImageView regImage;
	@FXML	private TableView<Phone> regTableView;
	@FXML	private TextField clientName;
	@FXML	private TextField clientCeo;
	@FXML	private TextField clientAddress;
	@FXML	private TextField clientPhone;
	@FXML	private DatePicker clientDatePicker;
	@FXML	private BarChart barChart;

	public Stage mainStage;
	ObservableList<Stock> stockData = FXCollections.observableArrayList();
	ObservableList<Stock> newStockData = FXCollections.observableArrayList();
	ObservableList<RegPhone> regPhoneData = FXCollections.observableArrayList();
	ObservableList<OutPhone> outPhoneData = FXCollections.observableArrayList();
	ObservableList<Phone> modelData = FXCollections.observableArrayList();
	ObservableList<Client> clientData = FXCollections.observableArrayList();
	ObservableList<String> inCompany = FXCollections.observableArrayList();
	ObservableList<String> inModel = FXCollections.observableArrayList();
	ObservableList<String> inColor = FXCollections.observableArrayList();
	ObservableList<String> outClient = FXCollections.observableArrayList();
	ObservableList<String> outCompany = FXCollections.observableArrayList();
	ObservableList<String> outModel = FXCollections.observableArrayList();
	ObservableList<String> outColor = FXCollections.observableArrayList();
	ObservableList<String> outSerial = FXCollections.observableArrayList();
	ArrayList<Stock> stockArrayList;
	ArrayList<RegPhone> phoneArrayList;
	ArrayList<OutPhone> outPhoneArrayList;
	ArrayList<Phone> regPhoneArrayList;
	ArrayList<Client> clientArrayList;

	private File imageDir = new File("c:/phoneimages");
	private String fileName = "";
	File selectFile = null;
	Phone phone = null;
	RegPhone regPhone = null;
	OutPhone outPhone = null;
	Stock stock = null;
	private Client selectClient;
	private int clientIndex;
	private Phone selectPhone;
	private int phoneIndex;
	private Phone selectPhoneTable;
	private int selectPhoneTableIndex;
	private Phone selectModel;
	private int modelIndex;

	boolean flag = false;
	int check = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// 0. �����ͺ��̽� ���ӿ�û��ȣ
		Connection con = DBUtility.getConnection();

		// ��� ���̺�� ����
		setStockTableView();
		// �԰� ���̺�� ����
		setInTableView();
		// ��� ���̺�� ����
		setOutTableView();
		// �𵨵�� ���̺�� ����
		setRegTableView();
		// �ŷ�ó ���̺�� ����
		setClientTableView();

		inputPhoneNumberFormat(clientPhone);
		inputPriceFormat(regTextPrice);

		clientBtnEdit.setDisable(true);
		// ��� �� ȭ�� ����////////////
		tabStock.setOnSelectionChanged(event -> {
			stockData.clear();
			PhoneDAO.truncateStock();

			stockTableView.setItems(stockData);
			stockTableView.refresh();
			ArrayList<Stock> stockArray = PhoneDAO.DBtoStockData();
			for (Stock stock2 : stockArray) {
				stock = new Stock(
						stock2.getCompany(), 
						stock2.getModel(), 
						stock2.getColor(), 
						stock2.getQuantity());

				stockData.add(stock);
			}
			for (Stock stockValue : stockData) {
				int count3 = PhoneDAO.makeStockData(stockValue);
				if (count3 != 0) {

				}
			}

		});

		// 1. �԰��ǿ��� ��Ϲ�ư�� ������ ��
		inBtnReg.setOnAction(event -> {			inBtnRegAction();		});

		// 2. ��� �ǿ��� ����ư�� ���� ��
		outBtnOut.setOnAction(event -> {			outBtnRegAction();		});

		// 3. �𵨰��� �ǿ��� ��Ϲ�ư�� ������ ��
		regBtnReg.setOnAction(event -> {			regBtnRegAction();		});
		// �𵨰��� �ǿ��� �̹������� ��ư�� ���� ��
		regBtnImage.setOnAction(e -> {			regBtnImageAction();		});

		// �𵨰��� ���̺�並 Ŭ������ �� ó���ϴ� �Լ�(�ѹ�Ŭ�� : ������ �����ְ� ���� ������ ���·� ��ȯ )
		regTableView.setOnMouseClicked(event -> {
			selectPhoneTable = regTableView.getSelectionModel().getSelectedItem();
			selectPhoneTableIndex = regTableView.getSelectionModel().getSelectedIndex();
			if (event.getClickCount() == 1) {

				regTextCompany.setText(selectPhoneTable.getCompany());
				regTextModel.setText(selectPhoneTable.getModel());
				regTextColor.setText(selectPhoneTable.getColor());
				regTextPrice.setText(String.valueOf(selectPhoneTable.getPrice()));
				regDatePicker.setValue(LocalDate.parse(selectPhoneTable.getIndate()));
				Image image = new Image("file:///" + imageDir.getAbsolutePath() + "/" + selectPhoneTable.getImage());
				regImage.setImage(image);

			}
		});

		// �𵨰��� �ǿ��� ���� ��ư�� ���� ��
		regBtnDel.setOnAction(event -> {
			regBtnDelAction();
		});

		// 4. �ŷ�ó�������� ��Ϲ�ư�� ���� ��
		clientBtnReg.setOnAction(event -> {			clientBtnRegAction();		});
		// �ŷ�ó�ǿ��� ������ Ŭ���� ��
		clientBtnEdit.setOnAction(event -> {			clientBtnEditAction();		});
		// �ŷ�ó�ǿ��� ������ Ŭ���� ��
		clientBtnDel.setOnAction(event -> {			clientBtnDelAction();		});
		clientTableView.setOnMouseClicked(event -> {			clientBtnEdit.setDisable(false);		});

		// 5.�԰� ���� ������ DB�� ����� ������ �׸��� �������޺��ڽ� �׸����� �����´�
		tabIn.setOnSelectionChanged(event -> {
			regTextCompany.clear();
			regTextModel.clear();
			regTextColor.clear();
			regTextPrice.clear();
			regDatePicker.setValue(null);
			try {
				inCompany.clear();
				inCmbCompany.setValue(null);
				inCmbModel.setValue(null);
				inCmbColor.setValue(null);
				inCompany.addAll(PhoneDAO.DBtoCmbCompany());
				inCmbCompany.setItems(inCompany);
			} catch (NullPointerException e) {
			}

		});

		// �׷��� ���� ������ ��Ʈ ����
		barChart.setOnMouseClicked(event -> {

			// ����,���� �� ��������
			if (check == 0) {
				XYChart.Series seriesStock = new XYChart.Series<>();
				ObservableList stockList = FXCollections.observableArrayList();
				stockList.clear();
				seriesStock.getData().clear();
				barChart.getData().clear();
				for (int i = 0; i < stockData.size(); i++) {
					stockList.add(new XYChart.Data<>(stockData.get(i).getModel() + "\n" + stockData.get(i).getColor(),
							stockData.get(i).getQuantity()));
				}
				seriesStock.setData(stockList);

				barChart.getData().add(seriesStock);
				seriesStock.setName("������ ��������");
				stockData.clear();
				check = 1;
			}

		});

		// �𵨰��� ���� ������ �԰� �ǿ� �ִ� ������ �ʱ�ȭ
		tabOut.setOnSelectionChanged(event -> {
			inCmbCompany.setValue(null);
			inCmbModel.setValue(null);
			inCmbColor.setValue(null);
			inTextSerial.clear();
			inDatePicker.setValue(null);
			inTextMemo.clear();

			outCompany.clear();
			outClient.clear();
			outSerial.clear();
			outColor.clear();

			try {
				outClient.addAll(PhoneDAO.DBtoOutCmbAccount());
				outCmbClient.setItems(outClient);
				outCompany.addAll(PhoneDAO.DBtoOutCmbCompany());
				outCmbCompany.setItems(outCompany);
			} catch (NullPointerException e) {
			}

			// [���]���� [������]�޺��ڽ��� Ŭ���ϸ� [�𵨸�]�޺��ڽ��� ���� �ش� �������� �𵨷� �����Ѵ�.
			outCmbCompany.setOnAction(event1 -> {
				try {
					outModel.clear();
					String str = outCmbCompany.getSelectionModel().getSelectedItem().toString();
					outModel.addAll(PhoneDAO.DBtoOutCmbModel(str));
					outCmbModel.setItems(outModel);
				} catch (NullPointerException e) {
				}

			});

			// [���]���� [��]�޺��ڽ��� Ŭ���ϸ� [����]�޺��ڽ��� ���� �ش� ���� �������� �����Ѵ�.
			outCmbModel.setOnAction(event2 -> {
				try {
					outColor.clear();
					String str = outCmbModel.getSelectionModel().getSelectedItem().toString();
					outColor.addAll(PhoneDAO.DBtoOutCmbColor(str));
					outCmbColor.setItems(outColor);
				} catch (NullPointerException e) {

				}
			});

			// [���]���� [����]�޺��ڽ��� Ŭ���ϸ� [�Ϸù�ȣ]�޺��ڽ��� ���� ��+���� �ش��ϴ� ������ �����Ѵ�.
			outCmbColor.setOnAction(event3 -> {
				try {
					outSerial.clear();
					String str1 = outCmbModel.getSelectionModel().getSelectedItem().toString()
							+ outCmbColor.getSelectionModel().getSelectedItem().toString();
					outSerial.addAll(PhoneDAO.DBtoOutCmbSerial(str1));
					outCmbSerial.setItems(outSerial);
				} catch (NullPointerException e) {
				}

			});

		});

		// [�԰�]���� [������]�޺��ڽ��� Ŭ���ϸ� [�𵨸�]�׸��� �����翡 �ش��ϴ� ������ �����Ѵ�.
		inCmbCompany.setOnAction(event -> {
			try {
				inModel.clear();
				String str = inCmbCompany.getSelectionModel().getSelectedItem().toString();
				inModel.addAll(PhoneDAO.DBtoCmbModel(str));
				inCmbModel.setItems(inModel);
			} catch (NullPointerException e) {
			}

		});

		// [�԰�]���� [��]�޺��ڽ��� Ŭ���ϸ� [����]�׸��� �𵨸� �ش��ϴ� ������ �����Ѵ�.
		inCmbModel.setOnAction(event -> {
			try {
				inColor.clear();
				String str = inCmbModel.getSelectionModel().getSelectedItem().toString();
				inColor.addAll(PhoneDAO.DBtoCmbColor(str));
				inCmbColor.setItems(inColor);
			} catch (NullPointerException e) {
			}

		});

	}

	// �𵨰��� �ǿ��� ���� ��ư�� ���� ��
	private void regBtnDelAction() {
		selectModel = regTableView.getSelectionModel().getSelectedItem();
		modelIndex = regTableView.getSelectionModel().getSelectedIndex();

		int count = PhoneDAO.deleteModelData(selectModel.getModel() + selectModel.getColor());
		if (count != 0) {
			modelData.remove(modelIndex);
			regPhoneArrayList.remove(selectModel);

			callAlert("���� �Ϸ� : " + selectModel.getModel() + selectModel.getColor() + "�� ���� ���� �Ϸ�");
		}

	}

	// �̹��� ���� ��ư�� ������ ��
	private void regBtnImageAction() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"));
		selectFile = fileChooser.showOpenDialog(mainStage);
		String localURL = null;
		if (selectFile != null) {
			try {
				localURL = selectFile.toURI().toURL().toString();
			} catch (MalformedURLException e) {
			}
		}
		regImage.setImage(new Image(localURL, false));
		// ���õ� ���ϸ��� �ش�. fileName�� �ݵ�� �̹��������� �������� �� ���� �����Ѵ�
		fileName = selectFile.getName();
		callAlert("���õ� �̹��� :  " + fileName);
	}

	// �ŷ�ó�ǿ��� ������ Ŭ���� ��
	private void clientBtnDelAction() {
		selectClient = clientTableView.getSelectionModel().getSelectedItem();
		clientIndex = clientTableView.getSelectionModel().getSelectedIndex();

		int count = PhoneDAO.deleteClientData(selectClient.getAccount());
		if (count != 0) {
			clientData.remove(clientIndex);
			clientArrayList.remove(selectClient);

			callAlert("���� �Ϸ� : " + selectClient.getAccount() + "�� ���� ���� �Ϸ�");
		}

	}

	// �ŷ�ó�ǿ��� ������ Ŭ���� ��
	private void clientBtnEditAction() {
		selectClient = clientTableView.getSelectionModel().getSelectedItem();
		clientIndex = clientTableView.getSelectionModel().getSelectedIndex();
		try {
			Stage editStage = new Stage(StageStyle.UTILITY);
			editStage.initModality(Modality.WINDOW_MODAL);
			editStage.initOwner(mainStage);
			editStage.setTitle("[" + selectClient.getAccount() + "]" + " ��������");

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/editClient.fxml"));
			Parent root;
			root = loader.load();

			TextField editTxtAccount = (TextField) root.lookup("#editTxtAccount");
			TextField editTxtCEO = (TextField) root.lookup("#editTxtCEO");
			TextField editTxtAddress = (TextField) root.lookup("#editTxtAddress");
			TextField editTxtPhone = (TextField) root.lookup("#editTxtPhone");
			DatePicker editDatePicker = (DatePicker) root.lookup("#editDatePicker");

			Button editBtnEdit = (Button) root.lookup("#editBtnEdit");
			Button editBtnClose = (Button) root.lookup("#editBtnClose");

			inputPhoneNumberFormat(editTxtPhone);

			editTxtAccount.setDisable(true);

			editTxtAccount.setText(selectClient.getAccount());
			editTxtCEO.setText(selectClient.getCeo());
			editTxtAddress.setText(selectClient.getAddress());
			editTxtPhone.setText(selectClient.getPhone());
			editDatePicker.setValue(LocalDate.parse(selectClient.getDate()));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../application/edit.css").toString());
			editStage.setScene(scene);
			editStage.show();

			editBtnEdit.setOnAction(event -> {
				Client client = new Client(
						editTxtAccount.getText(), 
						editTxtCEO.getText(), 
						editTxtAddress.getText(),
						editTxtPhone.getText(), 
						editDatePicker.getValue().toString());

				int count = PhoneDAO.updateClientData(client);
				if (count != 0) {
					clientData.remove(clientIndex);
					clientData.add(clientIndex, client);
					int arrayIndex = clientArrayList.indexOf(selectClient);
					clientArrayList.set(arrayIndex, client);

					clientData.set(clientIndex, client);
					clientTableView.refresh();

					callAlert("���� �Ϸ� : " + selectClient.getAccount() + "�� ���� ���� �Ϸ�");
					editStage.close();

				}
			});

			editBtnClose.setOnAction(event -> {
				editStage.close();
			});

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	// [�԰�]�ǿ��� ��Ϲ�ư�� ������ �Է��� ������ �����Ѵ�.( �Ϸù�ȣ�� �������� ��� �Ұ���)
	private void inBtnRegAction() {

		outPhoneData.clear();
		outPhoneArrayList.clear();
		outTableView.setItems(outPhoneData);

		outPhoneArrayList = PhoneDAO.getOutPhoneTotalData();
		for (OutPhone phone : outPhoneArrayList) {
			outPhoneData.add(phone);
		}

		String str = inTextSerial.getText();

		for (RegPhone check : phoneArrayList) {
			if (check.getRegSerial().equals(str)) {
				flag = true;
				System.out.println(check.getRegSerial());
			}

			for (OutPhone outCheck : outPhoneArrayList) {
				if (outCheck.getSerial().equals(str)) {
					flag = true;
					System.out.println(outCheck.getSerial());
				}
			}

		}

		if (flag == true) {
			callAlert("�Է½��� : ���� �Ϸù�ȣ�� �ֽ��ϴ�");
			inCmbCompany.setValue(null);
			inCmbModel.setValue(null);
			inCmbColor.setValue(null);
			inTextSerial.setText(null);
			inDatePicker.setValue(null);
			inTextMemo.setText(null);
			flag = false;

		} else if (!(inCmbCompany.getSelectionModel().getSelectedItem() == null
				|| inCmbModel.getSelectionModel().getSelectedItem() == null
				|| inCmbColor.getSelectionModel().getSelectedItem() == null || inDatePicker.getValue() == null
				|| inTextSerial.getText() == null)) {
			RegPhone regPhone = new RegPhone(inCmbCompany.getSelectionModel().getSelectedItem().toString(),
					inCmbModel.getSelectionModel().getSelectedItem().toString(),
					inCmbColor.getSelectionModel().getSelectedItem().toString(), inDatePicker.getValue().toString(),
					inTextSerial.getText(),
					PhoneDAO.DBtoCmbPrice(inCmbModel.getSelectionModel().getSelectedItem().toString()),
					inTextMemo.getText());

			regPhoneData.add(regPhone);

			int count2 = PhoneDAO.truncateStock();
			if (count2 != 0) {
				callAlert("�Է¼��� :  �����ͺ��̽��� ���强��");

			}

			flag = false;

			// �����ͺ��̽��� ���ÿ� �Է��Ѵ�.
			int count = PhoneDAO.insertPhoneData(regPhone);
			if (count != 0) {
				callAlert("�Է¼��� :  �����ͺ��̽��� ���强��");
			}

		} else {
			callAlert("�Է½��� : ��� �׸��� �Է� ���ּ���");
		}
		regPhoneData.clear();
		phoneArrayList.clear();
		inTableView.setItems(regPhoneData);

		phoneArrayList = PhoneDAO.getPhoneTotalData();
		for (RegPhone phone : phoneArrayList) {
			regPhoneData.add(phone);
		}
		check = 0;
	}

	// ��� �ǿ��� ����ư�� ���� ��
	private void outBtnRegAction() {

		String str = outCmbSerial.getSelectionModel().getSelectedItem();

		for (OutPhone outCheck : outPhoneArrayList) {
			if (outCheck.getSerial().equals(str)) {
				flag = true;
				System.out.println(outCheck.getSerial());

			}

		}

		if (flag == true) {
			callAlert("�Է½��� : ���� �Ϸù�ȣ�� �ֽ��ϴ�");
			inCmbCompany.setValue(null);
			inCmbModel.setValue(null);
			inCmbColor.setValue(null);
			inTextSerial.setText(null);
			inDatePicker.setValue(null);
			inTextMemo.setText(null);
			flag = false;

		} else if (!(outCmbClient.getSelectionModel().getSelectedItem() == null
				|| outCmbCompany.getSelectionModel().getSelectedItem() == null
				|| outCmbModel.getSelectionModel().getSelectedItem() == null
				|| outCmbColor.getSelectionModel().getSelectedItem() == null
				|| outCmbSerial.getSelectionModel().getSelectedItem() == null || outDatePicker.getValue() == null)) {
			outPhone = new OutPhone(outCmbClient.getSelectionModel().getSelectedItem().toString(),
					outCmbCompany.getSelectionModel().getSelectedItem().toString(),
					outCmbModel.getSelectionModel().getSelectedItem().toString(),
					outCmbSerial.getSelectionModel().getSelectedItem().toString(),
					outCmbColor.getSelectionModel().getSelectedItem().toString(), outDatePicker.getValue().toString(),
					PhoneDAO.DBtoOutTableViewPrice(outCmbModel.getSelectionModel().getSelectedItem().toString()
							+ outCmbColor.getSelectionModel().getSelectedItem().toString()),
					outTextMemo.getText());
			outPhoneData.add(outPhone);

			int count = PhoneDAO.deleteRegModelData(outCmbSerial.getSelectionModel().getSelectedItem().toString());
			if (count != 0) {
				regPhoneData.clear();
				phoneArrayList.clear();
				inTableView.setItems(regPhoneData);

				phoneArrayList = PhoneDAO.getPhoneTotalData();
				for (RegPhone phone : phoneArrayList) {
					regPhoneData.add(phone);
				}

				outPhoneData.clear();
				outPhoneArrayList.clear();
				outTableView.setItems(outPhoneData);

				outPhoneArrayList = PhoneDAO.getOutPhoneTotalData();
				for (OutPhone phone : outPhoneArrayList) {
					outPhoneData.add(phone);
				}

				stockData.clear();

				stockTableView.setItems(stockData);
				stockTableView.refresh();
				ArrayList<Stock> stockArray = PhoneDAO.DBtoStockData();
				for (Stock stock2 : stockArray) {
					stock = new Stock(
							stock2.getCompany(), 
							stock2.getModel(), 
							stock2.getColor(), 
							stock2.getQuantity());

					stockData.add(stock);
					// �����ͺ��̽��� ���ÿ� �Է��Ѵ�.
				}

				flag = false;
			} else {
				callAlert("�Է½��� : ��� �׸��� �Է� ���ּ���");
			}

		}

		// �����ͺ��̽��� ���ÿ� �Է��Ѵ�.
		int count2 = PhoneDAO.insertOutPhoneData(outPhone);
		if (count2 != 0) {
			callAlert("�Է¼��� :  �����ͺ��̽��� ���强��");
		}

		outPhoneData.clear();
		outPhoneArrayList.clear();
		outTableView.setItems(outPhoneData);

		outPhoneArrayList = PhoneDAO.getOutPhoneTotalData();
		for (OutPhone phone : outPhoneArrayList) {
			outPhoneData.add(phone);
		}
		check = 0;
	}

	// �ŷ�ó�������� ��Ϲ�ư�� ������ �Է��� ������ �����Ѵ�.( �ŷ�ó���� �������� ��� �Ұ���)
	private void clientBtnRegAction() {
		String str = clientName.getText();

		for (Client check : clientArrayList) {
			if (check.getAccount().equals(str)) {
				flag = true;
				System.out.println(check.getAccount());
			}
		}

		if (flag == true) {
			callAlert("�Է½��� : ������ �ŷ�ó�� �ֽ��ϴ�.");
			clientName.setText(null);
			clientCeo.setText(null);
			clientAddress.setText(null);
			clientPhone.setText(null);
			clientDatePicker.setValue(null);

			flag = false;

		} else {

			Client client = new Client(
					clientName.getText(), 
					clientCeo.getText(), 
					clientAddress.getText(),
					clientPhone.getText(), 
					clientDatePicker.getValue().toString());
			clientData.add(client);

			regPhoneData.add(regPhone);
			clientName.setText(null);
			clientCeo.setText(null);
			clientAddress.setText(null);
			clientPhone.setText(null);
			clientDatePicker.setValue(null);

			// �����ͺ��̽��� ���ÿ� �Է��Ѵ�.
			int count = PhoneDAO.insertClientData(client);
			if (count != 0) {
				callAlert("�Է¼��� :  �����ͺ��̽��� ���强��");
			}
		}

	}

	// ��� ���̺�� ����
	private void setStockTableView() {
		TableColumn tcCompany = stockTableView.getColumns().get(0);
		tcCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
		tcCompany.setStyle("-fx-alignment:CENTER;");

		TableColumn tcModel = stockTableView.getColumns().get(1);
		tcModel.setCellValueFactory(new PropertyValueFactory<>("model"));
		tcModel.setStyle("-fx-alignment:CENTER;");

		TableColumn tcColor = stockTableView.getColumns().get(2);
		tcColor.setCellValueFactory(new PropertyValueFactory<>("color"));
		tcColor.setStyle("-fx-alignment:CENTER;");

		TableColumn tcIndate = stockTableView.getColumns().get(3);
		tcIndate.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		tcIndate.setStyle("-fx-alignment:CENTER;");

		stockTableView.setItems(stockData);

		stockArrayList = PhoneDAO.getTotalStockData();
		for (Stock stock : stockArrayList) {
			stockData.add(stock);
		}

	}

	// 1. �԰� ���̺�� ����
	private void setInTableView() {
		TableColumn tcCompany = inTableView.getColumns().get(0);
		tcCompany.setCellValueFactory(new PropertyValueFactory<>("regCompany"));
		tcCompany.setStyle("-fx-alignment:CENTER;");

		TableColumn tcModel = inTableView.getColumns().get(1);
		tcModel.setCellValueFactory(new PropertyValueFactory<>("regModel"));
		tcModel.setStyle("-fx-alignment:CENTER;");

		TableColumn tcColor = inTableView.getColumns().get(2);
		tcColor.setCellValueFactory(new PropertyValueFactory<>("regColor"));
		tcColor.setStyle("-fx-alignment:CENTER;");

		TableColumn tcIndate = inTableView.getColumns().get(3);
		tcIndate.setCellValueFactory(new PropertyValueFactory<>("regIndate"));
		tcIndate.setStyle("-fx-alignment:CENTER;");

		TableColumn tcSerial = inTableView.getColumns().get(4);
		tcSerial.setCellValueFactory(new PropertyValueFactory<>("regSerial"));
		tcSerial.setStyle("-fx-alignment:CENTER;");

		TableColumn tcPrice = inTableView.getColumns().get(5);
		tcPrice.setCellValueFactory(new PropertyValueFactory<>("regPrice"));
		tcPrice.setStyle("-fx-alignment:CENTER;");

		TableColumn tcMemo = inTableView.getColumns().get(6);
		tcMemo.setCellValueFactory(new PropertyValueFactory<>("regMemo"));
		tcMemo.setStyle("-fx-alignment:CENTER;");

		inTableView.setItems(regPhoneData);

		phoneArrayList = PhoneDAO.getPhoneTotalData();
		for (RegPhone phone : phoneArrayList) {
			regPhoneData.add(phone);
		}

	}

	// ��� ���̺�� ����
	private void setOutTableView() {
		TableColumn tcClient = outTableView.getColumns().get(0);
		tcClient.setCellValueFactory(new PropertyValueFactory<>("client"));
		tcClient.setStyle("-fx-alignment:CENTER;");

		TableColumn tcCompany = outTableView.getColumns().get(1);
		tcCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
		tcCompany.setStyle("-fx-alignment:CENTER;");

		TableColumn tcModel = outTableView.getColumns().get(2);
		tcModel.setCellValueFactory(new PropertyValueFactory<>("model"));
		tcModel.setStyle("-fx-alignment:CENTER;");

		TableColumn tcSerial = outTableView.getColumns().get(3);
		tcSerial.setCellValueFactory(new PropertyValueFactory<>("serial"));
		tcSerial.setStyle("-fx-alignment:CENTER;");

		TableColumn tcColor = outTableView.getColumns().get(4);
		tcColor.setCellValueFactory(new PropertyValueFactory<>("color"));
		tcColor.setStyle("-fx-alignment:CENTER;");

		TableColumn tcOutdate = outTableView.getColumns().get(5);
		tcOutdate.setCellValueFactory(new PropertyValueFactory<>("outdate"));
		tcOutdate.setStyle("-fx-alignment:CENTER;");

		TableColumn tcPrice = outTableView.getColumns().get(6);
		tcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		tcPrice.setStyle("-fx-alignment:CENTER;");

		TableColumn tcMemo = outTableView.getColumns().get(7);
		tcMemo.setCellValueFactory(new PropertyValueFactory<>("memo"));
		tcMemo.setStyle("-fx-alignment:CENTER;");

		outTableView.setItems(outPhoneData);

		outPhoneArrayList = PhoneDAO.getOutPhoneTotalData();
		for (OutPhone phone : outPhoneArrayList) {
			outPhoneData.add(phone);
		}

	}

	// �𵨰��� ���̺�� ����
	private void setRegTableView() {
		TableColumn tcCompany = regTableView.getColumns().get(0);
		tcCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
		tcCompany.setStyle("-fx-alignment:CENTER;");

		TableColumn tcModel = regTableView.getColumns().get(1);
		tcModel.setCellValueFactory(new PropertyValueFactory<>("model"));
		tcModel.setStyle("-fx-alignment:CENTER;");

		TableColumn tcSerial = regTableView.getColumns().get(2);
		tcSerial.setCellValueFactory(new PropertyValueFactory<>("color"));
		tcSerial.setStyle("-fx-alignment:CENTER;");

		TableColumn tcIndate = regTableView.getColumns().get(3);
		tcIndate.setCellValueFactory(new PropertyValueFactory<>("price"));
		tcIndate.setStyle("-fx-alignment:CENTER;");

		TableColumn tcClient = regTableView.getColumns().get(4);
		tcClient.setCellValueFactory(new PropertyValueFactory<>("indate"));
		tcClient.setStyle("-fx-alignment:CENTER;");

		TableColumn tcOutdate = regTableView.getColumns().get(5);
		tcOutdate.setCellValueFactory(new PropertyValueFactory<>("image"));
		tcOutdate.setStyle("-fx-alignment:CENTER;");

		regTableView.setItems(modelData);

		regPhoneArrayList = PhoneDAO.getRegPhoneTotalData();
		for (Phone phone : regPhoneArrayList) {
			modelData.add(phone);
		}

	}

	// �ŷ�ó ���� ���̺�� ����
	private void setClientTableView() {
		TableColumn tcCompany = clientTableView.getColumns().get(0);
		tcCompany.setCellValueFactory(new PropertyValueFactory<>("account"));
		tcCompany.setStyle("-fx-alignment:CENTER;");

		TableColumn tcModel = clientTableView.getColumns().get(1);
		tcModel.setCellValueFactory(new PropertyValueFactory<>("ceo"));
		tcModel.setStyle("-fx-alignment:CENTER;");

		TableColumn tcColor = clientTableView.getColumns().get(2);
		tcColor.setCellValueFactory(new PropertyValueFactory<>("address"));
		tcColor.setStyle("-fx-alignment:CENTER;");

		TableColumn tcPrice = clientTableView.getColumns().get(3);
		tcPrice.setCellValueFactory(new PropertyValueFactory<>("phone"));
		tcPrice.setStyle("-fx-alignment:CENTER;");

		TableColumn tcDate = clientTableView.getColumns().get(4);
		tcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		tcDate.setStyle("-fx-alignment:CENTER;");

		clientTableView.setItems(clientData);

		clientArrayList = PhoneDAO.getClientTotalData();
		for (Client client : clientArrayList) {
			clientData.add(client);
		}

	}

	// [�𵨰���]�ǿ��� ��Ϲ�ư�� ������ �Է��� ������ �����Ѵ�.( �𵨸�� ���� �ΰ����� �������� ��� �Ұ���)
	private void regBtnRegAction() {

		imageSave();
		if (selectFile == null) {
			callAlert("�̹��� ���� ���� : �̹����� �������ּ���");
			return;
		}

		String str = regTextModel.getText() + regTextColor.getText();

		for (Phone check : regPhoneArrayList) {
			if ((check.getModel() + check.getColor()).equals(str)) {
				flag = true;
			}
		}

		if (flag == true) {
			callAlert("�Է½��� : ���� ���� �ֽ��ϴ�");
			flag = false;

		} else {
			phone = new Phone(
					regTextCompany.getText(), 
					regTextModel.getText(), 
					regTextColor.getText(),
					Integer.parseInt(regTextPrice.getText()), 
					regDatePicker.getValue().toString(), 
					fileName,
					regTextModel.getText() + regTextColor.getText());

			modelData.add(phone);

		}

		// �����ͺ��̽��� ���ÿ� �Է��Ѵ�.
		int count = PhoneDAO.makePhoneData(phone);
		if (count != 0) {
			callAlert("�Է¼��� :  �����ͺ��̽��� ���强��");
		}
		regTableView.refresh();

	}

	// ��Ÿ : �˸�â
	public static void callAlert(String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("�˸�â");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":") + 1));
		alert.showAndWait();
	}

	// ��Ÿ : �Է°� �ʵ� ���˼��� ��� : �ϰ��ڸ� ���ڸ� �޴� ��� ����
	private void inputPriceFormat(TextField textField) {
		// ���ڸ� �Է�(������ �Է¹���), �Ǽ��Է¹ް������ new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("###");
		// ���� �Է½� ���� ���� �̺�Ʈ ó��
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// �Է¹��� ������ ������ �̺�Ʈ�� ������.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// ������ �м��� ���� ��ġ�� ������.
			ParsePosition parsePosition = new ParsePosition(0);
			// �Է¹��� ����� �м���ġ�� �������������� format ����� ��ġ���� �м���.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// ���ϰ��� null �̰ų�, �Է��ѱ��̿� �����м���ġ���� ���������(�� �м������������� ����) �ų�, �Է��ѱ��̰� 8�̸�(7�ڸ��� �Ѿ�����
			// ����.) �̸� null ������.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 8) {
				return null;
			} else {
				return event;
			}
		}));
	}

	// ��Ÿ : �Է°� �ʵ� ���˼��� ��� : ���ڸ� ���ڸ� �޴� ��� ����
	private void inputPhoneNumberFormat(TextField textField) {
		// ���ڸ� �Է�(������ �Է¹���), �Ǽ��Է¹ް������ new DecimalFormat("###.#");
		DecimalFormat format = new DecimalFormat("###");
		// ���� �Է½� ���� ���� �̺�Ʈ ó��
		textField.setTextFormatter(new TextFormatter<>(event -> {
			// �Է¹��� ������ ������ �̺�Ʈ�� ������.
			if (event.getControlNewText().isEmpty()) {
				return event;
			}
			// ������ �м��� ���� ��ġ�� ������.
			ParsePosition parsePosition = new ParsePosition(0);
			// �Է¹��� ����� �м���ġ�� �������������� format ����� ��ġ���� �м���.
			Object object = format.parse(event.getControlNewText(), parsePosition);
			// ���ϰ��� null �̰ų�, �Է��ѱ��̿� �����м���ġ���� ���������(�� �м������������� ����) �ų�, �Է��ѱ��̰� 4�̸�(3�ڸ��� �Ѿ�����
			// ����.) �̸� null ������.
			if (object == null || parsePosition.getIndex() < event.getControlNewText().length()
					|| event.getControlNewText().length() == 12) {
				return null;
			} else {
				return event;
			}
		}));
	}

	private void imageSave() {
		if (!imageDir.exists()) {
			imageDir.mkdir(); // ���丮�� ������ �ȵǾ� ������ ������ �����.
		}
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		// ���õ� �̹����� c:/phoneimages/"���õ� �̹��� ��" ���� �����Ѵ�.
		try {
			fis = new FileInputStream(selectFile);
			bis = new BufferedInputStream(fis);

			fileName = "phone" + selectFile.getName();
			fos = new FileOutputStream(imageDir.getAbsolutePath() + "\\" + fileName);

			bos = new BufferedOutputStream(fos);

			int data = -1;
			while ((data = bis.read()) != -1) {
				bos.write(data);
				bos.flush();

			}
		} catch (Exception e) {
			callAlert("�̹��� ���� ���� : �������� ����");
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (bis != null) {
					bis.close();
				}
				if (fos != null) {
					fos.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
			}
		}

	}

	// ��Ÿ : �̹��� ����
	private void imageDelete() {
		File imageFile = new File(imageDir.getAbsolutePath() + "\\" + selectPhoneTable.getImage());
		boolean delFlag = false;
		if (imageFile.exists() && imageFile.isFile()) {
			delFlag = imageFile.delete();
			if (delFlag == false) {
				callAlert("�̹��� ���� ���� : �̹��� ���� ����");
			}
		}

	}
}
