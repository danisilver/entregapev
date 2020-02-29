package facil;

public abstract class CromosomaReal extends Cromosoma{
	Double[] genes;
	
	@Override
	public Object getGen(int i) {
		return genes[i];
	}

	@Override
	public void setGen(int i, Object g) {
		genes[i]=(Double)g;
	}

	@Override
	public Object[] getGenes() {
		return genes;
	}

	public abstract Cromosoma clonar();

	public abstract int getGenLen(int i);

	public abstract Object getFenotipo();
}
