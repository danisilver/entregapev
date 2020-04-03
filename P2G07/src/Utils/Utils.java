package Utils;

import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Utils {
	
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
}
