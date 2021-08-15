package br.com.mrabello23.forum.controller;

import br.com.mrabello23.forum.controller.dto.TopicoDto;
import br.com.mrabello23.forum.controller.form.TopicoForm;
import br.com.mrabello23.forum.model.Topico;
import br.com.mrabello23.forum.repository.CursoRepository;
import br.com.mrabello23.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
        if(nomeCurso == null) {
            return TopicoDto.converter(topicoRepository.findAll());
        }

        return TopicoDto.converter(topicoRepository.findByCurso_Nome(nomeCurso));
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(
            @RequestBody TopicoForm form,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }
}
