/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_ecole;
import java.util.*;
import java.sql.SQLException;

/**
 *
 * @author sebas
 */
public abstract class Reporting {
    
    public static ArrayList moyenneClasse(Database data, ArrayList<String> val) throws SQLException //nom_td, nom_niveau, trimestre
    {
        String niveau=val.get(1);
        String td=val.get(0);
        int id_niv;
        int id_clas;
        int [] id_insc;
        int trimestre=Integer.parseInt(val.get(2));
        if(trimestre<1 || trimestre>3 )
        {
            System.out.println("Erreur, trimestre doit etre compris entre 1 et 3");
            return null;
        }
        ArrayList<String> moy_inf;
        ArrayList<String> moy_sup;
        ArrayList<String> nbr_eleve;
        
        int moy;
        double moins_cinq=0.0;
        double moins_dix=0.0;
        double moins_quinze=0.0;
        double plus_quinze=0.0;
        
        ArrayList<String> resultat=data.bdd.remplirChampsRequete("SELECT id_niveau FROM niveau WHERE nom='"+niveau+"'");
        if(!resultat.isEmpty())
        {
            id_niv=Integer.parseInt(resultat.get(0));
            resultat=data.bdd.remplirChampsRequete("SELECT id_classe FROM classe WHERE nom='"+td+"' AND id_niveau="+id_niv);
            if(!resultat.isEmpty())
            {
                id_clas=Integer.parseInt(resultat.get(0));
                resultat=data.bdd.remplirChampsRequete("SELECT id_inscription FROM inscription WHERE id_classe="+id_clas);
               
                if(!resultat.isEmpty())
                {
                    id_insc=new int[resultat.size()];
                    for(int i=0; i<resultat.size(); i++)
                    {
                        id_insc[i]=Integer.parseInt(resultat.get(i));
                       
                        
                        ArrayList<String> inscrip=data.bdd.remplirChampsRequete("SELECT moyenne FROM bulletin WHERE id_inscription ="+id_insc[i]+" AND id_trimestre+"+trimestre);
                        moy=Integer.parseInt(inscrip.get(0));
                        if(moy<5) 
                            moins_cinq++;
                        else if(moy<10)
                            moins_dix++;
                        else if(moy<15)
                            moins_quinze++;
                        else 
                            plus_quinze++;
                    }
                  ArrayList res=new ArrayList();
                  res.add(moins_cinq);
                  res.add(moins_dix);
                  res.add(moins_quinze);
                  res.add(plus_quinze);
                    return res;
                }
                
            }
            
        }
        return null;
    }
    
    public static ArrayList moyenneDiscipline(Database data, ArrayList<String> val) throws SQLException //nom_discipline
    {
        int id_disc;
        int [] id_ens;
        int [] id_bulletin;
        int moy;
        double moins_cinq=0.0;
        double moins_dix=0.0;
        double moins_quinze=0.0;
        double plus_quinze=0.0;
        
        ArrayList<String> resultat=data.bdd.remplirChampsRequete("SELECT id_discipline FROM discipline WHERE nom='"+val.get(0)+"'");
        ArrayList<String> bulletin, note;
        ArrayList res=new ArrayList();
        if(!resultat.isEmpty())
        {
            id_disc=Integer.parseInt(resultat.get(0));
            resultat=data.bdd.remplirChampsRequete("SELECT id_ens FROM enseignement WHERE id_discipline="+id_disc);
            if(!resultat.isEmpty())
            {
                id_ens=new int[resultat.size()];
                for(int i=0; i<resultat.size(); i++)
                {
                    id_ens[i]=Integer.parseInt(resultat.get(i));
                    bulletin=data.bdd.remplirChampsRequete("SELECT id_bulletin FROM detailbulletin WHERE id_ens="+id_ens[i]);
                    if(!bulletin.isEmpty())
                    {
                        id_bulletin=new int[bulletin.size()];
                        for(int j=0; j<bulletin.size(); j++)
                        {
                            id_bulletin[j]=Integer.parseInt(bulletin.get(j));
                            note=data.bdd.remplirChampsRequete("SELECT moyenne FROM bulletin WHERE id_bulletin="+id_bulletin[j]);
                            moy=Integer.parseInt(note.get(0));
                            if(moy<5)
                                moins_cinq++;
                            else if (moy<10)
                                moins_dix++;
                            else if(moy<15)
                                moins_quinze++;
                            else 
                                plus_quinze++;
                        }    
                            
                            
                    }
                }
                res.add(moins_cinq);
                res.add(moins_dix);
                res.add(moins_quinze);
                res.add(plus_quinze);
                return res;
            }
            
        }
        return null;
    }
    
<<<<<<< HEAD
    public static double medianeClasse(Database data, ArrayList<String> val) throws SQLException //nom_td, nom_classe, trimestre
    {
        String td=val.get(0);
        int id_clas;
        int id_niv;
        int [] id_insc;
        int indice;
        int indice_bis;
        double med=0.0;
        String niveau=val.get(1);
        int trimestre=Integer.parseInt(val.get(2));
        ArrayList<String> moy=new ArrayList(); //liste des moyenne d'une classe, à trier 
        int nb_eleve;
        ArrayList<String> bulletin;
        
        ArrayList<String> resultat=data.bdd.remplirChampsRequete("SELECT id_niveau FROM niveau WHERE nom='"+niveau+"'");
        if(!resultat.isEmpty())
        {
            id_niv=Integer.parseInt(resultat.get(0));
            resultat=data.bdd.remplirChampsRequete("SELECT id_classe FROM classe WHERE nom='"+td+"' AND id_niveau="+id_niv);
            if(!resultat.isEmpty())
            {
                id_clas=Integer.parseInt(resultat.get(0));
                resultat=data.bdd.remplirChampsRequete("SELECT id_inscription FROM inscription WHERE id_classe="+id_clas);
                if(!resultat.isEmpty())
                {
                    id_insc=new int[resultat.size()];
                    nb_eleve=resultat.size();
                    
                    for (int i=0; i<resultat.size(); i++)
                    {
                        id_insc[i]=Integer.parseInt(resultat.get(i));
                        bulletin=data.bdd.remplirChampsRequete("SELECT moyenne FROM bulletin WHERE id_inscription="+id_insc[i]+" AND id_trimestre="+trimestre);
                        moy.add((bulletin.get(0)));
                    }
                   Collections.sort(moy);
                   ArrayList tri=new ArrayList();
                    for(int i=0; i<moy.size(); i++)
                        tri.add(Integer.parseInt(moy.get(i)));
                    Collections.sort(tri);
                   if(nb_eleve%2==1)
                   {
                      indice= (nb_eleve+1)/2;
                      med= (int) tri.get(indice-1);
                   }
                   else
                   {
                       indice=nb_eleve/2;
                       med= (int) tri.get(indice-1);
                       indice_bis=(nb_eleve-1)/2;
                       med=(med+ (int) tri.get(indice_bis))/2;
                   }
                      
                    
                }
            }
             
        }
        return med;
    }
=======
    
>>>>>>> parent of adb6900... ajoutEleve+detailBull
    
<<<<<<< HEAD
    public static int medianeDiscipline(Database data, ArrayList<String> val) throws SQLException //discipline
    {
        double med=0.0;
        int id_disc;
        ArrayList<String> resultat;
        ArrayList<String> moy=new ArrayList();
        ArrayList<String> bulletin;
        ArrayList<String> note;
        int indice=0;
        int indice_impair;
        int nb_eleve=0;
        int [] id_ens;
        int [] id_bul;
        
        resultat=data.bdd.remplirChampsRequete("SELECT id_discipline FROM discipline WHERE nom='"+val.get(0)+"'");
        if(!resultat.isEmpty())
        {
            id_disc=Integer.parseInt(resultat.get(0));
            resultat=data.bdd.remplirChampsRequete("SELECT id_ens FROM enseignement WHERE id_discipline="+id_disc);
            if(!resultat.isEmpty())
            {
                id_ens=new int[resultat.size()];
                
                for(int i=0; i<resultat.size(); i++)
                {
                    id_ens[i]=Integer.parseInt(resultat.get(i));
                    bulletin=data.bdd.remplirChampsRequete("SELECT id_bulletin FROM detailbulletin WHERE id_ens="+id_ens[i]);
                    if(!bulletin.isEmpty())
                    {
                        nb_eleve=bulletin.size();
                        id_bul=new int[bulletin.size()];
                        for(int j=0; j<bulletin.size(); j++)
                        {
                            id_bul[j]=Integer.parseInt(bulletin.get(j));
                            note=data.bdd.remplirChampsRequete("SELECT moyenne FROM bulletin WHERE id_bulletin="+id_bul[j]);
                            moy.add(note.get(0));
                        }
                    }
                }
                ArrayList tri=new ArrayList();
                for(int i=0; i<moy.size(); i++)
                    tri.add(Integer.parseInt(moy.get(i)));
                Collections.sort(tri);
                
                if(nb_eleve%2==0)
                {
                    indice=indice/2-1;
                }
                else
                {
                    indice=nb_eleve+1;
                    indice=indice/2-1;
                }
                return (int) tri.get(indice);
                
            }
            
        }
        return 0;
    }
=======
    
>>>>>>> parent of adb6900... ajoutEleve+detailBull
    
    
    
}
