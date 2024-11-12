package fr.aplose.aploseframework.tool.dolibarr;

import fr.aplose.aploseframework.model.Person;
import fr.aplose.aploseframework.model.UserAccount;

    public class DolibarrTool {
    
        public static String createDolibarrLogin(String username){
            return username.replace('@', '_');
        }

        public static String createDolibarrLogin(Person person){
            return createDolibarrLogin(person.getUserAccount().getUsername());
        }
    
        public static String createDolibarrLogin(UserAccount userAccount){
            return createDolibarrLogin(userAccount.getUsername());
        }
}
