package com.project.remotemotormonitoring.service.Util;

import org.springframework.stereotype.Component;

@Component
public class MobileNumberFormatter {

    public static String formatNumberToInternational(String msisdn) {
        if (msisdn.matches("^263\\d+$")) {
            return msisdn;
        } else if (msisdn.matches("^00263\\d+$"))
            return msisdn.substring(2);
        else if (msisdn.matches("^0263\\d+$"))
            return msisdn.substring(1);
        else if (msisdn.matches("^0\\d+$"))
            return String.format("263%s", msisdn.substring(1));
        else
            return String.format("263%s", msisdn);
    }

}
