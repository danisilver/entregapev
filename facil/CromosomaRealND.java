package facil;

public class CromosomaRealND extends CromosomaReal {
	double xmax;
	int xmin;
	private int numgenes;
	
	public CromosomaRealND(int ngenes) {
		genes = new Double[ngenes];
		numgenes = ngenes;
		xmax = Math.PI;
		xmin = 0;
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

}
