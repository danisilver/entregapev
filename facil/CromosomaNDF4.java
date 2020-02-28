package facil;

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
			Integer x = (Integer)getGen(i);
			double xi = xmin + x*(xmax-xmin)/(Math.pow(2,getGenLen(i))-1);
			res -= Math.sin(xi)*Math.pow(
									Math.sin(
											((i+1)*xi*xi)/Math.PI), 20);
		}
		return -res;
	}

	@Override
	public Object[] getGenes() {
		return genes;
	}

	@Override
	public Cromosoma clonar() {
		return new CromosomaNDF4(numgenes);
	}
	
	@Override
	public int getGenLen(int i) {
		return (int) Math.ceil(Math.log(1+(xmax-xmin)/tolerancia)/Math.log(2));
	}
}
