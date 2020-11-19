package framework.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
  public static final String MM_DD_YYYY = "MM/dd/yyyy";

  public static String localDateToString(LocalDate localDate) {
    if (localDate == null) {
      return null;
    }
    return localDate.format(DateTimeFormatter.ofPattern(MM_DD_YYYY));
  }

  public static LocalDate stringToLocalDate(String dateString){

    if(dateString == null || "".equals(dateString)) {
      return null;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(MM_DD_YYYY);

    //convert String to LocalDate
    return LocalDate.parse(dateString, formatter);
  }

  public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }
}
