package co.com.poli.backlog.repository;

import co.com.poli.backlog.domain.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBacklogRepository extends JpaRepository<Backlog,Long> {

}
