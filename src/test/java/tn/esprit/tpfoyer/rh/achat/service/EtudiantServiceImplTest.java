package tn.esprit.tpfoyer.rh.achat.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.IEtudiantService;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl ;

import java.util.Date;
import java.util.List;
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
//@ContextConfiguration(classes = {EtudiantServiceImpl.class})
public class EtudiantServiceImplTest {

    @Autowired
    IEtudiantService etudientService;
    @Test
    @Order(1)
    public void testRetrieveAllEtudients() {
        List<Etudiant> listEtudiant = etudientService.retrieveAllEtudiants();
        Assertions.assertNotNull(listEtudiant); // Vérifie que la liste n'est pas nulle
    }


    @Test
    @Order(2)
    public void testAddReservation() {
        Etudiant e = new Etudiant();

        e.setNomEtudiant("Assil");
        e.setPrenomEtudiant("Dahmeni");
        e.setCinEtudiant(Long.parseLong("0165425"));
        e.setDateNaissance(new Date("13/07/1993"));
       Etudiant savedEtudiant = etudientService.addEtudiant(e);
      Assertions.assertNotNull(savedEtudiant); // Vérifie que la réservation est bien ajoutée
    }




   @Test
    @Order(3)
    public void testRetrieveEtudiant() {

       Etudiant etudiant = etudientService.retrieveEtudiant(Long.valueOf(1));
       //System.out.println("Etudiant retrieved: " + etudiant);

       Assertions.assertEquals(Long.valueOf(1), etudiant.getIdEtudiant());
// Vérifie que la réservation récupérée est correcte
    }


    @Test
    @Order(4)
    public void deleteEtudiant() {
        Etudiant etudiant = etudientService.removeEtudiant(Long.valueOf(2));
    }




}
