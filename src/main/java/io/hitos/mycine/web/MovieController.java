package io.hitos.mycine.web;

import io.hitos.mycine.dto.MovieDTO;
import io.hitos.mycine.model.Movie;
import io.hitos.mycine.service.MapValidationErrorService;
import io.hitos.mycine.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> saveOrUpdate(@Valid @RequestBody MovieDTO movieDTO, BindingResult result) throws ParseException{

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        Movie movie = convertToEntity(movieDTO);
        movie = movieService.saveOrUpdate(movie);
        movieDTO = convertToDto(movie);
        return new ResponseEntity<MovieDTO>(movieDTO, HttpStatus.CREATED);
    }


    @GetMapping("")
    public ResponseEntity<?> getMovies(){

        Iterable<Movie> lMovies = movieService.findAll();
        Iterable<MovieDTO> lMoviesDTO = convertToListDto(lMovies);

        return new ResponseEntity<Iterable<MovieDTO>>(lMoviesDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{movieID}")
    public ResponseEntity<?> delete(@PathVariable Long movieID){
        movieService.delete(movieID);
        return new ResponseEntity<String>(String.format("Movie with ID: %d was deleted", movieID), HttpStatus.OK);
    }

    private MovieDTO convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }

    private Movie convertToEntity(MovieDTO movieDTO) throws ParseException {
        return modelMapper.map(movieDTO, Movie.class);
    }

    private List<MovieDTO> convertToListDto(Iterable<Movie> movies) {
        List<MovieDTO> lMovies = new ArrayList<MovieDTO>();
        for (Movie movie : movies){
            lMovies.add(modelMapper.map(movie, MovieDTO.class));
        }
        return lMovies;
    }

    private List<Movie> convertToListEntity(Iterable<MovieDTO> lMoviesDTO) throws ParseException {
        List<Movie> lMovies = new ArrayList<Movie>();
        for (MovieDTO movieDTO : lMoviesDTO){
            lMovies.add(modelMapper.map(movieDTO, Movie.class));
        }
        return lMovies;
    }

}
