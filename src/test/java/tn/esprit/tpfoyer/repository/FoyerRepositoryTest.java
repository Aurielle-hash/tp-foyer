package tn.esprit.tpfoyer.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import tn.esprit.tpfoyer.entity.Foyer;

import java.util.HashSet;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FoyerRepositoryTest {
    @Mock
    private FoyerRepository foyerRepository;

    private Foyer foyer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
        foyer = new Foyer();
        foyer.setIdFoyer(1L); // Remplacer par un ID approprié si nécessaire
        foyer.setNomFoyer("Foyer A");
        foyer.setCapaciteFoyer(100);
    }

    /*@Test
    public void testSaveFoyer() {
        when(foyerRepository.save(any(Foyer.class))).thenReturn(foyer);

        // Appelle la méthode save du mock
        Foyer savedFoyer = foyerRepository.save(foyer);

        // Vérifie les résultats
        assertThat(savedFoyer).isNotNull();
        assertThat(savedFoyer.getNomFoyer()).isEqualTo(foyer.getNomFoyer());
        assertThat(savedFoyer.getCapaciteFoyer()).isEqualTo(foyer.getCapaciteFoyer());
    }*/

    /*@Test
    public void testFindFoyerById() {
        // Simule le comportement de la méthode findById
        when(foyerRepository.findById(foyer.getIdFoyer())).thenReturn(Optional.of(foyer));

        // Appelle la méthode findById du mock
        Optional<Foyer> foundFoyer = foyerRepository.findById(foyer.getIdFoyer());

        // Vérifie les résultats
        assertThat(foundFoyer).isPresent();
        assertThat(foundFoyer.get().getNomFoyer()).isEqualTo(foyer.getNomFoyer());
    }*/

    /*@Test
    public void testUpdate() {
        Foyer foyer = new Foyer();
        foyer.setNomFoyer("Nom Initial");
        foyer.setCapaciteFoyer(100);
        foyer = foyerRepository.save(foyer);

        foyer.setNomFoyer("Nom Mis à Jour");
        foyer.setCapaciteFoyer(150);
        foyerRepository.save(foyer);

        Optional<Foyer> foundFoyer = foyerRepository.findById(foyer.getIdFoyer());
        assertTrue(foundFoyer.isPresent());
        assertEquals("Nom Mis à Jour", foundFoyer.get().getNomFoyer());
        assertEquals(150, foundFoyer.get().getCapaciteFoyer());
    }*/
    /*@Test
    public void testDelete() {
        // Aucune action à simuler ici, vérifions seulement l'appel
        doNothing().when(foyerRepository).deleteById(any(Long.class));

        // Appelle la méthode deleteById du mock
        foyerRepository.deleteById(foyer.getIdFoyer());

        // Vérifie que deleteById a été appelé
        verify(foyerRepository, times(1)).deleteById(foyer.getIdFoyer());
    }*/


}
