package vdcb.psychoexpert.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vdcb.psychoexpert.modelo.Conexion;
import vdcb.psychoexpert.modelo.dto.Usuario;

public class UsuarioDao {

    private final String BUSCAR = "SELECT * FROM usuarios WHERE id_usuario=?;";
    private final String SELECTALL = "SELECT id_usuario, nombre, apellido, email, telefono, puesto FROM usuarios;";
    private final String INSERT = "INSERT INTO usuarios(nombre, apellido, telefono, email, puesto, password)"
            + " VALUES(?,?,?,?,?,?);";
    private final String LAST_INSERT_ID  = "SELECT LAST_INSERT_ID();";
    private final String UPDATE = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, telefono = ?, "
            + "puesto = ?, password = ? WHERE id_usuario = ?";
    
    public int insertar(Usuario usuario) throws SQLException{
        var conn = Conexion.getConexion();
        var stmt = conn.prepareStatement(INSERT);
        stmt.setString(1, usuario.getNombre());
        stmt.setString(2, usuario.getApellido());
        stmt.setString(3, usuario.getTelefono());
        stmt.setString(4, usuario.getEmail());
        stmt.setString(5, usuario.getPuesto());
        stmt.setString(6, usuario.getPassword());
        stmt.executeUpdate();
        
        stmt = conn.prepareStatement(LAST_INSERT_ID);
        var rs = stmt.executeQuery();
        rs.next();
        var id = rs.getInt("LAST_INSERT_ID()");
        Conexion.close(conn);
        Conexion.close(stmt);
        Conexion.close(rs);
        return id;
    }

    public Usuario buscar(int id_usuario) throws SQLException {
        
        Usuario usuario = null;

        var cn = Conexion.getConexion();
        var ps = cn.prepareStatement(BUSCAR);
        ps.setInt(1, id_usuario);
        var rs = ps.executeQuery();
        
        while (rs.next()) {
            usuario = new Usuario();
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setEmail(rs.getString("email"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setPuesto(rs.getString("puesto"));
            usuario.setPassword(rs.getString("password"));
        }

        Conexion.close(rs);
        Conexion.close(ps);
        Conexion.close(cn);

        return usuario;
    }
    
    public List<Usuario> selectAll() throws SQLException {
        
        Usuario usuario = null;
        var lista = new ArrayList<Usuario>();

        var cn = Conexion.getConexion();
        var ps = cn.prepareStatement(SELECTALL);
        var rs = ps.executeQuery();

        while (rs.next()) {
            usuario = new Usuario();
            usuario.setIdusuario(rs.getInt("id_usuario"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setEmail(rs.getString("email"));
            usuario.setTelefono(rs.getString("telefono"));
            usuario.setPuesto(rs.getString("puesto"));
            lista.add(usuario);
        }

        Conexion.close(rs);
        Conexion.close(ps);
        Conexion.close(cn);

        return lista;
    }

    public void modificar(Usuario usuario) throws SQLException {
        System.out.println("Modificando...");
        String s = "UPDATE usuarios SET nombre = ?, apellido = ?, email = ?, telefono = ?, "
            + "puesto = ?, password = ? WHERE id_usuario = ?";
           
        Connection cn = Conexion.getConexion();
        PreparedStatement stmt = cn.prepareStatement(UPDATE);
        
        stmt.setString(1, usuario.getNombre());
        stmt.setString(2, usuario.getApellido());
        stmt.setString(3, usuario.getEmail());
        stmt.setString(4, usuario.getTelefono());
        stmt.setString(5, usuario.getPuesto());
        stmt.setString(6, usuario.getPassword());
        stmt.setInt(7, usuario.getIdusuario());
        
        stmt.executeUpdate();
    }
}
