package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;
import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.UTILS.Utils;

import java.awt.*;

public class Observateur_fenetre implements WindowListener,ActionListener{

private JFrame fen; 
private JDialog validation;

    public Observateur_fenetre(JFrame fen){
        this.fen  = fen;


    }


  
   public void windowClosing(WindowEvent evenement){

    validation = new JDialog(fen,true);
    validation.setSize(250,100);
    validation.setLocationRelativeTo(fen);;

    validation.add((new JPanel().add(new JLabel("Voulez vous vraiment quitter ?"))),BorderLayout.CENTER);

     JPanel boutons = new JPanel();

     JButton quit = new JButton("Quitter");
     JButton cancel = new JButton("Annuler");

     quit.addActionListener(this);
     cancel.addActionListener(this);
     boutons.add(quit);
     boutons.add(cancel);
    validation.add(boutons,BorderLayout.SOUTH);
     validation.setVisible(true);
     


   } 

   public void actionPerformed (ActionEvent evt ){
        
    if(evt.getActionCommand() == "Quitter"){
        
        Utils.close_connection();
        System.exit(0);
       
        



    }

     if(evt.getActionCommand() == "Annuler"){

       
        validation.dispose();



    }
}
  
  
  
  
  
   public void windowActivated(WindowEvent evenement){}       
   public void windowClosed(WindowEvent evenement){}          
         
   public void windowDeactivated(WindowEvent evenement){}     
    public void windowDeiconified(WindowEvent evenement){}     
    public void windowIconified(WindowEvent evenement){}       
    public void windowOpened(WindowEvent evenement){}      

    
}