package repository;

import java.time.Instant;
import java.util.List;

import domain.ApplicationUser;
import domain.ApplicationUserRole;

public interface IUserRepository {
	ApplicationUser getUserByUsername(String username);
	void createUserLoginAttempt(Instant date, String username, boolean succeeded);
	void updateAccessFailedCount(ApplicationUser user);
}
