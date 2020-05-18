package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JFrame;
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
		
		int h = getHeight();
		int treeh = (c.getProfundidad()+1)*2*diam;
		double scalefactor = ((double)h)/treeh;
		g.setFont(font);
		g.setRenderingHints(hints);
		g.setTransform(zoomAndPanListener.getCoordTransform());
		g.scale(scalefactor, scalefactor);
		int rootpos = drawTree(g, c, 0);
//		g.translate(rootpos/2, (c.getProfundidad()*diam)/2);
		g.setColor(new Color(184, 217, 248));
		int x = rootpos/2;
		int y = 0;
		g.fillOval(x, y, diam, diam);
		paintText(g, c, x, y);
	}
	
	private int drawTree(java.awt.Graphics2D g, Ctree t, int pos) {
		if(t.getNumHijos()<=0) return diam;
		
		ArrayList<Ctree> hijos = t.getHijos();
		int[][] hijosPoints = new int[hijos.size()][];
		int twidth = 0;
		int cwidth = 0;
		for (int i = 0; i < hijos.size(); i++) {
			cwidth = drawTree(g, hijos.get(i), pos+twidth);
			int x = pos+twidth+(cwidth/2);
			twidth += cwidth;
			int y = hijos.get(i).getLevel()*2*diam;
			hijosPoints[i] = new int[] {x, y};
		}

		int i = 0;
		for (int[] point : hijosPoints) {		//pintar lineas al nodo padre
			g.drawLine(diamOff + point[0], diamOff+point[1], diamOff + (pos + twidth / 2), diamOff + t.getLevel() * 2 * diam);
			g.setColor(new Color(184, 217, 248));
			g.fillOval(point[0], point[1], diam, diam);
			paintText(g, hijos.get(i++), point[0], point[1]);
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
	
	public static void main(String[] args) {
		Ctree c = new Ctree("0");
				Ctree c0 = c.addNew("1");
						Ctree c4 = c0.addNew("3");
										c4.addNew("7");
										c4.addNew("8");
						Ctree c5 = c0.addNew("4");
										c5.addNew("9");
										c5.addNew("10");
						
				Ctree c1 = c.addNew("2");
						Ctree c3 = c1.addNew("5");
										c3.addNew("11");
						Ctree c2 = c1.addNew("6");
										c2.addNew("12");
										c2.addNew("22");
				Ctree cc = c.addNew("A0");
						Ctree o = cc.addNew("O");
										Ctree ccc = o.addNew("BB");
														ccc.addNew("CC");
														ccc.addNew("DD");
		int prof = c.getProfundidad();
		int maxnodos=0;
		for (int i = 0; i < prof; i++) {
			maxnodos = Math.max(maxnodos, c.nodesAtLevel(i).size());
		}
		JPanel customComp = new TreePanel(c);

		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.add(customComp);
		jFrame.setSize(700,400);
		jFrame.setVisible(true);
	}
}