package core.mutacion;

import java.util.LinkedList;
import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class MutacionInsercion implements Mutacion{

	private int numGens2Ins = 1;
	private Random random = Utils.random;

	@Override
	public Cromosoma[] mutacion(Cromosoma[] poblacion, double probMutacion) {
		int tamPoblacion = poblacion.length;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];
		
		int tam = 0;
		while(tam < tamPoblacion) {
			if(random.nextDouble() < probMutacion)
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
		
		for (int i = 0; i < getNumGens2Ins(); i++) {
			int indExtraer = 1 + random.nextInt(numgenes-1);
			int indPoner = random.nextInt(numgenes-2);
			Object gen = genes.remove(indExtraer);
			genes.add(indPoner, gen);
		}
		
		for (int i = 0; i < numgenes; i++) {
			mutado1.setGen(i, genes.get(i));
		}
		return mutado1;
	}

	public int getNumGens2Ins() {
		return numGens2Ins;
	}

	public void setNumGens2Ins(int numGens2Ins) {
		this.numGens2Ins = numGens2Ins;
	}

}
