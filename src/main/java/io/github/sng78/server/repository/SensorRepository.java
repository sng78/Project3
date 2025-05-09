package io.github.sng78.server.repository;

import io.github.sng78.server.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    Optional<Sensor> findByName(String name);
}
