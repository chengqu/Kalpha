package excelIO;
import gui.AnnotationPanel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
public class FileIO {


	public static AnnotationPanel panel = new AnnotationPanel();
	public static Iterator<Row> rowsIterator;
	public static String transcript;
	public static HSSFWorkbook workbook;
	public static FileInputStream file;
	public static Cell transcriptCell;
    public static Cell playerCell;
    public static Cell roleCell;
	public static Row row;
	public static Row firstRow;
	public static int marker;
	public static LinkedList<Row> rows = new LinkedList<Row>();
	public static ListIterator<Row> rowIterator;
	public static int hardCodedID;
	public static boolean isIDEnabled;
	
	public static String annotate(String lable, String trans)
	{
		StringBuilder s = new StringBuilder();
		trans = trans.trim();
		if(isIDEnabled){
			s.append("[" + lable + hardCodedID + " " + trans + "]");
		} else {
			s.append("[" + lable + " " + trans +"]");
		}
		return s.toString();
	}
	
	public static void display(Row row)
	{
		if(row.getRowNum() != 0)
		{
			transcriptCell = row.getCell(5);
			playerCell = row.getCell(0);
			roleCell = row.getCell(1);
         
			transcript = transcriptCell.getStringCellValue();
			String palyerID = playerCell.getStringCellValue();
			String role = roleCell.getStringCellValue();
         
         
			panel.dialogueField.setText(transcript);
			panel.lblPID.setText("PlayerID: " + palyerID);
			panel.lblROLE.setText("Role: " + role);
		}
	}
	
	public static void main(String[] args) {
		 
		 try {
             
	            file = new FileInputStream("SampleDialogues_47_48.xls");
	             
	            //Get the workbook instance for XLS file 
	            workbook = new HSSFWorkbook(file);
	 
	            //Get first sheet from the workbook
	            HSSFSheet sheet = workbook.getSheetAt(0);
	            //Iterate through each rows from first sheet
	            rowsIterator = sheet.iterator(); 
	            //populate the linkedList
	            while(rowsIterator.hasNext()){
	            	rows.add(rowsIterator.next());
	            }
	            rowIterator = rows.listIterator();
	            
                panel.frame.setVisible(true);
                firstRow = rowIterator.next();
                hardCodedID = (int)firstRow.getCell(1).getNumericCellValue();
                marker = (int) firstRow.getCell(0).getNumericCellValue();
                int temp = marker;
                row = firstRow;
                while(temp > 1 && rowIterator.hasNext())
                {
                	row = rowIterator.next();
                	panel.listModel.addElement(row.getCell(5).getStringCellValue());
                	temp--;
                }
                row = rowIterator.next();
                display(row);
                panel.lblID.setText("ID: " + hardCodedID);
                
                panel.btnNEXT.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{		
                		 if(rowIterator.hasNext()){                			 
                			 row = rowIterator.next();
                			 panel.listModel.addElement(panel.dialogueField.getText());
                			 display(row);
                			 panel.scrollBar.setValue(panel.scrollBar.getMaximum());
                		 }
                	}
                }
                );
                panel.btnPREV.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                			if(rowIterator.hasPrevious()){	
                				row = rowIterator.previous();
                				panel.listModel.removeElement(panel.listModel.lastElement());
                				display(row);
                				panel.scrollBar.setValue(panel.scrollBar.getMaximum());
                			}
                	}
                }
                );
                panel.btnGO.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(rowIterator.hasNext())
                		{
                			 	transcriptCell.setCellValue(panel.dialogueField.getText());
                			 	panel.listModel.addElement(panel.dialogueField.getText());
                				row = rowIterator.next();
                				display(row);
                				//For each row, iterate through each columns
        	                    marker = row.getRowNum();
        	                    panel.scrollBar.setValue(panel.scrollBar.getMaximum());
                		}
                	}
                }
                );
                
                panel.btnASI.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("As-I",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("As-I", transcript));
                		 
                	}
                }
                );
                
                panel.btnQYN.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("Q-YN",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("Q-YN", transcript));
                	}
                }
                );
                panel.btnQWH.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("Q-Wh",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("Q-Wh", transcript));
                	}
                }
                );
                panel.btnQD.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("Q-D",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("Q-D", transcript));
                	}
                }
                );
                panel.btnQC.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("Q-C",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("Q-C", transcript));
                	}
                }
                );
                panel.btnEC.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("EC",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("EC", transcript));
                	}
                }
                );
                panel.btnST.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("ST",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("ST", transcript));
                	}
                }
                );
                panel.btnAD.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("A-D",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("A-D", transcript));
                	}
                }
                );
                panel.btnACK.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("ACK",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("ACK", transcript));
                	}
                }
                );
                panel.btnBC.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("BC",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("BC", transcript));
                	}
                }
                );
                panel.btnAY.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("A-Y",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("A-Y", transcript));
                	}
                }
                );
                panel.btnAN.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("A-N",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("A-N", transcript));
                	}
                }
                );
                panel.btnDT.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("D-T",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("D-T", transcript));
                	}
                }
                );
                panel.btnRQS.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("Rq-S",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("Rq-S", transcript));
                	}
                }
                );
                panel.btnASS.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("As-S",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("As-S", transcript));
                	}
                }
                );
                panel.btnASM.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("As-M",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("As-M", transcript));
                	}
                }
                );
                panel.btnH.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("H",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("H", transcript));
                	}
                }
                );
                panel.btnP.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("P",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("P", transcript));
                	}
                }
                );
                panel.btnG.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("G",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("G", transcript));
                	}
                }
                );
                panel.btnCON.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("CON",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("CON", transcript));
                	}
                }
                );
                panel.btnDM.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		if(panel.dialogueField.getSelectedText() != null)
                			panel.dialogueField.replaceSelection(annotate("D-M",panel.dialogueField.getSelectedText()));
                		else
                			panel.dialogueField.setText(annotate("D-M", transcript));
                	}
                }
                );
                
                panel.btnINCID.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		hardCodedID++;
                		panel.lblID.setText("ID: " + hardCodedID);
                	}
                }
                );
                
                panel.btnDECID.addActionListener(new ActionListener()
                {
                	public void actionPerformed(ActionEvent e)
                	{
                		hardCodedID--;
                		panel.lblID.setText("ID: " + hardCodedID);
                	}
                }
                );
                
                panel.tglbtnENID.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent e) {
						isIDEnabled = panel.tglbtnENID.isSelected();
					}
                	
                });
	          
                //TODO: excel duan dian
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
                {
                	public void run() {
                		try{
                		file.close();
                		firstRow.getCell(0).setCellValue(marker);
                		firstRow.getCell(1).setCellValue(hardCodedID);
           	            FileOutputStream out = 
          	            new FileOutputStream(new File("SampleDialogues_47_48.xls"));
        	            workbook.write(out);
           	            out.close();
                		} catch (FileNotFoundException e) {
            	            e.printStackTrace();
            	        } catch (IOException e) {
            	            e.printStackTrace();
            	        }
                	}
                }
                ));
	         
	             
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 }
}
