package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

public class TreePanel extends JPanel {
	private final Ctree c;
	private static final long serialVersionUID = 172683191819778352L;
	int diam=50;
	int diamOff = diam/2;
	private RenderingHints hints;
	private ZoomAndPanListener zoomAndPanListener;
	private Font font;

	public TreePanel(Ctree c) {
		this.c = c;
		this.hints = new RenderingHints(null);
		this.hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		this.hints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		this.zoomAndPanListener = new ZoomAndPanListener(this);
		this.addMouseListener(zoomAndPanListener);
		this.addMouseMotionListener(zoomAndPanListener);
		this.addMouseWheelListener(zoomAndPanListener);
		this.font = new Font("Segoe UI", Font.TRUETYPE_FONT, 15);
	}

	@Override
	protected void paintComponent(java.awt.Graphics gg) {
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D)gg;
		g.setFont(font);
		g.setRenderingHints(hints);
		g.setTransform(zoomAndPanListener.getCoordTransform());
		int rootpos = drawTree(g, c, 0);
		g.setColor(new Color(184, 217, 248));
		int x = rootpos/2;
		int y = 0;
		g.fillOval(x, y, diam, diam);
		paintText(g, c, x, y);
	}
	
	private int drawTree(java.awt.Graphics gg, Ctree t, int pos) {
		Graphics2D g = (Graphics2D)gg;
		if(t.getNumHijos()<=0) return 2*diam;
		
		ArrayList<Ctree> hijos = t.getHijos();
		ArrayList<int[]> rootpoints = new ArrayList<>();
		int twidth = 0;
		int cwidth = 0;
		for (int i = 0; i < hijos.size(); i++) {
			cwidth = drawTree(g, hijos.get(i), pos+cwidth);
			int x = pos+twidth+(cwidth/2);
			twidth += cwidth;
			int y = hijos.get(i).getLevel()*2*diam;
			if(t!=c) {
				if(t.getNumHijos()==1)//straight
					g.drawLine(diamOff+x,diamOff+y, diamOff+(pos+cwidth)-diam, diamOff+t.getLevel()*2*diam);
				else
					g.drawLine(diamOff+x,diamOff+y, diamOff+(pos+cwidth), diamOff+t.getLevel()*2*diam);
			} else {
				rootpoints.add(new int[] {x, y});
			}
			g.setColor(new Color(184, 217, 248));
			g.fillOval(x, y, diam, diam);
			paintText(gg, hijos.get(i), x, y);
		}
		
		if(t==c) {
			for (int i = 0; i < rootpoints.size(); i++) {
				int[] p = rootpoints.get(i);
				g.drawLine(diamOff+p[0],p[1], diamOff+(twidth/2), diamOff+t.getLevel()*2*diam);
			}
		}
		return twidth;
	}

	private void paintText(Graphics gg, Ctree hijo, int x, int y) {
		Graphics2D g = (Graphics2D)gg;
		int stringLen = (int) g.getFontMetrics()
				.getStringBounds(hijo.toString(), gg)
				.getWidth();
		int start = diam / 2 - stringLen / 2;
		g.setColor(new Color(67, 67, 72));
		g.drawString(hijo.toString(), start + x, y + diam / 2 + 5);
	}
	
//	public static void main(String[] args) {
//	Ctree c = new Ctree("0");
//		Ctree c0 = c.addNew("1");
//			Ctree c4 = c0.addNew("3");
//							c4.addNew("7");
//							c4.addNew("8");
//			Ctree c5 = c0.addNew("4");
//							c5.addNew("9");
//							c5.addNew("10");
//		Ctree c1 = c.addNew("2");
//			Ctree c3 = c1.addNew("5");
//							c3.addNew("11");
//			Ctree c2 = c1.addNew("6");
//							c2.addNew("12");
//	int prof = c.getProfundidad();
//	int maxnodos=0;
//	for (int i = 0; i < prof; i++) {
//		maxnodos = Math.max(maxnodos, c.nodesAtLevel(i).size());
//	}
//	JPanel customComp = new TreePanel(c);
//	
//	JFrame jFrame = new JFrame();
//	jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	jFrame.add(customComp);
//	jFrame.setSize(700,400);
//	jFrame.setVisible(true);
//}
}