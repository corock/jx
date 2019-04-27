package chapter03.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {

	public static void main(String[] args) {
		BufferedReader br = null;
		
		try {
			// 기반 스트림
			FileReader fr = new FileReader("./src/chapter03/io/BufferedReaderTest.java");
			
			// 보조 스트림
			br = new BufferedReader(fr);
			
			// input
			int index = 0;
			String line = null;
			while ((line = br.readLine()) != null) {
				index++;
				System.out.print(index + ": ");
				System.out.print(line);
				System.out.print("\n");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e);
		} catch (IOException e) {
			System.out.println("IO error: " + e);
		} finally {
			try {
				if (br != null) { br.close(); }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
