import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.incognia.login.HomeActivity;

import java.util.Locale;

public class Address {
    private String COUNTRY_NAME;
    private String COUNTRY_CODE;
    private String ADMIN_AREA;
    private String SUB_ADMIN_AREA;
    private String LOCALITY;
    private String SUB_LOCALITY;
    private String THOROUGHFARE;
    private String SUB_THOROUGHFARE;
    private String POSTAL_CODE;
    private String LOCALE;


    public Address(Locale Locale) {
        this.COUNTRY_NAME = COUNTRY_NAME;
        this.COUNTRY_CODE = COUNTRY_CODE;
        this.ADMIN_AREA = ADMIN_AREA;
        this.SUB_ADMIN_AREA = SUB_ADMIN_AREA;
        this.LOCALITY = LOCALITY;
        this.SUB_LOCALITY = SUB_LOCALITY;
        this.THOROUGHFARE = THOROUGHFARE;
        this.SUB_THOROUGHFARE = SUB_THOROUGHFARE;
        this.POSTAL_CODE = POSTAL_CODE;
    }

    public void setCountryName(String COUNTRY_NAME) {
        this.COUNTRY_NAME = COUNTRY_NAME;
    }

    public void setCountryCode(String COUNTRY_CODE) {
        this.COUNTRY_CODE = COUNTRY_CODE;
    }

    public void setAdminArea(String ADMIN_AREA) {
        this.ADMIN_AREA = ADMIN_AREA;
    }

    public void setSubAdminArea(String SUB_ADMIN_AREA) {
        this.SUB_ADMIN_AREA = SUB_ADMIN_AREA;
    }

    public void setLocality(String LOCALITY) {
        this.LOCALITY = LOCALITY;
    }

    public void setSubLocality(String SUB_LOCALITY) {
        this.SUB_LOCALITY = SUB_LOCALITY;
    }

    public void setThoroughfare(String THOROUGHFARE) {
        this.THOROUGHFARE = THOROUGHFARE;
    }

    public void setSubThoroughfare(String SUB_THOROUGHFARE) {
        this.SUB_THOROUGHFARE = SUB_THOROUGHFARE;
    }

    public void setPostalCode(String POSTAL_CODE) {
        this.POSTAL_CODE = POSTAL_CODE;
    }
}
