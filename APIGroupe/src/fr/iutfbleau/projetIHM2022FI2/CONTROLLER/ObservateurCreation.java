package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.MODEL.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.*;


/**
 * gère les interactions entre l'utilisateur et l'interface dans la vue Creation_Etudiant() et Ajout_Etudiant().
 */
public class ObservateurCreation implements ActionListener {

    private JTextField form_nom_groupe;
    private JSpinner form_min;
    private JSpinner form_max;
    private JTextField form_nom;
    private JTextField form_prenom;
    private JDialog fen ;

    public ObservateurCreation(JTextField nom_g,JSpinner min,JSpinner max){

        form_nom_groupe = nom_g;
        form_min = min;
        form_max = max;
        



    }

    public ObservateurCreation(JTextField nom , JTextField prenom,JDialog fen ){
        this.form_nom = nom;
        this.form_prenom = prenom;
        this.fen = fen;
    }

    @Override

    public void actionPerformed(ActionEvent e){

       
        
            if(e.getActionCommand().equals("créer le groupe")  ){
                if(form_nom_groupe.getText().replaceAll("\\s", "").length() != 0){

                    String name_groupe = form_nom_groupe.getText();
                    int min =  ((Number)form_min.getValue()).intValue();
                    int max =  ((Number)form_max.getValue()).intValue();



                    
                DashboardGroupe.bd.createGroupe(Observateur_arborescence.group_selected, name_groupe, min, max);
                DashboardGroupe.refresh_pan_group();                    

                    System.out.println("nom du groupe :"+form_nom_groupe.getText().replaceAll("\\s", "") +"\n nombre min de membre :" + ((Number)form_min.getValue()).intValue() + "\n nombre max de membre: " +((Number)form_max.getValue()).intValue());

                    
                  CreationGroupe.fen_crea.dispose();  
                
                
                }
            }

            if(e.getActionCommand().equals("ajouter l'etudiant")  ){

                    if (form_prenom.getText().replaceAll("\\s", "").length() != 0 && form_nom.getText().replaceAll("\\s", "").length() != 0){
                   
                    DashboardGroupe.bd.brain.get(1).addEtudiant( new MyEtudiant(form_nom.getText(),form_prenom.getText()));
                    fen.dispose();
                    DashboardGroupe.refresh_pan_group();
                    }else{
                        System.out.println("champ vide");
                    }
            }




        


    }
    


}
