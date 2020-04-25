package gen;

import static java.lang.Math.PI;

public class Cromosoma2DF1 extends Cromosoma2D {
	
	private double fenotype_;
	private boolean evaluated;

	public Cromosoma2DF1() {
		setXmax(12.1d);
		setXmin(-3.0);
		setYmax(5.8d);
		setYmin(4.1d);
		evaluated = false;
	}

	@Override
	public Object getFenotipo() {
		if(evaluated) return fenotype_;
		
		double cx = getXmin() + (Integer)getGen(0) *(getXmax()-getXmin())/(Math.pow(2,getLgenx())-1);
		double cy = getYmin() + (Integer)getGen(1) *(getYmax()-getYmin())/(Math.pow(2,getLgeny())-1);
		fenotype_ = 21.5 + cx*Math.sin(4*PI*cx)+cy*Math.sin(20*PI*cy);
		evaluated = true;
		return fenotype_;
	}

	@Override
	public Cromosoma clonar() {
		Cromosoma2DF1 crom = new Cromosoma2DF1();
		crom.setTolerancia(getTolerancia());
		crom.setGen(0, getGen(0));
		crom.setGen(1, getGen(1));
		crom.fenotype_ = fenotype_;
		crom.evaluated = evaluated;
		return crom;
	}

	@Override
	public int getGenLen(int i) {
		if(i==0) return getLgenx();
		if(i==1) return getLgeny();
		return 0;
	}

	@Override
	public void setGen(int i, Object g) {
		super.setGen(i, g);
		evaluated = false;
	}
	
	@Override
	public void setTolerancia(double tolerancia) {
		super.setTolerancia(tolerancia);
		evaluated = false;
	}

	@Override
	public double value2optimize() {
		return (double) getFenotipo();
	}
	
}

