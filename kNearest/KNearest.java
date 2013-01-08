/**
 * Jan 8, 2013
 * KNearest.java
 */
package kNearest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.JFileChooser;

/**
 * @author sebastian
 *
 */
public class KNearest {

	public String[][] data = null;

	public static String[][] readData(File file) {
		ArrayList<String[]> data_list = new ArrayList<String[]>();
		try {
			RandomAccessFile f = new RandomAccessFile(file, "r");
			String text = null;
			while ((text = f.readLine()) != null) {
				String[] instance = text.split(",");
				data_list.add(instance);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data_list.toArray(new String[data_list.size()][]);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(null);
		String[][] data = readData(jfc.getSelectedFile());
		for(String[] instance : data) {
			for(String attr : instance) {
				System.out.print(" " + attr);
			}
			System.out.println();
		}
	}

}
