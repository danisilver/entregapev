package mapper;

import static java.lang.Math.*;

import org.jzy3d.plot3d.builder.Mapper;

public class F9Mapper extends Mapper {

	@Override
	public double f(double arg0, double arg1) {
		return -0.0001d*pow(abs(sin(arg0)*sin(arg1)*exp(abs(100-(sqrt(pow(arg0,2)+pow(arg1,2))/PI))))+1,0.1);
	}

}
