package Main;
import static java.lang.Math.PI;
import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.math.plot.Plot2DPanel;
import Parameters.Parameters;
public class Main{
	@SuppressWarnings("serial")
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { System.exit(-1);}
		
		Gui gui = new Gui();
		gui.setVisible(true);	
		gui.progressBar.setMaximum(Parameters.nIteraciones);
		Plot2DPanel plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		gui.panel_6.add(plot);
		
		ArrayList<Punto> pob = new ArrayList<>();
		Random rand = new Random();
		range(0,Parameters.tamPoblacion).forEach(e->{
			pob.add(new Punto(){{
				x=rand.nextInt((int)Math.pow(2,Parameters.lcromx)-1);
				y=rand.nextInt((int)Math.pow(2,Parameters.lcromy)-1);
			}});
		});

		ArrayList<Punto> npob = new ArrayList<>();
		Fitness<Punto> tf = i->{
			double cx = Parameters.xmin + i.x*(Parameters.xmax-Parameters.xmin)/(Math.pow(2,Parameters.lcromx)-1);
			double cy = Parameters.ymin + i.y*(Parameters.ymax-Parameters.ymin)/(Math.pow(2,Parameters.lcromy)-1);
			double res = 21.5 + cx*Math.sin(4*PI*cx)+cy*Math.sin(20*PI*cy);
			return res;
		};
		Seleccion<Punto> ts = p->{//Seleccion por ruleta
			Punto[] s = new Punto[2];
			double r1 = Math.random();
			double r2 = Math.random();
			double pacc = 0;
			int i = 0;
			double probMin = tf.fitness(p[0]);
			double probMax = probMin;
			double fitnessTotal = 0;
			while(i<p.length) {
				double fi = tf.fitness(p[i]);
				if(fi<probMin)probMin=fi;
				if(fi>probMax)probMax=fi;
				i++;
			}
			double[] probs = new double[p.length];
			i=0;
			while(i<p.length) {
				probs[i]=normalize(tf.fitness(p[i]), probMin, probMax);
				fitnessTotal+=probs[i];
				i++;
			}
			i=0;
			while (i<p.length){
				if(pacc<=r1) {
					s[0]=p[i];
				}
				if(pacc<=r2) {
					s[1]=p[i];
				}
				double probi = probs[i];
				pacc+= probi/fitnessTotal;
				i++;
			}
			return p;
		};
		Cruce<Punto> tc = (p1, p2)->{//monopunto
			double prob, pacc, r;
			int i;
			prob = 1f/(Parameters.lcromx-1);
			pacc = 0;
			r = Math.random();
			i = 0;
			while(i<Parameters.lcromx){
				if(r<=pacc) break;
				pacc+=prob;
				i++;
			}
			String h1rep = fillZeros(Integer.toBinaryString(p1.x), Parameters.lcromx);
			String h2rep = fillZeros(Integer.toBinaryString(p2.x), Parameters.lcromx);
			String h1 = h1rep.substring(0,i).concat(h2rep.substring(i,Parameters.lcromx));
			String h2 = h2rep.substring(0,i).concat(h1rep.substring(i,Parameters.lcromx));
			Integer a = Integer.parseInt(h1,2);
			Integer b = Integer.parseInt(h2,2);

			prob = 1f/(Parameters.lcromy-1);
			pacc=0;
			r = Math.random();
			i = 0;
			while(i<Parameters.lcromy){
				if(r<=pacc) break;
				pacc+=prob;
				i++;
			}
			h1rep = fillZeros(Integer.toBinaryString(p1.y), Parameters.lcromy);
			h2rep = fillZeros(Integer.toBinaryString(p2.y), Parameters.lcromy);
			h1 = h1rep.substring(0,i).concat(h2rep.substring(i,Parameters.lcromy));
			h2 = h2rep.substring(0,i).concat(h1rep.substring(i,Parameters.lcromy));
			Integer c = Integer.parseInt(h1,2);
			Integer d = Integer.parseInt(h2,2);

			return new Punto[]{
				new Punto(){{x=a;y=c;}},
				new Punto(){{x=b;y=d;}},
			};
		};
		Mutacion<Punto> tm = h->{//mutacion basica
			String brep = fillZeros(Integer.toBinaryString(h.x), Parameters.lcromx);
			StringBuffer sb = new StringBuffer();
			for(char c:brep.toCharArray()){
				double r = Math.random();
				if(r<Parameters.probMutacion) {
					if(c=='0') sb.append('1');
					else sb.append('0');
				} else {
					sb.append(c);
				}
			}
			Integer nx = Integer.parseInt(sb.toString(),2);

			brep = fillZeros(Integer.toBinaryString(h.y), Parameters.lcromy);
			sb = new StringBuffer();
			for(char c:brep.toCharArray()){
				double r = Math.random();
				if(r<Parameters.probMutacion) {
					if(c=='0') sb.append('1');
					else sb.append('0');
				} else {
					sb.append(c);
				}
			}
			Integer ny = Integer.parseInt(sb.toString(),2);
			return new Punto(){{x=nx;y=ny;}};
		};

		while(Parameters.generacion<Parameters.nIteraciones){
			for(int i=0; i<(pob.size()/2); i++){
				Punto[] sel, hijos, mutados;
				sel = seleccion(ts, pob.toArray(new Punto[]{}));
				hijos = cruce(tc, sel, Parameters.probCruce);
				mutados = mutacion(tm, hijos, Parameters.probMutacion);
				npob.add(mutados[0]);
				npob.add(mutados[1]);
			}
			pob.clear();
			pob.addAll(npob);
			npob.clear();
			
			Double w, avg, b;
			w=b=tf.fitness(pob.get(0));
			avg=0d;
			for(Punto p:pob) {
				double f = tf.fitness(p);
				if(f<w) w = f;
				if(f>b) b = f;
				avg += f/Parameters.tamPoblacion;
			};
			Parameters.best[Parameters.generacion]=b;
			Parameters.worst[Parameters.generacion]=w;
			Parameters.average[Parameters.generacion]=avg;
			final int g = Parameters.generacion;
			gui.progressBar.setValue(Parameters.generacion);
			Parameters.generacion++;
		}
		
		SwingUtilities.invokeLater(()->{
			plot.addLinePlot("best", Parameters.best);
			plot.addLinePlot("worst", Parameters.worst);
			plot.addLinePlot("average", Parameters.average);
		});
	}
	public static <T> T[] seleccion(Seleccion<T> tsel, T[] poblacion){
		return tsel.execute(poblacion);
	}
	public static <T> T[] cruce(Cruce<T> tcruce, T[] padres,double prob){
		if(Math.random()<prob) return tcruce.execute(padres[0], padres[1]) ;
		return padres;
	}
	static <T> T[] mutacion(Mutacion<T> tmutacion, T[] hijos, double prob){
		for(int h=0; h<hijos.length; h++){
			hijos[h] = tmutacion.execute(hijos[h]);
		}
		return Arrays.copyOf(hijos, hijos.length);
	}
	static String fillZeros(String ind, int nbits) {
		StringBuffer pad = new StringBuffer(nbits);
		nbits -= ind.length();
		for(int i=0; i<nbits; i++) pad.append('0');
		return pad.append(ind).toString();
	}
	static double normalize(double value, double min, double max) {
	    return 1 - ((value - min) / (max - min));
	}
}

interface Seleccion<T>{
	T[] execute(T[] poblacion);
}
interface Cruce<T>{
	T[] execute(T p1, T p2);
}
interface Mutacion<T>{
	T execute(T p);
}
interface Genotipo<G,F>{
	F fenotipo();
}
interface Fenotipo<G,F>{
	G genotipo();
}
interface Fitness<G>{
	double fitness(G genotipe);
}

class Punto{
	public Integer x,y;
	public String toString(){
		return "x:"+x+", y:"+y;
	}
}

//double cx = xmin + p.x*(xmax-xmin)/(Math.pow(2,lcromx)-1);
//double cy = ymin + p.y*(ymax-ymin)/(Math.pow(2,lcromy)-1);