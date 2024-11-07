package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambres();
    Chambre retrieveChambre(Long chambreId);
    Chambre addChambre(Chambre c);
    void removeChambre(Long chambreId);
    Chambre modifyChambre(Chambre chambre);
    Chambre trouverchambreSelonEtudiant(long cin);
    List<Chambre> recupererChambresSelonTyp(TypeChambre tc);
}
