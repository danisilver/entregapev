package utils;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public void setLevel(int level) 	{ this.level = level; }
	public int getNumHijos() 		{ return hijos.size(); }
	public Object getValue() 		{ return value; }
	public void setValue(Object val) { this.value = val; }
	public ArrayList<Ctree> getHijos() { return hijos; }
	public void setHijos(ArrayList<Ctree> hijos) { this.hijos = hijos; }
	@Override
	public String toString() {
		return value.toString();
	}

}
