package gen;

public abstract class Cromosoma2D extends Cromosoma{ //Binario
	private double xmax;
	private double xmin;
	private double ymax;
	private double ymin;
	
	private Integer x, y;
	
	@Override
	public Object[] getGenes() {
		return new Integer[] {x, y};
	}
	
	@Override
	public Object getGen(int i) {
		if(i==0) return x;
		if(i==1) return y;
		return null;
	}

	@Override
	public void setGen(int i, Object g) {
		if(i==0) x = Integer.valueOf((Integer)g);
		if(i==1) y = Integer.valueOf((Integer)g);
	}
	
	public abstract Object getFenotipo();

	public int getLgenx() {
		return (int) Math.ceil(Math.log(1+(getXmax()-getXmin())/getTolerancia())/Math.log(2));
	}

	public int getLgeny() {
		return (int) Math.ceil(Math.log(1+(getYmax()-getYmin())/getTolerancia())/Math.log(2));
	}
	
	@Override
	public int getNumGenes() {
		return 2;
	}

	public double getXmax() {
		return xmax;
	}

	public void setXmax(double xmax) {
		this.xmax = xmax;
	}

	public double getXmin() {
		return xmin;
	}

	public void setXmin(double xmin) {
		this.xmin = xmin;
	}

	public double getYmax() {
		return ymax;
	}

	public void setYmax(double ymax) {
		this.ymax = ymax;
	}

	public double getYmin() {
		return ymin;
	}

	public void setYmin(double ymin) {
		this.ymin = ymin;
	}
	
	@Override
	public String toString() {
		double cx = getXmin() + (Integer)getGen(0) *(getXmax()-getXmin())/(Math.pow(2,getLgenx())-1);
		double cy = getYmin() + (Integer)getGen(1) *(getYmax()-getYmin())/(Math.pow(2,getLgeny())-1);
		return "x:"+cx+ " y:"+cy+" value2optimize:" + value2optimize();
	}
}
