package com.monibus.moniteurbus.service;

import com.monibus.moniteurbus.dto.BusDTO;

import java.util.List;

public interface IBus {

    public BusDTO addBus(BusDTO busDTO);
    public BusDTO modBus(BusDTO busDTO);
    public List<BusDTO> afficherBuses();
    public BusDTO afficherBus(long id);
    public boolean delBus(long id);
}
