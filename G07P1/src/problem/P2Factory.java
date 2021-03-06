package problem;

import java.awt.Component;
import java.util.HashMap;
import java.util.Random;

import org.jzy3d.chart.AWTChart;
import org.jzy3d.chart.Chart;
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
import gen.Cromosoma2DF2;
import mapper.F2Mapper;
import utils.Utils;
import view.View;

public class P2Factory implements ProblemFactory{

	private Shape surface;
	private Chart chart;
	private Random random = Utils.random;

	@Override
	public Cromosoma createCromosoma(String tipo, HashMap<String,Object> props) {
		if(tipo.equalsIgnoreCase("Binario")) {
			Double tolerancia 	= (Double) props.get("tolerancia");
			Cromosoma2DF2 crom = new Cromosoma2DF2();
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
		
//		if(props.containsKey("chart")) {
//			chart = (Chart) props.get("chart");
//			chart.getScene().clear();
//			chart.getScene().dispose();
//		}
		
		surface = Surface.shape(new F2Mapper(), 
				new Range(-10f, 10f), 
				new Range(-10f, 10f), 
				steps, 
				new ColorMapRainbow(),
				0.5f); 

		ContourAxeBox cab = new ContourAxeBox(surface.getBounds());
		MapperContourPictureGenerator contour = new MapperContourPictureGenerator(
				new F2Mapper(), new Range(-10f, 10f), new Range(-10f, 10f));
		ColorMapper myColorMapper=new ColorMapper(
				new ColorMapRainbow(), 
				surface.getBounds().getZmin(), surface.getBounds().getZmax(), 
				new Color(1,1,1,.5f));
		cab.setContourImg( contour.getHeightMap(
				new DefaultContourColoringPolicy(myColorMapper), 400, 400, 10), 
				new Range(-10f, 10f), new Range(-10f, 10f));
		cab.getLayout().setXTickRenderer(Double::toString);
		cab.getLayout().setYTickRenderer(Double::toString);
		cab.getLayout().setZTickRenderer(Double::toString);
		
		chart = new AWTChart(Quality.Nicest);
		chart.add(surface);
		chart.addMouseCameraController();
		chart.getView().setAxe(cab);
//		props.put("chart", chart);
		
		return new View() {
			@Override
			public Component getComponent() {
				return (Component) chart.getCanvas();
			}
		};
	}

}
