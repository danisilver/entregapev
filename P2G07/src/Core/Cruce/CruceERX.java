package Core.Cruce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import Gen.Cromosoma;

public class CruceERX implements TipoCruce{

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
		ArrayList<Object> genesp1 = ind[0].getGenes();
		ArrayList<Object> genesp2 = ind[1].getGenes();
		
		int numgenes = ind[0].getGenes().size();
		
		Integer[][] adjMatrix = new Integer[numgenes][];
		
		for (int i = 0; i < numgenes; i++) {
			HashSet<Integer> setI = new HashSet<>();
			int anterior  = (i+numgenes-1)%numgenes;
			int siguiente = (i+numgenes+1)%numgenes;
			setI.add((Integer)genesp1.get(anterior));
			setI.add((Integer)genesp1.get(siguiente));
			setI.add((Integer)genesp2.get(anterior));
			setI.add((Integer)genesp2.get(siguiente));
			adjMatrix[i] = setI.toArray(new Integer[1]);
		}
		
		ArrayList<Integer> hijo1 = new ArrayList<>(); 
		ArrayList<Integer> hijo2 = new ArrayList<>();
		
		int sel = random.nextInt(2);
		Integer firstGenFirstChild = (Integer)ind[sel].getGen(0);
		Integer firstGenSecondChild = (Integer)ind[(sel+1)%2].getGen(0);
		fillGenesAdjacentes(numgenes, adjMatrix, hijo1, firstGenFirstChild);
		fillGenesAdjacentes(numgenes, adjMatrix, hijo2, firstGenSecondChild);
		
		Cromosoma c1 = ind[0].clonar();
		Cromosoma c2 = ind[1].clonar();
		for (int i = 0; i < numgenes; i++) {
			c1.setGen(i, hijo1.get(i));
			c2.setGen(i, hijo2.get(i));
		}
		
		return new Cromosoma[] {c1, c2};
	}

	private void fillGenesAdjacentes(
			int numgenes, 
			Integer[][] adjMatrix, 
			ArrayList<Integer> hijo1, 
			Integer primerGen) 
	{
		while(hijo1.size()<numgenes) {
			Set<Integer> set = Arrays.asList(adjMatrix[primerGen-1])//hijo -> numAdjacentes de hijo
					.stream()
					.map(indice->adjMatrix[indice-1].length).collect(Collectors.toSet());
			int min = Collections.min(set);
			List<Integer> minimos = Arrays.asList(adjMatrix[primerGen-1])//hijos numAdjacentes de tama�o min
					.stream()
					.filter(indice->(adjMatrix[indice-1].length==min))
					.collect(Collectors.toList());
			if(minimos.size()==1 && !hijo1.contains(minimos.get(0)+1)) {
				hijo1.add(minimos.get(0)+1);
			} else {
				int elegido = random.nextInt(minimos.size());
				hijo1.add(minimos.get(elegido));
			}
		}
	}


}
