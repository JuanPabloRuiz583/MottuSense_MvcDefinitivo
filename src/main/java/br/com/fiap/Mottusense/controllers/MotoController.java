package br.com.fiap.Mottusense.controllers;

import br.com.fiap.Mottusense.models.Moto;
import br.com.fiap.Mottusense.repositorys.PatioRepository;
import br.com.fiap.Mottusense.services.MotoService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/moto")
public class MotoController {

    private final MotoService motoService;
    private final MessageSource messageSource;
    private final PatioRepository patioRepository;

    public MotoController(MotoService motoService, MessageSource messageSource, PatioRepository patioRepository) {
        this.motoService = motoService;
        this.messageSource = messageSource;
        this.patioRepository = patioRepository;
    }


    @GetMapping
    public String index(Model model,@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            return "redirect:/login";
        }
        var motos = motoService.getAllMotos();
        model.addAttribute("motos", motos);
        model.addAttribute("user", user);
        var avatar = user.getAttribute("picture") != null ? user.getAttribute("picture") :  user.getAttribute("avatar_url");
        model.addAttribute("avatar", avatar);
        return "index";
    }


    @GetMapping("/form")
    public String form(Model model, Moto moto) {
        model.addAttribute("moto", moto);
        model.addAttribute("patios", patioRepository.findAll());
        return "form";
    }




    @PostMapping("/form")
    public String saveMoto(@Valid @ModelAttribute Moto moto, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String erro = motoService.validarDuplicidade(moto);
        if (erro != null) {
            result.reject("error.moto", erro);
        }
        if (result.hasErrors()) {
            model.addAttribute("patios", patioRepository.findAll());
            return "form";
        }
        try {
            motoService.save(moto);
            redirectAttributes.addFlashAttribute("message", "Moto criada com sucesso");
        } catch (DataIntegrityViolationException e) {
            result.reject("error.moto", "Placa or Chassi already exists.");
            model.addAttribute("patios", patioRepository.findAll());
            return "form";
        }
        return "redirect:/moto";
    }


    //deletar motos
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        motoService.deleteById(id);
        var message = messageSource.getMessage("delete", null, LocaleContextHolder.getLocale());
        redirect.addFlashAttribute("message", message + " realizada com sucesso!");
        return "redirect:/moto";
    }
}