package cn.com.carit.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class TypeCastException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	Throwable nested;

	public TypeCastException() {
		nested = null;
	}

	public TypeCastException(String msg) {
		super(msg);
		nested = null;
	}

	public TypeCastException(String msg, Throwable nested) {
		super(msg);
		this.nested = null;
		this.nested = nested;
	}

	public TypeCastException(Throwable nested) {
		this.nested = null;
		this.nested = nested;
	}

	public String getMessage() {
		if (nested != null){
			return super.getMessage() + " (" + nested.getMessage() + ")";
		}else{
			return super.getMessage();
		}
	}

	public String getNonNestedMessage() {
		return super.getMessage();
	}

	public Throwable getNested() {
		if (nested == null){
			return this;
		}else{
			return nested;
		}
	}

	public void printStackTrace() {
		super.printStackTrace();
		if (nested != null){
			nested.printStackTrace();
		}
	}

	public void printStackTrace(PrintStream ps) {
		super.printStackTrace(ps);
		if (nested != null){
			nested.printStackTrace(ps);
		}
	}

	public void printStackTrace(PrintWriter pw) {
		super.printStackTrace(pw);
		if (nested != null){
			nested.printStackTrace(pw);
		}
	}
}
