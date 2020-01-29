package io.hitos.mycine.service;

import io.hitos.mycine.dto.MovieDTO;
import io.hitos.mycine.exception.CinException;
import io.hitos.mycine.model.Movie;
import io.hitos.mycine.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;

@Service
public class MovieService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieRepository movieRepository;

    public Movie saveOrUpdate(Movie movie){
        if (movie.getId() != null){
            Movie m = movieRepository.findById(movie.getId()).get();
            if (m == null){
                throw new CinException("Movie not found");
            }
        }
        return movieRepository.save(movie);
    }

    public Iterable<Movie> findAll(){
        return movieRepository.findAll();
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }
}
