package co.com.poli.backlog.services;

import co.com.poli.backlog.domain.Backlog;
import co.com.poli.backlog.repository.IBacklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BacklogService implements IBacklogService{

    @Autowired
    private IBacklogRepository iBacklogRepository;

    @Override
    public Backlog createNewBacklog(Backlog backlog) {
        return iBacklogRepository.save(backlog);
    }


}
