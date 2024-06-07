package method;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Mth {

	static Scanner scan = new Scanner(System.in);

	public static String inputString(String msg) {
		System.out.print(msg);
		return scan.nextLine();
	}

	public static int inputInt(String msg) {
		boolean flag = false;

		do {
			try {
				System.out.print(msg);
				int res = scan.nextInt();
				scan.nextLine();
				return res;
			} catch (InputMismatchException e) {
				Mth.inputString("");
				System.out.println("정수로 입력하세요.");
				Mth.lineBreak();
				flag = true;
			}
		} while (flag);
		return 0;
	}

	public static void lineBreak() {
		System.out.println("==========");
	}

	public static int menu(String msg) {
		lineBreak();
		System.out.println(msg);
		int num = inputInt("명령어 입력 : ");
		lineBreak();
		
		return num;
	}
	
	public static String showTime() {
		String date = null;
		String time = null;

		Calendar cal = Calendar.getInstance();

		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);

		date = year + "-" + month + "-" + day;
		time = hour + "-" + minute + "-" + second;

		return date + " " + time;
	}

}
