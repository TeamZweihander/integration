package com.zweihander.navup.notification.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Maria.Qumayo
 * @author George
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
   private String username;
   private String email;
   private String phone;

   public User() {

   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   @Override
   public String toString() {
      return getUsername();
   }
}