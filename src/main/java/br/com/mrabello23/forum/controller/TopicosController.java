package br.com.mrabello23.forum.controller;

import br.com.mrabello23.forum.controller.dto.DetalheTopicoDto;
import br.com.mrabello23.forum.controller.dto.TopicoDto;
import br.com.mrabello23.forum.controller.form.AtualizaTopicoForm;
import br.com.mrabello23.forum.controller.form.TopicoForm;
import br.com.mrabello23.forum.model.Topico;
import br.com.mrabello23.forum.repository.CursoRepository;
import br.com.mrabello23.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos") // mapeia path para toda classe
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> listar(String nomeCurso) { // param opcional de query string da url
        if(nomeCurso == null) {
            return TopicoDto.converter(topicoRepository.findAll());
        }

        return TopicoDto.converter(topicoRepository.findByCurso_Nome(nomeCurso));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(
            @RequestBody @Valid TopicoForm form,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public DetalheTopicoDto listarPorid(@PathVariable Long id) {
        // Caso nome param diferente do nome GetMapping, usar param da anotação @PathVariable("nm_param_GetMapping")
        return new DetalheTopicoDto(topicoRepository.getById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(
            // Caso nome param diferente do nome PutMapping, usar param da anotação @PathVariable("nm_param_PutMapping")
            @PathVariable Long id,
            @RequestBody @Valid AtualizaTopicoForm form
    ) {
        Topico topico = form.atualizar(id, topicoRepository);
        return ResponseEntity.ok(new TopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
