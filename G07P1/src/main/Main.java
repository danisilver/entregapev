package main;

import javax.swing.UIManager;

import org.jzy3d.chart.Settings;

import controller.Controller;
import controller.MainController;
import model.MainModel;
import view.MainView;

public class Main {
	
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			Settings.getInstance().setHardwareAccelerated(true);
		} catch (Exception e) { System.exit(-1);}
	}
	
	public static void main(String[] args) {
		MainModel  mainModel   = new MainModel();
		MainView   mainView    = new MainView(mainModel);
		Controller controlador = new MainController(mainView, mainModel);
		controlador.restart();
	}
	
}
