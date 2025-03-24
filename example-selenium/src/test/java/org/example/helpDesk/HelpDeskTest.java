package org.example.helpDesk;

import org.example.core.BaseSeleniumTest;
import org.junit.jupiter.api.Test;

import static org.example.config.ConfigProvider.ADMIN_LOGIN;
import static org.example.config.ConfigProvider.ADMIN_PASSWORD;
import static org.example.helpers.StringModifier.generateUniqueString;
import static org.example.helpers.TestValues.BODY_VALUE;
import static org.example.helpers.TestValues.EMAIL_VALUE;
import static org.example.helpers.TestValues.TITLE_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelpDeskTest extends BaseSeleniumTest {

    @Test
    public void checkAddNewTicket() {
        String uniqueTitle = generateUniqueString(TITLE_VALUE);
        TicketPage ticketPage = new MainPage()
                .createTicket(uniqueTitle, BODY_VALUE, EMAIL_VALUE)
                .openLoginPage()
                .login(ADMIN_LOGIN, ADMIN_PASSWORD)
                .searchTicket(uniqueTitle);
        assertTrue(ticketPage.getTitle().getText().contains(uniqueTitle));
        assertEquals(BODY_VALUE, ticketPage.getBody().getText());
        assertEquals(EMAIL_VALUE, ticketPage.getEmail().getText());
    }
}
