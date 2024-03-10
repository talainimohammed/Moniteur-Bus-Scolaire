package com.monibus.busmicroservice.service;

import com.monibus.busmicroservice.dto.BusDTO;

import java.util.List;

public interface IBus {

    public BusDTO addBus(BusDTO busDTO);
    public BusDTO modBus(BusDTO busDTO,long id);
    public List<BusDTO> afficherBuses();
    public BusDTO afficherBus(long id);
    public boolean delBus(long id);
}
