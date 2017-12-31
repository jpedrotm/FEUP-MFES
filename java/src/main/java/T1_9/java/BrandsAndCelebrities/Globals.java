package T1_9.java.BrandsAndCelebrities;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Globals {
  public static Number DaysOfMonth(final Number year, final Number month) {

    Boolean orResult_2 = false;

    if (Utils.equals(month, 1L)) {
      orResult_2 = true;
    } else {
      Boolean orResult_3 = false;

      if (Utils.equals(month, 3L)) {
        orResult_3 = true;
      } else {
        Boolean orResult_4 = false;

        if (Utils.equals(month, 5L)) {
          orResult_4 = true;
        } else {
          Boolean orResult_5 = false;

          if (Utils.equals(month, 7L)) {
            orResult_5 = true;
          } else {
            Boolean orResult_6 = false;

            if (Utils.equals(month, 8L)) {
              orResult_6 = true;
            } else {
              Boolean orResult_7 = false;

              if (Utils.equals(month, 10L)) {
                orResult_7 = true;
              } else {
                orResult_7 = Utils.equals(month, 12L);
              }

              orResult_6 = orResult_7;
            }

            orResult_5 = orResult_6;
          }

          orResult_4 = orResult_5;
        }

        orResult_3 = orResult_4;
      }

      orResult_2 = orResult_3;
    }

    if (orResult_2) {
      return 31L;

    } else {
      if (Utils.equals(month, 2L)) {
        if (isLeapYear(year)) {
          return 29L;

        } else {
          return 28L;
        }

      } else {
        return 30L;
      }
    }
  }

  public static Date calculateFinalDate(final Date date, final Number duration) {

    Number d = duration;
    Date finalDate = Utils.copy(date);
    Boolean whileCond_2 = true;
    while (whileCond_2) {
      whileCond_2 = d.longValue() > 0L;
      if (!(whileCond_2)) {
        break;
      }

      {
        if (d.longValue() + finalDate.day.longValue()
            > DaysOfMonth(finalDate.year, finalDate.month).longValue()) {
          d =
              d.longValue()
                  - (DaysOfMonth(finalDate.year, finalDate.month).longValue()
                      - finalDate.day.longValue())
                  - 1L;
          finalDate.day = 1L;
          if (Utils.equals(finalDate.month, 12L)) {
            finalDate.month = 1L;
            finalDate.year = finalDate.year.longValue() + 1L;

          } else {
            finalDate.month = finalDate.month.longValue() + 1L;
          }

        } else {
          finalDate.day = finalDate.day.longValue() + d.longValue();
          d = 0L;
        }
      }
    }

    return Utils.copy(finalDate);
  }

  public Globals() {}

  private static Boolean isLeapYear(final Number year) {

    Boolean orResult_8 = false;

    Boolean andResult_23 = false;

    if (Utils.equals(Utils.mod(year.longValue(), 4L), 0L)) {
      if (!(Utils.equals(Utils.mod(year.longValue(), 100L), 0L))) {
        andResult_23 = true;
      }
    }

    if (andResult_23) {
      orResult_8 = true;
    } else {
      orResult_8 = Utils.equals(Utils.mod(year.longValue(), 400L), 4L);
    }

    return orResult_8;
  }

  public static Boolean compareDates(final Date d1, final Date d2) {

    Boolean orResult_9 = false;

    if (d1.year.longValue() < d2.year.longValue()) {
      orResult_9 = true;
    } else {
      Boolean orResult_10 = false;

      Boolean andResult_24 = false;

      if (Utils.equals(d1.year, d2.year)) {
        if (d1.month.longValue() < d2.month.longValue()) {
          andResult_24 = true;
        }
      }

      if (andResult_24) {
        orResult_10 = true;
      } else {
        Boolean andResult_25 = false;

        if (Utils.equals(d1.year, d2.year)) {
          Boolean andResult_26 = false;

          if (Utils.equals(d1.month, d2.month)) {
            if (d1.day.longValue() <= d2.day.longValue()) {
              andResult_26 = true;
            }
          }

          if (andResult_26) {
            andResult_25 = true;
          }
        }

        orResult_10 = andResult_25;
      }

      orResult_9 = orResult_10;
    }

    return orResult_9;
  }

  public String toString() {

    return "Globals{}";
  }

  public static class Date implements Record {
    public Number year;
    public Number month;
    public Number day;

    public Date(final Number _year, final Number _month, final Number _day) {

      year = _year;
      month = _month;
      day = _day;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(year, other.year))
          && (Utils.equals(month, other.month))
          && (Utils.equals(day, other.day));
    }

    public int hashCode() {

      return Utils.hashCode(year, month, day);
    }

    public Date copy() {

      return new Date(year, month, day);
    }

    public String toString() {

      return "mk_Globals`Date" + Utils.formatFields(year, month, day);
    }
  }

  public static Boolean inv_Date(final Date d) {

    Boolean andResult_27 = false;

    if (d.month.longValue() <= 12L) {
      if (d.day.longValue() <= DaysOfMonth(d.year, d.month).longValue()) {
        andResult_27 = true;
      }
    }

    return andResult_27;
  }
}
