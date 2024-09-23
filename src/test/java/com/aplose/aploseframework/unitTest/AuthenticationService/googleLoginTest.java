package com.aplose.aploseframework.unitTest.AuthenticationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import com.aplose.aploseframework.dto.AuthResponseDTO;
import com.aplose.aploseframework.enums.AuthenticationTypeEnum;
import com.aplose.aploseframework.model.Permission;
import com.aplose.aploseframework.model.Role;
import com.aplose.aploseframework.model.RoleEnum;
import com.aplose.aploseframework.model.UserAccount;
import com.aplose.aploseframework.model.security.Token;
import com.aplose.aploseframework.service.AuthenticationService;
import com.aplose.aploseframework.service.ConfigService;
import com.aplose.aploseframework.service.GoogleIdentityService;
import com.aplose.aploseframework.service.UserAccountService;
import com.aplose.aploseframework.tool.jwt.JwtTokenTool;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.auth.oauth2.TokenVerifier.VerificationException;

public class googleLoginTest {

    @Mock
    private ConfigService configServiceMock;
    @Mock
    private UserAccountService userAccountServiceMock;
    @Mock
    private PasswordEncoder passwordEncoderMock;
    @Mock
    private GoogleIdentityService googleServiceMock;
    @Mock
    private JsonWebSignature.Payload payloadMock;
    @Mock
    private UserAccount userAccountMock;
    @Mock
    private Role roleMock;
    @Mock
    private Permission permissionMock;
    @Mock
    private AuthResponseDTO authResponseDTOMock;
    @Mock
    private Token tokenMock;
    @Mock
    private AuthenticationManager authenticationManagerMock;
    @Mock
    private JwtTokenTool jwtTokenToolMock;

    @InjectMocks
    private AuthenticationService _authenticationService;

    @BeforeEach
    void setup() throws VerificationException{
        MockitoAnnotations.openMocks(this);

        this._authenticationService = spy(this._authenticationService);

        
        when(permissionMock.getId()).thenReturn(22L);
        when(permissionMock.getAuthority()).thenReturn("permissionTest");
        
        when(roleMock.getId()).thenReturn(80L);
        when(roleMock.getAuthority()).thenReturn(RoleEnum.ROLE_SUPER_ADMIN.toString());
        when(roleMock.getPermissions()).thenReturn(List.of(permissionMock));
        
        when(userAccountMock.getId()).thenReturn(7L);
        when(userAccountMock.getUsername()).thenReturn("test@test.fr");
        when(userAccountMock.getPassword()).thenReturn("defaultEncodedPassword");
        when(userAccountMock.getCompanyName()).thenReturn("companyNameTest");
        when(userAccountMock.getDolibarrContactId()).thenReturn(18);
        when(userAccountMock.getDolibarrThirdPartyId()).thenReturn(4);
        when(userAccountMock.getDolibarrUserId()).thenReturn(61);
        when(userAccountMock.getRoles()).thenReturn(List.of(roleMock));
        when(userAccountMock.getId()).thenReturn(7L);
        
        when(tokenMock.getAccessToken()).thenReturn("access_token");
        when(tokenMock.getType()).thenReturn(AuthenticationTypeEnum.INTERNAL);
        when(tokenMock.getExpireAt()).thenReturn(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10));
        
        when(jwtTokenToolMock.generateToken(userAccountMock)).thenReturn("access_token");
    }
    
    private String googleTokenMock = "default_google_token";
    private String uniqueGoogleCustomerIdMock = "unique_google_customer_id";
    
    
    
    
    
    @Test
    void successfully_googleLogin_return_token_of_type_AuthenticationTypeEnum_GOOGLE_Test() throws VerificationException{
    // ARANGE
        // le token Google est correct, un Payload est retourné
        when(this.payloadMock.get("email")).thenReturn("test@test.fr");
        when(this.payloadMock.getSubject()).thenReturn(uniqueGoogleCustomerIdMock);
        when(this.googleServiceMock.getPayload(googleTokenMock)).thenReturn(payloadMock);
        // le UserAccount existe, il est retourné 
        when(this.userAccountServiceMock.loadUserByUsername((String)payloadMock.get("email"))).thenReturn(userAccountMock);

    // ACT
        AuthResponseDTO response = this._authenticationService.googleLogin(googleTokenMock);

    // ASSERT
        // le token est de type AuthenticationTypeEnum.GOOGLE
        assertEquals(AuthenticationTypeEnum.GOOGLE, response.getToken().getType());
        // le token n'est pas d'un autre type que AuthenticationTypeEnum.GOOGLE
        assertNotEquals(AuthenticationTypeEnum.INTERNAL, response.getToken().getType());
    }


    // @Test
    // void VerificationException_is_throw_when_google_token_is_correct_but_UserAccount_not_exist()throws VerificationException{
    // // ARANGE
    //     // le token Google est correct, un Payload est retourné
    //     when(this.googleServiceMock.getPayload(googleTokenMock)).thenReturn(new Payload());
    //     // le UserAccount n'existe pas, il n'est pas retourné 
    //     when(this.userAccountServiceMock.loadUserByUsername(userAccountMock.getUsername())).thenThrow(VerificationException.class);

    // // ACT
    //     Executable test = ()->{this._authenticationService.googleLogin(googleTokenMock);};

    // // ASSERT
    //     // si le compte n'existe pas lors du login, une exception est levée
    //     assertThrowsExactly(VerificationException.class, test);
    // }
    // si les identifiants sont corrects, un AuthResponseDto est retourné
}
