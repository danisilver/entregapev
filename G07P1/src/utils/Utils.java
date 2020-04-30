package utils;

import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import gen.Cromosoma;

public class Utils {
	
	public static Random random = new Random();
	
	public static 
	Function<Arbol,
	Consumer<Integer>> inicCompleta = a->b-> a.inicializacionCompleta(b.intValue(), 0);
	public static 
	Function<Arbol,
	Consumer<Integer>> inicCreciente = a->b-> a.inicializacionCreciente(b.intValue());
	
	public static double normalize(double value, double min, double max) {
	    return ((value - min) / (max - min));
	}
	
	public static String fillZeros(String ind, int nbits) {
		StringBuffer pad = new StringBuffer(nbits);
		nbits -= ind.length();
		for(int i=0; i<nbits; i++) pad.append('0');
		return pad.append(ind).toString();
	}
	
	public static void permutations(ArrayList<Integer> prefix,ArrayList<Integer> value, ArrayList<ArrayList<Integer>> salida) {
        if(value.size()==0) {
            salida.add(prefix);
        } else {
            for(int i=0;i<value.size();i++) {
                ArrayList<Integer> a=new ArrayList<Integer>();
                a.addAll(prefix);
                a.add(value.get(i));

                ArrayList<Integer> b=new ArrayList<Integer>();

                b.addAll(value.subList(0, i));
                b.addAll(value.subList(i+1, value.size()));

                permutations(a,b,salida);
            }
        }
    }
	
	public static  class TitleClickAdapter extends MouseAdapter {
		private JPanel panel2hide;
		public TitleClickAdapter(JPanel panel2hide) {
			this.panel2hide = panel2hide;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			hidePanel2Click(panel2hide, e);
		}
	};
	
	public static void hidePanel2Click(JPanel panel2hide, MouseEvent e) {
		if (e.getClickCount() != 2)
            return;
		JComponent component = (JComponent)e.getSource();
        Border border = component.getBorder();

        if (border instanceof TitledBorder) {
        	
        	TitledBorder titledBorder = (TitledBorder)border;
            FontMetrics fm = component.getFontMetrics( titledBorder.getTitleFont() );
            int titleWidth = fm.stringWidth(titledBorder.getTitle()) + 20;
            Rectangle bounds = new Rectangle(0, 0, titleWidth, fm.getHeight());

            if (bounds.contains(e.getPoint())) {
            	if(panel2hide.isVisible()) panel2hide.setVisible(false);
	        	else panel2hide.setVisible(true);
            }
        }
	}

	public static double[] puntajes(Cromosoma[] poblacion) {
		return Arrays.asList(poblacion)
				.stream()
				.mapToDouble(c->c.getPuntuacion())
				.toArray();
	}
	
	public static double[] puntajesAcc(Cromosoma[] poblacion) {
		return Arrays.asList(poblacion)
				.stream()
				.mapToDouble(c->c.getPuntAcc())
				.toArray();
	}
	
	public static Object[] fenotipos(Cromosoma[] poblacion) {
		return Arrays.asList(poblacion)
				.stream()
				.map(c->c.getFenotipo())
				.toArray();
	}
	
	public static double covarianza(double[][] matrix) {
		double mediaX = 0d;
		double mediaY = 0d;
		
		for (int i = 0; i < matrix.length; i++) {
			mediaX += matrix[i][0];
			mediaY += matrix[i][1];
		}
		mediaX = mediaX / matrix.length;
		mediaY = mediaY / matrix.length;
		
		double cov=0d;
		for (int i = 0; i < matrix.length; i++) {
			cov += (matrix[i][0]-mediaX)*(matrix[i][1]-mediaY);
		}
		
		return cov / matrix.length;
	}
	
	public static double varianza(double[] matrix) {
		double media = 0d;
		for (int i = 0; i < matrix.length; i++) {
			media += matrix[i];
		}
		media = media / matrix.length;
		
		double var = 0d;
		for (int i = 0; i < matrix.length; i++) {
			double xi_minus_E = matrix[i]-media;
			var += xi_minus_E * xi_minus_E;
		}
		
		return var / matrix.length;
	}
	
}
