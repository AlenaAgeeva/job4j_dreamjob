package dreamjob.repository;

import dreamjob.model.Candidate;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class MemoryCandidateRepository implements CandidateRepository {
    private final AtomicInteger nextId = new AtomicInteger(1);
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Ivan Ivanov", "text",
                LocalDateTime.of(2022, 2, 13, 3, 45), 1, 0));
        save(new Candidate(1, "Sidor Sidorov", "text",
                LocalDateTime.of(2023, 4, 7, 10, 56), 1, 0));
        save(new Candidate(2, "Pert Petrov", "text",
                LocalDateTime.of(2024, 10, 3, 15, 23), 1, 0));
        save(new Candidate(3, "Semen Semenov", "text",
                LocalDateTime.of(2024, 8, 29, 20, 15), 1, 0));
        save(new Candidate(4, "Anton Antonov", "text",
                LocalDateTime.of(2023, 9, 23, 7, 34), 1, 0));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldCandidate) ->
                        new Candidate(oldCandidate.getId(),
                                candidate.getName(),
                                candidate.getDescription(),
                                candidate.getCreationDate(),
                                candidate.getCityId(),
                                candidate.getFileId())) != null;
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
