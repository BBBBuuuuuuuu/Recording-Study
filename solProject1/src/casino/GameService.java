package casino;

import java.util.ArrayList;
import java.util.Random;

import member.MemberVO;
import method.Mth;

public class GameService {

	Card card;
	ArrayList<Card> deck;
	ArrayList<Card> usedCard;
	Random rand;
	CasinoService cs;
	GameDAO gDao;

	public GameService() {
		card = new Card();
		deck = new ArrayList<Card>();
		rand = new Random();
		gDao = new GameDAO();
	}

	public int[] betCoin(MemberVO loginInfo) {
		cs = new CasinoService();
		System.out.print("베팅할 ");
		int[] coinEA = cs.inputCoinEA();

		gDao.betCoin(loginInfo, coinEA);

		return coinEA;
	}

	public void loseGame(MemberVO loginInfo, int[] betCoinEA) {
		System.out.println("게임에서 패배하여 배팅금을 모두 잃었습니다!");
		gDao.deleteBetTbl(loginInfo);
		gDao.loseCoin(loginInfo, betCoinEA);
	}

	public void winGame(MemberVO loginInfo, int[] coinEA) {
		System.out.println("게임에서 승리하였습니다");
		Mth.lineBreak();
		gDao.deleteBetTbl(loginInfo);
		gDao.getCoin(loginInfo, coinEA);
		System.out.printf("배팅금에 2배\n 500SC : %d\n100SC : %d\n50SC : %d\n10SC : %d개 획득하였습니다\n", 2 * coinEA[0],
				2 * coinEA[1], 2 * coinEA[2], 2 * coinEA[3]);
	}

	public void deleteBetTbl(MemberVO loginInfo) {
		gDao.deleteBetTbl(loginInfo);
	}

	public void RSP(MemberVO loginInfo) {
		String msg = "가위바위보는 동시에 가위, 바위, 보 중 하나를 보여주어 서로의 승패를 가르는 게임입니다.\n" + "상성 : 가위 < 바위 < 보 < 가위\n"
				+ "승리할 경우 배팅금의 2배를 얻습니다.";
		boolean run = true;
		System.out.println(msg);
		Mth.lineBreak();
		int[] coinEA = betCoin(loginInfo);
		do {
			Mth.lineBreak();
			int com = rand.nextInt(3); // 가위 : 0, 바위 : 1, 보 : 2
			System.out.println("가위, 바위, 보 중 하나를 입력해주세요.");
			String me = Mth.inputString("입력 : ");

			if (me.equals("가위")) {
				if (com == 0) {
					System.out.println("상대 : 가위");
					System.out.println("비겼습니다.\n재경기를 진행합니다.");
				} else if (com == 1) {
					System.out.println("상대 : 바위");
					loseGame(loginInfo, coinEA);
					run = false;
				} else if (com == 2) {
					System.out.println("상대 : 보");
					winGame(loginInfo, coinEA);
					run = false;
				}
			} else if (me.equals("바위")) {
				if (com == 0) {
					System.out.println("상대 : 가위");
					winGame(loginInfo, coinEA);
					run = false;
				} else if (com == 1) {
					System.out.println("상대 : 바위");
					System.out.println("비겼습니다.\n재경기를 진행합니다.");
				} else if (com == 2) {
					System.out.println("상대 : 보");
					loseGame(loginInfo, coinEA);
					run = false;
				}
			} else if (me.equals("보")) {
				if (com == 0) {
					System.out.println("상대 : 가위");
					loseGame(loginInfo, coinEA);
					run = false;
				} else if (com == 1) {
					System.out.println("상대 : 바위");
					winGame(loginInfo, coinEA);
					run = false;
				} else if (com == 2) {
					System.out.println("상대 : 보");
					System.out.println("비겼습니다.\n재경기를 진행합니다.");
				}
			} else
				System.out.println("올바른 단어를 입력해주세요.");

		} while (run);
	}

	public int sum(ArrayList<Card> hand) {
		int sum = 0;
		for (int i = 0; i < hand.size(); i++) {
			sum += hand.get(i).getNum();
		}
		return sum;
	}

	public ArrayList<Card> shuffle() {
		usedCard = null;
		ArrayList<Card> d = new ArrayList<Card>();
		for (int shape = 1; shape <= 4; shape++) {
			for (int num = 1; num <= 13; num++) {
				d.add(new Card(shape, num));
			}
		}
		return d;
	}

	public Card drawCard(ArrayList<Card> deck) {
		int num = rand.nextInt(deck.size());
		Card card = deck.get(num);
		deck.remove(num);

		if (card.getNum() > 10) {
			card.setNum(10);
		}
		return card;
	}

	public void printHandCard(ArrayList<Card> card) {
		for (int i = 0; i < card.size(); i++) {
			if (card.get(i).getNum() == 11) {
				System.out.print(this.card.printCard(card.get(i).getShape(), 1) + "  ");
			} else {
				System.out.print(this.card.printCard(card.get(i).getShape(), card.get(i).getNum()) + "  ");
			}
		}
	}

	public ArrayList<Card> chooseA(ArrayList<Card> Hand) {
		for (Card card : Hand) {
			if (card.getNum() == 1 || card.getNum() == 11) {
				if (sum(Hand) > 10)
					card.setNum(1);
				else
					card.setNum(11);
			}
		}
		return Hand;
	}

	public ArrayList<Card> checkWinner(ArrayList<Card> myHand, ArrayList<Card> dealerHand) {
		ArrayList<Card> winner = null;
		myHand = chooseA(myHand);
		dealerHand = chooseA(dealerHand);
		if (sum(myHand) > sum(dealerHand)) {
			winner = myHand;
		} else if (sum(myHand) < sum(dealerHand)) {
			winner = dealerHand;
		} else if (sum(dealerHand) == sum(myHand)) {
			return null;
		}
		return winner;
	}

	public void blackjack(MemberVO loginInfo) {
		String msg = "블랙잭은 카드를 한 장씩 받으며 숫자합 21을 맞추는 게임입니다.\n" + "카드의 합이 21이 넘어갈 경우 자동으로 패배하며 카드를 받을 때마다 오픈할 기회를 가지며.\n"
				+ "카드를 오픈했을 때 상대 카드의 합보다 높을경우 승리합니다.\n" + "승리할 경우 배팅금의 2배를 얻습니다.\n주의 : 1은 11로 바꿔 사용할 수 있습니다.";

		System.out.println(msg);
		Mth.lineBreak();
		int[] coinEA = betCoin(loginInfo);
		boolean flag = true;
		ArrayList<Card> myHand = new ArrayList<Card>();
		ArrayList<Card> dealerHand = new ArrayList<Card>();

		deck = shuffle();
		Mth.lineBreak();
		System.out.println("게임을 시작합니다!");
		Mth.inputString("카드 오픈은 \"오픈\"을 입력\n카드를 받으려면 엔터키를 눌러주세요..");
		while (flag && myHand != null && dealerHand != null) {
			boolean open = true;
			Card myCard = drawCard(deck);
			myHand.add(myCard);
			myHand = chooseA(myHand);

			Card dealerCard = drawCard(deck);
			dealerHand.add(dealerCard);
			dealerHand = chooseA(dealerHand);

			System.out.print("현재 패 ▶ ");
			printHandCard(myHand);
			System.out.println();

			if (sum(myHand) > 21) {
				Mth.lineBreak();
				System.out.println("패의 합계가 21을 넘어 패배하였습니다..");
				myHand = null;
			} else if (sum(dealerHand) > 21) {
				Mth.lineBreak();
				System.out.println("상대방의 패가 21을 넘어 승리하였습니다..");
				System.out.print("상대 패 ▶ ");
				printHandCard(dealerHand);
				System.out.println();
				dealerHand = null;
			} else if (sum(myHand) == 21) {
				Mth.lineBreak();
				System.out.println("패의 합계가 21이 되어 승리하였습니다.");
				dealerHand = null;
			} else if (sum(dealerHand) == 21) {
				Mth.lineBreak();
				System.out.println("상대방의 패가 21이 되어 패배하였습니다.");
				System.out.print("상대 패 ▶ ");
				printHandCard(dealerHand);
				System.out.println();
				myHand = null;
			}

			while (open && myHand != null && dealerHand != null) {
				String choice = Mth.inputString("");
				if (choice.length() == 0) {
					open = false;
					continue;
				} else if (choice.equals("오픈")) {
					Mth.lineBreak();
					chooseA(dealerHand);
					chooseA(myHand);
					System.out.print("상대 패 ▶ ");
					printHandCard(dealerHand);
					System.out.println("");
					open = false;
					flag = false;
				} else
					System.out.println("올바른 명령어를 입력해주세요.");
			}
		}

		if (myHand != null && dealerHand != null) {
			ArrayList<Card> winner = checkWinner(myHand, dealerHand);
			if (winner != null && winner == myHand) {
				winGame(loginInfo, coinEA);
			} else if (winner != null && winner == dealerHand) {
				loseGame(loginInfo, coinEA);
			}
		} else if (dealerHand == null && myHand != null) {
			winGame(loginInfo, coinEA);
		} else
			loseGame(loginInfo, coinEA);
	}

}
