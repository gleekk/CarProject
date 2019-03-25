package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//DB작업을 하는 자바빈 클래스 
public class CarDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//커넥션풀에서 커넥션객체를 얻는 메소드
	public void getCon(){		
		try {
			//WAS서버와 연결된 프로젝트의 모든 정보를 가지고 있는 컨텍스트 객체 얻기
			Context init = new InitialContext();
			//연결된 WAS서버에서 DataSource(커넥션풀 관리 객체) 검색해서 가져오기
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
			//DataSource(커넥션풀)에서 DB와 미리 연결 정보를 가지고 있는 접속 객체 얻기 
			con = ds.getConnection(); //DB접속
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}//getCon()
	
	/* 소형 , 중형, 대형 중  하나를 통해 차량을 검색 하기 위한 메소드*/
	public Vector<CarListBean>  getCartegoryCarList(String carcategory){
		
		//진행 순서: 소형, 중형, 대형중 선택한 값에 의해 검색한 레코드들을  -> CarListBean라는 DTO객체에 저장 -> Vector에 저장 
		
		//1.Vector객체 생성 : 참조변수이름 v
		Vector<CarListBean> v = new Vector<CarListBean>();
		
		//2.소형, 중형, 대형중 선택한 값에 의해 검색한 레코드들을 저장할~ CarListBean클래스 타입의  bean변수 선언
		CarListBean bean;
		
		try {
			//3.커넥션풀로 부터 커넥션 객체 얻기 
			getCon();
			
			//4.소형, 중형, 대형중 선택한 값에 의해 검색 SELECT구문 만들기
			String sql = "select * from carlist where carcategory=?";
			
			//5.  SELECT구문중에 ..?를 제외한  나머지 구문들을 담고 있는 SELECT를 실행할 객체 얻기 
			pstmt =  con.prepareStatement(sql);
			
			//6.  ? 값을 PreparedStatement객체에 셋팅 
			pstmt.setString(1, carcategory);
			
			//7.  PreparedStatement객체를 이용하여 DB에 SELECT검색 실행후 ~ 그결과를 ResultSet으로 받아옴
			rs = pstmt.executeQuery();
			
			//8. 반복을 하면서 DB로부터 검색한 결과데이터를 임시로 저장하고 있는 ResultSet객체에서 꺼내서  CarListBean객체에 저장
			while (rs.next()) {
				
				bean  = new CarListBean();
				//검색한 결과를 ResultSet에서 꺼내와서 CarListBean객체의 변수에 저장
				bean.setCarno(rs.getInt(1)); //차번호  
				bean.setCarname(rs.getString(2)); //차량 명
				bean.setCarcompany(rs.getString(3)); //차제조사명
				bean.setCarprice(rs.getInt(4));//차가격 
				bean.setCarusepeople(rs.getInt(5));//인승정보  
				bean.setCarinfo(rs.getString(6));//차정보 
				bean.setCarimg(rs.getString(7)); //차 이미지 명
				bean.setCarcategory(rs.getString(8));//차유형(소형,중형,대형)중 하나 
				
				v.add(bean); //백터에 추가 
				
			}//while끝		
			
			//DB접속객체 커넥션풀에 반납 
			con.close();
			
		} catch (Exception e) {
			System.out.println("getCartegoryCarList()메소드에서 오류 : " + e);
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
		
		return v; //백터 리턴
	}
	
	
	
	/*전체 차량 정보를 DB에서 검색하는 메소드 */
	public Vector<CarListBean> getAllCarList(){ //CarListController.java 서블릿 에서 호출하는 메소드 
		
		Vector<CarListBean> v = new Vector<CarListBean>();
		
		//DB로부터 하나의 자동차 정보를 검색해서 가져와서 저장할 CarListBean객체를 저장할 참조변수 선언
		CarListBean bean = null;
		
		try {
			//DB연결
			getCon();
			
			//쿼리 준비 : 전체 차량 검색
			String sql = "select * from carlist";
			
			//쿼리를 실행할 객체 얻기
			pstmt = con.prepareStatement(sql);
			
			//쿼리 실행후 결과를 리턴
			rs = pstmt.executeQuery();
			
			//반복하면서 검색한 자동차 레코드들을  ResultSet객체에서 꺼내서 CarListBean객체에 각각 저장
			while (rs.next()) {
				bean = new CarListBean();
				bean.setCarno(rs.getInt(1));//차번호 
				bean.setCarname(rs.getString(2));//차이름 담기
				bean.setCarcompany(rs.getString(3));//차제조사 이름 담기 
				bean.setCarprice(rs.getInt(4));//차가격 담기 
				bean.setCarusepeople(rs.getInt(5));//차 인승 정보 담기 
				bean.setCarinfo(rs.getString(6));//차정보 담기
				bean.setCarimg(rs.getString(7)); //차량 이미지 명 담기
				bean.setCarcategory(rs.getString(8)); //차종류(대형,소형,중형)중 하나 담기 
				
				v.add(bean); //백터에 CarListBean객체 추가 
			}//while반복 끝
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(con != null){con.close();}
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}//finally
		
		return v; //백터 리턴
		
	}//메소드 끝

	//하나의 자동차 정보를 검색하여 리턴 해주는 메소드 
	public CarListBean getOneCar(int carno) {
		
		//DB로부터 하나의 자동차 정보를 검색해서 가져와서 저장할 CarListBean객체를 저장할 참조변수 선언
		CarListBean bean = null;
		
		try {
			//DB연결
			getCon();
			
			//쿼리 준비 : 전체 차량 검색
			String sql = "select * from carlist where carno=?";
			
			//쿼리를 실행할 객체 얻기
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, carno);
			
			//쿼리 실행후 결과를 리턴
			rs = pstmt.executeQuery();
			
			//반복하면서 검색한 자동차 레코드들을  ResultSet객체에서 꺼내서 CarListBean객체에 각각 저장
			while (rs.next()) {
				bean = new CarListBean();
				bean.setCarno(rs.getInt(1));//차번호 
				bean.setCarname(rs.getString(2));//차이름 담기
				bean.setCarcompany(rs.getString(3));//차제조사 이름 담기 
				bean.setCarprice(rs.getInt(4));//차가격 담기 
				bean.setCarusepeople(rs.getInt(5));//차 인승 정보 담기 
				bean.setCarinfo(rs.getString(6));//차정보 담기
				bean.setCarimg(rs.getString(7)); //차량 이미지 명 담기
				bean.setCarcategory(rs.getString(8)); //차종류(대형,소형,중형)중 하나 담기 
				
			
			}//while반복 끝
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(con != null){con.close();}
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}//finally
		
		return bean; //리턴
	}

	//예약 정보를 DB에  insert
	public void insertCarOrder(CarOderBean cbBean) {
		
		try {
			//커넥션풀에서 커넥션 객체 얻기  (DB연결)
			getCon();
			//insert구문 준비
			String sql = "insert into carorder(carno,carqty,carreserveday,"
					   + "carbegindate,carins,carwifi,carnave,carbabyseat,memberphone,memberpass)"
					   + "values(?,?,?,?,?,?,?,?,?,?)";
					
			//?????제외한 insert구문을 담은 Preparedstatement(insert실행 객체 ) 얻기 
			pstmt = con.prepareStatement(sql);
			
			// ?값 PreparedStatement객체에 셋팅
			pstmt.setInt(1, cbBean.getCarno());
			pstmt.setInt(2, cbBean.getCarqty());
			pstmt.setInt(3, cbBean.getCarreserveday());
			pstmt.setString(4, cbBean.getCarbegindate());
			pstmt.setInt(5, cbBean.getCarins());
			pstmt.setInt(6, cbBean.getCarwifi());
			pstmt.setInt(7, cbBean.getCarnave());
			pstmt.setInt(8, cbBean.getCarbabyseat());
			pstmt.setString(9, cbBean.getMemberphone());
			pstmt.setString(10, cbBean.getMemberpass());
			
			
			//PreparedStatement객체를 이용하여  insert 실행 ~
		    pstmt.executeUpdate(); 
			
			//커넥션객체를 커넥션풀에 반납 (자원해제)
			con.close();
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
	}
	// 예약확인페이지 (CarReserveConfirm.jsp)에서 렌트카 예약시 작성한  폰번호와 패스워드에 해당하는 예약주문정보를 검색하기 위한 메소드
	public Vector<CarConfirmBean> getAllCarOrder(String memberphone, String memberpass) {
		
		Vector<CarConfirmBean> v = new Vector<CarConfirmBean>();
		
		//DB에서 검색한 렌트정보를 저장할 객체를 담을 변수 선언
		CarConfirmBean bean = null;
		
		try {
			//DB연결
			getCon();
			
			//SELECT구문 만들기
			//조건 : 예약한 날짜가 현재 날짜보다 크고 ,
			//     예약시 입력했던 비회원 전화번호와 패스워드에 해당하는 렌트정보를 검색하는데..
			//     carorder테이블과 carlist테이블을  natural조인하여 검색.!!!!!!!
			String sql = "select * from carorder natural join carlist where "
					 + "now() < str_to_date(carbegindate, '%Y-%m-%d') and "
					 + "memberphone=? and memberpass=?";
			//참고
			//SELECT 문에 *와 같이 별도의 컬럼 순서를 지정하지 않으면...
			//natural join의 기준이 되는 컬럼들이 다른 컬럼보다 먼저 출력 된다.
			// 이때  natural join은  join에 사용된 같은 이름의 컬럼을 중복출력하지않고 하나로 처리 한다.
			
			//?를 제외한 SELECT구문을 담은 Prepredstatemtnt객체 얻기 
			pstmt = con.prepareStatement(sql);
			//?셋팅
			pstmt.setString(1, memberphone); //예약확인시 입력한 전화번호
			pstmt.setString(2, memberpass); //예약확인시 입력한 패스워드
			//SELECT실행 ~ 
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bean = new CarConfirmBean(); //dto생성
				bean.setOrderid(rs.getInt(2)); //검색한 한줄정보중~~ 주문id를 rs에서 꺼내서 Dto에  담기 
				bean.setCarqty(rs.getInt(3));
				bean.setCarreserveday(rs.getInt(4));
				bean.setCarbegindate(rs.getString("carbegindate"));
				bean.setCarins(rs.getInt("carins"));
				bean.setCarwifi(rs.getInt(7));
				bean.setCarnave(rs.getInt(8));
				bean.setCarbabyseat(rs.getInt(9));
				bean.setCarname(rs.getString(12));
				bean.setCarprice(rs.getInt(14));
				bean.setCarimg(rs.getString(17));
				
				v.add(bean);//백터에 담기 
			}
			//자원해제
			con.close();
	
		} catch (Exception e) {
			System.out.println("getAllCarOrder메소드에서 오류 : " + e);
		}
		//백터 반환
		return v;
	}

	// 하나의 예약현황을 DB로부터 검색 해주는 메소드 ~ 
	public CarConfirmBean getOneOrder(int orderid) {
		
		CarConfirmBean cbean = null;
		
		try {
			  getCon();//커넥션풀로 부터 커넥션 객체 얻기 (DB연결)
			  
			  String sql  = "select * from carorder where orderid=?";
			  
			  pstmt = con.prepareStatement(sql);
			  pstmt.setInt(1, orderid);
			  
			  rs = pstmt.executeQuery();
			  
			  rs.next();
			  
			  cbean = new CarConfirmBean();
			  cbean.setOrderid(orderid);
			  cbean.setCarbegindate(rs.getString(5));
			  cbean.setCarreserveday(rs.getInt(4));
			  cbean.setCarins(rs.getInt(6));
			  cbean.setCarwifi(rs.getInt(7));
			  cbean.setCarnave(rs.getInt(8));
			  cbean.setCarbabyseat(rs.getInt(9));
			  
			  con.close();
			  
		} catch (Exception e) {
			  e.printStackTrace();
		}
		
		return cbean;
	}
	
	//예약 내역을 수정 하기 위해  수정DB작업 하는 메소드 
	public void carOrderUpdate(CarOderBean bean) {
		
		try {
			 getCon();
			
			 String sql = "update carorder set carbegindate=?, carreserveday=?, carqty=?"
					    + ", carins=?, carwifi=?, carbabyseat=? where orderid=? and memberpass=?";
			
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, bean.getCarbegindate());
			 pstmt.setInt(2, bean.getCarreserveday());
			 pstmt.setInt(3, bean.getCarqty());
			 pstmt.setInt(4, bean.getCarins());
			 pstmt.setInt(5, bean.getCarwifi());
			 pstmt.setInt(6, bean.getCarbabyseat());
			 pstmt.setInt(7, bean.getOrderid());
			 pstmt.setString(8, bean.getMemberpass());
			 
			 pstmt.executeUpdate();
			 
			 con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	//하나의 예약정보를 삭제 하는 메소드 
	public int carOrderDelete(int orderid, String memberpass) {
		
		int result = 0;
		
		try {
			getCon();
			String sql = "delete from carorder where orderid=? and memberpass=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, orderid);
			pstmt.setString(2, memberpass);
			
			result = pstmt.executeUpdate();//삭제에 성공하면 삭제에 성공한 레코드 갯수 반환 (1),
										   //삭제에 실패하면 0을 반환
			//자원해제 
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}












