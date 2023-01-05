package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;

import java.util.*;
import java.awt.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.Observateur_recherche;

public class panneau_recherche extends JPanel {


    public panneau_recherche(Set<Etudiant> liste_etu){
        
       setLayout(new GridBagLayout());
       GridBagConstraints gbc = new GridBagConstraints();

       gbc.gridx = 0;      // la plage de cellules commence à la première colonne
        gbc.gridy = 0;      // la plage de cellules commence à la deuxième ligne
        gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
        gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,5,0,5);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        JButton home_bout = new JButton("Accueil");
        home_bout.addActionListener(new Observateur_recherche());
        add(home_bout,gbc);

        gbc.gridx = 0;      // la plage de cellules commence à la première colonne
        gbc.gridy = 1;      // la plage de cellules commence à la deuxième ligne
        gbc.gridwidth = 1;  // la plage de cellules englobe deux colonnes
        gbc.gridheight = 1; // la plage de cellules englobe une seule ligne
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,5,0,5);
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;

        DefaultListModel<String> model = new DefaultListModel<>();
        for(Etudiant etu : liste_etu){
            model.addElement(etu.getNom()+" "+etu.getPrenom());
           
        }

        JList<String> List_etu = new JList<>(model);

        List_etu.setPreferredSize(new Dimension(300,300));
 
       
        add(List_etu,gbc);




    }

    
}
