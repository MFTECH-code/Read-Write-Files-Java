package application;

import java.util.List;
import java.util.Scanner;

import entities.Produto;
import tools.FileController;

public class Program {	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Path of File with data.csv: ");
		String strPath = sc.next();
		FileController fc = new FileController(strPath);
		
		
		if (fc.isCsv()) {
			System.out.println("File content: ");
			fc.read();
			
			System.out.print("Continue[y,n]: ");
			char opt = sc.next().toLowerCase().charAt(0);
			
			if (opt == 'y') {
				List<Produto> produtos = fc.readProducts();
				fc.writeInCsv(produtos);			
			} else if (opt == 'n') {
				System.out.println("...");
			} else {
				System.out.println(opt + ": not exist command...");
			}
			
		} else {
			System.out.println("This file is not a csv...");
		}
		
		sc.close();
	}
}
