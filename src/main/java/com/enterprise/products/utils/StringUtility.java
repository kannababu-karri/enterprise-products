package com.enterprise.products.utils;

import java.util.Optional;

public final class StringUtility {
   /**
    * Default constructor - disabled.
    */
   private StringUtility() {
   }

 
   public static boolean isEmpty(String s) {
      return s == null || s.trim().length() == 0;
   }

 
   public static String filterNull(String str) {
      //return str != null ? str : "";
	   return Optional.ofNullable(str).orElse("");
   }

 
   public static String trimString(String newString) {
      String returnString = null;
      if (newString != null) {
         returnString = newString.trim();
      }
      return returnString;
   } 

   public static boolean isNumber(String s) {
      int i;

      // Search through string's characters one by one
      // until we find a non-numeric character.
      // When we do, return false; if we don't, return true.

      for (i = 0; i < s.length(); i++) {
         // Check that current character is number.
         if (!Character.isDigit(s.charAt(i)))
            return false;
      }

      // All characters are numbers.
      return true;
   }

   public static String stripCharsNotInBag(String s, String bag) {
      // Search through string's characters one by one.
      // If character is in bag, append to returnString.

      if (s == null)
         return null;

      int i;

      StringBuffer returnString = new StringBuffer("");
      // Search through string's characters one by one.
      // If character is in bag, append to returnString.

      for (i = 0; i < s.length(); i++) {
         // Check that current character isn't whitespace.

         String c = String.valueOf(s.charAt(i));
         if (bag.indexOf(c) != -1) {
            returnString.append(c);
         }
      }
      return returnString.toString();
   }

  
}