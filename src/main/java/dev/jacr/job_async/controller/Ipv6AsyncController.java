package dev.jacr.job_async.controller;

import dev.jacr.job_async.dto.Ipv6ConsultaDto;
import dev.jacr.job_async.response.ApiResponse;
import dev.jacr.job_async.service.Ipv6ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/consultar-ipv6-async")
public class Ipv6AsyncController {

    private final Ipv6ConsultaService ipv6ConsultaService;

    public Ipv6AsyncController(Ipv6ConsultaService ipv6ConsultaService) {
        this.ipv6ConsultaService = ipv6ConsultaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Ipv6ConsultaDto>> consultarIpv6Async(@RequestParam("input") String input) {
        Ipv6ConsultaDto consulta = ipv6ConsultaService.crearIpv6ConsultaTask(input);

        return ResponseEntity.ok().body(ApiResponse.success(consulta));
    }

}
