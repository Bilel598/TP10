package ecole;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Eleve {

    private String nom;
    private String prenom;
    private String classe;
    private HashMap<Matiere, List<Double>> listeNote = new HashMap<>();

    public Eleve(String classe) {
        Faker faker = new Faker();
        this.nom = faker.name().lastName();
        this.prenom = faker.name().firstName();
        this.classe = classe;
    }

    public Double getMoyenneGenerale(){
        final Double[] sommeMoyenne = {0.0};
        listeNote.forEach( (matiere, notes) -> {
            sommeMoyenne[0] += notes.get(notes.size() - 1);
        });
        System.out.println(sommeMoyenne[0]/9);
        return 0.0;
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public HashMap<Matiere, List<Double>> getListeNote() {
        return listeNote;
    }

    public void setListeNote(Matiere matiere, List<Double> notes) {
        listeNote.put(matiere, notes);
    }

    public void afficherEleve(){
        System.out.println("\n" + nom + ' ' + prenom + " en " + classe);
        for(Matiere m : Matiere.values()) {
            System.out.println(m.toString() + listeNote.get(m) + ", ");
        }
    }
}
