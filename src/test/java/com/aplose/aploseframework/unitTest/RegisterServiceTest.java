// package com.aplose.aploseframework.unitTest;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import com.aplose.aploseframework.enums.AuthenticationTypeEnum;
// import com.aplose.aploseframework.model.Address;
// import com.aplose.aploseframework.model.Person;
// import com.aplose.aploseframework.model.UserAccount;
// import com.aplose.aploseframework.model.dictionnary.Civility;
// import com.aplose.aploseframework.model.dictionnary.Country;
// import com.aplose.aploseframework.service.DolibarrService;
// import com.aplose.aploseframework.service.UserAccountService;
// import com.aplose.aploseframework.service.PersonService;
// import com.aplose.aploseframework.service.RegisterService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import java.util.Locale;

// public class RegisterServiceTest {

//     @Mock
//     private PasswordEncoder passwordEncoder;

//     @Mock
//     private DolibarrService dolibarrService;

//     @Mock
//     private UserAccountService userAccountService;

//     @Mock
//     private PersonService personService;

//     @InjectMocks
//     private RegisterService registerService;

//     private Person person;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//         person = new Person();
        
//         Locale locale = Locale.FRANCE.getDefault();

//         UserAccount userAccount = new UserAccount();
//         userAccount.setPassword("plainpassword");

//         Civility civility = new Civility();
//         civility.setCode("M");
//         civility.setLabel("Monsieur");
//         civility.setRowid(1L);
        
//         Country country = new Country();
//         country.setCode("FR");
        
//         Address address = new Address();
//         address.setCountry(country);
        
//         userAccount.setLocale(locale);
//         person.setUserAccount(userAccount);
//         person.setAddress(address);
//         person.setCivility(civility);
//     }

//     @Test
//     void test(){
//         assertTrue(true);
//     }

//     @Test
//     void testRegisterInternalAccount() {
//         // Mocking the password encoder
//         when(passwordEncoder.encode("plainpassword")).thenReturn("encodedpassword");

//         // Mocking the saving of UserAccount
//         when(userAccountService.save(any(UserAccount.class))).thenAnswer(invocation -> invocation.getArgument(0));

//         // Mocking the saving of Person
//         when(personService.save(any(Person.class))).thenAnswer(invocation -> invocation.getArgument(0));

//         // Call the method
//         Person result = registerService.register(AuthenticationTypeEnum.INTERNAL, person, false);

//         // Verify the password has been encoded
//         assertEquals("encodedpassword", result.getUserAccount().getPassword());

//         // Verify that locale has been set correctly
//         assertEquals(Locale.FRANCE, result.getUserAccount().getLocale());

//         // Verify that the UserAccount and Person are saved
//         verify(userAccountService, times(1)).save(any(UserAccount.class));
//         verify(personService, times(1)).save(any(Person.class));
//     }

//     @Test
//     void testRegisterGoogleAccount() {
//         // Same structure as above but testing for Google account
//         Person result = registerService.register(AuthenticationTypeEnum.GOOGLE, person, false);

//         // Verify that the account is activated for Google account
//         assertNull(result.getUserAccount().getActivationCode());

//         verify(userAccountService, times(1)).save(any(UserAccount.class));
//         verify(personService, times(1)).save(any(Person.class));
//     }
// }
