package dev.jacr.job_async.repository;

import dev.jacr.job_async.model.Ipv6Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Ipv6ConsultaRepository extends JpaRepository<Ipv6Consulta, Long> {
    List<Ipv6Consulta> findByIp (String ip);
}
