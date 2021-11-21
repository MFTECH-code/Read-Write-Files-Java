package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Produto;

public class FileController {
	String strPath;
	
	public FileController(String strPath) {
		super();
		this.strPath = strPath;
	}

	public void read() {
		try(BufferedReader br = new BufferedReader(new FileReader(strPath))) {
			String line = br.readLine();
			
			while(line != null) {
				System.out.println(line);
				line = br.readLine();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Produto> readProducts() {
		Produto produto = null;
		List<Produto> produtos = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(strPath))) {
			
			String line = br.readLine();
			
			while(line != null) {
				produto = new Produto();
				String[] lineVetor = line.split(",");
				produto.setNome(lineVetor[0]);
				produto.setValor(Double.valueOf(lineVetor[1]));
				produto.setQuantidade(Integer.valueOf(lineVetor[2]));
				
				produtos.add(produto);
				line = br.readLine();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return produtos;
	}
	
	public void writeInCsv(List<Produto> produtos) {
		String folderPath = strPath.replace("\\dados.csv", "");
		File path = new File(folderPath);
		File[] folders = path.listFiles(File::isDirectory);
		
		// Verificar se o out existe
		if (!(folders.length > 0)) {
			boolean success = new File(path+"\\out").mkdir();
			System.out.println("Out Criado: " + success);	
		}

		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path+"\\out\\summary.csv"))) {
			
			for (Produto produto : produtos) {
				bw.write(produto.getNome()+","+produto.valorTotal());
				bw.newLine();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
	
	public boolean isCsv() {
		File path = new File(strPath);
		String fileName = path.getName();
		boolean isCsv = fileName.contains(".csv");
		
		return isCsv;
	}
	
}
