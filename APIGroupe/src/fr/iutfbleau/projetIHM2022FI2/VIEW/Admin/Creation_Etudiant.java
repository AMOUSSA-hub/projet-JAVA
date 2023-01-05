package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;

import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;


/**
 * fenêtre permettant de créer un étudiant.
 */
public class Creation_Etudiant extends JDialog {

    public  JDialog fen;
    
    public Creation_Etudiant(JFrame owner){
        super(owner,true);
        fen = this;
        setSize(300,400);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(5,1));

        JTextField form_nom = new JTextField();
        JTextField form_prenom = new JTextField();

        JButton valider  = new JButton("ajouter l'etudiant");
        valider.addActionListener(new ObservateurCreation(form_nom, form_prenom, fen));

        add(new JLabel("nom"),BorderLayout.NORTH);
        add(form_nom,BorderLayout.CENTER);

        add(new JLabel("prenom"),BorderLayout.NORTH);
        add(form_prenom,BorderLayout.CENTER);

    
        add(valider,BorderLayout.SOUTH);


        setVisible(true);


    }


}
