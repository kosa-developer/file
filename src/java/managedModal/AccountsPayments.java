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
public class AccountsPayments implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Integer request_id;
    private String task_id;
    private String operation;
    private Integer amount,paid;
    private String reason;
    

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AccountsPayments() {
    }
    
    
}
