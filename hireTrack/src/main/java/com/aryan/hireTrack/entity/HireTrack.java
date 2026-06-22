package com.aryan.hireTrack.entity;

import java.time.LocalDate;
import java.util.Map;

public class HireTrack {
    private long id;
    private String companyName;
    private Address companyAddress;
    private String companyUrl;
    private String appliedPortal;
    private String jobRole;
    private String jobType;
    private String roleDescription;
    private String salaryOffering;
    private Status applicationStatus;
    private Map<String, String> source;
    private Map<String, String> roundDetail;
    private Map<LocalDate, String> impDate;
    private Map<String, String> notes;
    }