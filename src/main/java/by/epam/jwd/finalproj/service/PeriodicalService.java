package by.epam.jwd.finalproj.service;

import by.epam.jwd.finalproj.model.periodicals.Periodical;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.model.user.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * Interface for implementing by class, representing {@link PeriodicalDto}'s service
 *
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface PeriodicalService {
    Optional<List<PeriodicalDto>> findAll();

    Optional<List<PeriodicalDto>> findForCurrentUser(int userId);

    List<PeriodicalDto> findPeriodicalByPhrase(int userId, String phrase);

    List<PeriodicalDto> findPeriodicalByPhrase(String phrase);

    Optional<PeriodicalDto> findByName(String name);

    Optional<PeriodicalDto> findById(int id);

    Optional<PeriodicalDto> save(PeriodicalDto dto);

    Optional<PeriodicalDto> update(PeriodicalDto dto);

    boolean delete(int id);

    Periodical convertToEntity(PeriodicalDto dto);

    PeriodicalDto convertToDto(Periodical periodical);
}
