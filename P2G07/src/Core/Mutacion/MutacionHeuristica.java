package Core.Mutacion;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import Core.Fitness.Fitness;
import Core.Fitness.TipoFitness;
import Gen.Cromosoma;
import Utils.Utils;

public class MutacionHeuristica implements TipoMutacion{

	public Integer numGen2Perm = 3;
	public Fitness fitness = TipoFitness.MINIMIZAR;
	ThreadLocalRandom random = ThreadLocalRandom.current();
	
	@Override
	public Cromosoma[] mutacion(Cromosoma[] poblacion, double probMutacion) {
		int tamPoblacion = poblacion.length;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];
		
		int tam = 0;
		while(tam < tamPoblacion) {
			if(random.nextDouble() < probMutacion)
				ret[tam] = bestPermutatedCromosome(poblacion[tam]);
			else
				ret[tam] = poblacion[tam].clonar();
			tam++;
		}
		
		return ret;
	}

	private Cromosoma bestPermutatedCromosome(Cromosoma individuo) {
		TreeSet<Integer> ind2perm = new TreeSet<>();
		numGen2Perm = numGen2Perm % individuo.getNumGenes();
		while(ind2perm.size() < numGen2Perm) {
			ind2perm.add(random.nextInt(numGen2Perm));
		}
		
		ArrayList<Integer> tmp = new ArrayList<>();
		ArrayList<Integer> indXchng = new ArrayList<>(ind2perm);
        ArrayList<ArrayList<Integer>> salida = new ArrayList<>();
        Utils.permutations(tmp,indXchng, salida);
        
        Cromosoma max = individuo.clonar();
        for (int i = 0; i < salida.size(); i++) {
			ArrayList<Integer> perm = salida.get(i);
			Cromosoma mut = individuo.clonar();
			for(Integer j:indXchng) {
				mut.setGen(j, individuo.getGen(perm.remove(0)));
			}
			if(fitness.compare(max, mut)==-1)
				max = mut;
		}
        return max;
	}
}
