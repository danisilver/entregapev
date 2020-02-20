package es.ucm.fdi.pe;
import java.util.*;

public class Main{
	public static void main(String[] args){

		int nIteraciones=100;
		int generacion=0;
		double probCruce = 0.6;
		double probMutacion = 0.01;
		double precision=0.001;
		double xmax=0;
		double xmin=10;
		double x = 1+(xmax-xmin)/precision;
		int nbits = (int)(Math.log(x)/Math.log(2));

		Punto[] ptos = new Punto[]{
			new Punto(){{x=1;y=2;}},
			new Punto(){{x=1;y=2;}}
		};

		Integer[] pob = new Integer[]{1,2};
		ArrayList<Integer> npob = new ArrayList<>(){};
		Fitness<Integer> tf = i->{
			double g = xmin + i*(xmax-xmin)/(Math.pow(2,nbits)-1);
			return (int)(1 - (g*g));
		};
		Seleccion<Integer> ts = p->{//Seleccion por ruleta
			Integer[] s = new Integer[2];
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
		Cruce<Integer> tc = (p1, p2)->{//monopunto
			double prob = 1/nbits;
			double pacc = 0;
			double r = Math.random();
			int i = 0;
			while(i<nbits){
				if(r<=pacc) break;
				pacc+=prob;
				i++;
			}
			String h1rep = Integer.toBinaryString(p1);
			String h2rep = Integer.toBinaryString(p2);
			String h1 = h1rep.substring(0,i).concat(h2rep.substring(i,nbits));
			String h2 = h2rep.substring(0,i).concat(h1rep.substring(i,nbits));
			Integer a = Integer.parseInt(h1,2);
			Integer b = Integer.parseInt(h2,2);
			return new Integer[]{a,b};
		};
		Mutacion<Integer> tm = h->{//mutacion basica
			String brep = Integer.toBinaryString(h);
			StringBuffer sb = new StringBuffer();
			for(char c:brep.toCharArray()){
				double r = Math.random();
				if(r<probMutacion) {
					if(c=='0') sb.append('0');
					else sb.append('1');
				}
			}
			return Integer.parseInt(sb.toString());
		};
		while(generacion<nIteraciones){
			for(int i=0; i<(pob.length/2); i++){
				Integer[] sel, hijos, mutados;
				sel = seleccion(ts, pob);
				hijos = cruce(tc, sel, probMutacion);
				mutados = mutacion(tm, hijos, probMutacion);
				npob.add(mutados[0]);
			}
			pob=npob.toArray(pob);
			generacion++;
		}
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

class Punto{public Integer x,y;}
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
