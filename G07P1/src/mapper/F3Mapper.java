package mapper;

import org.jzy3d.plot3d.builder.Mapper;

public class F3Mapper extends Mapper {
	public double f(double x, double y) {
		double a, b;
		a = b = 0;
		for (int i = 1; i < 6; i++) {
			a += i * Math.cos(((i + 1) * x) + 1);
		}
		for (int i = 1; i < 6; i++) {
			b += i * Math.cos(((i + 1) * y) + 1);
		}
		return a * b;
	}
}
