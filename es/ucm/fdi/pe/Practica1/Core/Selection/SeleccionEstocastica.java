package Core.Selection;

import Gen.Cromosoma;

public class SeleccionEstocastica implements TipoSeleccion {
private double marca;
	
	private double getRandom(int tamPob) {
		double n = Math.random();
		
		while(n > marca){
			n = Math.random();
		}
		
		return n;
	}

	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		int[] sel_super = new int[poblacion.length];
		marca = 1.0 / poblacion.length;
		double suma = getRandom(poblacion.length);
		int pos_super = 0;
		
		for(int i = 0; i < poblacion.length; i++){
			while((suma > poblacion[pos_super].getPuntAcc()) && (pos_super < poblacion.length)){
				pos_super++;
				sel_super[i] = pos_super;
			}
			suma += marca;
		}
		
		//Se genera la población intermedia
		Cromosoma[] nuevaPob = new Cromosoma[poblacion.length];
		for(int i = 0; i < poblacion.length; i++){
			nuevaPob[i] = poblacion[sel_super[i]].clonar();
		}
		
		for(int i = 0; i <poblacion.length; i++){
			poblacion[i] = nuevaPob[i].clonar();
		}
		return poblacion;
	}
}
