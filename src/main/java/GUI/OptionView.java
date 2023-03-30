package GUI;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class OptionView extends JPanel {
	int icHeight =0;
	int icWidth =0;

    View view;
    JFrame launcher;

	boolean deployer = true;
	JPanel container;
	JLabel icon;
    JLabel abandonner;
    JLabel replay;
    JLabel quitter;

    public OptionView(View view, JFrame launcher){
        this.setLayout(null);
		this.setOpaque(true);
		this.setBorder(new MyBorder());
        this.view = view;
        this.launcher = launcher;
		Image iconImage;
		try {
			iconImage = ImageIO.read(new File("src/ressource/iconDerouler.png"));
			icWidth = iconImage.getWidth(this)/3;
			icHeight = iconImage.getHeight(this)/3;
			iconImage = iconImage.getScaledInstance(icWidth, icHeight, Image.SCALE_FAST);

			ImageIcon imic = new ImageIcon(iconImage);
			icon = new JLabel(imic);
		} catch (IOException e) {
			System.out.println("Image non trouve dans OptionView");
		}
		icon.setBounds(0, 0, icWidth, icHeight);

		icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.deployerPanneau(deployer);
				deployer = !deployer;
				OptionView.this.setOpaque(deployer);
				
            }
		});

		
		container = new JPanel();
		container.setLayout(new GridLayout(2,1));
		container.setBounds(icWidth, 0, 300- icWidth, 200);
		container.setBorder(new MyBorder());


        abandonner = new JLabel("Abandonner");
		abandonner.setFont(new Font("Impact",Font.PLAIN,30));
		abandonner.setForeground(Color.GRAY);
		abandonner.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.dispose();
				launcher.setVisible(true);
            }

			@Override
			public void mouseEntered(MouseEvent e){
				view.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				abandonner.setForeground(Color.RED);
			}

			@Override
			public void mouseExited(MouseEvent e){
				view.setCursor(Cursor.getDefaultCursor());
				abandonner.setForeground(Color.GRAY);
			}
        });

		abandonner.setVisible(false);



        replay = new JLabel("Rejouer");
		replay.setFont(new Font("Impact",Font.PLAIN,30));
		replay.setForeground(Color.GRAY);

		replay.setVisible(false);
		container.setVisible(true);
		icon.setVisible(true);
       container.add(abandonner);
       container.add(replay);
	   this.add(icon);
	   this.add(container);
    }

	public JLabel getReplayLabel(){
		return replay;
	}

	public void visibility(boolean b){
		this.replay.setVisible(b);
		this.abandonner.setVisible(b);
	}
}
