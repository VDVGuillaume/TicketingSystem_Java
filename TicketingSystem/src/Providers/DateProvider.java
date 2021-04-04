package Providers;

import java.time.Instant;

public class DateProvider {
	public Instant getCurrentDate() {
		return Instant.now();
	}
}
