package com.emfso.henry.emfso_attendence.database_content;

/**
 * Created by henry on 2017-10-29.
 */

public class AttendenceEntry {
  public int flyer_num;
  public String event;
  public String upperStart;
  public String upperEnd;
  public String lowerStart;
  public String lowerEnd;
  public int spectators;

  public AttendenceEntry(String flyer_num, String event, String upperStart, String upperEnd,
                         String lowerStart, String lowerEnd, String spectators) {
    this.flyer_num = Integer.parseInt(flyer_num);
    this.event = event;
    this.upperStart = upperStart;
    this.upperEnd = upperEnd;
    this.lowerStart = lowerStart;
    this.lowerEnd = lowerEnd;
    this.spectators = Integer.parseInt(spectators);
  }

  public int getFlyer_num() {
    return flyer_num;
  }

  public String getEvent() {
    return event;
  }

  public String getUpperStart() {
    return upperStart;
  }

  public String getUpperEnd() {
    return upperEnd;
  }

  public String getLowerStart() {
    return lowerStart;
  }

  public String getLowerEnd() {
    return lowerEnd;
  }

  public int getSpectators() {
    return spectators;
  }

}
