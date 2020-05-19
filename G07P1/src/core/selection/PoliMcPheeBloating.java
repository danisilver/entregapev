package core.selection;

import static java.lang.Math.max;
import static utils.Utils.covarianza;
import static utils.Utils.normalize;
import static utils.Utils.varianza;

import java.util.Arrays;

import gen.Cromosoma;
import gen.CromosomaGramatica;

public class PoliMcPheeBloating implements Seleccion{

	private Seleccion tipoSeleccion;

	public PoliMcPheeBloating(Seleccion seleccion) {
		this.tipoSeleccion = seleccion;
	}
	
	@Override
	public Cromosoma[] seleccion(Cromosoma[] poblacion) {
		double mediaNodos = Arrays.asList(poblacion)
				.stream()
				.mapToDouble(crom->((CromosomaGramatica)crom)
						.getArbol()
						.getNumNodos())
				.sum();
		mediaNodos = mediaNodos/poblacion.length;
		double[] l = Arrays.asList(poblacion)
				.stream()
				.mapToDouble(crom->((CromosomaGramatica)crom).getArbol().getNumNodos())
				.toArray();
		double[] f = Arrays.asList(poblacion)
				.stream()
				.mapToDouble(crom->crom.value2optimize())
				.toArray();
		double[][] lf2dArr = new double[poblacion.length][]; 
		for (int i = 0; i < lf2dArr.length; i++) { lf2dArr[i] = new double[] {l[i], f[i]}; }
		double var = varianza(l);
		double cov = covarianza(lf2dArr);
		double c   = cov/var;
		
		for (int i = 0; i < poblacion.length; i++) {
			CromosomaGramatica cromI = (CromosomaGramatica) poblacion[i];
			if(cromI.getArbol().getNumNodos() > mediaNodos) {
				//F = fallos, con covarianza negativa sumamos fallos
				double fallos = cromI.evalua() - c*cromI.getArbol().getNumNodos();
				//la puntuacion es mas alta si hay 0 fallos
				double puntuacion = normalize(max(0, fallos), cromI.getnOutputs(), 0);
				cromI.setPuntuacion(puntuacion);
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
