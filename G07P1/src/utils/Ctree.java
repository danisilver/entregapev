package utils;

import static java.lang.Math.max;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ctree {
	private int level;
	private Object value;
	private ArrayList<Ctree> hijos;
	
	public Ctree(Object value) {
		this.value = value;
		this.level = 0;
		this.hijos = new ArrayList<Ctree>();
	}
	public Ctree(Object value, Object... hijos) {
		this.value = value;
		this.level = 0;
		for(Object h : hijos) addNew(h);
	}
	
	public void add(Ctree st) {
		hijos.add(st);
		st.setLevel(getLevel()+1);
	}
	
	public Ctree addNew(Object val) {
		Ctree nt = new Ctree(val);
		hijos.add(nt);
		nt.setLevel(level+1);
		return nt;
	}
	
	public Ctree addAppend(Object val) {
		Ctree nt = new Ctree(val);
		hijos.add(nt);
		nt.setLevel(level+1);
		return this;
	}
	
	public int indexOf(Ctree t) {
		return hijos.indexOf(t);
	}
	
	public int getProfundidad() {
		return max(level, hijos.stream()
							   .mapToInt(h->h.getProfundidad())
							   .max().orElse(level));
	}
	
	public int getNumNodos() {
		return 1 + hijos.stream()
						.mapToInt(h->h.getNumNodos())
						.sum();
	}
	
	public Ctree at(int index) {
		ArrayList<Ctree> array = toArray();
		if(array.size()<=index||index<0) return null;
		return array.get(index);
	}
	
	public ArrayList<Ctree> toArray() {
		ArrayList<Ctree> array = new ArrayList<>();
		array.add(this);
		array.addAll(toArrayAux());
		return array;
	}
	
	public ArrayList<Ctree> toArrayAux() {
		ArrayList<Ctree> array = new ArrayList<>();
		array.addAll(hijos);
		for (int i = 0; i < this.hijos.size(); i++) {
			array.addAll(this.hijos.get(i).toArrayAux());
		}
		return array;
	}
	
	public List<Ctree> nodesAtLevel(int level){
		if(level==this.level) return Arrays.asList(this);
		ArrayList<Ctree> nodes = new ArrayList<>();
		for(Ctree h: hijos) {
			nodes.addAll(h.nodesAtLevel(level));
		}
		return nodes;
	}
	
	public int getLevel() 			{ return level; }
	public void setLevel(int lvl) 	{ this.level = lvl; }
	public int getNumHijos() 		{ return hijos.size(); }
	public Object getValue() 		{ return value; }
	public void setValue(Object val) { this.value = val; }
	public ArrayList<Ctree> getHijos() { return hijos; }
	public void setHijos(ArrayList<Ctree> hijos) { this.hijos = hijos; }
	@Override
	public String toString() {
		return value.toString();
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
								c3.addNew("12");
				Ctree c2 = c1.addNew("6");
								c2.addNew("13");
								c2.addNew("14");
		System.out.println(c.toArray());
		int prof = c.getProfundidad();
		int maxnodos=0;
		for (int i = 0; i < prof; i++) {
			maxnodos = Math.max(maxnodos, c.nodesAtLevel(i).size());
		}
		int diam=50;
		JPanel customComp = new JPanel() {
			private static final long serialVersionUID = 1940978896828045002L;
			public void paint(java.awt.Graphics gg) {
				Graphics2D g = (Graphics2D)gg;
				int x = (c.getNumNodos()*diam)/2;
				g.setColor(new Color(184, 217, 248));
				int y = 0;
				g.fillOval(x, y, diam, diam);
				g.setStroke(new BasicStroke(2.0f));
				g.setColor(new Color(124, 181, 236));
				g.drawOval(x, y, diam, diam);
				g.setColor(new Color(67, 67, 72));
				int stringLen = (int) g.getFontMetrics()
						.getStringBounds(c.toString(), g)
						.getWidth();
				int start = diam / 2 - stringLen / 2;
				g.drawString(c.toString(), start +  x, y + diam / 2 + 5);
				drawTree(g, c, x);
			};
			private int drawTree(java.awt.Graphics gg, Ctree t, int pos) {
				Graphics2D g = (Graphics2D)gg;
				if(t.getNumHijos()<=0) { return pos; }
				ArrayList<Ctree> hijos = t.getHijos();
				int level = t.getLevel();
				int childwidth = (level+1)*diam;
				int depthWidth = hijos.size()*childwidth;
				int mitad = depthWidth/2;
				int posStart = pos-mitad;
				int x = posStart;
				int y = 0;
				for (int i = 0; i < t.getNumHijos(); i++) {
					y = hijos.get(i).getLevel()*2*diam;
					
					g.setColor(new Color(184, 217, 248));
					g.fillOval(x, y, diam, diam);
					g.setStroke(new BasicStroke(2.0f));
					g.setColor(new Color(124, 181, 236));
					g.drawOval(x, y, diam, diam);
					g.setColor(new Color(67, 67, 72));
					int stringLen = (int) g.getFontMetrics().getStringBounds(hijos.get(i).toString(), g).getWidth();
					int start = diam / 2 - stringLen / 2;
					g.drawString(hijos.get(i).toString(), start +  x, y + diam / 2 + 5);
					drawTree(g, hijos.get(i), x);
					if(hijos.get(i).getNumHijos()<=0) {
						x=pos+i*childwidth;					
					}
					else x += 2*((prof-t.getLevel())*diam);
				}
				return 0;
			}
		};
		
		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.add(customComp);
		jFrame.setSize(1300,700);
		jFrame.setVisible(true);
	}
}
