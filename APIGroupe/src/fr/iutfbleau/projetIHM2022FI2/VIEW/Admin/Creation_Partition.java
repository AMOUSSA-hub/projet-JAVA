package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;

import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.Observateur_creation_partition;

import java.awt.*;


/**
 * fenêtre permettant de créer une partition.
 */

public class Creation_Partition extends JDialog {

    
    public Creation_Partition(JFrame owner){
        super(owner,true);
        setSize(300, 300);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(5,1));

        JTextField form_nom =  new JTextField();
        
        JSpinner form_nombre = new JSpinner(new SpinnerNumberModel(2,2,null,1));
        JButton creer_part = new JButton("créer les partitions");
        creer_part.addActionListener(new Observateur_creation_partition(form_nom, form_nombre,this));
        add(new JLabel("nom de la partition"));
        add(form_nom);
        add(new JLabel("nombre de partition"));
        add(form_nombre);
        add(creer_part);

        setVisible(true);



    }
}
