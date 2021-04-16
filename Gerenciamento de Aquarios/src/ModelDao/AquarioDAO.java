/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDao;

import Connection.ConnectionFactory;
import Model.Aquario;
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
public class AquarioDAO {
        public void create(Aquario a){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO aquario (nome,descricao) VALUES (?,?)");
            stmt.setString(1,a.getNome());
            stmt.setString(2,a.getDescricao());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "SALVO COM SUCESSO!!!");
            
        }catch (SQLException ex) {
            Logger.getLogger(AquarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERRO AO SALVAR, CHAME O JOAREZ: "+ex);
        } finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
        
    }
    
        public java.util. List<Aquario> read() {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        java.util.List<Aquario> Aquario = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM aquario");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                
                Aquario aquario = new Aquario();
                aquario.setId(rs.getInt("id"));
                aquario.setNome(rs.getString("nome"));
                aquario.setDescricao(rs.getString("descricao"));
                Aquario.add(aquario);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AquarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt, rs);
        }
        
        return Aquario;
        
    }
    
    public void delete(Aquario a){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        int resultado = JOptionPane.showConfirmDialog(null, "Deseja realmente Deletar esses dados?");
        if(resultado == JOptionPane.YES_OPTION){
        try{
            stmt = con.prepareStatement("DELETE FROM aquario WHERE id = ?");
            stmt.setInt(1,a.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!!!");
            
        }catch (SQLException ex) {
            Logger.getLogger(AquarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR: "+ex);
        } finally{
            ConnectionFactory.closeConnection((com.mysql.jdbc.Connection) con, stmt);
        }
        
    }
    }
}
