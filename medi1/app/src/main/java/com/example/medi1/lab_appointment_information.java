package com.example.medi1;

public class lab_appointment_information {

    private String lab_name,lab_id,doctor_id,date,time,appointment_id;

    public lab_appointment_information(String lab_name, String lab_id, String doctor_id, String date, String time, String appointment_id) {
        this.lab_name = lab_name;
        this.lab_id = lab_id;
        this.doctor_id = doctor_id;
        this.date = date;
        this.time = time;
        this.appointment_id = appointment_id;
    }

    public  String getDoctor_id()
    {
        return doctor_id;
    }

    public  String getAppointment_id()
    {
        return appointment_id;
    }



    public  String getLab_name()
    {
        return lab_name;
    }

    public  String getLab_id()
    {
        return lab_id;
    }

    public  String getDate()
    {
        return date;
    }

    public  String getTime()
    {
        return time;
    }

}
