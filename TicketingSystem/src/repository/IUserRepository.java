package repository;

import java.time.Instant;
import java.util.List;

import domain.ApplicationUser;
import domain.ApplicationUserRole;

public interface IUserRepository {
	ApplicationUser getUserByUsername(String username);
	void createUserLoginAttempt(Instant date, String username, boolean succeeded);
	void updateAccessFailedCount(ApplicationUser user);
	List<ApplicationUser> getAll();
	List<ApplicationUser> getAllCustomers();
	List<ApplicationUser> getAllEmployees();
	List<ApplicationUserRole> GetAllRoles();
	ApplicationUser createUser(ApplicationUser user);
	ApplicationUser updateUser(ApplicationUser user);
}
