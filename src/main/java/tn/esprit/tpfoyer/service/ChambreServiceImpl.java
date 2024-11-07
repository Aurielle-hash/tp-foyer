package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChambreServiceImpl implements IChambreService {

    private final ChambreRepository chambreRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        log.info("In Method retrieveAllChambres:");
        List<Chambre> listC = chambreRepository.findAll();
        log.info("Out of retrieveAllChambres:");
        return listC;
    }

    @Override
    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).orElse(null);
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre modifyChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }

    @Override
    public List<Chambre> recupererChambresSelonTyp(TypeChambre tc) {
        return chambreRepository.findAllByTypeC(tc);
    }

    @Override
    public Chambre trouverchambreSelonEtudiant(long cin) {
        return chambreRepository.trouverChselonEt(cin);
    }
}
