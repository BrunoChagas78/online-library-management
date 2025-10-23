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
        // ARQUIVO EXISTENTE: templates/livros.html
        return "livros";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("livro", new Livro());
        // ARQUIVO EXISTENTE: templates/form-livro.html
        return "form-livro";
    }

    @PostMapping
    public String criar(@Valid @ModelAttribute Livro livro,
                        BindingResult br,
                        RedirectAttributes ra) {
        if (br.hasErrors()) return "form-livro";
        repo.save(livro);
        ra.addFlashAttribute("msg", "Livro criado!");
        return "redirect:/livros";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        var livro = repo.findById(id).orElseThrow();
        model.addAttribute("livro", livro);
        return "form-livro";
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id,
                            @Valid @ModelAttribute Livro livro,
                            BindingResult br,
                            RedirectAttributes ra) {
        if (br.hasErrors()) return "form-livro";
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
