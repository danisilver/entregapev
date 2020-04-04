package Core.Cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import Gen.Cromosoma;

public class CruceCO implements TipoCruce{//codificacion ordinal

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
		Cromosoma p1 = ind[0]; 
		Cromosoma p2 = ind[1];
		ArrayList<Object> genesp1 = p1.getGenes();
		ArrayList<Object> genesp2 = p2.getGenes();
		int numgenes = p1.getNumGenes();
		
		List<Integer> genesp1Cod = new ArrayList<>();
		List<Integer> genesp2Cod = new ArrayList<>();
		
		codificar(genesp1, numgenes, genesp1Cod);
		codificar(genesp2, numgenes, genesp2Cod);
		
		Cromosoma h1 = p1.clonar();
		Cromosoma h2 = p1.clonar();
		for (int i = 0; i < numgenes; i++) {
			h1.setGen(i, genesp1Cod.get(i));
			h2.setGen(i, genesp2Cod.get(i));
		}
		
		return new CruceMonoPunto().cruce(new Cromosoma[] {h1,h2}, 1);
	}

	private void codificar(ArrayList<Object> genesp1, int numgenes, List<Integer> genesp1Cod) {
		ArrayList<Integer> indices = new ArrayList<Integer>();
		for (int i = 0; i < numgenes; i++) { 
			indices.add(i);
		}
		
		for(var gen: genesp1) {
			genesp1Cod.add(indices.indexOf(gen));
			indices.remove((Object)gen);
		}
	}

	
	
}
