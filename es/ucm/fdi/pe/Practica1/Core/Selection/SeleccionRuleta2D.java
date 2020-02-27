package Core.Selection;

import Gen.Cromosoma;

public class SeleccionRuleta2D implements TipoSeleccion {
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		Cromosoma[] resultado = new Cromosoma[2];
		double r1 = Math.random();
		double r2 = Math.random();
		double pacc = 0;
		int i = 0;
		double probMin = (double) poblacion[0].getFenotipo();
		double probMax = probMin;
		double fitnessTotal = 0;
		while (i < poblacion.length) {
			double fi = (double) poblacion[i].getFenotipo();
			if (fi < probMin)
				probMin = fi;
			if (fi > probMax)
				probMax = fi;
			i++;
		}
		double[] probs = new double[poblacion.length];
		i = 0;
		while (i < poblacion.length) {
			probs[i] = normalize((double)poblacion[i].getFenotipo(), probMin, probMax);
			fitnessTotal += probs[i];
			i++;
		}
		i = 0;
		while (i < poblacion.length) {
			if (pacc <= r1) {
				resultado[0] = poblacion[i];
			}
			if (pacc <= r2) {
				resultado[1] = poblacion[i];
			}
			double probi = probs[i];
			pacc += probi / fitnessTotal;
			i++;
		}
		return resultado;
	}

	static double normalize(double value, double min, double max) {
		return 1 - ((value - min) / (max - min));
	}
}
