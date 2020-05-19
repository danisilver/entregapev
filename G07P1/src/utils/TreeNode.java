package utils;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TreeNode {
	private int level;
	private Object value;
	private ArrayList<TreeNode> hijos;
	
	public TreeNode(Object value) {
		this.value = value;
		this.level = 0;
		this.hijos = new ArrayList<TreeNode>();
	}
	public TreeNode(Object value, Object... hijos) {
		this.value = value;
		this.level = 0;
		for(Object h : hijos) addNew(h);
	}
	public TreeNode(Arbol a) {
		this.hijos = new ArrayList<TreeNode>();
		this.value = a.getValor();
		this.level = a.getProfundidad();
		for(Arbol b : a.getHijos()) {
			this.add(new TreeNode(b));
		}
	}
	
	public void add(TreeNode st) {
		hijos.add(st);
		st.setLevel(getLevel()+1);
	}
	
	public TreeNode addNew(Object val) {
		TreeNode nt = new TreeNode(val);
		hijos.add(nt);
		nt.setLevel(level+1);
		return nt;
	}
	
	public TreeNode addAppend(Object val) {
		TreeNode nt = new TreeNode(val);
		hijos.add(nt);
		nt.setLevel(level+1);
		return this;
	}
	
	public void replaceWith(TreeNode t) {
		t.setLevel(this.level);
		this.value = t.value;
		this.hijos = t.hijos;
	}
	
	@Override
	public TreeNode clone() {
		TreeNode clone = new TreeNode(this.value);
		clone.level = this.level;
		for (int i = 0; i < this.hijos.size(); i++) {
			clone.add(this.hijos.get(i).clone());
		}
		return clone;
	}
	
	public int indexOf(TreeNode t) {
		return hijos.indexOf(t);
	}
	
	public int getDepth() {
		return max(level, hijos.stream()
							   .mapToInt(h->h.getDepth())
							   .max().orElse(level));
	}
	
	public int getNumNodes() {
		return 1 + hijos.stream()
						.mapToInt(h->h.getNumNodes())
						.sum();
	}
	
	public List<TreeNode> getInnerNodes(){
		List<TreeNode> innerNodes = new ArrayList<>();
		List<TreeNode> collect = this.hijos.stream()
				.filter(h->h.getNumHijos()>0)
				.collect(Collectors.toList());
		innerNodes.addAll(collect);
		for(TreeNode h:collect)
			innerNodes.addAll(h.getInnerNodes());
		return innerNodes;
	}
	
	public List<TreeNode> getLeafNodes(){
		List<TreeNode> innerNodes = new ArrayList<>();
		List<TreeNode> collect = this.hijos.stream()
				.filter(h->h.getNumHijos()==0)
				.collect(Collectors.toList());
		innerNodes.addAll(collect);
		for(TreeNode h:collect)
			innerNodes.addAll(h.getLeafNodes());
		return innerNodes;
	}
	
	public TreeNode at(int index) {
		ArrayList<TreeNode> array = toArray();
		if(array.size()<=index||index<0) return null;
		return array.get(index);
	}
	
	public ArrayList<TreeNode> toArray() {
		ArrayList<TreeNode> array = new ArrayList<>();
		array.add(this);
		array.addAll(toArrayAux());
		return array;
	}
	
	public ArrayList<String> toArrayString() {
		ArrayList<String> array = new ArrayList<String>();
		toArrayAux(array, this);
		return array;
	}
	
	private void toArrayAux(ArrayList<String> array, TreeNode a) {
		array.add(a.toString());
		for (int i = 0; i < a.getHijos().size(); i++) {
			toArrayAux(array, a.getHijos().get(i));
		}
	}
	
	public ArrayList<TreeNode> toArrayAux() {
		ArrayList<TreeNode> array = new ArrayList<>();
		array.addAll(hijos);
		for (int i = 0; i < this.hijos.size(); i++) {
			array.addAll(this.hijos.get(i).toArrayAux());
		}
		return array;
	}
	
	public List<TreeNode> nodesAtLevel(int level){
		if(level==this.level) return Arrays.asList(this);
		ArrayList<TreeNode> nodes = new ArrayList<>();
		for(TreeNode h: hijos) {
			nodes.addAll(h.nodesAtLevel(level));
		}
		return nodes;
	}
	
	public void setLevel(int level) { 
		this.level = level;
		for(TreeNode h:hijos)
			h.setLevel(this.level+1);
	}
	
	public String toStringRecursive() {
		if(this.hijos.size()==0)
			return this.value.toString();
		StringBuilder sb = new StringBuilder("("+this.value.toString());
		for(TreeNode h:hijos) {
			sb.append(" ");
			sb.append(h.toString());
		}
		sb.append(")");
		return sb.toString();
	}
	
	public int getLevel() 			{ return level; }
	public int getNumHijos() 		{ return hijos.size(); }
	public Object getValue() 		{ return value; }
	public void setValue(Object val) { this.value = val; }
	public ArrayList<TreeNode> getHijos() { return hijos; }
	public void setHijos(ArrayList<TreeNode> hijos) { this.hijos = hijos; }
	@Override
	public String toString() {
		return this.value.toString();
	}
	
	

}
