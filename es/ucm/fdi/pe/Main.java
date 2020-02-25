package es.ucm.fdi.pe;
import static java.lang.Math.PI;
import static java.util.stream.IntStream.range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.math.plot.Plot2DPanel;

public class Main{
	@SuppressWarnings("serial")
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { System.exit(-1);}

		int tamPoblacion=100;
		int nIteraciones=100;
		int generacion=0;
		double probCruce = 0.4;
		double probMutacion = 0.05;
		double precision=0.001;
		double xmax = 12.1;
		double xmin = -3;
		double ymax = 5.8;
		double ymin = 4.1;
		int lcromx = (int) Math.ceil(Math.log(1+(xmax-xmin)/precision)/Math.log(2));
		int lcromy = (int) Math.ceil(Math.log(1+(ymax-ymin)/precision)/Math.log(2));
		
		double[] best = new double[nIteraciones];
		double[] average = new double[nIteraciones];
		double[] worst = new double[nIteraciones];
		
		Gui gui = new Gui();
		gui.setVisible(true);	
		gui.progressBar.setMaximum(nIteraciones);
		Plot2DPanel plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		gui.panel_6.add(plot);
		
		ArrayList<Punto> pob = new ArrayList<>();
		Random rand = new Random();
		range(0,tamPoblacion).forEach(e->{
			pob.add(new Punto(){{
				x=rand.nextInt((int)Math.pow(2,lcromx)-1);
				y=rand.nextInt((int)Math.pow(2,lcromy)-1);
			}});
		});

		ArrayList<Punto> npob = new ArrayList<>();
		Fitness<Punto> tf = i->{
			double cx = xmin + i.x*(xmax-xmin)/(Math.pow(2,lcromx)-1);
			double cy = ymin + i.y*(ymax-ymin)/(Math.pow(2,lcromy)-1);
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
			prob = 1f/(lcromx-1);
			pacc = 0;
			r = Math.random();
			i = 0;
			while(i<lcromx){
				if(r<=pacc) break;
				pacc+=prob;
				i++;
			}
			String h1rep = fillZeros(Integer.toBinaryString(p1.x), lcromx);
			String h2rep = fillZeros(Integer.toBinaryString(p2.x), lcromx);
			String h1 = h1rep.substring(0,i).concat(h2rep.substring(i,lcromx));
			String h2 = h2rep.substring(0,i).concat(h1rep.substring(i,lcromx));
			Integer a = Integer.parseInt(h1,2);
			Integer b = Integer.parseInt(h2,2);

			prob = 1f/(lcromy-1);
			pacc=0;
			r = Math.random();
			i = 0;
			while(i<lcromy){
				if(r<=pacc) break;
				pacc+=prob;
				i++;
			}
			h1rep = fillZeros(Integer.toBinaryString(p1.y), lcromy);
			h2rep = fillZeros(Integer.toBinaryString(p2.y), lcromy);
			h1 = h1rep.substring(0,i).concat(h2rep.substring(i,lcromy));
			h2 = h2rep.substring(0,i).concat(h1rep.substring(i,lcromy));
			Integer c = Integer.parseInt(h1,2);
			Integer d = Integer.parseInt(h2,2);

			return new Punto[]{
				new Punto(){{x=a;y=c;}},
				new Punto(){{x=b;y=d;}},
			};
		};
		Mutacion<Punto> tm = h->{//mutacion basica
			String brep = fillZeros(Integer.toBinaryString(h.x), lcromx);
			StringBuffer sb = new StringBuffer();
			for(char c:brep.toCharArray()){
				double r = Math.random();
				if(r<probMutacion) {
					if(c=='0') sb.append('1');
					else sb.append('0');
				} else {
					sb.append(c);
				}
			}
			Integer nx = Integer.parseInt(sb.toString(),2);

			brep = fillZeros(Integer.toBinaryString(h.y), lcromy);
			sb = new StringBuffer();
			for(char c:brep.toCharArray()){
				double r = Math.random();
				if(r<probMutacion) {
					if(c=='0') sb.append('1');
					else sb.append('0');
				} else {
					sb.append(c);
				}
			}
			Integer ny = Integer.parseInt(sb.toString(),2);
			return new Punto(){{x=nx;y=ny;}};
		};

		while(generacion<nIteraciones){
			for(int i=0; i<(pob.size()/2); i++){
				Punto[] sel, hijos, mutados;
				sel = seleccion(ts, pob.toArray(new Punto[]{}));
				hijos = cruce(tc, sel, probCruce);
				mutados = mutacion(tm, hijos, probMutacion);
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
				avg += f/tamPoblacion;
			};
			best[generacion]=b;
			worst[generacion]=w;
			average[generacion]=avg;
			gui.progressBar.setValue(generacion);
			generacion++;
		}
		
		SwingUtilities.invokeLater(()->{
			plot.addLinePlot("best", best);
			plot.addLinePlot("worst", worst);
			plot.addLinePlot("average", average);
		});
	}
	public static <T> T[] seleccion(Seleccion<T> tsel, T[] poblacion){
		return tsel.seleccion(poblacion);
	}
	public static <T> T[] cruce(Cruce<T> tcruce, T[] padres,double prob){
		if(Math.random()<prob) return tcruce.cruce(padres[0], padres[1]) ;
		return padres;
	}
	static <T> T[] mutacion(Mutacion<T> tmutacion, T[] hijos, double prob){
		for(int h=0; h<hijos.length; h++){
			hijos[h] = tmutacion.mutacion(hijos[h]);
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
	T[] seleccion(T[] poblacion);
}
interface Cruce<T>{
	T[] cruce(T p1, T p2);
}
interface Mutacion<T>{
	T mutacion(T p);
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