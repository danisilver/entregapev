package core.mutacion;

import java.util.Random;
import java.util.Stack;
import java.util.TreeSet;

import gen.Cromosoma;
import utils.Utils;

public class MutacionIntercambio implements Mutacion{

	private int numG2Inv = 2;
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
	public Cromosoma mutarInd(Cromosoma original) {//mutarConIntercambio
		Cromosoma mutacion = original.clonar();
		int numgenes = original.getNumGenes(); 
		
		TreeSet<Integer> indX = new TreeSet<>();
		
		while(indX.size()<getNumG2Inv())
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

	public int getNumG2Inv() {
		return numG2Inv;
	}

	public void setNumG2Inv(int numG2Inv) {
		this.numG2Inv = numG2Inv;
	}

}
