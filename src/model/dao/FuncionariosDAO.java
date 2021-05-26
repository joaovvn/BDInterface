package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Funcionarios;
public class FuncionariosDAO {
    public void create(Funcionarios f){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO funcionarios (Nome, Nascimento, CPF, RG, Sexo, Telefone, Celular, Email, Rua, Número, Complemento, CEP, Bairro, Cidade, Estado, Cargo, Setor, Matrícula, Salário)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getNascimento());
            stmt.setString(3, f.getCPF());
            stmt.setString(4, f.getRG());
            stmt.setString(5, f.getSexo());
            stmt.setString(6, f.getTelefone());
            stmt.setString(7, f.getCelular());
            stmt.setString(8, f.getEmail());
            stmt.setString(9, f.getEndereco());
            stmt.setInt(10, f.getNumero());
            stmt.setString(11, f.getComplemento());
            stmt.setString(12, f.getCEP());
            stmt.setString(13, f.getBairro());
            stmt.setString(14, f.getCidade());
            stmt.setString(15, f.getEstado());
            stmt.setString(16, f.getCargo());
            stmt.setString(17, f.getSetor());
            stmt.setInt(18, f.getMatricula());
            stmt.setDouble(19, f.getSalario());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "O Funcionário " + f.getNome() + " foi cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "O Funcionário não foi salvo! Erro: " + ex);
        }
        finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public List<Funcionarios> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Funcionarios> Funcionarios = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM funcionarios");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Funcionarios funcionarios = new Funcionarios();
                funcionarios.setNome(rs.getString("Nome"));
                funcionarios.setNascimento(rs.getString("Nascimento"));
                funcionarios.setCPF(rs.getString("CPF"));
                funcionarios.setRG(rs.getString("RG"));
                funcionarios.setSexo(rs.getString("Sexo"));
                funcionarios.setTelefone(rs.getString("Telefone"));
                funcionarios.setCelular(rs.getString("Celular"));
                funcionarios.setEmail(rs.getString("Email"));
                funcionarios.setEndereco(rs.getString("Rua"));
                funcionarios.setNumero(rs.getInt("Número"));
                funcionarios.setComplemento(rs.getString("Complemento"));
                funcionarios.setCEP(rs.getString("CEP"));
                funcionarios.setBairro(rs.getString("Bairro"));
                funcionarios.setCidade(rs.getString("Cidade"));
                funcionarios.setEstado(rs.getString("Estado"));
                funcionarios.setCargo(rs.getString("Cargo"));
                funcionarios.setSetor(rs.getString("Setor"));
                funcionarios.setMatricula(rs.getInt("Matrícula"));
                funcionarios.setSalario(rs.getDouble("Salário"));
            }
                    } 
        catch (SQLException ex) {
            Logger.getLogger(FuncionariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
        ConnectionFactory.closeConnection(con, stmt, rs);
    }
        return(Funcionarios);
    }
    public void delete(Funcionarios f){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE from produto WHERE Código = ?");
            stmt.setInt(1, f.getMatricula());
            JOptionPane.showMessageDialog(null, "O Funcionário " + f.getNome() + " foi excluido com sucesso!");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "O Funcionário não foi excluido! Erro: " + ex);
        }
        finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}