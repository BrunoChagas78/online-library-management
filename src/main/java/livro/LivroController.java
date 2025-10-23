package livro;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class LivroController {

    private final LivroRepository repo;

    public LivroController(LivroRepository repo) {
        this.repo = repo;
    }

    // Página principal
    @GetMapping({"/", "/livros"})
    public String listar(Model model) {
        List<Livro> livros = repo.findAll();
        model.addAttribute("livros", livros);
        model.addAttribute("livro", new Livro());
        return "livros";
    }

    // Salvar livro
    @PostMapping("/livros")
    public String salvar(@ModelAttribute Livro livro) {
        repo.save(livro);
        return "redirect:/livros";
    }

    // API para evidência de persistência
    @GetMapping("/api/livros")
    @ResponseBody
    public List<Livro> apiList() {
        return repo.findAll();
    }
}
