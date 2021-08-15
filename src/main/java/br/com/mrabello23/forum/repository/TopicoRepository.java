package br.com.mrabello23.forum.repository;

import br.com.mrabello23.forum.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    // Para evitar ambiguidade e erro de nomenclatura de atributos, deve-se usar o "_" para indicar a hierarquia de obj e atributo
    // findByObjeto_AtributoDoObjFilho
    Page<Topico> findByCurso_Nome(String nomeCurso, Pageable paginacao);
}
