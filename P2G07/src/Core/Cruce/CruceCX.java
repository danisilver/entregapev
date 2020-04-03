package Core.Cruce;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Gen.Cromosoma;
import Gen.CromosomaNDP5;

public class CruceCX implements TipoCruce{

	ThreadLocalRandom random = ThreadLocalRandom.current();

	@Override
	public Cromosoma[] cruce(Cromosoma[] poblacion, double probCruce) {
		int tamPoblacion = poblacion.length;
		Cromosoma ret[] = new Cromosoma[tamPoblacion];
		int tam = 0;
		
		while(tam < tamPoblacion) {
			if(random.nextDouble()<probCruce) {
				Cromosoma[] crossed = crossPair(new Cromosoma[] {poblacion[tam], poblacion[tam+1]});
				ret[tam] = crossed[0];
				ret[tam+1] = crossed[1];
			} else {
				ret[tam] = poblacion[tam];
				ret[tam+1] = poblacion[tam+1];
			}
			tam+=2;
		}
		return ret;
	}

	private Cromosoma[] crossPair(Cromosoma[] ind) {
		CromosomaNDP5[] crPadres = (CromosomaNDP5[])ind;
		int numgenes = crPadres[0].getGenes().length;
		
		ArrayList<Integer> child1 = new ArrayList<Integer>();
		ArrayList<Integer> child2 = new ArrayList<Integer>();
		for (int i = 0; i < numgenes; i++) {
			child1.add(-1);child2.add(-1);
		}
		
		ponerHomologos((Integer[])crPadres[0].getGenes(), 
				(Integer[])crPadres[1].getGenes(),
				child1);
		ponerHomologos((Integer[])crPadres[1].getGenes(), 
				(Integer[])crPadres[0].getGenes(), 
				child2);
		
		for (int i = 0; i < numgenes; i++) {
			if(child1.get(i)==-1) child1.add((Integer)crPadres[1].getGen(i));
			if(child2.get(i)==-1) child2.add((Integer)crPadres[0].getGen(i));
		}
		
		Cromosoma ret1 = ind[0].clonar();
		Cromosoma ret2 = ind[1].clonar();
		for (int i = 0; i < numgenes; i++) {
			ret1.setGen(i, Integer.valueOf(child1.get(i)));
			ret2.setGen(i, Integer.valueOf(child2.get(i)));
		}
		
		return new Cromosoma[] {ret1,ret2};
	}

	private void ponerHomologos(Integer[] genesp1, Integer[] genesp2, ArrayList<Integer> child1) {
		Integer genHomologo, homologo;
		boolean continuar = true;
		homologo = 0;
		while(continuar){
			genHomologo = genesp1[homologo];
			if(!child1.contains(genHomologo)) {
				child1.add(homologo, genHomologo);
			} else { continuar = false; }
			homologo = getPosOfGen(genHomologo, genesp2) ;
		};
	}
	
	private Integer getPosOfGen(Integer g, Integer[] genes) {
		for (int i = 0; i < genes.length; i++) {
			if(genes[i]==g) return i;
		}
		return 0;
	}

}
