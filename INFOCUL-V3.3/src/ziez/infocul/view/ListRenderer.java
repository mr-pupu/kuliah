package ziez.infocul.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import ziez.infocul.model.Alamat;
import ziez.infocul.model.MenuWarung;
import ziez.infocul.model.Warung.Status;

@SuppressWarnings("rawtypes")
public class ListRenderer extends JPanel implements ListCellRenderer {

	private static final long serialVersionUID = 1L;
	private Adapter binding;
	private final Border noFocusBorder;
	private Color evenRowColor;
	private Color oddRowColor;
	private Color alamatColor;
	private Icon blankStatusIcon;
	private Icon redStatusIcon;
	private Icon greenStatusIcon;
	private ImageIcon defaultWarungIcon;
	private ImageIcon defaultOfflineWarungIcon;
	private final Map<ImageIcon, ImageIcon> warungBukaIcons;
	private final Map<ImageIcon, ImageIcon> warungTutupIcons;
	private JLabel lblStatusWarung;
	private JLabel lblNamaWarung;
	private JLabel lblAlamatWarung;
	private JLabel lblWarung;
	private StarRater starRater;
	private Dimension warungIconSize = new Dimension(120, 90);
	private final Insets zeroInsets = new Insets(0, 0, 0, 0);


	public Component getListCellRendererComponent(JList jList, Object value,
			int index, boolean isSelected, boolean hasFocus) {

		setComponentOrientation(jList.getComponentOrientation());

		if (isSelected) {
			setBackground(jList.getSelectionBackground());
			setForeground(jList.getSelectionForeground());
		} else {
			setBackground(((index & 0x1) == 1) ? evenRowColor : oddRowColor);
			setForeground(jList.getForeground());
		}

		Border border = null;
		if (hasFocus) {
			if (isSelected) {
				border = UIManager
						.getBorder("List.focusSelectedCellHighlightBorder");
			}
			if (border == null) {
				border = UIManager.getBorder("List.focusCellHighlightBorder");
			}
		} else {
			border = noFocusBorder;
		}
		setBorder(border);

		Adapter adapter = getAdapter(value);
		setToolTipText(adapter.getToolTipText());

		switch (adapter.getStatus()) {
			case BUKA:
				lblStatusWarung.setIcon(greenStatusIcon);
				break;
			case TUTUP:
				lblStatusWarung.setIcon(redStatusIcon);
				break;
			default:
				lblStatusWarung.setIcon(blankStatusIcon);
		}
		
		lblStatusWarung.setBackground(getBackground());

		String namaWarung = adapter.getNamaWarung();

		lblNamaWarung.setText((namaWarung == null) ? "" : namaWarung);
		lblNamaWarung.setForeground(getForeground());
		lblNamaWarung.setEnabled(adapter.getStatus() != Status.KUKUT);

		String alamat = adapter.getAlamat().toString();
		GridBagLayout l = (GridBagLayout) getLayout();
		GridBagConstraints c = l.getConstraints(lblNamaWarung);
		c.gridheight = 1;
		l.setConstraints(lblNamaWarung, c);
		lblAlamatWarung.setVisible(true);
		lblAlamatWarung.setText(alamat);

		Icon warungIcon = (adapter.getStatus() == Status.KUKUT) ? statusIcon(adapter
				.getIconWarung()) : onlineBuddyIcon(adapter.getIconWarung());

		lblWarung.setIcon(warungIcon);
		lblWarung.setBackground(getBackground()); 
		
		starRater.setBackground(getBackground());
		starRater.setRating(adapter.getRating());
		

		return this;
	}

	public Dimension getBuddyIconSize() {
		return new Dimension(warungIconSize);
	}

	public void setBuddyIconSize(Dimension size) {
		if ((size == null) || (size.width < 0) || (size.height < 0)) {
			throw new IllegalArgumentException("invalid size");
		}
		this.warungIconSize = new Dimension(size);
	}

	private ImageIcon onlineBuddyIcon(ImageIcon adapterBuddyIcon) {
		if (adapterBuddyIcon == null) {
			return defaultWarungIcon;
		} else {
			ImageIcon buddyIcon = warungBukaIcons.get(adapterBuddyIcon);
			if (buddyIcon != null) {
				return buddyIcon;
			} else {
				Dimension maxIconSize = getBuddyIconSize();
				int iconWidth = adapterBuddyIcon.getIconWidth();
				int iconHeight = adapterBuddyIcon.getIconHeight();
				if ((iconWidth > maxIconSize.width)
						|| (iconHeight > maxIconSize.height)) {
					double xScale = maxIconSize.getWidth() / (double) iconWidth;
					double yScale = maxIconSize.getHeight()
							/ (double) iconHeight;
					double scale = Math.min(xScale, yScale);
					int scaledWidth = (int) (scale * (double) iconWidth);
					int scaledHeight = (int) (scale * (double) iconHeight);
					int flags = Image.SCALE_SMOOTH;
					Image scaledBuddyImage = adapterBuddyIcon
							.getImage()
							.getScaledInstance(scaledWidth, scaledHeight, flags);
					buddyIcon = new ImageIcon(scaledBuddyImage);
				}
				warungBukaIcons.put(adapterBuddyIcon, buddyIcon);
				return buddyIcon;
			}
		}
	}

	private ImageIcon createStatusIcon(ImageIcon icon) {
		return new ImageIcon(GrayFilter.createDisabledImage(icon.getImage()));
	}

	ImageIcon statusIcon(ImageIcon adapterStatusIcon) {
		if (adapterStatusIcon == null) {
			return defaultOfflineWarungIcon;
		} else {
			ImageIcon buddyIcon = warungTutupIcons.get(adapterStatusIcon);
			if (buddyIcon != null) {
				return buddyIcon;
			} else {
				ImageIcon onlineBuddyIcon = onlineBuddyIcon(adapterStatusIcon);
				buddyIcon = createStatusIcon(onlineBuddyIcon);
				warungTutupIcons.put(adapterStatusIcon, buddyIcon);
				return buddyIcon;
			}
		}
	}

	private void initGridBagConstraints(GridBagConstraints c) {
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = GridBagConstraints.RELATIVE;
		c.gridy = GridBagConstraints.RELATIVE;
		c.insets = zeroInsets;
		c.ipadx = 5;
		c.ipady = 10;
		c.weightx = 0.0;
		c.weighty = 0.0;
	}

	private ImageIcon loadIcon(String name) {
		return new ImageIcon(getClass().getResource("/resources/" + name));
	}

	public ListRenderer(Adapter binding) {
		super(new GridBagLayout());
		this.binding = binding;
		warungBukaIcons = new HashMap<ImageIcon, ImageIcon>();
		warungTutupIcons = new HashMap<ImageIcon, ImageIcon>();

		noFocusBorder = new EmptyBorder(1, 1, 1, 1);
		setBorder(noFocusBorder);
		evenRowColor = Color.white;
		oddRowColor = new Color(237, 243, 255);
		alamatColor = new Color(30, 17, 33);
		redStatusIcon = loadIcon("red-led.png");
		greenStatusIcon = loadIcon("green-led.png");
		blankStatusIcon = new BlankIcon(greenStatusIcon);
		defaultWarungIcon = loadIcon("default-icon.png");
		defaultOfflineWarungIcon = createStatusIcon(defaultWarungIcon);

		lblStatusWarung = new JLabel(greenStatusIcon);
		lblNamaWarung = new JLabel("Nama Warung");
		lblAlamatWarung = new JLabel("Alamat");
		lblWarung = new JLabel(defaultWarungIcon);

		lblWarung.setPreferredSize(new Dimension(90, 80));

		Font f = lblNamaWarung.getFont();
		lblNamaWarung.setFont(f.deriveFont(Font.BOLD, f.getSize() + 4f));
		lblAlamatWarung.setForeground(alamatColor);

		starRater = new StarRater(5, 2, 1);

		GridBagConstraints c = new GridBagConstraints();

		initGridBagConstraints(c);
		c.gridheight = 3;
		c.insets = new Insets(0, 6, 0, 10); // top, left, bottom, right;
		add(lblWarung, c);

		initGridBagConstraints(c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.ipadx = 10;
		c.insets = new Insets(4, 0, 0, 0); // top, left, bottom, right;
		add(lblNamaWarung, c);

		initGridBagConstraints(c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 10;
		c.insets = new Insets(0, 0, 0, 0); // top, left, bottom, right;
		add(lblAlamatWarung, c);

		initGridBagConstraints(c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = 1;
		c.gridy = 2;
		c.ipadx = 15;
		c.insets = new Insets(0, 0, 4, 0); // top, left, bottom, right;
		add(starRater, c);

		initGridBagConstraints(c);
		c.gridheight = 3;
		c.insets = new Insets(0, 6, 0, 7); // top, left, bottom, right;
		add(lblStatusWarung, c);

	}

	public ListRenderer() {
		this(new Adapter());
	
	}

	private static class BlankIcon implements Icon {

		private final int width, height;

		public BlankIcon(Icon icon) {
			this.width = icon.getIconWidth();
			this.height = icon.getIconHeight();
		}

		public int getIconWidth() {
			return width;
		}

		public int getIconHeight() {
			return height;
		}

		public void paintIcon(Component c, Graphics g, int x, int y) {
			g.setColor(c.getBackground());
			g.fillRect(x, y, getIconWidth(), getIconHeight());
		}
	}

	public StarRater getStarRater() {
		return starRater;
	}

	public void setStarRater(StarRater starRater) {
		this.starRater = starRater;
	}

	public Adapter getAdapter() {
		return binding;
	}

	public void setAdapter(Adapter binding) {
		if (binding == null) {
			throw new IllegalArgumentException("null binding");
		}
		this.binding = binding;
	}

	public final Adapter getAdapter(Object value) {
		Adapter adapter = getAdapter();
		adapter.setValue(value);
		return adapter;
	}

	public static class Adapter {

		private Object value;

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public String getNamaWarung() {
			return " ";
		}

		public Status getStatus() {
			return Status.BUKA;
		}

		public Alamat getAlamat() {
			return null;
		}

		public MenuWarung[] getMenu() {
			return null;
		}

		public ImageIcon getIconWarung() {
			return null;
		}

		public String getToolTipText() {
			return getNamaWarung() + " " + getStatus();
		}

		public float getRating() {

			return 0;
		}

	}
}
