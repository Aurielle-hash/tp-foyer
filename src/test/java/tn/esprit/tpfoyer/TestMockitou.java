package tn.esprit.tpfoyer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.class)

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TestMockitou {
    @Mock

    private ChambreRepository chambreRepository;
    @InjectMocks
    private ChambreServiceImpl chambreService;

    @Order(1)
    @Test
    public void testAddChambre() {
        // Arrange: Define a chambre object and mock the save method
        Chambre chambre = new Chambre("ch1", "ch2", new Date(), TypeChambre.SIMPLE);
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // Act: Call the addChambre method in service
        Chambre savedChambre = chambreService.addChambre(chambre);

        // Assert: Check that the chambre was saved correctly
        assertNotNull(savedChambre);
        assertEquals(chambre, savedChambre);
    }

    @Order(2)
    @Test
    public void testRetrieveChambreById() {
        // Arrange: Define a chambre and mock the findById method
        Chambre chambre = new Chambre("ch1", "ch2", new Date(), TypeChambre.SIMPLE);
        chambre.setIdChambre(1L);
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        // Act: Call the retrieveChambre method in service
        Chambre foundChambre = chambreService.retrieveChambre(1L);

        // Assert: Verify that the retrieved chambre matches the expected result
        assertNotNull(foundChambre);
        assertEquals(1L, foundChambre.getIdChambre());
    }

    @Order(3)
    @Test
    public void testRetrieveAllChambres() {
        // Arrange: Mock a list of chambres
        Chambre chambre = new Chambre("ch1", "ch2", new Date(), TypeChambre.SIMPLE);
        List<Chambre> chambreList = Collections.singletonList(chambre);
        when(chambreRepository.findAll()).thenReturn(chambreList);

        // Act: Call the retrieveAllChambres method in service
        List<Chambre> result = chambreService.retrieveAllChambres();

        // Assert: Verify that the expected list of chambres is returned
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(chambreList, result);
    }

    @Order(4)
    @Test
    public void testRemoveChambre() {
        // Arrange: Define a chambre ID to delete
        Long chambreId = 1L;

        // Act: Call the removeChambre method in service
        chambreService.removeChambre(chambreId);

        // Assert: Verify that deleteById was called in repository
        verify(chambreRepository, times(1)).deleteById(chambreId);
    }



    @Order(5)
    @Test
    public void testModifyChambre() {
        // Arrange: Define a chambre to update and mock the save method
        Chambre chambre = new Chambre("ch1", "ch2", new Date(), TypeChambre.SIMPLE);
        chambre.setIdChambre(1L);
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        // Act: Call the modifyChambre method in service
        Chambre updatedChambre = chambreService.modifyChambre(chambre);

        // Assert: Verify that the updated chambre matches the expected result
        assertNotNull(updatedChambre);
        assertEquals(chambre, updatedChambre);
    }


    @Order(6)
    @Test
    public void testRecupererChambresSelonTyp() {
        // Arrange: Mock a list of chambres based on TypeChambre
        Chambre chambre = new Chambre("ch1", "ch2", new Date(), TypeChambre.SIMPLE);
        List<Chambre> chambreList = Collections.singletonList(chambre);
        when(chambreRepository.findByTypeC(TypeChambre.SIMPLE)).thenReturn(chambreList);

        // Act: Call the recupererChambresSelonTyp method in service
        List<Chambre> result = chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE);

        // Assert: Verify that the expected list of chambres is returned
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(chambreList, result);
    }





    //Chambre chambre= new Chambre("ch1","ch2",new Date(), TypeChambre.SIMPLE);
   //List<Chambre> chambreList=new ArrayList<Chambre>(){
      //void add(new Chambre("f3","f4",new Date(), TypeChambre.SIMPLE));
  // };


  // List<Chambre>result = Collections.singletonList(ChambreServiceImpl.retrieveChambre());
   //assertEquals(Chambre,result);

}
