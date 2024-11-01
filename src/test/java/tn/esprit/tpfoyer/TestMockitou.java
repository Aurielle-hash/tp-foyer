package tn.esprit.tpfoyer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TestMethodOrder(MethodOrderer.class)

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TestMockitou {
    @Mock
            @Order(4)
    ChambreRepository chambreRepository= Mockito.mock(ChambreRepository.class);
    Chambre chambre= new Chambre("ch1","ch2",new Date(), TypeChambre.SIMPLE);
   List<Chambre> chambreList=new ArrayList<Chambre>(){
      void add(new Chambre("f3","f4",new Date(), TypeChambre.SIMPLE));
   };


   List<Chambre>result = ChambreServiceImpl.retrieveChambre();
   //assertEquals(Chambre,result);

}
