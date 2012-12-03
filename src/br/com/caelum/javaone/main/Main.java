package br.com.caelum.javaone.main;

import java.util.Scanner;

import br.com.caelum.javaone.converter.JsonConverter;
import br.com.caelum.javaone.converter.JsonForkJoinConverter;
import br.com.caelum.javaone.converter.JsonMultiThreadConverter;
import br.com.caelum.javaone.converter.JsonSequentialConverter;
import br.com.caelum.javaone.generator.MassGenerator;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("What to do?");
			System.out.println("1 - Generate mass");
			System.out.println("2 - Convert to Json (single thread)");
			System.out.println("3 - 3Convert to Json (Multi thread)");
			System.out.println("4 - Convert to Json (Fork/Join)");
			System.out.println("5 - Exit");
			System.out.println();

			int choice = sc.nextInt();

			switch (choice) {

			case 1: {
				System.out.println("How many ? (int)");
				int size = sc.nextInt();
				new MassGenerator().generate(size);
				System.out.println();
				break;
			}
			case 2: {
				convert(new JsonSequentialConverter());
				break;
			}
			case 3:{
				convert(new JsonMultiThreadConverter());
				break;
			}
			case 4:{
				convert(new JsonForkJoinConverter());
				break;
			}
			case 5:
				System.exit(0);
			}
			System.out.println("\n========================================================\n");
		}

	}

	protected static void convert(JsonConverter converter) throws Exception {
		System.out.println("Converting....");
		long ini = System.currentTimeMillis();
		converter.convert();
		long end = System.currentTimeMillis();
		System.out.printf("Total Time: [ %d ] milliseconds %n", end - ini);
		System.out.println();
	}
}
