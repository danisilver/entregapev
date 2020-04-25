package gen;

public class Cromosoma2DF3 extends Cromosoma2D{

	private double fenotype_;
	private boolean evaluated;
	
	public Cromosoma2DF3() {
		setXmax(10);
		setXmin(-10);
		setYmax(10);
		setYmin(-10);
		evaluated = false;
	}
	
	@Override
	public Object getFenotipo() {
		if(evaluated) return fenotype_;
		
		double cx = getXmin() + (Integer)getGen(0)*(getXmax()-getXmin())/(Math.pow(2,getLgenx())-1);
		double cy = getYmin() + (Integer)getGen(1)*(getYmax()-getYmin())/(Math.pow(2,getLgeny())-1);
		double a=0;
		for (int i = 1; i < 6 ; i++) {
			a+=i*Math.cos(((i+1)*cx)+i);
		}
		double b=0;
		for (int i = 1; i < 6; i++) {
			b+=i*Math.cos(((i+1)*cy)+i);
		}
		fenotype_ = a*b;
		evaluated = true;
		return fenotype_;
	}

	@Override
	public Cromosoma clonar() {
		Cromosoma2DF3 cromosoma2df3 = new Cromosoma2DF3();
		cromosoma2df3.setTolerancia(getTolerancia());
		cromosoma2df3.setGen(0, getGen(0));
		cromosoma2df3.setGen(1, getGen(1));
		cromosoma2df3.fenotype_ = fenotype_;
		cromosoma2df3.evaluated = evaluated;
		return cromosoma2df3;
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
	public int getGenLen(int i) {
		if(i==0) return getLgenx();
		if(i==1) return getLgeny();
		return 0;
	}
	
	@Override
	public double value2optimize() {
		return (double) getFenotipo();
	}

}
