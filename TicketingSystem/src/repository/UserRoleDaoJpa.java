package repository;

import domain.ApplicationUserRole;

public class UserRoleDaoJpa extends GenericDaoJpa<ApplicationUserRole> implements UserRoleDao  {
    public UserRoleDaoJpa() {
        super(ApplicationUserRole.class);
    }
}
