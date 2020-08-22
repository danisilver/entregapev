package mapper;

import static java.lang.Math.*;

import org.jzy3d.plot3d.builder.Mapper;

public class F11Mapper extends Mapper {

	@Override
	public double f(double arg0, double arg1) {
		return (100*sqrt(abs(arg1-0.01*pow(arg0,2))))+(0.01*abs(arg0+10));
	}

}
