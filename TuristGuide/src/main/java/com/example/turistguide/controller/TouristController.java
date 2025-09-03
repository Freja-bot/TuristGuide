package com.example.turistguide.controller;

import com.example.turistguide.model.TouristAttraction;
import com.example.turistguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/attractions")
public class TouristController {

    private TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }

    private boolean doesAttractionExist(TouristAttraction touristAttraction) {
        return (touristService.findAttractionByName(touristAttraction.getName()) != null);
    }


    @GetMapping()
    public String getAllTouristAttraction(Model model) {
        model.addAttribute("attractions", touristService.getAllTouristAttraction());
        return "attractionList";
    }

    @GetMapping("{name}")
    public String getTouristAttraction(@PathVariable(required = false) String name, Model model) {
        TouristAttraction touristAttraction = touristService.getTouristAttraction(name);
        if (touristAttraction != null) {
            model.addAttribute("attraction", touristAttraction);
            return "show-attraction";
        }
        return "redirect:/attractions";
    }

    @GetMapping("{name}/tags")
    public String showTagsForAttractions() {
        return "";
    }

    @GetMapping("/add")
    public String showTouristAttractionForm(Model model) {
        TouristAttraction touristAttraction = new TouristAttraction();
        model.addAttribute("attraction", touristAttraction);
        return "add-Attraction-From";
    }

    @PostMapping("/save")
    public String addTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        TouristAttraction newAttraction = touristAttraction;
        if (newAttraction.getName().isEmpty() || newAttraction.getDescription().isEmpty()) {
            return "redirect:/attractions/add";
        }
        if (touristService.addTouristAttraction(newAttraction) == null) {
            return "redirect:/attractions/add";
        }
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String updateAttraction(@PathVariable String name, Model model) {
        TouristAttraction touristAttraction = touristService.getTouristAttraction(name);
        if (touristAttraction == null) {
            return "redirect:/attractions";
        }
        model.addAttribute("attraction", touristAttraction);
        return "update-attraction";
    }

    @PostMapping("/update")
    public String updateTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        touristService.updateTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/delete/{name}")
    public String deleteTouristAttraction(@ModelAttribute TouristAttraction touristAttraction) {
        TouristAttraction deletedTouristAttraction = touristService.findAttractionByName(touristAttraction.getName());
        touristService.deleteTouristAttraction(touristAttraction.getName());
        return "redirect:/attractions";
    }

}
