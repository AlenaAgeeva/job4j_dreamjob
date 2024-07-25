package dreamjob.repository;

import dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MemoryCandidateRepository implements CandidateRepository {

    private static final MemoryCandidateRepository INSTANCE = new MemoryCandidateRepository();

    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Ivan Ivanov", "text",
                DateFormatter.format(LocalDateTime.of(2022, 2, 13, 3, 45))));
        save(new Candidate(1, "Sidor Sidorov", "text",
                DateFormatter.format(LocalDateTime.of(2023, 4, 7, 10, 56))));
        save(new Candidate(2, "Pert Petrov", "text",
                DateFormatter.format(LocalDateTime.of(2024, 10, 3, 15, 23))));
        save(new Candidate(3, "Semen Semenov", "text",
                DateFormatter.format(LocalDateTime.of(2024, 8, 29, 20, 15))));
        save(new Candidate(4, "Anton Antonov", "text",
                DateFormatter.format(LocalDateTime.of(2023, 9, 23, 7, 34))));
    }

    public static MemoryCandidateRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public void deleteById(int id) {
        candidates.remove(id);
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldCandidate) ->
                        new Candidate(oldCandidate.getId(),
                                candidate.getName(),
                                candidate.getDescription(),
                                candidate.getCreationDate())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
