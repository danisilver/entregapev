package mapper;

import static java.lang.Math.*;

import org.jzy3d.plot3d.builder.Mapper;

public class F7Mapper extends Mapper {//DeJong

//	public double a=20;
//	public double b=0.2;
//	public double c=PI;
	
	public int a[][] = {
			{-32,-16,0,16,32,-32,-16,0,16,32,-32,-16,0,16,32,-32,-16,0,16,32,-32,-16,0,16,32},
			{-32,-32,-32,-32,-32,-16,-16,-16,-16,-16,0,0,0,0,0,16,16,16,16,16,32,32,32,32,32}
	}; 
	
	@Override
	public double f(double arg0, double arg1) {
		double res = 0.002;
		for (int i = 0; i < 25; i++) {
			res+= 1d/((i+1)+pow((arg0-a[0][i]),6)+pow((arg1-a[1][i]),6));
		}
		return 1d/res;
	}

}
