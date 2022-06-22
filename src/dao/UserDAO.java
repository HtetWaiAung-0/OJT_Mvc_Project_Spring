package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.UserRequestDTO;
import dto.UserResponseDTO;



public class UserDAO {
	public static Connection con = null;
	static {
		con = MyConnection.getConnection();
	}
	
	public int insertUserData(UserRequestDTO dto) {
        int result=0;
        String sql="insert into user(userId,userMail,userPassword,userRole) values(?,?,?,?)";
        try {

                PreparedStatement ps=con.prepareStatement(sql);
                ps.setString(1,dto.getUserId());
                ps.setString(2,dto.getUserMail());
                ps.setString(3,dto.getUserPassword());
                ps.setString(4,dto.getUserRole());
                result=ps.executeUpdate();
        } catch (SQLException e) {
        	
            System.out.println("Database error in inserting course data.");
            e.printStackTrace();
        }
        return result;
    }
	
	
	public void updateUserData(UserRequestDTO dto) {
		String sql = "update user set userMail=?,userPassword=?,userRole=? where userId=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);

			
            ps.setString(1,dto.getUserMail());
            ps.setString(2,dto.getUserPassword());
            ps.setString(3,dto.getUserRole());
            ps.setString(4,dto.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database error in update");
			e.printStackTrace();
		}

	}
	
	public void deleteUserData(UserRequestDTO dto) {
		String sql = "delete from user where userId=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Database error delete");
		}

	}
	
	public UserResponseDTO selectId(String id) {
		UserResponseDTO res = new UserResponseDTO();
		String sql = "select * from user where userId=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res.setUserId(rs.getString("userId"));
				res.setUserMail(rs.getString("userMail"));
				res.setUserPassword(rs.getString("userPassword"));
				res.setUserRole(rs.getString("userRole"));
			}
		} catch (SQLException e) {

			System.out.println("Database error in search");
			e.printStackTrace();
		}
		return res;

	}
	
	public UserResponseDTO selectIdAndMail(String id,String mail) {
		UserResponseDTO res = new UserResponseDTO();
		String sql = "select * from user where userId=? OR userMail=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2,mail);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res.setUserId(rs.getString("userId"));
				res.setUserMail(rs.getString("userMail"));
				res.setUserPassword(rs.getString("userPassword"));
				res.setUserRole(rs.getString("userRole"));
			}
		} catch (SQLException e) {
			System.out.println("Database error in search");
			e.printStackTrace();
		}
		return res;

	}
	
	public Boolean selectMailAndPassword(String mail,String password) {
		boolean a = false;
		UserResponseDTO res = new UserResponseDTO();
		String sql = "select * from user where userMail=? AND userpassword=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,mail);
			ps.setString(2,password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res.setUserId(rs.getString("userId"));
				res.setUserMail(rs.getString("userMail"));
				res.setUserPassword(rs.getString("userPassword"));
				res.setUserRole(rs.getString("userRole"));
				 a = true;
			}
		} catch (SQLException e) {
			System.out.println("Database error in search");
			e.printStackTrace();
		}
		return a;

	}
	
	public UserResponseDTO selectMail(String mail) {
		UserResponseDTO res = new UserResponseDTO();
		String sql = "select * from user where userMail=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,mail);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				res.setUserId(rs.getString("userId"));
				res.setUserMail(rs.getString("userMail"));
				res.setUserPassword(rs.getString("userPassword"));
				res.setUserRole(rs.getString("userRole"));
			}
		} catch (SQLException e) {
			System.out.println("Database error in search");
			e.printStackTrace();
		}
		return res;

	}
	
	public ArrayList<UserResponseDTO> selectAll(){
		ArrayList<UserResponseDTO> list = new ArrayList<>();
		String sql = "select * from user";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				UserResponseDTO res = new UserResponseDTO();
				res.setUserId(rs.getString("userId"));
				res.setUserMail(rs.getString("userMail"));
				res.setUserPassword(rs.getString("userPassword"));
				res.setUserRole(rs.getString("userRole"));
				list.add(res);
			}
		} catch (SQLException e) {
			System.out.println("Database error in search all");
			e.printStackTrace();
		}
		return list;
	}
	
	public int getId() {
		int res =0;
		String sql = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'mvcproject' AND TABLE_NAME = 'user';";
//		SELECT MAX(id) AS id FROM user
//		SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'mvcproject' AND TABLE_NAME = 'user';
	
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			 while (rs.next()){
		            res=rs.getInt(1);
		        }
			System.out.println(res);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("eroor getiing id");
			
			e.printStackTrace();
		}
		return res;
	}
}
