package tn.esprit.tpfoyer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.repository.FoyerRepositoryTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FoyerServiceImplTest {
    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des mocks
    }

    @Test
    public void testRetrieveAllFoyers() {
        List<Foyer> mockFoyers = new ArrayList<>();
        mockFoyers.add(new Foyer(1L, "Foyer A", 100)); // Remplacez par le constructeur approprié
        mockFoyers.add(new Foyer(2L, "Foyer B", 200));

        when(foyerRepository.findAll()).thenReturn(mockFoyers);

        List<Foyer> listFoyers = foyerService.retrieveAllFoyers();

        Assertions.assertNotNull(listFoyers); // Vérifie que la liste n'est pas nulle
        Assertions.assertEquals(2, listFoyers.size()); // Vérifie le nombre d'éléments
    }
    /*@Test
    public void testCreate() {
        when(foyerRepository.save(foyer)).thenReturn(foyer);
        Foyer createdFoyer = foyerService.addFoyer(foyer);
        assertNotNull(createdFoyer);
        assertEquals(foyer.getNomFoyer(), createdFoyer.getNomFoyer());
        verify(foyerRepository, times(1)).save(foyer);
    }*/

    @Test
    public void testAddFoyer() {
        Foyer f = new Foyer(1L, "Foyer A", 100); // Remplacez par le constructeur approprié
        when(foyerRepository.save(any(Foyer.class))).thenReturn(f);

        Foyer savedFoyer = foyerService.addFoyer(f);

        Assertions.assertNotNull(savedFoyer); // Vérifie que le foyer est bien ajouté
        Assertions.assertEquals("Foyer A", savedFoyer.getNomFoyer()); // Vérifie le nom
    }

    @Test
    public void testRetrieveFoyer(){
        Foyer f = new Foyer(1L, "Foyer A", 100);
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(f));
        Foyer foyer = foyerService.retrieveFoyer(1L);
        Assertions.assertEquals("Foyer A", foyer.getNomFoyer()); // Vérifie que le foyer récupéré est correct
    }

    /*@Test
    public void testUpdate() {
        Foyer f = new Foyer(1L, "Foyer A", 100);
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(f));
        when(foyerRepository.save(any(Foyer.class))).thenReturn(f);
        f.setCapaciteFoyer(150); // Modification de la capacité
        Foyer updatedFoyer = foyerService.modifyFoyer(f);
        Assertions.assertEquals(150, updatedFoyer.getCapaciteFoyer()); // Vérifie que la modification est correcte
    }*/

    @Test
    public void testDelete() {
        Foyer f = new Foyer(1L, "Foyer A", 100);
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(f));
        doNothing().when(foyerRepository).deleteById(1L);

        foyerService.removeFoyer(1L); // Appelle le service de suppression

        // Vérification que la méthode deleteById a été appelée
        verify(foyerRepository, times(1)).deleteById(1L);

        // Vérification que le foyer a bien été supprimé
        when(foyerRepository.findById(1L)).thenReturn(Optional.empty());

        // Vérifiez qu'une exception est levée lorsque l'on essaie de récupérer un foyer supprimé
        assertThrows(Exception.class, () -> {
            foyerService.retrieveFoyer(1L); // Vérifie que le foyer est bien supprimé
        });
    }
}
