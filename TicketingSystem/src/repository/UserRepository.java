package repository;

import java.time.Instant;
import domain.ApplicationUser;
import domain.UserLoginAttempt;

public class UserRepository implements IUserRepository {
    private UserDaoJpa userDao;
    private UserLoginAttemptDaoJpa userLoginAttemptDao;

    public UserRepository(){
    	this.userDao = new UserDaoJpa();
    	userLoginAttemptDao = new UserLoginAttemptDaoJpa();
    }
    
	@Override
	public ApplicationUser getUserByUsername(String username) {
		return userDao.getUserByUserName(username);
	}

	@Override
	public void createUserLoginAttempt(Instant date, String username, boolean succeeded) {
		userLoginAttemptDao.insert(new UserLoginAttempt(date.toString(), username, succeeded));		
	}

	@Override
	public void updateAccessFailedCount(ApplicationUser user) {
		userDao.update(user);
	}
}
