package Core.Selection;

import Gen.*;

public class SeleccionEstocastica implements TipoSeleccion{

	int kindividuos = 2;
	
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		Cromosoma[] ret = new Cromosoma[kindividuos];
		double r = Math.random();
		
		double dist = 1d / kindividuos;
		double prob = r*dist;
		int i, j;
		i = j = 0;
		while (i < kindividuos) {
			while(prob < poblacion[j].getPuntAcc()) 
				j++;
			prob+=dist;
			ret[i] = poblacion[j];
			i++;
		}
		return ret;
	}
}
