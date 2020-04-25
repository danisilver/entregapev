package controller;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import core.cruce.Cruce;
import core.fitness.TipoFitness;
import core.mutacion.Mutacion;
import core.selection.Seleccion;
import gen.Cromosoma;
import main.PGenetico;
import model.MainModel;
import problem.ConcreteFactory;
import problem.ProblemFactory;
import utils.Utils;
import view.MainView;

public class MainController implements Controller{
	
	private MainView  view;
	private MainModel model;
	private PGenetico pg;
	private ProblemFactory    factory;
	private ArrayList<Double> maxValues = new ArrayList<>();
	private ArrayList<Double> values = new ArrayList<>();
	private ArrayList<Double> media = new ArrayList<>();

	public MainController(MainView view, MainModel model) {
		this.setPg(pg);
		this.setView(view);
		this.setModel(model);
		configViewEvents();
		configInitialState();
	}

	private void configViewEvents() {
		model.addPropObserver("randomSeed",		view::updateGeneralPanel);
		model.addPropObserver("seed", 			view::updateGeneralPanel);
		model.addPropObserver("tipoCromosoma", 	view::updateCromosomaPanel);
		model.addPropObserver("numVariables",	view::updateCromosomaPanel);
		model.addPropObserver("tipoSeleccion",	view::updateSeleccionPanel);
		model.addPropObserver("tipoCruce", 		view::updateCrucePanel);
		model.addPropObserver("tipoMutacion", 	view::updateMutacionPanel);
		model.addPropObserver("funcion", 		view::updateGeneralPanel);
		model.addPropObserver("funcion", 		view::updateCromosomaProps);
		model.addPropObserver("p3d",	 		view::update3dPlot);
		model.addPropObserver("funcion", 		this::replaceProblemView);
		model.addPropObserver("fichero",		this::loadDataFromFile);
		view.btnEjecutar.addActionListener(e->run());
	}

	private void configInitialState() {
		model.setPropValue("maxValues", maxValues);
		model.setPropValue("values", values);
		model.setPropValue("media", media);	
		view.updateView();
	}
	
	@Override
	public void run() {
		inicializarProblema();
		maxValues.clear(); values.clear(); media.clear();
		while(pg.getGeneracionActual()<pg.getNumIteraciones()) {
			pg.buscarNiter(1);
			
			maxValues.add(pg.getMaxFound());
			values.add(pg.getMaxIter());
			media.add(pg.getAverage());
			
			SwingUtilities.invokeLater(view::update2dPlot);
			SwingUtilities.invokeLater(view::updateProgressBar);
		}
	}
	
	private void replaceProblemView() {
		String funcion 			= model.getPropValue("funcion").toString();
		factory = new ConcreteFactory(funcion);
		Component pv = factory.createView(model.getPropsMap()).getComponent();
		model.setPropValue("p3d", pv);
	}

	private void inicializarProblema() {
		String funcion 			= model.getPropValue("funcion").toString();
		factory = new ConcreteFactory(funcion);
		Integer tamPoblacion	= (Integer)model.getPropValue("tamPoblacion");
		Integer maxIteraciones  = (Integer)model.getPropValue("maxIteraciones");

		Double probCruce 		= (Double)model.getPropValue("probCruce");
		Double probMutacion 	= (Double)model.getPropValue("probMutacion");
		Double prctjElitismo 	= (Double)model.getPropValue("%elitismo");
		
		Long seed 				= (Long) model.getPropValue("seed");
		Utils.random.setSeed(seed);
		
		Cromosoma[] poblacion = factory.createPoblacionInicial(
				funcion, model.getPropsMap(), tamPoblacion);
		pg = new PGenetico(maxIteraciones, probCruce, probMutacion, prctjElitismo, 
				poblacion);
		
		String selStr			= model.getPropValue("tipoSeleccion").toString();
		String cruceStr			= model.getPropValue("tipoCruce").toString();
		String mutStr			= model.getPropValue("tipoMutacion").toString();
		
		TipoFitness tf = (TipoFitness)model.getPropValue("tipoFitness");
		Seleccion ts = (Seleccion) factory.createSeleccion(selStr, model.getPropsMap());
		Cruce tc = (Cruce) factory.createCruce(cruceStr, model.getPropsMap());
		Mutacion tm = (Mutacion) factory.createMutacion(mutStr, model.getPropsMap());
		pg.setTipoFitness(tf);
		pg.setTipoSeleccion(ts);
		pg.setTipoCruce(tc);
		pg.setTipoMutacion(tm);
		
	}
	
	private void loadDataFromFile() {
		String fich = model.getPropValue("fichero").toString();
		Scanner scanner;
		scanner = new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream("res/"+fich));
		int tam = scanner.nextInt();
		int[][] distancias = new int[tam][tam];
		int[][] flujo = new int[tam][tam];
		for (int i = 0; i < tam; i++) 
			for (int j = 0; j < tam; j++) 
				distancias[i][j] = scanner.nextInt();

		for (int i = 0; i < tam; i++) 
			for (int j = 0; j < tam; j++) 
				flujo[i][j] = scanner.nextInt();

		scanner.close();
		model.setPropValue("flujo", flujo);
		model.setPropValue("distancias", distancias);
		model.setPropValue("tamMatrixFichero", Integer.valueOf(tam));
		
	}

	public PGenetico getPg() {
		return pg;
	}

	public void setPg(PGenetico pg) {
		this.pg = pg;
	}

	@Override
	public void restart() {
		maxValues.clear(); values.clear(); media.clear();
	}

	public MainView getView() {
		return view;
	}

	public void setView(MainView view) {
		this.view = view;
	}

	public MainModel getModel() {
		return model;
	}

	public void setModel(MainModel model) {
		this.model = model;
	}

	public ProblemFactory getFactory() {
		return factory;
	}

	public void setFactory(ProblemFactory factory) {
		this.factory = factory;
	}
	


}
