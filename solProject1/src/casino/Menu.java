package casino;

import member.MemberService;
import member.MemberVO;
import method.Mth;

public class Menu {
	
	public static void mainMenu() {
		MemberService ms = new MemberService();
		
		while(true) {
			int num = Mth.menu("1. 회원가입 | 2. 로그인 | 3. 회원정보 찾기 | 4. 종료");
			
			if(num == 1) {
				ms.regist();
			}
			else if(num == 2) {
				MemberVO loginInfo = ms.login();
				if(loginInfo != null) {
					memberMenu(loginInfo);
				}else System.out.println("로그인 실패");
			}
			else if(num == 3) {
				searchMenu();
			}else if(num == 4) {
				ms.searchMember();
			}
			else if(num == 5) {
				System.out.println("종료합니다.");
				System.exit(0);
			}
			else System.out.println("올바른 명령어를 입력하세요");
			
		}
	}
	
	public static void memberMenu(MemberVO loginInfo) {
		boolean run = true;
		
		System.out.println("로그인 성공.");
		
		while(run) {
			int num = Mth.menu("1. 게임선택| 2. 코인 교환 | 3. 회원정보 | 4. 로그아웃");
			
			if(num == 1) {
				gameMenu(loginInfo);
			}else if(num == 2){
				transMenu(loginInfo);
			}else if(num == 3) {
				memberInfoMenu(loginInfo);
			}else if(num == 4) {
				System.out.println("로그아웃 되었습니다..");
				run = false;
			}else System.out.println("올바른 명령어를 입력하세요.");
		}
	}
	
	public static void gameMenu(MemberVO loginInfo) {
		boolean run = true;
		GameService gs = new GameService();
		
		System.out.println("해당화면으로 이동합니다.");
		while(run) {
			int num = Mth.menu("1. 가위바위보 | 2. 블랙잭 | 3. 잭팟 | 4. 이전화면");
			
			if(num == 1) {
				gs.RSP(loginInfo);
			}else if(num == 2) {
				gs.blackjack(loginInfo);
			}else if(num == 4) {
				System.out.println("이전화면으로 돌아갑니다.");
				run = false;
			}else System.out.println("올바른 명령어를 입력해주세요.");
		}
	}
	public static void transMenu(MemberVO loginInfo) {
		boolean run = true;
		CasinoService cs = new CasinoService();
		
		System.out.println("해당화면으로 이동합니다.");
		while(run) {
	
			int num = Mth.menu("1. 계좌 ▶ 코인 | 2. 코인 ▶ 계좌 | 3. 이전화면");
			
			if(num == 1) {
				cs.accountToCoin(loginInfo);
				run = false;
			}
			else if(num == 2) {
				cs.coinToAccount(loginInfo);
				run = false;
			}
			else if(num == 3) {
				System.out.println("이전화면으로 돌아갑니다.");
				run = false;
			}else System.out.println("올바른 명령어를 입력하세요.");
		}
	}
	
	public static void memberInfoMenu(MemberVO loginInfo) {
		boolean run = true;
		MemberService ms = new MemberService();
		CasinoService cs = new CasinoService();
		
		System.out.println("해당화면으로 이동합니다.");
		while(run) {
			int num = Mth.menu("1.내 정보 | 2. 정보 수정 | 3. 사용계좌 변경 | 4. 잔액확인 | 5. 거래내역 | 6. 탈퇴하기 | 7. 이전화면");
			
			if(num==1) {
				ms.printMyInfo(loginInfo);
			}else if(num == 2) {
				ms.updateMember();
			}else if(num == 3) {
				ms.chooseAcc(loginInfo);
			}else if(num == 4){
				cs.checkAccBalance(loginInfo);
			}else if (num == 5){
				ms.printTransInfo(loginInfo);
			}else if(num == 6) {
				ms.deleteMember();
			}else if(num == 7) {
				System.out.println("이전화면으로 돌아갑니다.");
				run = false;
			}else System.out.println("올바른 명령어를 입력하세요.");
		}
	}
	
	public static void searchMenu() {
	boolean run = true;
	MemberService ms = new MemberService();
	
		System.out.println("해당화면으로 이동합니다.");
		while(run) {
			int num = Mth.menu("1. 아이디 찾기 | 2. 비밀번호 찾기 | 3. 이전화면");
			
			if(num == 1) {
				ms.searchID();
				run = false;
			}
			else if(num ==2) {
				ms.searchPW();
				run = false;
			}
			else if(num == 3) {
				System.out.println("이전화면으로 돌아갑니다.");
				run = false;
		}
			else System.out.println("올바른 명령어를 입력하세요.");
		}
	}
}
