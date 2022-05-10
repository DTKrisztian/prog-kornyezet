package progkor.factsite.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import progkor.factsite.model.FactText;
import progkor.factsite.model.Theme;
import progkor.factsite.model.exception.NotFoundException;
import progkor.factsite.service.FactSiteService;

@Service
public class FactSiteServiceImpl implements FactSiteService {

    private static final List<FactText> DATA_BASE = new ArrayList<>();

    static {
        DATA_BASE.add(new FactText(1L, "Test fact", Theme.GENERAL));
        DATA_BASE.add(new FactText(2L, "Test animal", Theme.ANIMALS));
    }

    @Override
    public List<FactText> getAllFactTexts() {
        return Collections.unmodifiableList(DATA_BASE);
    }

    @Override
    public FactText getFactText(Long id) {
        return DATA_BASE.stream()
                .filter(factText -> factText.getId().equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public FactText createFactText(FactText factText) {
        factText.setId(getNextId());
        DATA_BASE.add(factText);
        return factText;
    }

    @Override
    public FactText updateFactText(Long id, FactText factTextChange) {
        final FactText factText = getFactText(id);
        factText.setContext(factTextChange.getContext());
        factText.setTheme(factTextChange.getTheme());
        return factText;
    }

    @Override
    public void deleteFactText(Long id) {
        final FactText factText = getFactText(id);
        DATA_BASE.remove(factText);
    }

    private long getNextId() {
        return getLastId() + 1L;
    }

    private long getLastId() {
        return DATA_BASE.stream()
                .mapToLong(FactText::getId)
                .max()
                .orElse(0);
    }
}
