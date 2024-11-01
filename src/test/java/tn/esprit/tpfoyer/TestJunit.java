package tn.esprit.tpfoyer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.List;

@TestMethodOrder(MethodOrderer.class)
@SpringBootTest
@Slf4j
public class TestJunit {
    private ChambreServiceImpl chambreService;

    @Test
    @Order(0)
    public void testRetriveAllChambre(){
        List<Chambre> chambreList=chambreService.retrieveAllChambres();
        Assertions.assertEquals(0,chambreList.size());
    }
}
