package GUI;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;


import javax.swing.*;

import SearchFile.BanqueImage;


public class OptionView extends JPanel {
	

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
		this.setOpaque(false);
        this.view = view;
        this.launcher = launcher;
		this.setOpaque(false);
		
		
		ImageIcon imic = new ImageIcon(BanqueImage.images[2]);
		icon = new JLabel(imic);
		

		int icWidth = BanqueImage.images[2].getWidth(null);
		int icHeight = BanqueImage.images[2].getHeight(null);

		icon.setBounds(5, 10, icWidth, icHeight);

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
		container.setOpaque(false);

		BanqueImage.images[7]=BanqueImage.images[7].getScaledInstance(300,200,Image.SCALE_SMOOTH);


        abandonner = new JLabel("Abandonner",SwingConstants.CENTER);
		abandonner.setFont(new Font("Dyuthi",Font.PLAIN,30));
		abandonner.setForeground(new Color(173, 103, 53));
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
				abandonner.setForeground(new Color(173, 103, 53));
			}
        });

		abandonner.setVisible(false);

        replay = new JLabel("Rejouer",SwingConstants.CENTER);
		replay.setFont(new Font("Dyuthi",Font.PLAIN,30));
		replay.setForeground(new Color(173, 103, 53));

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
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.drawImage(BanqueImage.images[7],0,0,null);
		//Draws the rounded opaque panel with borders.
		//graphics.setColor(getBackground());
		//graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
		//graphics.setColor(getForeground());
		//graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
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
