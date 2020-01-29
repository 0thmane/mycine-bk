package io.hitos.mycine.repository;

import io.hitos.mycine.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository  extends CrudRepository<Movie, Long> {


}
