package com.example.medi1;

public class appointment_information {
    private String doctor_name,doctor_id,date,time,status,appointment_id;

    public appointment_information(String doctor_name, String doctor_id, String date, String time, String status, String appointment_id) {
        this.doctor_name = doctor_name;
        this.doctor_id = doctor_id;
        this.date = date;
        this.time = time;
        this.appointment_id = appointment_id;
        this.status=status;
    }

    public  String getStatus()
    {
        return status;
    }

    public  String getAppointment_id()
    {
        return appointment_id;
    }



public  String getDoctor_name()
{
return doctor_name;
        }

    public  String getDoctor_id()
    {
        return doctor_id;
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