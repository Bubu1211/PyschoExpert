package vdcb.psychoexpert.modelo.dto;

public class Usuario {

    private int idusuario;
    private String nombre;
    private String Apellido;
    private String email;
    private String Telefono;
    private String puesto;
    private String password;
    
    public Usuario() {
        this.idusuario = 0;
        this.nombre = "";
        this.Apellido = "";
        this.email = "";
        this.Telefono = "";
        this.puesto = "";
        this.password = "";
    }

    public Usuario(String nombre, String Apellido, String email, String Telefono, String puesto, String password) {
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.email = email;
        this.Telefono = Telefono;
        this.puesto = puesto;
        this.password = password;
    }

    public Usuario(int idusuario, String nombre, String Apellido, String email, String Telefono, String puesto, String password) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.email = email;
        this.Telefono = Telefono;
        this.puesto = puesto;
        this.password = password;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwrod) {
        this.password = passwrod;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idusuario=" + idusuario + ", nombre=" + nombre + ", Apellido=" + Apellido + ", email=" + email + ", Telefono=" + Telefono + ", puesto=" + puesto + ", password=" + password + '}';
    }
}
