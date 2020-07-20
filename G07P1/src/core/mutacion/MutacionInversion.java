package core.mutacion;

import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class MutacionInversion implements Mutacion{

	private Random random = Utils.random;

	@Override
	public Cromosoma[] mutacion(Cromosoma[] poblacion, double probMutacion) {
		int tamPoblacion = poblacion.length;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];
		
		int tam = 0;
		while(tam < tamPoblacion) {
			if(random.nextDouble()<probMutacion)
				ret[tam] = mutarInd(poblacion[tam]);
			else
				ret[tam] = poblacion[tam].clonar();
			tam++;
		}
		
		return ret;
	}

	@Override
	public Cromosoma mutarInd(Cromosoma original) {
		Cromosoma res = original.clonar();
		int numgenes = original.getNumGenes();
		
		int pto1 = random.nextInt(numgenes-1);
		int pto2 = pto1 + random.nextInt(numgenes-pto1);
		
		for (int i = 0; i < numgenes; i++) {
			if(i>=pto1 && i<=pto2) {
				res.setGen(i, original.getGen(pto2-(i-pto1)));
			} else {
				res.setGen(i, original.getGen(i));
			}
		}
		return res;
	}

}
