package Gen;

import java.util.ArrayList;

public abstract class CromosomaND extends Cromosoma {
	ArrayList<Object> genes;

	public CromosomaND(int ngenes) {
		genes = new ArrayList<Object>();
	}

	@Override
	public Object getGen(int i) {
		return genes.get(i);
	}

	@Override
	public void setGen(int i, Object g) {
		if (genes.size() >= i) {
			genes.set(i, g);
		} else {
			genes.add(i, g);
		}
	}

	@Override
	public int getNumGenes() {
		return genes.size();
	}

	@Override
	public abstract Object getFenotipo();

}
