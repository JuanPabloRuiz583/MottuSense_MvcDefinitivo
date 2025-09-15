package br.com.fiap.Mottusense.controllers;

import br.com.fiap.Mottusense.models.SensorLocalizacao;
import br.com.fiap.Mottusense.repositorys.MotoRepository;
import br.com.fiap.Mottusense.services.SensorLocalizacaoService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.fiap.Mottusense.models.Moto;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/sensor-localizacao")
public class SensorLocalizacaoController {

    private final SensorLocalizacaoService sensorService;
    private final MessageSource messageSource;
    private final MotoRepository motoRepository;

    public SensorLocalizacaoController(SensorLocalizacaoService sensorService, MessageSource messageSource, MotoRepository motoRepository) {
        this.sensorService = sensorService;
        this.messageSource = messageSource;
        this.motoRepository = motoRepository;
    }

    @GetMapping
    public String index(Model model) {
        var sensores = sensorService.listarTodos();
        model.addAttribute("sensores", sensores);
        return "sensor-index";
    }

    @GetMapping("/form")
    public String form(Model model, SensorLocalizacao sensorLocalizacao) {
        model.addAttribute("sensorLocalizacao", sensorLocalizacao);
        model.addAttribute("motos", motoRepository.findAll());
        return "formsensor";
    }


    @PostMapping("/form")
    public String saveSensor(@Valid @ModelAttribute SensorLocalizacao sensorLocalizacao, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String erro = sensorService.validarDuplicidadeCadastro(sensorLocalizacao);
        if (erro != null) {
            result.reject("error.sensor", erro);
        }
        if (result.hasErrors()) {
            model.addAttribute("motos", motoRepository.findAll());
            return "formsensor";
        }
        try {
            sensorService.validarSensor(sensorLocalizacao);
            sensorService.salvar(sensorLocalizacao);
            redirectAttributes.addFlashAttribute("message", "Sensor cadastrado com sucesso");
        } catch (IllegalArgumentException e) {
            result.reject("error.sensor", e.getMessage());
            model.addAttribute("motos", motoRepository.findAll());
            return "formsensor";
        } catch (DataIntegrityViolationException e) {
            result.reject("error.sensor", "Erro de integridade ao salvar sensor.");
            model.addAttribute("motos", motoRepository.findAll());
            return "formsensor";
        }
        return "redirect:/sensor-localizacao";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        SensorLocalizacao sensor = sensorService.buscarPorId(id);
        model.addAttribute("sensorLocalizacao", sensor);
        model.addAttribute("motos", motoRepository.findAll());
        return "formsensor";
    }

    @PostMapping("/edit/{id}")
    public String updateSensor(@PathVariable Long id, @Valid @ModelAttribute SensorLocalizacao sensorAtualizado, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        String erro = sensorService.validarDuplicidadeEdicao(sensorAtualizado, id);
        if (erro != null) {
            result.reject("error.sensor", erro);
        }
        if (result.hasErrors()) {
            model.addAttribute("sensorLocalizacao", sensorAtualizado);
            model.addAttribute("motos", motoRepository.findAll());
            return "formsensor";
        }
        SensorLocalizacao sensorExistente = sensorService.buscarPorId(id);
        sensorExistente.setLatitude(sensorAtualizado.getLatitude());
        sensorExistente.setLongitude(sensorAtualizado.getLongitude());
        sensorExistente.setTimeDaLocalizacao(sensorAtualizado.getTimeDaLocalizacao());
        sensorExistente.setMoto(sensorAtualizado.getMoto());
        try {
            sensorService.validarSensor(sensorExistente);
            sensorService.salvar(sensorExistente);
            redirectAttributes.addFlashAttribute("message", "Sensor editado com sucesso");
        } catch (IllegalArgumentException e) {
            result.reject("error.sensor", e.getMessage());
            model.addAttribute("motos", motoRepository.findAll());
            return "formsensor";
        } catch (DataIntegrityViolationException e) {
            result.reject("error.sensor", "Erro de integridade ao editar sensor.");
            model.addAttribute("motos", motoRepository.findAll());
            return "formsensor";
        }
        return "redirect:/sensor-localizacao";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        sensorService.deletar(id);
        var message = messageSource.getMessage("delete", null, LocaleContextHolder.getLocale());
        redirect.addFlashAttribute("message", message + " realizada com sucesso!");
        return "redirect:/sensor-localizacao";
    }
}