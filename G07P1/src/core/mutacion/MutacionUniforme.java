package core.mutacion;

import java.util.Random;

import gen.Cromosoma;
import gen.CromosomaRealND;
import utils.Utils;

public class MutacionUniforme implements Mutacion{

	private Random random = Utils.random;
	private double probMutacion;
	
	@Override
	public Cromosoma[] mutacion(Cromosoma[] poblacion, double probMutacion) {//para cromosomas reales solo
		this.probMutacion = probMutacion;
		int tamPoblacion = poblacion.length;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];
		
		int tam = 0;
		while(tam < tamPoblacion) {
			if(random.nextDouble()<probMutacion)
				ret[tam] = mutarInd(poblacion[tam]);
			else
				ret[tam] = poblacion[tam];
			tam++;
		}
		return ret;
	}

	@Override
	public Cromosoma mutarInd(Cromosoma cromosoma) {
		CromosomaRealND crom = (CromosomaRealND) cromosoma;
		Object[] genes = crom.getGenes();
		for (int i = 0; i < genes.length; i++) {
			double prob2Change = random.nextDouble();
			if(prob2Change < probMutacion) {
				Double gen = crom.getXmin() + (crom.getXmax() - crom.getXmin()) * random.nextDouble();
				crom.setGen(i, gen);
			} 
		}
		return crom;
	}
}


/* legacy code
Cromosoma[] ret = new Cromosoma[2];
ret[0] = mutacion(ind[0], probMutacion);
ret[1] = mutacion(ind[1], probMutacion);
return ret;
 */
