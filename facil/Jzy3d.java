package facil;

import java.awt.Component;

import javax.swing.JFrame;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.SwingChart;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;

public class Jzy3d {
	public static void main(String[] args) {

		// Define a function to plot
		Mapper mapper = new Mapper() {
			public double f(double x, double y) {
				return (21.5 + x * Math.sin(4 * Math.PI * x) + y * Math.sin(4 * Math.PI * y));
				// return 10 * Math.sin(x / 10) * Math.cos(y / 20);
			}
		};

		// Define range and precision for the function to plot
		int steps = 150;

		// Create a surface drawing that function
		Shape surface = Builder.buildOrthonormal(
				new OrthonormalGrid(new Range(3.0f, 12f), steps, new Range(4.1f, 5.8f), steps), mapper);
		surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZRange()));
		surface.setFaceDisplayed(true);
		surface.setWireframeDisplayed(false);
		surface.setWireframeColor(Color.BLACK);

		// Create a chart and add the surface
		Chart chart = new SwingChart(Quality.Advanced);
		chart.add(surface);

		chart.addMouseCameraController();
		chart.addMousePickingController(1);

		Coord3d[] points = new Coord3d[1];
		Color[] colors = new Color[1];
		points[0] = new Coord3d(11.625f, 5.726f, 39f);
		colors[0] = new Color(0f, 0f, 0f);
		Scatter scat = new Scatter(points, colors);
		scat.setWidth(10);

		chart.add(scat);
		JFrame jFrame = new JFrame();
		jFrame.getContentPane().add((Component)chart.getCanvas());
		jFrame.setVisible(true);

		//chart.open("Jzy3d Demo", 600, 600);

	}
}
