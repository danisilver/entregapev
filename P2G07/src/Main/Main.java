package Main;

import java.awt.Component;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

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

import Core.Cruce.CruceCO;
import Core.Cruce.CruceCX;
import Core.Cruce.CruceERX;
import Core.Cruce.CruceOX;
import Core.Cruce.CruceOXPP;
import Core.Cruce.CrucePMX;
import Core.Fitness.TipoFitness;
import Core.Mutacion.MutacionHeuristica;
import Core.Mutacion.MutacionInsercion;
import Core.Mutacion.MutacionIntercambio;
import Core.Mutacion.MutacionInversion;
import Core.Selection.SeleccionEstocastica;
import Core.Selection.SeleccionRanking;
import Core.Selection.SeleccionRuleta;
import Core.Selection.SeleccionTorneo;
import Core.Selection.SeleccionTruncamiento;
import Gen.Cromosoma;
import Gen.CromosomaNDP5;

public class Main {

	static Integer[][] N;
	static Integer[][] M;
	static int numValues;
	static Gui gui;

	public static void main(String[] args) {
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.exit(-1);
		}*/

		/*
		 * Coord3d[] points = new Coord3d[1]; Color[] colors = new Color[1]; points[0] =
		 * new Coord3d(11.625f, 5.726f, 39f); colors[0] = new Color(0f, 0f, 0f); Scatter
		 * scat = new Scatter(points, colors); scat.setWidth(10); chart.add(scat);
		 */

		gui = new Gui();
		Plot2DPanel plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		PGenetico pg = new PGenetico(100, 100, 0.6, 0.05, 0, null);
		gui.filechooserbutton.addActionListener((a) -> {
			readFile();
		});
		gui.panel_6.add(plot);
		gui.btnPaso.setEnabled(false);
		/*
		 * gui.cbFuncionSeleccionada.addActionListener((a)->{ int i =
		 * gui.cbFuncionSeleccionada.getSelectedIndex(); if(i==3) {
		 * gui.tfNumVariables.setEnabled(true); gui.cbTipoCromosoma.setEnabled(true); }
		 * else { gui.tfNumVariables.setEnabled(false);
		 * gui.cbTipoCromosoma.setEnabled(false); } });
		 */gui.btnEjecutar.addActionListener((a) ->

		{

			if (a.getActionCommand().equalsIgnoreCase("ejecutar")) {
				EventQueue.invokeLater(() -> {
					int res = inicializarPGenetico(gui, pg);
					if (res == -1)
						return;
					double[] mejores = new double[pg.getNumIteraciones()];
					double[] mejoresAbs = new double[pg.getNumIteraciones()];
					double[] mediaArr = new double[pg.getNumIteraciones()];
					double mejorAbs = 0;
					if (pg.getTipoFitness() == TipoFitness.MAXIMIZAR) {
						mejorAbs = Double.MIN_VALUE;
					} else {
						mejorAbs = Double.MAX_VALUE;
					}
					while (pg.getGeneracionActual() < pg.getNumIteraciones()) {
						plot.removeAllPlots();
						pg.buscarNiter(1);
						Cromosoma[] poblacion = pg.getPoblacion();

						double media = 0;
						for (int i = 0; i < poblacion.length; i++) {
							media += (((double) poblacion[i].getFenotipo()) / pg.getTamPoblacion());
						}
						Cromosoma mejorCromosoma = pg.getMejorPoblacion();

						double mejor = (double) mejorCromosoma.getFenotipo();
						mejores[pg.getGeneracionActual() - 1] = mejor;

						if (pg.getTipoFitness() == TipoFitness.MINIMIZAR) {
							if (mejor < mejorAbs) {
								mejorAbs = mejor;
								gui.jtaLog.append("mejorAbsoluto: " + mejorCromosoma.toString() + " ms:"
										+ System.currentTimeMillis() + "\n");
							}
						} else {
							if (mejor > mejorAbs) {
								mejorAbs = mejor;
								gui.jtaLog.append("mejorAbsoluto: " + mejorCromosoma.toString() + " ms:"
										+ System.currentTimeMillis() + "\n");
							}
						}
						mejoresAbs[pg.getGeneracionActual() - 1] = mejorAbs;
						mediaArr[pg.getGeneracionActual() - 1] = media;

						plot.addLinePlot("mejores gen", mejores);
						plot.addLinePlot("media gen", mediaArr);
						plot.addLinePlot("mejor Abs.", mejoresAbs);
						gui.progressBar.setValue(pg.getGeneracionActual());
						gui.progressBar.update(gui.progressBar.getGraphics());
					}
				});
			} else if (a.getActionCommand().equalsIgnoreCase("detener")) {
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

	private static void readFile() {
		try {
			gui.filechooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			gui.filechooser.setDialogTitle("Select the file");
			gui.filechooser.showOpenDialog(null);
			File selectedFile = gui.filechooser.getSelectedFile();
			BufferedReader br = new BufferedReader(new FileReader(selectedFile));
			String st = "";
			numValues = Integer.valueOf(br.readLine().replace(" ", ""));
			N = new Integer[numValues][numValues];
			st = br.readLine();
			st = br.readLine();
			for (int i = 0; i < numValues; st = br.readLine(), ++i) {
				var vals = new String[numValues];
				vals = st.split(" ");
				for (int j = 0; j < numValues; ++j)
					N[i][j] = Integer.valueOf(vals[j]);
			}
			M = new Integer[numValues][numValues];
			st = br.readLine();
			for (int i = 0; i < numValues; st = br.readLine(), ++i) {
				var vals = new String[numValues];
				vals = st.split(" ");
				for (int j = 0; j < numValues; ++j)
					M[i][j] = Integer.valueOf(vals[j]);
			}
			br.close();
		} catch (Exception ex) {

		}
	}

	private static int inicializarPGenetico(Gui gui, PGenetico pg) {
		try {
			int tamPoblacion, ngeneraciones, tipoSeleccion, tipoCruce, tipoMutacion;
			double prcjElitismo, prbCruce, prbMutacion, tolerancia;
			tamPoblacion = Integer.parseInt(gui.tfTamPoblacion.getText());
			ngeneraciones = Integer.parseInt(gui.tfNGeneraciones.getText());
			tipoSeleccion = gui.cbTipoSeleccion.getSelectedIndex();
			tipoCruce = gui.cbTipoCruce.getSelectedIndex();
			tipoMutacion = gui.cbTipoMutacion.getSelectedIndex();
			prcjElitismo = Double.parseDouble(gui.tfPorcntjElitismo.getText());
			prbCruce = Double.parseDouble(gui.tfProbCruce.getText());
			prbMutacion = Double.parseDouble(gui.tfProbMutacion.getText());
			tolerancia = Double.parseDouble(gui.tfTolerancia.getText());
			gui.btnEjecutar.setActionCommand("detener");
			gui.btnEjecutar.setText("Limpiar");
			pg.setTamPoblacion(tamPoblacion);
			pg.setNumIteraciones(ngeneraciones);
			pg.setProbCruce(prbCruce);
			pg.setProbMutacion(prbMutacion);
			pg.setPctjElitismo(prcjElitismo);
			Cromosoma.tolerancia = tolerancia;
			if (tipoSeleccion == 0)
				pg.setTipoSeleccion(new SeleccionRuleta());
			if (tipoSeleccion == 1)
				pg.setTipoSeleccion(new SeleccionEstocastica());
			if (tipoSeleccion == 2)
				pg.setTipoSeleccion(new SeleccionTorneo());
			if (tipoSeleccion == 3)
				pg.setTipoSeleccion(new SeleccionRanking());
			if (tipoSeleccion == 4)
				pg.setTipoSeleccion(new SeleccionTruncamiento());
			if (tipoCruce == 0)
				pg.setTipoCruce(new CrucePMX());
			if (tipoCruce == 1)
				pg.setTipoCruce(new CruceOX());
			if (tipoCruce == 2)
				pg.setTipoCruce(new CruceOXPP());
			if (tipoCruce == 3)
				pg.setTipoCruce(new CruceCX());
			if (tipoCruce == 4)
				pg.setTipoCruce(new CruceERX());
			if (tipoCruce == 5)
				pg.setTipoCruce(new CruceCO());
			if (tipoMutacion == 0)
				pg.setTipoMutacion(new MutacionHeuristica());
			if (tipoMutacion == 1)
				pg.setTipoMutacion(new MutacionInsercion());
			if (tipoMutacion == 2)
				pg.setTipoMutacion(new MutacionIntercambio());
			if (tipoMutacion == 3)
				pg.setTipoMutacion(new MutacionInversion());
			Cromosoma tipoCromosoma = new CromosomaNDP5(numValues, N, M);
			pg.setTipoFitness(TipoFitness.MINIMIZAR);
			inicializaPoblacionInicial(pg, tipoCromosoma);
			gui.progressBar.setMaximum(pg.getNumIteraciones());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(gui, "datos incorrectos", "error", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		return 0;
	}

	private static void inicializaPoblacionInicial(PGenetico pg, Cromosoma tipoCromosoma) {
		Cromosoma[] poblInicial = new Cromosoma[pg.getTamPoblacion()];
		Random r = new Random();
		ArrayList<Object> genes = tipoCromosoma.getGenes();
		for (int i = 0; i < poblInicial.length; i++) {
			Cromosoma nuevo = tipoCromosoma.clonar();
			Collections.shuffle(genes,r);
			int j = 0;
			for(Object ob : genes){
				nuevo.setGen(j, ob);
				++j;
			}
			poblInicial[i] = nuevo;
		}
		pg.setPoblacion(poblInicial);
	}

}

/* EventQueue.invokeLater(()->{}); */
