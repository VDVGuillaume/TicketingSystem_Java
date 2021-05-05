package repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.ApplicationUser;
import domain.ApplicationUserRole;
import domain.UserLoginAttempt;

public class UserRepository implements IUserRepository {
    private UserDaoJpa userDao;
    private UserLoginAttemptDaoJpa userLoginAttemptDao;
    private UserRoleDaoJpa userRoleDao;

    public UserRepository(){
    	this.userDao = new UserDaoJpa();
    	this.userLoginAttemptDao = new UserLoginAttemptDaoJpa();
    	this.userRoleDao = new UserRoleDaoJpa();
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
    public List<ApplicationUser> getAllCustomers(){
    	var users = getAll();
    	List<ApplicationUser> customers = new ArrayList<ApplicationUser>();
    	
    	for(var user : users) {
			if(user.getUserRoles().stream().anyMatch(item -> item.getName().equals(Constants.Constants.CUSTOMER_ROLE))) {
				customers.add(user);
			}
		}
    	
    	return customers;
    }

    @Override
    public List<ApplicationUser> getAllEmployees(){
    	var users = getAll();
    	List<ApplicationUser> employees = new ArrayList<ApplicationUser>();
    	
    	for(var user : users) {			
			if(
					user.getUserRoles().stream().anyMatch(item -> 
						item.getName().equals(Constants.Constants.ADMINISTRATOR_ROLE) || 
						item.getName().equals(Constants.Constants.SUPPORTMANAGER_ROLE) ||
						item.getName().equals(Constants.Constants.TECHNICIAN_ROLE)
						)
				) {
			employees.add(user);
			}	
    	}
    	return employees;
    }

	@Override
	public List<ApplicationUser> getAll() {
		return userDao.findAll();
	}
	
	@Override 
	public List<ApplicationUserRole> GetAllRoles(){
		return userRoleDao.findAll();
	}
	
	@Override
	public 	ApplicationUser createUser(ApplicationUser user) {
		try {
			GenericDaoJpa.startTransaction();	
			userDao.insert(user);
			GenericDaoJpa.commitTransaction();
		}catch(Exception ex){
			GenericDaoJpa.rollbackTransaction();
			throw ex;
		}
		
		return user;
	}
	
	@Override
	public 	ApplicationUser updateUser(ApplicationUser user) {
		try {
			GenericDaoJpa.startTransaction();	
			userDao.update(user);
			GenericDaoJpa.commitTransaction();
		}catch(Exception ex){
			GenericDaoJpa.rollbackTransaction();
			throw ex;
		}
		
		return user;
	}	
}
