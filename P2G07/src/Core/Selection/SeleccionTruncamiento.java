package Core.Selection;

import Gen.Cromosoma;

public class SeleccionTruncamiento implements TipoSeleccion{

	/*
	 * en una pob de 100 individuos
	 * si 0.5 * 100 = 50 mejores cada uno repetido 100/50 = 2 veces
	 * si 0.1 * 100 = 10 mejores cada uno repetido 100/20 = 5 veces
	 */
	
	public double trunc = 0.5;//entre 0.5 y 0.1
	
	
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		int tamPoblacion = poblacion.length;
		int nMejores = (int) Math.floor(tamPoblacion * trunc);
		int copias = (int) Math.floor(tamPoblacion/nMejores);
		Cromosoma[] ret = new Cromosoma[tamPoblacion];
		
		int next = tamPoblacion - nMejores;
		int backup = next;
		while(next<tamPoblacion) {
			Cromosoma nextC = poblacion[next];
			for (int i = 0; i < copias; i++)
				ret[next++] = nextC.clonar();
		}
		while(backup>=0)
			ret[backup--] = poblacion[tamPoblacion-1].clonar();
		
		return ret;
	}

}
