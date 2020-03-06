package facil;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeNode;

@SuppressWarnings("serial")
public class Experimento extends JFrame {

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
					Experimento frame = new Experimento();
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
	public Experimento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Item rootItem = new Item("root", new JTextField());
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootItem);
		DefaultMutableTreeNode a = new DefaultMutableTreeNode(new Item("tam poblacion", new JTextField(10)));
		DefaultMutableTreeNode b = new DefaultMutableTreeNode(new Item("num generaciones", new JComboBox<String>(new String[] {"aaaaa","bbbbb","ccccc"})));
		DefaultMutableTreeNode c = new DefaultMutableTreeNode(new Item("tres", new JPasswordField(10)));
		DefaultMutableTreeNode d = new DefaultMutableTreeNode(new Item("seleccion", new JTextField(10)));
		DefaultMutableTreeNode dd = new DefaultMutableTreeNode(new Item("elitismo", new JTextField("0.001")));
		DefaultMutableTreeNode ddd = new DefaultMutableTreeNode(new Item("tipo", new JTextField(10)));
		d.add(dd);
		d.add(ddd);
		root.add(a);
		root.add(b);
		root.add(c);
		root.add(d);
		
		JTree tree = new JTree(root);
		
		tree.setCellRenderer(new DefaultTreeCellRenderer() {
			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
					boolean leaf, int row, boolean hasFocus) {
				if(!leaf)
					return super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
				//if(root.isNodeChild((TreeNode)value)) {
				DefaultMutableTreeNode tn = (DefaultMutableTreeNode)value;
				Item i = (Item) tn.getUserObject();
				JLabel la = (JLabel)super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
				la.setText(i.label.getText());
				return la;
				//}
			}
		});
		tree.setCellEditor(new TreeCellEditor() {
			@Override
			public boolean stopCellEditing() { return true; }
			
			@Override
			public boolean shouldSelectCell(EventObject anEvent) { return true; }
			
			@Override
			public void removeCellEditorListener(CellEditorListener l) { }
			
			@Override
			public boolean isCellEditable(EventObject anEvent) {
				if ((anEvent instanceof MouseEvent)){
					TreeNode tp	 = (TreeNode) tree.getLastSelectedPathComponent();
					MouseEvent me = (MouseEvent) anEvent;
					if(me.getClickCount()>=2 && tp.isLeaf()) return true;
				} 
				if(anEvent==null) return true;//f2
				return false;
			}
			
			@Override
			public Object getCellEditorValue() { return null; }
			
			@Override
			public void cancelCellEditing() { }
			
			@Override
			public void addCellEditorListener(CellEditorListener l) { }
			
			@Override
			public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,
					boolean leaf, int row) {
				if(!leaf) {
					return tree.getCellRenderer().getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf, row, true);
				}
				//if(root.isNodeChild((TreeNode)value)) {
					DefaultMutableTreeNode tn = (DefaultMutableTreeNode)value;
					Item i = (Item) tn.getUserObject();
					DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
					JLabel jl = new JLabel(i.toString());
					jl.setIcon(renderer.getDefaultLeafIcon());
					return new JPanel() {{
						setBorder(new EmptyBorder(0,0,0,0));
						setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
						add(jl);
						add(i.componente);
						setOpaque(false);
					}};
				//}
			}
		});

		tree.setEditable(true);
		tree.setRowHeight(20);
		contentPane.add(tree,BorderLayout.CENTER);
	}

}

class Item{
	JLabel label;
	JComponent componente;
	private String text;
	public Item(String txt, JTextField comp) {
		text = txt;
		label = new JLabel(text+": "+comp.getText());
		componente = comp;
		comp.addActionListener((a)->{
			label.setText(text+": "+comp.getText());
		});
	}
	public Item(String txt, JComboBox<String> comp) {
		componente = comp;
		text = txt;
		label = new JLabel(text+": "+comp.getSelectedItem().toString());
		comp.addActionListener((a)->{
			label.setText(text+": "+comp.getSelectedItem().toString());
		});
	}
	public Item(String txt, JPasswordField comp) {
		componente = comp;
		text = txt;
		label = new JLabel(text+": "+new String(comp.getPassword()));
		comp.addActionListener((a)->{
			label.setText(text+": "+new String(comp.getPassword()));
		});
	}
	
	@Override
	public String toString() {
		return text;
	}
}
