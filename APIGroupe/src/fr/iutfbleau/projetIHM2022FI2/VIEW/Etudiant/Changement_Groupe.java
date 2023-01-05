package fr.iutfbleau.projetIHM2022FI2.VIEW.Etudiant;

import javax.swing.*;
import java.awt.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;


/**
 * fenêtre permettant à un étudiant de faire une demande de changement.
 */
public class Changement_Groupe extends JDialog {

    public  JComboBox<String> form_groupe_depart = new JComboBox<String>();
    public  JComboBox<String> form_groupe_arrivee = new JComboBox<String>();
    public  JTextField explication = new JTextField();


    
    public Changement_Groupe(JFrame owner, Etudiant etu){
        super(owner,true);



        
        setSize(300,400);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(7,1));

        for (Groupe g : ViewEtudiant.bd.getGroupesOfEtudiant(etu) ){

            if(g.getId() != 1){
                form_groupe_depart.addItem(g.getName());

                for (Groupe sg :g.getPointPoint().getSousGroupes()){

                    if(sg.getId() != g.getId()){
                    form_groupe_arrivee.addItem(sg.getName());
                    }
                }
            }


        } 

      

/* 
       for (Map.Entry<Integer,MyGroupe> group_stocké :  ViewEtudiant.bd.brain.entrySet()) {

                for (Groupe g : ViewEtudiant.bd.getGroupesOfEtudiant(etu) ){

                    if(g.getId() != group_stocké.getValue().getId()){
                        form_groupe_arrivee.addItem(group_stocké.getValue().getName());
                    }

                } 


       }*/

       

        

        JButton valider  = new JButton("Faire la demande");
        valider.addActionListener(new Observateur_creation_demande(this, etu,form_groupe_depart,form_groupe_arrivee,explication));
        /*valider.addActionListener(new ObservateurCreation(form_nom, form_prenom, fen));*/

        add(new JLabel("Groupe de départ"),BorderLayout.NORTH);
        add(form_groupe_depart,BorderLayout.CENTER);

        add(new JLabel("Groupe d'arrivée"),BorderLayout.NORTH);
        add(form_groupe_arrivee,BorderLayout.CENTER);

        add(new JLabel("Entrer  une explication"), BorderLayout.NORTH);
        add(explication, BorderLayout.CENTER);

        add(valider, BorderLayout.SOUTH);

        setVisible(true);


    }


}
