package com.example.medi1;

public class docUser {
        String name;
        String Description;

public String getName() {
        return name;
        }

public void setName(String name) {
        this.name = name;
        }

public String getDescription() {
        return Description;
        }

public void setDescription(String Des) {
        this.Description = Des;
        }


public docUser(String name, String des) {
        super();
        this.name = name;
        this.Description = des;
        }

        }