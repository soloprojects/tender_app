package tender.example.tender.iservice;

import tender.example.tender.dto.RoleRequest;
import tender.example.tender.entity.Role;

import java.util.List;

public interface IRole {
    Role findById(Long roleId);
    List<Role> getAll();
    Role saveRole(RoleRequest role);
}
