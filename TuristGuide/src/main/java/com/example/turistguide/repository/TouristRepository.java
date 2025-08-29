package com.example.turistguide.repository;

import com.example.turistguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {

    private final List<TouristAttraction> attractions;

    public TouristRepository() {

        attractions = new ArrayList<>();
        attractions.add(new TouristAttraction("Tivoli", "Forlystelsespark i København"));
        attractions.add(new TouristAttraction("Bakken", "Forlystelsespark i ydre København"));
        attractions.add(new TouristAttraction("Djurs sommerland", "Danmarks bedste rutschebaner, det kæmpestore Vandland og over 60 sjove forlystelser for både små og store legebørn."));

    }

    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction){
        if(touristAttraction.getDescription() == null){
            touristAttraction.setDescription("Ingen oplysninger");
        }
        attractions.add(touristAttraction);
        return touristAttraction;
    }

    public TouristAttraction findAttractionByName(String name){
        for (TouristAttraction touristAttraction : attractions){
            if(touristAttraction.getName().equalsIgnoreCase(name)){
                return touristAttraction;
            }
        }
        return null;
    }

    public List<TouristAttraction> getAllTouristAttraction(){
        return attractions;
    }
    public TouristAttraction getTouristAttraction(String name){
        return findAttractionByName(name);
    }

    public TouristAttraction updateTouristAttraction(TouristAttraction updatedTouristAttraction){
        TouristAttraction touristAttraction = findAttractionByName(updatedTouristAttraction.getName());
        deleteTouristAttraction(touristAttraction.getName());

        //check all attributes of TouristAttraction class for updated information
        if(updatedTouristAttraction.getDescription() != null){
            touristAttraction.setDescription(updatedTouristAttraction.getDescription());
        }

        //resolve update
        attractions.add(touristAttraction);
        return touristAttraction;
    }

    public void deleteTouristAttraction(String name){
        for (TouristAttraction touristAttraction : attractions){
            if(touristAttraction.getName().equalsIgnoreCase(name)){
                attractions.remove(touristAttraction);
                return;
            }
        }
    }

}
