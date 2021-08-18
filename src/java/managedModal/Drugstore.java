package managedModal;

import java.io.Serializable;

public
        class Drugstore
        implements Serializable {

    private
            Integer daysoutofstock;

    private static final
            long serialVersionUID = 1L;
    private
            Integer drug_id;
    private
            String status;
    private
            String item_Name;
    private
            String dosage;
    private
            String category;
    private
            String unit_Type;
    private
            Integer buy_In_Unit;
    private
            Integer issue_Unit;
    private
            String issue_date;
    private
            String main_Supplier;
    private
            String sec_Supplier;
    private
            String catalog_code;
    private
            Integer price_Main_Supplier;
    private
            Integer cost_per_issue_unit;
    private
            Integer item_current_units;
    private
            Integer supplier_id;
    private
            String supplier_name;
    private
            String supplier_type;
    private
            Integer units_issued;
    private
            Integer units_received;
    private
            Integer department_id;
    private
            String department_name;
    private
            String initials;
    private String storage_conditions;
    private
            String contact;
    private
            String email;
    private
            String prescribed_item;
    private
            String prescribe_unit;
    private
            Integer prescribe_units_per_issue;
    private
            String ecn,code_no;

    public
            String getEcn() {
        return ecn;
    }

    public
            void setEcn(String ecn) {
        this.ecn = ecn;
    }

    public
            String getPrescribed_item() {
        return prescribed_item;
    }

    public
            void setPrescribed_item(String prescribed_item) {
        this.prescribed_item = prescribed_item;
    }

    public
            String getPrescribe_unit() {
        return prescribe_unit;
    }

    public
    String getStorage_conditions() {
        return storage_conditions;
    }

    public
    void setStorage_conditions(String storage_conditions) {
        this.storage_conditions = storage_conditions;
    }

    public
            void setPrescribe_unit(String prescribe_unit) {
        this.prescribe_unit = prescribe_unit;
    }

    public
    String getCode_no() {
        return code_no;
    }

    public
    void setCode_no(String code_no) {
        this.code_no = code_no;
    }

    public
            Integer getPrescribe_units_per_issue() {
        return prescribe_units_per_issue;
    }

    public
            void setPrescribe_units_per_issue(Integer prescribe_units_per_issue) {
        this.prescribe_units_per_issue = prescribe_units_per_issue;
    }

    public
            Integer getDaysoutofstock() {
        return daysoutofstock;
    }

    public
            void setDaysoutofstock(Integer daysoutofstock) {
        this.daysoutofstock = daysoutofstock;
    }

    public
            String getIssue_date() {
        return issue_date;
    }

    public
            void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public
            Integer getDrug_id() {
        return this.drug_id;
    }

    public
            String getInitials() {
        return initials;
    }

    public
            void setInitials(String initials) {
        this.initials = initials;
    }

    public
            String getContact() {
        return contact;
    }

    public
            void setContact(String contact) {
        this.contact = contact;
    }

    public
            String getEmail() {
        return email;
    }

    public
            void setEmail(String email) {
        this.email = email;
    }

    public
            void setDrug_id(Integer drug_id) {
        this.drug_id = drug_id;
    }

    public
            String getStatus() {
        return this.status;
    }

    public
            void setStatus(String status) {
        this.status = status;
    }

    public
            String getItem_Name() {
        return this.item_Name;
    }

    public
            void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }

    public
            String getDosage() {
        return this.dosage;
    }

    public
            void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public
            String getCategory() {
        return this.category;
    }

    public
            void setCategory(String category) {
        this.category = category;
    }

    public
            String getUnit_Type() {
        return this.unit_Type;
    }

    public
            void setUnit_Type(String unit_Type) {
        this.unit_Type = unit_Type;
    }

    public
            Integer getBuy_In_Unit() {
        return this.buy_In_Unit;
    }

    public
            void setBuy_In_Unit(Integer buy_In_Unit) {
        this.buy_In_Unit = buy_In_Unit;
    }

    public
            Integer getIssue_Unit() {
        return this.issue_Unit;
    }

    public
            void setIssue_Unit(Integer issue_Unit) {
        this.issue_Unit = issue_Unit;
    }

    public
            String getMain_Supplier() {
        return this.main_Supplier;
    }

    public
            void setMain_Supplier(String main_Supplier) {
        this.main_Supplier = main_Supplier;
    }

    public
            String getSec_Supplier() {
        return this.sec_Supplier;
    }

    public
            void setSec_Supplier(String sec_Supplier) {
        this.sec_Supplier = sec_Supplier;
    }

    public
            String getCatalog_code() {
        return this.catalog_code;
    }

    public
            void setCatalog_code(String catalog_code) {
        this.catalog_code = catalog_code;
    }

    public
            Integer getPrice_Main_Supplier() {
        return this.price_Main_Supplier;
    }

    public
            void setPrice_Main_Supplier(Integer price_Main_Supplier) {
        this.price_Main_Supplier = price_Main_Supplier;
    }

    public
            Integer getCost_per_issue_unit() {
        return this.cost_per_issue_unit;
    }

    public
            void setCost_per_issue_unit(Integer cost_per_issue_unit) {
        this.cost_per_issue_unit = cost_per_issue_unit;
    }

    public
            Integer getItem_current_units() {
        return this.item_current_units;
    }

    public
            void setItem_current_units(Integer item_current_units) {
        this.item_current_units = item_current_units;
    }

    public
            Integer getSupplier_id() {
        return this.supplier_id;
    }

    public
            void setSupplier_id(Integer supplier_id) {
        this.supplier_id = supplier_id;
    }

    public
            String getSupplier_name() {
        return this.supplier_name;
    }

    public
            void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public
            String getSupplier_type() {
        return this.supplier_type;
    }

    public
            void setSupplier_type(String supplier_type) {
        this.supplier_type = supplier_type;
    }

    public
            Integer getUnits_issued() {
        return this.units_issued;
    }

    public
            void setUnits_issued(Integer units_issued) {
        this.units_issued = units_issued;
    }

    public
            Integer getUnits_received() {
        return this.units_received;
    }

    public
            void setUnits_received(Integer units_received) {
        this.units_received = units_received;
    }

    public
            Integer getDepartment_id() {
        return this.department_id;
    }

    public
            void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public
            String getDepartment_name() {
        return this.department_name;
    }

    public
            void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public
            Drugstore(Integer drug_id, String status, String item_Name, String dosage, String category, String unit_Type, Integer buy_In_Unit, Integer issue_Unit, String main_Supplier, String sec_Supplier, String catalog_code, Integer price_Main_Supplier, Integer cost_per_issue_unit, String prescribed_item, String prescribe_unit, Integer prescribe_units_per_issue, String ecn,String storage_conditions,String code_no) {
        this.drug_id = drug_id;
        this.status = status;
        this.item_Name = item_Name;
        this.dosage = dosage;
        this.category = category;
        this.unit_Type = unit_Type;
        this.buy_In_Unit = buy_In_Unit;
        this.issue_Unit = issue_Unit;
        this.main_Supplier = main_Supplier;
        this.sec_Supplier = sec_Supplier;
        this.catalog_code = catalog_code;
        this.price_Main_Supplier = price_Main_Supplier;
        this.cost_per_issue_unit = cost_per_issue_unit;
        this.prescribed_item = prescribed_item;
        this.prescribe_unit = prescribe_unit;
        this.prescribe_units_per_issue = prescribe_units_per_issue;
        this.ecn = ecn;
        this.storage_conditions=storage_conditions;
        this.code_no=code_no;
    }

    public
            Drugstore(Integer drug_id, String item_Name) {
        this.drug_id = drug_id;
        this.item_Name = item_Name;
    }

    public
            Drugstore(Integer drug_id, String item_Name, Integer item_current_units) {
        this.drug_id = drug_id;
        this.item_Name = item_Name;
        this.item_current_units = item_current_units;
    }

    public
            Drugstore(String item_Name, String supplier_name, Integer units_received) {
        this.item_Name = item_Name;
        this.supplier_name = supplier_name;
        this.units_received = units_received;

    }

    public
            Drugstore(String item_Name, Integer units_issued, String department_name) {
        this.item_Name = item_Name;
        this.units_issued = units_issued;
        this.department_name = department_name;
    }

    public
            Drugstore(String item_Name, Integer units_issued, String department_name, String issue_date) {
        this.item_Name = item_Name;
        this.units_issued = units_issued;
        this.department_name = department_name;
        this.issue_date = issue_date;
    }

    public
            Drugstore(Integer supplier_id, String supplier_name, String supplier_type, String initials, String contact, String email) {
        this.supplier_id = supplier_id;
        this.supplier_name = supplier_name;
        this.supplier_type = supplier_type;
        this.initials = initials;
        this.contact = contact;
        this.email = email;
    }

    public
            Drugstore(Integer daysoutofstock, Integer drug_id, String item_Name) {
        this.daysoutofstock = daysoutofstock;
        this.drug_id = drug_id;
        this.item_Name = item_Name;
    }
}
