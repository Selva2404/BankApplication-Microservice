package org.bankapp.Controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bankapp.DTO.AccountDTO;
import org.bankapp.DTO.CustomerDTO;
import org.bankapp.service.iAccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class AccountControllerDiffblueTest {
    @InjectMocks
    private AccountController accountController;

    @Mock
    private iAccountService iAccountService;

    /**
     * Test {@link AccountController#createAccount(CustomerDTO)}.
     *
     * <p>Method under test: {@link AccountController#createAccount(CustomerDTO)}
     */
    @Test
    @DisplayName("Test createAccount(CustomerDTO)")
    void testCreateAccount() throws Exception {
        // Arrange
        doNothing().when(iAccountService).CreateAccount(Mockito.<CustomerDTO>any());
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(1234567890L);
        accountDTO.setAccountType("3");
        accountDTO.setBranchAddress("42 Main St");
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAccountDTO(accountDTO);
        customerDTO.setCustomerName("Customer Name");
        customerDTO.setEmail("jane.doe@example.org");
        customerDTO.setMobileNumber("42");
        String content = new ObjectMapper().writeValueAsString(customerDTO);
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/api/Create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string(
                                        "{\"responseCode\":\"201\",\"responseMessage\":\"Account Created successfully\"}"));
    }

    /**
     * Test {@link AccountController#fetchAccount(String)}.
     *
     * <p>Method under test: {@link AccountController#fetchAccount(String)}
     */
    @Test
    @DisplayName("Test fetchAccount(String)")
    void testFetchAccount() throws Exception {
        // Arrange
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(1234567890L);
        accountDTO.setAccountType("3");
        accountDTO.setBranchAddress("42 Main St");
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAccountDTO(accountDTO);
        customerDTO.setCustomerName("Customer Name");
        customerDTO.setEmail("jane.doe@example.org");
        customerDTO.setMobileNumber("42");
        when(iAccountService.fetchAccount(Mockito.<String>any())).thenReturn(customerDTO);
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/fetchAccount").param("mobileNumber", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string(
                                        "{\"customerName\":\"Customer Name\",\"email\":\"jane.doe@example.org\",\"mobileNumber\":\"42\",\"accountDTO\":{"
                                                + "\"accountNumber\":1234567890,\"accountType\":\"3\",\"branchAddress\":\"42 Main St\"}}"));
    }

    /**
     * Test {@link AccountController#updateAccount(CustomerDTO)}.
     *
     * <ul>
     *   <li>Then status {@link StatusResultMatchers#isInternalServerError()}.
     * </ul>
     *
     * <p>Method under test: {@link AccountController#updateAccount(CustomerDTO)}
     */
    @Test
    @DisplayName("Test updateAccount(CustomerDTO); then status isInternalServerError()")
    void testUpdateAccount_thenStatusIsInternalServerError() throws Exception {
        // Arrange
        when(iAccountService.updateAccountDetails(Mockito.<CustomerDTO>any())).thenReturn(false);

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(1234567890L);
        accountDTO.setAccountType("3");
        accountDTO.setBranchAddress("42 Main St");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAccountDTO(accountDTO);
        customerDTO.setCustomerName("Customer Name");
        customerDTO.setEmail("jane.doe@example.org");
        customerDTO.setMobileNumber("42");
        String content = new ObjectMapper().writeValueAsString(customerDTO);
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.put("/api/updateAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string(
                                        "{\"responseCode\":\"500\",\"responseMessage\":\"internal server error\"}"));
    }

    /**
     * Test {@link AccountController#updateAccount(CustomerDTO)}.
     *
     * <ul>
     *   <li>Then status {@link StatusResultMatchers#isOk()}.
     * </ul>
     *
     * <p>Method under test: {@link AccountController#updateAccount(CustomerDTO)}
     */
    @Test
    @DisplayName("Test updateAccount(CustomerDTO); then status isOk()")
    void testUpdateAccount_thenStatusIsOk() throws Exception {
        // Arrange
        when(iAccountService.updateAccountDetails(Mockito.<CustomerDTO>any())).thenReturn(true);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountNumber(1234567890L);
        accountDTO.setAccountType("3");
        accountDTO.setBranchAddress("42 Main St");
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAccountDTO(accountDTO);
        customerDTO.setCustomerName("Customer Name");
        customerDTO.setEmail("jane.doe@example.org");
        customerDTO.setMobileNumber("42");
        String content = new ObjectMapper().writeValueAsString(customerDTO);
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.put("/api/updateAccount")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string(
                                        "{\"responseCode\":\"200\",\"responseMessage\":\"account details updated successfully\"}"));
    }

    /**
     * Test {@link AccountController#removeAccount(String)}.
     *
     * <p>Method under test: {@link AccountController#removeAccount(String)}
     */
    @Test
    @DisplayName("Test removeAccount(String)")
    void testRemoveAccount() throws Exception {
        // Arrange
        doNothing().when(iAccountService).removeAccount(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.delete("/api/RemoveAccount").param("mobileNumber", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content()
                                .string(
                                        "{\"responseCode\":\"200\",\"responseMessage\":\"Account Created successfully\"}"));
    }

    /**
     * Test {@link AccountController#getDBDetails()}.
     *
     * <p>Method under test: {@link AccountController#getDBDetails()}
     */
    @Test
    @DisplayName("Test getDBDetails()")
    void testGetDBDetails() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/config-info");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(accountController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
