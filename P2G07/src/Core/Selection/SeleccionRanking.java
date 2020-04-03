package Core.Selection;

import java.util.concurrent.ThreadLocalRandom;

import Gen.Cromosoma;

public class SeleccionRanking implements TipoSeleccion {

	ThreadLocalRandom random = ThreadLocalRandom.current();
	public double beta = 1;
	
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		
		//fitness creciente recorrido creciente
		//fitnes decreciente recorrido decreciente
		int tamPoblacion = poblacion.length;
		double n = tamPoblacion;
		double oneOfN = 1d / n;
		
		double[] ranks = new double[tamPoblacion];
		double probAcc = 0d;
		for (int i = 0; i < ranks.length; i++) {
			ranks[i] = oneOfN*(beta-2*(beta-1)*(i-1)/(n-1))+probAcc;
			probAcc = ranks[i];
		}
		
		Cromosoma[] nuevaPoblacion = new Cromosoma[tamPoblacion];
		int i = 0;
		while(i<tamPoblacion) {
			double prob = random.nextDouble(probAcc);
			int j = 0;
			while(ranks[j]<prob) j++;
			nuevaPoblacion[i] = poblacion[tamPoblacion-1-i].clonar();
			i++;
		}
		
		return null;
	}

}
