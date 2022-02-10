package ru.buildservice.project;

import java.util.Calendar;

public class Datetime {
    Long years = 31556952000L;
    Long jan = 2678400000L;
    Long feb = 2505600000L;
    Long apr = 2592000000L;

    Calendar date = Calendar.getInstance();

    Long datetime = date.getTimeInMillis();

    String month;
    Long ingaborga = datetime % years;


    public int extractYear() {

        return (int) (datetime / years) + 1970;
    }


    public String extractMonth() {

        if (ingaborga < jan) {
            month = "Январь";
        } else if (ingaborga - jan < feb) {
            month = "Февраль";

        } else if (ingaborga - jan - feb < jan) {
            month = "Март";
        } else if (ingaborga - (2 * jan) - feb < apr) {
            month = "Апрель";
        } else if (ingaborga - (2 * jan) - feb - apr < jan) {
            month = "Май";
        } else if (ingaborga - (3 * jan) - feb - apr < apr) {
            month = "Июнь";
        } else if (ingaborga - (3 * jan) - feb - (2 * apr) < jan) {
            month = "Июль";
        } else if (ingaborga - (4 * jan) - feb - (2 * apr) < apr) {
            month = "Август";
        } else if (ingaborga - (4 * jan) - feb - (3 * apr) < jan) {
            month = "Сентябрь";
        } else if (ingaborga - (5 * jan) - feb - (3 * apr) < apr) {
            month = "Октябрь";
        } else if (ingaborga - (5 * jan) - feb - (4 * apr) < jan) {
            month = "Ноябрь";

        } else month = "Декабрь";

        return month;
    }


}
