package tender.example.tender.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import tender.example.tender.dto.RoleRequest;
import tender.example.tender.entity.Role;
import tender.example.tender.exception.BusinessException;
import tender.example.tender.iservice.IRole;
import tender.example.tender.repository.RoleRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService implements IRole {

    private final RoleRepository roleRepository;

    @Override
    public Role findById(Long roleId){
        return roleRepository.findById(roleId).orElseThrow(() -> new BusinessException("Role not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    @Override
    public Role saveRole(RoleRequest role){
        Role roleBuilder = Role.builder()
                .name(role.getName())
                .description(role.getDescription())
                .build();
        roleRepository.save(roleBuilder);
        return roleBuilder;
    }

}
