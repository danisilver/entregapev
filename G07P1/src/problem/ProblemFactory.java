package problem;

import java.util.HashMap;

import core.cruce.Cruce;
import core.fitness.Fitness;
import core.mutacion.Mutacion;
import core.selection.Seleccion;
import gen.Cromosoma;
import view.View;

public interface ProblemFactory {
	public Cromosoma createCromosoma (String tipo, HashMap<String,Object> props);
	public Cruce     createCruce     (String tipo, HashMap<String,Object> props);
	public Seleccion createSeleccion (String tipo, HashMap<String,Object> props);
	public Mutacion  createMutacion  (String tipo, HashMap<String,Object> props);
	public Fitness   createFitness   (String tipo, HashMap<String,Object> props);
	public View      createView      (HashMap<String,Object> props);
	public default Cromosoma[] createPoblacionInicial(
			String tipo, HashMap<String,Object> props, int tam) {
		
		String crom = props.get("tipoCromosoma").toString();
		Cromosoma tipoCromosoma = createCromosoma(crom, props);
		if(tipoCromosoma==null) return null;
		
		Cromosoma[] poblInicial = new Cromosoma[tam];
		for (int i = 0; i < poblInicial.length; i++) {
			Cromosoma nuevo = createCromosoma(crom, props);
			poblInicial[i]=nuevo;
		}
		return poblInicial;
	}
}
