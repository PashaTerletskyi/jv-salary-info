package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final String DELIMITER = " ";
    static final int DATA_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOURS_INDEX = 2;
    static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromWithFormatter = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateToWithFormatter = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder salaryInfo = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        String[] splitLine;
        for (String name : names) {
            int earnedPerName = 0;
            for (String element : data) {
                splitLine = element.split(DELIMITER);
                if (name.equals(splitLine[NAME_INDEX])
                        && isDateInPeriod(splitLine[DATA_INDEX],
                        dateTimeFormatter, dateFromWithFormatter, dateToWithFormatter)) {
                    earnedPerName += Integer.parseInt(splitLine[HOURS_INDEX])
                            * Integer.parseInt(splitLine[SALARY_PER_HOUR_INDEX]);
                }
            } OutOfMemoryError
            salaryInfo = salaryInfo.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(earnedPerName);
        }
        return salaryInfo.toString();
    }

    private boolean isDateInPeriod(String date,
                                   DateTimeFormatter dateTimeFormatter,
                                   LocalDate dateFromWithFormatter,
                                   LocalDate dateToWithFormatter) {
        return (LocalDate.parse(date, dateTimeFormatter).isAfter(dateFromWithFormatter)
                || LocalDate.parse(date, dateTimeFormatter).equals(dateToWithFormatter))
                && (LocalDate.parse(date, dateTimeFormatter).isBefore(dateToWithFormatter)
                || LocalDate.parse(date, dateTimeFormatter).equals(dateToWithFormatter));
    }
}
