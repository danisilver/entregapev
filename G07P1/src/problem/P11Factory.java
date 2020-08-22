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

import gen.Cromosoma;
import gen.Cromosoma2D;
import gen.CromosomaRealND;
import mapper.F11Mapper;
import utils.Utils;
import view.View;

public class P11Factory extends P4Factory {

	private Shape surface;
	private Chart chart;
	private Random random = Utils.random;
	private static final double XMAX = 10;
	private static final double XMIN = -10;
	private static final double YMAX = 3;
	private static final double YMIN = -3;
	
	private class P11CromBin extends Cromosoma2D{
		public P11CromBin() { setXmin(XMIN); setXmax(XMAX); setYmin(YMIN); setYmax(YMAX); }
		@Override
		public Object getFenotipo() {
			double cx = getXmin() + (Integer)getGen(0)*(getXmax()-getXmin())/(Math.pow(2,getLgenx())-1);
			double cy = getYmin() + (Integer)getGen(1)*(getYmax()-getYmin())/(Math.pow(2,getLgeny())-1);
			return new F11Mapper().f(cx,cy);
		}
		@Override public double value2optimize() { return (Double)getFenotipo(); }
		@Override
		public Cromosoma clonar() {
			P11CromBin crom = new P11CromBin();
			crom.setTolerancia(getTolerancia());
			crom.setGen(0, getGen(0));
			crom.setGen(1, getGen(1));
			return crom;
		}
	}
	
	private class P11CromReal extends CromosomaRealND{
		public P11CromReal() { super(2); }
		@Override
		public Object getFenotipo() {
			return new F11Mapper().f((Double)getGen(0), (Double)getGen(1));
		}
		@Override
		public Cromosoma clonar() {
			P11CromReal crom = new P11CromReal();
			for (int i = 0; i < 2; i++) { crom.setGen(i, getGen(i)); }
			return crom;
		}
	}
	
	@Override
	public Cromosoma createCromosoma(String tipo, HashMap<String, Object> props) {
		if(tipo.equalsIgnoreCase("Binario")) {
			Double tolerancia 	= (Double) props.get("tolerancia");
			P11CromBin crom = new P11CromBin();
			crom.setTolerancia(tolerancia);
			crom.setGen(0, random.nextInt((int)Math.pow(2, crom.getGenLen(0))-1));
			crom.setGen(1, random.nextInt((int)Math.pow(2, crom.getGenLen(1))-1));
			return crom;
		} else if(tipo.equalsIgnoreCase("Real")) {
			P11CromReal crom = new P11CromReal();
			crom.setGen(0, XMIN + random.nextDouble()*(XMAX-XMIN));
			crom.setGen(1, YMIN + random.nextDouble()*(YMAX-YMIN));
			return crom;
		}
		return null;
	}
	
	@Override
	public View createView(HashMap<String, Object> props) {
		int steps = 100;
		
		F11Mapper f11Mapper = new F11Mapper();
		
		surface = Surface.shape(f11Mapper, 
				new Range(-15f, -5f), 
				new Range(-3f, 3f), 
				steps, 
				new ColorMapRainbow(),
				0.5f); 
		
		ContourAxeBox cab = new ContourAxeBox(surface.getBounds());
		MapperContourPictureGenerator contour = new MapperContourPictureGenerator(
				f11Mapper, new Range(-15f, -5f), new Range(-3f, 3f));
		ColorMapper myColorMapper=new ColorMapper(
				new ColorMapRainbow(), 
				surface.getBounds().getZmin(), surface.getBounds().getZmax(), 
				new Color(1,1,1,.5f));
		cab.setContourImg( contour.getHeightMap(
				new DefaultContourColoringPolicy(myColorMapper), 400, 400, 10), 
				new Range(-15f, -5f), new Range(-3f, 3f));
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
