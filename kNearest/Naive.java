/**
 *
 * Naive.java
 * created: Jan 8, 2013
 *
 */
package kNearest;

/**
 * @author sebastian
 *
 */
public class Naive implements Distance {

	@Override
	public double dist(String[] x, String[] y) {
		double d = 0;
		for(int i = 0; i < x.length && i < y.length; i++) {
			if(! x[i].equalsIgnoreCase(y[i])) {
				d++;
			}
		}

		return d;
	}

}

