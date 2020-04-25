package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import core.fitness.TipoFitness;

public class MainModel {
	private String title = "Practica Programacion Genetica";
	
	private final HashMap<String, Object> props = new HashMap<>();
	public final HashMap<String, List<Object>> posibleValues = new HashMap<>();
	public final Set<String> funciones = new HashSet<>();
	public final Set<String> cromosomas = new HashSet<>();
	public final Set<String> selecciones = new HashSet<>();
	public final Set<String> cruces = new HashSet<>();
	public final Set<String> mutaciones = new HashSet<>();
	
	public final Event titleChangedEvent = new Event();
	HashMap<String, Event> propObservers = new HashMap<>();

	private HashMap<String,String> crom4fun;
	private HashMap<String,String> mut4crom;
	private HashMap<String,String> cruces4crom;

	private int searchProgress;
	
	public MainModel() {
		funciones.addAll(
				Arrays.asList("funcion 1",
						"Holder table", 
						"Michalewicz", 
						"Schubert", 
						"Problema5"));
		cromosomas.addAll(
				Arrays.asList("Binario", 
						"Real", 
						"Permutacion"));
		selecciones.addAll(
				Arrays.asList("Ruleta", 
						"Estocastico",
						"Torneo", 
						"Ranking", 
						"Truncamiento"));
		cruces.addAll(
				Arrays.asList("Monopunto", 
						"Uniforme",
						"Aritmetico",
						"CruceCO", 
						"CruceCX", 
						"CruceOX",
						"CruceOXPP", 
						"CrucePMX", 
						"CruceERX"));
		mutaciones.addAll(
				Arrays.asList("Basica",
						"Uniforme",
						"Insercion",
						"Intercambio",
						"Inversion",
						"Heuristica"));
		
		cruces4crom = new HashMap<>();
		cruces4crom.put("Binario"	 , "Monopunto, Uniforme");
		cruces4crom.put("Real"		 , "Monopunto, Uniforme, Aritmetico");
		cruces4crom.put("Permutacion", "CruceCO, CruceCX, CruceOX, CruceOXPP, CrucePMX, CruceERX");
		
		mut4crom = new HashMap<>();
		mut4crom.put("Binario"		, "Basica");
		mut4crom.put("Real"			, "Uniforme");
		mut4crom.put("Permutacion"	, "Insercion, Inversion, Intercambio, Heuristica");
		
		crom4fun = new HashMap<>();
		crom4fun.put("funcion 1"	, "Binario");
		crom4fun.put("Holder table"	, "Binario");
		crom4fun.put("Schubert"		, "Binario");
		crom4fun.put("Michalewicz"	, "Binario, Real");
		crom4fun.put("Problema5"	, "Permutacion");
		
		props.put("tamPoblacion"	, Integer.valueOf(100));
		props.put("maxIteraciones"	, Integer.valueOf(100));
		props.put("tolerancia"		, Double.valueOf(0.001d));
		props.put("randomSeed"		, Boolean.TRUE);
		props.put("seed"			, Long.valueOf(System.currentTimeMillis()));
		props.put("numVariables"	, Integer.valueOf(2));
		props.put("%elitismo"		, Double.valueOf(0.0d));
		props.put("probCruce"		, Double.valueOf(0.6d));
		props.put("probMutacion"	, Double.valueOf(0.1d));
		props.put("funcion"			, "funcion 1");
		props.put("minX"			, Double.valueOf(-3.0d));
		props.put("maxX"			, Double.valueOf(12.1d));
		props.put("minY"			, Double.valueOf(4.1d));
		props.put("maxY"			, Double.valueOf(5.8d));
		
		posibleValues.put("tipoSeleccion"	, selecciones
				.stream()
				.collect(Collectors.toList()));
		posibleValues.put("funcion"			, funciones
				.stream()
				.collect(Collectors.toList()));
		
		props.put("tipoCromosoma"	, "Binario");
		addPropObserver("tipoCromosoma", this::generaCruces4crom);
		addPropObserver("tipoCromosoma", this::generaMut4crom);
		addPropObserver("funcion"		,this::generaCroms4Fun);
		addPropObserver("funcion"		,this::setDefVals4Fun);
		addPropObserver("funcion"		,this::setDefFitness4Fun);
		addPropObserver("randomSeed"	,this::updateRandomSeed);
		props.put("funcion"			, "funcion 1");
		props.put("tipoSeleccion"	, "Ruleta");
		props.put("tipoCruce"		, "Monopunto");
		props.put("tipoMutacion"	, "Basica");
		props.put("tipoFitness"		, TipoFitness.MAXIMIZAR);
		generaCroms4Fun();
		setSearchProgress(0);
		props.put("mejores"			, new ArrayList<Double>());
		props.put("media"			, new ArrayList<Double>());
		props.put("mejores"			, new ArrayList<Double>());
		
		props.put("kindividuos"		, Integer.valueOf(3));//SeleccionEstocastica
		props.put("tamMuestra"		, Integer.valueOf(3));//SeleccionTorneo
		props.put("umbral"			, Double.valueOf(1d));//SeleccionTorneo
		props.put("beta"			, Double.valueOf(1d));//SeleccionRanking
		props.put("trunc"			, Double.valueOf(0.5d));//SeleccionTruncamiento
		props.put("alfa"			, Double.valueOf(0.6d));//CruceAritmetico
		props.put("numGens2Xchng"	, Integer.valueOf(2));//CruceOXPP
		props.put("probXchngGen"	, Double.valueOf(0.4d));//CruceUniforme
		props.put("probFlipBit"		, Double.valueOf(0.15d));//MutacionBasica
		props.put("numGen2Perm"		, Integer.valueOf(3));//MutacionHeuristica
		props.put("numGens2Ins"		, Integer.valueOf(1));//MutacionInsercion
		props.put("numG2Inv"		, Integer.valueOf(2));//MutacionIntercambio
		props.put("fichero"			, "ajuste.dat");
	}
	
	private void generaCroms4Fun() {
		String fun = getPropsMap().get("funcion").toString();
		String str = crom4fun.get(fun);
		List<Object> ret = cromosomas.stream()
				.filter(crom->str.indexOf(crom)!=-1)
				.collect(Collectors.toList());
		posibleValues.put("tipoCromosoma", ret);
		String crom = getPropsMap().get("tipoCromosoma").toString();
		if(!ret.contains(crom))
			getPropsMap().put("tipoCromosoma", ret.get(0));
		if(propObservers.containsKey("tipoCromosoma"))
			propObservers.get("tipoCromosoma").notifyAllObservers();
	}

	private void generaCruces4crom() {
		String crom = getPropsMap().get("tipoCromosoma").toString();
		List<Object> cruces2 = cruces.stream()
				.filter(cruce->cruces4crom.get(crom).indexOf(cruce)!=-1)
				.collect(Collectors.toList());
		posibleValues.put("tipoCruce", cruces2);
		String cruc = getPropsMap().get("tipoCruce").toString();
		if(!cruces2.contains(cruc)) {
			getPropsMap().put("tipoCruce", cruces2.get(0));
		}
		if(propObservers.containsKey("tipoCruce")) 
			propObservers.get("tipoCruce").notifyAllObservers();
	}

	private void generaMut4crom() {
		String crom = getPropsMap().get("tipoCromosoma").toString();
		List<Object> mutaciones2 = mutaciones.stream()
				.filter(mut->mut4crom.get(crom).indexOf(mut)!=-1)
				.collect(Collectors.toList());
		posibleValues.put("tipoMutacion", mutaciones2);
		String muta = getPropsMap().get("tipoMutacion").toString();
		if(!mutaciones2.contains(muta)) {
			getPropsMap().put("tipoMutacion", mutaciones2.get(0));
		}
		if(propObservers.containsKey("tipoMutacion")) 
			propObservers.get("tipoMutacion").notifyAllObservers();
	}
	
	private void setDefVals4Fun() {
		String selF = getPropsMap().get("funcion").toString();
		if(selF.equalsIgnoreCase("funcion 1")) {
			getPropsMap().put("minX",Double.valueOf(-3.0d));
			getPropsMap().put("maxX",Double.valueOf(12.1d));
			getPropsMap().put("minY",Double.valueOf(4.1d));
			getPropsMap().put("maxY",Double.valueOf(5.8d));
		} else if(selF.equalsIgnoreCase("Holder table")) {
			getPropsMap().put("minX",Double.valueOf(-10d));
			getPropsMap().put("maxX",Double.valueOf(10d));
			getPropsMap().put("minY",Double.valueOf(-10d));
			getPropsMap().put("maxY",Double.valueOf(10d));
		} else if(selF.equalsIgnoreCase("Schubert")) {
			getPropsMap().put("minX",Double.valueOf(-10d));
			getPropsMap().put("maxX",Double.valueOf(10d));
			getPropsMap().put("minY",Double.valueOf(-10d));
			getPropsMap().put("maxY",Double.valueOf(10d));
		} else if(selF.equalsIgnoreCase("Michalewicz")) {
			getPropsMap().put("minX",Double.valueOf(0d));
			getPropsMap().put("maxX",Double.valueOf(Math.PI));
			getPropsMap().put("minY",Double.valueOf(0d));
			getPropsMap().put("maxY",Double.valueOf(Math.PI));
		} else if(selF.equalsIgnoreCase("Problema5")) {
			setPropValue("fichero", "ajuste.dat");
		}
	}
	
	private void setDefFitness4Fun() {
		String selF = getPropsMap().get("funcion").toString();
		if(selF.equalsIgnoreCase("funcion 1")) 
			props.put("tipoFitness", TipoFitness.MAXIMIZAR);
		if(selF.equalsIgnoreCase("Holder table")) 
			props.put("tipoFitness", TipoFitness.MINIMIZAR);
		else if(selF.equalsIgnoreCase("Michalewicz")) 
			props.put("tipoFitness", TipoFitness.MINIMIZAR);
		else if(selF.equalsIgnoreCase("Schubert")) 
			props.put("tipoFitness", TipoFitness.MINIMIZAR);
		else if(selF.equalsIgnoreCase("Problema5")) 
			props.put("tipoFitness", TipoFitness.MINIMIZAR);
	}
	
	private void updateRandomSeed() {
		Boolean randEnabled = (Boolean) props.get("randomSeed");
		if(randEnabled) setPropValue("seed", Long.valueOf(System.currentTimeMillis()));
 }
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		titleChangedEvent.notifyAllObservers();
	}
	public void addPropObserver(String prop, Observer obs) {
		if(!propObservers.containsKey(prop)) 
			propObservers.put(prop, new Event()); 
		propObservers.get(prop).addObserver(obs);
	}
	public void remProp(String prop) {
		getPropsMap().remove(prop);
		posibleValues.remove(prop);
		propObservers.remove(prop);
	}
	public void setPropValue(String prop, Object value) {
		if(value==null) return;
		
		if(!propObservers.containsKey(prop)) 
			propObservers.put(prop, new Event()); 
		
		getPropsMap().put(prop, value);
		propObservers.get(prop).notifyAllObservers();
	}
	public boolean containsProp(String prop) {
		return getPropsMap().containsKey(prop);
	}
	public void setPropValues(String prop, List<Object> values) {
		if(!propObservers.containsKey(prop))
			propObservers.put(prop, new Event());
		posibleValues.put(prop, values);
	}
	public Object getPropValue(String prop) {
		return getPropsMap().get(prop);
	}
	public List<Object> getPropValues(String prop) {
		return posibleValues.get(prop);
	}

	public int getSearchProgress() {
		return searchProgress;
	}

	public void setSearchProgress(int searchProgress) {
		this.searchProgress = searchProgress;
	}

	public HashMap<String, Object> getPropsMap() {
		return props;
	}
}
