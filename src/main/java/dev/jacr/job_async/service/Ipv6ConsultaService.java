package dev.jacr.job_async.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.jacr.job_async.dto.Ipv6ConsultaDto;
import dev.jacr.job_async.dto.Ipv6ConsultaMapper;
import dev.jacr.job_async.model.Ipv6Consulta;
import dev.jacr.job_async.repository.Ipv6ConsultaRepository;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Ipv6ConsultaService {

    private final JobScheduler jobScheduler;
    private final Ipv6ConsultaRepository ipv6ConsultaRepository;
    private final Ipv6ConsultaMapper ipv6ConsultaMapper;


    @Autowired
    public Ipv6ConsultaService(JobScheduler jobScheduler, Ipv6ConsultaRepository ipv6ConsultaRepository, Ipv6ConsultaMapper ipv6ConsultaMapper) {
        this.jobScheduler = jobScheduler;
        this.ipv6ConsultaRepository = ipv6ConsultaRepository;
        this.ipv6ConsultaMapper = ipv6ConsultaMapper;
    }

    public Ipv6ConsultaDto crearIpv6ConsultaTask(String input) {
        // Primero consultar si ya existe un proceso
        List<Ipv6Consulta> consultaTaskExistente = ipv6ConsultaRepository.findByIp(input);

        // Si existe devuelve
        if (!consultaTaskExistente.isEmpty()) {
            Ipv6Consulta consultaTaskFirst = consultaTaskExistente.getFirst();
            return ipv6ConsultaMapper.toDto(consultaTaskFirst);
        }

        // Si no existe, se crea nuevo task
        Ipv6Consulta consultaTask = new Ipv6Consulta(input, null, Ipv6Consulta.Estado.pendiente);
        ipv6ConsultaRepository.save(consultaTask);

        jobScheduler.enqueue(() -> this.procesarIpv6Consulta(consultaTask));

        return ipv6ConsultaMapper.toDto(consultaTask);
    }


    public void procesarIpv6Consulta(Ipv6Consulta consulta) {
        System.out.println("Se procesa: " + consulta.getTaskId().toString());

        // Se llama a la api
        try{
            System.out.println("Se llama a la API");
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        System.out.println("Se obtiene respuesta de API");

        // Actualizar tabla
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode dummy = mapper.createObjectNode();
        dummy.put("ip", "10.0.0.1");
        dummy.put("pais", "py");
        dummy.put("imei", "097123456");

        consulta.setDatos(dummy);
        consulta.setEstado(Ipv6Consulta.Estado.completado);

        ipv6ConsultaRepository.save(consulta);

        System.out.println("PROCESADO y GUARDADO: " + consulta.getTaskId().toString());
    }

}
