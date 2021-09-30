package co.edu.uniandes.dse.parcialejemplo.services;

import  static  org.junit.jupiter.api.Assertions.assertEquals;
import  java.util.ArrayList;
import  java.util.List;
import  org.junit.jupiter.api.BeforeEach;
import  org.junit.jupiter.api.Test;
import  org.junit.jupiter.api.extension.ExtendWith;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import  org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import  org.springframework.context.annotation.Import;
import  org.springframework.test.context.junit.jupiter.SpringExtension;
import  org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import  uk.co.jemos.podam.api.PodamFactory;
import  uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoService.class)
public class MedicoServiceTest {
    @Autowired
    private MedicoService medicoService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();
    private List<MedicoEntity> medicoList = new ArrayList<>();

    @BeforeEach
    void setUp() throws Exception {
        clearData();
        insertData();
    }

    private void clearData() {
        entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
    }

    private void insertData() {
        for (int i=0; i<5; i++) {
            MedicoEntity medico = factory.manufacturePojo(MedicoEntity.class);
            entityManager.persist(medico);
            medicoList.add(medico);
        }
    }

    @Test
    void testGetMedicos() {
        List<MedicoEntity> list = medicoService.getMedicos();
        assertEquals(list.size(), medicoList.size());

        MedicoEntity medico1 = list.get(0);
        MedicoEntity medico2 = entityManager.find(MedicoEntity.class, medico1.getId());

        assertEquals(medico2.getNombre(), medico1.getNombre());
        assertEquals(medico2.getApellido(), medico1.getApellido());
        assertEquals(medico2.getRegistroMedico(), medico1.getRegistroMedico());
        assertEquals(medico2.getEspecialidad(), medico1.getEspecialidad());
    }
}
