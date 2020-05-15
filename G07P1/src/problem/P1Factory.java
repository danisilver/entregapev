package problem;

import java.awt.Component;
import java.util.HashMap;
import java.util.Random;

import org.jzy3d.chart.AWTChart;
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.Settings;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.contour.DefaultContourColoringPolicy;
import org.jzy3d.contour.MapperContourPictureGenerator;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.primitives.Surface;
import org.jzy3d.plot3d.primitives.axes.ContourAxeBox;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import core.cruce.Cruce;
import core.cruce.CruceMonoPunto;
import core.cruce.CruceUniforme;
import core.fitness.Fitness;
import core.mutacion.Mutacion;
import core.mutacion.MutacionBasica;
import core.selection.Seleccion;
import core.selection.SeleccionEstocastica;
import core.selection.SeleccionRanking;
import core.selection.SeleccionRuleta;
import core.selection.SeleccionTorneo;
import core.selection.SeleccionTruncamiento;
import gen.Cromosoma;
import gen.Cromosoma2DF1;
import mapper.F1Mapper;
import utils.Utils;
import view.View;

public class P1Factory implements ProblemFactory{

	private Shape surface;
	private Chart chart;
	private Random random = Utils.random;

	@Override
	public Cromosoma createCromosoma(String tipo, HashMap<String,Object> props) {
		if(tipo.equalsIgnoreCase("Binario")) {
			Double tolerancia 	= (Double) props.get("tolerancia");
			Cromosoma2DF1 crom = new Cromosoma2DF1();
			crom.setTolerancia(tolerancia);
			for (int j = 0; j < crom.getNumGenes(); j++) 
				crom.setGen(j, random.nextInt((int)Math.pow(2, crom.getGenLen(j))-1));
			return crom;
		} 
		return null;
	}

	@Override
	public Cruce createCruce(String tipo, HashMap<String,Object> props) {
		if(tipo.equalsIgnoreCase("Monopunto")) return new CruceMonoPunto();
		else if(tipo.equalsIgnoreCase("Uniforme")) {
			Double probXchngGen = (Double)props.get("probXchngGen");
			CruceUniforme cruceUniforme = new CruceUniforme();
			cruceUniforme.setProbXchngGen(probXchngGen);
			return cruceUniforme;
		}
		return null;
	}

	@Override
	public Seleccion createSeleccion(String tipo, HashMap<String,Object> props) {
		if(tipo.equalsIgnoreCase("Ruleta")) return new SeleccionRuleta();
		else if(tipo.equalsIgnoreCase("Estocastico")) {
			Integer kindividuos = (Integer)props.get("kindividuos");
			SeleccionEstocastica seleccionEstocastica = new SeleccionEstocastica();
			seleccionEstocastica.setKindividuos(kindividuos);
			return seleccionEstocastica;
		}
		else if(tipo.equalsIgnoreCase("Torneo")) {
			Integer tamMuestra 	= (Integer) props.get("tamMuestra");
			Double umbral 		= (Double) props.get("umbral");
			SeleccionTorneo seleccionTorneo = new SeleccionTorneo();
			seleccionTorneo.setTamMuestra(tamMuestra);
			seleccionTorneo.setUmbral(umbral);
			return seleccionTorneo;
		}
		else if(tipo.equalsIgnoreCase("Ranking")) {
			Double beta 		= (Double) props.get("beta");
			SeleccionRanking seleccionRanking = new SeleccionRanking();
			seleccionRanking.setBeta(beta);
			return seleccionRanking;
		}
		else if(tipo.equalsIgnoreCase("Truncamiento")) {
			Double trunc 		= (Double) props.get("trunc");
			SeleccionTruncamiento seleccionTruncamiento = new SeleccionTruncamiento();
			seleccionTruncamiento.setTrunc(trunc);
			return seleccionTruncamiento;
		}
		return null;
	}
	
	@Override
	public Mutacion createMutacion(String tipo, HashMap<String,Object> props) {
		if(tipo.equalsIgnoreCase("Basica")) {
			Double probFlipBit = (Double)props.get("probFlipBit");
			MutacionBasica mutacionBasica = new MutacionBasica();
			mutacionBasica.setProbFlipBit(probFlipBit);
			return mutacionBasica;
		}
		return null;
	}

	@Override
	public Fitness createFitness(String tipo, HashMap<String,Object> props) {
		return null;
	}

	@Override
	public View createView(HashMap<String,Object> props) {
		int steps = 100;
		Settings.getInstance().setHardwareAccelerated(true);
		surface = Surface.shape(new F1Mapper(), 
				new Range(-3.0f, 12f),  //rango x
				new Range(4.1f, 5.8f),  //rango y
				steps, 					//precision
				new ColorMapRainbow(),  //colores altura y profundidad
				0.2f);
		
		ContourAxeBox cab = new ContourAxeBox(surface.getBounds());
		MapperContourPictureGenerator contour = new MapperContourPictureGenerator(
				new F1Mapper(), 
				new Range(-3.0f, 12f), new Range(4.1f, 5.8f));
		ColorMapper myColorMapper = new ColorMapper(
				new ColorMapRainbow(), 
				surface.getBounds().getZmin(), surface.getBounds().getZmax(), 
				new Color(1,1,1,.5f));
		cab.setContourImg( 
				contour.getHeightMap(
						new DefaultContourColoringPolicy(myColorMapper), 400, 400, 10), 
				new Range(-3.0f, 12f), new Range(4.1f, 5.8f));
		cab.getLayout().setXTickRenderer(Double::toString);
		cab.getLayout().setYTickRenderer(Double::toString);
		cab.getLayout().setZTickRenderer(Double::toString);
		

		chart = new AWTChart(Quality.Nicest);
		chart.addMouseCameraController();
		chart.add(surface);
		chart.getView().setAxe(cab);

		
		return new View() {
			@Override
			public Component getComponent() {
				return (Component) chart.getCanvas();
			}
		};
	}

}
//new org.jzy3d.chart.AWTChart() 
//org.jzy3d.bridge
//org.jzy3d.bridge.swing.SimpleBufferedPanelSwing
//org.jzy3d.chart.SwingChart
//org.jzy3d.chart.factories.IChartComponentFactory.Toolkit.swing
//SwingChart chart = new SwingChart(Quality.Nicest);
//chart.getScene().clearView(c.getView());//this one doesnt work
//chart.getScene().clear();
//org.jzy3d.chart.ChartView.current();
//Chart chart = new org.jzy3d.chart.AWTChart(Quality.Nicest);
//org.jzy3d.chart.factories.ContourChartComponentFactory factory;
