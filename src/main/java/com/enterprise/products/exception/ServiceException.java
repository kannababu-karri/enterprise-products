package com.enterprise.products.exception;

/**
 * ServiceException
 *
 */
public class ServiceException extends ApplicationException {

	private static final long serialVersionUID = 1L;

   /**
    * 
    */
   public ServiceException() {
      super();
   }

   /**
    * @param message
    */
   public ServiceException(String message) {
      super(message);
   }

   /**
    * @param message
    * @param cause
    */
   public ServiceException(String message, Throwable cause) {
      super(message, cause);
   }

   /**
    * @param cause
    */
   public ServiceException(Throwable cause) {
      super(cause);
   }

}
