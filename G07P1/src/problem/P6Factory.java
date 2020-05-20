package problem;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.HashMap;

import javax.swing.JPanel;

import core.cruce.Cruce;
import core.cruce.CruceNodeXchng;
import core.fitness.Fitness;
import core.mutacion.Mutacion;
import core.mutacion.MutacionFuncionalSimple;
import core.mutacion.MutacionNodeRestart;
import core.mutacion.MutacionPermutarArgs;
import core.mutacion.MutacionTerminalSimple;
import core.mutacion.MutacionTreeHoist;
import core.selection.PoliMcPheeBloating;
import core.selection.Seleccion;
import core.selection.SeleccionEstocastica;
import core.selection.SeleccionRanking;
import core.selection.SeleccionRuleta;
import core.selection.SeleccionTorneo;
import core.selection.SeleccionTruncamiento;
import core.selection.TarpeianBloating;
import gen.Cromosoma;
import gen.CromosomaGramatica;
import model.MainModel;
import model.Observer;
import utils.NAddrInputsObserver;
import view.View;

/**
 * @author daniel
 * gramatica: 
 * 
	PROGRAM ::= OP0 | OP1 ;
	OP0     ::= if OP1 out OP0;
	OP1     ::= and IN OP1 | or IN OP1 | not IN | IN;
	OUT     ::= d0 | d1 | d2 | d3 | d4 ;
	IN      ::= a0 | a1 ;
 * 
 */
public class P6Factory implements ProblemFactory{

	private static View view;
	private Observer obs;

	@Override
	public Cromosoma createCromosoma(String tipo, HashMap<String, Object> props) {
		if (tipo.equalsIgnoreCase("Gramatica")) {
			Integer nAddrInputs = (Integer) props.get("nAddrInputs"); 
			String _useIf		= props.get("useIF").toString();
			String _profundidad = props.get("profundidad").toString();
			String _tipoCreacion= props.get("tipoCreacion").toString();
			Boolean useIf		= Boolean.valueOf(_useIf);
			Integer profundidad	= Integer.parseInt(_profundidad);
			Integer tipoCreacion= Integer.parseInt(_tipoCreacion);
			return new CromosomaGramatica(profundidad, tipoCreacion, useIf, nAddrInputs);
		}
		return null;
	}

	@Override
	public Cruce createCruce(String tipo, HashMap<String, Object> props) {
		if (tipo.equalsIgnoreCase("NodeXchng")) {
			return new CruceNodeXchng();
		}
		return null;
	}

	private Seleccion adapt2bloating(Seleccion sel, HashMap<String, Object> props) {
		String bloating = "";
		if(props.containsKey("bloating")) 
			bloating = props.get("bloating").toString();
		if(bloating.equalsIgnoreCase("Tarpeian")) {
			Integer deathProportion = (Integer) props.get("tarpeianDeathProportion");
			return new TarpeianBloating(sel, deathProportion);
		} else if(bloating.equalsIgnoreCase("PoliMcPhee")) {
			return new PoliMcPheeBloating(sel);
		} else {
			return sel;
		}
	}
	
	@Override
	public Seleccion createSeleccion(String tipo, HashMap<String, Object> props) {
		
		if(tipo.equalsIgnoreCase("Ruleta")) {
			return adapt2bloating(new SeleccionRuleta(), props);
		}
		else if(tipo.equalsIgnoreCase("Estocastico")) {
			Integer kindividuos = (Integer)props.get("kindividuos");
			SeleccionEstocastica seleccionEstocastica = new SeleccionEstocastica();
			seleccionEstocastica.setKindividuos(kindividuos);
			return adapt2bloating(seleccionEstocastica, props);
		}
		else if(tipo.equalsIgnoreCase("Torneo")) {
			Integer tamMuestra 	= (Integer) props.get("tamMuestra");
			Double umbral 		= (Double) props.get("umbral");
			SeleccionTorneo seleccionTorneo = new SeleccionTorneo();
			seleccionTorneo.setTamMuestra(tamMuestra);
			seleccionTorneo.setUmbral(umbral);
			return adapt2bloating(seleccionTorneo, props);
		}
		else if(tipo.equalsIgnoreCase("Ranking")) {
			Double beta 		= (Double) props.get("beta");
			SeleccionRanking seleccionRanking = new SeleccionRanking();
			seleccionRanking.setBeta(beta);
			return adapt2bloating(seleccionRanking, props);
		}
		else if(tipo.equalsIgnoreCase("Truncamiento")) {
			Double trunc 		= (Double) props.get("trunc");
			SeleccionTruncamiento seleccionTruncamiento = new SeleccionTruncamiento();
			seleccionTruncamiento.setTrunc(trunc);
			return adapt2bloating(seleccionTruncamiento, props);
		}
		return null;
	}

	@Override
	public Mutacion createMutacion(String tipo, HashMap<String, Object> props) {
		if (tipo.equalsIgnoreCase("FuncionalSimple")) {
			return new MutacionFuncionalSimple();
		} else if(tipo.equalsIgnoreCase("NodeRestart")) {
			return new MutacionNodeRestart();
		} else if(tipo.equalsIgnoreCase("PermutarArgs")) {
			return new MutacionPermutarArgs();
		} else if(tipo.equalsIgnoreCase("TerminalSimple")) {
			return new MutacionTerminalSimple();
		} else if(tipo.equalsIgnoreCase("TreeHoist")) {
			return new MutacionTreeHoist();
		}
		return null;
	}

	@Override
	public Fitness createFitness(String tipo, HashMap<String, Object> props) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View createView(HashMap<String, Object> props) {
		if(view!=null) return view;
		
		MainModel model = (MainModel) props.get("mainmodel");
		view = new View() {
			JPanel jPanel = new JPanel(new BorderLayout());
			@Override public Component getComponent() {
				return jPanel;
			}
		};
		obs = new NAddrInputsObserver(props, view);
		obs.update();
		model.addPropObserver("nAddrInputs", obs);
		return view;
	}
	
	@Override
	public Cromosoma[] createPoblacionInicial(
			String tipo, HashMap<String,Object> props, int tam) {
		String crom = props.get("tipoCromosoma").toString();
		String _tipoCreacion = props.get("tipoCreacion").toString();
		Integer tipoCreacion = Integer.parseInt(_tipoCreacion);
		if(tipoCreacion!=2) {
			return ProblemFactory.super.createPoblacionInicial(tipo, props, tam);
		} else { //Ramped and Half
			String _profundidad = props.get("profundidad").toString();
			Integer profundidad = Integer.parseInt(_profundidad);
			Integer profActual = 2;
			Cromosoma[] poblInicial = new Cromosoma[tam];
			for (int i = 0; i < tam; i++) {
				if(profActual > profundidad) profActual = profundidad;
				if((i%(tam/profundidad))==0) profActual++;
				props.put("tipoCreacion",
						(i%2==0)?Integer.valueOf(0):Integer.valueOf(1));
				Cromosoma nuevo = createCromosoma(crom, props);
				poblInicial[i]=nuevo;
			}
			props.put("tipoCreacion", tipoCreacion);
			
			return poblInicial;
		}
	}
}
