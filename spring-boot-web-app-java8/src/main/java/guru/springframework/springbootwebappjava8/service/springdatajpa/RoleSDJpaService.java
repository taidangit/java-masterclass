package guru.springframework.springbootwebappjava8.service.springdatajpa;

import guru.springframework.springbootwebappjava8.model.Role;
import guru.springframework.springbootwebappjava8.repository.RoleRepository;
import guru.springframework.springbootwebappjava8.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class RoleSDJpaService implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Set<Role> findAll() {
        Set<Role> roles=new HashSet<Role>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role findById(Long aLong) {
        return roleRepository.findById(aLong).orElse(null);
    }

    @Override
    public Role save(Role object) {
        return roleRepository.save(object);
    }

    @Override
    public void delete(Role object) {
        roleRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        roleRepository.deleteById(aLong);
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
