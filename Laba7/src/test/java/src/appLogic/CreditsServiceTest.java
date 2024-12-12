package src.appLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import src.baseClasses.Credit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        when(credit1.getName()).thenReturn("Credit2");
        when(credit1.getBank()).thenReturn("Bank2");
        when(credit1.getPercents()).thenReturn(2.0);
        when(credit1.getTime()).thenReturn(24);
        when(credit1.getMoneyAmount()).thenReturn(20000);

    }

    @Test
    void showAllCredits() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Виклик методу
        creditsService.showAllCredits();

        // Перевірка результату
        String output = outputStreamCaptor.toString().trim();
        assertEquals("No credits found", output);
    }

    @Test
    void compareCredits() {
    }

    @Test
    void searchCreditsByParametrs() {
    }

    @Test
    void sortByComparator() {
    }

    @Test
    void readCredits() {
    }
}