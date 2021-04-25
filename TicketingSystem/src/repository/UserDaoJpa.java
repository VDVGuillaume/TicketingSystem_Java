package repository;

import domain.ApplicationUser;
import domain.Ticket;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;



public class UserDaoJpa extends GenericDaoJpa<ApplicationUser> implements UserDao  {
    public UserDaoJpa() {
        super(ApplicationUser.class);
    }

    @Override
    public ApplicationUser getUserByUserName(String userName) throws EntityNotFoundException {
        try {
            return em.createNamedQuery("ApplicationUser.findUserByUserName", ApplicationUser.class)
                 .setParameter("userName", userName)
                .getSingleResult();
        } catch (NoResultException ex) {
            throw new EntityNotFoundException();
        } 
    }
    
    @Override
    public List<ApplicationUser> getAllCustomers(){
    	return em.createNamedQuery("ApplicationUser.getCustomers", ApplicationUser.class)
    			.getResultList();
    }
    
    @Override
    public List<ApplicationUser> getAllEmployees(){
    	return em.createNamedQuery("ApplicationUser.getEmployees", ApplicationUser.class)
    			.getResultList();
    }
}
