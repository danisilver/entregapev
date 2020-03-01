package Core.Selection;

import Gen.*;

public class SeleccionRuleta implements TipoSeleccion {
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		Cromosoma[] ret = new Cromosoma[2];
		int i, j;
		i = j = 0;
		while (i < 2) {
			double r = Math.random();
			while(r < poblacion[j].getPuntAcc()) 
				j++;
			j=0;
			ret[i] = poblacion[j];
			i++;
		}
		return ret;
	}

}
