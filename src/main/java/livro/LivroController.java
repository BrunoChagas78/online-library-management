package livro.controller;

import livro.model.Livro;
import livro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    // Exibir lista de livros
    @GetMapping
    public String listarLivros(Model model) {
        List<Livro> livros = livroRepository.findAll();
        model.addAttribute("livros", livros);
        return "livros"; // renderiza templates/livros.html
    }

    // Exibir formulário para novo livro
    @GetMapping("/novo")
    public String novoLivroForm(Model model) {
        model.addAttribute("livro", new Livro());
        return "form-livro"; // renderiza templates/form-livro.html
    }

    // Salvar livro
    @PostMapping
    public String salvarLivro(@ModelAttribute Livro livro) {
        livroRepository.save(livro);
        return "redirect:/livros";
    }

    // Editar livro existente
    @GetMapping("/editar/{id}")
    public String editarLivro(@PathVariable Long id, Model model) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            model.addAttribute("livro", livro.get());
            return "form-livro";
        } else {
            return "redirect:/livros";
        }
    }

    // Deletar livro
    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Long id) {
        livroRepository.deleteById(id);
        return "redirect:/livros";
    }
}
