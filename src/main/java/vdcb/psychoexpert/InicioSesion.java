package vdcb.psychoexpert;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import vdcb.psychoexpert.modelo.dao.UsuarioDao;
import vdcb.psychoexpert.util.Cambios;

public class InicioSesion implements Initializable{
    @FXML 
    Button btnIniciarSesion;
    @FXML
    TextField txtUsuario;
    @FXML 
    PasswordField txtContraseña; 
    @FXML 
    Label lblUsuario;
    @FXML 
    Label lblContraseña;
    @FXML
    Label lblInicioSesion;
    @FXML       
    Button btnAgregarUsuario;
    
    private UsuarioDao usuarioDao;
    
    @FXML
    public void ingresar () {
        System.out.println("ingreso");
        try{
            
            var id = Integer.parseInt(this.txtUsuario.getText());
            var password = txtContraseña.getText();
            
            var usuario = usuarioDao.buscar(id);
            //si usuario esta vacio 
            if(usuario == null){
                App.alertInfo("Usuario no encontrado...", "El usuario con id "+id+" no fue encontrado");
            }else{
                if(usuario.getPassword().equals(password)){
                    System.out.println("Iniciando sesión...");
                    App.setMensaje("usuario", usuario);
                    App.setMensaje("origen", Cambios.INICIAR_SESION);
                    App.setRoot(App.SplashTop);
                } else{
                    App.alertError("Contraseña incorrecta..", "La contraseña o el ID son incorrectos");
                }
            }
            
        }catch(NumberFormatException e){
            App.alertError("Error" , "Debe ingresar un valor numérico: el ID de usuario");
        } catch (SQLException ex) {
            App.alertError("Error en la Base de datos",
                    "El siguiente error ha ocurrido intentando hacer la conexión\n"+ex.getSQLState());
            System.out.println("SQLState: "+ex.getSQLState());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            App.alertError("Error abriendo la aplicación", "Ocurrió un error al iniciar Sesión");
        }
    }
    
    @FXML
    public void  nuevoUsuario(){
        System.out.println("Registro de nuevo usuario");
        //Abre la ventana 
        
        try {
            App.setMensaje("origen", Cambios.REGISTRAR_NUEVO);
            App.setRoot(App.SplashTop);
        } catch (IOException ex) {
            App.alertError("Error al abrir aplicación", "Ocurrió un error inesperado al abrir la app");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao = new UsuarioDao();
    }
}
