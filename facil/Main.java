package facil;

import java.awt.EventQueue;
import java.util.Random;

import javax.swing.UIManager;

import org.math.plot.Plot2DPanel;
import org.math.plot.Plot3DPanel;

public class Main {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { System.exit(-1);}
		
		Gui gui = new Gui();
		Plot2DPanel plot = new Plot2DPanel();
		Plot3DPanel plot3d = new Plot3DPanel();
		plot.addLegend("SOUTH");
		PGenetico pg = new PGenetico(100, 100, 0.6, 0.05, 0, null);
		gui.panel_6.add(plot);
		gui.panel_7.add(plot3d);
		gui.btnPaso.setEnabled(false);
		gui.cbFuncionSeleccionada.addActionListener((a)->{
			int i = gui.cbFuncionSeleccionada.getSelectedIndex();
			if(i==3) {
				gui.tfNumVariables.setEnabled(true);
				gui.cbTipoCromosoma.setEnabled(true);
			} else {
				gui.tfNumVariables.setEnabled(false);
				gui.cbTipoCromosoma.setEnabled(false);
			}
		});
		gui.btnEjecutar.addActionListener((a)->{
			
			if(a.getActionCommand().equalsIgnoreCase("ejecutar")) {
				EventQueue.invokeLater(()->{
					inicializarPGenetico(gui,pg);
					double[] mejores  = new double[pg.getNumIteraciones()];
					double[] mejoresAbs = new double[pg.getNumIteraciones()];
					double[] mediaArr = new double[pg.getNumIteraciones()];
					double mejorAbs = 0;
					if(pg.getTipoFitness()==TipoFitness.MAXIMIZAR) {
						mejorAbs = Double.MIN_VALUE;
					} else {
						mejorAbs = Double.MAX_VALUE;
					}
					
					while(pg.getGeneracionActual()<pg.getNumIteraciones()) {
						plot.removeAllPlots();
						pg.buscarNiter(1);
						Cromosoma[] poblacion = pg.getPoblacion();
						
						double media = 0;
						for (int i = 0; i < poblacion.length; i++) {
							media += ((double)poblacion[i].getFenotipo()) / pg.getTamPoblacion();
						}
						double mejor = (double) pg.getMejorPoblacion().getFenotipo();
						mejores[pg.getGeneracionActual()-1] = mejor;
						
						if(pg.getTipoFitness()==TipoFitness.MINIMIZAR) {
							if(mejor<mejorAbs) mejorAbs = mejor;
						} else {
							if(mejor>mejorAbs) mejorAbs = mejor;
						}
						mejoresAbs[pg.getGeneracionActual()-1] = mejorAbs;
						mediaArr[pg.getGeneracionActual()-1] = media;
						
						
						plot.addLinePlot("mejores gen", mejores);
						plot.addLinePlot("media gen", mediaArr);
						plot.addLinePlot("mejor Abs.", mejoresAbs);
						gui.progressBar.setValue(pg.getGeneracionActual());
					}
				});
			} else if(a.getActionCommand().equalsIgnoreCase("detener")){
				gui.btnEjecutar.setActionCommand("ejecutar");
				gui.btnEjecutar.setText("Ejecutar");
				gui.progressBar.setValue(0);
				pg.reiniciarBusqueda();
				plot.removeAllPlots();
			}
		});
		gui.setVisible(true);
	}

	private static void inicializarPGenetico(Gui gui, PGenetico pg) {
		int tamPoblacion, ngeneraciones, tipoFuncion, tipoSeleccion, tipoCruce, tipoMutacion, tipoCromosomaElegido, numVariables;
		double prcjElitismo, prbCruce, prbMutacion, tolerancia;
		tamPoblacion = Integer.parseInt(gui.tfTamPoblacion.getText());
		ngeneraciones = Integer.parseInt(gui.tfNGeneraciones.getText());
		tipoFuncion = gui.cbFuncionSeleccionada.getSelectedIndex();
		tipoSeleccion = gui.cbTipoSeleccion.getSelectedIndex();
		tipoCruce = gui.cbTipoCruce.getSelectedIndex();
		tipoMutacion = gui.cbTipoMutacion.getSelectedIndex();
		tipoCromosomaElegido = gui.cbTipoCromosoma.getSelectedIndex();
		prcjElitismo = Double.parseDouble(gui.tfPorcntjElitismo.getText());
		prbCruce = Double.parseDouble(gui.tfProbCruce.getText());
		prbMutacion = Double.parseDouble(gui.tfProbMutacion.getText());
		tolerancia = Double.parseDouble(gui.tfTolerancia.getText());
		numVariables = Integer.parseInt(gui.tfNumVariables.getText());
		gui.btnEjecutar.setActionCommand("detener");
		gui.btnEjecutar.setText("Limpiar");
		pg.setTamPoblacion(tamPoblacion);
		pg.setNumIteraciones(ngeneraciones);
		pg.setProbCruce(prbCruce);
		pg.setProbMutacion(prbMutacion);
		pg.setPctjElitismo(prcjElitismo);
		Cromosoma.tolerancia = tolerancia;
		if(tipoSeleccion==0) pg.setTipoSeleccion(new SeleccionRuleta());
		if(tipoSeleccion==1) pg.setTipoSeleccion(new SeleccionEstocastica());
		if(tipoSeleccion==2) pg.setTipoSeleccion(new SeleccionTorneo());
		if(tipoCruce == 0) pg.setTipoCruce(new CruceMonoPunto());
		if(tipoCruce == 1) pg.setTipoCruce(new CruceUniforme());
		if(tipoCruce == 2) pg.setTipoCruce(new CruceAritmetico());
		if(tipoMutacion == 0) pg.setTipoMutacion(new MutacionBasica());
		if(tipoMutacion == 1) pg.setTipoMutacion(new MutacionUniforme());
		Cromosoma tipoCromosoma = new Cromosoma2DF1();
		if(tipoFuncion==0) {
			tipoCromosoma = new Cromosoma2DF1();
			pg.setTipoFitness(TipoFitness.MAXIMIZAR);
		}
		if(tipoFuncion==1) {
			tipoCromosoma = new Cromosoma2DF2();
			pg.setTipoFitness(TipoFitness.MINIMIZAR);
		}
		if(tipoFuncion==2) {
			tipoCromosoma = new Cromosoma2DF3();
			pg.setTipoFitness(TipoFitness.MINIMIZAR);
		}
		if(tipoFuncion==3) {
			tipoCromosoma = new CromosomaNDF4(4);
			if(numVariables<4) numVariables = 4;
			if(tipoCromosomaElegido==1) tipoCromosoma = new CromosomaRealND(numVariables);
			pg.setTipoFitness(TipoFitness.MINIMIZAR);
		}
		inicializaPoblacionInicial(pg, tipoCromosoma);		
		gui.progressBar.setMaximum(pg.getNumIteraciones());
	}

	private static void inicializaPoblacionInicial(PGenetico pg, Cromosoma tipoCromosoma) {
		Cromosoma[] poblInicial = new Cromosoma[pg.getTamPoblacion()];
		Random r = new Random();
		for (int i = 0; i < poblInicial.length; i++) {
			Object[] genes = tipoCromosoma.getGenes();
			Cromosoma nuevo = tipoCromosoma.clonar();
			for (int j = 0; j < genes.length; j++) {
				Object gen;
				if(tipoCromosoma.getGenLen(j)>0)//cromosoma binario
					gen = r.nextInt((int)Math.pow(2, tipoCromosoma.getGenLen(j))-1);
				else {
					CromosomaRealND cr = (CromosomaRealND) tipoCromosoma;
					gen = cr.xmin + Math.random()*(cr.xmax-cr.xmin);
				}
				nuevo.setGen(j, gen);
			}
			poblInicial[i]=nuevo;
		}
		pg.setPoblacion(poblInicial);	
	}
	
}

/*EventQueue.invokeLater(()->{});*/
