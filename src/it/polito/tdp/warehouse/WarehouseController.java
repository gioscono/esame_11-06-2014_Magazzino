package it.polito.tdp.warehouse;

import it.polito.tdp.warehouse.bean.Locale;
import it.polito.tdp.warehouse.bean.Statistiche;
import it.polito.tdp.warehouse.model.Model;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class WarehouseController {
	
	private Model model ;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> boxStrategia;

    @FXML
    private Button btnCarica;

    @FXML
    private Button btnSimula;

    @FXML
    private TextField txtLocali;

    @FXML
    private TextArea txtResult;


    @FXML
    void doOccupazione(ActionEvent event) {
    	
    	// double occ = model.calcolaOccupazione() ;
    	txtResult.appendText("Calcolo occupazione teorica:\n") ;
    	txtResult.appendText(model.calcolaOccupazioneMassima()+""+"\n");
    	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    	String loc = txtLocali.getText();
    	try{
    		int locali = Integer.parseInt(loc);
    		String strategia = boxStrategia.getValue();
    		Statistiche s = model.avviaSimulazione(locali, strategia);
    		txtResult.appendText("Distatri: "+s.getDisastri()+"\n"+"Tempo tot.:"+s.getTempo()+"\n");
    		for(Locale l :s.getMappaFattCarico().keySet()){
    			txtResult.appendText(l.toString()+"-"+s.getMappaFattCarico().get(l)+"\n");
    		}
    		
    	}catch(NumberFormatException e){
    		txtResult.appendText("Errore: inserire un numero di locali valido.\n");
    		return;
    	}

    	
    }
    
    public void setModel(Model model) {
		this.model = model;
	}

    @FXML
    void initialize() {
        assert boxStrategia != null : "fx:id=\"boxStrategia\" was not injected: check your FXML file 'Warehouse.fxml'.";
        assert btnCarica != null : "fx:id=\"btnCarica\" was not injected: check your FXML file 'Warehouse.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Warehouse.fxml'.";
        assert txtLocali != null : "fx:id=\"txtLocali\" was not injected: check your FXML file 'Warehouse.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Warehouse.fxml'.";
        
        boxStrategia.getItems().clear() ;
        boxStrategia.getItems().addAll("Meno occupato","Più occupato") ;
    }

}
