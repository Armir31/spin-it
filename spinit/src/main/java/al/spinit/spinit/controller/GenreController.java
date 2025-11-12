package al.spinit.spinit.controller;

import al.spinit.spinit.dto.CreateGenreDto;
import al.spinit.spinit.dto.GenreDto;
import al.spinit.spinit.entity.Genre;
import al.spinit.spinit.service.GenreService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {

    public static final Logger log = LoggerFactory.getLogger(GenreController.class);

    @Autowired
    private GenreService genreService;

    private ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    public ResponseEntity<GenreDto> createGenre(@RequestBody CreateGenreDto createGenreDto){
        log.info("Creating genre...");
        Genre genre = genreService.create(createGenreDto);
        return new ResponseEntity<>(modelMapper.map(genre, GenreDto.class), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Genre>> getList(){
        log.info("Getting list of genres...");
        return ResponseEntity.ok(genreService.getList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getById(@PathVariable Long id){
        Genre genre = genreService.getById(id);
        return new ResponseEntity<>(modelMapper.map(genre, GenreDto.class), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable Long id, @RequestBody CreateGenreDto updateGenreDto){
        log.info("Updating genre...");
        Genre saved = genreService.update(updateGenreDto, id);
        return new ResponseEntity<>(modelMapper.map(saved, GenreDto.class), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre (@PathVariable Long id){
        log.warn("Deleting genre with ID: " + id);
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
