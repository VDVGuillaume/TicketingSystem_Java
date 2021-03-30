package repository;

import domain.ApplicationUser;
import domain.Ticket;

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
}
