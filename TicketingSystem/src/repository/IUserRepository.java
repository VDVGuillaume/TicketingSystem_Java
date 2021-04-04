package repository;

import java.time.Instant;

import domain.ApplicationUser;

public interface IUserRepository {
	ApplicationUser getUserByUsername(String username);
	void createUserLoginAttempt(Instant date, String username, boolean succeeded);
	void updateAccessFailedCount(ApplicationUser user);
}
