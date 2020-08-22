package mapper;

import static java.lang.Math.*;

import org.jzy3d.plot3d.builder.Mapper;

public class F10Mapper extends Mapper {

	@Override
	public double f(double arg0, double arg1) {
		return -((1+cos(12*sqrt(pow(arg0,2)+pow(arg1,2))))/(0.5*(pow(arg0,2)+pow(arg1,2))+2));
	}

}
