package GUI;
import SearchFile.BanqueImage;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuNiveaux extends JPanel {

    private JLabel retour;
    JFrame fenetre;

    JPanel content;

    JPanel labels;


    public MenuNiveaux (int nbLignes,JFrame fen){
      fenetre = fen;

      this.setOpaque(false);
      this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

      labels=new JPanel(){
          @Override
          protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              g.drawImage(BanqueImage.images[10],0,0,null);
          }
      };
      labels.setLayout(new BorderLayout());

      labels.setOpaque(false);


      retour = new JLabel("  Retour",SwingConstants.CENTER);
      retour.setBounds(10,27,100,50);
      retour.setVerticalAlignment(SwingConstants.CENTER);

      retour.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            removeAll();
            Menu menu = new Menu(fenetre);
            fenetre.add(menu);
            fenetre.revalidate();
        }
      });


      labels.add(retour);

      JLabel def=new JLabel("     DEFI",SwingConstants.CENTER);
      def.setFont(new Font("Impact",Font.PLAIN,65));
      def.setForeground(new Color(155, 41, 0));

      def.setVerticalTextPosition(SwingConstants.TOP);
      def.setBounds(410,10,190,60);

      labels.setPreferredSize(new Dimension(1020,140));
      labels.add(def);
      labels.setVisible(true);

      labels.add(new JLabel(" "));

      this.add(labels);
      labels.repaint();


      content=new JPanel();
      content.setSize(fenetre.getWidth()-50,400);
      content.setOpaque(false);
      content.setLayout(new GridBagLayout());
      GridBagConstraints c2=new GridBagConstraints();
      c2.fill=GridBagConstraints.HORIZONTAL;
      for(int i = 0; i < nbLignes; i++){
          c2.gridx=i%4;
          c2.gridy=i/4;
          c2.weightx=1;
         
       
          DefiView dv = new DefiView(i, fen){
              @Override
              protected void paintComponent(Graphics g) {
                  super.paintComponent(g);
                  g.drawImage(BanqueImage.images[11],0,0,null);
              }
          };
          dv.setPreferredSize(new Dimension(2,100));
          dv.setBorder(new MyBorder());
          content.add(dv,c2);
      }


      JScrollPane scroll=new JScrollPane(content,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scroll.setPreferredSize(new Dimension(800,400));
      scroll.setMaximumSize(new Dimension(800,400));
      scroll.setOpaque(false);
      scroll.getViewport().setOpaque(false);
      scroll.setBorder(null);
      scroll.getViewport().setBorder(null);
      this.add(scroll);
      this.add(Box.createVerticalStrut(50));
      
    }

}
