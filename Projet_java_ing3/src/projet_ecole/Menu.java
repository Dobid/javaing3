/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_ecole;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author sebas
 */
public class Menu {
    public static void afficherMenu()
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Saisir infos de l'eleve");
        ArrayList<String> infos=new ArrayList<String>();
        infos.add(scan.next());
        infos.add(scan.next());
        infos.add(scan.next());
        
    }
    
}
