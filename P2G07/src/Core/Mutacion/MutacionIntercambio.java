package Core.Mutacion;

import java.util.Stack;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import Gen.Cromosoma;

public class MutacionIntercambio implements TipoMutacion{

	public int numG2Inv = 2;
	ThreadLocalRandom random = ThreadLocalRandom.current();
	
	@Override
	public Cromosoma[] mutacion(Cromosoma[] poblacion, double probMutacion) {
		int tamPoblacion = poblacion.length;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];
		int tam = 0;
		
		while(tam < tamPoblacion) {
			if(random.nextDouble()<probMutacion)
				ret[tam] = mutarConIntercambio(poblacion[tam]);
			else
				ret[tam] = poblacion[tam].clonar();
		}
		
		return ret;
	}

	private Cromosoma mutarConIntercambio(Cromosoma original) {
		Cromosoma mutacion = original.clonar();
		int numgenes = original.getNumGenes(); 
		
		TreeSet<Integer> indX = new TreeSet<>();
		
		while(indX.size()<numG2Inv)
			indX.add(random.nextInt(numgenes));
		
		Stack<Object> genes = new Stack<>();
		for(Integer i:indX) genes.add(original.getGen(i));
		
		for (int i = 0; i < numgenes; i++) {
			mutacion.setGen(i, original.getGen(i));
			if(indX.contains(i))
				mutacion.setGen(i, genes.pop());
		}
		return mutacion;
	}

}
