package Gen;

public abstract class CromosomaND extends Cromosoma {
	Integer[] genes;
	public CromosomaND(int ngenes) {
		genes = new Integer[ngenes];
	}
	
	@Override
	public Object getGen(int i) {
		return genes[i];
	}

	@Override
	public void setGen(int i, Object g) {
		genes[i]=(Integer)g;
	}

	@Override
	public abstract Object getFenotipo();
	
}
