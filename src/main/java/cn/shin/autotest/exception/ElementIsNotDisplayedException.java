package cn.shin.autotest.exception;

/**
 * 
 * @author Shin Feng
 * @date 2014-10-30
 * 
 */
public class ElementIsNotDisplayedException extends AssertionError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementIsNotDisplayedException() {
		super();
	}

	public ElementIsNotDisplayedException(String msg) {
		super(msg);
	}

	public ElementIsNotDisplayedException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ElementIsNotDisplayedException(Throwable cause) {
		super(cause);
	}
}
