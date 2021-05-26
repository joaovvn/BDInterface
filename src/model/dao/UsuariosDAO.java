package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Usuarios;

public class UsuariosDAO {
    public void create(Usuarios u) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO usuarios (Email, Usuario, Senha)VALUES(?, ?, ?)");
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getUsuario());
            stmt.setString(3, u.getSenha());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "O Usuário " + u.getUsuario() + " foi cadastrado com sucesso!");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "O Usuário não foi salvo! Erro: " + ex);
        }
        finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public boolean checkLogin(String usuario, String senha) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM usuarios WHERE usuario = ? and senha = ?");
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
                    } 
        catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
        ConnectionFactory.closeConnection(con, stmt, rs);
    }
        return check;
    }
    public boolean checkCadastro(String email, String usuario) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM usuarios WHERE email = ?, usuario = ?");
            stmt.setString(1, email);
            stmt.setString(2, usuario);
            rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
                    } 
        catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
        ConnectionFactory.closeConnection(con, stmt, rs);
    }
        return check;
    }
}