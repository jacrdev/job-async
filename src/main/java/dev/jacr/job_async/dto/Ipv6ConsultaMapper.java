package dev.jacr.job_async.dto;

import dev.jacr.job_async.model.Ipv6Consulta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Ipv6ConsultaMapper {
    Ipv6ConsultaDto toDto(Ipv6Consulta ipv6Consulta);
    Ipv6Consulta toEntity(Ipv6ConsultaDto ipv6ConsultaDto);
}
