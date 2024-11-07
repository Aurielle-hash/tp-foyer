package tn.esprit.tpfoyer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class TestMockitou {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    @Order(1)
    @Test
    public void testAddChambre() {
        Chambre chambre = new Chambre("ch1", "ch2", new Date(), TypeChambre.SIMPLE);
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre savedChambre = chambreService.addChambre(chambre);

        assertNotNull(savedChambre);
        assertEquals(chambre, savedChambre);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Order(2)
    @Test
    public void testRetrieveChambreById() {
        Chambre chambre = new Chambre("ch1", "ch2", new Date(), TypeChambre.SIMPLE);
        chambre.setIdChambre(1L);
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        Chambre foundChambre = chambreService.retrieveChambre(1L);

        assertNotNull(foundChambre);
        assertEquals(1L, foundChambre.getIdChambre());
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Order(3)
    @Test
    public void testRetrieveAllChambres() {
        Chambre chambre = new Chambre("ch1", "ch2", new Date(), TypeChambre.SIMPLE);
        List<Chambre> chambreList = Collections.singletonList(chambre);
        when(chambreRepository.findAll()).thenReturn(chambreList);

        List<Chambre> result = chambreService.retrieveAllChambres();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(chambreList, result);
        verify(chambreRepository, times(1)).findAll();
    }

    @Order(4)
    @Test
    public void testRemoveChambre() {
        Long chambreId = 1L;

        chambreService.removeChambre(chambreId);

        verify(chambreRepository, times(1)).deleteById(chambreId);
    }

    @Order(5)
    @Test
    public void testModifyChambre() {
        Chambre chambre = new Chambre("ch1", "ch2", new Date(), TypeChambre.SIMPLE);
        chambre.setIdChambre(1L);
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre updatedChambre = chambreService.modifyChambre(chambre);

        assertNotNull(updatedChambre);
        assertEquals(chambre, updatedChambre);
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Order(6)
    @Test
    public void testRecupererChambresSelonTyp() {
        Chambre chambre = new Chambre("ch1", "ch2", new Date(), TypeChambre.SIMPLE);
        List<Chambre> chambreList = Collections.singletonList(chambre);
        when(chambreRepository.findAllByTypeC(TypeChambre.SIMPLE)).thenReturn(chambreList);

        List<Chambre> result = chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(chambreList, result);
        verify(chambreRepository, times(1)).findAllByTypeC(TypeChambre.SIMPLE);
    }

    @Order(7)
    @Test
    public void testFindChambreByStudentCIN() {
        Chambre chambre = new Chambre("ch1", "ch2", new Date(), TypeChambre.SIMPLE);
        when(chambreRepository.trouverChselonEt(123456L)).thenReturn(chambre);

        Chambre result = chambreService.trouverchambreSelonEtudiant(123456L);

        assertNotNull(result);
        assertEquals(chambre, result);
        verify(chambreRepository, times(1)).trouverChselonEt(123456L);
    }
}
