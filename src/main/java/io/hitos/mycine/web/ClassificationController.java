package io.hitos.mycine.web;

import io.hitos.mycine.dto.ClassificationDTO;
import io.hitos.mycine.dto.MovieDTO;
import io.hitos.mycine.model.Classification;
import io.hitos.mycine.model.Movie;
import io.hitos.mycine.service.ClassificationService;
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
@RequestMapping("/api/classifications")
@CrossOrigin
public class ClassificationController {

    @Autowired
    private ClassificationService classificationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @GetMapping("")
    public ResponseEntity<?> getClassifications(){

        Iterable<Classification> lClassifications = classificationService.findAll();
        Iterable<ClassificationDTO> lClassificationsDTO = convertToListDto(lClassifications);

        return new ResponseEntity<Iterable<ClassificationDTO>>(lClassificationsDTO, HttpStatus.OK);
    }




    private List<ClassificationDTO> convertToListDto(Iterable<Classification> classifications) {
        List<ClassificationDTO> lClassifications = new ArrayList<ClassificationDTO>();
        for (Classification c : classifications){
            lClassifications.add(modelMapper.map(c, ClassificationDTO.class));
        }
        return lClassifications;
    }

    private List<Movie> convertToListEntity(Iterable<MovieDTO> lMoviesDTO) throws ParseException {
        List<Movie> lMovies = new ArrayList<Movie>();
        for (MovieDTO movieDTO : lMoviesDTO){
            lMovies.add(modelMapper.map(movieDTO, Movie.class));
        }
        return lMovies;
    }

}
