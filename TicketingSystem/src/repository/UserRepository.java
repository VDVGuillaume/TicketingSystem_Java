package repository;

import java.time.Instant;
import java.util.List;

import domain.ApplicationUser;
import domain.UserLoginAttempt;

public class UserRepository implements IUserRepository {
    private UserDaoJpa userDao;
    private UserLoginAttemptDaoJpa userLoginAttemptDao;

    public UserRepository(){
    	this.userDao = new UserDaoJpa();
    	this.userLoginAttemptDao = new UserLoginAttemptDaoJpa();
    }
    
	@Override
	public ApplicationUser getUserByUsername(String username) {
		ApplicationUser result = (ApplicationUser)null;
		try {
			GenericDaoJpa.startTransaction();	
			result = userDao.getUserByUserName(username);
			GenericDaoJpa.commitTransaction();
		}catch(Exception ex){
			GenericDaoJpa.rollbackTransaction();
			throw ex;
		}
		
		return result;
	}

	@Override
	public void createUserLoginAttempt(Instant date, String username, boolean succeeded) {
		try {
			GenericDaoJpa.startTransaction();	
			userLoginAttemptDao.insert(new UserLoginAttempt(date.toString(), username, succeeded));		
			GenericDaoJpa.commitTransaction();
		}catch(Exception ex){
			GenericDaoJpa.rollbackTransaction();
			throw ex;
		}	
	}

	@Override
	public void updateAccessFailedCount(ApplicationUser user) {
		try {
			GenericDaoJpa.startTransaction();	
			userDao.update(user);
			GenericDaoJpa.commitTransaction();
		}catch(Exception ex){
			GenericDaoJpa.rollbackTransaction();
			throw ex;
		}	
	}

	@Override
	public List<ApplicationUser> getAll() {
		return userDao.findAll();
	}
}
