package repository;

import domain.UserLoginAttempt;

public class UserLoginAttemptDaoJpa extends GenericDaoJpa<UserLoginAttempt> implements UserLoginAttemptDao  {
    public UserLoginAttemptDaoJpa() {
        super(UserLoginAttempt.class);
    }
}