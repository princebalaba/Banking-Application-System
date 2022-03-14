<<<<<<< HEAD
package com.learning.exceptions;

public class SecretDetailsDoNotMatchException extends Exception {
=======
  package com.learning.exceptions;

public class SecretDetailsDoNotMatchException extends RuntimeException {
>>>>>>> branch 'master' of https://github.com/KiLee16/bankApplication.git

	public SecretDetailsDoNotMatchException(String message) {
		super(message);

	}

	@Override
	public String toString() {
		return super.getMessage();
	}

}
