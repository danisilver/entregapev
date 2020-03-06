package facil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.math.plot.Plot3DPanel;

public class Experimento3 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Experimento3 frame = new Experimento3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Experimento3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Plot3DPanel p = new Plot3DPanel();
		Object[][] XYZ = new Object[8][3];
		Object[][] XYZ2 = new Object[10][3];

		for (int j = 0; j < XYZ.length; j++) {
			XYZ[j][0] = Math.random();
			XYZ[j][1] = Math.random();
			XYZ[j][2] = "" + ((char) ('a' + j));
		}

		for (int j = 0; j < XYZ2.length; j++) {
			XYZ2[j][0] = Math.random();
			XYZ2[j][1] = Math.random();
			XYZ2[j][2] = "" + ((char) ('a' + j));
		}

		p.addScatterPlot("toto", p.mapData(XYZ));
		p.addScatterPlot("toti", p.mapData(XYZ2));
		p.setAxisScale(1, "log");
		contentPane.add(p);
	}

}
