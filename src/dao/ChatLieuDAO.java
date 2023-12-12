package dao;

import ConnectDB.KetNoiSQL;
import entity.ChatLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatLieuDAO {

    public ChatLieuDAO() {
    }
    
    public ArrayList<ChatLieu> getAllChatLieu(){
        ArrayList<ChatLieu>listChatLieu = new ArrayList<>();
        KetNoiSQL.getInstance();
            Connection conn = KetNoiSQL.getConnection(); 
        try {
            String sql = "Select * from ChatLieu";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String maChatLieu = rs.getString(1);
                String chatLieu =  rs.getString(2);
                ChatLieu cl = new ChatLieu(maChatLieu, chatLieu);
                listChatLieu.add(cl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listChatLieu;
    }
    
    public ChatLieu getChatLieu(String id){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from ChatLieu where maChatLieu = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ChatLieu chatLieu = new ChatLieu();
                chatLieu.setMaChatLieu(rs.getString(1));
                chatLieu.setChatLieu(rs.getString(2));
                return chatLieu;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int updateChatLieu(ChatLieu chatLieu){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "update ChatLieu set chatLieu = (?) where maChatLieu = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, chatLieu.getChatLieu());
            stmt.setString(2, chatLieu.getMaChatLieu());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public int addChatLieu(ChatLieu chatLieu){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();    
        try {
            String sql = "insert into ChatLieu(maChatLieu, chatLieu) values (?, ?)";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, chatLieu.getMaChatLieu());
            stmt.setString(2, chatLieu.getChatLieu());
            return stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public ChatLieu getChatLieuByName(String name){
        KetNoiSQL.getInstance();
        Connection conn = KetNoiSQL.getConnection();
        
        try {
            String sql = "select * from ChatLieu where chatLieu = ?";
            PreparedStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ChatLieu chatLieu = new ChatLieu();
                chatLieu.setMaChatLieu(rs.getString(1));
                chatLieu.setChatLieu(rs.getString(2));
                return chatLieu;
            }
        } catch (SQLException ex) {
            Logger.getLogger(XuatXuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
