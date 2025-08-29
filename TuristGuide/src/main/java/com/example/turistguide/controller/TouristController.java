package com.example.turistguide.controller;
import com.example.turistguide.model.TouristAttraction;
import com.example.turistguide.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private TouristService touristService;

    public TouristController(TouristService touristService){
        this.touristService = touristService;
    }

    @PostMapping("/add")
    public ResponseEntity<TouristAttraction> addTouristAttraction(@RequestBody TouristAttraction touristAttraction){

        if(touristAttraction.getName() != null  && !touristService.doesAttractionExist(touristAttraction)) {

            return new ResponseEntity<>(touristService.addTouristAttraction(touristAttraction), HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public ResponseEntity<List<TouristAttraction>> getAllTouristAttraction(){

        return new ResponseEntity<>(touristService.getAllTouristAttraction(), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<TouristAttraction> getTouristAttraction(@PathVariable(required = false) String name){
        TouristAttraction touristAttraction = touristService.getTouristAttraction(name);
        if(touristAttraction != null){
            return new ResponseEntity<>(touristAttraction, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update")
    public ResponseEntity<TouristAttraction> updateTouristAttraction(@RequestBody TouristAttraction touristAttraction){
        if(touristService.doesAttractionExist(touristAttraction)) {
            return new ResponseEntity<>(touristService.updateTouristAttraction(touristAttraction), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete/{name}")
    public ResponseEntity<TouristAttraction> deleteTouristAttraction(@PathVariable String name){
        TouristAttraction deletedTouristAttraction = touristService.findAttractionByName(name);
        if(deletedTouristAttraction != null) {
            touristService.deleteTouristAttraction(name);
            return new ResponseEntity<>(deletedTouristAttraction, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
