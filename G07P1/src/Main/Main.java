package Main;

import java.awt.EventQueue;
import java.util.Random;

import javax.swing.UIManager;

import org.math.plot.Plot2DPanel;

import Core.Cruce.CruceMonoPunto;
import Core.Cruce.CruceUniforme;
import Core.Mutacion.MutacionBasica;
import Core.Selection.SeleccionEstocastica;
import Core.Selection.SeleccionRuleta;
import Core.Selection.SeleccionTorneo;
import Core.Selection.TipoFitness;
import Gen.Cromosoma;
import Gen.Cromosoma2DF1;
import Gen.Cromosoma2DF2;
import Gen.Cromosoma2DF3;
import Gen.CromosomaNDF4;
public class Main {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { System.exit(-1);}
		
		Gui gui = new Gui();
		Plot2DPanel plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		PGenetico pg = new PGenetico(100, 100, 0.6, 0.05, 0, null);
		gui.panel_6.add(plot);
		gui.btnPaso.setEnabled(false);
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
				pg.reiniciarBusqueda();
				plot.removeAllPlots();
			}
		});
		gui.setVisible(true);
	}

	private static void inicializarPGenetico(Gui gui, PGenetico pg) {
		int tamPoblacion, ngeneraciones, tipoFuncion, tipoSeleccion, tipoCruce, tipoMutacion;
		double prcjElitismo, prbCruce, prbMutacion;
		tamPoblacion = Integer.parseInt(gui.tfTamPoblacion.getText());
		ngeneraciones = Integer.parseInt(gui.tfNGeneraciones.getText());
		tipoFuncion = gui.cbFuncionSeleccionada.getSelectedIndex();
		tipoSeleccion = gui.cbTipoSeleccion.getSelectedIndex();
		tipoCruce = gui.cbTipoCruce.getSelectedIndex();
		tipoMutacion = gui.cbTipoMutacion.getSelectedIndex();
		prcjElitismo = Double.parseDouble(gui.tfPorcntjElitismo.getText());
		prbCruce = Double.parseDouble(gui.tfProbCruce.getText());
		prbMutacion = Double.parseDouble(gui.tfProbMutacion.getText());
		gui.btnEjecutar.setActionCommand("detener");
		gui.btnEjecutar.setText("Limpiar");
		pg.setTamPoblacion(tamPoblacion);
		pg.setNumIteraciones(ngeneraciones);
		pg.setProbCruce(prbCruce);
		pg.setProbMutacion(prbMutacion);
		pg.setPctjElitismo(prcjElitismo);
		if(tipoSeleccion==0) pg.setTipoSeleccion(new SeleccionRuleta());
		if(tipoSeleccion==1) pg.setTipoSeleccion(new SeleccionTorneo());
		if(tipoSeleccion==2) pg.setTipoSeleccion(new SeleccionEstocastica());
		if(tipoCruce == 0) pg.setTipoCruce(new CruceMonoPunto());
		if(tipoCruce == 1) pg.setTipoCruce(new CruceUniforme());
		if(tipoMutacion == 0) pg.setTipoMutacion(new MutacionBasica());
		Cromosoma tipoCromosoma = new Cromosoma2DF1();
		if(tipoFuncion==0) tipoCromosoma = new Cromosoma2DF1();
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
				Integer g = r.nextInt((int)Math.pow(2, tipoCromosoma.getGenLen(j))-1);
				nuevo.setGen(j, g);
			}
			poblInicial[i]=nuevo;
		}
		pg.setPoblacion(poblInicial);	
	}
	
}

/*EventQueue.invokeLater(()->{});*/
