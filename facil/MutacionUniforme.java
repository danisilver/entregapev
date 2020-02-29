package facil;

public class MutacionUniforme implements TipoMutacion{

	@Override
	public Cromosoma[] mutacion(Cromosoma[] ind, double probMutacion) {//para cromosomas reales solo
		Cromosoma[] ret = new Cromosoma[2];
		ret[0] = mutacion(ind[0], probMutacion);
		ret[1] = mutacion(ind[1], probMutacion);
		return null;
	}

	private Cromosoma mutacion(Cromosoma cromosoma, double probMutacion) {
		CromosomaRealND crom = (CromosomaRealND) cromosoma;
		Object[] genes = crom.getGenes();
		for (int i = 0; i < genes.length; i++) {
			double r = Math.random();
			if(r < probMutacion) {
				Double gen = crom.xmin + (crom.xmax - crom.xmin) * Math.random();
				crom.setGen(i, gen);
			}
		}
		return crom;
	}

}
