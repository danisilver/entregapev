package Gen;

public class CromosomaNDF4 extends CromosomaND {
	double xmax;
	int xmin;
	private int numgenes;

	public CromosomaNDF4(int ngenes) {
		super(ngenes);
		this.numgenes = ngenes;
		xmax = Math.PI;
		xmin = 0;
	}

	@Override
	public Object getFenotipo() {
		double res = 0;
		for (int i = 0; i < genes.length; i++) {
			double xi = (double)getGen(i);
			res -= Math.sin(xi)*Math.pow(
									Math.sin(
											((i+1)*xi*xi)/Math.PI), 20);
		}
		return res;
	}

	@Override
	public Object[] getGenes() {
		return genes;
	}

	@Override
	public Cromosoma clonar() {
		return new CromosomaNDF4(numgenes);
	}
}
