package core.mutacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.TreeSet;

import core.fitness.Fitness;
import core.fitness.TipoFitness;
import gen.Cromosoma;
import utils.Utils;

public class MutacionHeuristica implements Mutacion{

	private Integer numGen2Perm = 3;
	private Fitness fitness = TipoFitness.MINIMIZAR;
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

	public Cromosoma mutarInd(Cromosoma individuo) {
		TreeSet<Integer> ind2perm = new TreeSet<>();
		setNumGen2Perm(getNumGen2Perm() % individuo.getNumGenes());
		while(ind2perm.size() < getNumGen2Perm()) {
			ind2perm.add(random.nextInt(individuo.getNumGenes()));
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
			max = Collections.max(Arrays.asList(max, mut), fitness);
		}
        return max;
	}

	public Integer getNumGen2Perm() {
		return numGen2Perm;
	}

	public void setNumGen2Perm(Integer numGen2Perm) {
		this.numGen2Perm = numGen2Perm;
	}

	public Fitness getFitness() {
		return fitness;
	}

	public void setFitness(Fitness fitness) {
		this.fitness = fitness;
	}
}
