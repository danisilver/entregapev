package mapper;

import org.jzy3d.plot3d.builder.Mapper;

public class F1Mapper extends Mapper{
	public double f(double x, double y) {
		return (21.5 + x * Math.sin(4 * Math.PI * x) + y * Math.sin(4 * Math.PI * y));
	}
}
