package mapper;

import static java.lang.Math.*;

import org.jzy3d.plot3d.builder.Mapper;

public class F8Mapper extends Mapper {

	@Override
	public double f(double arg0, double arg1) {//Easom
		return -cos(arg0)*cos(arg1)*exp(-pow(arg0-PI,2)-pow(arg1-PI,2));
	}

}
