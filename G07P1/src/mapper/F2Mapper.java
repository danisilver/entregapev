package mapper;

import org.jzy3d.plot3d.builder.Mapper;

public class F2Mapper extends Mapper {
	public double f(double x, double y) {

		double res = 0;
		res -= (Math.abs(Math.sin(x) * Math.cos(y) * Math.exp((Math.abs(1 - (Math.sqrt(x * x + y * y) / Math.PI))))));
		return res;
	}
}
