/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_ecole;
import java.sql.SQLException;
import projet_ecole.Connexion;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author sebas
 */
public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        Connexion base=new Connexion("ecole", "root", "");
        ArrayList<String> val=new ArrayList<String>();
       // val.add("CM2");
       // val.add("pour");
       // val.add("67");
       val.add("1");
       val.add("1");
       val.add("0");
       val.add("Absent");
       Update.ajouterEval(base, val);
    }
    
}
