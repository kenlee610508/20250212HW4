package dao.impl;

import dao.MemberDao;
import model.Member;
import util.DbConnection;
import java.sql.*;

public class MemberDaoImpl implements MemberDao {
    private Connection conn;

    public MemberDaoImpl() {
        this.conn = DbConnection.getConnection();
    }

    @Override
    public boolean addMember(Member member) {
        if (member == null) return false;

        String sql = "INSERT INTO members (name, username, password, phone, address, tel, birthday) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getUsername());
            pstmt.setString(3, member.getPassword());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getAddress());
            pstmt.setString(6, member.getTel());
            pstmt.setDate(7, java.sql.Date.valueOf(member.getBirthday()));

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isUsernameExists(String username) {
        if (username == null || username.isEmpty()) return false;

        String sql = "SELECT COUNT(*) FROM members WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Member getMemberByUsername(String username) {
        if (username == null || username.isEmpty()) return null;

        String sql = "SELECT * FROM members WHERE username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Member(
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("tel"),
                    rs.getDate("birthday").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}