package gen;

public class Cromosoma2DF2 extends Cromosoma2D{
	
	private boolean evaluated;
	private double fenotype_;

	public Cromosoma2DF2() {
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
		
		fenotype_ = -(Math.abs(
					Math.sin(cx)
					*Math.cos(cy)
					*Math.exp((Math.abs(1-(Math.sqrt(cx*cx + cy*cy)/Math.PI))))));
		evaluated = true;
		return fenotype_;
	}

	@Override
	public Cromosoma clonar() {
		Cromosoma2DF2 cromosoma2df2 = new Cromosoma2DF2();
		cromosoma2df2.setTolerancia(getTolerancia());
		cromosoma2df2.setGen(0, getGen(0));
		cromosoma2df2.setGen(1, getGen(1));
		cromosoma2df2.fenotype_ = fenotype_;
		cromosoma2df2.evaluated = evaluated;
		return cromosoma2df2;
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
	
}
