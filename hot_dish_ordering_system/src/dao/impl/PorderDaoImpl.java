package dao.impl;

import dao.PorderDao;
import model.Porder;
import util.DbConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PorderDaoImpl implements PorderDao {
    private Connection conn;

    public PorderDaoImpl() {
        this.conn = DbConnection.getConnection();
    }

    @Override
    public boolean addPorder(Porder porder) {
        String sql = "INSERT INTO porders (member_name, dish1, dish2, dish3, dish4, dish5, order_date) VALUES (?, ?, ?, ?, ?, ?, NOW())";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, porder.getMemberName());
            ps.setInt(2, porder.getDish1());
            ps.setInt(3, porder.getDish2());
            ps.setInt(4, porder.getDish3());
            ps.setInt(5, porder.getDish4());
            ps.setInt(6, porder.getDish5());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("新增訂單失敗：" + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Porder> getAllPorders() {
        List<Porder> porders = new ArrayList<>();
        String sql = "SELECT * FROM porders";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                porders.add(new Porder(
                        rs.getInt("id"),
                        rs.getString("member_name"),
                        rs.getInt("dish1"),
                        rs.getInt("dish2"),
                        rs.getInt("dish3"),
                        rs.getInt("dish4"),
                        rs.getInt("dish5"),
                        rs.getTimestamp("order_date").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            System.err.println("查詢訂單失敗：" + e.getMessage());
        }
        return porders;
    }

    @Override
    public boolean updatePorder(int id, Porder porder) {
        String sql = "UPDATE porders SET dish1=?, dish2=?, dish3=?, dish4=?, dish5=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, porder.getDish1());
            ps.setInt(2, porder.getDish2());
            ps.setInt(3, porder.getDish3());
            ps.setInt(4, porder.getDish4());
            ps.setInt(5, porder.getDish5());
            ps.setInt(6, id);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("更新訂單失敗：" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePorder(int id) {
        String sql = "DELETE FROM porders WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("刪除訂單失敗：" + e.getMessage());
            return false;
        }
    }
}
