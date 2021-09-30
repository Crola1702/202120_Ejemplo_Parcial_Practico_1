package co.edu.uniandes.dse.parcialejemplo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;

@Service
public class MedicoService {
    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public List<MedicoEntity> getMedicos() {
        return medicoRepository.findAll();
    }
}
