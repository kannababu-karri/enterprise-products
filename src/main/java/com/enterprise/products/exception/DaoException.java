package com.enterprise.products.exception;

/**
 * DaoException
 *
 */
public class DaoException extends ApplicationException {
	
	private static final long serialVersionUID = 1L;

   /**
    * 
    */
   public DaoException() {
      super();
   }

   /**
    * @param message
    */
   public DaoException(String message) {
      super(message);
   }

   /**
    * @param message
    * @param cause
    */
   public DaoException(String message, Throwable cause) {
      super(message, cause);
   }

   /**
    * @param cause
    */
   public DaoException(Throwable cause) {
      super(cause);
   }

}
