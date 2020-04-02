package Main;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.SwingChart;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.math.plot.Plot2DPanel;

import Core.Cruce.CruceAritmetico;
import Core.Cruce.CruceMonoPunto;
import Core.Cruce.CruceUniforme;
import Core.Mutacion.MutacionBasica;
import Core.Mutacion.MutacionUniforme;
import Core.Selection.SeleccionEstocastica;
import Core.Selection.SeleccionRuleta;
import Core.Selection.SeleccionTorneo;
import Core.Selection.TipoFitness;
import Gen.Cromosoma;
import Gen.Cromosoma2DF1;
import Gen.Cromosoma2DF2;
import Gen.Cromosoma2DF3;
import Gen.CromosomaNDF4;
import Gen.CromosomaRealND;

public class Main {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { System.exit(-1);}
		
		// Define a function to plot
		Mapper mapper = new Mapper() {
			public double f(double x, double y) {
				return (21.5 + x * Math.sin(4 * Math.PI * x) + y * Math.sin(4 * Math.PI * y));
			}
		};
		Mapper mapper2 = new Mapper() {
			public double f(double x, double y) {
				
				double res=0;
				res -= (Math.abs(
							Math.sin(x)
							*Math.cos(y)
							*Math.exp((Math.abs(1-(Math.sqrt(x*x + y*y)/Math.PI))))));
				return res;
			}
		};
		Mapper mapper3 = new Mapper() {
			public double f(double x, double y) {
				double a, b;
				a = b = 0;
				for (int i = 1; i < 6; i++) {
					a+= i*Math.cos(((i+1)*x)+1);
				}
				for (int i = 1; i < 6; i++) {
					b+= i*Math.cos(((i+1)*y)+1);
				}
				return a*b;
			}
		};
		Mapper mapper4 = new Mapper() {
			public double f(double x, double y) {
				double res = -Math.sin(x)*Math.pow(
								    Math.sin(
								    		((2)*x*x)/Math.PI), 20)
						     -Math.sin(x)*Math.pow(
							        Math.sin(
							    		    ((3)*x*x)/Math.PI), 20);
				return res;
			}
		};

		// Define range and precision for the function to plot
		int steps = 150;
		
		// Create a surface drawing that function
		Shape surface = Builder.buildOrthonormal(
				new OrthonormalGrid(new Range(-3.0f, 12f), steps, new Range(4.1f, 5.8f), steps), mapper);
		
		surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZRange()));
		surface.setFaceDisplayed(true);
		surface.setWireframeDisplayed(false);
		surface.setWireframeColor(Color.BLACK);

		// Create a chart and add the surface
		Chart chart = new SwingChart(Quality.Advanced);
		chart.add(surface);
		
		chart.addMouseCameraController();
		chart.addMousePickingController(1);
		
		/*Coord3d[] points = new Coord3d[1];
		Color[] colors = new Color[1];
		points[0] = new Coord3d(11.625f, 5.726f, 39f);
		colors[0] = new Color(0f, 0f, 0f);
		Scatter scat = new Scatter(points, colors);
		scat.setWidth(10);
		chart.add(scat);*/
		
		Gui gui = new Gui();
		Plot2DPanel plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		PGenetico pg = new PGenetico(100, 100, 0.6, 0.05, 0, null);
		gui.cbFuncionSeleccionada.addActionListener((a)->{
			if(surface.isDisplayed()) {
				surface.clear();
				surface.dispose();
				chart.removeDrawable(surface);
			}
			int sel = gui.cbFuncionSeleccionada.getSelectedIndex();
			if(sel==0) {
				Shape sf = Builder.buildOrthonormal(
						new OrthonormalGrid(new Range(-3.0f, 12f), steps, new Range(4.1f, 5.8f), steps), mapper);
				
				sf.setColorMapper(new ColorMapper(new ColorMapRainbow(), sf.getBounds().getZRange()));
				sf.setFaceDisplayed(true);
				sf.setWireframeDisplayed(false);
				sf.setWireframeColor(Color.BLACK);
				
				gui.panel_7.removeAll();
				Chart c = new SwingChart(Quality.Advanced);
				
				c.add(surface);

				c.addMouseCameraController();
				c.addMousePickingController(1);
				c.add(sf);
				gui.panel_7.add((Component)c.getCanvas());
				gui.panel_7.repaint();
			}else if(sel==1) {
				Shape sf = Builder.buildOrthonormal(
						new OrthonormalGrid(new Range(-10f, 10f), steps, new Range(-10f, 10f), steps), mapper2);
				
				sf.setColorMapper(new ColorMapper(new ColorMapRainbow(), sf.getBounds().getZRange()));
				sf.setFaceDisplayed(true);
				sf.setWireframeDisplayed(false);
				sf.setWireframeColor(Color.BLACK);
				
				gui.panel_7.removeAll();
				Chart c = new SwingChart(Quality.Advanced);
				c.add(surface);

				c.addMouseCameraController();
				c.addMousePickingController(1);
				c.add(sf);
				gui.panel_7.add((Component)c.getCanvas());
				gui.panel_7.repaint();
			}else if(sel==2) {
				Shape sf = Builder.buildOrthonormal(
						new OrthonormalGrid(new Range(-10f, 10f), steps, new Range(-10f, 10f), steps), mapper3);
				
				sf.setColorMapper(new ColorMapper(new ColorMapRainbow(), sf.getBounds().getZRange()));
				sf.setFaceDisplayed(true);
				sf.setWireframeDisplayed(false);
				sf.setWireframeColor(Color.BLACK);
				
				gui.panel_7.removeAll();
				Chart c = new SwingChart(Quality.Advanced);
				c.add(surface);

				c.addMouseCameraController();
				c.addMousePickingController(1);
				c.add(sf);
				gui.panel_7.add((Component)c.getCanvas());
				gui.panel_7.repaint();
			}else if(sel==3) {
				Shape sf = Builder.buildOrthonormal(
						new OrthonormalGrid(new Range(0f, (float) Math.PI), steps, new Range(0f, (float) Math.PI), steps), mapper4);
				
				sf.setColorMapper(new ColorMapper(new ColorMapRainbow(), sf.getBounds().getZRange()));
				sf.setFaceDisplayed(true);
				sf.setWireframeDisplayed(false);
				sf.setWireframeColor(Color.BLACK);
				
				gui.panel_7.removeAll();
				Chart c = new SwingChart(Quality.Advanced);
				c.add(surface);

				c.addMouseCameraController();
				c.addMousePickingController(1);
				c.add(sf);
				gui.panel_7.add((Component)c.getCanvas());
				gui.panel_7.repaint();
			}
		});
		gui.panel_6.add(plot);
		gui.panel_7.add((Component)chart.getCanvas());
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
					int res = inicializarPGenetico(gui,pg);
					if(res == -1) return;
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
							media += (((double)poblacion[i].getFenotipo()) / pg.getTamPoblacion());
						}
						Cromosoma mejorCromosoma = pg.getMejorPoblacion();
						
						double mejor = (double) mejorCromosoma.getFenotipo();
						mejores[pg.getGeneracionActual()-1] = mejor;
						
						if(pg.getTipoFitness()==TipoFitness.MINIMIZAR) {
							if(mejor<mejorAbs) {
								mejorAbs = mejor;
								gui.jtaLog.append("mejorAbsoluto: " + mejorCromosoma.toString()+" ms:"+System.currentTimeMillis()+ "\n");
							}
						} else {
							if(mejor>mejorAbs) {
								mejorAbs = mejor;
								gui.jtaLog.append("mejorAbsoluto: " + mejorCromosoma.toString()+" ms:"+System.currentTimeMillis()+ "\n");
							}
						}
						mejoresAbs[pg.getGeneracionActual()-1] = mejorAbs;
						mediaArr[pg.getGeneracionActual()-1] = media;
						
						
						plot.addLinePlot("mejores gen", mejores);
						plot.addLinePlot("media gen", mediaArr);
						plot.addLinePlot("mejor Abs.", mejoresAbs);
						gui.progressBar.setValue(pg.getGeneracionActual());
						gui.progressBar.update(gui.progressBar.getGraphics());
					}
				});
			} else if(a.getActionCommand().equalsIgnoreCase("detener")){
				gui.btnEjecutar.setActionCommand("ejecutar");
				gui.btnEjecutar.setText("Ejecutar");
				gui.progressBar.setValue(0);
				pg.reiniciarBusqueda();
				plot.removeAllPlots();
				gui.jtaLog.setText("");
			}
		});
		gui.setVisible(true);
	}

	private static int inicializarPGenetico(Gui gui, PGenetico pg) {
		try {
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
		}catch(Exception e) {
			JOptionPane.showMessageDialog(gui, "datos incorrectos", "error", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		return 0;
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
