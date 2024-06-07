package member;

import java.util.ArrayList;

import casino.CasinoDAO;
import method.Mth;

public class MemberService {

	MemberVO mVo;
	MemberDAO mDao;
	CasinoDAO cDao;
	
	public MemberService() {
		mVo = new MemberVO();
		mDao = new MemberDAO();
		cDao = new CasinoDAO();
	}
	public void regist() {
		boolean flag = false;
		
		String id = Mth.inputString("ID : ");
			flag = mDao.checkID(id); // return true : 생성 가능
		
		if (!flag) {
			System.out.println("사용가능한 아이디입니다.");
			String pw = Mth.inputString("PW : ");
			String name = Mth.inputString("이름 : ");
			String phone = Mth.inputString("핸드폰 번호 : ");
			
			int checkID = mDao.inputMember(id, pw, name, phone);
			if(checkID == 0) {
				System.out.println("회원 생성 오류");
			}
			else {
				System.out.println("아이디가 생성되었습니다.");
			}
		}
		else System.out.println("이미 존재하는 아이디입니다.");
	}
	
	public MemberVO loginRes(String id, String pw) {
		MemberVO loginInfo = null;
		
		loginInfo = mDao.loginRes(id, pw);
		return loginInfo;
	}
	
	public MemberVO login() {
		MemberVO loginInfo = null;
		String id = Mth.inputString("ID : ");
		String pw = Mth.inputString("PW : ");
		
		boolean checkID = mDao.checkID(id);
		boolean checkBlacklist = cDao.checkBlackList(id);
		if(checkBlacklist) {
			if(checkID) {
				if(mDao.checkPW(id, pw)) {
					loginInfo = mDao.loginRes(id, pw);
					mDao.setCoinEA(loginInfo);
					return loginInfo;
				}else System.out.println("비밀번호가 일치하지 않습니다.");
			}else System.out.println("존재하지 않는 아이디입니다..");
		}else System.out.println("정지된 계정입니다.");
		return null;
	}
	
	public void searchMember() {
		String id = Mth.inputString("변경 전 ID : ");
		String pw = Mth.inputString("변경 전 PW : ");
		
		boolean checkID = mDao.checkID(id);
		boolean checkPW = mDao.checkPW(id, pw);
		
		if(checkID && checkPW) {
			Mth.lineBreak();
			System.out.println("변경할 정보를 입력하세요");
			String newUserid = Mth.inputString("ID : ");
			String newPwd = Mth.inputString("PW : ");
			String newName = Mth.inputString("이름 : ");
			String newPhone = Mth.inputString("전화번호 : ");
			
			int result = mDao.searchMember(id, newUserid, newPwd, newName, newPhone);
			
			if(result ==0) 	System.out.println("수정 실패"); else System.out.println(id + "의 정보가 수정되었습니다.");
			}
		}
	
	public void printMyInfo(MemberVO loginInfo) {
		System.out.printf("아이디 : %s\n비밀번호 : %s\n이름 : %s\n전화번호 : %s\n사용계좌 : %s\n", loginInfo.getId(), loginInfo.getPw(), loginInfo.getName(), loginInfo.getPhone(), loginInfo.getAccCode());
	}
	
	public void updateMember() {
		String id = Mth.inputString("변경 전 ID : ");
		String pw = Mth.inputString("변경 전 PW : ");
		
		boolean checkID = mDao.checkID(id);
		boolean checkPW = mDao.checkPW(id, pw);
		
		if(checkID && checkPW) {
			Mth.lineBreak();
			System.out.println("변경할 정보를 입력하세요");
			String newUserid = Mth.inputString("ID : ");
			String newPwd = Mth.inputString("PW : ");
			String newName = Mth.inputString("이름 : ");
			String newPhone = Mth.inputString("전화번호 : ");
			
			int result = mDao.updateMember(id, newUserid, newPwd, newName, newPhone);
			
			if(result ==0) 	System.out.println("수정 실패"); else System.out.println(id + "의 정보가 수정되었습니다.");
			}
		}

	public void chooseAcc(MemberVO loginInfo) {
		int accCode = Mth.inputInt("변경할 계좌의 코드를 입력하세요 : ");
		
		int res =mDao.chooseAcc(loginInfo, accCode);
		if(res != 0) {
			loginInfo.setAccCode(accCode);
			System.out.println("이용계좌가 " + accCode + "로 변경되었습니다.");
		}else {
			System.out.println("변경에 실패하였습니다.");
		}
	}
	
	public void deleteMember() {
		String id = Mth.inputString("ID : ");
		String pw = Mth.inputString("PW : ");
		
		boolean checkID = mDao.checkID(id);
		boolean checkPW = mDao.checkPW(id, pw);
		
		if (checkID && checkPW) {
			mDao.deleteMember(id);
		}
		else {
			System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
	}
	
	public void searchID() {
		String name = Mth.inputString("찾을 아이디의 이름 입력 : ");
		
		String id = mDao.searchID(name);
		
		if(id != null) System.out.println(name + "님의 아이디는 [ " + id +" ]입니다.");
		else System.out.println("아이디찾기 실패");
	}
	
	public void searchPW() {
		String id = Mth.inputString("찾을 비밀번호의 아이디 입력 : ");
		
		String pw = mDao.searchPW(id);
		
		if(id != null) System.out.println(id + "의 비밀번호는 [ " + pw +" ]입니다.");
		else System.out.println("비밀번호찾기 실패");
	}
	
	public void printTransInfo(MemberVO loginInfo) {
		ArrayList<TransVO> trans = mDao.printTransInfo(loginInfo);
		
		System.out.println(trans.get(0).getAccCode() + "의 거래내역\n");
		for(TransVO info : trans) {
			System.out.println(info.getPrice() + "원 "+ info.getState()+ "후 잔액 : " + info.getBalance() + "원\n[거래일자 : " + info.getDate() + "]");
		}
		
	}
}
