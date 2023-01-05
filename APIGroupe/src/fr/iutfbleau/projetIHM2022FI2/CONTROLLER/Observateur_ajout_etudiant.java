package fr.iutfbleau.projetIHM2022FI2.CONTROLLER;

import java.awt.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.VIEW.Admin.DashboardGroupe;

/**
 * permet d'ajouter un étudiant à un groupe hors le groupe Promotion.
 */

public class Observateur_ajout_etudiant implements MouseListener {

private static Set<Etudiant> selection;
private Etudiant etudiant_clicked;
private  boolean selected = false;
public JButton bout;
public JDialog fen;


    public Observateur_ajout_etudiant (Etudiant e, Set<Etudiant> liste){

        etudiant_clicked = e ;
        selection = liste;


    }

    
    public Observateur_ajout_etudiant (JButton val,JDialog fen){

        
        bout = val;
        this.fen = fen;
        

    }

    





    @Override

    public void mouseClicked(MouseEvent e){

        if(bout != null){
                if(bout.getText().equals("valider")){

                    if(selection.size()!=0){
                        if(selection.size()+Observateur_arborescence.group_selected.getSize() <= Observateur_arborescence.group_selected.getMax()  ){
                                    fen.dispose();
                                    Iterator<Etudiant> iterator = selection.iterator();

                                    while(iterator.hasNext()){    
                                    
                                    DashboardGroupe.bd.addToGroupe(Observateur_arborescence.group_selected, iterator.next());
                                
                                    }

                                    DashboardGroupe.refresh_pan_group();;
                                    selection.clear();
                        }else{
                            JOptionPane.showMessageDialog(new JDialog(), Observateur_arborescence.group_selected.getName() + " ne peut pas acccueilir autant d'élève.");
                            
                        }
                    }
                }

                else if(bout.getText().equals("annuler"))
                this.fen.dispose();
        }

         else if (!selected){

            ((JButton)e.getSource()).setBackground(new Color(116, 208, 241));
            selection.add(etudiant_clicked);
            selected = true;
            System.out.println("+");
            System.out.println(selection.size()); 
        }


        else  {

            ((JButton)e.getSource()).setBackground(new Color(203, 201, 201));
            selection.remove(etudiant_clicked);
            selected =false;
            System.out.println("-");
            System.out.println(selection.size());
        }

      

        

      }

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {

          
       

      
    
    
    }

    public void mouseReleased(MouseEvent e) {}
    
}
