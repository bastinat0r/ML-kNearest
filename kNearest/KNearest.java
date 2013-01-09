package kNearest;
/**
 * Jan 8, 2013
 * KNearest.java
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JFileChooser;

/**
 * 
 * @author robert & sebastian
 * 
 */

public class KNearest {

	HashMap<String[], String> training_data;
	HashMap<String[], String> testing_data;
	int k;
	Distance dist;

	public KNearest(File f, int k, Distance dist) {
		readData(f);
		testing_data = new HashMap<String[], String>();
		this.k = k;
		this.dist = dist;
		for (String[] instance : training_data.keySet()) {
			if (Math.random() < 1.0 / 3) {
				testing_data.put(instance, training_data.get(instance));
			}
		}
		for (String[] instance : testing_data.keySet())
			training_data.remove(instance);
		System.out.println("Training Elements: " + training_data.size());
		System.out.println("Testing Elements: " + testing_data.size());
		test();
	}

	public String classify(String[] instance) {
		HashMap<String[], Double> distances = new HashMap<String[], Double>();
		for (String[] te : training_data.keySet()) {
			double d = dist.dist(instance, te);
			distances.put(te, d);
			//System.out.println(Arrays.toString(te)+"->"+d);
		}
		HashMap<String, Integer> occ = new HashMap<String, Integer>();
		for (int i = 0; i < k; i++) {
			double sd = Collections.min(distances.values());
			String bc = null;
			for (String[] te : distances.keySet()) {
				if (distances.get(te) == sd) {
					bc = training_data.get(te);
					distances.remove(bc);
					Integer o = occ.get(bc);
					if (o == null) {
						o = 1;
					} else {
						o++;
					}
					occ.put(bc, o);
					break;
				}
			}
		}
		int bestocc = Collections.max(occ.values());
		for (String c : occ.keySet()) {
			if (occ.get(c) == bestocc) {
				return c;
			}
		}
		return null;
	}

	public void test() {
		int correct_classifications = 0;
		for (String[] instance : testing_data.keySet()) {
			String c = classify(instance);
			if (c.equalsIgnoreCase(testing_data.get(instance))){
				correct_classifications++;
			}
		}
		System.out.println("Correct: "
				+ (correct_classifications * 1.0 / testing_data.size())+"%");

	}

	public void readData(File file) {
		training_data = new HashMap<String[], String>();
		try {
			RandomAccessFile f = new RandomAccessFile(file, "r");
			String instane = null;
			while ((instane = f.readLine()) != null) {
				String[] d = instane.split(",");
				training_data.put(Arrays.copyOfRange(d, 0, d.length - 1),
						d[d.length - 1]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(null);
		new KNearest(jfc.getSelectedFile(), 5, new Naive());

	}

}
