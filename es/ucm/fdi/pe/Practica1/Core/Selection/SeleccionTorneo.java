package Core.Selection;

import java.util.Random;

import Gen.Cromosoma;

public class SeleccionTorneo implements TipoSeleccion {
private static final double P = 0.75;

	private Cromosoma getMenorSubpoblacion(Cromosoma[] subpoblacion) {
		if(subpoblacion[0].getPuntuacion() < subpoblacion[1].getPuntuacion()){
			return subpoblacion[0];
		}
		else{
			return subpoblacion[1];
		}
	}

	private Cromosoma getMejorSubpoblacion(Cromosoma[] subpoblacion) {
		if(subpoblacion[0].getPuntuacion() > subpoblacion[1].getPuntuacion()){
			return subpoblacion[0];
		}
		else{
			return subpoblacion[1];
		}
	}

	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		Cromosoma subpoblacion[] =  new Cromosoma[2];
		Cromosoma poblacionAux[] = new Cromosoma[poblacion.length];
		Random rnd = new Random();
		int posElegida;
		double prob;
		
		for(int j = 0; j < poblacion.length; j++){
			
			for(int i = 0; i < 2; i++){ //Seleccionamos 2 individuos al azar
				posElegida = (int) (rnd.nextDouble() * poblacion.length);
				subpoblacion[i] = poblacion[posElegida].clonar();
			}
			
			prob = rnd.nextDouble();
			if(prob > P){
				poblacionAux[j] = getMejorSubpoblacion(subpoblacion);
			}else{
				poblacionAux[j] = getMenorSubpoblacion(subpoblacion);
			}
		}
		
		for(int i = 0; i < poblacion.length; i++){
			poblacion[i] = poblacionAux[i].clonar();
		}
		return poblacion;
	}
}
