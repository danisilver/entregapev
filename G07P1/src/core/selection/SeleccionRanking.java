package core.selection;

import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class SeleccionRanking implements Seleccion {

	private Random random = Utils.random;
	private double beta = 1;
	
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
			ranks[i] = oneOfN*(getBeta()-2*(getBeta()-1)*(i-1)/(n-1))+probAcc;
			probAcc = ranks[i];
		}
		
		Cromosoma[] nuevaPoblacion = new Cromosoma[tamPoblacion];
		int i = 0;
		while(i<tamPoblacion) {
			double prob = probAcc*random.nextDouble();
			int j = 0;
			while(ranks[j]<prob) j++;
			nuevaPoblacion[i] = poblacion[tamPoblacion-1-i].clonar();
			i++;
		}
		
		return nuevaPoblacion;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

}
