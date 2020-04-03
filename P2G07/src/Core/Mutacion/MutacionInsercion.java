package Core.Mutacion;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import Gen.Cromosoma;

public class MutacionInsercion implements TipoMutacion {

	public int numGens2Ins = 1;
	ThreadLocalRandom random = ThreadLocalRandom.current();

	@Override
	public Cromosoma[] mutacion(Cromosoma[] poblacion, double probMutacion) {
		int tamPoblacion = poblacion.length;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];

		int tam = 0;
		while (tam < tamPoblacion) {

			if (random.nextDouble() < probMutacion)
				ret[tam] = fillWithRandomShifts(poblacion[tam]);
			else
				ret[tam] = poblacion[tam].clonar();

			tam++;
		}

		return ret;
	}

	private Cromosoma fillWithRandomShifts(Cromosoma original) {
		int numgenes = original.getNumGenes();
		Cromosoma mutado1 = original.clonar();
		LinkedList<Object> genes = new LinkedList<>();
		for (int i = 0; i < numgenes; i++) {
			genes.add(mutado1.getGen(i));
		}

		for (int i = 0; i < numGens2Ins; i++) {
			int indExtraer = 1 + random.nextInt(numgenes - 1);
			int indPoner = random.nextInt(numgenes - 2);
			Object gen = genes.remove(indExtraer);
			genes.add(indPoner, gen);
		}

		for (int i = 0; i < numgenes; i++) {
			mutado1.setGen(i, genes.get(i));
		}
		return mutado1;
	}

}
