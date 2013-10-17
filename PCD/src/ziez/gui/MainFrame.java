package ziez.gui;


import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ziez.image.*;
import ziez.alg.gan.Segmentasi;
import ziez.gui.ImagePanel;

public class MainFrame implements ActionListener, ChangeListener, Runnable{

	// ///////////////////////// COMPONENT INSTANCE ///////////////////////////

	private JFrame frame;
	private ImagePanel panelContainer;

	private ImagePanel panelFungsiButton, panelWrapper, panelProses,
			panelDialog, panelTool, panelImage, panelImage2, panelImage3,
			panelImageKiri, panelImageTengah, panelImageKanan,
			panelImageKananWrapper, panelHistogram, panelHistogram2,
			panelHistogram3, panelHistogramKiri, panelHistogramKanan,
			panelHistogramTengah, panelHistogramProses, panelKernel,
			panelKernel3, panelKernel5;
	private ImagePanel panelDialogBW, panelDialogGrayscale,
			panelDialogBrightnest, panelDialogContrast, panelDialogNegasi,
			panelDialogBlending, panelDialogLogic, panelDialogMotion,
			panelConvCard, panelConvAll, panelConvSmooth, panelConvSharp,
			panelConvBlur, panelConvEmboss, panelConvEdge, panelEdgeCard,
			panelEdgeLaplacian, panelEdgeSobel, panelEdgePrewit,
			panelEdgeRobert, panelDialogSegmentation, panelDialogStatistic;
	private JScrollPane scrollKanan, scrollTengah, scrollKiri;
	private JButton btnFungsi, btnGrayscale, btnLogic, btnConvAll,
			btnConvSmooth, btnConvSharp, btnConvBlur, btnConvEmboss,
			btnConvEdge, btnEdgeLaplacian, btnEdgeSobel, btnEdgePrewit,
			btnEdgeRobert, btnStatistic;
	private JMenuBar menuBar;
	private JMenu menuFile, menuEdit, menuFilter, menuLogic, menuConvolution,
			menuStatistic, menuSegmentation;
	private JMenuItem fileItem, editItem, filterItem, convolutionItem,
			laplacianItem, prewitItem, sobelItem, robertItem, logicItem,
			statisticItem, segmentationItem, regionGrowingItem,edgeSegItem;
	private JMenuItem item;

	private JMenu menuEdgeDetection, menuRegionGrowing,menuEdgeSeg, menuLaplacian,
			menuPrewit, menuSobel, menuRobert, menuZoom;
	private JSlider sliderBrightnest, sliderContrast_G, sliderContrast_P,
			sliderBlending, sliderSegment;
	private JLabel labelBrightnest, labelContrast_G, labelContrast_P,
			labelBlending, labelMotion, labelSegment;

	private JTextField textKernel3[][], textKernel5[][];

	private CardLayout cardImage, cardHistogram, cardKernel, cardDialog,
			cardConv, cardEdge, cardSegment;
	private Border compound, raise, lower;
	
	// ///////////////////////// CLASS INSTANCE ///////////////////////////
	private Font mrFont;
	private BufferedImage bg = null;
	private BufferedImage imageRegion = null;
	private int valueP, valueG;
	public boolean isGray = false;
	
	private static final Color SEED_COLOUR = Color.green;
	public static final int DEFAULT_THRESHOLD = 10;
	private static final int SEEDING = 1;
	private static final int GROWING = 2;
	private static final int GROWN = 3;
	private int status = SEEDING;
	@SuppressWarnings("rawtypes")
	private List seedPixels = new ArrayList ();
	private int connectivity = 8;
	private Thread growthThread;

	private ImageModel imageModel = new ImageModel ();
	private ImagePanel panelImageAsli = new ImagePanel ();
	private ImagePanel panelImageHasil = new ImagePanel ();
	private ImagePanel panelImageProses = new ImagePanel ();
	private ImagePanel panelImageRegion = new ImagePanel ();
	private View panelImageRegGrowSegment = new View ();
	private View panelImageEdgeSegment = new View ();

	// /////////////////////////// INNER CLASSES //////////////////////////////

	@SuppressWarnings("serial")
	class View extends ImagePanel {

		private BufferedImage overlay;

		@Override
		public void setLayout(LayoutManager mgr) {

			super.setLayout (new FlowLayout (FlowLayout.CENTER));
		}

		public View(BufferedImage inputImage, BufferedImage statusImage) {

			super (inputImage);
			overlay = statusImage;
			addMouseListener (new MouseAdapter () {
				@SuppressWarnings("unchecked")
				public void mouseReleased(MouseEvent event) {

					if (status == SEEDING) {
						Point pixel = event.getPoint ();
						if (!seedPixels.contains (pixel)) {
							seedPixels.add (pixel);

							repaint ();
						}
					}
				}
			});
		}

		public View() {

		}

		public void setOverlay(BufferedImage image) {

			overlay = image;
			repaint ();
		}

		public void paintComponent(Graphics g) {

			super.paintComponent (g);
			if (overlay != null)
				g.drawImage (overlay, 0, 0, this);
			if (status == SEEDING) {
				g.setColor (SEED_COLOUR);
				@SuppressWarnings("rawtypes")
				Iterator iterator = seedPixels.iterator ();
				while (iterator.hasNext ()) {
					Point pixel = (Point) iterator.next ();
					g.fillRect (pixel.x - 1, pixel.y - 1, 3, 3);
				}
			}
		}

	} // END VIEW

	@SuppressWarnings("serial")
	public MainFrame() {

		frame = new JFrame ();
		panelContainer = new ImagePanel ();

		menuBar = new JMenuBar () {

			protected void paintComponent(Graphics g) {

				// Paint glass = new GradientPaint (0, 0, new Color (42, 55, 57,
				// 50), 0, getHeight (), new Color (9, 119, 168, 127));
				Paint glass = new GradientPaint (0, 0,
						(new Color (getBackground ().getRed (),
								getBackground ().getGreen (), getBackground ()
										.getBlue (), 50)), 0, getHeight (),
						(new Color (getBackground ().getRed (),
								getBackground ().getGreen (), getBackground ()
										.getBlue (), 250)));
				Graphics2D g2 = (Graphics2D) g;
				super.paintComponent (g);

				g2.setPaint (glass);

				g2.fillRoundRect (0, 0, getWidth (), getHeight (), 0, 10);
				// g2.dispose ();
			}
		};
		menuFile = new JMenu ();
		menuEdit = new JMenu ();
		menuFilter = new JMenu ();
		menuLogic = new JMenu ();
		menuConvolution = new JMenu ();
		menuEdgeDetection = new JMenu ();
		menuLaplacian = new JMenu ();
		menuPrewit = new JMenu ();
		menuSobel = new JMenu ();
		menuRobert = new JMenu ();
		menuStatistic = new JMenu ();
		menuSegmentation = new JMenu ();
		menuRegionGrowing = new JMenu ();
		menuEdgeSeg = new JMenu ();
                menuZoom = new JMenu("Zoom");
		panelFungsiButton = new ImagePanel ();
		panelWrapper = new ImagePanel ();
		panelProses = new ImagePanel ();
		panelImage = new ImagePanel ();
		panelImage2 = new ImagePanel ();
		panelImage3 = new ImagePanel ();
		scrollKanan = new JScrollPane ();
		scrollKiri = new JScrollPane ();
		scrollTengah = new JScrollPane ();
		panelImageKiri = new ImagePanel ();
		panelImageTengah = new ImagePanel ();
		panelImageKanan = new ImagePanel ();
		panelImageKananWrapper = new ImagePanel ();
		panelHistogram = new ImagePanel ();
		panelHistogram2 = new ImagePanel ();
		panelHistogram3 = new ImagePanel ();
		panelHistogramKiri = new ImagePanel ();
		panelHistogramKanan = new ImagePanel ();
		panelHistogramTengah = new ImagePanel ();
		textKernel3 = new JTextField[3][3];
		textKernel5 = new JTextField[5][5];
		panelHistogramProses = new ImagePanel ();
		panelKernel = new ImagePanel ();
		panelKernel3 = new ImagePanel ();
		panelKernel5 = new ImagePanel ();
		panelDialog = new ImagePanel ();
		panelDialogBW = new ImagePanel ();
		panelDialogGrayscale = new ImagePanel ();
		panelDialogBrightnest = new ImagePanel ();
		sliderBrightnest = new JSlider (JSlider.HORIZONTAL, -255, 255, 0);
		labelBrightnest = new JLabel ();
		panelDialogContrast = new ImagePanel ();
		sliderContrast_G = new JSlider (JSlider.HORIZONTAL, 0, 100, 50);
		labelContrast_G = new JLabel ();
		sliderContrast_P = new JSlider (JSlider.HORIZONTAL, 0, 255, 128);
		labelContrast_P = new JLabel ();
		panelDialogNegasi = new ImagePanel ();
		panelDialogBlending = new ImagePanel ();
		sliderBlending = new JSlider (JSlider.HORIZONTAL, 0, 100, 100);
		labelBlending = new JLabel ();
		panelConvCard = new ImagePanel ();
		panelConvAll = new ImagePanel ();
		panelConvBlur = new ImagePanel ();
		panelConvEmboss = new ImagePanel ();
		panelConvSharp = new ImagePanel ();
		panelConvSmooth = new ImagePanel ();
		panelConvEdge = new ImagePanel ();
		panelEdgeCard = new ImagePanel ();
		panelEdgeLaplacian = new ImagePanel ();
		panelEdgeSobel = new ImagePanel ();
		panelEdgePrewit = new ImagePanel ();
		panelEdgeRobert = new ImagePanel ();
		panelDialogLogic = new ImagePanel ();
		panelDialogMotion = new ImagePanel ();
		labelMotion = new JLabel ();
		panelDialogStatistic = new ImagePanel ();
		btnStatistic = new JButton ();
		panelDialogSegmentation = new ImagePanel ();
		sliderSegment = new JSlider (JSlider.HORIZONTAL, 0, 256, 20);
		labelSegment = new JLabel ();
		panelTool = new ImagePanel ();

		cardImage = new CardLayout ();
		cardHistogram = new CardLayout ();
		cardKernel = new CardLayout ();
		cardDialog = new CardLayout ();
		cardConv = new CardLayout ();
		cardEdge = new CardLayout ();
		cardSegment = new CardLayout ();

		raise = BorderFactory.createRaisedBevelBorder ();
		lower = BorderFactory.createLoweredBevelBorder ();
		compound = BorderFactory.createCompoundBorder (lower, raise);

		mrFont = new Font ("ubuntu", Font.PLAIN, 14);

		loadComponent ();
	}

	// ///////////////////////// LAYOUT ///////////////////////////

	private void loadComponent() {
		
		frame.setSize (Toolkit.getDefaultToolkit ().getScreenSize ());
		frame.setTitle ("OLAH CITRA DIGITAL - ABDILLAH AZIZ/06.2008.1.04297");
		frame.setLayout (new BorderLayout ());
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

			panelContainer.setLayout (new BorderLayout (25, 2));
			panelContainer.setBackground (bg, "/resource/bg1.jpg");
			
				// MenuBar
			
				JMenu menumenu[]={menuFile, menuEdit,menuFilter, menuLogic, menuConvolution,
						menuStatistic, menuSegmentation};
				setComponentDecoration (menumenu);
				menuBar.setOpaque (false);
				menuBar.setBorderPainted (false);
				menuBar.setForeground (new Color (43, 155, 219, 20));
				menuBar.setBackground (new Color (48, 48, 49, 0));
				menuBar.setFont (new Font ("ubuntu", Font.BOLD, 20));
					menuFile.setText ("FILE");
					menuFile.setMnemonic ('F');
					String[] fileMenuCMD = { "Open", "Open2","Save", "Exit" };
					char[] fileMenuMNC = { 'O', '2', 'S','X' };
					createMenu (fileMenuCMD, fileMenuMNC, menuFile, fileItem);
				menuBar.add (menuFile);
						
					menuEdit.setText ("EDIT");
					menuEdit.setMnemonic ('E');
					String[] menuEditCMD = { "Undo","HistogramNot", "HistogramOk","Preference"};
					char[] menuEditMNC = { 'U','N','O', 'P'};
					createMenu (menuEditCMD, menuEditMNC, menuEdit, editItem);
				menuBar.add (menuEdit);
				
					menuFilter.setText ("FILTER");
					menuFilter.setMnemonic ('T');
					String[] filterMenuCMD = { "Black-White", "Grayscale", "Contrast",
							"Brightnest", "Negasi", "Image Blending", "Motion Detection" };
					char[] filterMenuMNC = { 'W', 'G', 'C', 'B', 'N', 'G', 'M' };
					createMenu (filterMenuCMD, filterMenuMNC, menuFilter, filterItem);
				menuBar.add (menuFilter);
				
					menuConvolution.setText ("CONVOLUTION");
					menuConvolution.setMnemonic ('C');
					String[] convolutionMenuCMD = { "Smoothing", "Sharpening", "Emboss",
							"Blur"};
					char[] convolutionMenuMNC = { 'S', 'H', 'E', 'B'};
					createMenu (convolutionMenuCMD, convolutionMenuMNC, menuConvolution, convolutionItem);
						menuEdgeDetection.setFont (mrFont);
						menuEdgeDetection.setText ("Edge Detection");
					menuConvolution.add (menuEdgeDetection);
				menuBar.add (menuConvolution);
				
							menuLaplacian.setFont (mrFont);
							menuLaplacian.setText("Laplacian");
							String[] laplacianMenuCMD = { "Laplacian 3 Kernel","Laplacian 5 Kernel"};
							char[] laplacianMenuMNC = { '3', '5'};
							createMenu (laplacianMenuCMD, laplacianMenuMNC,menuLaplacian,laplacianItem);
						menuEdgeDetection.add (menuLaplacian);
						
							menuPrewit.setFont (mrFont);
							menuPrewit.setText("Prewit");
							String[] prewitMenuCMD = { "Prewit-H 3 Kernel", "Prewit-H 5 Kernel", "Prewit-V 3 Kernel", "Prewit-V 5 Kernel"};
							char[] prewitMenuMNC = { 'H', 'H','V', 'V'};
							createMenu (prewitMenuCMD, prewitMenuMNC,menuPrewit,prewitItem);
						menuEdgeDetection.add (menuPrewit);
							
							menuSobel.setFont (mrFont);
							menuSobel.setText("Sobel");
							String[] sobelMenuCMD = { "Sobel-H 3 Kernel", "Sobel-H 5 Kernel", "Sobel-V 3 Kernel", "Sobel-V 5 Kernel"};
							char[] sobelMenuMNC = { 'H', 'H','V', 'V'};
							createMenu (sobelMenuCMD, sobelMenuMNC,menuSobel,sobelItem);
						menuEdgeDetection.add (menuSobel);
						
							menuRobert.setFont (mrFont);
							menuRobert.setText("Robert");
							String[] robertMenuCMD = { "Robert-H 3 Kernel", "Robert-H 5 Kernel", "Robert-V 3 Kernel", "Robert-V 5 Kernel"};
							char[] robertMenuMNC = { 'H', 'H','V', 'V'};
							createMenu (robertMenuCMD, robertMenuMNC,menuRobert,robertItem);
						menuEdgeDetection.add (menuRobert);
					
			
					menuLogic.setText ("LOGIC");
					menuLogic.setMnemonic ('L');
					String[] logicMenuCMD = {  "Not","And", "Or","XOR"};
					char[] logicMenuMNC = { 'N','A', 'O', 'X'};
					createMenu (logicMenuCMD, logicMenuMNC, menuLogic, logicItem);
				menuBar.add (menuLogic);
				
					
					menuStatistic.setText ("STATISTIC");
					menuStatistic.setMnemonic ('S');
					String[] statisticMenuCMD = { "Mean", "Median", "Max","Min"};
					char[] statisticMenuMNC = { 'M', 'E', 'A', 'I'};
					createMenu (statisticMenuCMD, statisticMenuMNC, menuStatistic, statisticItem);
				menuBar.add (menuStatistic);
				
				menuSegmentation.setText ("SEGMENTATION");
				menuSegmentation.setMnemonic ('G');
			menuBar.add (menuSegmentation);
					
					menuRegionGrowing.setFont (mrFont);
					menuRegionGrowing.setText ("Neighborhood Based Segmentation");
					menuRegionGrowing.setMnemonic ('N');
					String[] regionGrowingMenuCMD = {"SetRegSamplePixel", "Start Segmentation", "Re-Seed ", "New-Seed"};
					char[] regionGrowingMenuMNC = { 'R','S', 'R', 'N'};
					createMenu (regionGrowingMenuCMD, regionGrowingMenuMNC, menuRegionGrowing, regionGrowingItem);
				menuSegmentation.add (menuRegionGrowing);
					
					menuEdgeSeg.setFont (mrFont);
					menuEdgeSeg.setText ("Edge Based Segmentation");
					menuEdgeSeg.setMnemonic ('E');
					String[] edgeSegMenuCMD = {"SetEdgeSamplePixel"};
					char[] edgeSegMenuMNC = {'E'};
					createMenu (edgeSegMenuCMD, edgeSegMenuMNC, menuEdgeSeg, edgeSegItem);
				menuSegmentation.add (menuEdgeSeg);

                        menuBar.add(menuZoom);
                        menuZoom.addActionListener(this);

			panelContainer.add (menuBar, BorderLayout.NORTH);
		
				// PanelFungsi
				panelFungsiButton.setPreferredSize (new Dimension (130, 100));
				panelFungsiButton.setLayout (new FlowLayout (FlowLayout.CENTER));
				panelFungsiButton.setBorder (createTitleBorder ("FUNGSI"));
				String[] btnFungsiCMD = { "BW", "GRAY", "BRIGHT", "CONTRAST", "NEGASI",
						"BLEND", "LOGIC", "MOTION", "CONV", "STATISTIC", "SEGM" };
				createButton (btnFungsiCMD, panelFungsiButton, btnFungsi,
						new Dimension (100, 30),12);
			panelContainer.add (panelFungsiButton, BorderLayout.WEST);
		
				// PanelProses
				panelWrapper.setLayout (new BorderLayout (0, 0));
				panelWrapper.setBorder (createTitleBorder (""));
					panelProses.setLayout (new BorderLayout ());
						
						//Image
						panelImage.setLayout (cardImage);						
						panelImage2.setLayout (new GridLayout (1, 2, 50, 0));
						panelImage3.setLayout (new GridLayout (1,3,20,0));
						
							panelImageKiri.setLayout (new FlowLayout (FlowLayout.CENTER));
							panelImageKiri.setBorder (raise);
						
						scrollKiri.setViewportBorder (lower);
						scrollKiri.setOpaque (false);
						scrollKiri.getViewport ().setOpaque (false);
						scrollKiri.getViewport ().add (panelImageKiri, null);
										
							try {
								BufferedImage bi = imageModel.setImage ("/home/ziez/Pictures/citra/resource/0conv.png");
								panelImageAsli.setBuffImage (bi);
								panelImageKiri.add (panelImageAsli);
								panelHistogramKiri.setBuffImage (imageModel.getHistogram (bi));
							}
							catch (IOException e) {
								JOptionPane.showMessageDialog (frame, "SetBase Image");
								openImage ("/home/",panelImageAsli,panelImageKiri,panelHistogramKiri);
							}
							
							
							panelImageKanan.setLayout (cardSegment);
							panelImageKanan.add (panelImageHasil,"ImageHasil");
							panelImageKanan.add (panelImageRegGrowSegment,"RegGrowSegment");
							panelImageKanan.add (panelImageEdgeSegment,"EdgeSegment");
							panelImageKanan.add (panelImageRegion,"ImageRegion");
							panelImageKananWrapper.setLayout (new FlowLayout (FlowLayout.CENTER));
							panelImageKananWrapper.setBorder (lower);
							panelImageKananWrapper.add(panelImageKanan);
						scrollKanan.setViewportBorder (raise);
						scrollKanan.setOpaque (false);
						scrollKanan.getViewport ().setOpaque (false);
						scrollKanan.getViewport ().add (panelImageKananWrapper, null);
						
							panelImageTengah.setLayout (new FlowLayout (FlowLayout.CENTER));
							panelImageTengah.setBorder (compound);
						scrollTengah.setViewportBorder (compound);
						scrollTengah.setOpaque (false);
						scrollTengah.getViewport ().setOpaque (false);
						scrollTengah.getViewport ().add (panelImageTengah, null);
						
						setCardImage2 ();
					panelProses.add (panelImage, BorderLayout.CENTER);
						
						//Histogram
						panelHistogram.setBorder (createTitleBorder ("HISTOGRAM"));
						panelHistogram.setPreferredSize (new Dimension (100, 260));
						panelHistogram.setLayout (cardHistogram);
							panelHistogram2.setLayout (new GridLayout (1, 2, 100, 1));
							panelHistogram3.setLayout (new GridLayout (1, 3, 20, 0));
							panelHistogramKiri.setBorder (raise);
							panelHistogramTengah.setBorder (compound);
							panelHistogramKanan.setBorder (lower);
							panelHistogramTengah.setLayout (cardKernel);
								panelKernel.setLayout (cardKernel);
								panelKernel.setBorder (createTitleBorder ("KERNEL USED"));
									createTextKernel3 (textKernel3, panelKernel3);
									
								panelKernel.add(panelKernel3,"kernel3");
									createTextKernel5 (textKernel5, panelKernel5);
								panelKernel.add(panelKernel5,"kernel5");
							panelHistogramTengah.add (panelHistogramProses,"histogram");								
							panelHistogramTengah.add (panelKernel,"kernel");
							setCardHistogram2 ();						
					panelProses.add (panelHistogram, BorderLayout.SOUTH);
				panelWrapper.add (panelProses, BorderLayout.CENTER);
					
					//PanelDialog
				
					ImagePanel dialog[] = {panelDialogBW, panelDialogGrayscale,
							panelDialogBrightnest, panelDialogContrast, panelDialogNegasi,
							panelDialogBlending, panelDialogLogic, panelDialogMotion,
							panelConvCard, panelConvAll, panelConvSmooth, panelConvSharp,
							panelConvBlur, panelConvEmboss, panelConvEdge, panelEdgeCard, panelEdgeLaplacian,
							panelEdgeSobel, panelEdgePrewit, panelEdgeRobert,panelDialogSegmentation,
							panelDialogStatistic};
					setComponentDecoration (dialog);
					
					panelDialog.setBorder (createTitleBorder (""));
					panelDialog.setPreferredSize (new Dimension (100, 110));
					panelDialog.setLayout (cardDialog);
					JComponent component[] = { sliderBrightnest, sliderContrast_G,
							sliderContrast_P, sliderBlending, labelBrightnest, labelContrast_G,
							labelContrast_P, labelBlending, labelMotion,sliderSegment,labelSegment };
					setComponentDecoration (component);
						panelDialog.add (panelDialogBW,"Black-White");
						
						//Grayscale
						String[] btnGrayscaleCMD = { "2", "4", "8", "16", "32",
								"64", "128", "256"};
						
						createButton (btnGrayscaleCMD, panelDialogGrayscale, btnGrayscale,
								new Dimension (80, 40),13);		
						
					panelDialog.add (panelDialogGrayscale,"Grayscale");
						
						//Brightnest
							sliderBrightnest.setPreferredSize (new Dimension (400,40));
							
							sliderBrightnest.setMaximum (255);
							sliderBrightnest.setMinimum (-255);
							sliderBrightnest.setPaintTicks (true);
							sliderBrightnest.setSnapToTicks (true);
							sliderBrightnest.setMinorTickSpacing (15);
							sliderBrightnest.setMajorTickSpacing (255);
							sliderBrightnest.setPaintLabels (true);
							sliderBrightnest.addChangeListener (this);
						panelDialogBrightnest.add (sliderBrightnest);
							labelBrightnest.setText ("  "+sliderBrightnest.getValue ());
							labelBrightnest.setBorder (createTitleBorder (""));
						panelDialogBrightnest.add (labelBrightnest);
					panelDialog.add (panelDialogBrightnest,"Brightnest");
					
						//Contrast
							sliderContrast_P.setPreferredSize (new Dimension (300,40));
							
							sliderContrast_P.setPaintTicks (true);
							sliderContrast_P.setMajorTickSpacing (51);
							sliderContrast_P.setLabelTable (sliderContrast_P
									.createStandardLabels (51));
							sliderContrast_P.setPaintLabels (true);
							sliderContrast_P.addChangeListener (this);
							
							labelContrast_P.setText ("  "+sliderContrast_P.getValue ());
							sliderContrast_G.setPreferredSize (new Dimension (300,40));
							
							sliderContrast_G.setPaintTicks (true);
							sliderContrast_G.setMinorTickSpacing (5);
							sliderContrast_G.setMajorTickSpacing (20);
							sliderContrast_G.setLabelTable (sliderContrast_G
									.createStandardLabels (25));
							sliderContrast_G.setPaintLabels (true);							
							sliderContrast_G.addChangeListener (this);
							labelContrast_G.setText ("  "+sliderContrast_G.getValue ());
						panelDialogContrast.add (labelContrast_P);
						panelDialogContrast.add (sliderContrast_P);
						panelDialogContrast.add (sliderContrast_G);
						panelDialogContrast.add (labelContrast_G);
					panelDialog.add (panelDialogContrast,"Contrast");
					
						//Negasi
					panelDialog.add (panelDialogNegasi,"Negasi");
						
						//Blending
							sliderBlending.setPreferredSize (new Dimension (400,40));
							sliderBlending.setPaintTicks (true);
							sliderBlending.setMinorTickSpacing (5);
							sliderBlending.setMajorTickSpacing (10);
							sliderBlending.setPaintLabels (true);
							sliderBlending.setLabelTable (sliderBlending.createStandardLabels (10));
							sliderBlending.setInverted (true);
							sliderBlending.addChangeListener (this);
							labelBlending.setText ("  "+sliderBlending.getValue ());
						panelDialogBlending.add (sliderBlending);
						panelDialogBlending.add (labelBlending);
					panelDialog.add (panelDialogBlending,"Image Blending");
					
						//Logic
						String[] btnLogicCMD =  { "NOT","AND", "OR","XOR"};
						createButton (btnLogicCMD, panelDialogLogic, btnLogic,
								new Dimension (80, 40),12);
					panelDialog.add (panelDialogLogic,"Logic");
					
						//Motion
							labelMotion.setText ("       ");
						panelDialogMotion.add (labelMotion);						
					panelDialog.add (panelDialogMotion,"Motion Detection");
					
						//Convolution
						panelConvCard.setLayout (cardConv);					
								String[] btnConvAllCMD =  { "SMOOTHING", "SHARPENING", "EMBOSS",
										"BLUR","EDGE"};
								createButton (btnConvAllCMD, panelConvAll, btnConvAll,
										new Dimension (100, 40),11);
							panelConvCard.add (panelConvAll,"ConvAll");
							
								String[] btnConvSmoothCMD =  { "SMOOTH-3", "SMOOTH-5","BACK"};
								createButton (btnConvSmoothCMD, panelConvSmooth, btnConvSmooth,
										new Dimension (100, 40),11);
							panelConvCard.add (panelConvSmooth,"Smoothing");
							
								String[] btnConvSharpCMD =  { "SHARP-3", "SHARP-5","BACK"};
								createButton (btnConvSharpCMD, panelConvSharp, btnConvSharp,
										new Dimension (100, 40),11);
							panelConvCard.add (panelConvSharp,"Sharpening");
							
								String[] btnConvEmbossCMD =  { "EMBOSS-3", "EMBOSS-5","BACK"};
								createButton (btnConvEmbossCMD, panelConvEmboss, btnConvEmboss,
										new Dimension (100, 40),11);
							panelConvCard.add (panelConvEmboss,"Emboss");
							
								String[] btnConvBlurCMD =  { "GAUSSIAN BLUR", "MOTION BLUR","BACK"};
								createButton (btnConvBlurCMD, panelConvBlur, btnConvBlur,
										new Dimension (120, 40),11);
							panelConvCard.add (panelConvBlur,"Blur");
							
								String[] btnConvEdgeCMD = { "LAPLACIAN", "SOBEL","PREWIT","ROBERT", "BACK" };
								createButton (btnConvEdgeCMD, panelConvEdge, btnConvEdge,
									new Dimension (100, 40),11);
							panelConvCard.add (panelConvEdge,"Edge");
							
							panelConvCard.add (panelEdgeCard,"EdgeCard");
								panelEdgeCard.setLayout (cardEdge);
									String[] btnEdgeLaplacianCMD = { "L 3 KERNEL","L 5 KERNEL", "BACK!" };
									createButton (btnEdgeLaplacianCMD, panelEdgeLaplacian, btnEdgeLaplacian,
										new Dimension (130, 40),11);
								panelEdgeCard.add (panelEdgeLaplacian,"Laplacian");
									
									String[] btnEdgeSobelCMD = { "S-H 3 KERNEL","S-H 5 KERNEL","S-V 3 KERNEL","S-V 5 KERNEL", "BACK!" };
									createButton (btnEdgeSobelCMD, panelEdgeSobel, btnEdgeSobel,
										new Dimension (130, 40),11);
								panelEdgeCard.add (panelEdgeSobel,"Sobel");
								
									String[] btnEdgePrewitCMD = { "P-H 3 KERNEL","P-H 5 KERNEL","P-V 3 KERNEL","P-V 5 KERNEL", "BACK!" };
									createButton (btnEdgePrewitCMD, panelEdgePrewit, btnEdgePrewit,
										new Dimension (130, 40),11);
								panelEdgeCard.add (panelEdgePrewit,"Prewit");
								
									String[] btnEdgeRobertCMD = { "R-H 3 KERNEL","R-H 5 KERNEL","R-V 3 KERNEL","R-V 5 KERNEL", "BACK!" };
									createButton (btnEdgeRobertCMD, panelEdgeRobert, btnEdgeRobert,
										new Dimension (130, 40),11);
								panelEdgeCard.add(panelEdgeRobert,"Robert");
					panelDialog.add (panelConvCard,"Convolution");
						
						//Statistic		
						String[] btnStatisticCMD = { "MEAN", "MEDIAN", "MAX","MIN" };
						createButton (btnStatisticCMD, panelDialogStatistic, btnStatistic,
							new Dimension (100, 40),11);	
					panelDialog.add (panelDialogStatistic,"Statistic");
					
						//Segmentation
							sliderSegment.setPreferredSize (new Dimension (600,40));
							sliderSegment.setPaintTicks (true);
							sliderSegment.setMinorTickSpacing (4);
							sliderSegment.setMajorTickSpacing (16);
							sliderSegment.setLabelTable (sliderSegment.createStandardLabels (16));
							sliderSegment.setPaintLabels (true);
							sliderSegment.addChangeListener (this);
							labelSegment.setText (""+sliderSegment.getValue ());
						panelDialogSegmentation.add(sliderSegment);
						panelDialogSegmentation.add (labelSegment);
					panelDialog.add (panelDialogSegmentation,"Segmentation");
				panelWrapper.add (panelDialog, BorderLayout.SOUTH);
			panelContainer.add (panelWrapper, BorderLayout.CENTER);
		
				// PanelTools
				panelTool.setBorder (createTitleBorder ("TOOLS"));
				panelTool.setPreferredSize (new Dimension (220, 100));
			panelContainer.add (panelTool, BorderLayout.EAST);
		frame.add (panelContainer, BorderLayout.CENTER);
		frame.setVisible (true);
		
		
	}

	// ////////////////////////////// METHODS /////////////////////////////////

	// Layout
	private void createButton(String[] commands, ImagePanel panelButton,
			JButton buton, Dimension dimension, int fontSize) {

		for (int i = 0; i < commands.length; ++i) {
			buton = new JButton ();
			buton.setText (commands[i]);
			buton.setPreferredSize (dimension);
			buton.setFont (new Font ("ubuntu", Font.BOLD, fontSize));
			buton.setBackground (new Color (25, 25, 25, 255));
			buton.setForeground (new Color (43, 155, 219, 225));
			buton.addActionListener (this);
			panelButton.add (buton);
		}
	}

	@SuppressWarnings("serial")
	private void createMenu(String[] commands, char[] mnemonic, JMenu menu,
			JMenuItem item2) {

		for (int i = 0; i < commands.length; ++i) {
			item = new JMenuItem () {
				@Override
				protected void paintComponent(Graphics g) {

					super.paintComponent (g);

					Graphics2D gd = (Graphics2D) g.create ();

					Paint glass = new GradientPaint (0, 0, new Color (1F, 1F,
							1F, 0.5F), 0, getHeight (), new Color (1F, 1F, 1F,
							0F));
					gd.setPaint (glass);
					gd.fillRect (0, 0, getWidth (), getHeight ());

					gd.dispose ();
				}
			};
			item.setText (commands[i]);
			item.setMnemonic (mnemonic[i]);
			item.addActionListener (this);
			item.setOpaque (false);
			item.setContentAreaFilled (false);
			item.setFocusPainted (false);
			item.setBorderPainted (false);
			item.setFont (new Font ("ubuntu", Font.PLAIN, 14));
			menu.add (item);
		}
	}

	private void createTextKernel3(JTextField textField[][], ImagePanel panel) {

		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < textField[i].length; j ++) {

				panel.setLayout (new GridLayout (3, 3, 3, 3));
				panel.add (textField[i][j] = new JTextField ());
				textField[i][j].setHorizontalAlignment (JTextField.CENTER);
				textField[i][j].setFont (new Font ("ubuntu", Font.BOLD, 16));
				textField[i][j].setForeground (new Color (225, 225, 225, 225));
				textField[i][j].setBackground (new Color (25, 25, 25, 60));
				// textField[i][j].setOpaque (false);
				textField[i][j].addMouseListener (new MouseListener () {

					@Override
					public void mouseReleased(MouseEvent e) {

					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseExited(MouseEvent e) {

					}

					@Override
					public void mouseEntered(MouseEvent e) {

					}

					@Override
					public void mouseClicked(MouseEvent e) {

						if (isGray = false)
							setConvolution (3, 3, textKernel3, false);
						else
							setConvolution (3, 3, textKernel3, true);
					}
				});

			}
		}
	}

	private void createTextKernel5(JTextField textField[][], ImagePanel panel) {

		for (int i = 0; i < 5; i ++) {
			for (int j = 0; j < textField[i].length; j ++) {

				panel.setLayout (new GridLayout (5, 5, 3, 3));
				panel.add (textField[i][j] = new JTextField ());
				textField[i][j].setHorizontalAlignment (JTextField.CENTER);
				textField[i][j].setFont (new Font ("ubuntu", Font.BOLD, 16));
				textField[i][j].setForeground (new Color (225, 225, 225, 225));
				textField[i][j].setBackground (new Color (25, 25, 25, 60));
				// textField[i][j].setOpaque (false);
				textField[i][j].addMouseListener (new MouseListener () {

					@Override
					public void mouseReleased(MouseEvent e) {

					}

					@Override
					public void mousePressed(MouseEvent e) {

					}

					@Override
					public void mouseExited(MouseEvent e) {

					}

					@Override
					public void mouseEntered(MouseEvent e) {

					}

					@Override
					public void mouseClicked(MouseEvent e) {

						if (isGray = false)
							setConvolution (5, 5, textKernel5, false);
						else
							setConvolution (5, 5, textKernel5, true);
					}
				});

			}
		}
	}

	private Border createTitleBorder(String title) {

		Border border = BorderFactory.createTitledBorder (new TitledBorder (
				null, title, TitledBorder.CENTER, TitledBorder.TOP, new Font (
						"ubuntu", Font.BOLD, 13)));
		return border;
	}

	private void setComponentDecoration(JComponent component[]) {

		for (int i = 0; i < component.length; i ++) {
			component[i].setForeground (new Color (225, 225, 225, 225));
			component[i].setBackground (new Color (43, 155, 219, 225));
			component[i].setFont (new Font ("ubuntu", Font.BOLD, 14));
			component[i].setBorder (createTitleBorder (""));
		}
	}

	private void setComponentDecoration(JPanel dialog[]) {

		for (int i = 0; i < dialog.length; i ++) {
			dialog[i].setLayout (new BorderLayout ());
			dialog[i].setOpaque (false);
			dialog[i].setPreferredSize (new Dimension (120, 100));
			dialog[i].setLayout (new FlowLayout (FlowLayout.CENTER));

		}
	}

	private void setComponentDecoration(JMenu menumenu[]) {

		for (int i = 0; i < menumenu.length; i ++) {
			menumenu[i].setBackground (new Color (43, 188, 188, 0));
			menumenu[i].setFont (new Font ("ubuntu", Font.BOLD, 12));
			menumenu[i].setMargin (new Insets (0, 5, 0, 8));

		}
	}

	private void setDialogTitle(String title) {

		panelDialog.setBorder (createTitleBorder (title));
	}

	private void setCardImage3() {

		panelImage3.add (scrollKiri);
		panelImage3.add (scrollTengah);
		panelImage3.add (scrollKanan);
		panelImage.add (panelImage3, "3");

	}

	private void setCardImage2() {

		panelImage2.add (scrollKiri);
		panelImage2.add (scrollKanan);
		panelImage.add (panelImage2, "2");

	}

	private void setCardHistogram2() {

		panelHistogram2.add (panelHistogramKiri);
		panelHistogram2.add (panelHistogramKanan);
		panelHistogram.add (panelHistogram2, "histogram2");

	}

	private void setsetCardHistogram3() {

		panelHistogram3.add (panelHistogramKiri);
		panelHistogram3.add (panelHistogramTengah);
		panelHistogram3.add (panelHistogramKanan);
		panelHistogram.add (panelHistogram3, "histogram3");

	}

	private void setCardFilter() {

		setCardImage2 ();
		setCardHistogram2 ();
		cardImage.show (panelImage, "2");
		cardSegment.show (panelImageKanan, "ImageHasil");
		cardHistogram.show (panelHistogram, "histogram2");
		cardKernel.show (panelHistogramTengah, "histogram");
		panelHistogram.setVisible (true);
	}

	private void setCardLogic() {

		setCardImage3 ();
		setsetCardHistogram3 ();
		cardImage.show (panelImage, "3");
		cardSegment.show (panelImageKanan, "ImageHasil");
		cardHistogram.show (panelHistogram, "histogram3");
		cardKernel.show (panelHistogramTengah, "histogram");
		setDialogTitle ("LOGIC");
		cardDialog.show (panelDialog, "Logic");
		panelHistogramTengah.setBorder (compound);
		panelHistogram.setVisible (true);

	}

	private void setCardConvolution() {

		setCardImage2 ();
		setsetCardHistogram3 ();
		cardImage.show (panelImage, "2");
		cardSegment.show (panelImageKanan, "ImageHasil");
		panelKernel.setVisible (true);
		cardHistogram.show (panelHistogram, "histogram3");
		cardKernel.show (panelHistogramTengah, "kernel");
		panelHistogramTengah.setBorder (null);
		panelHistogram.setVisible (true);
	}

	private void setCardEdge(String edge) {

		cardConv.show (panelConvCard, "EdgeCard");
		cardEdge.show (panelEdgeCard, edge);
		cardKernel.show (panelKernel, "kernel3");
		panelHistogram.setVisible (true);

	}

	private void setCardEdge(String edge, String kernel) {

		cardDialog.show (panelDialog, "Convolution");
		cardConv.show (panelConvCard, "EdgeCard");
		cardEdge.show (panelEdgeCard, edge);
		cardKernel.show (panelKernel, kernel);
		panelHistogram.setVisible (true);
	}

	private void setCardSegment(String constraint) {

		setCardImage2 ();
		setCardHistogram2 ();
		cardImage.show (panelImage, "2");
		cardSegment.show (panelImageKanan, constraint);
		cardHistogram.show (panelHistogram, "histogram2");
		cardKernel.show (panelHistogramTengah, "histogram");
		
	}

	// ///////////////////////// ACTION-ACTION ///////////////////////////

	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand ();

		if (cmd.equalsIgnoreCase ("Open")) {
			openImage ("/home/ziez/Pictures/citra/resource", panelImageAsli, panelImageKiri,
					panelHistogramKiri);

		}
		else if (cmd.equalsIgnoreCase ("Open2")) {
			cardSegment.show (panelImageKanan, "ImageHasil");
			openImage2 ();
		}
		else if (cmd.equalsIgnoreCase ("Save")) {

                    panelImageHasil.setBuffImage(panelImageAsli.getBuffImage());


		}
		else if (cmd.equalsIgnoreCase ("Exit")) {

		}
		else if (cmd.equalsIgnoreCase ("HistogramOk")) {
			panelHistogram.setVisible (true);
		}
		
		else if (cmd.equalsIgnoreCase ("HistogramNot")) {
			panelHistogram.setVisible (false);
		}

		// Filtering

		else if ((cmd.equalsIgnoreCase ("Black-White"))
				| ((cmd.equalsIgnoreCase ("BW")))) {
			setCardFilter ();
			setDialogTitle ("");
			cardDialog.show (panelDialog, "Black-White");

			setHitamPutih ();

		}
		else if ((cmd.equalsIgnoreCase ("Grayscale"))
				| ((cmd.equalsIgnoreCase ("GRAY")))) {
			setCardFilter ();
			setDialogTitle ("GRAYSCALE");
			cardDialog.show (panelDialog, "Grayscale");

			setGrayscale (8);

		}
		else if (cmd.equalsIgnoreCase ("2")) {
			setCardFilter ();
			setDialogTitle ("GRAYSCALE");
			cardDialog.show (panelDialog, "Grayscale");

			setGrayscale (1);

		}
		else if (cmd.equalsIgnoreCase ("4")) {
			setCardFilter ();
			setDialogTitle ("GRAYSCALE");
			cardDialog.show (panelDialog, "Grayscale");

			setGrayscale (2);

		}
		else if (cmd.equalsIgnoreCase ("8")) {
			setCardFilter ();
			setDialogTitle ("GRAYSCALE");
			cardDialog.show (panelDialog, "Grayscale");

			setGrayscale (3);

		}
		else if (cmd.equalsIgnoreCase ("16")) {
			setCardFilter ();
			setDialogTitle ("GRAYSCALE");
			cardDialog.show (panelDialog, "Grayscale");

			setGrayscale (4);

		}
		else if (cmd.equalsIgnoreCase ("32")) {
			setCardFilter ();
			setDialogTitle ("GRAYSCALE");
			cardDialog.show (panelDialog, "Grayscale");

			setGrayscale (5);

		}
		else if (cmd.equalsIgnoreCase ("64")) {
			setCardFilter ();
			setDialogTitle ("GRAYSCALE");
			cardDialog.show (panelDialog, "Grayscale");

			setGrayscale (6);

		}
		else if (cmd.equalsIgnoreCase ("128")) {
			setCardFilter ();
			setDialogTitle ("GRAYSCALE");
			cardDialog.show (panelDialog, "Grayscale");

			setGrayscale (7);

		}
		else if (cmd.equalsIgnoreCase ("256")) {
			setCardFilter ();
			setDialogTitle ("GRAYSCALE");
			cardDialog.show (panelDialog, "Grayscale");

			setGrayscale (8);

		}
		else if (cmd.equalsIgnoreCase ("Contrast")) {
			setCardFilter ();
			setDialogTitle ("P                                 - CONTRAST -                                  G");
			cardDialog.show (panelDialog, "Contrast");
			valueG = sliderContrast_G.getValue ();
			double gain = valueG / 100.0;
			setContrast (gain, sliderContrast_P.getValue ());
		}
		else if ((cmd.equalsIgnoreCase ("Brightnest"))
				| ((cmd.equalsIgnoreCase ("BRIGHT")))) {
			setCardFilter ();
			setDialogTitle ("BRIGHTNEST");
			cardDialog.show (panelDialog, "Brightnest");

			setBrightnest (0);

		}
		else if (cmd.equalsIgnoreCase ("Negasi")) {
			setCardFilter ();
			setDialogTitle ("");
			cardDialog.show (panelDialog, "Negasi");

			setNegasi ();

		}
		else if ((cmd.equalsIgnoreCase ("Motion Detection"))
				| (cmd.equalsIgnoreCase ("MOTION"))) {
			setCardFilter ();
			setDialogTitle ("MOTION DETECTION");
			cardDialog.show (panelDialog, "Motion Detection");

			setMotion ();

		}
		else if ((cmd.equalsIgnoreCase ("Image Blending"))
				| (cmd.equalsIgnoreCase ("BLEND"))) {
			setCardLogic ();
			setDialogTitle ("IMAGE BLENDING");
			cardDialog.show (panelDialog, "Image Blending");

			int value = sliderBlending.getValue ();
			double set = value / 100.0;
			labelBlending.setText ("" + set);
			setBlending (set);
		}

		// Logic
		else if (cmd.equalsIgnoreCase ("LOGIC")) {
			setCardLogic ();
			System.out.println (""+panelImageKiri.getHeight ()+", "+panelImageKiri.getWidth ());
		}
		else if (cmd.equalsIgnoreCase ("And")) {
			setCardLogic ();
			setLogic ("And");

		}
		else if (cmd.equalsIgnoreCase ("Not")) {
			setCardFilter ();
			setNot ();

		}
		else if (cmd.equalsIgnoreCase ("Or")) {
			setCardLogic ();
			setLogic ("Or");

		}
		else if (cmd.equalsIgnoreCase ("XOR")) {
			setCardLogic ();
			setLogic ("Xor");
		}

		// Convolution

		else if (cmd.equalsIgnoreCase ("CONV")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			cardDialog.show (panelDialog, "Convolution");
			isGray = false;
			setTextKernel (ConvKernel.defaultKernel3, textKernel3);
			setConvolution (3, 3, textKernel3, false);

		}
		else if (cmd.equalsIgnoreCase ("Smoothing")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			cardDialog.show (panelDialog, "Convolution");
			cardConv.show (panelConvCard, "Smoothing");
			isGray = false;
			setTextKernel (ConvKernel.smooth3, textKernel3);
			setConvolution (3, 3, textKernel3, false);

		}
		else if (cmd.equalsIgnoreCase ("SMOOTH-3")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			cardDialog.show (panelDialog, "Convolution");
			cardConv.show (panelConvCard, "Smoothing");
			cardKernel.show (panelKernel, "kernel3");
			isGray = false;
			setTextKernel (ConvKernel.smooth3, textKernel3);
			setConvolution (3, 3, textKernel3, false);

		}
		else if (cmd.equalsIgnoreCase ("SMOOTH-5")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			cardDialog.show (panelDialog, "Convolution");
			cardConv.show (panelConvCard, "Smoothing");
			cardKernel.show (panelKernel, "kernel5");
			isGray = false;
			setTextKernel (ConvKernel.smooth5, textKernel5);
			setConvolution (5, 5, textKernel5, false);

		}
		else if (cmd.equalsIgnoreCase ("Sharpening")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			cardDialog.show (panelDialog, "Convolution");
			cardConv.show (panelConvCard, "Sharpening");
			isGray = false;
			setTextKernel (ConvKernel.sharp3, textKernel3);
			setConvolution (3, 3, textKernel3, false);

		}
		else if (cmd.equalsIgnoreCase ("SHARP-3")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			cardDialog.show (panelDialog, "Convolution");
			cardConv.show (panelConvCard, "Sharpening");
			cardKernel.show (panelKernel, "kernel3");
			isGray = false;
			setTextKernel (ConvKernel.sharp3, textKernel3);
			setConvolution (3, 3, textKernel3, false);

		}
		else if (cmd.equalsIgnoreCase ("SHARP-5")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			cardDialog.show (panelDialog, "Convolution");
			cardConv.show (panelConvCard, "Sharpening");
			cardKernel.show (panelKernel, "kernel5");
			isGray = false;
			setTextKernel (ConvKernel.sharp5, textKernel5);
			setConvolution (5, 5, textKernel5, false);

		}
		else if (cmd.equalsIgnoreCase ("Emboss")) {
			setCardConvolution ();
			cardDialog.show (panelDialog, "Convolution");
			setDialogTitle ("CONVOLUTION");
			cardConv.show (panelConvCard, "Emboss");
			isGray = false;
			setTextKernel (ConvKernel.emboss3, textKernel3);
			setConvolution (3, 3, textKernel3, false);

		}
		else if (cmd.equalsIgnoreCase ("EMBOSS-3")) {
			setCardConvolution ();
			cardDialog.show (panelDialog, "Convolution");
			setDialogTitle ("CONVOLUTION");
			cardConv.show (panelConvCard, "Emboss");
			cardKernel.show (panelKernel, "kernel3");
			isGray = false;
			setTextKernel (ConvKernel.emboss3, textKernel3);
			setConvolution (3, 3, textKernel3, false);

		}
		else if (cmd.equalsIgnoreCase ("EMBOSS-5")) {
			setCardConvolution ();
			cardDialog.show (panelDialog, "Convolution");
			setDialogTitle ("CONVOLUTION");
			cardConv.show (panelConvCard, "Emboss");
			cardKernel.show (panelKernel, "kernel5");
			isGray = false;
		}
		else if (cmd.equalsIgnoreCase ("Blur")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			cardDialog.show (panelDialog, "Convolution");
			cardConv.show (panelConvCard, "Blur");
			cardKernel.show (panelKernel, "kernel3");
			isGray = false;
			setTextKernel (ConvKernel.blur, textKernel3);
			setConvolution (3, 3, textKernel3, false);

		}
		else if (cmd.equalsIgnoreCase ("GAUSSIAN BLUR")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			cardDialog.show (panelDialog, "Convolution");
			cardConv.show (panelConvCard, "Blur");
			cardKernel.show (panelKernel, "kernel5");
			isGray = false;
			
			setTextKernel (ConvKernel.gaussianBlur (), textKernel5);
			setConvolution (5, 5, textKernel5, false);

		}
		else if (cmd.equalsIgnoreCase ("MOTION BLUR")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			cardDialog.show (panelDialog, "Convolution");
			cardConv.show (panelConvCard, "Blur");
			cardKernel.show (panelKernel, "kernel3");
			isGray = false;
			setTextKernel (ConvKernel.motionBlur, textKernel3);
			setConvolution (3, 3, textKernel3, false);

		}
		else if (cmd.equalsIgnoreCase ("EDGE")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			panelKernel.setVisible (false);
			cardConv.show (panelConvCard, "Edge");
			// isGray = true;
			// setTextKernel (ConvKernel.laplacian3, textKernel3);
			// setConvolution (3,3,textKernel3,true);
			CannyEdgeDetector cani = new CannyEdgeDetector ();
			cani.setSourceImage (panelImageAsli.getBuffImage ());
			cani.process ();
			panelImageHasil.setBuffImage (cani.getEdgesImage ());

		}
		else if (cmd.equalsIgnoreCase ("Laplacian")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Laplacian");
			isGray = true;
			setTextKernel (ConvKernel.laplacian3, textKernel3);
			setConvolution (3, 3, textKernel3, true);

		}
		else if ((cmd.equalsIgnoreCase ("Laplacian 3Kernel"))
				| (cmd.equalsIgnoreCase ("L 3 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Laplacian", "kernel3");
			cardKernel.show (panelKernel, "kernel3");
			isGray = true;
			setTextKernel (ConvKernel.laplacian3, textKernel3);
			setConvolution (3, 3, textKernel3, true);

		}
		else if ((cmd.equalsIgnoreCase ("Laplacian 5Kernel"))
				| (cmd.equalsIgnoreCase ("L 5 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Laplacian", "kernel5");
			cardKernel.show (panelKernel, "kernel5");
			isGray = true;
			setTextKernel (ConvKernel.laplacian5, textKernel5);
			setConvolution (5, 5, textKernel5, true);

		}
		else if (cmd.equalsIgnoreCase ("Prewit")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Prewit");
			isGray = true;
			setTextKernel (ConvKernel.prewitt_h3, textKernel3);
			setConvolution (3, 3, textKernel3, true);

		}
		else if ((cmd.equalsIgnoreCase ("Prewit-V 3 Kernel"))
				| (cmd.equalsIgnoreCase ("P-V 3 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Prewit", "kernel3");
			cardKernel.show (panelKernel, "kernel3");
			isGray = true;
			setTextKernel (ConvKernel.prewitt_v3, textKernel3);
			setConvolution (3, 3, textKernel3, true);

		}
		else if ((cmd.equalsIgnoreCase ("Prewit-V 5 Kernel"))
				| (cmd.equalsIgnoreCase ("P-V 5 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Prewit", "kernel5");
			cardKernel.show (panelKernel, "kernel5");
			isGray = true;

		}
		else if ((cmd.equalsIgnoreCase ("Prewit-H 3 Kernel"))
				| (cmd.equalsIgnoreCase ("P-H 3 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Prewit", "kernel3");
			cardKernel.show (panelKernel, "kernel3");
			isGray = true;
			setTextKernel (ConvKernel.prewitt_h3, textKernel3);
			setConvolution (3, 3, textKernel3, true);

		}
		else if ((cmd.equalsIgnoreCase ("Prewit-H 5 Kernel"))
				|| (cmd.equalsIgnoreCase ("P-H 5 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Prewit", "kernel5");
			cardKernel.show (panelKernel, "kernel5");
			isGray = true;

		}
		else if (cmd.equalsIgnoreCase ("Robert")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Robert");
			isGray = true;
			setTextKernel (ConvKernel.robert_h3, textKernel3);
			setConvolution (3, 3, textKernel3, true);

		}
		else if ((cmd.equalsIgnoreCase ("Robert-V 3 Kernel"))
				| (cmd.equalsIgnoreCase ("R-V 3 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Robert", "kernel3");
			cardKernel.show (panelKernel, "kernel3");
			isGray = true;
			setTextKernel (ConvKernel.robert_v3, textKernel3);
			setConvolution (3, 3, textKernel3, true);

		}
		else if ((cmd.equalsIgnoreCase ("Robert-V 5 Kernel"))
				| (cmd.equalsIgnoreCase ("R-V 5 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Robert", "kernel5");
			cardKernel.show (panelKernel, "kernel5");
			isGray = true;

		}
		else if ((cmd.equalsIgnoreCase ("Robert-H 3 Kernel"))
				| (cmd.equalsIgnoreCase ("R-H 3 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Robert", "kernel3");
			cardKernel.show (panelKernel, "kernel3");
			isGray = true;
			setTextKernel (ConvKernel.robert_h3, textKernel3);
			setConvolution (3, 3, textKernel3, true);

		}
		else if ((cmd.equalsIgnoreCase ("Robert-H 5 Kernel"))
				|| (cmd.equalsIgnoreCase ("R-H 5 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Robert", "kernel5");
			cardKernel.show (panelKernel, "kernel5");
			isGray = true;

		}
		else if (cmd.equalsIgnoreCase ("Sobel")) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Sobel");
			isGray = true;
			setTextKernel (ConvKernel.sobel_h3, textKernel3);
			setConvolution (3, 3, textKernel3, true);

		}
		else if ((cmd.equalsIgnoreCase ("Sobel-H 3 Kernel"))
				| (cmd.equalsIgnoreCase ("S-H 3 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Sobel", "kernel3");
			cardKernel.show (panelKernel, "kernel3");
			isGray = true;
			setTextKernel (ConvKernel.sobel_h3, textKernel3);
			setConvolution (3, 3, textKernel3, true);

		}
		else if ((cmd.equalsIgnoreCase ("Sobel-H 5 Kernel"))
				| (cmd.equalsIgnoreCase ("S-H 5 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Sobel", "kernel5");
			cardKernel.show (panelKernel, "kernel5");
			isGray = true;

		}
		else if ((cmd.equalsIgnoreCase ("Sobel-V 3 Kernel"))
				| (cmd.equalsIgnoreCase ("S-V 3 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Sobel", "kernel3");
			cardKernel.show (panelKernel, "kernel3");
			isGray = true;
			setTextKernel (ConvKernel.sobel_v3, textKernel3);
			setConvolution (3, 3, textKernel3, true);

		}
		else if ((cmd.equalsIgnoreCase ("Sobel-V 5 Kernel"))
				| (cmd.equalsIgnoreCase ("S-V 5 Kernel"))) {
			setCardConvolution ();
			setDialogTitle ("CONVOLUTION");
			setCardEdge ("Sobel", "kernel5");
			cardKernel.show (panelKernel, "kernel5");
			isGray = true;

		}
		else if (cmd.equalsIgnoreCase ("BACK")) {
			setCardConvolution ();
			cardConv.show (panelConvCard, "ConvAll");

		}
		else if (cmd.equalsIgnoreCase ("BACK!")) {
			setCardConvolution ();
			cardConv.show (panelConvCard, "Edge");

		}

		// Statistic
		else if (cmd.equalsIgnoreCase ("STATISTIC")) {
			setCardFilter ();
			setDialogTitle ("STATISTIC");
			cardDialog.show (panelDialog, "Statistic");
		}
		else if (cmd.equalsIgnoreCase ("Mean")) {
			setCardFilter ();
			setDialogTitle ("STATISTIC");
			cardDialog.show (panelDialog, "Statistic");

			setMean ();

		}
		else if (cmd.equalsIgnoreCase ("Median")) {
			setCardFilter ();
			setDialogTitle ("STATISTIC");
			cardDialog.show (panelDialog, "Statistic");

			setMedian ();

		}
		else if (cmd.equalsIgnoreCase ("Max")) {
			setCardFilter ();
			setDialogTitle ("STATISTIC");
			cardDialog.show (panelDialog, "Statistic");

			setMaxMin ("Max");

		}
		else if (cmd.equalsIgnoreCase ("Min")) {
			setCardFilter ();
			setDialogTitle ("STATISTIC");
			cardDialog.show (panelDialog, "Statistic");

			setMaxMin ("Min");
		}

		// Segmentation

		else if ((cmd.equalsIgnoreCase ("SEGM"))|((cmd.equalsIgnoreCase("SetRegSamplePixel")))) {
			setCardSegment ("RegGrowSegment");
			
			setDialogTitle ("THRESHOLD SEGMENTATION");
			cardDialog.show (panelDialog, "Segmentation");

			setRegionGrowing ();
		}
		else if (cmd.equalsIgnoreCase ("Start Segmentation")) {
			setCardSegment ("RegGrowSegment");

			if (status == SEEDING) {
				growthThread = new Thread (this);
				growthThread.start ();
			}

		}
		else if (cmd.equalsIgnoreCase ("Re-Seed ")) {
			setCardSegment ("RegGrowSegment");

			if (status == GROWN) {
				panelImageRegGrowSegment.setOverlay (null);
				status = SEEDING;
			}

		}
		else if (cmd.equalsIgnoreCase ("New-Seed")) {
			setCardSegment ("RegGrowSegment");

			if (status != GROWING) {
				seedPixels.clear ();
				panelImageRegGrowSegment.setOverlay (null);
				status = SEEDING;

			}
		}
		else if (cmd.equalsIgnoreCase ("SetEdgeSamplePixel")) {
			setCardSegment ("EdgeSegment");
			setEdgeSegmentation ();
		}


	}

	@Override
	public void stateChanged(ChangeEvent e) {

		Object change = e.getSource ();

		// Brightnest
		if (change == sliderBrightnest) {
			int value = sliderBrightnest.getValue ();
			labelBrightnest.setText ("" + value);
			setBrightnest (value);
		}
		else if (change == sliderContrast_G) {
			valueG = sliderContrast_G.getValue ();
			double gain = valueG / 100.0;
			labelContrast_G.setText ("" + gain);
			setContrast (gain, valueP);
		}
		else if (change == sliderContrast_P) {
			valueP = sliderContrast_P.getValue ();
			labelContrast_P.setText ("" + valueP);
			double gain = valueG / 100.0;
			setContrast (gain, valueP);
		}
		else if (change == sliderBlending) {
			int value = sliderBlending.getValue ();
			double set = value / 100.0;
			labelBlending.setText ("" + set);
			setBlending (set);
		}
		else if (change == sliderSegment) {
			int t = sliderSegment.getValue ();
			labelSegment.setText ("" + t);

		}
	}

	// //////////////////// OPERATION // /////////////////////////////////

	// Open Image
	private void openImage(String home, ImagePanel imagePanel,
			ImagePanel panelPos, ImagePanel panelHis) {

		JFileChooser chooser = new JFileChooser (home);
		chooser.setFileFilter (new javax.swing.filechooser.FileFilter () {
			public final static String jpeg = "jpeg";
			public final String jpg = "jpg";
			public final String gif = "gif";
			public final String tiff = "tiff";
			public final String tif = "tif";
			public final String png = "png";

			/*
			 * Get the extension of a file.
			 */
			public String getExtension(java.io.File f) {

				String ext = null;
				String s = f.getName ();
				int i = s.lastIndexOf ('.');

				if (i > 0 && i < s.length () - 1) {
					ext = s.substring (i + 1).toLowerCase ();
				}
				return ext;
			}

			public String getDescription() {

				return "Images File";
			}

			public boolean accept(java.io.File f) {

				if (f.isDirectory ()) {
					return true;
				}

				String extension = getExtension (f);
				if (extension != null) {
					if (extension.equals (tiff) || extension.equals (tif)
							|| extension.equals (gif)
							|| extension.equals (jpeg)
							|| extension.equals (jpg) || extension.equals (png)) {
						return true;
					}
					else {
						return false;
					}
				}

				return false;
			}

		});

		if (chooser.showDialog (frame, "Open Image") == javax.swing.JFileChooser.APPROVE_OPTION) {

			java.io.File file = chooser.getSelectedFile ();

			String path = file.getPath ();
			// infoLabel.setText("Path Image"+path);
			try {
				BufferedImage img = ImageIO.read (new File (path));

				imagePanel.setBuffImage (img);
				panelPos.add (imagePanel);
				panelHis.setBuffImage (imageModel.getHistogram (imagePanel
						.getBuffImage ()));

			}
			catch (Exception e) {
				JOptionPane.showMessageDialog (frame, "File tidak ditemukan");
			}
		}
	}
	
	private void openImage2() {

		JFileChooser chooser = new JFileChooser ("/home/ziez/Pictures/citra/resource");
		chooser.setFileFilter (new javax.swing.filechooser.FileFilter () {
			public final static String jpeg = "jpeg";
			public final String jpg = "jpg";
			public final String gif = "gif";
			public final String tiff = "tiff";
			public final String tif = "tif";
			public final String png = "png";

			/*
			 * Get the extension of a file.
			 */
			public String getExtension(java.io.File f) {

				String ext = null;
				String s = f.getName ();
				int i = s.lastIndexOf ('.');

				if (i > 0 && i < s.length () - 1) {
					ext = s.substring (i + 1).toLowerCase ();
				}
				return ext;
			}

			public String getDescription() {

				return "Images File";
			}

			public boolean accept(java.io.File f) {

				if (f.isDirectory ()) {
					return true;
				}

				String extension = getExtension (f);
				if (extension != null) {
					if (extension.equals (tiff) || extension.equals (tif)
							|| extension.equals (gif)
							|| extension.equals (jpeg)
							|| extension.equals (jpg) || extension.equals (png)) {
						return true;
					}
					else {
						return false;
					}
				}

				return false;
			}

		});

		if (chooser.showDialog (frame, "Open Image") == javax.swing.JFileChooser.APPROVE_OPTION) {

			java.io.File file = chooser.getSelectedFile ();

			String path = file.getPath ();
			// infoLabel.setText("Path Image"+path);
			try {
				BufferedImage img = ImageIO.read (new File (path));

				panelImageHasil.setBuffImage (img);
				panelHistogramKanan.setBuffImage (imageModel.getHistogram (panelImageHasil
						.getBuffImage ()));

			}
			catch (Exception e) {
				JOptionPane.showMessageDialog (frame, "File tidak ditemukan");
			}
		}
	}

	// Set BlackWhite
	private void setHitamPutih() {

		BufferedImage img = panelImageAsli.getBuffImage ();
		panelImageHasil.setBuffImage (imageModel.getHitamPutih (img));
		panelHistogramKanan.setBuffImage (imageModel
				.getHistogram (panelImageHasil.getBuffImage ()));

	}

	// Set Grayscale
	private void setGrayscale(int pangkat) {

		BufferedImage img = panelImageAsli.getBuffImage ();
		panelImageHasil.setBuffImage (imageModel.getGrayscale (img, pangkat));
		panelHistogramKanan.setBuffImage (imageModel
				.getHistogram (panelImageHasil.getBuffImage ()));
	}

	// Set Grayscale
	private void setBrightnest(int bobot) {

		BufferedImage img = panelImageAsli.getBuffImage ();
		panelImageHasil.setBuffImage (imageModel.getBrightness (img, bobot));
		panelHistogramKanan.setBuffImage (imageModel
				.getHistogram (panelImageHasil.getBuffImage ()));
	}

	// Set Contrast
	private void setContrast(double gain, int p) {

		BufferedImage img = panelImageAsli.getBuffImage ();
		panelImageHasil.setBuffImage (imageModel.getContrast (img, gain, p));
		panelHistogramKanan.setBuffImage (imageModel
				.getHistogram (panelImageHasil.getBuffImage ()));
	}

	// Set Negasi
	private void setNegasi() {

		BufferedImage img = panelImageAsli.getBuffImage ();
		panelImageHasil.setBuffImage (imageModel.getNegasi (img));
		panelHistogramKanan.setBuffImage (imageModel
				.getHistogram (panelImageHasil.getBuffImage ()));
	}

	// Set Motion
	private void setMotion() {
		try{
		BufferedImage img1 = panelImageAsli.getBuffImage ();
		BufferedImage img2 = panelImageHasil.getBuffImage ();
		Boolean state = imageModel.getMotion (img1, img2);
		if (state == false)
			labelMotion.setText ("           OBJEK BERGERAK !           ");
		else
			labelMotion
					.setText ("           OBJEK TIDAK BERGERAK !           ");
		}catch (Exception e) {
			JOptionPane.showMessageDialog (frame, "Image Hasile == null");
		}
	}

	// Set Negasi
	private void setNot() {

		BufferedImage img = panelImageAsli.getBuffImage ();
		panelImageHasil.setBuffImage (imageModel.getNot (img));
		panelHistogramKanan.setBuffImage (imageModel
				.getHistogram (panelImageHasil.getBuffImage ()));
	}

	// Set Logic
	private void setLogic(String set) {

		BufferedImage img1 = panelImageAsli.getBuffImage ();
		BufferedImage img2 = panelImageHasil.getBuffImage ();
		BufferedImage temp = img2;
		panelImageProses.setBuffImage (imageModel.getLogic (img1, temp, set));
		panelImageTengah.add (panelImageProses);

		panelHistogramProses.setBuffImage (imageModel
				.getHistogram (panelImageProses.getBuffImage ()));
	}

	// Set Blending
	private void setBlending(double set) {
try{
		BufferedImage img1 = panelImageAsli.getBuffImage ();
		BufferedImage img2 = panelImageHasil.getBuffImage ();

		panelImageProses
				.setBuffImage (imageModel.getBlending (img1, img2, set));
		panelImageTengah.add (panelImageProses);
		panelHistogramProses.setBuffImage (imageModel
				.getHistogram (panelImageProses.getBuffImage ()));
		}catch (Exception e) {
			JOptionPane.showMessageDialog (frame, "Image Hasile == null");
		}
	}

	// Set Convolve
	private void setTextKernel(double k[][], JTextField kernel[][]) {

		for (int i = 0; i < k.length; i ++) {
			for (int j = 0; j < k[i].length; j ++) {
				kernel[i][j].setText (String.valueOf (k[i][j]));
			}
		}
	}

	private void setTextKernel(int k[][], JTextField kernel[][]) {

		for (int i = 0; i < k.length; i ++) {
			for (int j = 0; j < k[i].length; j ++) {
				kernel[i][j].setText (String.valueOf (k[i][j]));
			}
		}
	}

	private void setConvolution(int kx, int ky, JTextField k[][], boolean isGray) {

		try {
			double kernel[][] = new double[kx][ky];
			for (int i = 0; i < k.length; i ++) {
				for (int j = 0; j < k[i].length; j ++) {

					kernel[i][j] = Double.valueOf (k[i][j].getText ());
				}
			}

			if (isGray == true) {
				BufferedImage gray = imageModel.getGrayscale (
						panelImageAsli.getBuffImage (), 8);
				panelImageHasil.setBuffImage (imageModel.getConvolve (gray,
						kernel));
				panelHistogramKanan.setBuffImage (imageModel
						.getHistogram (panelImageHasil.getBuffImage ()));

			}
			else {
				BufferedImage img = imageModel.getConvolve (
						panelImageAsli.getBuffImage (), kernel);

				panelImageHasil.setBuffImage (img);
				panelHistogramKanan.setBuffImage (imageModel
						.getHistogram (panelImageHasil.getBuffImage ()));
			}
		}
		catch (Exception xxx) {
			JOptionPane.showMessageDialog (frame, "can't convolve");
		}
	}
	
	// set Statistic
	private void setMean() {

		BufferedImage img = panelImageAsli.getBuffImage ();
		panelImageHasil.setBuffImage (imageModel.getMean (img));
		panelHistogramKanan.setBuffImage (imageModel
				.getHistogram (panelImageHasil.getBuffImage ()));
	}

	private void setMedian() {

		BufferedImage img = panelImageAsli.getBuffImage ();
		panelImageHasil.setBuffImage (imageModel.getMedian (img));
		panelHistogramKanan.setBuffImage (imageModel
				.getHistogram (panelImageHasil.getBuffImage ()));
	}

	private void setMaxMin(String m) {

		BufferedImage img = panelImageAsli.getBuffImage ();
		panelImageHasil.setBuffImage (imageModel.getMaxMin (img, m));
		panelHistogramKanan.setBuffImage (imageModel
				.getHistogram (panelImageHasil.getBuffImage ()));

	}

	// Set RegionGrowing
	public void setRegionGrowing() {

		panelImageRegGrowSegment.setEnabled (true);
		panelImageRegGrowSegment.setBuffImage (panelImageAsli.getBuffImage ());
		panelImageRegGrowSegment.addMouseListener (new MouseAdapter () {
			@SuppressWarnings("unchecked")
			public void mouseReleased(MouseEvent event) {

				if (status == SEEDING) {
					Point pixel = event.getPoint ();
					if (!seedPixels.contains (pixel)) {
						seedPixels.add (pixel);

						panelImageRegGrowSegment.repaint ();
					}
				}
			}

		});

	}

	@SuppressWarnings("static-access")
	public void run() {

		status = GROWING;

		RegionGrower regionGrower = new RegionGrower (
				panelImageAsli.getBuffImage (), seedPixels, connectivity,
				sliderSegment.getValue (), true);

		while (regionGrower.isNotFinished ()) {
			try {
				growthThread.sleep (100);
			}
			catch (InterruptedException e) {}
			regionGrower.grow ();
			panelImageRegGrowSegment.setOverlay (regionGrower.getStatusImage ());
			imageRegion = regionGrower.getStatusImage ();
		}

		cardSegment.show (panelImageKanan, "ImageRegion");
		panelImageRegion.setOpaque (true);
		try {
			growthThread.sleep (400);
			panelImageRegion.setBackground (Color.black);
			panelImageRegion.setBuffImage (imageRegion);
			panelHistogramKanan
					.setBuffImage (imageModel.getHistogram (imageRegion));
		}
		catch (InterruptedException e) {}
		
		status = GROWN;
		
	}
	
	// new Segmentation
	
	private void setEdgeSegmentation(){
		panelImageEdgeSegment.setBuffImage (panelImageAsli.getBuffImage ());
		panelImageEdgeSegment.addMouseListener (new MouseAdapter() {
			public void mousePressed(MouseEvent event){
				Point pixel = event.getPoint ();
				
				try {
					Segmentasi segmentasi = new Segmentasi (panelImageEdgeSegment.getBuffImage (), pixel);
					cardSegment.show (panelImageKanan, "ImageRegion");
					panelImageRegion.setOpaque (true);
					panelImageRegion.setBuffImage (segmentasi.getHasilSegmentasi ());
				}catch (Exception e) {
					System.out.println(e);
				}
			}
		});
	}
	
	/*
    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
//        		panTengah.setVisible(false);
            pilihArea =new JDialog(this);
            pilihArea.setLayout(new BorderLayout());
                    final fungsiMenampilkangambarKePanel segmentPanel=new fungsiMenampilkangambarKePanel();
                    segmentPanel.setBuffImage(pictBuffer, true);
                    segmentPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                            // TODO Auto-generated method stub
                            super.mousePressed(e);
                            Point kordinat=segmentPanel.getMousePosition();
                            try {
                                    System.out.println("masuk try koordinat= "+kordinat);
                                    pilihArea.dispose();
                                    JOptionPane.showMessageDialog(pilihArea, "Silahkan tunggu pak");
                                    segmentasi saya = new segmentasi(pictBuffer, kordinat);
                                    System.out.println("masuk saya 2 "+kordinat+" proses di segmentasi selesai");
                                    fungsiMenampilkangambarKePanel2.setBuffImage(saya.getHasilSegmentasi());                                    
//						sulit=new segmentasi(pictBuffer, kordinat);
//						fungsiMenampilkangambarKePanel2.setBuffImage(sulit.getHasilSegmentasi());
                            } catch (IOException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(pilihArea, "proses segmentasi selesai");
                            pilihArea.dispose();
                    }
                    });*/

	// MAIN
	public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            new MainFrame();
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

	}

}
