package ziez.infocul.view;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ziez.infocul.model.Alamat;
import ziez.infocul.model.Data;
import ziez.infocul.model.MenuWarung;
import ziez.infocul.model.Warung;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;

public class PanelForm extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private GridBagConstraints gbc;
	private JTextField txtMenuNama; 
	private JTextField txtMenuHarga ;
	private JTextField txtAlamatJalan; 
	private JTextField txtAlamatKota;
	private JTextField txtNamaWarung;
	private JLabel lblIconWarung;
	
	private Warung warung;
    private List<MenuWarung> menuWarung;
    private int rating; 
    private JButton btnSubmit;
    
	public PanelForm(){
		setLayout(new GridBagLayout());
		setBorder(BorderFactory.createTitledBorder(""));
		buildComponent();
		warung = new Warung();
		btnSubmit.setVisible(false);
        menuWarung = new ArrayList<>();
	
	}
	
	public Warung getWarung() {
		return warung;
	}
	public void setWarung(Warung warung) {
		this.warung = warung;
	}
	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	private void buildComponent(){
		JLabel lblNamaWarung = new JLabel();
		JLabel lblAlamat = new JLabel();
		JLabel lblMenu = new JLabel();
		JLabel lblJamBuka = new JLabel();
		JLabel lblJamTutup = new JLabel();
		JLabel lblRating = new JLabel();
		lblIconWarung = new JLabel();
		
		txtNamaWarung = new JTextField();
		txtAlamatJalan = new JTextField();
		txtAlamatKota = new JTextField();
		txtMenuNama = new JTextField();
		txtMenuHarga = new JTextField();
		JTextField txtJamBuka = new JTextField();
		JTextField txtJamTutup = new JTextField();
		
		JButton btnTambahMenu = new JButton();
		btnSubmit = new JButton();
		
		StarRater starRater = new StarRater(5, 3f);
		
		
		lblNamaWarung.setText("Nama Warung");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        add(lblNamaWarung, gbc);

        txtNamaWarung.setText("");
        txtNamaWarung.setPreferredSize(new Dimension(100,28));
        gbc = new GridBagConstraints();
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 15, 10, 0);
        add(txtNamaWarung, gbc);

        lblAlamat.setText("Alamat");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        add(lblAlamat, gbc);

        txtAlamatJalan.setText("");
        txtAlamatJalan.setPreferredSize(new Dimension(100,28));
        gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 2.0;
        gbc.insets = new Insets(0, 15, 10, 5);
        add(txtAlamatJalan, gbc);

        txtAlamatKota.setText("");
        txtAlamatKota.setPreferredSize(new Dimension(100,28));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(txtAlamatKota, gbc);

        lblMenu.setText("Menu");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        add(lblMenu, gbc);

        txtMenuNama.setText("Nama");
        txtMenuNama.setPreferredSize(new Dimension(100,28));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 15, 10, 5);
        add(txtMenuNama, gbc);

        txtMenuHarga.setText("Harga");
        txtMenuHarga.setPreferredSize(new Dimension(100,28));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(txtMenuHarga, gbc);

        btnTambahMenu.setText("+");
        btnTambahMenu .setPreferredSize(new Dimension(45, 28));
        btnTambahMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAddMenuActionPerformed(evt);
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 5, 10, 0);
        add(btnTambahMenu, gbc);

        lblJamBuka.setText("Jam Buka");
        gbc = new GridBagConstraints();
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        add(lblJamBuka, gbc);

        txtJamBuka .setText("08:00");
        txtJamBuka.setPreferredSize(new Dimension(100,28));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 15, 10, 10);
        add(txtJamBuka, gbc);

        txtJamTutup.setText("22:00");
        txtJamTutup.setPreferredSize(new Dimension(100,28));
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 2.0;
        gbc.insets = new Insets(0, 10, 10, 0);
        add(txtJamTutup, gbc);

        lblJamTutup.setText("-");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(lblJamTutup, gbc);

        
        lblIconWarung.setPreferredSize(new Dimension(200, 18));
        lblIconWarung.setIcon(new ImageIcon(getClass().getResource("/resources/images/default.jpg")));
        lblIconWarung.setBorder(BorderFactory.createEtchedBorder());
        lblIconWarung.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                lblIconWarungMouseClicked(evt);
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.RELATIVE;
        gbc.gridheight = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 10, 10, 0);
        add(lblIconWarung, gbc);

        
        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LAST_LINE_END;
        add(btnSubmit, gbc);

        lblRating.setText("Rating :");
        gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 4;
        add(lblRating, gbc);
        
        starRater.setPreferredSize(new Dimension(210, 35));
        starRater.addStarListener(new StarRater.StarListener() {
			
			public void handleSelection(int selection) {
				setRating(selection);
			}
		});
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 10, 0, 0);
        add(starRater, gbc);
	
	}
	
	private void btnAddMenuActionPerformed(java.awt.event.ActionEvent evt) {
        menuWarung.add(new MenuWarung(txtMenuNama.getText(), txtMenuHarga.getText()));
        txtMenuNama.setText("");
        txtMenuHarga.setText("");
    }
	
	private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {                                      
        List<Warung> listWarung = Data.dataWarung;

        final Geocoder geocoder = new Geocoder();
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(txtAlamatJalan.getText()).setLanguage("en").getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
        List<GeocoderResult> results = geocoderResponse.getResults();

        warung.setNamaWarung(txtNamaWarung.getText());
        warung.setAlamat(new Alamat(50, txtAlamatJalan.getText(),
                txtAlamatKota.getText(), "" + results.get(0).getGeometry().getLocation().getLat(), "" + results.get(0).getGeometry().getLocation().getLng()));
        menuWarung.add(new MenuWarung(txtMenuNama.getText(), txtMenuHarga.getText()));

        MenuWarung[] menu = new MenuWarung[menuWarung.size()];

        for (int i = 0; i < menuWarung.size(); i++) {
            menu[i] = menuWarung.get(i);
        }
        warung.setMenu(menu);
        if (warung.getIconWarungURL() == null) {
            warung.setIconWarungURL(PanelForm.class.getResource("/resources/images/default.jpg"));
        }
        listWarung.add(warung);


    } 
	
	private void lblIconWarungMouseClicked(java.awt.event.MouseEvent evt) {
        openImage();
    }
	
	 @SuppressWarnings("deprecation")
	private void openImage() {

	        JFileChooser chooser = new JFileChooser("/home/ziez/Pictures/");
	        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
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
	                String s = f.getName();
	                int i = s.lastIndexOf('.');

	                if (i > 0 && i < s.length() - 1) {
	                    ext = s.substring(i + 1).toLowerCase();
	                }
	                return ext;
	            }

	            public String getDescription() {
	                return "Images File";
	            }

	            public boolean accept(java.io.File f) {

	                if (f.isDirectory()) {
	                    return true;
	                }

	                String extension = getExtension(f);
	                if (extension != null) {
	                    if (extension.equals(tiff) || extension.equals(tif)
	                            || extension.equals(gif)
	                            || extension.equals(jpeg)
	                            || extension.equals(jpg) || extension.equals(png)) {
	                        return true;
	                    } else {
	                        return false;
	                    }
	                }

	                return false;
	            }
	        });

	        if (chooser.showDialog(this, "Open Image") == javax.swing.JFileChooser.APPROVE_OPTION) {

	            java.io.File file = chooser.getSelectedFile();

	            String path = file.getPath();
	            // infoLabel.setText("Path Image"+path);
	            Image ii = null;
	            try {
	                ii = ImageIO.read(new File(path));
	                warung.setIconWarungURL(file.getAbsoluteFile().toURL());
	                lblIconWarung.setIcon(new ImageIcon(ii.getScaledInstance(lblIconWarung.getWidth(),
	                        lblIconWarung.getHeight(), Image.SCALE_SMOOTH)));
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	 }
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 600, 300);
		
		frame.add(new PanelForm());
		frame.pack();
		frame.setVisible(true);
	}

}






















