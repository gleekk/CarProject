package db;

//예약한 정보를 저장 하기 위한 DTO
public class CarOderBean {

	private int orderid; //렌트 주문 id값 저장
	
	//차번호를 이용하여  조인하여 검색 하기 위함.
	private int carno;//렌트할 차번호 저장
	private int carqty;//대여 수량 저장
	private int carreserveday;//대여기간
	private String carbegindate;//대여 시작 날짜
	private int carins;//보험적용 
	private int carwifi;
	private int carnave;
	private int carbabyseat;
	
	private String memberphone;//비회원으로 예약한 사람의 폰번호 
	private String memberpass; //비회원으로 예약한 사람의 주문 패스워드 저장 
	
	// getter,setter
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getCarno() {
		return carno;
	}
	public void setCarno(int carno) {
		this.carno = carno;
	}
	public int getCarqty() {
		return carqty;
	}
	public void setCarqty(int carqty) {
		this.carqty = carqty;
	}
	public int getCarreserveday() {
		return carreserveday;
	}
	public void setCarreserveday(int carreserveday) {
		this.carreserveday = carreserveday;
	}
	public String getCarbegindate() {
		return carbegindate;
	}
	public void setCarbegindate(String carbegindate) {
		this.carbegindate = carbegindate;
	}
	public int getCarins() {
		return carins;
	}
	public void setCarins(int carins) {
		this.carins = carins;
	}
	public int getCarwifi() {
		return carwifi;
	}
	public void setCarwifi(int carwifi) {
		this.carwifi = carwifi;
	}
	public int getCarnave() {
		return carnave;
	}
	public void setCarnave(int carnave) {
		this.carnave = carnave;
	}
	public int getCarbabyseat() {
		return carbabyseat;
	}
	public void setCarbabyseat(int carbabyseat) {
		this.carbabyseat = carbabyseat;
	}
	public String getMemberphone() {
		return memberphone;
	}
	public void setMemberphone(String memberphone) {
		this.memberphone = memberphone;
	}
	public String getMemberpass() {
		return memberpass;
	}
	public void setMemberpass(String memberpass) {
		this.memberpass = memberpass;
	}
	

	
	
	
}









