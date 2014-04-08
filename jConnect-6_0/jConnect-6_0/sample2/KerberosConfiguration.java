// KerberosConfiguration.java  Sybase Product Support Group
//  Copyright (c) 2004, Sybase. Dublin, CA 94568.
//    All Rights Reserved
//
//  TITLE: KerberosConfiguration.java
//
//


package sample2;

import java.util.Hashtable;

import javax.security.auth.login.Configuration;
import javax.security.auth.login.AppConfigurationEntry;

/**
 * This class illustrates how to programatically set the values that would
 * otherwise be set in a JAAS login configuration file
 */

public class KerberosConfiguration extends Configuration
{

    static AppConfigurationEntry []ace;

    static
    {
        Hashtable map = new Hashtable();
       
        // By setting useTicketCache to true, we are telling Java to try and
        // locate a Keberos location in a well-defined location (for example,
        // in the /tmp/krb5cc_{userId} file on a Solaris machine or in the
        // in-memory cache on a Windows 2000 client machine which has 
        // authenticated to Active Directory Server).
        map.put("useTicketCache", "true");
        AppConfigurationEntry ac = new AppConfigurationEntry(
            "com.sun.security.auth.module.Krb5LoginModule",
            AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
            map);
        ace = new AppConfigurationEntry[1];
        ace[0] = ac;
    }

    public KerberosConfiguration()
    {
        super();
    }

    public AppConfigurationEntry[] getAppConfigurationEntry (String
        applicationName)
    {

        return ace;
    }

    public void refresh()
    {
    }

    static public void setConfiguration() 
    {
        Configuration.setConfiguration(new KerberosConfiguration());
    }
}
