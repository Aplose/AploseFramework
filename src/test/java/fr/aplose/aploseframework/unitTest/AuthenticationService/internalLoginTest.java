package fr.aplose.aploseframework.unitTest.AuthenticationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.aplose.aploseframework.dto.AuthResponseDTO;
import fr.aplose.aploseframework.enums.AuthenticationTypeEnum;
import fr.aplose.aploseframework.model.Permission;
import fr.aplose.aploseframework.model.Role;
import fr.aplose.aploseframework.model.RoleEnum;
import fr.aplose.aploseframework.model.UserAccount;
import fr.aplose.aploseframework.service.AuthenticationService;
import fr.aplose.aploseframework.service.UserAccountService;
import fr.aplose.aploseframework.tool.jwt.JwtTokenTool;

public class internalLoginTest {

    @Mock
    private UserAccountService userAccountService;
    @Mock
    private JwtTokenTool _jwtTokenTool;
    @Mock
    private AuthenticationManager _authenticationManager;
    @Mock
    private PasswordEncoder _passwordEncoder;


    @InjectMocks
    private AuthenticationService _authenticationService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        when(this._jwtTokenTool.generateToken(defaultUserAccount)).thenReturn(defaultCorrectAccessToken);
        when(this.userAccountService.loadUserByUsername(defaultUsername)).thenReturn(defaultUserAccount);
        when(this.userAccountService.loadUserByUsername(wrongUsername)).thenThrow(UsernameNotFoundException.class);
        when(this._passwordEncoder.encode(defaultNonEncodedPassword)).thenReturn(defaultEncodedPassword);
        when(this._passwordEncoder.encode(wrongPassword)).thenReturn(wrongPassword);
        when(this._passwordEncoder.matches(defaultNonEncodedPassword, defaultEncodedPassword)).thenReturn(true);
    }

    private UserAccount defaultUserAccount = new UserAccount();

    private String defaultUsername = "test@test.fr";

    private String defaultNonEncodedPassword = "defaultNonEncodedPassword1234!!!!";
    private String defaultEncodedPassword = "defaultEncodedPassword";

    private String defaultCompanyname = "companyNameTest";
    private Integer defaultDolibarrContactId = 45;
    private Integer defaultDolibarrUserId = 12;
    private Integer defaultDolibarrThirPartyId = 107;

    private Role defaultRole = new Role();
    private Long defaultRoleId = 9L;
    private String defaultRoleAuthority = RoleEnum.ROLE_SUPER_ADMIN.toString();

    private Permission defaultPermission = new Permission("permissionTest");

    private String defaultCorrectAccessToken = "authentication_access_token";


    private String wrongUsername = "wrong@wrong.fr";
    private String wrongPassword = "wrongPassword";




    internalLoginTest(){
        defaultRole.setAuthority(defaultRoleAuthority);
        defaultRole.setId(defaultRoleId);
        defaultRole.setPermissions(List.of(defaultPermission));
        defaultUserAccount.setId(7L);
        defaultUserAccount.setUsername(defaultUsername);
        defaultUserAccount.setPassword(defaultEncodedPassword);
        defaultUserAccount.setCompanyName(defaultCompanyname);
        defaultUserAccount.setDolibarrContactId(defaultDolibarrContactId);
        defaultUserAccount.setDolibarrThirdPartyId(defaultDolibarrThirPartyId);
        defaultUserAccount.setDolibarrUserId(defaultDolibarrUserId);
        defaultUserAccount.setEnabled(null);
        defaultUserAccount.setRoles(List.of(defaultRole));
    }

    
    @Test
    void login_with_wrong_username_return_null_response_Test(){
        // ACT
        // connexion avec de mauvais identifiants
        AuthResponseDTO response = this._authenticationService.internalLogin(wrongUsername, defaultNonEncodedPassword);
        // ASSERT
        // la méthode retourne null lorsque les identifiants ne sont pas correctes
        assertNull(response);
    }
    
    @Test
    void login_with_wrong_password_return_null_response_Test(){
        // ACT
        // connexion avec de mauvais identifiants
        AuthResponseDTO response = this._authenticationService.internalLogin(defaultUsername, wrongPassword);
        // ASSERT
        // la méthode retourne null lorsque les identifiants ne sont pas correctes
        assertNull(response);
    }

    @Test
    void login_with_correct_credentials_return_non_null_userDetail_and_non_null_accessToken_Test(){
        // ACT
        // connexion avec des identifiants correctes
        AuthResponseDTO response = this._authenticationService.internalLogin(defaultUsername, defaultNonEncodedPassword);
        // ASSERT
        // la méthode dois retourné un userAccount quand les identifiants sont correctes
        assertNotNull(response.getUserAccount());
        // la méthode dois retourné un token quand les identifiants sont correctes
        assertNotNull(response.getToken().getAccessToken());
    }


    @Test
    void login_with_correct_credentials_return_correct_userDetail_and_correct_accessToken_Test(){
        // ACT
        // connexion avec des identifiants correctes
        AuthResponseDTO response = this._authenticationService.internalLogin(defaultUsername, defaultNonEncodedPassword);
        // ASSERT
        // le dto retourné dois contenir le UserAccount attendu
        assertEquals(defaultUserAccount, response.getUserAccount());
        assertEquals(defaultUserAccount.getId(), response.getUserAccount().getId());
        // la méthode dois correctement créer et retourner le token
        assertEquals(defaultCorrectAccessToken, response.getToken().getAccessToken());
    }


    @Test
    void internal_login_return_token_of_type_AuthenticationTypeEnum_INTERNAL_Test(){
        // ACT
        // connexion avec des identifiants correctes
        AuthResponseDTO response = this._authenticationService.internalLogin(defaultUsername, defaultNonEncodedPassword);
        // ASSERT
        // le token dois avoir de type INTERNAL
        assertEquals(AuthenticationTypeEnum.INTERNAL, response.getToken().getType());
        // le token ne dois pas avoir un autre type que INTERNAL
        assertNotEquals(AuthenticationTypeEnum.DOLIBARR, response.getToken().getType());
    }

    @Test
    void userAccount_is_correctly_instancied_when_he_is_loged(){
        // ACT
        UserAccount resultUserAccount = this._authenticationService.internalLogin(defaultUsername, defaultNonEncodedPassword).getUserAccount();
        
        // ASSERT
        // verification que chaque propriété est assignée correctement
        assertEquals(defaultUserAccount.getId(), resultUserAccount.getId());
        assertEquals(defaultUserAccount.getCompanyName(), resultUserAccount.getCompanyName());
        assertEquals(defaultUserAccount.getCreationDate(), resultUserAccount.getCreationDate());
        assertEquals(defaultUserAccount.getDolibarrContactId(), resultUserAccount.getDolibarrContactId());
        assertEquals(defaultUserAccount.getDolibarrThirdPartyId(), resultUserAccount.getDolibarrThirdPartyId());
        assertEquals(defaultUserAccount.getDolibarrUserId(), resultUserAccount.getDolibarrUserId());
        assertEquals(defaultUserAccount.getEnabled(), resultUserAccount.getEnabled());

        // Vérification des rôles
        assertEquals(defaultUserAccount.getRoles().size(), resultUserAccount.getRoles().size());
        for (int i = 0; i < defaultUserAccount.getRoles().size(); i++) {
            Role expectedRole = defaultUserAccount.getRoles().get(i);
            Role resultRole = resultUserAccount.getRoles().get(i);
            assertEquals(expectedRole.getId(), resultRole.getId());
            assertEquals(expectedRole.getAuthority(), resultRole.getAuthority());

            // Vérification des permissions associées au rôle
            assertEquals(expectedRole.getPermissions().size(), resultRole.getPermissions().size());
            for (int j = 0; j < expectedRole.getPermissions().size(); j++) {
                Permission expectedPermission = expectedRole.getPermissions().get(j);
                Permission resultPermission = resultRole.getPermissions().get(j);
                assertEquals(expectedPermission.getAuthority(), resultPermission.getAuthority());
            }
        }

        // Vérification que le mot de passe est bien assigné
        assertEquals(defaultUserAccount.getPassword(), resultUserAccount.getPassword());
        // Vérification que le mot de passe retourné n'est pas le mot de passe non encodé
        assertNotEquals(defaultNonEncodedPassword, resultUserAccount.getPassword());
        // vérification que le mot de passe est celui encodé
        assertEquals(defaultEncodedPassword, resultUserAccount.getPassword());
    }

}
