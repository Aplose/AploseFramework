// package com.aplose.aploseframework;

// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.web.client.RestClient;

// import com.aplose.aploseframework.model.dictionnary.AbstractDictionnary;
// import com.aplose.aploseframework.repository.ConfigRepository;


// @SpringBootTest
// public class DolibarrServiceTest {

//     @Autowired
//     private ConfigRepository _configRepository;
//     @Autowired
//     private RestClient _restClient;
    
//     @Test
//     public void getDictionnaryReturn200StatusTest(){
//         String dolibarrUrl = this._configRepository.findById("dolibarr.api.url").orElse(null).getStringValue();
//         String dolibarrUserKey = this._configRepository.findById("dolibarr.api.userKey").orElse(null).getStringValue();
//         AbstractDictionnary[] civilities = this._restClient.get()
//             .uri(dolibarrUrl + "/dictionnary/civilities?DOLAPIKEY=" + dolibarrUserKey)
//             .accept(MediaType.APPLICATION_JSON)
//             .retrieve()
//             .body(AbstractDictionnary[].class);
//         assertTrue(civilities.length > 1);
//     }
// }
