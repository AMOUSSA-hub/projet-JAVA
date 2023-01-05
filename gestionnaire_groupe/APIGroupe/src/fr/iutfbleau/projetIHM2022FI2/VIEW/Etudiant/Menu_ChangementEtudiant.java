package fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.Observateur_demande;


import javax.swing.*;
import java.awt.*;
import java.util.*;


/**
 * fenêtre affichant toutes les demandes de changement (pour l'étudiant).
 */
public class Menu_ChangementEtudiant extends JDialog {

    public Menu_ChangementEtudiant (JFrame owner){
        super(owner,true);
        setSize(600,400);
        setLocationRelativeTo(owner);
        JPanel caption = new JPanel();
        JPanel tab = new JPanel();

        caption.setLayout(new GridBagLayout());
        tab.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;      // la plage de cellules commence à la première colonne
        gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
        gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
        gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,5,5,5);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        caption.add(new JLabel("Etudiant"),gbc);
        
        gbc.gridx = 1;      // la plage de cellules commence à la première colonne
        gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
        gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
        gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,5,5,5);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        caption.add(new JLabel("Groupe de départ"),gbc);

        gbc.gridx = 2;      // la plage de cellules commence à la première colonne
        gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
        gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
        gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,5,5,5);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        caption.add(new JLabel("Groupe d'arrivé"),gbc);

        Set<Changement> liste_c =  ViewEtudiant.demandes.getAllChangements();
        if (!liste_c.isEmpty()){
                Iterator<Changement> i =  liste_c.iterator();
                int nbr_demande = 1;
                while(i.hasNext()){
                    Changement  c= i.next();

                    gbc.gridx = 0;      // la plage de cellules commence à la première colonne
                    gbc.gridy = nbr_demande;      // la plage de cellules commence à la deuxième ligne
                    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
                    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
                    gbc.fill = GridBagConstraints.VERTICAL;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.insets = new Insets(5,5,5,5);
                    gbc.weightx = 0.0;
                    gbc.weighty = 0.0;
            
                    tab.add(new JLabel(c.getEtu().getNom()+" "+c.getEtu().getPrenom()),gbc);

                    gbc.gridx = 1;      // la plage de cellules commence à la première colonne
                    gbc.gridy = nbr_demande;      // la plage de cellules commence à la deuxième ligne
                    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
                    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
                    gbc.fill = GridBagConstraints.VERTICAL;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.insets = new Insets(5,5,5,5);
                    gbc.weightx = 0.0;
                    gbc.weighty = 0.0;

                    tab.add(new JLabel(c.getA().getName()),gbc);

                    gbc.gridx = 2;      // la plage de cellules commence à la première colonne
                    gbc.gridy = nbr_demande;      // la plage de cellules commence à la deuxième ligne
                    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
                    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
                    gbc.fill = GridBagConstraints.VERTICAL;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.insets = new Insets(5,5,5,5);
                    gbc.weightx = 0.0;
                    gbc.weighty = 0.0;

                    tab.add(new JLabel(c.getB().getName()),gbc);

                    gbc.gridx = 3;      // la plage de cellules commence à la première colonne
                    gbc.gridy = nbr_demande;      // la plage de cellules commence à la deuxième ligne
                    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
                    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
                    gbc.fill = GridBagConstraints.VERTICAL;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.insets = new Insets(5,5,5,5);
                    gbc.weightx = 0.0;
                    gbc.weighty = 0.0;

                    JButton explication = new JButton("voir explication");
                    explication.addActionListener(new Observateur_demande(c,this));
                    tab.add(explication,gbc);

                    gbc.gridx = 4;      // la plage de cellules commence à la première colonne
                    gbc.gridy = nbr_demande;      // la plage de cellules commence à la deuxième ligne
                    gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
                    gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
                    gbc.fill = GridBagConstraints.VERTICAL;
                    gbc.anchor = GridBagConstraints.CENTER;
                    gbc.insets = new Insets(5,5,5,5);
                    gbc.weightx = 0.0;
                    gbc.weighty = 0.0;

                }
            }else{
                tab.add(new JLabel("Aucune demande"));
            }

        add(caption,BorderLayout.NORTH);
        add(tab,BorderLayout.CENTER);

        setVisible(true);
    }
    
}
