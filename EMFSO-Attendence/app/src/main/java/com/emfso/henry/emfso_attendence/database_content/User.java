package com.emfso.henry.emfso_attendence.database_content;

/**
 * Created by henry on 2017-10-29.
 */

public class User {
  private String firstName;
  private String lastName;
  private boolean outdoor;
  private boolean fixed_wing;
  private boolean rotary_wing;
  private boolean junior;

  public User (String first, String last, boolean out, boolean fixed,
               boolean rotary, boolean junior) {
    firstName = first;
    lastName = last;
    outdoor = out;
    fixed_wing = fixed;
    rotary_wing = rotary;
    this.junior = junior;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public boolean isOutdoor() {
    return outdoor;
  }

  public boolean isFixed_wing() {
    return fixed_wing;
  }

  public boolean isRotary_wing() {
    return rotary_wing;
  }

  public boolean isJunior() {
    return junior;
  }

}
