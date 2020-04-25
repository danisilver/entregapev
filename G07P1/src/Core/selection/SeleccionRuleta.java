package core.selection;

import java.util.Random;

import gen.Cromosoma;
import utils.Utils;

public class SeleccionRuleta implements Seleccion {
	
	private Random random = Utils.random;
	
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		int tamPoblacion = poblacion.length;
		Cromosoma[] ret = new Cromosoma[tamPoblacion];
		int i, j;
		i = j = 0;
		while (i < tamPoblacion) {
			double r = random.nextDouble();
			while(r > poblacion[j].getPuntAcc()) 
				j++;
			ret[i] = poblacion[j];
			j=0;
			i++;
		}
		return ret;
	}

}
