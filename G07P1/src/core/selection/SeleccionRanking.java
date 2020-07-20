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
		
		int ind = tamPoblacion-1;
		int muestras=1;
		double bestP=poblacion[ind].getPuntuacion();
		while(ind>0 && bestP==poblacion[ind--].getPuntuacion())
			muestras++;
		beta = ((double)muestras)/tamPoblacion;
		
		double[] fitnessSegments = rankPopulation(tamPoblacion);
		Cromosoma[] selection = new Cromosoma[tamPoblacion];
		selection[tamPoblacion-1] = poblacion[tamPoblacion-1];
		selection[tamPoblacion-2] = poblacion[tamPoblacion-2];
		double entireSegment = fitnessSegments[tamPoblacion-1];
		for (int i = tamPoblacion-3; i >= 0; i--) {
			double x = random.nextDouble()*entireSegment;
			int j = tamPoblacion-1;
			while(j>=0 && x<fitnessSegments[j]) j--;
			selection[i]=poblacion[++j];
		}
		
		return selection;
	}
	
	double[] rankPopulation(int n) {
		double[] fitnessSegments = new double[n];
		for (int i = 0; i < n; i++) {
			double probOfIth = ((double)i)/n;
			probOfIth = probOfIth*2*(beta-1);
			probOfIth = beta - probOfIth;
			probOfIth = probOfIth*(1d/n);
			if(i!=0)fitnessSegments[i] = fitnessSegments[i-1]+probOfIth;
			else fitnessSegments[i] = probOfIth;
		}
		return fitnessSegments;
	}

	public double getBeta() {
		return beta;
	}

	public void setBeta(double beta) {
		this.beta = beta;
	}

}
