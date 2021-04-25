package repository;
import domain.ApplicationUser;
import domain.Ticket;

import java.util.List;

import javax.persistence.EntityNotFoundException;

public interface UserDao extends GenericDao<ApplicationUser>  {
        public ApplicationUser getUserByUserName(String userName) throws EntityNotFoundException;
        public List<ApplicationUser> getAllCustomers();
        public List<ApplicationUser> getAllEmployees();
}