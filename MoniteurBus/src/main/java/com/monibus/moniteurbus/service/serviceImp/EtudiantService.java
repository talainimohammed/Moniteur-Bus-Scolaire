package com.monibus.moniteurbus.service.serviceImp;

import com.monibus.moniteurbus.dto.EcoleDTO;
import com.monibus.moniteurbus.dto.EtudiantDTO;
import com.monibus.moniteurbus.entity.Etudiant;
import com.monibus.moniteurbus.repository.EtudiantRepository;
import com.monibus.moniteurbus.service.IEtudiant;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService implements IEtudiant {

    private EtudiantRepository etudiantRepository;
    private ModelMapper modelMapper;
    public EtudiantService(EtudiantRepository etudiantRepository,ModelMapper modelMapper){
        this.etudiantRepository=etudiantRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public EtudiantDTO addEtudiant(EtudiantDTO etudiantDTO) {
        if(etudiantDTO==null) throw new NullPointerException();
        return modelMapper.map(this.etudiantRepository.save(modelMapper.map(etudiantDTO, Etudiant.class)),EtudiantDTO.class);
    }

    @Override
    public EtudiantDTO modEtudiant(EtudiantDTO etudiantDTO) {
        if(etudiantDTO==null) throw new NullPointerException();
        return modelMapper.map(this.etudiantRepository.save(modelMapper.map(etudiantDTO, Etudiant.class)),EtudiantDTO.class);

    }

    @Override
    public List<EtudiantDTO> afficherEtudiants() {
        return this.etudiantRepository.findAll().stream().map(e->modelMapper.map(e,EtudiantDTO.class)).toList();
    }

    @Override
    public EtudiantDTO afficherEtudiantById(long id) {
        Etudiant etudiant=this.etudiantRepository.findById(id).orElse(null);
        if(etudiant==null) throw new NullPointerException();
        return modelMapper.map(etudiant, EtudiantDTO.class);
    }

    @Override
    public boolean delEtudiant(long id) {
        Etudiant etudiant=this.etudiantRepository.findById(id).orElse(null);
        if(etudiant==null) throw new NullPointerException();
        etudiant.setDeleted(true);
        return this.etudiantRepository.save(etudiant).isDeleted();
    }
}
