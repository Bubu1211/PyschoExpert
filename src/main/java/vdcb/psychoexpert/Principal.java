package vdcb.psychoexpert;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vdcb.psychoexpert.modelo.dto.Usuario;
import vdcb.psychoexpert.util.Cambios;


public class Principal {

    @FXML
    Button btnNewuser;

    @FXML
    Button btnClose;

    @FXML
    public void registro() {
        App.setMensaje("origen", Cambios.REGISTRAR_NUEVO_USUARIO);
        try {
            App.setRoot(App.RegistroUsuarios);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void cerrar(){
        App.getMensaje("usuario");
        try {
            App.setRoot(App.InicioSesion);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void modificar(){
        Usuario usuario = (Usuario) App.getMensajePersistente("usuario");
        System.out.println("usuario = " + usuario);
        try {
            App.setMensaje("origen", Cambios.MODIFICAR);
            App.setRoot(App.RegistroUsuarios);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

