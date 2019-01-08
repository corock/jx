package chapter04.network.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	private static final String BASIC_SERVER = "ns4.epidc.co.kr";
	private static final String BASIC_ADDRESS = "211.115.194.4";
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = null;
		boolean flag = true;
		
		System.out.println("기본 서버:  " + BASIC_SERVER);
		System.out.println("Address:  " + BASIC_ADDRESS);
		
		do {
			if ("exit".equals(line)) {
				flag = false;
				break;
			}
			System.out.print("\n> ");
			line = sc.nextLine();

			try {
				InetAddress[] inetAddress = InetAddress.getAllByName(line);
				
				int index = 0;
				if (inetAddress != null) {
					System.out.println("서버:    " + BASIC_SERVER);
					System.out.println("Address:  " + BASIC_ADDRESS);
					System.out.println();
					System.out.println("권한 없는 응답:");
					System.out.println("이름:    " + inetAddress[index].getHostName());
				}
				for (InetAddress ia : inetAddress) {
					if (index == 0) {						
						System.out.println("Address:  " + ia.getHostAddress());
						index++;
						continue;
					}
					System.out.println("          " + ia.getHostAddress());
				}
				
			} catch (UnknownHostException e) {
				System.out.println("*** ns4.epidc.co.kr이(가) " +
								   line + "을(를) 찾을 수 없습니다. " +
								   "Non-existent domain"			
				);
				continue;
			}
		} while (flag);
	}
	
}
