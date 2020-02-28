package Gen;

import static java.lang.Math.PI;

public class Cromosoma2DF1 extends Cromosoma2D {
	public Cromosoma2DF1() {
		xmax = 12.1d;
		xmin = -3.0;
		ymax = 5.8d;
		ymin = 4.1d;
		setTolerancia(0.001d);
	}

	@Override
	public Object getFenotipo() {
		double cx = xmin + x*(xmax-xmin)/(Math.pow(2,lgenx)-1);
		double cy = ymin + y*(ymax-ymin)/(Math.pow(2,lgeny)-1);
		double res = 21.5 + cx*Math.sin(4*PI*cx)+cy*Math.sin(20*PI*cy);
		return res;
	}

	@Override
	public Object[] getGenes() {
		return new Object[] {x,y};
	}

	@Override
	public Cromosoma clonar() {
		return new Cromosoma2DF1();
	}
}
