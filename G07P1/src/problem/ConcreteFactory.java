package problem;

import java.util.HashMap;

import core.cruce.Cruce;
import core.fitness.Fitness;
import core.mutacion.Mutacion;
import core.selection.Seleccion;
import gen.Cromosoma;
import view.View;

@SuppressWarnings("serial")
public class ConcreteFactory implements ProblemFactory {
	
	HashMap<String, ProblemFactory> factorias = new HashMap<>() {{
		put("funcion 1"		, new P1Factory());
		put("Holder table"	, new P2Factory());
		put("Schubert"		, new P3Factory());
		put("Michalewicz"	, new P4Factory());
		put("Problema5"		, new P5Factory());
		put("Multiplexor"	, new P6Factory());
		put("DeJong"	    , new P7Factory());
		put("Easom" 	    , new P8Factory());
		put("CrossInTray"   , new P9Factory());
		put("Drop-wave"     , new P10Factory());
		put("Bukin"         , new P11Factory());
	}};
	
	ProblemFactory selectedF;

	public ConcreteFactory(String tipoFactoria) {
		if(factorias.containsKey(tipoFactoria)) 
			selectedF = factorias.get(tipoFactoria);
	}

	@Override
	public Cromosoma createCromosoma(String tipo, HashMap<String,Object> props) {
		return selectedF.createCromosoma(tipo, props);
	}

	@Override
	public Cruce createCruce(String tipo, HashMap<String,Object> props) {
		return selectedF.createCruce(tipo, props);
	}

	@Override
	public Seleccion createSeleccion(String tipo, HashMap<String,Object> props) {
		return selectedF.createSeleccion(tipo, props);
	}

	@Override
	public Fitness createFitness(String tipo, HashMap<String,Object> props) {
		return selectedF.createFitness(tipo, props);
	}

	@Override
	public Cromosoma[] createPoblacionInicial(String tipoCromosoma, HashMap<String,Object> props, int tam) {
		return selectedF.createPoblacionInicial(tipoCromosoma, props, tam);
	}

	@Override
	public View createView(HashMap<String,Object> props) {
		return selectedF.createView(props);
	}

	@Override
	public Mutacion createMutacion(String tipo, HashMap<String,Object> props) {
		return selectedF.createMutacion(tipo, props);
	}
}
