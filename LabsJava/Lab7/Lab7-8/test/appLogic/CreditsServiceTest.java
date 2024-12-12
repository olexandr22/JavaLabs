package appLogic;

import baseClasses.Credit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreditsServiceTest {

    private CreditsService creditsService;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Mock
    private List<Credit> credits;

    @BeforeEach
    void setUp() {
        creditsService = new CreditsService();
        System.setOut(new PrintStream(outputStreamCaptor));

        credits = new ArrayList<>();

        Credit credit1 = mock(Credit.class);
        Credit credit2 = mock(Credit.class);

        credits.add(credit1);
        credits.add(credit2);

        when(credit1.getName()).thenReturn("Credit1");
        when(credit1.getBank()).thenReturn("Bank1");
        when(credit1.getPercents()).thenReturn(1.0);
        when(credit1.getTime()).thenReturn(12);
        when(credit1.getMoneyAmount()).thenReturn(10000);

        when(credit2.getName()).thenReturn("Credit2");
        when(credit2.getBank()).thenReturn("Bank2");
        when(credit2.getPercents()).thenReturn(2.0);
        when(credit2.getTime()).thenReturn(24);
        when(credit2.getMoneyAmount()).thenReturn(20000);

        creditsService.setCredits(credits);

    }

    @Test
    void showAllCreditsInvalid() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        List<Credit> creditsInvalid = new ArrayList<Credit>();
        creditsService.setCredits(creditsInvalid);
        // Виклик методу
        creditsService.showAllCredits();

        // Перевірка результату
        String output = outputStreamCaptor.toString().trim();
        assertEquals("No credits found", output);
    }

    @Test
    void compareCredits() {
        // Simulate user input to select the first and second credits (1 and 2)
        String input = "1\n2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Create a scanner to be used as input
        Scanner scanner = new Scanner(System.in);

        // Call the method to be tested
        creditsService.compareCredits(scanner);

        // Capture the output
        String output = outputStreamCaptor.toString();

        // Assertions to verify the output contains the expected comparison details
        assertTrue(output.contains("\t\tПорівняння кредитів Credit1 та Credit2"), "Missing comparison header");
        assertTrue(output.contains("Банк: \tBank1\t|Bank2"), "Missing bank comparison");
        assertTrue(output.contains("Проценти: \t1.0\t|2.0"), "Missing percent comparison");
        assertTrue(output.contains("Час: \t12\t|24"), "Missing time comparison");
        assertTrue(output.contains("Максимальна сума: \t10000\t|20000"), "Missing money amount comparison");
    }

    @Test
    void testSearchCreditsByParameters_SortByPercentsAscending() {
        // Simulate user input: option 1 (percents), asc 1 (ascending)
        String input = "1\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        creditsService.searchCreditsByParametrs(scanner);

        // Verify the order of the credits by percents
        assertEquals("Credit1", credits.get(0).getName());
        assertEquals("Credit2", credits.get(1).getName());
    }

    @Test
    void testSearchCreditsByParameters_SortByTimeDescending() {
        // Simulate user input: option 2 (time), asc 0 (descending)
        String input = "2\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        creditsService.searchCreditsByParametrs(scanner);

        // Verify the order of the credits by time
        assertEquals("Credit2", credits.get(0).getName());
        assertEquals("Credit1", credits.get(1).getName());
    }

    @Test
    void testSearchCreditsByParameters_SortByMoneyAmountAscending() {
        // Simulate user input: option 3 (money amount), asc 1 (ascending)
        String input = "3\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        creditsService.searchCreditsByParametrs(scanner);

        // Verify the order of the credits by money amount
        assertEquals("Credit1", credits.get(0).getName());
        assertEquals("Credit2", credits.get(1).getName());
    }

    @Test
    void testSearchCreditsByParameters_InvalidOption() {
        // Simulate user input: invalid option 4
        String input = "4\n1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        creditsService.searchCreditsByParametrs(scanner);

        // Verify the console output for invalid option
        assertTrue(outputStreamCaptor.toString().contains("Немає такого параметра"));
    }

    @Test
    void testReadCredits_SuccessfulRead() throws IOException, ClassNotFoundException {
        // Create a list of credits to serialize and write to a file
        List<Credit> creditsToWrite = new ArrayList<>();
        Credit credit1 = new Credit("Credit1", "Bank1", 1.0, 12, 10000);
        Credit credit2 = new Credit("Credit2", "Bank2", 2.0, 24, 20000);
        creditsToWrite.add(credit1);
        creditsToWrite.add(credit2);

        // Write the credits to a temporary file
        String filePath = "test_credits.dat";
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(creditsToWrite);
        }

        // Call the method to read the credits from the file
        creditsService.readCredits(filePath);

        // Verify the contents of the credits list
        List<Credit> readCredits = creditsService.getCredits();
        assertEquals(2, readCredits.size());
        assertEquals("Credit1", readCredits.get(0).getName());
        assertEquals("Credit2", readCredits.get(1).getName());

        // Clean up by deleting the test file
        new File(filePath).delete();
    }
}