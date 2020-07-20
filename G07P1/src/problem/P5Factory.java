package problem;

import java.util.*;

import javax.swing.*;

import core.cruce.*;
import core.fitness.Fitness;
import core.mutacion.*;
import core.selection.*;
import gen.*;
import utils.Utils;
import view.View;

public class P5Factory implements ProblemFactory{

	private Random random = Utils.random;
	
	@Override
	public Cromosoma createCromosoma(String tipo, HashMap<String, Object> props) {
		if(tipo.equalsIgnoreCase("permutacion")) {
			Integer ngenes = (Integer)props.get("tamMatrixFichero");
			int[][] distancias = (int[][]) props.get("distancias");
			int[][] flujo = (int[][]) props.get("flujo");
			CromosomaNDP5 cromosomaNDP5 = new CromosomaNDP5(ngenes, distancias, flujo);
			HashSet<Integer> set = new HashSet<>();
			while(set.size()<ngenes) {
				Integer gen = 1 + random.nextInt(ngenes);
				if(!set.contains(gen)) {
					cromosomaNDP5.setGen(set.size(), gen);
					set.add(gen);
				}
			}
			return cromosomaNDP5;
		}
		return null;
	}

	@Override
	public Cruce createCruce(String tipo, HashMap<String, Object> props) {
		if(tipo.equalsIgnoreCase("CruceCO")) return new CruceCO();
		else if(tipo.equalsIgnoreCase("CruceCX")) return new CruceCX();
		else if(tipo.equalsIgnoreCase("CruceOX")) return new CruceOX();
		else if(tipo.equalsIgnoreCase("CruceOXPP")) {
			Integer numGens2Xchng = (Integer)props.get("numGens2Xchng");
			CruceOXPP cruceOXPP = new CruceOXPP();
			cruceOXPP.setNumGens2Xchng(numGens2Xchng);
			return cruceOXPP;
		}
		else if(tipo.equalsIgnoreCase("CruceOXOP")) return new CruceOXOP();
		else if(tipo.equalsIgnoreCase("CrucePMX")) return new CrucePMX();
		else if(tipo.equalsIgnoreCase("CruceERX")) return new CruceERX();
		else if(tipo.equalsIgnoreCase("CruceZ0")) return new CrucePropioP5();
		return null;
	}

	@Override
	public Seleccion createSeleccion(String tipo, HashMap<String, Object> props) {
		if(tipo.equalsIgnoreCase("Ruleta")) return new SeleccionRuleta();
		else if(tipo.equalsIgnoreCase("Estocastico")) {
			Integer kindividuos = (Integer)props.get("kindividuos");
			SeleccionEstocastica seleccionEstocastica = new SeleccionEstocastica();
			seleccionEstocastica.setKindividuos(kindividuos);
			return seleccionEstocastica;
		}
		else if(tipo.equalsIgnoreCase("Torneo")) {
			Integer tamMuestra 	= (Integer) props.get("tamMuestra");
			Double umbral 		= (Double) props.get("umbral");
			SeleccionTorneo seleccionTorneo = new SeleccionTorneo();
			seleccionTorneo.setTamMuestra(tamMuestra);
			seleccionTorneo.setUmbral(umbral);
			return seleccionTorneo;
		}
		else if(tipo.equalsIgnoreCase("Ranking")) {
			Double beta 		= (Double) props.get("beta");
			SeleccionRanking seleccionRanking = new SeleccionRanking();
			seleccionRanking.setBeta(beta);
			return seleccionRanking;
		}
		else if(tipo.equalsIgnoreCase("Truncamiento")) {
			Double trunc 		= (Double) props.get("trunc");
			SeleccionTruncamiento seleccionTruncamiento = new SeleccionTruncamiento();
			seleccionTruncamiento.setTrunc(trunc);
			return seleccionTruncamiento;
		}
		return null;
	}

	@Override
	public Mutacion createMutacion(String tipo, HashMap<String, Object> props) {
		if(tipo.equalsIgnoreCase("Insercion")) {
			Integer numGens2Ins = (Integer) props.get("numGens2Ins");
			MutacionInsercion mutacionInsercion = new MutacionInsercion();
			mutacionInsercion.setNumGens2Ins(numGens2Ins);
			return mutacionInsercion;
		}
		else if(tipo.equalsIgnoreCase("Intercambio")) {
			Integer numG2Inv 	= (Integer) props.get("numG2Inv");
			MutacionIntercambio mutacionIntercambio = new MutacionIntercambio();
			mutacionIntercambio.setNumG2Inv(numG2Inv);
			return mutacionIntercambio;
		}
		if(tipo.equalsIgnoreCase("Inversion")) return new MutacionInversion();
		if(tipo.equalsIgnoreCase("Heuristica")) {
			Integer numGen2Perm = (Integer) props.get("numGen2Perm");
			MutacionHeuristica mutacionHeuristica = new MutacionHeuristica();
			mutacionHeuristica.setNumGen2Perm(numGen2Perm);
			return mutacionHeuristica;
		}
		if(tipo.equalsIgnoreCase("ShuffleGens")) return new MutacionShuffleG();
		return null;
	}

	@Override
	public Fitness createFitness(String tipo, HashMap<String, Object> props) {
		return null;
	}

	@Override
	public View createView(HashMap<String,Object> props) {
		return new View() {
			@Override
			public JComponent getComponent() {
				return new JPanel();
			}
		};
	}

}
