package com.besysoft.taller.service.implementation;

import com.besysoft.taller.exception.NonExistingException;
import com.besysoft.taller.model.Administrativo;
import com.besysoft.taller.repository.AdminRepository;
import com.besysoft.taller.service.interfaces.IAdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImple implements IAdminService {

    private final AdminRepository repo;

    public AdminServiceImple(AdminRepository repo) {
        this.repo = repo;
    }

    @Override
    public Administrativo altaAdmin(Administrativo admin) {
        return this.repo.save(admin);
    }

    @Override
    public List<Administrativo> verAdmin() {
        return (List<Administrativo>) this.repo.findAll();
    }

    @Override
    public Administrativo buscarById(Long id) {
        Optional<Administrativo> oAdmin=this.repo.findById(id);
        if(oAdmin.isEmpty()){
            throw new NonExistingException(
                    String.format("El administrativo con id %d no existe",
                            id)
            );
        }
        return oAdmin.get();
    }
}
