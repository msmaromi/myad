/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myad.model;

import myad.model.CommunicationChannel.Phone;
import myad.model.CommunicationChannel.PhysicalAddress;

/**
 *
 * @author FUJITSU
 */
public class Contact {
    private String role;
    private String name;
    private Phone phone;
    private PhysicalAddress address;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public PhysicalAddress getAddress() {
        return address;
    }

    public void setAddress(PhysicalAddress address) {
        this.address = address;
    }
    
    public Contact(String role, String name, Phone phone, PhysicalAddress address) {
        this.role = role;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
