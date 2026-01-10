package com.enterprise.products.exception;

/**
 * ApplicationException
 * 
 */
public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	protected String _message;
	protected boolean errorFlag;

   /**
    *  
    */
   public ApplicationException() {
      super();      
   }

   /**
    * @param message
    */
   public ApplicationException(String message) {
      super(message);
      _message = message;
   }

   /**
    * @param message
    * @param cause
    */
   public ApplicationException(String message, Throwable cause) {
      super(message, cause);
      _message = message;
   }

   /**
    * @param cause
    */
   public ApplicationException(Throwable cause) {
      super(cause);
   }

   public String getMessage() {
      return _message;
   }

   /**
    * @return Returns the errorFlag.    
    */
   public boolean isError() {
      return errorFlag;
   }
}
