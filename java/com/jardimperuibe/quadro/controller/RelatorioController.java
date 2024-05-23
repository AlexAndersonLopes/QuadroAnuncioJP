package com.jardimperuibe.quadro.controller;

import com.jardimperuibe.quadro.model.Relatorio;
import com.jardimperuibe.quadro.model.Usuario;
import com.jardimperuibe.quadro.service.RelatorioService;
import com.jardimperuibe.quadro.service.UsuarioService;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RelatorioController {

    @Autowired
    RelatorioService relatorioService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/mostrarRelatorios")
    public String irMostrarRelatorios(Model model, @RequestParam(required = false) String mes, @RequestParam("permissao") String permissao) {
        if (permissao.equals("R7EaswL55ATschOR901IOs") || permissao.equals("AD25MI87NIST347RADO4lkR")) {
            List<Relatorio> ListaRelatorio, ListaRelatoriop = new ArrayList<>(), ListaRelatorioa = new ArrayList<>(), ListaRelatorior = new ArrayList<>();
            if (mes == null) {
                ListaRelatorio = relatorioService.mostrarTodosRelatorios();

                int qtd = 0, est = 0;
                int rqtd = 0, rhor = 0, rest = 0;
                int aqtd = 0, ahor = 0, aest = 0;

                for (Relatorio lista : ListaRelatorio) {
                    if (lista.getPioneiro().equals("Publicador")) {
                        ListaRelatoriop.add(relatorioService.mostrarRelatorioPorId(lista.getId()));
                        qtd += 1;
                        if (!lista.getEstudo().isEmpty()) {
                            est += Integer.parseInt(lista.getEstudo());
                        }
                    }
                    if (lista.getPioneiro().equals("Regular")) {
                        ListaRelatorior.add(relatorioService.mostrarRelatorioPorId(lista.getId()));
                        rqtd += 1;
                        if (!lista.getHoras().isEmpty()) {
                            rhor += Integer.parseInt(lista.getHoras());
                        }
                        if (!lista.getEstudo().isEmpty()) {
                            rest += Integer.parseInt(lista.getEstudo());
                        }
                    }
                    if (lista.getPioneiro().equals("Auxiliar")) {
                        ListaRelatorioa.add(relatorioService.mostrarRelatorioPorId(lista.getId()));
                        aqtd += 1;
                        if (!lista.getHoras().isEmpty()) {
                            ahor += Integer.parseInt(lista.getHoras());
                        }
                        if (!lista.getEstudo().isEmpty()) {
                            aest += Integer.parseInt(lista.getEstudo());
                        }
                    }
                }
                model.addAttribute("aqtd", aqtd);
                model.addAttribute("ahor", ahor);
                model.addAttribute("aest", aest);

                model.addAttribute("rqtd", rqtd);
                model.addAttribute("rhor", rhor);
                model.addAttribute("rest", rest);

                model.addAttribute("qtd", qtd);
                model.addAttribute("est", est);

            } else {
                ListaRelatorio = relatorioService.encontrarRelatorioPorMes(mes);
                int qtd = 0, est = 0;
                int rqtd = 0, rhor = 0, rest = 0;
                int aqtd = 0, ahor = 0, aest = 0;

                for (Relatorio lista : ListaRelatorio) {
                    if (lista.getPioneiro().equals("Publicador")) {
                        ListaRelatoriop.add(relatorioService.mostrarRelatorioPorId(lista.getId()));
                        qtd += 1;
                        if (!lista.getEstudo().isEmpty()) {
                            est += Integer.parseInt(lista.getEstudo());
                        }
                    }
                    if (lista.getPioneiro().equals("Regular")) {
                        ListaRelatorior.add(relatorioService.mostrarRelatorioPorId(lista.getId()));
                        rqtd += 1;
                        if (!lista.getHoras().isEmpty()) {
                            rhor += Integer.parseInt(lista.getHoras());
                        }
                        if (!lista.getEstudo().isEmpty()) {
                            rest += Integer.parseInt(lista.getEstudo());
                        }
                    }
                    if (lista.getPioneiro().equals("Auxiliar")) {
                        ListaRelatorioa.add(relatorioService.mostrarRelatorioPorId(lista.getId()));
                        aqtd += 1;
                        if (!lista.getHoras().isEmpty()) {
                            ahor += Integer.parseInt(lista.getHoras());
                        }
                        if (!lista.getEstudo().isEmpty()) {
                            aest += Integer.parseInt(lista.getEstudo());
                        }
                    }
                }
                model.addAttribute("aqtd", aqtd);
                model.addAttribute("ahor", ahor);
                model.addAttribute("aest", aest);

                model.addAttribute("rqtd", rqtd);
                model.addAttribute("rhor", rhor);
                model.addAttribute("rest", rest);

                model.addAttribute("qtd", qtd);
                model.addAttribute("est", est);

                model.addAttribute("mes", mes);
            }
            List<String> datas = relatorioService.encontrarMesesUnicosOrdenadosPorData();
            model.addAttribute("datas", datas);
            model.addAttribute("permissao", permissao);
            model.addAttribute("ListaRelatoriop", ListaRelatoriop);
            model.addAttribute("ListaRelatorioa", ListaRelatorioa);
            model.addAttribute("ListaRelatorior", ListaRelatorior);
            return "mostrarRelatorios";
        } else {
            return "negado";
        }
    }

    @PostMapping("/cadastrarRelatorio")
    public String cadastrarSemana(@ModelAttribute Relatorio relatorio, Model model) {
        if (!relatorio.getNome().isEmpty() && !relatorio.getGrupo().isEmpty() && !relatorio.getMes().isEmpty() && !relatorio.getParticipou().isEmpty()) {
            relatorioService.cadastrarRelatorio(relatorio);
            return "redirect:/relatorioEnviado";
        } else {
            List<Usuario> usuario = usuarioService.obterUsuariosComGrupo();
            model.addAttribute("usuario", usuario);
            model.addAttribute("erro", "Por favor preencha o relatório corretamente.");
            return "enviarRelatorio";
        }
    }

    @PostMapping("/mostrarRelatoriosGrupo")
    public String irMostrarRelatoriosGrupo(Model model, @RequestParam(required = false) String mes, @RequestParam("grupo") String grupo, @RequestParam("permissao") String permissao) {
        if (grupo == null || grupo.isEmpty()) {
            return "negado";
        } else {
            List<Relatorio> ListaRelatorio;
            if (mes == null || mes.equals("Todos")) {
                ListaRelatorio = relatorioService.encontrarRelatorioPorGrupo(grupo);
            } else {
                ListaRelatorio = relatorioService.encontrarRelatorioPorGrupoMes(grupo, mes);
                model.addAttribute("mes", mes);
            }
            List<String> datas = relatorioService.encontrarMesesUnicosOrdenadosPorData();
            model.addAttribute("datas", datas);
            model.addAttribute("grupo", grupo);
            model.addAttribute("permissao", permissao);
            model.addAttribute("ListaRelatorio", ListaRelatorio);
            return "mostrarRelatoriosGrupo";
        }
    }
    
     @PostMapping("/mostrarRelatoriosGrupo2")
    public String irMostrarRelatoriosGrupo2(Model model, @RequestParam(required = false) String mes, @RequestParam("grupo") String grupo, @RequestParam("permissao") String permissao) {
        if (grupo == null || grupo.isEmpty()) {
            return "negado";
        } else {
            List<Relatorio> ListaRelatorio;
            if (mes == null || mes.equals("Todos")) {
                ListaRelatorio = relatorioService.encontrarRelatorioPorGrupo(grupo);
            } else {
                ListaRelatorio = relatorioService.encontrarRelatorioPorGrupoMes(grupo, mes);
                model.addAttribute("mes", mes);
            }
            List<String> datas = relatorioService.encontrarMesesUnicosOrdenadosPorData();
            model.addAttribute("datas", datas);
            model.addAttribute("grupo", grupo);
            model.addAttribute("permissao", permissao);
            model.addAttribute("ListaRelatorio", ListaRelatorio);
            return "mostrarRelatoriosGrupo";
        }
    }

    @GetMapping("/relatorioEnviado")
    public String enviarRelatorio(Model model) {
        return "relatorioEnviado";
    }

    @GetMapping("/enviarRelatorio")
    public String irEnviarRelatorio(Model model) {
        List<Usuario> usuario = usuarioService.obterUsuariosComGrupo();
        Relatorio relatorio = new Relatorio();
        List<String> mesano = new ArrayList<>();
        YearMonth yearMonth = YearMonth.now().minusMonths(1);
        for (int i = 0; i < 3; i++) {
            mesano.add(yearMonth.plusMonths(i).format(DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("pt", "BR"))));
        }
        model.addAttribute("mesano", mesano);
        model.addAttribute("usuario", usuario);
        model.addAttribute("relatorio", relatorio);
        return "enviarRelatorio";
    }

    @DeleteMapping("/excluirRelatorio")
    public ResponseEntity<String> excluirRelatorioId(@RequestParam String id) {
        try {
            int excluir = Integer.parseInt(id);
            relatorioService.excluirRelatorio(excluir);
            return ResponseEntity.ok("Relatório excluido com sucesso!");
        } catch (NumberFormatException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir o relatório!");
        }
    }

    @DeleteMapping("/excluirRelatorioMes")
    public ResponseEntity<String> excluirRelatorioPorMes(@RequestParam String mes) {
        try {
            relatorioService.excluirRelatoriosPorMes(mes);
            return ResponseEntity.ok("Relatório de " + mes + " excluido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir o relatórios de " + mes);
        }
    }

    @GetMapping("/confirmarExcluirRelatorio")
    public String irconfirmarExcluirRelatorio(Model model, @RequestParam String mes) {
        model.addAttribute("mes", mes);
        return "confirmarExcluirRelatorio";
    }

}
