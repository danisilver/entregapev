package core.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class CruceCO implements Cruce{//codificacion ordinal

	private Random random = Utils.random;
	
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

	public Cromosoma[] crossPair(Cromosoma[] ind) {
		Cromosoma p1 = ind[0]; 
		Cromosoma p2 = ind[1];
		Integer[] genesp1 = (Integer[])p1.getGenes();
		Integer[] genesp2 = (Integer[])p2.getGenes();
		int numgenes = p1.getNumGenes();
		
		List<Integer> genesp1Cod = codificar(genesp1, numgenes);
		List<Integer> genesp2Cod = codificar(genesp2, numgenes);
		
		int crossPoint = 1 + random.nextInt(numgenes-2);
		List<Integer> genesh1Crossed = new ArrayList<>();
		List<Integer> genesh2Crossed = new ArrayList<>();

		for (int i = 0; i < numgenes; i++) {
			if(i<crossPoint) {
				genesh1Crossed.add(i, genesp1Cod.get(i));
				genesh2Crossed.add(i, genesp2Cod.get(i));
			} else {
				genesh1Crossed.add(i, genesp2Cod.get(i));
				genesh2Crossed.add(i, genesp1Cod.get(i));
			}
		}
		
		List<Integer> genesp1Dec = decodificar(numgenes, genesh1Crossed);
		List<Integer> genesp2Dec = decodificar(numgenes, genesh2Crossed);
		
		Cromosoma h1 = p1.clonar();
		Cromosoma h2 = p1.clonar();
		for (int i = 0; i < numgenes; i++) {
			h1.setGen(i, genesp1Dec.get(i));
			h2.setGen(i, genesp2Dec.get(i));
		}
		
		return new Cromosoma[] {h1, h2};
	}

	private List<Integer> codificar(Integer[] genesp1, int numgenes) {
		ArrayList<Integer> indices 	= new ArrayList<Integer>();
		List<Integer> genesp1Cod	= new ArrayList<Integer>();
		for (int i = 1; i <= numgenes; i++)
			indices.add(i);
		
		for(Integer gen:genesp1) {
			genesp1Cod.add(indices.indexOf(gen));
			indices.remove(gen);
		}
		return genesp1Cod;
	}

	private List<Integer> decodificar(int numgenes, List<Integer> genesp1Cod) {
		ArrayList<Integer> indices 	= new ArrayList<Integer>();
		ArrayList<Integer> ret 		= new ArrayList<Integer>();
		for (int i = 1; i <= numgenes; i++)
			indices.add(i);
		
		for(Integer gen:genesp1Cod) {
			ret.add(indices.get(gen));
			indices.remove((int)gen);
		}
		return ret;
	}
	
}
