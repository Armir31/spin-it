package al.spinit.spinit.service;

import al.spinit.spinit.dto.CreateGenreDto;
import al.spinit.spinit.entity.Genre;
import al.spinit.spinit.repository.GenreRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    public static final Logger log = LoggerFactory.getLogger(GenreService.class);

    @Autowired
    private GenreRepository genreRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public Genre create (CreateGenreDto createGenreDto){
        Genre genre = modelMapper.map(createGenreDto, Genre.class);
        return genreRepository.save(genre);
    }
    public Genre getById (Long id){
        return genreRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Genre" + id + "not found!"));
    }
    public void deleteById (Long id) {
        genreRepository.deleteById(id);
    }
    public Genre update (CreateGenreDto createGenreDto, Long id){
        Genre existingGenre = getById(id);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(createGenreDto, existingGenre);
        return genreRepository.save(existingGenre);
    }
    public List<Genre> getList () {
        return genreRepository.findAll();
    }
}