package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Division;

public class DivisionDao {
	private static final String DRIVER_NAME = "org.h2.Driver";
	private static final String JDBC_URL = "jdbc:h2:file:/Users/unokun/db/codecamp/ems3";
	private static final String DB_USER = "sa";
	private static final String DB_PASS = "";

	public List<Division> findAll() {
        List<Division> divisionList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // データベースへ接続
        	conn = getConnection();

            String sql = "SELECT id, name FROM divisions ORDER BY id";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                Division division = new Division(id, name);
                divisionList.add(division);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        	closeResultSet(rs);
        	closePreparedStatement(pstmt);
        	closeConnection(conn);
        }
        return divisionList;
	}

	public Division findById(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
        	conn = getConnection();
            String sql = "SELECT name FROM divisions WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                return new Division(id, name);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        	closeResultSet(rs);
        	closePreparedStatement(pstmt);
        	closeConnection(conn);
        }
        return null;
	}

	/**
	 * 部署挿入
	 * @param id	部署ID
	 * @param name	部署名
	 * @return	成功した場合true、それ以外false
	 */
	public boolean insert(String id, String name) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	conn = getConnection();
            String sql = "INSERT INTO divisions (id, name) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        	closePreparedStatement(pstmt);
        	closeConnection(conn);
        }
        return false;

	}

	/**
	 * 部署更新
	 * @param id	部署ID
	 * @param name	部署名
	 * @return	成功した場合true、それ以外false
	 */
	public boolean update(String id, String name) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	conn = getConnection();
            String sql = "UPDATE divisions set name = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, id);
            pstmt.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        	closePreparedStatement(pstmt);
        	closeConnection(conn);
        }
        return false;
	}

	/**
	 * 部署削除
	 * @param id	部署ID
	 * @return	成功した場合true、それ以外false
	 */
	public boolean delete(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	conn = getConnection();
            String sql = "DELETE FROM divisions WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.execute();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        	closePreparedStatement(pstmt);
        	closeConnection(conn);
        }
        return false;
	}

	/**
	 * データベース接続
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_NAME);
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	}
	/**
	 * ResultSetインスタンスのクローズ処理
	 * @param rs
	 */
	private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	/**
	 * PrepareStatementインスタンスのクローズ処理
	 * @param pstmt
	 */
	private void closePreparedStatement(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	/**
	 * データベース切断
	 * @param conn
	 */
	private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // データベース切断失敗時の処理
                e.printStackTrace();
            }
        }

	}
}
