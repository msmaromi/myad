/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myad.model.CommunicationChannel;

/**
 *
 * @author FUJITSU
 */
public class PhysicalAddress {
    private String street;
    private String zipPostalCode;
    private String city;
    private String stateProvince;
    private String countryName;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public void setZipPostalCode(String zipPostalCode) {
        this.zipPostalCode = zipPostalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }   
}