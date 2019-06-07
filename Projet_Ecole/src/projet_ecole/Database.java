package projet_ecole;

import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    Connexion bdd;

    public Database() throws ClassNotFoundException, SQLException {
        bdd = new Connexion("ecole", "root", "");
    }

    /**
     * Fonction permettant d'afficher les élèves d'une classe
     * @param val : ArrayList de String contenant les informations (td, niveau) utiles pour la recherche d'une classe à afficher
     * @return ArrayList contenant les infos de tous les élèves faisant partie de la classe
     * @throws SQLException 
     */
     public ArrayList<String> afficherClasse(ArrayList<String> val) throws SQLException //td, niveau
     {
         if(!isClasseExist(val))
         {
             ArrayList<String> resultat=bdd.remplirChampsRequete("SELECT id_niveau FROM niveau WHERE nom='"+val.get(1)+"'");
             int id_niv=Integer.parseInt(resultat.get(0));
             resultat=bdd.remplirChampsRequete("SELECT id_classe FROM classe WHERE nom='"+val.get(0)+"' AND id_niveau="+id_niv);
             int id_clas=Integer.parseInt(resultat.get(0));
             resultat=bdd.remplirChampsRequete("SELECT id_eleve FROM inscription WHERE id_classe="+id_clas);
             int []id_eleve;
             if(!resultat.isEmpty())
             {
                 id_eleve=new int[resultat.size()];
                 for(int i=0; i<resultat.size(); i++)
                 {
                     id_eleve[i]=Integer.parseInt(resultat.get(i));
                 resultat.set(i, (String) bdd.remplirChampsRequete("SELECT nom, prenom, age FROM eleve WHERE id_eleve="+id_eleve[i]).get(0));
                 }
                     
             }
             return resultat;
         }
         return null;
     }
     /**
      * méthode qui vérifie que la classe recherchée existe dans la base de données : elle servira pour blinder d'autres fonctions
      * @param val : ArrayList des valeurs utiles d'une classe pour faire une recherche de classe dans la BDD
      * @return true si classe n'existe pas false sinon
      * @throws SQLException 
      */
    public boolean isClasseExist(ArrayList<String> val) throws SQLException {
        ArrayList<String> resNiv = new ArrayList<>();
        String nomClasse = val.get(0);
        String niveauStr = val.get(1);
        resNiv = bdd.remplirChampsRequete("SELECT id_niveau FROM niveau WhERE nom='" + niveauStr + "'");
        if (!resNiv.isEmpty()) {
            System.out.println("Niveau existe");
            int id_niveau = Integer.parseInt(resNiv.get(0));
            ArrayList<String> resNivCla = bdd.remplirChampsRequete("SELECT id_classe FROM classe WHERE id_niveau=" + id_niveau + " AND nom='" + nomClasse + "'");
            System.out.println(resNivCla.isEmpty());
            return resNivCla.isEmpty();
        } else {
            System.out.println("Classe Existe pas");
            return true;
        }
    }

    /**
     * méthode qui determine si l'élève recherché est dans la base de données : elle servira dans le blindage d'autres fonctions
     * @param val : Arraylist des infos sur l'élève utiles pour trouver un élève dans la BDD
     * @param table : int qui dit si on cherche une Personne dans la table élève ou professeur  : 0prof, 1 eleve.
     * @return true si non trouvé, false sinon
     * @throws SQLException 
     */
    public boolean isPersExiste(ArrayList<String> val, int table) throws SQLException //0 prof, 1 eleve
    {
        ArrayList<String> resultat = new ArrayList<String>();
        if (table == 0) {
            resultat = bdd.remplirChampsRequete("SELECT * FROM professeur WHERE nom='" + val.get(0) + "' AND prenom='" + val.get(1) + "'");
        } else
            resultat = bdd.remplirChampsRequete("SELECT * FROM eleve WHERE nom='" + val.get(0) + "' AND prenom='" + val.get(1) + "'");
        if (resultat.isEmpty()) {
            System.out.println("Personne Inexistante");
        } else {
            System.out.println("Personne Existante");
        }
        return resultat.isEmpty(); //true si champ vide, false si champ existant
    }
    /**
     * méthode qui determine si une discipline existe dans la base de données, utilisée dans le blindage pour d'autres méthodes
     * @param nomDiscipline : String du nom de la discipline recherchée
     * @return true si pas de discipline trouvée, false sinon
     * @throws SQLException 
     */
    public boolean isDisciplineExiste(String nomDiscipline) throws SQLException
    {
        ArrayList<String> tableDis;
        tableDis = bdd.remplirChampsRequete("SELECT id_discipline FROM discipline WHERE nom='"+nomDiscipline+"'");
        return tableDis.isEmpty();
    }
    
    /**
     * méthode qui modifie un élève dans la base de données
     * @param valeurs : ArrayList des informations de l'élève à modifier, et des nouvelles infos à remplacer dans la BDD
     * @return true si opération réussie, false sinon
     * @throws SQLException 
     */
    public boolean modifierEleve(ArrayList<String> valeurs) throws SQLException { //nom, prenom, nouv_nom, nouv_pren, nouv_age
        
        boolean ret = false;
        String nom=valeurs.get(0);
        String prenom=valeurs.get(1);
        String nouv_nom=valeurs.get(2);
        String nouv_prenom=valeurs.get(3);
        String nouv_age=valeurs.get(4);
        
        ArrayList<String> info = new ArrayList();
        info.add(nom);
        info.add(prenom);
        int age = Integer.parseInt(nouv_age);
        if (!isPersExiste(info, 1))
        {
            bdd.executeUpdate("UPDATE eleve SET nom='" + nouv_nom + "', prenom='" + nouv_prenom + "', age=" + age
                    + " WHERE nom='"+nom+"' AND prenom='"+prenom+"'" );
            ret = true;
        }
        return ret;
    }
    

    public boolean modifier(String table, ArrayList<String> valeurs) throws SQLException {
        boolean ret = false;
        if (table == "eleve")
        {
            modifierEleve(valeurs);
            ret = true;
        }
        return ret;
            
    }
    
    /**
     * fonction qui ajoute un élève dans la BDD
     * @param valeurs : ArrayList des infos du nouvel élève
     * @return true si opération réussie, false sinon
     * @throws SQLException 
     */
    public boolean ajoutEleve(ArrayList<String> valeurs) throws SQLException { //nom, prenom, age, td, niveau
        int age = Integer.parseInt(valeurs.get(2), 10);
        boolean ret = false;

        if (isPersExiste(valeurs, 1)) {
            bdd.executeUpdate("INSERT INTO eleve (nom, prenom, age)" + "VALUES ('" + valeurs.get(0) + "', '"
                    + valeurs.get(1) + "', '" + age + "')");
            inscrireEleve(valeurs);
            ret = true;
        }
        return ret;
    }

    public void ajoutNiveau(ArrayList<String> valeurs) throws SQLException {
        bdd.executeUpdate("INSERT INTO niveau (nom)" + "VALUES ('" + valeurs.get(0) + "')");
    }

    /**
     * méthode qui ajoute une discipline supplémentaire dans la BDD
     * @param valeurs : ArrayList des infos de la nouvelle discipline
     * @return true si succès, false sinon
     * @throws SQLException 
     */
    public boolean ajoutDiscipline(ArrayList<String> valeurs) throws SQLException {
        String nomDis = valeurs.get(0);
        boolean ret = false;
        if(isDisciplineExiste(nomDis))
        {
            bdd.executeUpdate("INSERT INTO discipline(nom)" + "VALUES ('" + valeurs.get(0) + "')");
            ret = true;
        }
        return ret;
    }

    /**
     * méthode qui ajoute une nouvelle classe dans la base de données
     * @param valeurs : ArrayList des infos de la nouvelle classe à créer
     * @return true si succès, false sinon
     * @throws SQLException 
     */
    public boolean ajoutClasse(ArrayList<String> valeurs) throws SQLException {

        boolean ret = false;
        if (this.isClasseExist(valeurs)) {
            String nomClasse = valeurs.get(0);
            String niveauStr = valeurs.get(1);
            ArrayList<String> res = bdd.remplirChampsRequete("SELECT id_niveau FROM niveau WHERE nom='" + niveauStr + "'");
            int niveau = Integer.parseInt(res.get(0));
            bdd.executeUpdate("INSERT INTO classe (nom, id_ecole, id_niveau, id_annee)" + "VALUES ('" + nomClasse
                    + "', 1, '" + niveau + "', 2009)");
            ret = true;
        } else {
            System.out.println("Classe déjà existante");
            ret = false;
        }
        return ret;

    }

    /**
     * méthode pour ajouter un professeur dans la BDD
     * @param valeurs : ArrayList contenant les infos du nouveau professeur
     * @return true si succès, false sinon
     * @throws SQLException 
     */
    public boolean ajoutProf(ArrayList<String> valeurs) throws SQLException { //nom, prenom, age, discipline, classe, niveau
        ArrayList<String> resNiv = new ArrayList<String>();
        ArrayList<String> resCla = new ArrayList<String>();
        ArrayList<String> resDis = new ArrayList<String>();
        
        boolean ret = false;
        resNiv = bdd.remplirChampsRequete("SELECT id_niveau FROM niveau WHERE nom='" + valeurs.get(5) + "'");
        resDis = bdd.remplirChampsRequete("SELECT id_discipline FROM discipline WHERE nom='" + valeurs.get(3) + "'");
        if (!resNiv.isEmpty()) {
            int niveau = Integer.parseInt(resNiv.get(0));
            resCla = bdd.remplirChampsRequete(
                    "SELECT id_classe FROM classe WHERE nom='" + valeurs.get(4) + "'AND id_niveau=" + niveau);
            if (!resCla.isEmpty() && !resDis.isEmpty()) {
                int classe = Integer.parseInt(resCla.get(0));

                int age = Integer.parseInt(valeurs.get(2), 10);
                bdd.executeUpdate("INSERT INTO professeur (nom, prenom, age)" + "VALUES ('" + valeurs.get(0) + "', '"
                        + valeurs.get(1) + "', '" + age + "')");

                int discipline = Integer.parseInt(resDis.get(0));

                ArrayList<String> resultat = new ArrayList<String>();
                resultat = bdd.remplirChampsRequete(
                        "SELECT id_professeur FROM professeur WHERE nom='" + valeurs.get(0) + "'");
                int prof = Integer.parseInt(resultat.get(0));

                bdd.executeUpdate("INSERT INTO enseignement (id_classe, id_discipline, id_professeur)" + "VALUES ("
                        + classe + ", " + discipline + ", " + prof + ")");
                ret = true;
            }
        }
        return ret;
    }

    /**
     * méthode qui modifie un professeur dans la BDD
     * @param valeurs : Arraylist des nouvelles infos du professeur
     * @return true si succès, false sinon
     * @throws SQLException 
     */
    public   boolean modifierProf(  ArrayList<String> valeurs) throws SQLException // nom, prenom, discipline, classe, niveau
    {       
            boolean ret = false;
            String nom=valeurs.get(0);
            String prenom=valeurs.get(1);
            ArrayList<String> info=new ArrayList();
            info.add(nom);
            info.add(prenom);
            ArrayList<String> resultat;
            String nouv_disc=valeurs.get(2);
            
            ArrayList<String> verifClasse=new ArrayList();
            verifClasse.add(valeurs.get(3));
            verifClasse.add(valeurs.get(4));
            String nouv_classe=valeurs.get(3);
            String nouv_niveau=valeurs.get(4);
            
            int id_prof;
            int id_disc;
            int id_ens;
            int id_classe;
            int id_niv;
            
            
                    
            if(!isPersExiste(info, 0) && !isClasseExist(verifClasse) && !isDisciplineExiste(nouv_disc))
            {
                
                resultat=bdd.remplirChampsRequete("SELECT id_professeur FROM professeur WHERE nom='"+nom+"'");
                id_prof=Integer.parseInt(resultat.get(0));
                System.out.println(id_prof);
                resultat=bdd.remplirChampsRequete("SELECT id_ens FROM enseignement WHERE id_professeur="+id_prof);
                id_ens=Integer.parseInt(resultat.get(0));
                
                bdd.executeUpdate("DELETE FROM enseignement WHERE id_ens="+id_ens);
                
                ArrayList<String> resNiv=bdd.remplirChampsRequete("SELECT id_niveau FROM niveau WHERE nom='"+nouv_niveau+"'");
                id_niv=Integer.parseInt(resNiv.get(0));
                ArrayList<String> resDisc=bdd.remplirChampsRequete("SELECT id_discipline FROM discipline WHERE nom='"+nouv_disc+"'");
                id_disc=Integer.parseInt(resDisc.get(0));
                ArrayList<String> resCla=bdd.remplirChampsRequete("SELECT id_classe FROM classe WHERE nom='"+nouv_classe+"' AND id_niveau="+id_niv);
                id_classe=Integer.parseInt(resCla.get(0));
                
                bdd.executeUpdate("INSERT INTO enseignement (id_ens, id_classe, id_discipline, id_professeur)" + "VALUES ("+id_ens+", "+id_classe+", "+id_disc+", "+id_prof+")");
                ret = true;
            }
            return ret;
      /*  int id = Integer.parseInt(valeurs.get(0), 10);
        int age = Integer.parseInt(valeurs.get(3), 10);
            ArrayList<String> info=new ArrayList();
            info.add(valeurs.get(1));
            info.add(valeurs.get(2));
            if(!isPersExiste(info, 1))
                bdd.executeUpdate("UPDATE professeur SET nom='" + valeurs.get(1) + "', prenom='" + valeurs.get(2) + "', age="
                + age + " WHERE id_professeur=" + id);
          */
    }

    public void setMoyenne(int detail, int bulletin, int trimestre, int note)
            throws SQLException {
        System.out.println(note);
        ArrayList<String> resultat = bdd
                .remplirChampsRequete("SELECT COUNT(note) FROM evaluation WHERE id_detailbull=" + detail);
        int nbr_note = Integer.parseInt(resultat.get(0));
        System.out.println(nbr_note);
        resultat = bdd.remplirChampsRequete("SELECT moyenne FROM bulletin WHERE id_bulletin=" + bulletin);
        int moy = Integer.parseInt(resultat.get(0));
        System.out.println(moy);
        moy = moy + note;
        System.out.println(moy);
        moy = moy / nbr_note;
        System.out.println(moy);
        System.out.println(resultat.get(0));
        bdd.executeUpdate("UPDATE bulletin SET moyenne=" + moy + " WHERE id_bulletin=" + bulletin);
    }
    
    /**
     * méthode qui ajoute une nouvelle évaluation à un élève
     * @param valeurs : Arraylist des valeurs neccéssaires à l'ajout d'une nouvelle évaluation
     * @return true si succès, false sinon
     * @throws SQLException 
     */
    public boolean ajouterEval(ArrayList<String> valeurs) throws SQLException // nomEleve, prenomEleve, trimestre, nomEval, discipline Note, appréciation
    {   
        boolean ret = false;
        String nomEleve = valeurs.get(0);
        String prenomEleve = valeurs.get(1);
        String trimestreStr = valeurs.get(2);
        int trimestre = Integer.parseInt(trimestreStr);
        String nomEval = valeurs.get(3);
        String disciplineStr=valeurs.get(4);
        String noteStr = valeurs.get(5);
        int note = Integer.parseInt(noteStr);
        String appreciation = valeurs.get(6);
        
        int[] listenote;
        int nb_note=0;

        ArrayList<String> attrEleve = new ArrayList<>();
        attrEleve.add(nomEleve);
        attrEleve.add(prenomEleve);
        int eleve = 1;
        if(!(this.isPersExiste(attrEleve, eleve)))
        {
            ArrayList<String> tabDisc=bdd.remplirChampsRequete("SELECT id_discipline FROM discipline WHERE nom='"+disciplineStr+"'");
            if(!tabDisc.isEmpty())
            {
                int id_disc=Integer.parseInt(tabDisc.get(0));
                
                ArrayList<String> tabEleve = bdd.remplirChampsRequete("SELECT id_eleve FROM eleve WHERE nom= '"+nomEleve+"' AND prenom='"+prenomEleve+"'");
                int id_eleve = Integer.parseInt(tabEleve.get(0));

                ArrayList<String> tabInscription = bdd.remplirChampsRequete("SELECT id_inscription FROM inscription WHERE id_eleve="+id_eleve);
                int id_inscription = Integer.parseInt(tabInscription.get(0));
                
                ArrayList<String> tabClasse=bdd.remplirChampsRequete("SELECT id_classe FROM inscription WHERE id_inscription="+id_inscription);
                int id_clas=Integer.parseInt(tabClasse.get(0));
                
                ArrayList<String> tabEnseignement=bdd.remplirChampsRequete("SELECT id_ens FROM enseignement WHERE id_classe="+id_clas+" AND id_discipline="+id_disc);
                int id_ens=Integer.parseInt(tabEnseignement.get(0));
                
                ArrayList<String> tabBulletin = bdd.remplirChampsRequete("SELECT id_bulletin FROM bulletin WHERE id_inscription="+id_inscription+" AND id_trimestre="+trimestre);
                int id_bulletin = Integer.parseInt(tabBulletin.get(0));
                
                ArrayList<String> tabDetailBulletin = bdd.remplirChampsRequete("SELECT id_detailbull FROM detailbulletin WHERE id_bulletin="+id_bulletin+" And id_ens="+id_ens);
                int id_detailbull = Integer.parseInt(tabDetailBulletin.get(0));

                ArrayList<String> tabEval = bdd.remplirChampsRequete("SELECT id_eval FROM evaluation WHERE nom='"+nomEval+"' AND id_detailbull="+id_detailbull);

                if(tabEval.isEmpty())
                {
                    bdd.executeUpdate("INSERT INTO evaluation (id_detailbull, nom, note, appreciation)"+"VALUES('"+id_detailbull+"','"+nomEval+"',"+note+",'"+appreciation+"')");
                    ret = true;
                }
                else
                {
                    System.out.println("Note portant ce nom déjà existante");
                }
                
                ArrayList<String> details=bdd.remplirChampsRequete("SELECT id_detailbull FROM detailbulletin WHERE id_bulletin="+id_bulletin);
                int [] listeDetail=new int[details.size()];
                listenote=new int[details.size()*3];
                ArrayList<String> tabNote;
                for(int i=0; i<details.size(); i++)
                {
                    listeDetail[i]=Integer.parseInt(details.get(i));
                    tabNote=bdd.remplirChampsRequete("SELECT note FROM evaluation WHERE id_detailbull="+listeDetail[i]);
                    if(!tabNote.isEmpty())
                    {
                        listenote[i]=Integer.parseInt(tabNote.get(0));
                        nb_note++;
                    }
                }
                double moy=0;
                for(int i=0; i<listenote.length; i++)
                    moy=moy+listenote[i];
                moy=moy/nb_note;
                System.out.print(moy);
                bdd.executeUpdate("UPDATE bulletin SET moyenne="+moy+ " WHERE id_bulletin="+id_bulletin);
                
            }
        }
        else
        {
            System.out.println("Personne n'existe pas");
        }
        return ret;
    }

    /**
     * méthode qui inscrit un élève dans la base de données 
     * @param valeurs : ArrayList contenant les infos du nouvel élève 
     * @return return true si succès, false sinon
     * @throws SQLException 
     */
    public boolean inscrireEleve(ArrayList<String> valeurs) throws SQLException {
        boolean ret = false;
        String nom = valeurs.get(0);
        System.out.println(nom);
        String prenom = valeurs.get(1);
        System.out.println(prenom);
        String nomClasse = valeurs.get(3);
        System.out.println(nomClasse);
        String niveauStr = valeurs.get(4);
        System.out.println(niveauStr);

        ArrayList<String> attrClasseNiv = new ArrayList<>();
        attrClasseNiv.add(nomClasse);
        attrClasseNiv.add(niveauStr);

        ArrayList<String> attrPers = new ArrayList<>();
        attrPers.add(nom);
        attrPers.add(prenom);
        int tableEleve = 1;
        if (!this.isClasseExist(attrClasseNiv) && !(this.isPersExiste(attrPers, tableEleve))) {
            ArrayList<String> result = new ArrayList<>();
            String sqlQuery = "SELECT id_eleve FROM eleve WHERE nom='" + nom + "'AND prenom='" + prenom + "'";
            result = bdd.remplirChampsRequete(sqlQuery);
            int id_eleve = Integer.parseInt(result.get(0));
            System.out.println("id_eleve"+id_eleve);

            sqlQuery = "SELECT id_classe FROM classe WHERE nom='" + nomClasse + "'";
            result = bdd.remplirChampsRequete(sqlQuery);
            int id_classe = Integer.parseInt(result.get(0));
            System.out.println("id_classe"+id_classe);
            
            sqlQuery = "INSERT INTO inscription (id_classe, id_eleve)" + "VALUES ('" + id_classe + "', '" + id_eleve + "')";
            bdd.executeUpdate(sqlQuery);

            sqlQuery = "SELECT id_inscription FROM inscription WHERE id_eleve='" + id_eleve + "'AND id_classe='" + id_classe
                    + "'";
            result = bdd.remplirChampsRequete(sqlQuery);
            int id_inscription = Integer.parseInt(result.get(0));

            sqlQuery = "INSERT INTO bulletin (id_trimestre, id_inscription, moyenne)" + "VALUES (1, '" + id_inscription
                    + "', 0)";
            bdd.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO bulletin (id_trimestre, id_inscription, moyenne)" + "VALUES (2, '" + id_inscription
                    + "', 0)";
            bdd.executeUpdate(sqlQuery);
            sqlQuery = "INSERT INTO bulletin (id_trimestre, id_inscription, moyenne)" + "VALUES (3, '" + id_inscription
                    + "', 0)";
            bdd.executeUpdate(sqlQuery);
            
            ArrayList<String> tabBull = bdd.remplirChampsRequete("SELECT id_bulletin FROM bulletin WHERE id_inscription="+id_inscription);
            int nb_bull_par_eleve = tabBull.size();
            ArrayList<String> tabEns = bdd.remplirChampsRequete("SELECT id_ens FROM enseignement WHERE id_classe ="+id_classe);
            int nb_ens_par_classe = tabEns.size();
            System.out.println("Nombre de bulletins par élève :"+nb_bull_par_eleve);
            System.out.println("Nombre d'enseignements pour une classe:" +nb_ens_par_classe);
            for(int i=0; i<nb_bull_par_eleve; i++)
            {
                for(int j=0; j<nb_ens_par_classe; j++)
                {
                    bdd.executeUpdate("INSERT INTO detailbulletin (id_bulletin, id_ens)" + "VALUES('"+tabBull.get(i)+"', '"+tabEns.get(j)+"')");
                }  
            }
            ret = true;
        } else {
            System.out.println("Classe Inexistante ou Eleve Inexistant");
        }
        return ret;
    }
    
    /**
     * méthode qui retourne le nombre d'élèves dans une classe : utile pour afficherClasse.
     * @param val : ArrayList contenant les infos de la classe dans laquelle compter le nombre d'élèves
     * @return
     * @throws SQLException 
     */
    public int numEleve(ArrayList<String> val) throws SQLException
    {
        String nomClasse = val.get(0);
        String niveau = val.get(1);
        ArrayList<String> tabNiv = bdd.remplirChampsRequete("SELECT id_niveau FROM niveau WHERE nom='"+niveau+"'");
        int id_niveau = Integer.parseInt(tabNiv.get(0));
        ArrayList<String> tabClasse = bdd.remplirChampsRequete("SELECT id_classe FROM classe WHERE nom='"+nomClasse+"' AND id_niveau="+id_niveau);
        int id_classe = Integer.parseInt(tabClasse.get(0));
        ArrayList<String>tabEleves = bdd.remplirChampsRequete("SELECT id_eleve FROM inscription WHERE id_classe="+id_classe);
        return tabEleves.size();
    }
    
    /**
     * méthode qui modifie une évaluation 
     * @param val : ArrayList qui contient le nom de l'évaluation à modifier
     * @return true si succès, false sinon
     * @throws SQLException 
     */
    public boolean modifierEval(ArrayList<String> val) throws SQLException {
        boolean ret = false;
        int nouv_eval;
        String nouv_app;
        if (val.get(0) != "") {
            String nom_eval = val.get(0);
            ArrayList<String> resultat = new ArrayList();
            resultat = bdd.remplirChampsRequete("SELECT note FROM evaluation WHERE nom='" + nom_eval + "'");
            if (!resultat.isEmpty()) {
                int anc_note = Integer.parseInt(resultat.get(0));
                resultat = bdd.remplirChampsRequete("SELECT appreciation FROM evaluation WHERE nom='" + nom_eval + "'");
                String anc_app = resultat.get(0);
                if (val.get(1) == "")
                    nouv_eval = anc_note;
                else
                    nouv_eval = Integer.parseInt(val.get(1));
                if (val.get(2) == "")
                    nouv_app = anc_app;
                else
                    nouv_app = val.get(2);
                bdd.executeUpdate("UPDATE evaluation SET appreciation='" + nouv_app + "' WHERE nom='" + nom_eval + "'");
                bdd.executeUpdate("UPDATE evaluation SET note=" + nouv_eval + " WHERE nom='" + nom_eval + "'");
                ret = true;
                int moy = 0;
                int detail, bulletin;
                String[] notes;
                resultat = bdd.remplirChampsRequete("SELECT id_detailbull FROM evaluation WHERE nom='" + nom_eval + "'");
                detail = Integer.parseInt(resultat.get(0));
                resultat = bdd.remplirChampsRequete("SELECT id_bulletin FROM detailbulletin WHERE id_detailbull=" + detail);
                bulletin = Integer.parseInt(resultat.get(0));
                //resultat=bdd.remplirChampsRequete("SELECT moyenne FROM bulletin WHERE id_bulletin="+bulletin);
                // moy=Integer.parseInt(resultat.get(0));
                resultat = bdd.remplirChampsRequete("SELECT COUNT(note) FROM evaluation WHERE nom='" + nom_eval + "'");
                int nbr_note = Integer.parseInt(resultat.get(0));
                resultat = bdd.remplirChampsRequete("SELECT note FROM evaluation WHERE id_detailbull=" + detail);
                notes = new String[resultat.size()];
                for (int i = 0; i < resultat.size(); i++) {
                    notes[i] = resultat.get(i);
                    moy = moy + Integer.parseInt(notes[i]);
                }
                moy = moy / resultat.size();
                bdd.executeUpdate("UPDATE bulletin SET moyenne=" + moy + " WHERE id_bulletin=" + bulletin);
            }
        }
        else
        {
            ret = false;
        }
        return ret;
    }

    /**
     * méthode qui supprime un élève de la base de données
     * @param valeurs : ArrayList contenant les infos de l'élève à supprimer
     * @return true si succès, false sinon
     * @throws SQLException 
     */
    public boolean supEleve(ArrayList<String> valeurs) throws SQLException
    {
        String nomEleve = valeurs.get(0);
        String prenomEleve = valeurs.get(1);
        boolean ret = false;

        ArrayList<String> attrEleve = new ArrayList<>();
        attrEleve.add(nomEleve);
        attrEleve.add(prenomEleve);
        int eleve = 1;
        if(!(this.isPersExiste(attrEleve, eleve)))
        {
            ArrayList<String> tabEleve = bdd.remplirChampsRequete("SELECT id_eleve FROM eleve WHERE nom='"+nomEleve+"' AND prenom='"+prenomEleve+"' ");
            int id_eleve = Integer.parseInt(tabEleve.get(0));
            ArrayList<String> tabInsc = bdd.remplirChampsRequete("SELECT id_inscription FROM inscription WHERE id_eleve="+id_eleve);
            int id_inscription = Integer.parseInt(tabInsc.get(0));

            bdd.executeUpdate("DELETE FROM inscription WHERE id_inscription="+id_inscription);
            bdd.executeUpdate("DELETE FROM eleve WHERE id_eleve="+id_eleve);
            ret = true;
        }
        else
        {
            System.out.println("Eleve non existant");
        }
        return ret;

    }

    /**
     * méthode qui supprime un professeur de la base de données 
     * @param valeurs : ArrayList contenant les infos du professeur à supprimer
     * @return true si succès, false sinon
     * @throws SQLException 
     */
    public boolean supProf(ArrayList<String> valeurs) throws SQLException
    {
        String nomProf = valeurs.get(0);
        String prenomProf = valeurs.get(1);
        boolean ret = false;

        ArrayList<String> attrProf = new ArrayList<>();
        attrProf.add(nomProf);
        attrProf.add(prenomProf);
        int prof = 0;
        if(!(this.isPersExiste(attrProf, prof)))
        {
            ArrayList<String> tabProf = bdd.remplirChampsRequete("SELECT id_professeur FROM professeur WHERE nom='"+nomProf+"' AND prenom='"+prenomProf+"' ");
            int id_prof = Integer.parseInt(tabProf.get(0));

            bdd.executeUpdate("DELETE FROM enseignement WHERE id_professeur="+id_prof);
            bdd.executeUpdate("DELETE FROM professeur WHERE id_professeur="+id_prof);
            ret = true;
        }
        else
        {
            System.out.println("Prof non existant");
        }
        return ret;
    }

    /**
     * méthode qui supprime une évaluation de la BDD
     * @param nom_eval : ArrayList contenant le nom de l'éval à supprimer
     * @return true si succès, false sinon
     * @throws SQLException 
     */
    public   boolean supEval(ArrayList<String> nom_eval) throws SQLException
    {   
        boolean ret = false;
        int moy, nouv_moy=0;
        int detail, bulletin;
        String[] notes;
        ArrayList<String> resultat =new ArrayList<String>();
        resultat=bdd.remplirChampsRequete("SELECT id_detailbull FROM evaluation WHERE nom='"+nom_eval.get(0)+"'");
        if(!resultat.isEmpty()){
            ret = true;
               detail=Integer.parseInt(resultat.get(0));
                resultat=bdd.remplirChampsRequete("SELECT id_bulletin FROM detailbulletin WHERE id_detailbull="+detail);
                bulletin=Integer.parseInt(resultat.get(0));
                resultat=bdd.remplirChampsRequete("SELECT moyenne FROM bulletin WHERE id_bulletin="+bulletin);
                moy=Integer.parseInt(resultat.get(0));
                resultat=bdd.remplirChampsRequete("SELECT COUNT(note) FROM evaluation WHERE nom='"+nom_eval.get(0)+"'");
                int nbr_note=Integer.parseInt(resultat.get(0));
                if(nbr_note==1)
                {
                    bdd.executeUpdate("UPDATE bulletin SET moyenne="+0+" WHERE id_bulletin="+bulletin);
                    bdd.executeUpdate("DELETE FROM evaluation WHERE nom'="+nom_eval.get(0)+"'");
                }
                else
                {
                    bdd.executeUpdate("DELETE FROM evaluation WHERE nom='"+nom_eval.get(0)+"'");
                    resultat=bdd.remplirChampsRequete("SELECT note FROM evaluation WHERE id_detailbull="+detail);
                     notes=new String[resultat.size()];
                    for (int i=0; i<resultat.size(); i++)
                    {
                        notes[i]=resultat.get(i);
                        nouv_moy=nouv_moy+Integer.parseInt(notes[i]);
                    }
                    System.out.println(resultat.size());
                    nouv_moy=nouv_moy/resultat.size();
                    bdd.executeUpdate("UPDATE bulletin SET moyenne="+nouv_moy+" WHERE id_bulletin="+bulletin);
                } 
        }else
        {
            ret = false;
        }
        return ret;
            
    }
    
    /**
     * méthode qui retourne le bulletin d'un élève
     * @param val : infos d'un élève dont on veut le bulletin
     * @return ArrayList si succès, null sinon
     * @throws SQLException 
     */
    public ArrayList<String> afficheBulletin(ArrayList<String> val) throws SQLException //nom, prenom
    {
        if(!isPersExiste(val, 1))
        {   String nom=val.get(0);
            String prenom=val.get(1);
            ArrayList<String> resultat=bdd.remplirChampsRequete("SELECT id_eleve FROM eleve WHERE nom='"+nom+"' AND prenom='"+prenom+"'");
            int id_eleve=Integer.parseInt(resultat.get(0));
            resultat=bdd.remplirChampsRequete("SELECT id_inscription FROM inscription WHERE id_eleve="+id_eleve);
            int id_inscription=Integer.parseInt(resultat.get(0));
            resultat=bdd.remplirChampsRequete("SELECT id_bulletin, id_trimestre, moyenne, appreciation FROM bulletin WHERE id_inscription="+id_inscription);
            
            return resultat;    //retourne id_bulletin, trimestre, moyenne et appreciation
        }
        else return null;
        
    }
    
    
    /**
     * méthode qui retourne le Detail bulletin d'un bulletin spécifique
     * @param val : id_bulletin
     * @return ArrayList d'un detailbulletin, null lorsque Arraylist vide
     * @throws SQLException 
     */
    public ArrayList<String> afficheDetailBulletin(ArrayList<String> val) throws SQLException //id_bulletin
    {
        int bulletin=Integer.parseInt(val.get(0));
        String[] detailbull;
        String[] nom_disc;
        String [] appreciation;
        int [] id_detailbull;
        int[] id_ens;
        int [] id_disc;
        ArrayList<String> resultat=bdd.remplirChampsRequete("SELECT id_detailbull FROM detailbulletin WHERE id_bulletin="+bulletin);
        if(!resultat.isEmpty())
        {
            detailbull=new String[resultat.size()];
            id_detailbull =new int [resultat.size()];
            appreciation=new String[id_detailbull.length];
            for(int i=0; i<resultat.size(); i++)
            {
                detailbull[i]=resultat.get(i);
                id_detailbull[i]=Integer.parseInt(resultat.get(i));
            } //id des bulletins, a renvoyer
         resultat=bdd.remplirChampsRequete("SELECT id_ens FROM detailbulletin WHERE id_bulletin="+bulletin);
        if(!resultat.isEmpty())
        {
            id_ens=new int[resultat.size()];
            id_disc=new int[resultat.size()];
            for (int i=0; i<resultat.size(); i++)
            {
                id_ens[i]=Integer.parseInt(resultat.get(i));
                resultat=bdd.remplirChampsRequete("SELECT id_discipline FROM enseignement WHERE id_ens="+id_ens[i]);
                id_disc[i]=Integer.parseInt(resultat.get(0));
                resultat=bdd.remplirChampsRequete("SELECT nom FROM discipline WHERE id_discipline="+id_disc[i]);
                
            }
            nom_disc=new String[resultat.size()];
            for(int i=0; i<resultat.size(); i++)
                nom_disc[i]=resultat.get(i);
            for(int i=0; i<id_detailbull.length; i++)
            {
                resultat=bdd.remplirChampsRequete("SELECT appreciation FROM detailbulletin WHERE id_detailbull="+id_detailbull[i]);
                appreciation[i]=resultat.get(0);
            }
            for(int i=0; i<id_detailbull.length; i++)
            {
                resultat.set(i,detailbull[i]+", "+nom_disc[i]+", "+appreciation[i] );
            }
            return resultat;
        }
        
    }  return null;  
    }
    
    /**
     * méthode qui modifie un Bulletin
     * @param val : ArrayList d'infos du bulletin id_bulletin, trimestre, nouvelle appréciation
     * @throws SQLException 
     */
    public void modifierBulletin(ArrayList<String> val) throws SQLException //id_bulletin, trimestre, nouv_appr
    {   //dans la fenetre, conserver l'id_bulletin et le renvoyer au s-p
        int id=Integer.parseInt(val.get(0));
        int trimestre=Integer.parseInt(val.get(1));
        String nouv_appr=val.get(2);
        bdd.executeUpdate("UPDATE bulletin SET appreciation='"+nouv_appr+"' WHERE id_bulletin="+id);
        
    }
    
    /**
     * méthode qui modifier le détail bulletin
     * @param val : Arraylist : id du detailbulletin pour savoir lequel modifier, et la nouvelle appréciation
     * @throws SQLException 
     */
    public void modifierDetailBulletin(ArrayList<String> val) throws SQLException   //id_detailbull, nouvelle appreciation
    {
        int detailbull=Integer.parseInt(val.get(0));
        bdd.executeUpdate("UPDATE detailbulletin SET appreciation='"+val.get(1)+"' WHERE id_detailbull="+detailbull);
    }
    
    
    
    
}



