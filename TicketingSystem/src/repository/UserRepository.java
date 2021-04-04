package repository;

import java.time.Instant;
import domain.ApplicationUser;

public class UserRepository implements IUserRepository {
    private UserDaoJpa userDao;

    public UserRepository(){
    	this.userDao = new UserDaoJpa();
    }
    
	@Override
	public ApplicationUser getUserByUsername(String username) {
		return userDao.getUserByUserName(username);
	}

	@Override
	public void createUserLoginAttempt(Instant date, String username, boolean succeeded) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void increaseAccessFailedCount(String userId, int increment) {
		// TODO Auto-generated method stub
	}
}
