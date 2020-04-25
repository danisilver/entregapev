package gen;

public class CromosomaNDF4 extends CromosomaND {
	private double xmax;
	private double xmin;
	private boolean evaluated;
	private double fenotype_;

	public CromosomaNDF4(int ngenes) {
		super(ngenes);
		xmax = Math.PI;
		xmin = 0;
		evaluated = false;
	}

	@Override
	public Object getFenotipo() {
		if(evaluated) return fenotype_;
		
		double res = 0;
		for (int i = 0; i < genes.length; i++) {
			Integer x = (Integer)getGen(i);
			double xi = xmin + x*(xmax-xmin)/(Math.pow(2,getGenLen(i))-1);
			res -= Math.sin(xi)*Math.pow(
									Math.sin(
											((i+2)*xi*xi)/Math.PI), 20);
		}
		fenotype_ = res;
		evaluated = true;
		return fenotype_;
	}

	@Override
	public Object[] getGenes() {
		return genes;
	}

	@Override
	public Cromosoma clonar() {
		CromosomaNDF4 cromosomaNDF4 = new CromosomaNDF4(getNumGenes());
		cromosomaNDF4.setTolerancia(getTolerancia());
		for (int i = 0; i < getNumGenes(); i++) 
			cromosomaNDF4.setGen(i, getGen(i));
		cromosomaNDF4.evaluated = evaluated;
		cromosomaNDF4.fenotype_ = fenotype_;
		return cromosomaNDF4;
	}
	
	@Override
	public int getGenLen(int i) {
		return (int) Math.ceil(Math.log(1+(xmax-xmin)/getTolerancia())/Math.log(2));
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
