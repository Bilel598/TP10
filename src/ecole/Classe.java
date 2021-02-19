package ecole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Classe {
    private String nomClasse;
    private annee annee;
    private ArrayList<Eleve> listeEleve;
    private HashMap<Matiere, List<Double>> listeNoteClasse = new HashMap<>();


    public Classe(annee annee, String nomClasse){
        this.nomClasse = annee + nomClasse;
        this.annee = annee;
        this.listeEleve = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Eleve eleve = new Eleve(this.nomClasse);
            listeEleve.add(eleve);
        }
    }


    public HashMap<Matiere, List<Double>> getListeNoteClasse() {

        for(Eleve eleve : listeEleve) {
            eleve.getListeNote().forEach( (matiere, note) -> {
                listeNoteClasse.merge(matiere, note, (v1, v2) -> Stream.concat(v1.stream(), v2.stream()).collect(Collectors.toList()));
            });
        }
        return listeNoteClasse;
    }

    public void setListeNoteClasse(HashMap<Matiere, List<Double>> listeNoteClasse) {
        this.listeNoteClasse = listeNoteClasse;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public ecole.annee getAnnee() {
        return annee;
    }

    public void setAnnee(ecole.annee annee) {
        this.annee = annee;
    }

    public ArrayList<Eleve> getListeEleve() {
        return listeEleve;
    }

    public void setListeEleve(ArrayList<Eleve> listeEleve) {
        this.listeEleve = listeEleve;
    }

    @Override
    public String toString() {
        return nomClasse + '\'' + ", annee=" + annee + "\n";
    }
}

enum annee {
    SIXIEME,
    CINQUIEME,
    QUATRIEME,
    TROISIEME
}
