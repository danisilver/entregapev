package problem;

import java.awt.Component;
import java.util.*;

import org.jzy3d.chart.*;
import org.jzy3d.colors.*;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.contour.*;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.primitives.*;
import org.jzy3d.plot3d.primitives.axes.ContourAxeBox;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import core.cruce.*;
import core.fitness.Fitness;
import core.mutacion.*;
import core.selection.*;
import gen.*;
import mapper.F4Mapper;
import utils.Utils;
import view.View;

public class P4Factory implements ProblemFactory{

	private Shape surface;
	private Chart chart;
	private Random random = Utils.random;

	@Override
	public Cromosoma createCromosoma(String tipo, HashMap<String, Object> props) {
		Integer numgenes = Integer.parseInt(props.get("numVariables").toString());
		if(tipo.equalsIgnoreCase("Binario")) {
			Double tolerancia 	= (Double) props.get("tolerancia");
			CromosomaNDF4 crom = new CromosomaNDF4(numgenes);
			crom.setTolerancia(tolerancia);
			for (int j = 0; j < crom.getNumGenes(); j++) 
				crom.setGen(j, random.nextInt((int)Math.pow(2, crom.getGenLen(j))-1));
			return crom;
		} else if(tipo.equalsIgnoreCase("Real")) {
			CromosomaRealND crom = new CromosomaRealND(numgenes);
			for (int j = 0; j < numgenes; j++) 
				crom.setGen(j, crom.xmin + random.nextDouble()*(crom.xmax-crom.xmin));
			return crom;
		}
		return null;
	}

	@Override
	public Cruce createCruce(String tipo, HashMap<String, Object> props) {
		if(tipo.equalsIgnoreCase("Monopunto")) return new CruceMonoPunto();
		else if(tipo.equalsIgnoreCase("Uniforme")) {
			Double probXchngGen = (Double)props.get("probXchngGen");
			CruceUniforme cruceUniforme = new CruceUniforme();
			cruceUniforme.setProbXchngGen(probXchngGen);
			return cruceUniforme;
		}
		else if(tipo.equalsIgnoreCase("Aritmetico")) {
			Double alfa			= (Double)props.get("alfa");
			CruceAritmetico cruceAritmetico = new CruceAritmetico();
			cruceAritmetico.setAlfa(alfa);
			return cruceAritmetico;
		}
		else if(tipo.equalsIgnoreCase("CruceBLXalpha")) {
			Double alfa			= (Double)props.get("blxalfa");
			CruceBLXalpha cruceBlxalpha = new CruceBLXalpha();
			cruceBlxalpha.setAlpha(alfa);
			return cruceBlxalpha;
		}
		return null;
	}

	@Override
	public Seleccion createSeleccion(String tipo, HashMap<String, Object> props) {
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
	public Mutacion createMutacion(String tipo, HashMap<String, Object> props) {
		if(tipo.equalsIgnoreCase("Basica")) {
			Double probFlipBit = (Double)props.get("probFlipBit");
			MutacionBasica mutacionBasica = new MutacionBasica();
			mutacionBasica.setProbFlipBit(probFlipBit);
			return mutacionBasica;
		}
		else if(tipo.equalsIgnoreCase("Uniforme")) return new MutacionUniforme();
		return null;
	}

	@Override
	public Fitness createFitness(String tipo, HashMap<String, Object> props) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View createView(HashMap<String,Object> props) {
		int steps = 100;
		
		Integer numVariables = (Integer) props.get("numVariables");
		
		F4Mapper f4Mapper = new F4Mapper();
		f4Mapper.setNumgenes(numVariables);
		
		surface = Surface.shape(f4Mapper, 
				new Range(0f, (float) Math.PI), 
				new Range(0f, (float) Math.PI), 
				steps, 
				new ColorMapRainbow(),
				0.5f); 
		
		ContourAxeBox cab = new ContourAxeBox(surface.getBounds());
		MapperContourPictureGenerator contour = new MapperContourPictureGenerator(
				f4Mapper, new Range(0f, (float) Math.PI), new Range(0f, (float) Math.PI));
		ColorMapper myColorMapper=new ColorMapper(
				new ColorMapRainbow(), 
				surface.getBounds().getZmin(), surface.getBounds().getZmax(), 
				new Color(1,1,1,.5f));
		cab.setContourImg( contour.getHeightMap(
				new DefaultContourColoringPolicy(myColorMapper), 400, 400, 10), 
				new Range(0f, (float) Math.PI), new Range(0f, (float) Math.PI));
		cab.getLayout().setXTickRenderer(Double::toString);
		cab.getLayout().setYTickRenderer(Double::toString);
		cab.getLayout().setZTickRenderer(Double::toString);

		chart = new AWTChart(Quality.Nicest);
		chart.add(surface);
		chart.addMouseCameraController();
		chart.getView().setAxe(cab);
		
		return ()-> (Component) chart.getCanvas();
	}
	

}
