package Gen;

public class CromosomaRealND extends CromosomaReal {
	private double xmax;
	private int xmin;
	private int numgenes;
	
	public CromosomaRealND(int ngenes) {
		genes = new Double[ngenes];
		numgenes = ngenes;
		setXmax(Math.PI);
		setXmin(0);
	}

	@Override
	public Object getFenotipo() {
		double res = 0;
		for (int i = 0; i < genes.length; i++) {
			Double x = (Double)getGen(i);
			res -= Math.sin(x)*Math.pow(
									Math.sin(
											((i+2)*x*x)/Math.PI), 20);
		}
		return res;
	}

	@Override
	public Cromosoma clonar() {
		return new CromosomaRealND(numgenes);
	}

	@Override
	public int getGenLen(int i) {
		return 0;
	}

	public int getXmin() {
		return xmin;
	}

	public void setXmin(int xmin) {
		this.xmin = xmin;
	}

	public double getXmax() {
		return xmax;
	}

	public void setXmax(double xmax) {
		this.xmax = xmax;
	}

}
