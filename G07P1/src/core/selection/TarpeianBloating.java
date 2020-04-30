package core.selection;

import java.util.Arrays;
import java.util.Random;

import gen.Cromosoma;
import gen.CromosomaGramatica;
import utils.Utils;

public class TarpeianBloating implements Seleccion{

	private Random random = Utils.random;
	private Seleccion tipoSeleccion;

	public TarpeianBloating(Seleccion seleccion) {
		this.tipoSeleccion = seleccion;
	}
	
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		double mediaNodos = Arrays.asList(poblacion)
				.stream()
				.mapToDouble(crom->((CromosomaGramatica)crom)
						.getArbol()
						.getNumNodos()/poblacion.length)
				.sum();
		double penaltyProb = 1d / poblacion.length; 
		for (int i = 0; i < poblacion.length; i++) {
			CromosomaGramatica cromI = (CromosomaGramatica) poblacion[i];
			if(cromI.getArbol().getNumNodos() > mediaNodos) {
				if(random.nextDouble()<penaltyProb) {
					cromI.setPuntuacion(poblacion[0].getPuntuacion());
				}
			}
		}
		
		recalcularPuntuaciones(poblacion);
		
		return tipoSeleccion.seleccion(poblacion);
	}
	
	private void recalcularPuntuaciones(Cromosoma[] nueva) {
		double total = 0d;
		for(Cromosoma c:nueva)
			total += c.getPuntuacion();
		
		double puntAcc = 0;
		for (Cromosoma c:nueva) {
			puntAcc += c.getPuntuacion() / total;
			c.setPuntAcc(puntAcc);
		}
	}

}
