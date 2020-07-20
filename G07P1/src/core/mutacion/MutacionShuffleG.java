package core.mutacion;

import java.util.*;

import gen.Cromosoma;
import utils.Utils;

public class MutacionShuffleG implements Mutacion {
	private Random random = Utils.random;

	@Override
	public Cromosoma[] mutacion(Cromosoma[] poblacion, double probMutacion) {
		int tamPoblacion = poblacion.length;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];
		
		int tam = 0;
		while(tam < tamPoblacion) {
			if(random.nextDouble() < probMutacion)
				ret[tam] = mutarInd(poblacion[tam]);
			else
				ret[tam] = poblacion[tam].clonar();
			tam++;
		}
		
		return ret;
	}

	@Override
	public Cromosoma mutarInd(Cromosoma cromosoma) {//shuffleGens
		Cromosoma newC = cromosoma.clonar();
		List<Object> gens = Arrays.asList(newC.getGenes());
		Collections.shuffle(gens,random);
		for (int i = 0; i < gens.size(); i++) 
			newC.setGen(i, gens.get(i));
		return newC;
	}

}
