package fr.iutfbleau.projetIHM2022FI2.VIEW.Admin;


import java.util.*;
import java.awt.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.CONTROLLER.*;
import fr.iutfbleau.projetIHM2022FI2.MODEL.*;

/**
 * fenêtre qui permet de choisir les étudiants que l'on veut ajouter à un groupe.
 */
public class Ajout_Etudiant extends JDialog {

    public Ajout_Etudiant (JFrame owner, MyGroupe group){

        super(owner,true);
        setSize(500,400);
        setLocationRelativeTo(owner);
        JPanel central_pan = new JPanel();
        JPanel footer_pan = new JPanel();
        Set<Etudiant> selection  = new HashSet<Etudiant>();
        
        JButton valider = new JButton("valider");
        JButton annuler = new JButton("annuler");
        
        valider.addMouseListener(new Observateur_ajout_etudiant(valider, this));
        annuler.addMouseListener(new Observateur_ajout_etudiant(annuler, this));
       
        Groupe group_parent = group.getPointPoint();
        Set<Etudiant> etudiant_group_parent =   group_parent.getEtudiants();
        Iterator<Etudiant> iterator = etudiant_group_parent.iterator();
        
        Set<Etudiant> etudiant = group.getEtudiants();
        
        while(iterator.hasNext()){

            Etudiant a = iterator.next();
            boolean contain = false;
            for(Groupe g : group_parent.getSousGroupes()){

                if (g.contain(a)){
                    contain = true;  
                }

            }
            
            for(Etudiant i : etudiant){
               
                if(a.getId() == i.getId()){
                    contain = true;

                    }

                }
                
                if(!contain ){

                    JButton bout = new JButton(a.getNom()+" "+ a.getPrenom());
                    bout.addMouseListener(new Observateur_ajout_etudiant(a,selection));
                    bout.setBackground(new Color(203, 201, 201));

                    central_pan.add(bout);
                }

            
            
            


        }

        central_pan.setLayout(new GridLayout(3,etudiant.size()/3+1));


        footer_pan.setBackground(new Color(116, 208, 241));
        footer_pan.add(valider);
        footer_pan.add(annuler);

        add(central_pan);
        add(footer_pan,BorderLayout.SOUTH);



        


        


        setVisible(true);

    }





    
}
