package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.dao.impl.PeriodicalDao;
import by.epam.jwd.finalproj.model.periodicals.Periodical;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class PeriodicalService implements CommonService<PeriodicalDto> {

    private final PeriodicalDao periodicalDao;

    public PeriodicalService(){
        this.periodicalDao = new PeriodicalDao();
    }

    private final Logger logger = LogManager.getLogger(PeriodicalService.class);

    @Override
    public Optional<List<PeriodicalDto>> findAll() {
        Optional<List<Periodical>> allPeriodicals = periodicalDao.findAll();
        if (!allPeriodicals.isPresent()){
            return Optional.empty();
        }
        return Optional.of(
                allPeriodicals.get()
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList())
        );
    }

    public Optional<PeriodicalDto> findByName(String name){
        Optional<Periodical> foundPeriodical = periodicalDao.findByName(name);
        return foundPeriodical.map(this::convertToDto);
    }

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

    private Periodical convertToEntity(PeriodicalDto dto) {
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

    private PeriodicalDto convertToDto(Periodical periodical){
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
