package appLogic;

import baseClasses.Credit;
import baseClasses.User;
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

class UserServiceTest {
    private UserService userService;
    private Scanner mockScanner;
    private CreditsService mockCreditService;
    private User mockUser;

    @Mock
    private List<Credit> credits;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        mockScanner = mock(Scanner.class);
        mockCreditService = mock(CreditsService.class);
        mockUser = new User();
        mockUser.setName("Іван Іванов");
        mockUser.setPassword("пароль123");
        mockUser.setCreditLine(10000);
        mockUser.setCredits(new ArrayList<>());

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

        mockCreditService.setCredits(credits);
    }

    @Test
    public void testRegisterUser() throws IOException {
        String filePath = "test_user_data.ser";

        String input = "Іван Іванов\nпароль123\n10000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Create a scanner to be used as input
        Scanner scanner = new Scanner(System.in);

        userService.registerUser(scanner, filePath);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            User registeredUser = (User) ois.readObject();

            assertEquals("Іван Іванов", registeredUser.getName());
            assertEquals("пароль123", registeredUser.getPassword());
            assertEquals(10000, registeredUser.getCreditLine());
        } catch (IOException | ClassNotFoundException e) {
            fail("Exception during user data read: " + e.getMessage());
        }
    }

    @Test
    public void testLoginUserSuccessful() throws IOException, ClassNotFoundException {
        String filePath = "test_user_data.ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(mockUser);
        }

        when(mockScanner.nextLine()).thenReturn("Іван Іванов", "пароль123");
        userService.loginUser(mockScanner, filePath);
    }

    @Test
    public void testLoginUserFailure() {
        String filePath = "test_user_data.ser";
        when(mockScanner.nextLine()).thenReturn("Wrong Name", "wrongpassword");

        assertDoesNotThrow(() -> userService.loginUser(mockScanner, filePath));
    }

    @Test
    public void testTakeCredit() {
        when(mockCreditService.getCredits()).thenReturn(credits);
        when(mockScanner.nextInt()).thenReturn(1);

        userService.takeCredit(mockScanner, mockCreditService);
        assertEquals(1, userService.getUser().getCredits().size());
    }

    @Test
    public void testCloseCredit() {

        userService.getUser().setCredits(credits);

        when(mockScanner.nextInt()).thenReturn(1);

        userService.closeCredit(mockScanner, mockCreditService);
        assertEquals(1, userService.getUser().getCredits().size());
    }

    @Test
    public void testCloseCreditWithEmptyList() {
        userService.getUser().setCredits(new ArrayList<>());

        assertDoesNotThrow(() -> userService.closeCredit(mockScanner, mockCreditService));
    }

    @Test
    public void testUpdateCreditLine() {
        when(mockScanner.nextInt()).thenReturn(7000);

        userService.updateCreditLine(mockScanner);
        assertEquals(7000, userService.getUser().getCreditLine());
    }

    @Test
    public void testCloseCreditInvalidIndex() {
        userService.getUser().setCredits(credits);

        when(mockScanner.nextInt()).thenReturn(3);

        assertEquals(2, userService.getUser().getCredits().size());
    }

}
