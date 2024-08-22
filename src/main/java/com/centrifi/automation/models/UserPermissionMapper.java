package com.centrifi.automation.models;

import java.util.HashMap;
import java.util.Map;

public class UserPermissionMapper {

    public static Map<String, String> mapping = new HashMap<>();

    static {
        mapping.put("CONFIRM REPORT SENT", "//*[@name='confirm_report_sent']");
        mapping.put("EDIT SENT RECOMMENDATION DRP","//*[@name='edit_sent_recommendation_drp']");
        mapping.put("UPLOAD FILE","//*[@name='upload_file']");
        mapping.put("EDIT SENT RECOMMENDATION RESPONSE","//*[@name='edit_sent_recommendation_response']");
        mapping.put("PUBLISH REPORTS","//*[@name='publish_reports']");
        mapping.put("VIEW PHARMACIST TASKS","//*[@name='view_pharmacist_tasks']");
        mapping.put("ARCHIVE SENT RECOMMENDATIONS","//*[@name='archive_sent_recommendations']");
        mapping.put("GENERATE REPORTS","//*[@name='generate_reports']");
        mapping.put("ARCHIVE REPORTS","//*[@name='archive_reports']");
        mapping.put("DOWNLOAD REPORTS","//*[@name='download_reports']");
        mapping.put("VIEW SUPPORT TASKS","//*[@name='view_support_tasks']");
        mapping.put("SEND FAX","//*[@name='send_fax']");
        mapping.put("DELETE REPORTS","//*[@name='delete_reports']");
        mapping.put("CAN EDIT PATIENT PLAN","//*[@name='can_edit_patient_plan']");

        mapping.put("PATIENT ASSIGN RECOMMENDATIONS-READ", "//*[@name='patient_assign_recommendations' and @value='r']/../..");
        mapping.put("PATIENT ASSIGN RECOMMENDATIONS-WRITE", "//*[@name='patient_assign_recommendations' and @value='w']/../..");

        mapping.put("PATIENT ADDRESS-READ", "//*[@name='patient_address' and @value='r']/../..");
        mapping.put("PATIENT ADDRESS-WRITE", "//*[@name='patient_address' and @value='w']/../..");

        mapping.put("PATIENT OTHERS-READ", "//*[@name='patient_others' and @value='r']/../..");
        mapping.put("PATIENT OTHERS-WRITE", "//*[@name='patient_others' and @value='w']/../..");

        mapping.put("PATIENT BIRTHDATE-READ", "//*[@name='patient_birthDate' and @value='r']/../..");
        mapping.put("PATIENT BIRTHDATE-WRITE", "//*[@name='patient_birthDate' and @value='w']/../..");

        mapping.put("PHARMACIST TASKS-READ", "//*[@name='pharmacist_tasks' and @value='r']/../..");
        mapping.put("PHARMACIST TASKS-WRITE", "//*[@name='pharmacist_tasks' and @value='w']/../..");

        mapping.put("PHARMACY NAME-READ", "//*[@name='pharmacy_name' and @value='r']/../..");
        mapping.put("PHARMACY NAME-WRITE", "//*[@name='pharmacy_name' and @value='w']/../..");

        mapping.put("PATIENT NAME-READ", "//*[@name='patient_name' and @value='r']/../..");
        mapping.put("PATIENT NAME-WRITE", "//*[@name='patient_name' and @value='w']/../..");

        mapping.put("PRACTITIONER FIRST NAME-READ", "//*[@name='practitioner_first_name' and @value='r']/../..");
        mapping.put("PRACTITIONER FIRST NAME-WRITE", "//*[@name='practitioner_first_name' and @value='w']/../..");

        mapping.put("PATIENT GENDER-READ", "//*[@name='patient_gender' and @value='r']/../..");
        mapping.put("PATIENT GENDER-WRITE", "//*[@name='patient_gender' and @value='w']/../..");

        mapping.put("PATIENT SET CONVERSATION REVIEW DATE-READ", "//*[@name='patient_set_conversation_review_date' and @value='r']/../..");
        mapping.put("PATIENT SET CONVERSATION REVIEW DATE-WRITE", "//*[@name='patient_set_conversation_review_date' and @value='w']/../..");

        mapping.put("PATIENT CONTACT-READ", "//*[@name='patient_contact' and @value='r']/../..");
        mapping.put("PATIENT CONTACT-WRITE", "//*[@name='patient_contact' and @value='w']/../..");

        mapping.put("PRACTITIONER LAST NAME-READ", "//*[@name='practitioner_last_name' and @value='r']/../..");
        mapping.put("PRACTITIONER LAST NAME-WRITE", "//*[@name='practitioner_last_name' and @value='w']/../..");

        mapping.put("POPULATION DATA-READ", "//*[@name='population_data' and @value='r']/../..");
        mapping.put("POPULATION DATA-WRITE", "//*[@name='population_data' and @value='w']/../..");

    }

}
