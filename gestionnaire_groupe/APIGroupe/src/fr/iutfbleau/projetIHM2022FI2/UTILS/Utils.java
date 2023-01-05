package fr.iutfbleau.projetIHM2022FI2.UTILS;
import java.sql.*;
import javax.swing.tree.*;
import javax.swing.tree.TreePath;


import java.awt.*;
/**
 * classe regroupant des méthodes annexes.
 */
public class Utils {

public static final Dimension fen_dimension = new Dimension(800,500);


public static   Connection con;
/**
 * permet l'ouverture de la connexion avec la base de données.
 */
    public static void open_connection(){

            try{
                Class.forName("org.mariadb.jdbc.Driver"); 
                try{
                    con = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/amoussa","amoussa", "badbodhurul");          
                } catch (Exception se) {
                    System.err.println("errreur Sql"+se);            
                }            
            
            }catch(ClassNotFoundException ce){
                    
                System.err.println("erreur class"+ce);
                ce.printStackTrace();
            }
    
    }



/**
 * permet la fermeture de la connexion avec la base de données.
 */
    public static void close_connection(){

            
        try{
            con.close();
        } catch (Exception se) {
            System.err.println("errreur Sql"+se);

        }
    }




/**
 * Convertis un objet de type TreeNode[] en une chaine de caractère. 
 * @param a chemin 
 * @return chaîne de caractères du chemin
 */ 
    public static String TreeNode_to_String(TreeNode[] a){

       String [] path_2 = new String[a.length];
            
        for(int i = 0 ; i <= a.length-1; i++){
           path_2[i] = a[i].toString();
        }
        System.out.println(new TreePath(path_2));
        
        return  new TreePath(path_2).toString();

    }


    




    
}
