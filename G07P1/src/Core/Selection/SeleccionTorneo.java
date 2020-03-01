package Core.Selection;

import java.util.Random;
import Gen.*;

public class SeleccionTorneo implements TipoSeleccion{

	int tamMuestra = 3;
	int numSeleccionados = 2;
	double umbral = 1;
	
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		int i = 0;
		Cromosoma[] ret = new Cromosoma[numSeleccionados];
		while(i < numSeleccionados) {
			ret[i] = torneo(poblacion); 
			i++;
		}
		
		return ret;
	}
	private Cromosoma torneo(Cromosoma[] poblacion) {
		int i = 0;
		Random r = new Random();
		Cromosoma mejor, peor;
		mejor = peor = poblacion[0];
		double mejorPunt, peorPunt;
		mejorPunt = 0;
		peorPunt = 1;
		while(i < tamMuestra) {
			int indice = r.nextInt(poblacion.length);
			if(poblacion[indice].getPuntuacion()>mejorPunt) {
				mejor = poblacion[indice];
				mejorPunt = mejor.getPuntuacion();
			}
			if(poblacion[indice].getPuntuacion()<peorPunt) {
				peor = poblacion[indice];
				peorPunt = peor.getPuntuacion();
			}
			i++;
		}
		// cuando umbral < 1 el torneo es probabilistico
		// cuando umbral == 1 el torneo es deterministico (siempre el mejor)
		if(Math.random() < umbral) return mejor;
		else return peor;
	}

}
