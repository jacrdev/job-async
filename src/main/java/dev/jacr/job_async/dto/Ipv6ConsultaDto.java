package dev.jacr.job_async.dto;

import com.fasterxml.jackson.databind.JsonNode;
import dev.jacr.job_async.model.Ipv6Consulta;

import java.util.UUID;

public class Ipv6ConsultaDto {

    private UUID taskId;
    private String ip;
    private JsonNode datos;
    private Ipv6Consulta.Estado estado;

    public Ipv6ConsultaDto(UUID taskId, String ip, JsonNode datos, Ipv6Consulta.Estado estado) {
        this.taskId = taskId;
        this.ip = ip;
        this.datos = datos;
        this.estado = estado;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public JsonNode getDatos() {
        return datos;
    }

    public void setDatos(JsonNode datos) {
        this.datos = datos;
    }

    public Ipv6Consulta.Estado getEstado() {
        return estado;
    }

    public void setEstado(Ipv6Consulta.Estado estado) {
        this.estado = estado;
    }
}
