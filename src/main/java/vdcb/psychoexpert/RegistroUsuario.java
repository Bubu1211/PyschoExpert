package vdcb.psychoexpert;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import vdcb.psychoexpert.modelo.dao.UsuarioDao;
import vdcb.psychoexpert.modelo.dto.Usuario;
import vdcb.psychoexpert.util.Cambios;

public class RegistroUsuario implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtPuesto;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCorreoElectronico;
    @FXML
    private TextField txtContraseña;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnReiniciar;
    @FXML
    private Button btnSalir;

    private UsuarioDao usuarioDao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao = new UsuarioDao();
        var origen = (Cambios) App.getMensajePersistente("origen");
        if(origen == Cambios.MODIFICAR){
            var u = (Usuario) App.getMensajePersistente("usuario");
            txtNombre.setText(u.getNombre());
            txtApellidos.setText(u.getApellido());
            txtCorreoElectronico.setText(u.getEmail());
            txtPuesto.setText(u.getPuesto());
            txtContraseña.setText(u.getPassword());
            txtTelefono.setText(u.getTelefono());
        }
    }

    @FXML
    public void guardar() {
        var nombre = txtNombre.getText();
        var apellido = txtApellidos.getText();
        var email = txtCorreoElectronico.getText();
        var telefono = txtTelefono.getText();
        var puesto = txtPuesto.getText();
        var password = txtContraseña.getText();
        var usuario = new Usuario(nombre, apellido, email, telefono, puesto, password);
        try {
            int id = 0;
            boolean aceptado = false;
            var origen = App.getMensajePersistente("origen");
            System.out.println("origen = " + origen);
            if(origen == Cambios.REGISTRAR_NUEVO_USUARIO || origen == Cambios.REGISTRAR_NUEVO){
                id = usuarioDao.insertar(usuario);
                aceptado = App.alertConfirm("Usuario ingresado en la BD ", "Por favor guarde el siguiente número \nEs su ID de usuario"
                    + "con él podrá ingresar a este sistema \n(Una vez que cierre esta ventana ya no podrá ver el ID"
                    + "\n\n" + id);
            } else if(origen == Cambios.MODIFICAR){
                int idUsuarioModificar = ((Usuario) App.getMensajePersistente("usuario")).getIdusuario();
                usuario.setIdusuario(idUsuarioModificar);
                usuarioDao.modificar(usuario);
                App.alertConfirm("Usuario MODIFICADO en la BD ", "El siguiente usuario ha sido modificado: "+id);
            }
            if (aceptado) {
                reiniciar();
            }
        } catch (SQLException ex) {
            App.alertError("Error en la BD", "Ocurrió un error a la hora de guardar la información en la BD " + ex.getSQLState());
            System.out.println("SQLSTATE: "+ex.getSQLState());
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    public void reiniciar() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreoElectronico.setText("");
        txtTelefono.setText("");
        txtPuesto.setText("");
        txtContraseña.setText("");
    }

    @FXML
    public void salir() {
        var origen = App.getMensaje("origen");
        try {
            if (origen == Cambios.REGISTRAR_NUEVO) {
                App.setRoot(App.InicioSesion);
            } else if (origen == Cambios.REGISTRAR_NUEVO_USUARIO || origen == Cambios.MODIFICAR) {
                App.setRoot(App.Principal);
            }
        } catch (IOException ex) {
            App.alertError("Error entrando a pantalla", "Un error inesperado ha ocurrido durante el cierre");
        }
    }
}
