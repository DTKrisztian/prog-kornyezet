package progkor.factsite.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import progkor.factsite.model.FactText;
import progkor.factsite.service.FactSiteService;

@Controller
@RequestMapping("/fact")
public class FactSiteController {

    private final FactSiteService factSiteService;

    public FactSiteController(FactSiteService factSiteService) {
        this.factSiteService = factSiteService;
    }

    @GetMapping
    public String getAllFactText(final Model model) {
        final List<FactText> factTexts = factSiteService.getAllFactTexts();
        model.addAttribute("factTexts", factTexts);
        return "factsite/list";
    }

    @GetMapping("/{id}")
    public String getFactText(final Model model, @PathVariable final Long id) {
        final FactText factText = factSiteService.getFactText(id);
        model.addAttribute("factText", factText);
        return "factsite/edit";
    }



}
