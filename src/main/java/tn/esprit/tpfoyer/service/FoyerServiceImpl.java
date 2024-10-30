package tn.esprit.tpfoyer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    FoyerRepository foyerRepository;
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }
    public Foyer retrieveFoyer(Long foyerId) {
        return foyerRepository.findById(foyerId).get();
    }
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }
    public Foyer modifyFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    public void removeFoyer(Long foyerId) {
        foyerRepository.deleteById(foyerId);
    }

    public Optional<Foyer> read(Long id) {
        return foyerRepository.findById(id);
    }

    public Foyer update(Long id, Foyer updatedFoyer) {
        Optional<Foyer> existingFoyer = foyerRepository.findById(id);
        if (existingFoyer.isPresent()) {
            Foyer foyer = existingFoyer.get();
            foyer.setNomFoyer(updatedFoyer.getNomFoyer());
            return foyerRepository.save(foyer);
        } else {
            throw new RuntimeException("Foyer not found");
        }
    }

    public void delete(Long id) {
        foyerRepository.deleteById(id);
    }
}
