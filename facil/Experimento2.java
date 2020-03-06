package facil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.math.plot.Plot2DPanel;
import java.awt.Component;
import javax.swing.JTree;

public class Experimento2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { System.exit(-1);}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Experimento2 frame = new Experimento2();
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
	public Experimento2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel() {
			@Override
			public boolean isOptimizedDrawingEnabled() {
				return false;
			}
		};
		contentPane.setLayout(new OverlayLayout(contentPane));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		
		
		Plot2DPanel plot2DPanel = new Plot2DPanel();
		plot2DPanel.addPlotToolBar(Plot2DPanel.SOUTH);
		plot2DPanel.plotToolBar.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		plot2DPanel.plotToolBar.setBackground(new Color(0,0,0,0));
		plot2DPanel.plotToolBar.setBorder(new EmptyBorder(0,0,0,0));
		plot2DPanel.plotToolBar.setOpaque(false);
		plot2DPanel.plotToolBar.remove(3);
		plot2DPanel.plotToolBar.remove(3);
		plot2DPanel.plotToolBar.remove(3);
		plot2DPanel.setAlignmentX(0.005f);
		//plot2DPanel.setAutoBounds();
		
		JPanel plot3d = new JPanel();
		plot3d.setBorder(new EmptyBorder(10, 10, 10, 10));
		plot3d.setMaximumSize(new Dimension(200,Integer.MAX_VALUE));
		plot3d.setBackground(new Color(0,0,0,0));
		plot3d.setOpaque(false);
		plot3d.setAlignmentX(0.5f);
		
		contentPane.add(plot3d);
		plot3d.setLayout(new BorderLayout(0, 0));
		
		JTree tree = new JTree();
		tree.setOpaque(false);
		tree.setBackground(new Color(0,0,0,0));
		DefaultTreeCellRenderer defaultTreeCellRenderer = new DefaultTreeCellRenderer();
		defaultTreeCellRenderer.setLeafIcon(null);
		defaultTreeCellRenderer.setClosedIcon(null);
		defaultTreeCellRenderer.setOpenIcon(null);
		tree.setCellRenderer(defaultTreeCellRenderer);
		//tree.setRootVisible(false);
		plot3d.add(tree, BorderLayout.WEST);
		
		contentPane.add(plot2DPanel);
		
		
		
	}
}
