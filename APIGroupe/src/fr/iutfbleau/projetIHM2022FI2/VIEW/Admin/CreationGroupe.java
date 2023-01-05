package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.ObservateurCreation;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.Observateur_arborescence;

import java.awt.*;

/**
 * fenêtre permettant de créer un groupe.
 */
public class CreationGroupe extends JDialog {


    public static CreationGroupe fen_crea;

    public CreationGroupe(JFrame fen){
        super(fen,true);
        fen_crea = this;
        setSize(300, 400);
        setLocationRelativeTo(fen);
       
        setLayout(new GridLayout(8,1));
        

        
        JTextField nom_groupe =  new JTextField();
        
        
        JSpinner min_member = new JSpinner(new SpinnerNumberModel(1,1,Observateur_arborescence.group_selected.getMax(),1));
        JSpinner max_member = new JSpinner(new SpinnerNumberModel(1,1,Observateur_arborescence.group_selected.getMax(),1));
        
        

         JButton valider = new JButton("créer le groupe");
         valider.addActionListener(new ObservateurCreation(nom_groupe, min_member, max_member));

        add(new JLabel("création sous groupe de "+Observateur_arborescence.group_selected.getName()),BorderLayout.CENTER);
        add(new JLabel("Nom du groupe"),BorderLayout.CENTER);
        add(nom_groupe,BorderLayout.CENTER);
        add(new JLabel("Nombre minimum de membre"),BorderLayout.CENTER);
        add(min_member,BorderLayout.CENTER);
        
        add(new JLabel("Nombre maximum de membre"),BorderLayout.CENTER);
        add(max_member,BorderLayout.CENTER);
        add(valider,BorderLayout.SOUTH);









        setVisible(true);



    }

  




    
}
