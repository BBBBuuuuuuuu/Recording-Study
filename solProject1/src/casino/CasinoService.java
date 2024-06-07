package casino;

import member.MemberVO;
import method.Mth;

public class CasinoService {
	
	CasinoDAO cDao;
	
	public CasinoService() {
		cDao = new CasinoDAO();
	}
	
	public MemberVO chooseAcc(MemberVO loginInfo) {
		System.out.println("계좌코드와 비밀번호를 입력해주세요.");
		int accCode = Mth.inputInt("계좌코드 : ");
		String pwd = Mth.inputString("비밀번호 : ");
		
		boolean res = cDao.chooseAcc(loginInfo, accCode, pwd);
		if(res) {
			int[] coinEA = cDao.checkCoinEA(loginInfo);
			loginInfo.setCoinArr(coinEA);
			return loginInfo;
		}else {
			System.out.println("chooseAcc 실패");
			return null;
		}
	}
	
	public void accountToCoin(MemberVO loginInfo) {
		int accBalance = cDao.checkAccBalance(loginInfo);
		System.out.println("1만원 ▶ 10SC 1개로 교환됩니다..");
		int[] coinEA = inputCoinEA();
		int coinPrice = checkCoinPrice(coinEA);
		
		if(loginInfo.getAccCode() != 0) {
			if(accBalance >= coinPrice) {
				cDao.accountToCoin(loginInfo, coinEA, accBalance, coinPrice);
				int[] afterCoinEA = {loginInfo.getCoinArr()[0] + coinEA[0], loginInfo.getCoinArr()[1] + coinEA[1], loginInfo.getCoinArr()[2] + coinEA[2], loginInfo.getCoinArr()[3] + coinEA[3]};
				loginInfo.setCoinArr(afterCoinEA);
				System.out.println("교환되었습니다.");
			}else System.out.println("잔액이 부족합니다.");
		}else System.out.println("내정보에서 사용할 계좌를 지정해주세요.");
	}
	
	public void coinToAccount(MemberVO loginInfo) {
		int accBalance = cDao.checkAccBalance(loginInfo);
		System.out.println("10SC ▶ 1만원 으로 환전됩니다.");
		int[] coinEA = inputCoinEA();
		int coinPrice = checkCoinPrice(coinEA);
		if(loginInfo.getCoinArr()[0] >= coinEA[0] && loginInfo.getCoinArr()[1] >= coinEA[1] && loginInfo.getCoinArr()[2] >= coinEA[2] && loginInfo.getCoinArr()[3] >= coinEA[3]) {
			cDao.coinToAccount(loginInfo, coinEA, accBalance, coinPrice);
			int[] afterCoinEA = {loginInfo.getCoinArr()[0] - coinEA[0], loginInfo.getCoinArr()[1] - coinEA[1], loginInfo.getCoinArr()[2] - coinEA[2], loginInfo.getCoinArr()[3] - coinEA[3]};
			loginInfo.setCoinArr(afterCoinEA);
			System.out.println("환전되었습니다.");
		}else {
			System.out.println("코인이 부족합니다.");
		}
		
	}
	
	public int[] inputCoinEA() {
		System.out.println("코인 개수를 입력해주세요.");
		int SC500 = Mth.inputInt("500SC : ");
		int SC100 = Mth.inputInt("100SC : ");
		int SC50 = Mth.inputInt("50SC : ");
		int SC10 = Mth.inputInt("10SC :  ");
		
		int[] arr = {SC500, SC100, SC50, SC10};
		return arr;
	}

	public int checkCoinPrice(int[] coinArr) {
		int price = 0;
		
		price += 500000 * coinArr[0];
		price += 100000 * coinArr[1];
		price += 50000 * coinArr[2];
		price += 10000 * coinArr[3];
		
		return price;
	}
	
	public void checkAccBalance(MemberVO loginInfo) {
		int balance = cDao.checkAccBalance(loginInfo);
		
		if(balance != 0 || loginInfo.getCoinArr() != null) {
			Mth.lineBreak();
			System.out.println("계좌 " + loginInfo.getAccCode() + "의 잔액은 " + balance + "원\n"+ loginInfo.getName() + "님의 보유 칩은");
			System.out.println(loginInfo.printCoinEA());
		}else System.out.println("잔액조회 실패");
	}
}
