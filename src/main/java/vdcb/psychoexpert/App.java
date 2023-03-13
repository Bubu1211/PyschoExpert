package vdcb.psychoexpert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import vdcb.psychoexpert.util.Mensajes;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    public static final String InicioSesion = "iniciosesion";
    public static final String Principal = "principal";
    public static final String RegistroUsuarios = "registrousuario";
    public static final String SplashTop = "splashtop";
    public static Mensajes mensajes;

    @Override
    public void start(Stage stage) throws IOException {
        mensajes = new Mensajes();

        var pane = loadFXML(App.InicioSesion);
        scene = new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight());
        App.stage = stage;
        App.stage.setScene(scene);
        App.stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        var p = loadFXML(fxml);
        stage.setWidth(p.getPrefWidth());
        stage.setHeight(p.getPrefHeight());
        scene.setRoot(p);
    }

    public static void alertError(String titulo, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(titulo);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static boolean alertConfirm(String titulo, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText(titulo);
        alert.setContentText(content);
        var button = alert.showAndWait();
        return button.get() == ButtonType.OK;
    }

    public static void alertInfo(String titulo, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(titulo);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void setMensaje(String clave, Object o) {
        mensajes.set(clave, o);
    }

    public static Object getMensaje(String clave) {
        return mensajes.get(clave);
    }

    public static Object getMensajePersistente(String clave) {
        return mensajes.getPersistence(clave);
    }

    private static Pane loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return (Pane) fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}
