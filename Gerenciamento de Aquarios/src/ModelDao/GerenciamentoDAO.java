/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDao;

import Connection.ConnectionFactory;
import Model.Aquario;
import Model.Gerenciamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lucas
 */
public class GerenciamentoDAO {
        public void create(Gerenciamento g){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO gerenciamento (aquario,ph,mortes,observacao,data,hora) VALUES (?,?,?,?,?,?)");
            stmt.setString(1,g.getAquario());
            stmt.setFloat(2,g.getPh());
            stmt.setInt(3,g.getMortes());
            stmt.setString(4,g.getObservacao());
            stmt.setString(5,g.getData());
            stmt.setString(6,g.getHora());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO!!!");
            
        }catch (SQLException ex) {
            Logger.getLogger(GerenciamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR, CHAME O JOAREZ: "+ex);
        } finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
        
    }
    
        public java.util. List<Gerenciamento> read() {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        java.util.List<Gerenciamento> Gerenciamento = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM gerenciamento");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Gerenciamento gerenciamento = new Gerenciamento();
                gerenciamento.setId(rs.getInt("id"));
                gerenciamento.setAquario(rs.getString("aquario"));
                gerenciamento.setPh(rs.getFloat("ph"));
                gerenciamento.setMortes(rs.getInt("mortes"));
                gerenciamento.setObservacao(rs.getString("observacao"));
                gerenciamento.setData(rs.getString("data"));
                gerenciamento.setHora(rs.getString("hora"));
                Gerenciamento.add(gerenciamento);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerenciamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt, rs);
        }
        
        return Gerenciamento;
        
    }
        
        public java.util. List<Gerenciamento> NewRead(String aquario) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        java.util.List<Gerenciamento> Gerenciamento = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM gerenciamento WHERE aquario LIKE ?");
            stmt.setString(1,"%"+aquario+"%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Gerenciamento gerenciamento = new Gerenciamento();
                gerenciamento.setId(rs.getInt("id"));
                gerenciamento.setAquario(rs.getString("aquario"));
                gerenciamento.setPh(rs.getFloat("ph"));
                gerenciamento.setMortes(rs.getInt("mortes"));
                gerenciamento.setObservacao(rs.getString("observacao"));
                gerenciamento.setData(rs.getString("data"));
                gerenciamento.setHora(rs.getString("hora"));
                Gerenciamento.add(gerenciamento);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GerenciamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt, rs);
        }
        
        return Gerenciamento;
        
    }   
    
    public void delete(Gerenciamento g){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja realmente Deletar esses dados?");
        if(resultado == JOptionPane.YES_OPTION){
        try{
            stmt = con.prepareStatement("DELETE FROM gerenciamento WHERE id = ?");
            stmt.setInt(1,g.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Dados Excluido com sucesso!!!");
            
        }catch (SQLException ex) {
            Logger.getLogger(GerenciamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR: "+ex);
        } finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
        
    }
    }
}
