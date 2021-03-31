package Helpers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.microsoft.sqlserver.jdbc.StringUtils;

public class PasswordHasher {
	private byte _formatMarker;
	// private KeyDerivationPrf _prf; // Requires Microsoft.AspNetCore
	// private HashAlgorithmName _hashAlgorithmName;
	private boolean _includeHeaderInfo;
	private int _saltLength;
	private int _requestedLength;
	private int _iterCount;

	public PasswordHasher() {
		// IdentityV2
		// _formatMarker = 0x00;
		// _prf = KeyDerivationPrf.HMACSHA1; // Requires Microsoft.AspNetCore
		// _hashAlgorithmName = HashAlgorithmName.SHA1;
		// _includeHeaderInfo = false;
		// _saltLength = 128 / 8; // bits/1 byte = 16
		// _requestedLength = 256 / 8; // bits/1 byte = 32
		// _iterCount = 1000;

		// IdentityV3
		_formatMarker = 0x01;
		// _prf = KeyDerivationPrf.HMACSHA256; // Requires Microsoft.AspNetCore
		// _hashAlgorithmName = HashAlgorithmName.SHA256;
		_includeHeaderInfo = true;
		_saltLength = 128 / 8; // bits/1 byte = 16
		_requestedLength = 256 / 8; // bits/1 byte = 32
		_iterCount = 10000;
	}

	private static byte[] getEncryptedPassword(String password, byte[] salt, int iterations, int derivedKeyLength)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength * 8);

		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

		return f.generateSecret(spec).getEncoded();
	}

	public String hashPassword(String password) {
		if (StringUtils.isEmpty(password))
			throw new IllegalArgumentException("password");

		byte[] salt = new byte[_saltLength];

		SecureRandom rnd = new SecureRandom();
		rnd.nextBytes(salt);

		byte[] subkey = new byte[_requestedLength];

		// generate subkey
		try {
			subkey = getEncryptedPassword(password, salt, _iterCount, _requestedLength);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		var headerByteLength = 1; // Format marker only
		if (_includeHeaderInfo)
			headerByteLength = 13;

		var outputBytes = new byte[headerByteLength + salt.length + subkey.length];

		outputBytes[0] = (byte) _formatMarker;

		if (_includeHeaderInfo) {
			writeNetworkByteOrder(outputBytes, 1, 1);
			writeNetworkByteOrder(outputBytes, 5, _iterCount);
			writeNetworkByteOrder(outputBytes, 9, _saltLength);
		}

		System.arraycopy(salt, 0, outputBytes, headerByteLength, salt.length);
		System.arraycopy(subkey, 0, outputBytes, headerByteLength + _saltLength, subkey.length);

		return Base64.getEncoder().encodeToString(outputBytes);
	}

	public boolean verifyHashedPassword(String hashedPassword, String providedPassword) {
		if (StringUtils.isEmpty(providedPassword))
			return false;

		byte[] decodedHashedPassword;
		decodedHashedPassword = Base64.getDecoder().decode(hashedPassword);

		if (decodedHashedPassword.length == 0)
			return false;

		// Read the format marker
		var verifyMarker = (byte) decodedHashedPassword[0];
		if (_formatMarker != verifyMarker)
			return false;

		try {
			var headerByteLength = 1; // Format marker only
			if (_includeHeaderInfo)
				headerByteLength = 13;

			// Read the salt
			byte[] salt = new byte[_saltLength];
			System.arraycopy(decodedHashedPassword, headerByteLength, salt, 0, salt.length);

			// Read the subkey (the rest of the payload)
			int subkeyLength = decodedHashedPassword.length - headerByteLength - salt.length;

			if (_requestedLength != subkeyLength)
				return false;

			byte[] expectedSubkey = new byte[subkeyLength];
			System.arraycopy(decodedHashedPassword, headerByteLength + salt.length, expectedSubkey, 0,
					expectedSubkey.length);

			// Hash the incoming password and verify it
			byte[] actualSubkey = new byte[_requestedLength];
			actualSubkey = getEncryptedPassword(providedPassword, salt, _iterCount, subkeyLength);

			return byteArraysEqual(actualSubkey, expectedSubkey);
		} catch (Exception ex) {
			return false;
		}
	}

	private static boolean byteArraysEqual(byte[] a, byte[] b) {
		if (a == null && b == null)
			return true;
		if (a == null || b == null || a.length != b.length)
			return false;
		var areSame = true;
		for (var i = 0; i < a.length; i++) {
			areSame &= (a[i] == b[i]);
		}
		return areSame;
	}

	private static long readNetworkByteOrder(byte[] buffer, int offset) {
		return ((long) (buffer[offset + 0]) << 24) | ((long) (buffer[offset + 1]) << 16)
				| ((long) (buffer[offset + 2]) << 8) | ((long) (buffer[offset + 3]));
	}

	private static void writeNetworkByteOrder(byte[] buffer, int offset, long value) {
		buffer[offset + 0] = (byte) (value >> 24);
		buffer[offset + 1] = (byte) (value >> 16);
		buffer[offset + 2] = (byte) (value >> 8);
		buffer[offset + 3] = (byte) (value >> 0);
	}
}
