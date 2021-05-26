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
import model.bean.Produtos;

public class ProdutoDAO {
    public void create(Produtos p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produto (Código, Nome, Fornecedor, Preço, Tipo, Quantidade)VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getFornecedor());
            stmt.setDouble(4, p.getPreco());
            stmt.setString(5, p.getTipo());
            stmt.setInt(6, p.getQuantidade());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "O Produto " + p.getNome() + " foi salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "O Produto não foi salvo! Erro: " + ex);
        }
        finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Produtos> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produtos> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Produtos produto = new Produtos();
                produto.setCodigo(rs.getInt("Código"));
                produto.setNome(rs.getString("Nome"));
                produto.setFornecedor(rs.getString("Fornecedor"));
                produto.setPreco(rs.getDouble("Preço"));
                produto.setTipo(rs.getString("Tipo"));
                produto.setQuantidade(rs.getInt("Quantidade"));
                produtos.add(produto);
            }
                    } 
        catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
        ConnectionFactory.closeConnection(con, stmt, rs);
    }
        return(produtos);
    }
    
    public void update(Produtos p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE produto SET Nome = ?, Fornecedor = ?, Preço = ?, Tipo = ?, Quantidade = ? WHERE Código = ?");
            stmt.setInt(6, p.getCodigo());
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getFornecedor());
            stmt.setDouble(3, p.getPreco());
            stmt.setString(4, p.getTipo());
            stmt.setInt(5, p.getQuantidade());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "O Produto " + p.getNome() + " foi atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "O Produto não foi atualizado! Erro: " + ex);
        }
        finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Produtos p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE from produto WHERE Código = ?");
            stmt.setInt(1, p.getCodigo());
            JOptionPane.showMessageDialog(null, "O Produto " + p.getNome() + " foi excluido com sucesso!");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "O Produto não foi excluido! Erro: " + ex);
        }
        finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}