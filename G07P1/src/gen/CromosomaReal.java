package gen;

import java.util.Arrays;

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

	@Override
	public int getNumGenes() {
		return genes.length;
	}

	public abstract Cromosoma clonar();

	public abstract int getGenLen(int i);

	public abstract Object getFenotipo();
	
	@Override
	public double value2optimize() {
		return (double) getFenotipo();
	}
	
	@Override
	public String toString() {
		return Arrays.deepToString(genes)+" value2optimize:"+value2optimize();
	}
}