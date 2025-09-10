package com.example.turistguide.controller;
import com.example.turistguide.model.TouristAttraction;
import com.example.turistguide.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/attractions")
public class TouristController {
    //TODO:
    // lav endpoint /attractions/{nam}/tags
    // lav fail states
    private final TouristService touristService;

    public TouristController(TouristService touristService){
        this.touristService = touristService;
    }

    @GetMapping()
    public String getAllTouristAttraction(Model model){
        model.addAttribute("touristAttractions", touristService.getAllTouristAttraction());
        return "attractionList";
    }

    @GetMapping("/add")
    public String showAddAttractionForm(Model model){
        model.addAttribute("touristAttraction", new TouristAttraction());
        model.addAttribute("cities", touristService.getCities());
        model.addAttribute("tags", touristService.getTags());
        return "add-attraction-form";
    }

    @PostMapping("/save")
    public String addTouristAttraction(@ModelAttribute TouristAttraction touristAttraction){

        TouristAttraction resultingTouristAttraction = touristService.addTouristAttraction(touristAttraction);

        if(resultingTouristAttraction != null){
            return "redirect:/attractions";
        }
        return "redirect:index"; //create failed to add page

    }

    @GetMapping("{name}")
    public String getTouristAttraction(@PathVariable(required = false) String name, Model model){
        TouristAttraction touristAttraction = touristService.getTouristAttraction(name);
        if(touristAttraction != null){
            model.addAttribute("touristAttraction", touristAttraction);
            return "show_attraction";
        }
        return "redirect:index"; //create fail state
    }

    @GetMapping("/{name}/edit")
    public String showUpdateAttractionForm(@PathVariable String name, Model model){
        TouristAttraction touristAttraction = touristService.getTouristAttraction(name);
        if(touristAttraction != null){
            model.addAttribute("touristAttraction", touristAttraction);
            return "update-attraction-form";
        }
        return "redirect:index";
    }

    @GetMapping("/{name}/tags")
    public String showAttractionTags(@PathVariable String name, Model model){
        TouristAttraction touristAttraction = touristService.getTouristAttraction(name);
        if(touristAttraction != null) {
            model.addAttribute("touristAttraction", touristAttraction);
            return "tags";
        }
        return "redirect:/attractions";
    }


    @PostMapping("/update")
    public String updateTouristAttraction(@ModelAttribute TouristAttraction touristAttraction){
        touristService.updateTouristAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/delete/{name}")
    public String deleteTouristAttraction(@PathVariable String name){
        touristService.deleteTouristAttraction(name);
        return "redirect:/attractions";
    }

}
