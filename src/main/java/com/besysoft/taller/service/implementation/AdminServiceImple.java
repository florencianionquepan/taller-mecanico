package com.besysoft.taller.service.implementation;

import com.besysoft.taller.model.Administrativo;
import com.besysoft.taller.repository.AdminRepository;
import com.besysoft.taller.service.interfaces.IAdminService;
import org.springframework.stereotype.Service;

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
}
