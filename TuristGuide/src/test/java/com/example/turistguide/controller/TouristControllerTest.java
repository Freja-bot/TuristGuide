package com.example.turistguide.controller;

import com.example.turistguide.model.Tag;
import com.example.turistguide.model.TouristAttraction;
import com.example.turistguide.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//TODO
// Create another test for endpoint attractionList where an item has been removed, added and modified?

@WebMvcTest(TouristController.class)
class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService mockedTouristService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldShowAllTouristAttractions() throws Exception {

        TouristAttraction first = new TouristAttraction("Tivoli", "Forlystelsespark i København", "København", List.of(Tag.ADGANG_FOR_GANGBESVAEREDE, Tag.KONCERTHAL));
        TouristAttraction second = new TouristAttraction("Bakken", "Forlystelsespark i ydre København", "Klampenborg", List.of(Tag.ADGANG_FOR_GANGBESVAEREDE, Tag.GRATIS_INDGANG, Tag.KONCERTHAL, Tag.HUNDETILLADELSE));
        TouristAttraction third = new TouristAttraction("Djurs sommerland", "Danmarks bedste rutschebaner, det kæmpestore Vandland og over 60 sjove forlystelser for både små og store legebørn.", "Nimtofte", List.of(Tag.VANDLAND, Tag.HUNDETILLADELSE));
        List<TouristAttraction> attractionList = List.of(first, second, third);
        when(mockedTouristService.getAllTouristAttraction()).thenReturn(attractionList);

        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"))
                //.andExpect(model().attribute("touristAttractions", hasSize(3)))
                .andExpect(model().attribute("touristAttractions", List.copyOf(attractionList)));
                //        .andExpect(model().attribute("touristAttractions", hasItems(first, second, third)));


        verify(mockedTouristService).getAllTouristAttraction();

    }

    /*
    @GetMapping("/add")
    public String showAddAttractionForm(Model model) {
        model.addAttribute("touristAttraction", new TouristAttraction());
        model.addAttribute("cities", touristService.getCities());
        model.addAttribute("tags", touristService.getTags());
        return "add-attraction-form";
    }
    */
    @Test
    void shouldShowAddAttractionForm() throws Exception
    {

        when(mockedTouristService.getCities()).thenReturn(List.of
                ("Allinge",
                "Allingaabro",
                "Almind",
                "Anholt",
                "Ans By"));
        when(mockedTouristService.getTags()).thenReturn(List.of(Tag.values()));
        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-attraction-form"))
               // .andExpect(model().attribute("cities", hasSize(5)))
                .andExpect(model().attribute("cities", List.of("Allinge",
                        "Allingaabro",
                        "Almind",
                        "Anholt",
                        "Ans By")))
               // .andExpect(model().attribute("tags", hasSize(5)))
                .andExpect(model().attribute("tags", List.of(Tag.values())));

        verify(mockedTouristService).getCities();
        verify(mockedTouristService).getTags();


    }

    @Test
    void addTouristAttraction() throws Exception
    {
        TouristAttraction touristAttraction = new TouristAttraction("BonbonLand", "Sjov park", "Anholt", List.of(Tag.ADGANG_FOR_GANGBESVAEREDE));
        when(mockedTouristService.addTouristAttraction(any(TouristAttraction.class))).thenReturn(touristAttraction);
        mockMvc.perform(post("/attractions/save"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void shouldShowTivoli() throws Exception {
        TouristAttraction tivoli = new TouristAttraction("Tivoli", "Forlystelsespark i København", "København", List.of(Tag.ADGANG_FOR_GANGBESVAEREDE, Tag.KONCERTHAL));
        when(mockedTouristService.getTouristAttraction("Tivoli")).thenReturn(tivoli);

        mockMvc.perform(get("/attractions/{name}", "tivoli"))
                .andExpect(status().isOk())
                .andExpect(view().name("show_attraction"))
                .andExpect(model().attribute("touristAttraction", tivoli));

    }

    @Test
    void showUpdateAttractionForm() {
    }

    @Test
    void showAttractionTags() {
    }

    @Test
    void updateTouristAttraction() {
    }

    @Test
    void deleteTouristAttraction() {
    }
}