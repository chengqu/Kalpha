package kalpha;

import java.awt.Label;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class convertToR {
	//construct the label hashmap
	public static HashMap<String,Integer> labelMap = new HashMap<>();
	public static String[] files = {"Cheng_43_44_old.xls","SampleDialogues_43_44.xls"};
	//public static String[] files = {"file1.xls","file2.xls"};
	public static String[] labels = {"As-I","As-M","As-S","A-D","ACK",
									"A-Y","A-N","Q-YN","Q-Wh","Q-D",
									"Q-C","EC","ST","BC","D-M","D-T","Rq-S",
									"P","H","G","CON"};
	public static int minSize;
	public static ArrayList<String> standardLines = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> listOfLines = new ArrayList<ArrayList<String>>();
	public static String resFileTemp = "R-Script";
	
	public static void main1(String[] args){
		//populate the label map
				for(int i=0; i < labels.length; i++){
					labelMap.put(labels[i], i);
				}
		String standard = "now this robot is silver white and is looks like it's in the bathroom ";
		String chunk1 = "[D-M now] [D-T this robot is silver white and is looks like it's in the bathroom ]";
		String chunk2 = "[D-T now this robot is silver white and is looks like it's in the bathroom ]";
		List<String> listOfString = new ArrayList<String>();
		listOfString.add(chunk1);
		listOfString.add(chunk2);
		for(int i = 0; i < 2; i++){
			String chunk = listOfString.get(i);
			//construct a 2D array for standard
			String[] standardArray = standard.trim().split(" ");
			String matrix[][] = new String[2][standardArray.length];
			//check if two chunks equal	
			if(!isChunk1EqualsChunk2(standard,chunk)){
				//populate the matrix with *
				for(int index = 0; index < standardArray.length; index++){
					matrix[i][index] = "NA";
				}
			} else {
				//populate the matrices with according keys
					 getKey(matrix,i,standardArray.length,chunk);
					
						 for(int b = 0; b <matrix[i].length; b++){
							 System.out.print(matrix[i][b] + " ");
						 }
						 System.out.print("\n");
					 
			}
		}
		
		
	}
	
	public static void main(String[] args){
		//populate the label map
		for(int i=0; i < labels.length; i++){
			labelMap.put(labels[i], i);
		}
		
		//get the less size of the lines
		minSize = contentsOfTheFileAsList(files[0]).size();
		standardLines = originalLinesAsList(files[0]);
		for(int i=0; i < files.length; i++){
			ArrayList<String> lines = contentsOfTheFileAsList(files[i]);
			listOfLines.add(lines);
			if(lines.size() < minSize){
				minSize = lines.size();
				standardLines = originalLinesAsList(files[i]);
			}
			else continue;
		}
		
		//convert each line to R matirces
		FileUtils.writeToFileAlreadyExisting(resFileTemp, "List <- list()");
		FileUtils.writeToFileAlreadyExisting(resFileTemp, "\n");
		for(int i=0; i < minSize; i++){
			FileUtils.writeToFileAlreadyExisting(resFileTemp, "tryCatch({");
			FileUtils.writeToFileAlreadyExisting(resFileTemp, "\n");
			FileUtils.writeToFileAlreadyExisting(resFileTemp, "nmm <- matrix(c(");
			for(int j=0; j < files.length; j++){
				String standard = standardLines.get(i);
				String chunk = listOfLines.get(j).get(i);
				//construct a 2D array for standard
				String[] standardArray = standard.trim().replaceAll("\\s+", " ").split(" ");
				String matrix[][] = new String[files.length][standardArray.length];
				//check if two chunks equal	
				if(!isChunk1EqualsChunk2(standard,chunk)){
					//populate the matrix with *
					for(int index = 0; index < standardArray.length; index++){
						matrix[j][index] = "NA";
					}
				} else {
					//populate the matrices with according keys
						 getKey(matrix,j,standardArray.length,chunk);
						 
						 for(int b = 0; b <matrix[j].length; b++){
							 if(j == files.length -1 && b == matrix[j].length-1)
								 FileUtils.writeToFileAlreadyExisting(resFileTemp,matrix[j][b]);
							 else
								 FileUtils.writeToFileAlreadyExisting(resFileTemp,matrix[j][b]+",");
						 }
				}
			}
			FileUtils.writeToFileAlreadyExisting(resFileTemp, "),"+"nrow="+files.length+",byrow=T)");
			FileUtils.writeToFileAlreadyExisting(resFileTemp, "\n");
			FileUtils.writeToFileAlreadyExisting(resFileTemp, "kalpha <- kripp.alpha(nmm)");
			FileUtils.writeToFileAlreadyExisting(resFileTemp, "\n");
			FileUtils.writeToFileAlreadyExisting(resFileTemp, "List[[length(List)+1]] <- kalpha");
			FileUtils.writeToFileAlreadyExisting(resFileTemp, "\n");
			FileUtils.writeToFileAlreadyExisting(resFileTemp, "}, error = function(e){print(0)})");
			FileUtils.writeToFileAlreadyExisting(resFileTemp, "\n");
			
		}
		FileUtils.writeToFileAlreadyExisting(resFileTemp, "x <- 0");
		FileUtils.writeToFileAlreadyExisting(resFileTemp, "\n");
		FileUtils.writeToFileAlreadyExisting(resFileTemp, "for(i in 1:length(List)){");
		FileUtils.writeToFileAlreadyExisting(resFileTemp, "\n");
		FileUtils.writeToFileAlreadyExisting(resFileTemp, "x = x + List[[i]]$value}");
		FileUtils.writeToFileAlreadyExisting(resFileTemp, "\n");
		FileUtils.writeToFileAlreadyExisting(resFileTemp, "x = x/length(List)");
	}
	
	public  static void getKey(String[][] matrix, int rowIndex, int colLength, String chunk) {
		String[] chunkArray = chunk.trim().replaceAll("\\s+", " ").split("\\]");
		int index = 0;
		for(String word:chunkArray){
			if(index < colLength){
				String[] wordArray = word.trim().replaceAll("\\s+", " ").split(" ");
				String label = wordArray[0].replaceAll("\\[", "").trim();
				if(!labelMap.containsKey(label)){
					for(int i=0; i<wordArray.length; i++){
						matrix[rowIndex][index] = "NA";
						index++;
					}
				} else { 
					for(int i=1; i<wordArray.length; i++){
						matrix[rowIndex][index] = labelMap.get(label).toString();
						index++;
					}
				}
					
			}
		}
	}
	public static boolean isChunk1EqualsChunk2(String chunk1, String chunk2) {
		String[] regex = new String[] {
				"As-I","As-M","As-S","A-D","ACK",
				"A-Y","A-N","Q-YN","Q-Wh","Q-D",
				"Q-C","EC","ST","BC","D-M","D-T","Rq-S",
				"P","H","G","CON","\\[","\\]"," "
		};
		for(String reg: regex){
			chunk1 = chunk1.replaceAll(reg, "");
			chunk2 = chunk2.replaceAll(reg, "");
		}
		
		if(chunk1.equals(chunk2))
			return true;
		else 
			return false;
	}
	public static ArrayList<String> contentsOfTheFileAsList(String fileName) {
				ArrayList<String> completeString = new ArrayList<String>();
				try {
						FileInputStream file = new FileInputStream(fileName);
						HSSFWorkbook workbook = new HSSFWorkbook(file);
						HSSFSheet sheet = workbook.getSheetAt(0);
						Iterator<Row> rowIterator = sheet.iterator();
						int limit = (int)rowIterator.next().getCell(0).getNumericCellValue();
					
						while(limit > 1 && rowIterator.hasNext()){
							Row row = rowIterator.next();
							completeString.add(row.getCell(5).getStringCellValue());
							limit--;
						}
					} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
						return completeString;
	}
	public static ArrayList<String> originalLinesAsList(String fileName) {
		ArrayList<String> completeString = new ArrayList<String>();
		try {
				FileInputStream file = new FileInputStream(fileName);
				HSSFWorkbook workbook = new HSSFWorkbook(file);
				HSSFSheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rowIterator = sheet.iterator();
				int limit = (int)rowIterator.next().getCell(0).getNumericCellValue();
			
				while(limit > 1 && rowIterator.hasNext()){
					Row row = rowIterator.next();
					completeString.add(row.getCell(4).getStringCellValue());
					limit--;
				}
			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
				return completeString;
}
}
