package es.ucm.fdi.pe;
import java.util.*;

public class Main{
	public static void main(String[] args){

		int nIteraciones=100;
		int generacion=0;
		double probCruce = 0.6;
		double probMutacion = 0.01;
		double precision=0.001;
		double xmax=10;
		double xmin=0;
		double x = 1+(xmax-xmin)/precision;
		int nbits = (int)(Math.log(x)/Math.log(2));

		Punto[] pob = new Punto[]{
			new Punto(){{x=1;y=2;}},
			new Punto(){{x=2;y=2;}},
			new Punto(){{x=3;y=2;}},
			new Punto(){{x=1;y=3;}}
		};

		ArrayList<Punto> npob = new ArrayList<>(){};
		Fitness<Punto> tf = i->{
			double pi = Math.PI;
			double cx = xmin + i.x*(xmax-xmin)/(Math.pow(2,nbits)-1);
			double cy = xmin + i.y*(xmax-xmin)/(Math.pow(2,nbits)-1);
			double res = 21.5 + cx*Math.sin(4*pi*cx)+cy*Math.sin(20*pi*cy);
			return (int)(-res);
		};
		Seleccion<Punto> ts = p->{//Seleccion por ruleta
			Punto[] s = new Punto[2];
			double r1 = Math.random();
			double r2 = Math.random();
			int pacc = 0;
			int i = 0;
			while (i<p.length){
				if(pacc>=r1) s[0]=p[i];
				if(pacc>=r2) s[1]=p[i];
				pacc+=tf.fitness(p[i]);
				i++;
			}
			return p;
		};
		Cruce<Punto> tc = (p1, p2)->{//monopunto
			double prob = 1/nbits;
			double pacc = 0;
			double r = Math.random();
			int i = 0;
			while(i<nbits){
				if(r<=pacc) break;
				pacc+=prob;
				i++;
			}
			String h1rep = Integer.toBinaryString(p1.x);
			String h2rep = Integer.toBinaryString(p2.x);
			String h1 = h1rep.substring(0,i).concat(h2rep.substring(i,nbits));
			String h2 = h2rep.substring(0,i).concat(h1rep.substring(i,nbits));
			Integer a = Integer.parseInt(h1,2);
			Integer b = Integer.parseInt(h2,2);

			r = Math.random();
			i = 0;
			while(i<nbits){
				if(r<=pacc) break;
				pacc+=prob;
				i++;
			}
			h1rep = Integer.toBinaryString(p1.y);
			h2rep = Integer.toBinaryString(p2.y);
			h1 = h1rep.substring(0,i).concat(h2rep.substring(i,h2.length()));
			h2 = h2rep.substring(0,i).concat(h1rep.substring(i,h1.length()));
			Integer c = Integer.parseInt(h1,2);
			Integer d = Integer.parseInt(h2,2);

			return new Punto[]{
				new Punto(){{x=a;y=c;}},
				new Punto(){{x=b;y=d;}},
			};
		};
		Mutacion<Punto> tm = h->{//mutacion basica
			String brep = Integer.toBinaryString(h.x);
			StringBuffer sb = new StringBuffer();
			for(char c:brep.toCharArray()){
				double r = Math.random();
				if(r<probMutacion) {
					if(c=='0') sb.append('0');
					else sb.append('1');
				}
			}
			Integer nx = Integer.parseInt(sb.toString());

			brep = Integer.toBinaryString(h.y);
			sb = new StringBuffer();
			for(char c:brep.toCharArray()){
				double r = Math.random();
				if(r<probMutacion) {
					if(c=='0') sb.append('0');
					else sb.append('1');
				}
			}
			Integer ny = Integer.parseInt(sb.toString());
			return new Punto(){{x=nx;y=ny;}};
		};

		while(generacion<nIteraciones){
			for(int i=0; i<(pob.length/2); i++){
				Punto[] sel, hijos, mutados;
				sel = seleccion(ts, pob);
				hijos = cruce(tc, sel, probMutacion);
				mutados = mutacion(tm, hijos, probMutacion);
				npob.add(mutados[0]);
			}
			pob=npob.toArray(pob);
			generacion++;
		}
		System.out.println(Arrays.toString(pob));

	}
	public static <T> T[] seleccion(Seleccion<T> tsel, T[] poblacion){
		return tsel.execute(poblacion);
	}
	public static <T> T[] cruce(Cruce<T> tcruce, T[] padres,double prob){
		return tcruce.execute(padres[0], padres[1]);
	}
	static <T> T[] mutacion(Mutacion<T> tmutacion, T[] hijos, double prob){
		for(int h=0; h<hijos.length; h++){
			hijos[h] = tmutacion.execute(hijos[h]);
		}
		return Arrays.copyOf(hijos, hijos.length+1);
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
	int fitness(G genotipe);
}

class Punto{
	public Integer x,y;
	public String toString(){
		return "x:"+x+"y:"+y;
	}
}
//System.out.println(Arrays.toString(s));
//tamPoblacion
//seleccion() 	ruleta, estocastico, torneo
//cruce() monopunto, uniforme
//mutacion() basica
//ejecucion paso a paso, ejecucion total
/*
		Cruce<Integer> tc2 = (p1, p2)->{//uniforme
			return new Integer[]{p1,p2};
		};
*/
