package maus.dat19a.exam.jpa;

import maus.dat19a.exam.models.Supervisor;
import maus.dat19a.exam.repos.ISupervisorRepo;
import maus.dat19a.exam.services.ISupervisorService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SupervisorJPA implements ISupervisorService {

    private final ISupervisorRepo supervisorRepo;

    public SupervisorJPA(ISupervisorRepo supervisorRepo) {
        this.supervisorRepo = supervisorRepo;
    }

    @Override
    public Set<Supervisor> findAll() {
        Set<Supervisor> set = new HashSet<>();
        supervisorRepo.findAll().forEach(set::add);
        return set;
    }

    @Override
    public Supervisor save(Supervisor object) {
        return supervisorRepo.save(object);
    }

    @Override
    public void delete(Supervisor object) {
        supervisorRepo.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        supervisorRepo.deleteById(aLong);
    }

    @Override
    public Optional<Supervisor> findById(Long aLong) {
        return supervisorRepo.findById(aLong);
    }
}
