package GUI;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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
	Image iconImage;	
	BufferedImage iconIm;

    public OptionView(View view, JFrame launcher){
        this.setLayout(null);
		this.setOpaque(true);
        this.view = view;
        this.launcher = launcher;
		this.setOpaque(false);
		
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
                deployerPanneau(deployer);
				deployer = !deployer;
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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension arcs = new Dimension(15,15);
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


		//Draws the rounded opaque panel with borders.
		graphics.setColor(getBackground());
		graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
		graphics.setColor(getForeground());
		graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
	}

	public void visibility(boolean b){
		this.replay.setVisible(b);
		this.abandonner.setVisible(b);
	}

	public void deployerPanneau(boolean ouverture){
		Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle winSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

		int taskBarWidth = scrnSize.width - winSize.width;
		Timer vibe = new Timer();
		if(ouverture){
			this.visibility(true);
		}
		vibe.schedule(new TimerTask() {
			int time = 27-taskBarWidth/9;
			public void run() {
				if(ouverture){
					OptionView.this.setBounds(OptionView.this.getX()-10,OptionView.this.getY(), OptionView.this.getWidth(), OptionView.this.getHeight());
				}
				else{
					OptionView.this.setBounds(OptionView.this.getX()+10,OptionView.this.getY(), OptionView.this.getWidth(), OptionView.this.getHeight());
				}
				

				if(time == 0){
					cancel();
					
				}
				time--;
			}
		},0,10);
		if(!ouverture) OptionView.this.visibility(false);
		OptionView.this.repaint();
	}
}
