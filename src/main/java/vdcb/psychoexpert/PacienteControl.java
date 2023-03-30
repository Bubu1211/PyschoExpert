package vdcb.psychoexpert;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import vdcb.psychoexpert.modelo.dao.OttisDao;
import vdcb.psychoexpert.modelo.dto.PreguntaOttisDto;

public class PacienteControl implements Initializable{
    
    private OttisDao ottisDao;
    
    @FXML
    private ScrollPane scrollPreguntas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ottisDao = new OttisDao();
    }
    
    @FXML
    public void iniciarTest(){
        System.out.println("Iniciando test");
        var lista = ottisDao.obtenerPreguntas();
        for(PreguntaOttisDto d : lista){
            System.out.println("d = " + d);
        }
    }
}
