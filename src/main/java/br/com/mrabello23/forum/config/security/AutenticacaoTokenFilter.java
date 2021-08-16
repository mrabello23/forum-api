package br.com.mrabello23.forum.config.security;

import br.com.mrabello23.forum.model.Usuario;
import br.com.mrabello23.forum.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = recuperarToken(request);
        boolean isValido = tokenService.isTokenValido(token);

        if (isValido) {
            autenticar(token);
        }

        // Continua a requisição
        filterChain.doFilter(request, response);
    }

    private void autenticar(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Optional<Usuario> dadosUsuario = usuarioRepository.findById(idUsuario);

        if(dadosUsuario.isPresent()) {
            Usuario usuario = dadosUsuario.get();
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            // Força autenticação stateless
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }
}
