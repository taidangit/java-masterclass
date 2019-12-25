package guru.springframework.springbootwebapp.service.map;

import guru.springframework.springbootwebapp.model.Role;
import guru.springframework.springbootwebapp.service.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("map")
public class RoleMapService extends AbstractMapService<Role, Long> implements RoleService {

    @Override
    public Set<Role> findAll() {
        return super.findAll();
    }

    @Override
    public Role findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Role save(Role role) {
        return super.save(role);
    }

    @Override
    public void delete(Role role) {
        super.delete(role);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Role findByRole(String role) {
        return null;
    }
}
