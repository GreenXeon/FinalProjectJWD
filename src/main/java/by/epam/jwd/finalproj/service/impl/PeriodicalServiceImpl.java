package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.dao.impl.PeriodicalDaoImpl;
import by.epam.jwd.finalproj.model.periodicals.Periodical;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.PeriodicalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum PeriodicalServiceImpl implements PeriodicalService {
    INSTANCE;

    private final PeriodicalDaoImpl periodicalDao;

    PeriodicalServiceImpl(){
        this.periodicalDao = new PeriodicalDaoImpl();
    }

    private final Logger logger = LogManager.getLogger(PeriodicalServiceImpl.class);

    @Override
    public Optional<List<PeriodicalDto>> findAll() {
        Optional<List<Periodical>> allPeriodicals = periodicalDao.findAll();
        return allPeriodicals.map(periodicals -> periodicals
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @Override
    public Optional<List<PeriodicalDto>> findForCurrentUser(int userId){
        Optional<List<Periodical>> periodicals = periodicalDao.findForCurrentUser(userId);
        return periodicals.map(periodical -> periodical
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @Override
    public List<PeriodicalDto> findPeriodicalByPhrase(int userId, String phrase) {
        List<PeriodicalDto> periodicals = this.findForCurrentUser(userId).orElse(Collections.emptyList());
        if(periodicals.isEmpty()){
            logger.error("Periodicals are not read");
        }
        List<PeriodicalDto> foundPeriodicals = new ArrayList<>();
        for (PeriodicalDto periodical : periodicals){
            if (periodical.getName().toLowerCase().contains(phrase.toLowerCase())){
                foundPeriodicals.add(periodical);
            }
        }
        return foundPeriodicals;
    }

    @Override
    public List<PeriodicalDto> findPeriodicalByPhrase(String phrase) {
        List<PeriodicalDto> periodicals = this.findAll().orElse(Collections.emptyList());
        if (periodicals.isEmpty()){
            logger.error("Periodicals are not read");
        }
        List<PeriodicalDto> foundPeriodicals = new ArrayList<>();
        for (PeriodicalDto periodical : periodicals){
            if (periodical.getName().toLowerCase().contains(phrase.toLowerCase())){
                foundPeriodicals.add(periodical);
            }
        }
        return foundPeriodicals;
    }

    @Override
    public Optional<PeriodicalDto> findByName(String name){
        Optional<Periodical> foundPeriodical = periodicalDao.findByName(name);
        return foundPeriodical.map(this::convertToDto);
    }

    @Override
    public Optional<PeriodicalDto> findById(int id){
        Optional<Periodical> foundPeriodical = periodicalDao.findById(id);
        return foundPeriodical.map(this::convertToDto);
    }

    @Override
    public Optional<PeriodicalDto> save(PeriodicalDto dto) {
        return Optional.of(convertToDto(periodicalDao.save(convertToEntity(dto)).get()));
    }

    @Override
    public Optional<PeriodicalDto> update(PeriodicalDto dto) {
        return Optional.of(convertToDto(periodicalDao.update(convertToEntity(dto)).get()));
    }

    @Override
    public boolean delete(int id) {
        return periodicalDao.delete(id);
    }

    @Override
    public Periodical convertToEntity(PeriodicalDto dto) {
        return new Periodical(
                dto.getId(),
                dto.getName(),
                dto.getAuthor(),
                dto.getPublishDate(),
                dto.getType(),
                dto.getSubCost(),
                dto.getPublisher()
        );
    }

    @Override
    public PeriodicalDto convertToDto(Periodical periodical){
        return new PeriodicalDto(
                periodical.getId(),
                periodical.getName(),
                periodical.getAuthor(),
                periodical.getPublishDate(),
                periodical.getType(),
                periodical.getSubCost(),
                periodical.getPublisher()
        );
    }
}
