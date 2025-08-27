package com.example.turistguide.service;

import com.example.turistguide.model.TouristAttraction;
import com.example.turistguide.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {

    private TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository){
        this.touristRepository = touristRepository;
    }

    public TouristAttraction findAttractionByName(String name) {
        return touristRepository.findAttractionByName(name);
    }

    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction){
        return touristRepository.addTouristAttraction(touristAttraction);
    }

    public List<TouristAttraction> getAllTouristAttraction(){
        return touristRepository.getAllTouristAttraction();
    }

    public TouristAttraction getTouristAttraction(String name){
        return touristRepository.getTouristAttraction(name);
    }

    public TouristAttraction updateTouristAttraction(TouristAttraction touristAttraction){
        return touristRepository.updateTouristAttraction(touristAttraction);
    }

    public void deleteTouristAttraction(String name){
        touristRepository.deleteTouristAttraction(name);
    }

}
