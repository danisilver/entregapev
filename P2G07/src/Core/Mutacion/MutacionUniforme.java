package Core.Mutacion;

import Gen.*;

public class MutacionUniforme implements TipoMutacion{

	@Override
	public Cromosoma[] mutacion(Cromosoma[] ind, double probMutacion) {//para cromosomas reales solo
		Cromosoma[] ret = new Cromosoma[2];
		ret[0] = mutacion(ind[0], probMutacion);
		ret[1] = mutacion(ind[1], probMutacion);
		return ret;
	}

	private Cromosoma mutacion(Cromosoma cromosoma, double probMutacion) {
		CromosomaRealND crom = (CromosomaRealND) cromosoma;
		Object[] genes = crom.getGenes();
		for (int i = 0; i < genes.length; i++) {
			double r = Math.random();
			if(r < probMutacion) {
				Double gen = crom.getXmin() + (crom.getXmax() - crom.getXmin()) * Math.random();
				crom.setGen(i, gen);
			} 
		}
		return crom;
	}

}
