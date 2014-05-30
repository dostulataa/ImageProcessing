package imageprocessing;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import filters.Filter;
import filters.FilterPresets;

public class UserInterface extends JFrame {
	
	private BufferedImage img;

	private JPanel imagePanel = new JPanel(){
		public void paint(java.awt.Graphics g) {if(img!=null)g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);};
	};
	private JMenuBar  menuBar    = new JMenuBar(            );
	private JMenu     menuFile   = new JMenu(        "Datei");
	private JMenu     menuFilter = new JMenu(       "Filter");
	private JMenuItem menuOpen   = new JMenuItem(  "Oeffnen");
	private JMenuItem menuSave   = new JMenuItem("Speichern");
	private JMenuItem menuExit   = new JMenuItem(  "Beenden");
	
	public UserInterface(){
		super("ImageProcessing");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		int screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height;
		setBounds(screenwidth / 2 - 500, screenheight / 2 - 400, 1000, 800);
		
		add(imagePanel);
		imagePanel.setBounds(0, 0, getWidth(), getHeight());
		
		setJMenuBar(menuBar);
		menuBar.add(menuFile);
		menuFile.add(menuOpen);
		menuFile.add(menuSave);
		menuFile.addSeparator();
		menuFile.add(menuExit);
		menuBar.add(menuFilter);
		
		for (Entry<String,Filter> filter : FilterPresets.getAllFilters()) {
			JMenuItem newitem = new JMenuItem(filter.getKey());
			menuFilter.add(newitem);
			newitem.addActionListener(new ActionListener() {
				@Override public void actionPerformed(ActionEvent e) {
					img = FilterPresets.getFilter(((JMenuItem)e.getSource()).getText()).process(img);
					imagePanel.repaint();
				}
			});
		}
		
		try {
			img = ImageIO.read(new File("test_image.bmp"));
			imagePanel.repaint();
		} catch (IOException e2) {
			System.out.println("Standartbild konnte nicht geladen werden");
		}
		
		menuOpen.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				filechooser.setApproveButtonText("Bild oeffnen");
				filechooser.setDialogTitle("Bild oeffnen");
				filechooser.setFileFilter(new FileFilter() {
					@Override public String getDescription() {
						return "Bilddateien";
					}
					@Override public boolean accept(File f) {
						return f.getName().endsWith(".bmp") || f.isDirectory();
					}
				});
				if(filechooser.showOpenDialog(null)!=JFileChooser.APPROVE_OPTION){
					return;
				}
				try {
					img = ImageIO.read(filechooser.getSelectedFile());
					imagePanel.repaint();
				} catch (IOException e1) {
					System.err.println("Fehler beim Laden der Datei " + filechooser.getSelectedFile());
				}
			}
		});
		
		menuSave.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				if(img == null){
					JOptionPane.showInternalMessageDialog(null, "Kein Bild ausgewaehlt!");
					return;
				}
				JFileChooser filechooser = new JFileChooser();
				filechooser.setApproveButtonText("Bild speichern");
				filechooser.setDialogTitle("Bild speichern");
				filechooser.setFileFilter(new FileFilter() {
					@Override public String getDescription() {
						return "Bitmap (*.bmp)";
					}
					@Override public boolean accept(File f) {
						return f.getName().endsWith(".bmp") || f.isDirectory();
					}
				});
				if(filechooser.showOpenDialog(null)!=JFileChooser.APPROVE_OPTION){
					return;
				}
				try {
					ImageIO.write(img, "bmp", filechooser.getSelectedFile());
				} catch (IOException e1) {
					System.err.println("Fehler beim schreiben der Datei " + filechooser.getSelectedFile());
				}
			}
		});
		
		menuExit.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
