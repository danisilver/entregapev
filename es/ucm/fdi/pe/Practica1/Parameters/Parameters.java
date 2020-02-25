package Parameters;

public final class Parameters {
	public static int tamPoblacion=100;
	public static int nIteraciones=100;
	public static int generacion=0;
	public static double probCruce = 0.4;
	public static double probMutacion = 0.05;
	public static double precision=0.001;
	public static double xmax = 12.1;
	public static double xmin = -3;
	public static double ymax = 5.8;
	public static double ymin = 4.1;
	public static int lcromx = (int) Math.ceil(Math.log(1+(xmax-xmin)/precision)/Math.log(2));
	public static int lcromy = (int) Math.ceil(Math.log(1+(ymax-ymin)/precision)/Math.log(2));
	
	public static double[] best = new double[nIteraciones];
	public static double[] average = new double[nIteraciones];
	public static double[] worst = new double[nIteraciones];	
}
