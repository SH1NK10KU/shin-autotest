package cn.shin.autotest.exception;

/**
 * 
 * @author Shin Feng
 * @date 2014-10-30
 * 
 */
public class ElementIsDisplayedException extends AssertionError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementIsDisplayedException() {
		super();
	}

	public ElementIsDisplayedException(String msg) {
		super(msg);
	}

	public ElementIsDisplayedException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ElementIsDisplayedException(Throwable cause) {
		super(cause);
	}
}
