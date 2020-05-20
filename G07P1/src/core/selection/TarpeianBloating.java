package core.selection;

import java.util.Random;

import gen.Cromosoma;
import gen.CromosomaGramatica;
import utils.Utils;

public class TarpeianBloating implements Seleccion{

	private Random random = Utils.random;
	private Seleccion tipoSeleccion;
	private int deathProportion = 7;

	public TarpeianBloating(Seleccion seleccion, int deathProportion) {
		this.tipoSeleccion = seleccion;
		this.deathProportion = deathProportion;
	}
	
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		double mediaNodos = mediaNodos(poblacion);
		for (int i = 0; i < poblacion.length; i++) {
			CromosomaGramatica cromI = (CromosomaGramatica) poblacion[i];
			int cromINodos = cromI.getArbol().getNumNodos();
			double prob2penalize = 1d/deathProportion;
			if(cromINodos > mediaNodos && random.nextDouble()<prob2penalize) {
				cromI.setPuntuacion(poblacion[0].getPuntuacion());
			}
		}
		recalcularPuntuaciones(poblacion);
		
		return tipoSeleccion.seleccion(poblacion);
	}

	private double mediaNodos(Cromosoma[] poblacion) {
		double mediaTradicional = 0;
		for(Cromosoma p:poblacion) 
			mediaTradicional+=((CromosomaGramatica)p).getArbol().getNumNodos();
		return mediaTradicional/poblacion.length;
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
