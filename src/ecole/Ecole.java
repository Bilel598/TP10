package ecole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ecole {
    private String nomEcole;
    private ArrayList<Classe> listeDesClasse;

    public Ecole(String name){
        this.nomEcole = name;
        this.listeDesClasse = new ArrayList<>();
        for (annee annee : annee.values()){
            for(char alphabet = 'A'; alphabet <='F'; alphabet++ ) {
                listeDesClasse.add(new Classe(annee, String.valueOf(alphabet)));
            }
        }
    }

    public void genererNotes(){
        for (Classe classe : this.listeDesClasse) {
            for (Eleve eleve : classe.getListeEleve()) {
                int matiereAleatoire = (int) (Math.random() * 3);
                ArrayList<Matiere> matieres = new ArrayList<>(Arrays.asList(Matiere.values()));

                if(classe.getAnnee() == annee.SIXIEME) {
                    matieres.remove(Matiere.LANGUE_VIVANTE);
                    matieres.remove(Matiere.PHYSIQUE);
                }

                for (Matiere matiere : matieres) {
                    List<Double> notes = new ArrayList<>();
                    Double somme = 0.0;
                    int nombreNote = (matiere == Matiere.MUSIQUE || matiere == Matiere.SPORT) ? 2 : 3;
                    for (int i = 0; i < nombreNote; i++){
                        notes.add(Math.round(Math.random() * (21) * 100.0) / 100.0);
                        somme += notes.get(i);
                    }
                    notes.add(Math.round(somme / notes.size() * 100.0) / 100.0);
                    eleve.setListeNote(matiere, notes);
                    // Trouver la solution pour extend le enum pour rajouter les matieres optionnel
                }
                System.out.println(eleve.getListeNote().toString() + eleve.getMoyenneGenerale());
            }
            //System.out.println(classe.getListeNoteClasse());
        }
    }

    public ArrayList<Classe> getListeDesClasse() {
        return listeDesClasse;
    }

    public void setListeDesClasse(ArrayList<Classe> listeDesClasse) {
        this.listeDesClasse = listeDesClasse;
    }

    public String getNomEcole() {
        return nomEcole;
    }

    public void setNomEcole(String nomEcole) {
        this.nomEcole = nomEcole;
    }

}
