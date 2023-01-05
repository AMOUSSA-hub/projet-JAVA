package fr.iutfbleau.projetIHM2022FI2.MODEL;


import fr.iutfbleau.projetIHM2022FI2.API.*;
import fr.iutfbleau.projetIHM2022FI2.UTILS.*;


import java.util.*;
import javax.swing.*;
import java.sql.*;
/**
 * Un groupe.
 */

public class MyGroupe implements Groupe {

    private int id;
    private String name;
    private int id_father;
    private  Groupe father;
    private int min;
    private int max;
    private TypeGroupe type;
    private int size = 0;
    private String path;
    private Set<Etudiant> membre_groupe ;
    private Set<Groupe> sous_groupes;
    private MyAbstractGroupeFactory magf;
    


//permet de charger un groupe déjà présent dans la base de données.
    public MyGroupe(int id_groupe, MyAbstractGroupeFactory magf){

        this.magf = magf;

        try{

                PreparedStatement req = Utils.con.prepareStatement("Select * from Groupe where Id = ? ");
            req.setInt(1,id_groupe);
            ResultSet res = req.executeQuery();
            while (res.next()){
                    id = res.getInt(1);
                    name = res.getString(2);
                    id_father = res.getInt(3)  ;
                    min = res.getInt(4);
                    max = res.getInt(5);
                    
                    if(res.getString(6).equals("Tous les étudiants")){
                        type = TypeGroupe.ROOT;
                    }
                    
                    if(res.getString(6).equals("partition")){
                    type = TypeGroupe.PARTITION;
                    }
                    
                    if(res.getString(6).equals("libre")){
                    type = TypeGroupe.FREE;
                    }
            }
            
        
                 

            } catch (SQLException  se) {
                System.err.println("errreur Sql at MyGroupe()"+se);

            }

        this.membre_groupe  = new LinkedHashSet<Etudiant>();
        this.sous_groupes = new LinkedHashSet<Groupe>();

            

   }
//créer un groupe de type FREE
   public MyGroupe(Groupe pere, String name, int min, int max){
   
    Objects.requireNonNull(name,"On ne peut pas créer un groupe dont le nom est null");
    this.name=name;
    this.min=min;
    this.max=max;
    this.type=TypeGroupe.FREE;
    this.father=pere;
    id_father = pere.getId();
    this.sous_groupes=new LinkedHashSet<Groupe>();
    this.membre_groupe=new LinkedHashSet<Etudiant>();

    try{
    PreparedStatement req = Utils.con.prepareStatement("Insert into Groupe (Nom,idPere,Min,Max,Type) Values (?,?,?,?,?)  ");
            req.setString(1,name);
            req.setInt(2,father.getId());
            req.setInt(3,min);
            req.setInt(4,max);
            req.setString(5,"libre");
            req.executeUpdate();
        
    req = Utils.con.prepareStatement("Select max(id) From Groupe");
           ResultSet res = req.executeQuery();
           while(res.next()){
           id = res.getInt(1);
           }
    
        } catch (SQLException  se) {
            System.err.println("errreur Sql at MyGroupe()"+se);

        }


   }
//crée un groupe partition du groupe pere
   public MyGroupe(Groupe pere){
        Objects.requireNonNull(pere,"On ne peut pas créer un groupe dont le père est null");
     
                
                try{   
                    PreparedStatement req = Utils.con.prepareStatement("Select max(id) From Groupe");
                ResultSet res = req.executeQuery();
                while(res.next()){
                id = res.getInt(1)+1;
                }
                } catch (SQLException  se) {
                    System.err.println("errreur Sql at MyGroupe()"+se);

                }


                    this.name=pere.getName()+"_PARTITION_"+ this.id;
                    this.min=pere.getMin();
                    this.max=pere.getMax();
                    this.type=TypeGroupe.PARTITION;
                    id_father = pere.getId();
                    this.father = pere;
                    this.sous_groupes= new LinkedHashSet<Groupe>();
                    this.membre_groupe= pere.getEtudiants();

                    try{
                        PreparedStatement req = Utils.con.prepareStatement("Insert into Groupe (Nom,idPere,Min,Max,Type) Values (?,?,?,?,?)  ");
                                req.setString(1,name);
                                req.setInt(2,father.getId());
                                req.setInt(3,min);
                                req.setInt(4,max);
                                req.setString(5,"partition");
                                req.executeUpdate();
                            
                        
                            } catch (SQLException  se) {
                                System.err.println("errreur Sql at MyGroupe()"+se);
                    
                            }


                    


    }

    
   
   
   
  
   public boolean contain( Etudiant e){

    for(Etudiant etu : membre_groupe){

        if(etu.getId() == e.getId()){

            return true;
        }

    }

    
    return false;
   }
   
   public boolean addEtudiant(Etudiant e){

    for(Etudiant etu : membre_groupe){

        if(((etu.getNom()+etu.getPrenom()).toLowerCase()).equals((e.getNom()+e.getPrenom()).toLowerCase())){
            JOptionPane.showMessageDialog(new JDialog(), name+" contient déjà un étudiant avec le même nom.");
            throw new IllegalStateException(name+" contient déjà un étudiant avec le même nom.");
        }

    }
    

    if(!contain(e)){

                try{

                    PreparedStatement req = Utils.con.prepareStatement("Insert into Contient (Contient.idGroupe,Contient.IdEtudiant) VALUES(?,?) ");
                        req.setInt(1,id);
                        req.setInt(2, e.getId());
                        req.executeUpdate();


                } catch (SQLException  se) {
                            System.err.println("errreur Sql at addEtudiant():"+se);

                        }
        
              membre_groupe.add(e);
              size++;

            }
            System.out.println("appartient déjà");
        return false;
    }

    
    
    public boolean removeEtudiant(Etudiant e){

        if(contain(e)){
      

                try{

                    PreparedStatement req = Utils.con.prepareStatement("DELETE from Contient Where Contient.idGroupe = ? AND Contient.IdEtudiant = ? ");
                        req.setInt(1,id);
                        req.setInt(2, e.getId());
                        req.executeUpdate();
            
            
                } catch (SQLException  se) {
                            System.err.println("errreur Sql at removeEtudiant():"+se);
                            return false;
            
                        }


                        size--;
                        membre_groupe.remove(e);
                    //test suppression etudiant récursivement    
                        
                    for(Groupe g : getSousGroupes()){   
                                           
                        if(g.contain(e)){
                            return g.removeEtudiant(e);
                        }
                    }
                    
       
    }
              System.out.println("n'est pas présent dans ce groupe");
          return false;
                
    }

    public boolean addSousGroupe(Groupe g){


      
        try{

            PreparedStatement req = Utils.con.prepareStatement("UPDATE Groupe SET Groupe.idPere = ? WHERE Groupe.id = ? ");
                req.setInt(1,id);
                req.setInt(2, g.getId());
                req.executeUpdate();
    
    
           } catch (SQLException  se) {
                    System.err.println("errreur Sql at addSousGroupe():"+se);
                    return false;
                }




        return sous_groupes.add(g);
    }


    public boolean removeSousGroupe(Groupe g){

        Objects.requireNonNull(g,"On ne peut pas enlever un Étudiant qui est null");
        try{

            PreparedStatement req = Utils.con.prepareStatement("Delete from Groupe where id = ? ");
            req.setInt(1,g.getId());
            req.executeUpdate();
    
    
        } catch (SQLException  se) {
            System.err.println("errreur Sql at addEtudiant():"+se);
            return false;
        }

        
        
        return this.sous_groupes.remove(g);
        
    };


    public int getId(){

        return id ;
    };

    public String getName(){

        return name;
    };

    public int getMin(){

        return  min;
    };

    public int getMax(){

        return max; 
    };

    public int getSize(){

        return size;
       
    };


    public TypeGroupe getType(){

        return type;
    };


    public Groupe getPointPoint(){

        if(father == null){
        father =  this.magf.brain.get(id_father) ;
        }
        return father;
    };

    
    
    public Set<Groupe> getSousGroupes(){

        if(sous_groupes.isEmpty()){
        
    try{
        
       

        PreparedStatement req = Utils.con.prepareStatement("Select Groupe.id,Groupe.Nom from Groupe where Groupe.idPere = ?");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();

            while(res.next()){

                sous_groupes.add(this.magf.brain.get(res.getInt(1)) );
            }

   
        } catch (SQLException e) {
            System.err.println("errreur Sql at getSousgroupes()"+e);
            
          }


        }


            return sous_groupes;





    }

    public Set<Etudiant> getEtudiants(){

        
    if(membre_groupe.isEmpty()){
        

        try{

            PreparedStatement req = Utils.con.prepareStatement("Select Contient.IdEtudiant from Contient WHERE Contient.idGroupe = ? ");
            req.setInt(1,id);
            ResultSet res = req.executeQuery();

            while(res.next()){
                size++;
                membre_groupe.add(new MyEtudiant(res.getInt(1)));
            }

           

        } catch (SQLException se) {
            System.err.println("errreur Sql at getEtudiants()"+se);

        }
    
    
    }

        return membre_groupe;

    }

    public void setPath (String path){

        this.path = path;
    }

    public String getPath(){

        return path;
    }

    public String monPrint() {

        return "name";
    }

    public boolean editName(String new_name){


        

        try{

            PreparedStatement req = Utils.con.prepareStatement("UPDATE Groupe SET Groupe.nom = ? WHERE Groupe.id = ? ");
                req.setString(1, new_name);
                req.setInt(2, id);
              int check =   req.executeUpdate();

              if(check !=0){

                System.out.println("commande traité avec succès");
                this.name = new_name;
                System.out.println("nouveau nom : "+name);
                return true;
              }

              
              
    
            
           } catch (SQLException  se) {
                    System.err.println("errreur Sql at editName():"+se);
    
                }



        return false;

    
    }






    
}
