/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedModal;

import java.io.Serializable;

/**
 *
 * @author romugabi
 */
public class AccountsPaymentsView implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String track_id;
    private String operation;
    private String amount;
    private Integer number;

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }

    public String getOperation() {
        return operation;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public AccountsPaymentsView() {
    }
    
}
