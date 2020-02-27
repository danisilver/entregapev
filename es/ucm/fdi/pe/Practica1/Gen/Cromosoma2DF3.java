package Gen;

public class Cromosoma2DF3 extends Cromosoma2D{
	
	public Cromosoma2DF3() {
		xmax = 10;
		xmin =-10;
		ymax = 10;
		ymin =-10;
		setTolerancia(0.001d);
		lgenx = (int) Math.ceil(Math.log(1+(xmax-xmin)/getTolerancia())/Math.log(2));
		lgeny = (int) Math.ceil(Math.log(1+(ymax-ymin)/getTolerancia())/Math.log(2));
	}
	
	Integer gen1;
	Integer gen2;
	
	@Override
	public Object getGen(int i) {
		if(i==0) return gen1;
		else if (i==1) return gen2;
		else throw new IllegalArgumentException();
	}

	@Override
	public void setGen(int i, Object g) {
		if(i==0) gen1=(Integer)g;
		else if (i==1) gen2=(Integer)g;
	}

	@Override
	public Object getFenotipo() {
		double cx = xmin + x*(xmax-xmin)/(Math.pow(2,lgenx)-1);
		double cy = ymin + y*(ymax-ymin)/(Math.pow(2,lgeny)-1);
		double res=0;
		for (int i = 0; i < 5 ; i++) {
			res+=i*Math.cos(((i+1)*cx)+i)*i*Math.cos(((i+1)*cy)+i);
		}
		return res;
	}

	@Override
	public Object[] getGenes() {
		return new Object[] {x,y};
	}

	@Override
	public Cromosoma clonar() {
		return new Cromosoma2DF3();
	}

}
