package net.member.db;

import java.security.spec.DSAGenParameterSpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DB작업 담당
public class MemberDAO {

	private Connection getConnection() throws Exception{
		Connection con = null;
		Context init = new InitialContext();
		DataSource datasource = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		con = datasource.getConnection();
		return con;
	}
	
	//회원가입 메소드 
	public boolean insertMember(MemberBean mb){
		
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		
		//회원가입 성공 여부 저장
		int result = 0;
		
		try {
			con = getConnection(); //DB접속 객체 얻기 
			sql = "insert into member2(id,pass,name,age,date,email,address,phone,mobile)"
				+ "values(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getId()); 
			pstmt.setString(2, mb.getPass());
			pstmt.setString(3, mb.getName());
			pstmt.setInt(4, mb.getAge());
			pstmt.setTimestamp(5,mb.getDate());
			pstmt.setString(6, mb.getEmail());
			pstmt.setString(7, mb.getAddress());
			pstmt.setString(8, mb.getPhone());
			pstmt.setString(9, mb.getMobile());
			
			result = pstmt.executeUpdate(); //회원가입 성공 하면 1을 리턴 실패시 0을 리턴
			
			//만약에 회원가입에 성공 하면 true리턴
			if(result != 0){
				return true;
			}		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				//자원해제
				if(pstmt != null){
					pstmt.close();
				}
				if(con != null){
					con.close();
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		}
		return false;
	}

	//로그인 처리시 사용하는 메소드 
	//login.jsp에서 사용자로부터입력한  id,pass값을 매개변수로 전달받아 DB에 있는 id와 pass값과 비교하여 로그인 처리 하는 메소드
	public int userCheck(String id, String pass) {
		
		Connection con = null;
		String sql = "";
		PreparedStatement pstmt = null;
		int check = -1; //  1 -> 아이디, 비밀번호 맞음
				 		//  0 -> 아이디 맞음, 비밀번호 틀림
						//  -1 -> 아이디 틀림
		
		ResultSet rs = null;
		
		try {
			con = getConnection(); //커넥션풀 내부에 있는 커넥션얻기
			
			sql = "select pass from member2 where id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){ //아이디가 존재 함.
				//로그인시 입력한 pass와 DB에 저장되어 있는 pass가 같으면
				if(pass.equals(rs.getString("pass"))){
					
					check = 1; //아이디 맞음, 비밀번호 맞음 을 나타내는 판별값 저장
				
				}else{ //아이디 는 맞으나 비밀번호가 틀리면
					check = 0; //아이디 맞음, 비밀번호 틀림 을 나타내는 판별값 저장
				}	
			}else{//아이디에 해당하는 pass가 없으면(아이디가 없다는 뜻과 같음)
				check = -1; //아이디 없음 판별값 저장
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null)try{rs.close();}catch(SQLException ex){ex.printStackTrace();}
			if(pstmt != null)try{pstmt.close();}catch(SQLException ex){ex.printStackTrace();}
			if(con != null)try{con.close();}catch(SQLException ex){ex.printStackTrace();}		
		}	
		return check;
	}


	
}











