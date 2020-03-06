/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facil;

import org.sf.surfaceplot.ISurfacePlotModel;

/**
 *
 * @author salagarsamy
 */
public class ExampleSurfaceModel implements ISurfacePlotModel {

	public float calculateZ(float x, float y) {
		return (float) (21.5 + x*Math.sin(4*Math.PI*x)+y*Math.sin(4*Math.PI*y));
		//return (float) (x * Math.sin(y));
	}

	public int getPlotMode() {
		return ISurfacePlotModel.PLOT_MODE_SPECTRUM;
	}

	public boolean isBoxed() {
		return true;
	}

	public boolean isMesh() {
		return true;
	}

	public boolean isScaleBox() {
		return false;
	}

	public boolean isDisplayXY() {
		return true;
	}

	public boolean isDisplayZ() {
		return true;
	}

	public boolean isDisplayGrids() {
		return true;
	}

	public int getCalcDivisions() {
		return 50;
	}

	public int getDispDivisions() {
		return 50;
	}

	public float getXMin() {
		return -3;
	}

	public float getXMax() {
		return 12;
	}

	public float getYMin() {
		return 4.1f;
	}

	public float getYMax() {
		return 5.8f;
	}

	public float getZMin() {
		return 0f;
	}

	public float getZMax() {
		return 50;
	}

	public String getXAxisLabel() {
		return "X";
	}

	public String getYAxisLabel() {
		return "Y";
	}

	public String getZAxisLabel() {
		return "Z";
	}
}
