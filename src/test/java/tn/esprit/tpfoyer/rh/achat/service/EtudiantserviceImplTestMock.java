package tn.esprit.tpfoyer.rh.achat.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EtudiantserviceImplTestMock {
    @Mock
    EtudiantRepository etudiantRepository;
    @InjectMocks
    private EtudiantServiceImpl etudiantService;
    @InjectMocks
        Etudiant etudiant = new Etudiant("Assil", "Dahmeni", 165425, new Date(1994/ 07/ 01));
    List<Etudiant> listEtudiants = new ArrayList<Etudiant>() {
        {
            add(new Etudiant("Sami", "Sami", 123456, new Date(1999/12/11)));
            add(new Etudiant("Islem", "BenHammed", 255368874, new Date(1999/11/10)));
        }
    };


    @Test
    public void testerRetrieveEtudientMock() {
        when(etudiantService.retrieveAllEtudiants()).thenReturn(listEtudiants);
        List<Etudiant> result = etudiantService.retrieveAllEtudiants();
        assertEquals(2, result.size());
        assertEquals("Sami", result.get(0).getNomEtudiant());
        assertEquals(Long.valueOf(123456), result.get(0).getCinEtudiant());






    }









}
