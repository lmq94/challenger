package app.repository;

import app.domain.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkPlaceRepository extends JpaRepository<WorkPlace, Long> {

    public Optional<WorkPlace> findByName(String name);
}
