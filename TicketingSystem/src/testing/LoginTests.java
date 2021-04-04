package testing;

import java.time.Instant;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.*;

import Constants.Constants;
import Providers.DateProvider;
import controller.LoginController;
import domain.ApplicationUser;
import exceptions.ValidationException;
import repository.IUserRepository;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;


//Junit5 uitbreiden met Mockito
@ExtendWith(MockitoExtension.class)
class LoginTests {
	
	//create mock objects
	@Mock
	private IUserRepository loginRepository;
	@Mock
	private DateProvider dateProvider;
	
	/* Create the class that is being tested.
	 * mock objects are injected through construction injection or setter injection.
	 * Works like @BeforeEach
	 */
	@InjectMocks
	private LoginController loginController;

	@Test
	public void Login_Invalid_User_Not_Found() {
		Mockito
			.when(loginRepository.getUserByUsername("test"))
			.thenReturn(null);
		
		var exception = Assertions.assertThrows(ValidationException.class, () -> loginController.login("test", "P@ssword1"));
		Assertions.assertEquals(Constants.ERROR_LOGIN_USER_NOT_FOUND, exception.getMessage());
	}
	
	@ParameterizedTest
	@ValueSource(ints = {5, 6, 10})
	public void Login_Invalid_User_Locked(int accessFailedCount) {
		String username = "test";
		String password = "P@ssword1";
		var user = new ApplicationUser();
		user.setUsername(username);
		user.setAccessFailedCount(accessFailedCount);
		
		Mockito
			.when(loginRepository.getUserByUsername(username))
			.thenReturn(user);
		
		var exception = Assertions.assertThrows(ValidationException.class, () -> loginController.login(username, password));
		
		Assertions.assertEquals(Constants.ERROR_LOGIN_USER_LOCKED_OUT, exception.getMessage());
	}
	
	@ParameterizedTest
	@MethodSource("get_invalid_logins")
	public void Login_Invalid_Login_Failed(String id, String username, String passwordHashed, String password) {
		var user = new ApplicationUser();
		var dateNow = Instant.now();
		user.setUsername(username);
		user.setPasswordHash(passwordHashed);
		user.setId(id);
		
		
		Mockito
			.when(loginRepository.getUserByUsername(username))
			.thenReturn(user);
		
		Mockito
			.when(dateProvider.getCurrentDate())
			.thenReturn(dateNow);
		
		var exception = Assertions.assertThrows(ValidationException.class, () -> loginController.login(username, password));
		
		Assertions.assertEquals(Constants.ERROR_LOGIN_FAILED, exception.getMessage());
		Mockito.verify(loginRepository).createUserLoginAttempt(dateNow, username, false);
		Mockito.verify(loginRepository).updateAccessFailedCount(user);
		Assertions.assertEquals(user.getAccessFailedCount(), 1);
	}
	
	@ParameterizedTest
	@MethodSource("get_valid_logins")
	public void Login_valid_should_login(String id, String username, String passwordHashed, String password) {
		var user = new ApplicationUser();
		var dateNow = Instant.now();
		user.setUsername(username);
		user.setPasswordHash(passwordHashed);
		user.setId(id);
		
		Mockito
			.when(loginRepository.getUserByUsername(username))
			.thenReturn(user);
		
		Mockito
			.when(dateProvider.getCurrentDate())
			.thenReturn(dateNow);
		
		loginController.login(username, password);
		
		Mockito.verify(loginRepository).createUserLoginAttempt(dateNow, username, true);
		Mockito.verify(loginRepository).updateAccessFailedCount(user);
		Assertions.assertEquals(user.getAccessFailedCount(), 0);
	}
	
	private static Stream<Arguments> get_valid_logins(){
		return Stream.of(
				Arguments.of("1e6db034-8dca-410f-a411-eb6cca114e48", "technician2", "AQAAAAEAACcQAAAAEHyR6dRkAGYN1RYz/i5No2WV8jUsA3t1HgduqnS5VL5MYn6dT1jw8Uh/55eCoWnSzg==", "P@ssword1"),
				Arguments.of("2ba4b3ae-941f-4747-b057-3eeb6cef82bf", "customer4", "AQAAAAEAACcQAAAAELNWMQhsTNRicZhvmWQGzp9HD+XFuIicTbFGEL2VMO8XBS8Jm3BUtVAPoeT7t/MQVw==", "P@ssword1"),
				Arguments.of("731b3790-f294-4f91-ad35-f9225b82b3d4", "customer2", "AQAAAAEAACcQAAAAEAVCCKwB6bDPcXQKCqJYuMg9PSAttiBOwwI8PVGKMhJ8EpMZdg4l+T2Vajw6KbcnSg==", "P@ssword1"),
				Arguments.of("a423e60e-e1f1-4cf3-a8d0-51f08084cde7", "supportmanager", "AQAAAAEAACcQAAAAEBAzhVoWQTHnrRGq/tKrI8Dliz7F8NWw5kUDQehexh+jRFLMK0b+ipD2Ppa+Fxaz4Q==", "P@ssword1"),
				Arguments.of("af9c472b-32a2-4086-ab8a-20a6804894f0", "customer3", "AQAAAAEAACcQAAAAEAB2GXxt6SQoGRRu6Fj+6L3/DhcWlW7h3V54aZJW7bgOexcdLe1E/bkPPa3HqX9FYg==", "P@ssword1"),
				Arguments.of("b687dd23-22cb-4f11-b539-1a87052f819b", "technician", "AQAAAAEAACcQAAAAEA1ozoo66QnQld99ucA1vgNTRRxeZGNXvRoeM7D2LlpG1UfRuFEKLD8Y7pPCW0+geA==", "P@ssword1"),
				Arguments.of("b7ba7b53-dab8-44b2-95a9-c8a909770f49", "customer", "AQAAAAEAACcQAAAAEFVq+88au0jbYTf/tyW0UEfLVLHnKak2yHycQjB66kRB9P1E4jCZnkA7aPUJn53RjQ==", "P@ssword1")
				);
		}
	
	private static Stream<Arguments> get_invalid_logins(){
		return Stream.of(
				Arguments.of("1e6db034-8dca-410f-a411-eb6cca114e48", "technician2", "AQAAAAEAACcQAAAAEHyR6dRkAGYN1RYz/i5No2WV8jssA3t1HgduqnS5VL5MYn6dT1jw8Uh/55eCoWnSzg==", "P@ssword1"),
				Arguments.of("2ba4b3ae-941f-4747-b057-3eeb6cef82bf", "customer4", "AQAAAAEAACcQAAAAELNWMQhsTNRicZhvmWQGzp9HD+XFuIicTbFGEL2VMO8XBS8Jm3aUtVAPoeT7t/MQVw==", "P@ssword1"),
				Arguments.of("731b3790-f294-4f91-ad35-f9225b82b3d4", "customer2", "AQAAAAEAACcQAAAAEAVCCKwB6bDPcXQKCqJYuMg9PSAttiBOwwI8PVGKMhJ8EpMZdg4l+T2Vajd6KbcnSg==", "P@ssword1"),
				Arguments.of("a423e60e-e1f1-4cf3-a8d0-51f08084cde7", "supportmanager", "AQAAAAEAACcQAAAAEBAzpVoWQTHnrRGq/tKrI8Dliz7F8NWw5kUDQehexh+jRFLMK0b+ipD2Ppa+Fxaz4Q==", "P@ssword1"),
				Arguments.of("af9c472b-32a2-4086-ab8a-20a6804894f0", "customer3", "AQAAAAEAACcQAAAAEAB2GXxt6SQoGqRu6Fj+6L3/DhcWlW7h3V54aZJW7bgOexcdLe1E/bkPPa3HqX9FYg==", "P@ssword1"),
				Arguments.of("b687dd23-22cb-4f11-b539-1a87052f819b", "technician", "AQAAAAEAACcQAAAAEA1ozoo66QnQad99ucA1vgNTRRxeZGNXvRoeM7D2LlpG1UfRuskdLD8Y7pPCW0+geA==", "P@ssword1"),
				Arguments.of("b7ba7b53-dab8-44b2-95a9-c8a909770f49", "customer", "AQAAAAEAACcQAAAAEFVq+88ausjbYTf/tyW0UEfLVLHnKak2yHycQjB66kRB9P1E4jCZnss7aPUJn53RjQ==", "P@ssword1")
				);
		}
}
