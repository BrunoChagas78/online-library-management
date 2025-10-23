package livro.controller;

import jakarta.validation.Valid;
import livro.Livro;
import livro.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository repo;

    public LivroController(LivroRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         @RequestParam(required = false) String q,
                         Model model) {
        var pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Livro> pagina;

        if (q != null && !q.isBlank()) {
            pagina = repo.findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(q, q, pageable);
        } else {
            pagina = repo.findAll(pageable);
        }

        model.addAttribute("pagina", pagina);
        model.addAttribute("q", q);
        return "livros/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/form";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute("livro") Livro livro,
                        BindingResult br,
                        RedirectAttributes ra) {
        if (br.hasErrors()) return "livros/form";

        repo.save(livro);
        ra.addFlashAttribute("msg", "Livro criado com sucesso!");
        return "redirect:/livros";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        var livro = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado: " + id));
        model.addAttribute("livro", livro);
        return "livros/form";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute("livro") Livro livro,
                            BindingResult br,
                            RedirectAttributes ra) {
        if (br.hasErrors()) return "livros/form";

        livro.setId(id);
        repo.save(livro);
        ra.addFlashAttribute("msg", "Livro atualizado!");
        return "redirect:/livros";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes ra) {
        repo.deleteById(id);
        ra.addFlashAttribute("msg", "Livro excluído!");
        return "redirect:/livros";
    }
}
