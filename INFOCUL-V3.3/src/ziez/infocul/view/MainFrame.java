package ziez.infocul.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ziez.infocul.model.Alamat;
import ziez.infocul.model.Data;
import ziez.infocul.model.MenuWarung;
import ziez.infocul.model.Warung;
import ziez.infocul.model.Warung.Status;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JWebBrowser browser;
    private JPanel panelCardMap;
    private CardLayout cardMap;
    private FilteredJList listWarung;
    private PanelForm panelForm;

    public MainFrame() {
        // SUPER-CLASS CONSTRUCTOR
        super("PBO-INFOCUL/ AZIZ-04297/ V-3.3");

        setMinimumSize(new Dimension(Toolkit.getDefaultToolkit()
                .getScreenSize().width, Toolkit.getDefaultToolkit()
                .getScreenSize().height));

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        cardMap = new CardLayout();
       browser = new JWebBrowser();
        panelCardMap = new JPanel(cardMap);
        listWarung = new FilteredJList();
        panelForm = new PanelForm();
        getContentPane().add(buildContent());

    }

    // COMPONENT BUILDER
    private JComponent buildContent() {
        JPanel panelContainer = new JPanel(new BorderLayout());
        panelContainer.add(buildContentHeader(), BorderLayout.NORTH);
        panelContainer.add(buildContentCenter(), BorderLayout.CENTER);
        panelContainer.add(buildContentFooter(), BorderLayout.SOUTH);

        return panelContainer;
    }

    private JComponent buildContentHeader() {
        JPanel panelHeader = new JPanel(new BorderLayout());

        panelHeader.setPreferredSize(new Dimension(300, 30));

        return panelHeader;
    }

    private JComponent buildContentCenter() {
        JPanel panelCenter = new JPanel(new BorderLayout());

        panelCenter.add(buildPanelMap(), BorderLayout.CENTER);
        panelCenter.add(buildPanelList(), BorderLayout.EAST);

        return panelCenter;
    }

    private JComponent buildPanelMap() {
       
    	final JPanel panelMap = new JPanel(new BorderLayout());
        panelMap.setBorder(BorderFactory.createEtchedBorder());
        new Thread("MapLoader") {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                     //   browser = new JWebBrowser();

                        browser.navigate(MainFrame.class.getResource(
                                "/resources/html/Gmap.html").toString());

                        panelMap.add(browser, BorderLayout.CENTER);

                        browser.setMenuBarVisible(false);
                        browser.setButtonBarVisible(false);
                        browser.setLocationBarVisible(false);
                        browser.setStatusBarVisible(false);

                    }
                });
            }
        }.start();

        JPanel panelAddWarung = new JPanel(new BorderLayout());
        JPanel panelFormButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        final JPanel panelShowMap = new JPanel(new BorderLayout());
        panelShowMap.setPreferredSize(new Dimension(300, 430));
        
        new Thread("MapLoaders") {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JWebBrowser browserMini = new JWebBrowser();

                        browserMini.navigate(MainFrame.class.getResource(
                                "/resources/html/Gmap.html").toString());

                        panelShowMap.add(browserMini, BorderLayout.CENTER);

                        browserMini.setMenuBarVisible(false);
                        browserMini.setButtonBarVisible(false);
                        browserMini.setLocationBarVisible(false);
                        browserMini.setStatusBarVisible(false);

                    }
                });
            }
        }.start();
               
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setPreferredSize(new Dimension(100, 28));
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setPreferredSize(new Dimension(100, 28));
        JLabel lblSeparator = new JLabel("");
        lblSeparator.setPreferredSize(new Dimension(440, 28));
       
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelForm.getBtnSubmit().doClick();
                cardMap.show(panelCardMap, "panelMap");
                Warung w = panelForm.getWarung();
                w.setRating(panelForm.getRating());
                w.setStatus(Status.BUKA);
              //  browser.setHTMLContent(HtmlLoader.oneMarker(w.getAlamat().getLatitude(), w.getAlamat().getLongitude(), w.getNamaWarung()));
                browser.setHTMLContent(HtmlLoader.showAndLoadMap(w.getAlamat().getLatitude(), w.getAlamat().getLongitude(), w.getNamaWarung()));
                listWarung.addItem(w);
                
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardMap.show(panelCardMap, "panelMap");
            }
        });

        panelFormButton.add(lblSeparator);
        panelFormButton.add(btnCancel);
        panelFormButton.add(btnSubmit);
     
        panelAddWarung.add(panelShowMap, BorderLayout.NORTH);
        panelAddWarung.add(panelForm, BorderLayout.CENTER);
        panelAddWarung.add(panelFormButton, BorderLayout.SOUTH);


        panelCardMap.setBorder(BorderFactory.createEtchedBorder());
        panelCardMap.add(panelMap, "panelMap");
        panelCardMap.add(panelAddWarung, "panelAddWarung");
        return panelCardMap;
    }

   

    @SuppressWarnings("unchecked")
    private JComponent buildPanelList() {
        final CardLayout cardLayout = new CardLayout();
        final JPanel panelCardList = new JPanel(cardLayout);
        panelCardList.setPreferredSize(new Dimension(450, 900));

        // buildListContent
        JPanel panelList = new JPanel(new BorderLayout(10, 5));
        //listContent
        JScrollPane scrollPanelList = new JScrollPane();

        final ListRenderer listRnder = new ListRenderer(
                new WarungAdapter());
        listWarung.setCellRenderer(listRnder);
        for (Warung w : Data.allData()) {
            listWarung.addItem(w);
        }
        scrollPanelList.getViewport().add(listWarung);

        //panelSearching
        JPanel panelSearching = new JPanel(new FlowLayout());
        panelSearching.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
                Color.LIGHT_GRAY));
        JLabel lblSearch = new JLabel(new ImageIcon(
                MainFrame.class.getResource("/resources/Search.png")));
        JTextField txtSearch = listWarung.getFilterField();

        panelSearching.add(lblSearch);
        panelSearching.add(txtSearch);
        panelList.add(panelSearching, BorderLayout.NORTH);
        panelList.add(scrollPanelList, BorderLayout.CENTER);
        // end buildListContent	

        // buildListContentDetail
        JPanel panelListDetail = new JPanel(new BorderLayout());
        //slidePanel
        final JPanel panelSlide = new JPanel(new BorderLayout());
        panelSlide.setPreferredSize(new Dimension(430, 300));
        panelSlide.setBorder(BorderFactory.createTitledBorder("Bakso Pak Brengos"));
        final JLabel labelSlide = new JLabel();
        panelSlide.add(labelSlide, BorderLayout.CENTER);

        JPanel panelTableMenu = new JPanel(new BorderLayout());
        panelTableMenu.setBorder(BorderFactory.createTitledBorder("Menu"));

        //tableMenu


        String[] header = {"Menu", "Harga"};
        Object[][] menu = {
            {"Soto", "8000"},
            {"Kodok", "5000"}
        };
        DefaultTableModel tableModel = new DefaultTableModel(menu,
                header);
        final JTable tableMenu = new JTable(tableModel);

        tableMenu.setRowHeight(25);
        JScrollPane scrollTable = new JScrollPane(tableMenu);
        panelTableMenu.add(scrollTable, BorderLayout.CENTER);

        //panelButon
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton btnCardBack = new JButton("Back");
        btnCardBack.setPreferredSize(new Dimension(80, 30));
        final JTextField txtDirection = new JTextField();
        txtDirection.setPreferredSize(new Dimension(250, 30));
        JButton btnDirection = new JButton("GO ->");
        btnDirection.setPreferredSize(btnCardBack.getPreferredSize());
        panelButton.add(btnCardBack);
        panelButton.add(txtDirection);
        panelButton.add(btnDirection);
        panelListDetail.add(panelSlide, BorderLayout.NORTH);
        panelListDetail.add(panelTableMenu, BorderLayout.CENTER);
        panelListDetail.add(panelButton, BorderLayout.SOUTH);
        // end buildListContentDetail


        listWarung.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                cardLayout.show(panelCardList, "panelListDetail");
                final Warung w = (Warung) listWarung.getSelectedValue();

                if (!e.getValueIsAdjusting()) {
                	browser.setHTMLContent(HtmlLoader.showAndLoadMap(w.getAlamat().getLatitude(),
                			w.getAlamat().getLongitude(),w.getNamaWarung()));
                    Image ii = null;
                    try {
                        ii = ImageIO.read(w.getIconWarungURL());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    panelSlide.setBorder(BorderFactory.createTitledBorder(w.getNamaWarung()));
                    labelSlide.setIcon(new ImageIcon(ii.getScaledInstance(labelSlide.getWidth(), labelSlide.getHeight(),
                            Image.SCALE_SMOOTH)));
                    fromDirection = w.getAlamat().getLatlang();

                    Object[][] wMenu = new String[w.getMenu().length][2];

                    for (int i = 0; i < w.getMenu().length; i++) {

                        String mM = w.getMenu()[i].getNama();
                        String mH = w.getMenu()[i].getHarga();

                        wMenu[i][0] = mM;
                        wMenu[i][1] = "Rp. " + mH;
                    }

                    tableMenu.setModel(setDefaultTableModel(wMenu));
                }
            }
        });


        btnCardBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelCardList, "panelList");
            }
        });

        btnDirection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browser.setHTMLContent(HtmlLoader.goDirection(fromDirection, txtDirection.getText()));

            }
        });

        panelCardList.add(panelList, "panelList");
        panelCardList.add(panelListDetail, "panelListDetail");
        panelCardList.setBorder(BorderFactory.createEtchedBorder());

        return panelCardList;
    }

    private JComponent buildContentFooter() {
        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFooter.setPreferredSize(new Dimension(300, 60));

        JButton jb = new JButton("New");
        panelFooter.add(jb);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardMap.show(panelCardMap, "panelAddWarung");
            }
        });

        return panelFooter;
    }

    private TableModel setDefaultTableModel(Object[][] menu) {
        String[] header = {"Menu", "Harga"};

        DefaultTableModel tableModel = new DefaultTableModel(menu,
                header);
        return tableModel;
    }

    // INNERCLASS
    private static class WarungAdapter extends ListRenderer.Adapter {

        private final Map<URL, ImageIcon> iconCache = new HashMap<URL, ImageIcon>();

        private Warung getWarung() {
            return (Warung) getValue();
        }

        public String getNamaWarung() {
            return getWarung().getNamaWarung();
        }

        public Status getStatus() {
            return getWarung().getStatus();
        }

        @Override
        public Alamat getAlamat() {
            return getWarung().getAlamat();
        }

        @Override
        public MenuWarung[] getMenu() {
            return getWarung().getMenu();
        }

        @Override
        public ImageIcon getIconWarung() {
            URL url = getWarung().getIconWarungURL();
            if (url == null) {
                return null;
            }
            ImageIcon icon = iconCache.get(url);
            if (icon != null) {
                return icon;
            }
            icon = new ImageIcon(url);
            iconCache.put(url, icon);
            return icon;
        }

        public float getRating() {
            return getWarung().getRating();
        }
    }
    protected String fromDirection = "";
  }
