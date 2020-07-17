package mapper;

import org.jzy3d.plot3d.builder.Mapper;
import static java.lang.Math.sin;
import static java.lang.Math.pow;
import static java.lang.Math.PI;

public class F4Mapper extends Mapper {
	
	private int numgenes = 2;
	
	public double f(double x, double y) {
		
		double res = 0d;
		res  +=	  sin(x)
				* pow( sin( ((numgenes-1)*x*x)/PI), 20)
				+ sin(y)
				* pow( sin( ((numgenes)*y*y)/PI), 20);			
		for (int j = 1; j <= numgenes-1; j++) {
			double xn = 0;
			res  +=	  sin(xn)
					* pow( sin( (j*xn*xn)/PI), 20);
		}
		
		return -res;
	}

	public int getNumgenes() {
		return numgenes;
	}

	public void setNumgenes(int numgenes) {
		this.numgenes = numgenes;
	}
}

/*
 * res = -Math.sin(x) * Math.pow(Math.sin(((2) * x * x) / Math.PI), 20)
					- Math.sin(x) * Math.pow(Math.sin(((3) * x * x) / Math.PI), 20);
 */
