package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class designController implements Initializable{
	
    @FXML
    private TableView<Refrigrator> simulTable;

    @FXML
    private TableView<Refrigrator> RefrigTable;
    @FXML
    private TableView<Refrigrator> leadTable;
    private TableColumn<Refrigrator, Integer> DayCol,beginCol,RDACol,demCol,endCol,shorCol,ordCol,RanLeaCol,leadCol;
    private TableColumn<Refrigrator, Double> DPropCol,RDADCol,RDALCol,LPropCol;

    @FXML
    private JFXTextField DaysText;
    private double x=0,y=0;
    private ObservableList<Refrigrator> list;
    public static ObservableList<Refrigrator> refDemandProb;//{0.10,0.25,0.35,0.21,0.09};
	public static ObservableList<Refrigrator> leadTimeProb;//{0.6,0.3,0.1};
    
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		justANumberVaild();
		list = FXCollections.observableArrayList();
		leadTimeProb = FXCollections.observableArrayList();
		leadTimeProb.addAll(new Refrigrator(0,0.6),
							new Refrigrator(0,0.3),
							new Refrigrator(0,0.1));
		refDemandProb = FXCollections.observableArrayList();
		refDemandProb.addAll(new Refrigrator(0.10,0),
							 new Refrigrator(0.25,0),
							 new Refrigrator(0.35,0),
							 new Refrigrator(0.21,0),
							 new Refrigrator(0.09,0));
		
		DayCol = tableColumn("Days","count");
		beginCol = tableColumn("BeginEnven","beginingInv");
		RDACol = tableColumn("RDA Demand","randDem");
		demCol = tableColumn("Demand","RefDemand");
		endCol = tableColumn("EndEnven","endingInv");
		shorCol	= tableColumn("Shortage","shortageQuan");
		ordCol = tableColumn("Order Quant","orderQuan");
		RanLeaCol = tableColumn("Random Time","rmled");
		leadCol = tableColumn("Lead Time","leadTime");
		simulTable.getColumns().setAll(DayCol,beginCol,RDACol,demCol,endCol,shorCol,ordCol,RanLeaCol,leadCol);
		
		DPropCol = tableColumn("Demand","refProp");
		RDADCol = tableColumn("RDA","refRDA");
		RefrigTable.setItems(refDemandProb);
		RefrigTable.getColumns().setAll(DPropCol,RDADCol);
		LPropCol = tableColumn("Lead Time","leadProb");
		RDALCol = tableColumn("RDA","leadRDA");		
		leadTable.getColumns().setAll(LPropCol,RDALCol);
		leadTable.setItems(leadTimeProb);
	}
	
	public <T, E>TableColumn<T, E> tableColumn(String title,String value){
		TableColumn<T, E> Col = new TableColumn<>(title);
		Col.setPrefWidth(110);
		Col.setCellValueFactory(new PropertyValueFactory<>(value));
		
		return Col;
	}
	
	public void Simulation(ActionEvent event) {
    	list = SimulationProcess.SimAlgorithm(Integer.parseInt(DaysText.getText()));
		simulTable.setItems(list);
    }
	@FXML
    private void closeWindow(ActionEvent event) {
    	Platform.exit();
    	System.exit(0);
    }
	
    
    @FXML
    private void dragged(MouseEvent event) {
    	Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setX(event.getScreenX()-x);
		stage.setY(event.getScreenY()-y);
    }
    @FXML
    private void pressed(MouseEvent event) {
    	x = event.getSceneX();
		y = event.getSceneY();
    }
    
	private void justANumberVaild() {
		NumberValidator validator = new NumberValidator();
    	validator.setMessage("just a number...");
    	validator.setStyle("-fx-text-fill:white;");
    	//validator.setIcon(new ImageView("error.png"));
    	DaysText.getValidators().add(validator);
    	DaysText.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					if(!DaysText.validate()) {
						DaysText.clear();
					}
				}
			}
		});
	}
}
