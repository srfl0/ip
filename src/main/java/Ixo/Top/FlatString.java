package Ixo.Top;

import java.util.Scanner;

/**
 * Contains all fixed values to be used throughout chatbot
 * application
 */

public interface FlatString {
    String SEPARATOR = "____________________________________________________";
    int EXPECTED_TASK_LENGTH = 2;
    int EXPECTED_DEADLINE_LENGTH = 2;
    int EXPECTED_EVENT_LENGTH = 3;
    int EXPECTED_BY_FIELD_COUNT = 1;
    int EXPECTED_FROM_FIELD_COUNT = 1;
    int EXPECTED_TO_FIELD_COUNT = 1;
    Scanner inpScan = new Scanner(System.in);
}
