package vdcb.psychoexpert;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import vdcb.psychoexpert.util.Cambios;

public class SplashTop implements Initializable{

    @FXML
    private ProgressBar progressBar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        var origen = App.getMensajePersistente("origen");
        
        
        Thread progress = new Thread(){
            @Override 
            public void run(){
                try {
                    
                    for(double i = 0; i<=1; i+= 0.05){
                        Thread.sleep(250);
                        progressBar.setProgress(i);
                    }
                    Thread.sleep(1000);
                    progressBar.setProgress(1);
                    //Abrir la interface adecuada
                    System.out.println("Abriendo");
                    
                    if(origen == Cambios.REGISTRAR_NUEVO){
                        try {
                            App.setRoot(App.RegistroUsuarios);
                        } catch (IOException ex) {
                            Logger.getLogger(SplashTop.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if(origen == Cambios.INICIAR_SESION){
                        try {
                            App.setRoot(App.Principal);
                        } catch (IOException ex) {
                            Logger.getLogger(SplashTop.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(SplashTop.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        progress.start();
    }
}