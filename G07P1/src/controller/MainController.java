package controller;

import java.util.List;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

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
        double[] maxValues, values, media;

        public MainController(MainView view, MainModel model) {
                this.setPg(pg);
                this.setView(view);
                this.setModel(model);
                configViewEvents();
        }

        private void configViewEvents() {
        	String[] props4GP = "funcion, randomSeed, seed, tolerancia".split(", ");
        	String[] props4CP = "tipoCromosoma, numVariables".split(", ");
        	model.addPropsObserver(props4GP, 		view::updateGeneralPanel);
        	model.addPropsObserver(props4CP, 		view::updateCromosomaPanel);
        	model.addPropObservers("funcion", 		view::updateCromosomaProps,
        											view::updateProblemView);
        	model.addPropObserver("tipoSeleccion",  view::updateSeleccionPanel);
        	model.addPropObserver("tipoCruce",      view::updateCrucePanel);
        	model.addPropObserver("tipoMutacion",   view::updateMutacionPanel);
        	model.addPropObserver("fichero",        this::loadDataFromFile);
        	view.btnEjecutar.addActionListener(e->new Thread(this::run).start());
        	view.updateView();
        }

        @Override
        public void run() {
        	view.btnEjecutar.setEnabled(false);
                inicializarProblema();
                new SwingWorkerProgress().execute();
                while(pg.getGeneracionActual()<pg.getNumIteraciones()) {
                        pg.buscarNiter(1);
                        
                        int it = pg.getGeneracionActual()-1;
						maxValues[it] = pg.getMaxFound();
    					values   [it] = pg.getMaxIter();
    					media    [it] = pg.getAverage();
    					
    					view.jtaLog.append("mejor iteracion:"+pg.getMaxIterCrom().toString()+"\n");
                }
                utils.Utils.showTreeCromosoma(pg.getMaxGlobalCrom());
        }

        private void inicializarProblema() {
                String funcion          = model.getPropValue("funcion").toString();
                factory = new ConcreteFactory(funcion);
                Integer tamPoblacion    = (Integer)model.getPropValue("tamPoblacion");
                Integer maxIteraciones  = (Integer)model.getPropValue("maxIteraciones");

                Double probCruce        = (Double)model.getPropValue("probCruce");
                Double probMutacion     = (Double)model.getPropValue("probMutacion");
                Double prctjElitismo    = (Double)model.getPropValue("%elitismo");
                
                Long seed               = (Long) model.getPropValue("seed");
                Utils.random.setSeed(seed);
                
                Cromosoma[] poblacion = factory.createPoblacionInicial(
                                funcion, model.getPropsMap(), tamPoblacion);
                pg = new PGenetico(maxIteraciones, probCruce, probMutacion, prctjElitismo, 
                                poblacion);
                
                String selStr           = model.getPropValue("tipoSeleccion").toString();
                String cruceStr         = model.getPropValue("tipoCruce").toString();
                String mutStr           = model.getPropValue("tipoMutacion").toString();
                
                TipoFitness tf = (TipoFitness)model.getPropValue("tipoFitness");
                Seleccion ts   = (Seleccion)  factory.createSeleccion(selStr, model.getPropsMap());
                Cruce tc       = (Cruce)      factory.createCruce(cruceStr, model.getPropsMap());
                Mutacion tm    = (Mutacion)   factory.createMutacion(mutStr, model.getPropsMap());
                pg.setTipoFitness(tf);
                pg.setTipoSeleccion(ts);
                pg.setTipoCruce(tc);
                pg.setTipoMutacion(tm);
                
    			maxValues = new double[maxIteraciones];
    			values    = new double[maxIteraciones];
    			media     = new double[maxIteraciones];
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
        
		private final class SwingWorkerProgress extends SwingWorker<Integer, Integer> {
			Integer maxIteraciones 	= (Integer) model.getPropValue("maxIteraciones");
			
			@Override
			protected Integer doInBackground() throws Exception {
				SwingUtilities.invokeLater(()->{
					view.p2d.removeAllPlots();
					view.p2d.setFixedBounds(1, 0, maxIteraciones);
					view.progressBar.setMaximum(maxIteraciones);
				});
				Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
				int generacionActual = pg.getGeneracionActual();
				while(pg.getGeneracionActual()<pg.getNumIteraciones()) {
					if(generacionActual<pg.getGeneracionActual()) {
						publish(pg.getGeneracionActual());
						generacionActual = pg.getGeneracionActual();
						model.setSearchProgress(pg.getGeneracionActual());
					}
					Thread.yield();
				}
				publish(maxIteraciones);
				view.btnEjecutar.setEnabled(true);
				return maxIteraciones;
			}

			@Override
			protected void process(List<Integer> chunks) {
				Integer prog = chunks.get(chunks.size()-1);
				view.progressBar.setValue(prog);
				view.p2d.removeAllPlots();
				view.p2d.addLinePlot("globales", maxValues);
				view.p2d.addLinePlot("locales", values);
				view.p2d.addLinePlot("media", media);
				view.jtaLog.append("mejor global:" +pg.getMaxGlobalCrom().toString()+"\n");
				super.process(chunks);
			}
		}

        @Override
        public void      restart()                 { }
        public PGenetico getPg()                   { return pg; }
        public MainView  getView()                 { return view; }
        public MainModel getModel()                { return model; }
        public void      setPg(PGenetico pg)       { this.pg = pg; }
        public void      setView(MainView view)    { this.view = view; }
        public void      setModel(MainModel model) { this.model = model; }
        public ProblemFactory getFactory()         { return factory; }
        public void      setFactory(ProblemFactory factory) { this.factory = factory; }

}
