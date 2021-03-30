package repository;
import domain.ApplicationUser;
import domain.Ticket;
import javax.persistence.EntityNotFoundException;

public interface UserDao extends GenericDao<ApplicationUser>  {
        public ApplicationUser getUserByUserName(String userName) throws EntityNotFoundException;   
}