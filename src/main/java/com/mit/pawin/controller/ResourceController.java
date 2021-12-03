package com.mit.pawin.controller;

import com.mit.pawin.config.CustomJwtAuthenticationFilter;
import com.mit.pawin.dto.*;
import com.mit.pawin.entity.*;
import com.mit.pawin.service.*;
import com.mit.pawin.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping(value = "/hris/web")
@CrossOrigin
public class ResourceController extends CommonUtil {

    @Autowired
    private CommonService commonService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Autowired
    private ResponseDto responseDto;

    @Autowired
    private FilesStorageService storageService;

    @Autowired
    private UtilDto utilDto;

    @Value("${mit.pawin.password.expir.by.admin}")
    private String passExpirByAdmin;

    @Value("${mit.pawin.web.refresh.token}")
    private String refreshToken;

    @Value("${mit.pawin.email.password.listener.folder}")
    private String sysUsrCodeFolder;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final Logger log = LoggerFactory.getLogger(ResourceController.class);


    @Autowired
    private AppointmentService appointmentService;

    /**
     * PawIn apis 2021-06-10
     */

    @RequestMapping(value = "/creatingCustomer", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            SystemUser systemUser = (SystemUser) commonService.getObjectByColumnName("SystemUser", "username", customerDto.getUsername());
            Customer customer = (Customer) commonService.getObjectByColumnName("Customer", "custCode", systemUser.getEmpCode(), "Create Customer");
            BeanUtils.copyProperties(customerDto, customer);

            //Object objectCustomer = commonService.checkTableEmpty("com.mit.pawin.entity.Customer", "Create Customer");

            customer.setCustCode("CUS-" + customer.getCustId());
            customer.setCreatedBy("super");
            customer.setUpdatedAt(new Date());
            customer.setUpdatedBy("super");

            Object object1 = commonService.updateObject(customer, "Customer Creation");

            if (null != object1) {

                response.put("response", responseDto.getOk());
                response.put("message", "Successfully Customer Created.");
                response.put("custCode", customer.getCustCode());
                //response.put("petCode", pet.getPetCode());
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Customer Not Created.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingAllergy", method = RequestMethod.POST)
    public ResponseEntity<?> createAllergy(@RequestBody AllergyDto allergyDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Allergy allergy = new Allergy();
            BeanUtils.copyProperties(allergyDto, allergy);

            Object objectAllergy = commonService.checkTableEmpty("com.mit.pawin.entity.Allergy", "Create Allergy");

            if (null == objectAllergy) {
                allergy.setAllergyId(1);
                allergy.setAllergyCode("ALR-" + "1");
            } else {
                long allergyId = commonService.getLastRecordId("com.mit.pawin.entity.Allergy", "allergyId", "Create Allergy");
                allergy.setAllergyId(allergyId + 1);
                allergy.setAllergyCode("ALR-" + (allergyId + 1));
            }

            allergy.setCreatedBy("super");

            Object object = commonService.addObject(allergy, "Allergy Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Allergy Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Allergy Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingDisabilities", method = RequestMethod.POST)
    public ResponseEntity<?> createDisabilities(@RequestBody MedPhysicalDisableDto medPhysicalDisableDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            MedicalPhysicalDisabilities medicalPhysicalDisabilities = new MedicalPhysicalDisabilities();
            BeanUtils.copyProperties(medPhysicalDisableDto, medicalPhysicalDisabilities);

            Object objectDisabilities = commonService.checkTableEmpty("com.mit.pawin.entity.MedicalPhysicalDisabilities", "Create Disabilities");

            if (null == objectDisabilities) {
                medicalPhysicalDisabilities.setDisableId(1);
                medicalPhysicalDisabilities.setDisableCode("DIS-" + "1");
            } else {
                long DisableId = commonService.getLastRecordId("com.mit.pawin.entity.MedicalPhysicalDisabilities", "disableId", "Create Disabilities");
                medicalPhysicalDisabilities.setDisableId(DisableId + 1);
                medicalPhysicalDisabilities.setDisableCode("DIS-" + (DisableId + 1));
            }

            medicalPhysicalDisabilities.setCreatedBy("super");

            Object object = commonService.addObject(medicalPhysicalDisabilities, "Disability Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Medical & Physical Disable Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Medical & Physical Disable Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingInjury", method = RequestMethod.POST)
    public ResponseEntity<?> createInjury(@RequestBody InjuryDto injuryDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Injury injury = new Injury();
            BeanUtils.copyProperties(injuryDto, injury);

            Object objectInjury = commonService.checkTableEmpty("com.mit.pawin.entity.Injury", "Create Injury");

            if (null == objectInjury) {
                injury.setInjuryId(1);
                injury.setInjuryCode("INJ-" + "1");
            } else {
                long InjuryId = commonService.getLastRecordId("com.mit.pawin.entity.Injury", "injuryId", "Create Injury");
                injury.setInjuryId(InjuryId + 1);
                injury.setInjuryCode("INJ-" + (InjuryId + 1));
            }

            injury.setCreatedBy("super");

            Object object = commonService.addObject(injury, "Injury Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Injury Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Injury Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/creatingMedication", method = RequestMethod.POST)
    public ResponseEntity<?> createMedication(@RequestBody MedicationDto medicationDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Medication medication = new Medication();
            BeanUtils.copyProperties(medicationDto, medication);

            Object objectMedication = commonService.checkTableEmpty("com.mit.pawin.entity.Medication", "Create Medication");

            if (null == objectMedication) {
                medication.setMedId(1);
                medication.setMedCode("MED-" + "1");
            } else {
                long MedId = commonService.getLastRecordId("com.mit.pawin.entity.Medication", "medId", "Create Medication");
                medication.setMedId(MedId + 1);
                medication.setMedCode("MED-" + (MedId + 1));
            }

            medication.setCreatedBy("super");

            Object object = commonService.addObject(medication, "Medication Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Medication Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Medication Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingDietary", method = RequestMethod.POST)
    public ResponseEntity<?> createDietary(@RequestBody DietaryDto dietaryDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Dietary dietary = new Dietary();
            BeanUtils.copyProperties(dietaryDto, dietary);

            Object objectDietary = commonService.checkTableEmpty("com.mit.pawin.entity.Dietary", "Create Dietary");

            if (null == objectDietary) {
                dietary.setDietId(1);
                dietary.setDietCode("DIET-" + "1");
            } else {
                long DietId = commonService.getLastRecordId("com.mit.pawin.entity.Dietary", "dietId", "Create Dietary");
                dietary.setDietId(DietId + 1);
                dietary.setDietCode("DIET-" + (DietId + 1));
            }

            dietary.setCreatedBy("super");

            String strMap = String.valueOf(dietaryDto.getFeedingTimeMap()).replace("{", "").replace("}", "")
                    .replace("\"", "").replace("true", "").replace(",", "");
            String[] timeArr = strMap.split(":");

            for (String s : timeArr) {
                if (s.equals("Morning")) {
                    dietary.setFeedTimeMorning(s);
                } else if (s.equals("Afternoon")) {
                    dietary.setFeedTimeAfternoon(s);
                } else if (s.equals("Evening")) {
                    dietary.setFeedTimeEvening(s);
                }
            }

            dietary.setFoodType(dietaryDto.getFeedType());

            Object object = commonService.addObject(dietary, "Dietary Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Dietary Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Dietary Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/addingVaccinations", method = RequestMethod.POST)
    public ResponseEntity<?> addVaccination(@RequestBody VaccinationDto vaccinationDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Vaccination vaccination = new Vaccination();
            BeanUtils.copyProperties(vaccinationDto, vaccination);

            Object objectVaccination = commonService.checkTableEmpty("com.mit.pawin.entity.Vaccination", "Add Vaccination");

            if (null == objectVaccination) {
                vaccination.setVaccId(1);
                vaccination.setVaccCode("VACC-" + "1");
            } else {
                long VaccId = commonService.getLastRecordId("com.mit.pawin.entity.Vaccination", "vaccId", "Added Vaccination");
                vaccination.setVaccId(VaccId + 1);
                vaccination.setVaccCode("VACC-" + (VaccId + 1));
            }

            vaccination.setCreatedBy("super");

            Object object = commonService.addObject(vaccination, "Added Vaccination");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Vaccination Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Vaccination Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/addingPet", method = RequestMethod.POST)
    public ResponseEntity<?> createPet(@RequestBody PetDto petDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Pet pet = new Pet();
            BeanUtils.copyProperties(petDto, pet);

            Object objectPet = commonService.checkTableEmpty("com.mit.pawin.entity.Pet", "Create Pet");

            if (null == objectPet) {
                pet.setPetId(1);
                pet.setPetCode("PET-" + "1");
            } else {
                long petId = commonService.getLastRecordId("com.mit.pawin.entity.Pet", "petId", "Create Pet");
                pet.setPetId(petId + 1);
                pet.setPetCode("PET-" + (petId + 1));
            }

            pet.setCreatedBy("super");
            pet.setCustCode(pet.getCustCode());

            Object object = commonService.addObject(pet, "Pet Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Successfully Pet Created.");
                response.put("petCode", pet.getPetCode());
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Pet Not Created.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingPetsByCust", method = RequestMethod.POST)
    public ResponseEntity<?> getPetsByCust(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            String custCode = String.valueOf(commonCodeDto.getCode().get("custCode"));

            ArrayList<String> col = new ArrayList<>();
            col.add("custId");

            ArrayList<Object> val = new ArrayList<>();
            val.add(custCode);

            List<Object> objects = commonService.searchRecords("Pet", col, val, "Search Pets");

            response.put("response", responseDto.getOk());
            response.put("message", "All Pets.");
            response.put("pets", objects);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Customer customer = null;
            Supplier supplier = null;

            if (registerDto.getRoleType().toUpperCase().equals("USROLE-3")) {
                customer = new Customer();
            }else if(registerDto.getRoleType().toUpperCase().equals("USROLE-4")){
                supplier = new Supplier();
            }

            Object objectCustomer = null;
            Object objectSupplier = null;

            if(null != customer) {
                objectCustomer = commonService.checkTableEmpty("com.mit.pawin.entity.Customer", "Create Customer");
            }else if(null != supplier){
                objectSupplier = commonService.checkTableEmpty("com.mit.pawin.entity.Supplier", "Create Customer");
            }

            if(null != customer) {
                if (null == objectCustomer) {
                    customer.setCustId(1);
                    customer.setCustCode("CUS-" + "1");
                } else {
                    long customerId = commonService.getLastRecordId("com.mit.pawin.entity.Customer", "custId", "Create Customer");
                    customer.setCustId(customerId + 1);
                    customer.setCustCode("CUS-" + (customerId + 1));
                }
            }else if(null != supplier){
                if (null == objectSupplier) {
                    supplier.setSuppId(1);
                    supplier.setSuppCode("SUP-" + "1");
                } else {
                    long supplierId = commonService.getLastRecordId("com.mit.pawin.entity.Supplier", "suppId", "Create Customer");
                    supplier.setSuppId(supplierId + 1);
                    supplier.setSuppCode("SUP-" + (supplierId + 1));
                }
            }

            if(null != customer) {
                customer.setCreatedBy("super");
            }else if(null != supplier){
                supplier.setCreatedBy("super");
            }

            Object object = null;

            if(null != customer) {
                object = commonService.addObject(customer, "Customer Creation");
            }else if(null != supplier){
                object = commonService.addObject(supplier, "Customer Creation");
            }

            //create system user
            //if (employee.getIsActive() && (null != appType) && userRole.getIsActive()) {
            LinkedList<String> strings = new LinkedList<>();
            strings.add("username");
            strings.add("isActive");

            SystemUserDto systemUserDto = new SystemUserDto();
            systemUserDto.setUsername(registerDto.getUsername());
            systemUserDto.setPassword(registerDto.getPassword());
            systemUserDto.setAppCode("APP-1");

            if(null != customer) {
                systemUserDto.setEmpCode(customer.getCustCode());
            }else if(null != supplier){
                systemUserDto.setEmpCode(supplier.getSuppCode());
            }

            LinkedHashMap chkDup = employeeService.checkDuplicatesOfSystemUserInput("SystemUser", strings, systemUserDto, "System User Creation");

            SystemUser systemUser = new SystemUser();

            if (!chkDup.get("response").equals(responseDto.getOk())) {

                return new ResponseEntity<>(chkDup, HttpStatus.OK);

            } else {

                String sentPassword = systemUserDto.getPassword();

                String decryptedPassword = (String) decryptString(systemUserDto.getPassword()).get("value");
                String encryptedPassword = bcryptEncoder.encode(decryptedPassword);

                systemUserDto.setPassword(encryptedPassword);

                BeanUtils.copyProperties(systemUserDto, systemUser);
                long id = commonService.generateId("com.mit.pawin.entity.SystemUser", "SystemUser", "sysUsrId", "System User Creation");
                systemUser.setSysUsrId(id);
                systemUser.setSysUsrCode(utilDto.getSysUsr() + id);

                Date passExpir = addDaysToDate(new Date(), Integer.parseInt(passExpirByAdmin));

                systemUser.setPassExpir(passExpir);
                systemUser.setIsActive(true);
                systemUser.setCreatedBy(customJwtAuthenticationFilter.getLoginUsername());
                systemUser.setUserLoggingCount(0);
                systemUser.setPassExpirStatus(SystemUser.EmailSendStatus.NOT_SENT);
                systemUser.setCreatedBy("super");
                systemUser.setUserRoleCode(registerDto.getRoleType().toUpperCase());

                Object sysUsrObject = commonService.addObject(systemUser, "System User Creation");

                if (null != sysUsrObject) {
                    response.put("response", responseDto.getOk());
                    response.put("message", "Successfully System User Created.");
                    response.put("sysUsrCode", systemUser.getSysUsrCode());

                    //write empCode to file to send email...
                    writeFileWithAppend(sysUsrCodeFolder + simpleDateFormat.format(new Date()) + ".txt", CredentialEmailStatus.USERNAME_AND_PASSWORD_CHANGED.ordinal() + "," + systemUser.getSysUsrCode()
                            + "," + systemUser.getUsername() + "," + sentPassword);

                    return new ResponseEntity<>(response, HttpStatus.OK);

                } else {

                    response.put("response", responseDto.getFail());
                    response.put("message", "System User Not Created.");
                    return new ResponseEntity<>(response, HttpStatus.OK);

                }
            }
            /*} else {

                response.put("response", responseDto.getFail());
                response.put("message", "Input Is Not Valid.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }*/

            /*if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Successfully Customer Created.");
                //response.put("petCode", pet.getPetCode());
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Customer Not Created.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }*/

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingCustomer", method = RequestMethod.POST)
    public ResponseEntity<?> getCustomer(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String username = String.valueOf(commonCodeDto.getCode().get("username"));

        try {

            SystemUser systemUser = (SystemUser) commonService.getObjectByColumnName("SystemUser", "username", username);
            Customer customer = (Customer) commonService.getObjectByColumnName("Customer", "custCode", systemUser.getEmpCode(), "Create Customer");

            CustomerDto customerDto = new CustomerDto();
            customerDto.setCustFirstName(customer.getCustFirstName());
            customerDto.setCustLastName(customer.getCustLastName());

            response.put("response", responseDto.getOk());
            response.put("message", "Current Customer.");
            response.put("customer", customer);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingPets", method = RequestMethod.POST)
    public ResponseEntity<?> getPets(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String username = String.valueOf(commonCodeDto.getCode().get("username"));

        try {

            SystemUser systemUser = (SystemUser) commonService.getObjectByColumnName("SystemUser", "username", username);
            //Customer customer = (Customer) commonService.getObjectByColumnName("Customer", "custCode", custCode, "Get Pets");

            List<Object> petObjects = commonService.getObjectListByColumnName("Pet", "custCode", systemUser.getEmpCode(), "Get Pets");

            response.put("response", responseDto.getOk());
            response.put("message", "Current Customer pets.");
            response.put("pets", petObjects);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingAllergies", method = RequestMethod.POST)
    public ResponseEntity<?> getAllergies(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String petCode = String.valueOf(commonCodeDto.getCode().get("petCode"));

        try {

            List<Object> allergyObjects = commonService.getObjectListByColumnName("Allergy", "petCode", petCode, "Get Allergies");

            response.put("response", responseDto.getOk());
            response.put("message", "Current Pet allergies.");
            response.put("allergies", allergyObjects);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingDisabilities", method = RequestMethod.POST)
    public ResponseEntity<?> getDisabilities(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String petCode = String.valueOf(commonCodeDto.getCode().get("petCode"));

        try {

            List<Object> disabilitiesObjects = commonService.getObjectListByColumnName("MedicalPhysicalDisabilities", "petCode", petCode, "Get Disabilities");

            response.put("response", responseDto.getOk());
            response.put("message", "Current Pet disabilities.");
            response.put("disabilities", disabilitiesObjects);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingInjuries", method = RequestMethod.POST)
    public ResponseEntity<?> getInjuries(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String petCode = String.valueOf(commonCodeDto.getCode().get("petCode"));

        try {

            List<Object> injuriesObjects = commonService.getObjectListByColumnName("Injury", "petCode", petCode, "Get Injuries");

            response.put("response", responseDto.getOk());
            response.put("message", "Current Pet injuries.");
            response.put("injuries", injuriesObjects);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingMedications", method = RequestMethod.POST)
    public ResponseEntity<?> getMedications(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String petCode = String.valueOf(commonCodeDto.getCode().get("petCode"));

        try {

            List<Object> medicationObjects = commonService.getObjectListByColumnName("Medication", "petCode", petCode, "Get Medications");

            response.put("response", responseDto.getOk());
            response.put("message", "Current Pet medication.");
            response.put("medications", medicationObjects);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingAppointment", method = RequestMethod.POST)
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentDto appointmentDto) {

        LinkedHashMap response = new LinkedHashMap();
        LinkedHashMap<String, Object> invoiceMap = new LinkedHashMap<>();

        try {

            Appointment appointment = new Appointment();
            BeanUtils.copyProperties(appointmentDto, appointment);

            Object objectAppointment = commonService.checkTableEmpty("com.mit.pawin.entity.Appointment", "Create Appointment");

            if (null == objectAppointment) {
                appointment.setAppId(1);
                appointment.setAppCode("APPO-" + "1");
            } else {
                long appoId = commonService.getLastRecordId("com.mit.pawin.entity.Appointment", "appId", "Create Appointment");
                appointment.setAppId(appoId + 1);
                appointment.setAppCode("APPO-" + (appoId + 1));
            }

            appointment.setCreatedBy("super");

            String date1 = convertDateToString(appointmentDto.getCheckInDate());
            String date2 = convertDateToString(appointmentDto.getCheckOutDate());

            invoiceMap.put("dates", date1 + " ->> " + date2);

            long noOfDays = 0;

            if (appointmentDto.getServiceCatCode().equals("Boarding")) {
                noOfDays = noOfDayCountBetweenTwoDays(date1, date2);
            } else {
                noOfDays = noOfDayCountBetweenTwoDays(date1, date2) + 1;
            }

            Service service = (Service) commonService.getObjectByColumnName("Service", "serviceCode", appointmentDto.getServiceCode(), "Create Appointment");

            String strMap = String.valueOf(appointmentDto.getPetCodes()).replace("{", "").replace("}", "")
                    .replace("\"", "").replace("true", "").replace(",", "");
            String[] petCodeArr = strMap.split(":");

            int noOfPets = petCodeArr.length;

            double totPrice = noOfPets * noOfDays * Double.parseDouble(service.getServicePrice());
            appointment.setTotalPrice(String.valueOf(totPrice));
            appointment.setPortalStatus("WAITING");

            Object object = commonService.addObject(appointment, "Appointment Creation");

            for (String petCode : petCodeArr) {
                PetAppointment petAppointment = new PetAppointment();
                //BeanUtils.copyProperties(appointmentDto, appointment);

                Object objectPetAppointment = commonService.checkTableEmpty("com.mit.pawin.entity.PetAppointment", "Create Appointment");

                if (null == objectPetAppointment) {
                    petAppointment.setPetAppId(1);
                    petAppointment.setPetAppCode("PETAPPO-" + "1");
                } else {
                    long petAppoId = commonService.getLastRecordId("com.mit.pawin.entity.PetAppointment", "petAppId", "Create Appointment");
                    petAppointment.setPetAppId(petAppoId + 1);
                    petAppointment.setPetAppCode("PETAPPO-" + (petAppoId + 1));
                }

                petAppointment.setPetCode(petCode);
                petAppointment.setAppCode(appointment.getAppCode());
                petAppointment.setCreatedBy("super");

                Object objectPetApp = commonService.addObject(petAppointment, "Appointment Creation");

            }

            if (null != object) {

                //invoice
                invoiceMap.put("serviceName", service.getServiceName());

                invoiceMap.put("noOfPets", noOfPets);
                invoiceMap.put("noOfDays", noOfDays);
                invoiceMap.put("price", service.getServicePrice());
                invoiceMap.put("total", noOfDays * noOfPets * Double.parseDouble(service.getServicePrice()));

                response.put("response", responseDto.getOk());
                response.put("message", "Appointment added Successfully.");
                response.put("invoice", invoiceMap);
                response.put("appointmentCode", appointment.getAppCode());
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Appointment Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingSupplier", method = RequestMethod.POST)
    public ResponseEntity<?> createSupplier(@RequestBody SupplierDto supplierDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Supplier supplier = new Supplier();
            BeanUtils.copyProperties(supplierDto, supplier);

            Object objectSupplier = commonService.checkTableEmpty("com.mit.pawin.entity.Supplier", "Create Supplier");

            if (null == objectSupplier) {
                supplier.setSuppId(1);
            } else {
                long suppId = commonService.getLastRecordId("com.mit.pawin.entity.Supplier", "suppId", "Create Supplier");
                supplier.setSuppId(suppId + 1);
                supplier.setSuppCode("SUPP-" + (suppId + 1));
            }

            supplier.setCreatedBy("super");

            Object object = commonService.addObject(supplier, "Supplier Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Supplier Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Supplier Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingSupplier", method = RequestMethod.POST)
    public ResponseEntity<?> getSupplier(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String suppCode = String.valueOf(commonCodeDto.getCode().get("suppCode"));

        try {

            List<Object> supplierObjects = commonService.getObjectListByColumnName("Supplier", "suppCode", suppCode, "Get Supplier");

            response.put("response", responseDto.getOk());
            response.put("message", "Current Pet injuries.");
            response.put("supplier", supplierObjects);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingVeterinary", method = RequestMethod.POST)
    public ResponseEntity<?> createVeterinary(@RequestBody VeterinaryDto veterinaryDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Veterinary veterinary = new Veterinary();
            BeanUtils.copyProperties(veterinaryDto, veterinary);

            Object objectVeterinary = commonService.checkTableEmpty("com.mit.pawin.entity.Veterinary", "Create Veterinary");

            if (null == objectVeterinary) {
                veterinary.setVetId(1);
                veterinary.setVetCode("VET-" + 1);
            } else {
                long vetId = commonService.getLastRecordId("com.mit.pawin.entity.Veterinary", "vetId", "Create Veterinary");
                veterinary.setVetId(vetId + 1);
                veterinary.setVetCode("VET-" + (vetId + 1));
            }

            veterinary.setCreatedBy("super");

            Object object = commonService.addObject(veterinary, "Veterinary Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Veterinary Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Veterinary Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingVeterinary", method = RequestMethod.GET)
    public ResponseEntity<?> getVeterinary() {

        LinkedHashMap response = new LinkedHashMap();

        List<LinkedHashMap> veterinary = new ArrayList<>();

        try {

            List<Object> veterinaryDogsObjects = commonService.getAllObjects("Veterinary", "Get Veterinary");

            for (Object object : veterinaryDogsObjects) {
                Veterinary vet = (Veterinary) object;
                LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                hashMap.put("code", vet.getVetCode());
                hashMap.put("vetName", vet.getVetName());
                hashMap.put("vetPhone", vet.getVetPhone());
                hashMap.put("vetAddress", vet.getVetAddress());
                veterinary.add(hashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current veterinary.");
            response.put("veterinarians", veterinary);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingServiceDog", method = RequestMethod.POST)
    public ResponseEntity<?> createServiceDogs(@RequestBody ServiceDogDto serviceDogDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            ServiceDog serviceDog = new ServiceDog();
            BeanUtils.copyProperties(serviceDogDto, serviceDog);

            Object objectServiceDog = commonService.checkTableEmpty("com.mit.pawin.entity.ServiceDog", "Create ServiceDog");

            if (null == objectServiceDog) {
                serviceDog.setServiceDogId(1);
            } else {
                long serviceDogId = commonService.getLastRecordId("com.mit.pawin.entity.ServiceDog", "serviceDogId", "Create ServiceDog");
                serviceDog.setServiceDogId(serviceDogId + 1);
                serviceDog.setServiceDogCode("SD-" + (serviceDogId + 1));
            }

            serviceDog.setCreatedBy("super");

            Object object = commonService.addObject(serviceDog, "ServiceDog Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Service Dog Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Service Dog Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingServiceDogs", method = RequestMethod.POST)
    public ResponseEntity<?> getServiceDogs(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String serviceDogCode = String.valueOf(commonCodeDto.getCode().get("serviceDogCode"));

        try {

            List<Object> serviceDogObjects = commonService.getObjectListByColumnName("ServiceDogs", "serviceDogCode", serviceDogCode, "Get ServiceDogs");

            response.put("response", responseDto.getOk());
            response.put("message", "Current ServiceDogs.");
            response.put("serviceDog", serviceDogObjects);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingResource", method = RequestMethod.POST)
    public ResponseEntity<?> createResource(@RequestBody ResourcesDto resourcesDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Resources resources = new Resources();
            BeanUtils.copyProperties(resourcesDto, resources);

            Object objectResources = commonService.checkTableEmpty("com.mit.pawin.entity.Resources", "Create Resource");

            if (null == objectResources) {
                resources.setRoomId(1);
                resources.setRoomCode("RE-" + 1);
            } else {
                long roomId = commonService.getLastRecordId("com.mit.pawin.entity.Resources", "roomId", "Create Resource");
                resources.setRoomId(roomId + 1);
                resources.setRoomCode("RE-" + (roomId + 1));
            }

            resources.setCreatedBy("super");

            Object object = commonService.addObject(resources, "Resource Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Resource Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Resource Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    /*@RequestMapping(value = "/gettingResourceList", method = RequestMethod.GET)
    public ResponseEntity<?> getResourceList() {

        LinkedHashMap response = new LinkedHashMap();
        String roomCode = String.valueOf(commonCodeDto.getCode().get("roomCode"));

        try {

            List<Object> resourceObjects = commonService.getObjectListByColumnName("Resources", "roomCode", roomCode, "Get Resources");

            response.put("response", responseDto.getOk());
            response.put("message", "Current Resources.");
            response.put("resources", resourceObjects);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }*/

    @RequestMapping(value = "/creatingBreed", method = RequestMethod.POST)
    public ResponseEntity<?> createBreed(@RequestBody BreedDto breedDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Breed breed = new Breed();
            BeanUtils.copyProperties(breedDto, breed);

            Object objectBreed = commonService.checkTableEmpty("com.mit.pawin.entity.Breed", "Create Breed");

            if (null == objectBreed) {
                breed.setBreedId(1);
                breed.setBreedCode("BR-" + 1);
            } else {
                long breedId = commonService.getLastRecordId("com.mit.pawin.entity.Breed", "breedId", "Create Breed");
                breed.setBreedId(breedId + 1);
                breed.setBreedCode("BR-" + (breedId + 1));
            }

            breed.setCreatedBy("super");

            Object object = commonService.addObject(breed, "Breed Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Breed Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Breed Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingBreed", method = RequestMethod.GET)
    public ResponseEntity<?> getBreed() {

        LinkedHashMap response = new LinkedHashMap();
        List records = new ArrayList();

        try {

            List<Object> breedObjects = commonService.getAllObjects("Breed", "Get Breeds");
            for (Object object : breedObjects) {
                Breed breed = (Breed) object;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put("code", breed.getBreedCode());
                linkedHashMap.put("name", breed.getBreedName());
                records.add(linkedHashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Breeds.");
            response.put("breeds", records);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingColor", method = RequestMethod.POST)
    public ResponseEntity<?> createColor(@RequestBody ColorDto colorDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Color color = new Color();
            BeanUtils.copyProperties(colorDto, color);

            Object objectColor = commonService.checkTableEmpty("com.mit.pawin.entity.Color", "Create Color");

            if (null == objectColor) {
                color.setColorId(1);
                color.setColorCode("CO-" + 1);
            } else {
                long colorId = commonService.getLastRecordId("com.mit.pawin.entity.Color", "colorId", "Create Color");
                color.setColorId(colorId + 1);
                color.setColorCode("CO-" + (colorId + 1));
            }

            color.setCreatedBy("super");

            Object object = commonService.addObject(color, "Color Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Color Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Color Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingColor", method = RequestMethod.GET)
    public ResponseEntity<?> getColor() {

        LinkedHashMap response = new LinkedHashMap();
        List records = new ArrayList();

        try {

            List<Object> colorObjects = commonService.getAllObjects("Color", "Get Colors");

            for (Object object : colorObjects) {
                Color color = (Color) object;
                LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("code", color.getColorCode());
                linkedHashMap.put("name", color.getColorName());
                records.add(linkedHashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Colors.");
            response.put("colors", records);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingService", method = RequestMethod.POST)
    public ResponseEntity<?> createService(@RequestBody ServiceDto serviceDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Service service = new Service();
            BeanUtils.copyProperties(serviceDto, service);

            Object objectService = commonService.checkTableEmpty("com.mit.pawin.entity.Service", "Create Service");

            if (null == objectService) {
                service.setServiceId(1);
                service.setServiceCode("SE-" + 1);
            } else {
                long serviceId = commonService.getLastRecordId("com.mit.pawin.entity.Service", "serviceId", "Create Service");
                service.setServiceId(serviceId + 1);
                service.setServiceCode("SE-" + (serviceId + 1));
            }

            service.setCreatedBy("super");

            Object object = commonService.addObject(service, "Color Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Service Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Service Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingEmployee", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeDto, employee);

            Object objectEmployee = commonService.checkTableEmpty("com.mit.pawin.entity.Employee", "Create Employee");

            if (null == objectEmployee) {
                employee.setEmpId(1);
                employee.setEmpCode("EMP-" + 1);
            } else {
                long empId = commonService.getLastRecordId("com.mit.pawin.entity.Employee", "empId", "Create Employee");
                employee.setEmpId(empId + 1);
                employee.setEmpCode("EMP-" + (empId + 1));
            }

            employee.setCreatedBy("super");

            Object object = commonService.addObject(employee, "Employee Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Employee Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Employee Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingServiceByCategory", method = RequestMethod.POST)
    public ResponseEntity<?> getServiceByServiceCatCode(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();


        String serviceCatCode = String.valueOf(commonCodeDto.getCode().get("serviceCatCode"));
        List<LinkedHashMap> recMap = new ArrayList<>();

        try {

            List<Object> serviceCatObjects = commonService.getObjectListByColumnName("Service", "serviceCatCode", serviceCatCode, "Get Services");

            for (Object object : serviceCatObjects) {
                Service service = (Service) object;
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put("name", service.getServiceName());
                linkedHashMap.put("code", service.getServiceCode());
                recMap.add(linkedHashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Services.");
            response.put("serviceData", recMap);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/gettingResource", method = RequestMethod.GET)
    public ResponseEntity<?> getResource() {

        LinkedHashMap response = new LinkedHashMap();

        List<LinkedHashMap> resources = new ArrayList<>();

        try {

            List<Object> resourceObjects = commonService.getAllObjects("Resources", "Get Resources");

            for (Object object : resourceObjects) {
                Resources res = (Resources) object;
                LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                hashMap.put("code", res.getRoomCode());
                hashMap.put("name", res.getRoomName());
                hashMap.put("maxWeight", res.getMaxWeight());
                hashMap.put("cleanNeeded", res.getCleanNeeded());
                resources.add(hashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Resources.");
            response.put("resources", resources);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingEmployee", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee() {

        LinkedHashMap response = new LinkedHashMap();

        List<LinkedHashMap> employees = new ArrayList<>();

        try {

            List<Object> employeeObjects = commonService.getAllObjects("Employee", "Get Employees");

            for (Object object : employeeObjects) {
                Employee emp = (Employee) object;
                LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                hashMap.put("code", emp.getEmpCode());
                hashMap.put("name", emp.getEmpName());
                hashMap.put("number", emp.getEmpNo());
                hashMap.put("phone", emp.getEmpPhone());
                hashMap.put("address", emp.getEmpAddress());
                hashMap.put("nic", emp.getEmpNic());
                employees.add(hashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Employees.");
            response.put("employees", employees);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/savingPayment", method = RequestMethod.POST)
    public ResponseEntity<?> savePayment(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String appointmentCode = String.valueOf(commonCodeDto.getCode().get("appointmentCode"));
        String paymentType = String.valueOf(commonCodeDto.getCode().get("paymentType"));
        List<LinkedHashMap> recMap = new ArrayList<>();

        try {

            int updated = commonService.updateRecords("Appointment", "paymentType", "appCode", paymentType, appointmentCode, "Save Payment");

            if (updated != -1) {
                response.put("response", responseDto.getOk());
                response.put("message", "Successfully paid by " + paymentType);

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("response", responseDto.getFail());
                response.put("message", "Error in Cash Payment");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingService", method = RequestMethod.GET)
    public ResponseEntity<?> getService() {

        LinkedHashMap response = new LinkedHashMap();

        List<LinkedHashMap> services = new ArrayList<>();

        try {

            List<Object> servicesObjects = commonService.getAllObjects("Service", "Get Services");

            for (Object object : servicesObjects) {
                Service service = (Service) object;
                LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                hashMap.put("code", service.getServiceCode());
                hashMap.put("serviceCatName", service.getServiceCatCode());
                hashMap.put("serviceName", service.getServiceName());
                hashMap.put("servicePrice", service.getServicePrice());
                services.add(hashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Services.");
            response.put("resources", services);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingAllServiceDogs", method = RequestMethod.GET)
    public ResponseEntity<?> getServiceDogs() {

        LinkedHashMap response = new LinkedHashMap();

        List<LinkedHashMap> serviceDogs = new ArrayList<>();

        try {

            List<Object> serviceDogsObjects = commonService.getAllObjects("ServiceDog", "Get Service Dogs");

            for (Object object : serviceDogsObjects) {
                ServiceDog serviceDog = (ServiceDog) object;
                LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                hashMap.put("code", serviceDog.getServiceDogCode());
                hashMap.put("serviceDogName", serviceDog.getServiceDogName());
                hashMap.put("serviceDogDob", convertDateToString(serviceDog.getServiceDogDob()));

                Breed breed = (Breed) commonService.getObjectByColumnName("Breed", "breedCode", serviceDog.getBreedCode(), "Get Service Dogs");

                hashMap.put("breed", breed.getBreedName());

                Color color = (Color) commonService.getObjectByColumnName("Color", "colorCode", serviceDog.getColorCode(), "Get Service Dogs");

                hashMap.put("color", color.getColorName());
                hashMap.put("note", serviceDog.getNote());
                serviceDogs.add(hashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Service Dogs.");
            response.put("serviceDogs", serviceDogs);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingRetail", method = RequestMethod.POST)
    public ResponseEntity<?> createRetail(@RequestBody RetailDto retailDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Retail retail = new Retail();
            BeanUtils.copyProperties(retailDto, retail);

            Object objectRetail = commonService.checkTableEmpty("com.mit.pawin.entity.Retail", "Create Retail");

            if (null == objectRetail) {
                retail.setRetailId(1);
                retail.setRetailCode("RE-" + 1);
            } else {
                long retailId = commonService.getLastRecordId("com.mit.pawin.entity.Retail", "retailId", "Create Retail");
                retail.setRetailId(retailId + 1);
                retail.setRetailCode("RE-" + (retailId + 1));
            }

            retail.setCreatedBy("super");

            Object object = commonService.addObject(retail, "Retail Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Retail Details added Successfully.");
                response.put("retailCode", retail.getRetailCode());
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Retail Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingRetails", method = RequestMethod.GET)
    public ResponseEntity<?> getRetails() {

        LinkedHashMap response = new LinkedHashMap();

        List<LinkedHashMap> retails = new ArrayList<>();

        try {

            List<Object> retailObjects = commonService.getAllObjects("Retail", "Get Retail");

            for (Object object : retailObjects) {
                Retail retail = (Retail) object;
                LinkedHashMap hashMap = new LinkedHashMap<>();
                hashMap.put("code", retail.getRetailCode());
                hashMap.put("name", retail.getRetailName());
                hashMap.put("price", retail.getRetailPrice());
                hashMap.put("category", retail.getRetailCatCode());
                retails.add(hashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "All Retails.");
            response.put("retails", retails);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingStock", method = RequestMethod.POST)
    public ResponseEntity<?> createStock(@RequestBody SupplierRetailDto supplierRetailDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            Retail retail = (Retail) commonService.getObjectByColumnName("Retail", "retailCode", supplierRetailDto.getRetailCode(), "Create Stocks");

            SupplierRetail supplierRetail = new SupplierRetail();
            BeanUtils.copyProperties(supplierRetailDto, supplierRetail);

            Object objectRetail = commonService.checkTableEmpty("com.mit.pawin.entity.SupplierRetail", "Create Stocks");

            if (null == objectRetail) {
                supplierRetail.setSuppRetailId(1);
                supplierRetail.setSuppCode("SR-" + 1);
            } else {
                long retailId = commonService.getLastRecordId("com.mit.pawin.entity.SupplierRetail", "suppRetailId", "Create Stocks");
                supplierRetail.setSuppRetailId(retailId + 1);
                supplierRetail.setSuppCode("SR-" + (retailId + 1));
            }

            supplierRetail.setRetailName(retail.getRetailName());
            supplierRetail.setCreatedBy(customJwtAuthenticationFilter.getLoginUsername());
            supplierRetail.setIsApproved(false);

            Object object = commonService.addObject(supplierRetail, "Supplier Retail Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Supplier Retail Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Retail Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingStocks", method = RequestMethod.GET)
    public ResponseEntity<?> getStocks() {

        LinkedHashMap response = new LinkedHashMap();

        List<LinkedHashMap> retails = new ArrayList<>();

        try {

            List<Object> retailObjects = commonService.getAllObjects("SupplierRetail", "Get Retail");

            for (Object object : retailObjects) {
                SupplierRetail retail = (SupplierRetail) object;
                LinkedHashMap hashMap = new LinkedHashMap<>();
                hashMap.put("code", retail.getSuppCode());
                hashMap.put("name", retail.getRetailName());
                hashMap.put("price", retail.getWholeSalePrice());
                hashMap.put("count", retail.getMaxCount());
                hashMap.put("supName", retail.getCreatedBy());
                retails.add(hashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "All Retails.");
            response.put("stocks", retails);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingRetailAllCategories", method = RequestMethod.POST)
    public ResponseEntity<?> getRetailAllCategories(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String retailCode = String.valueOf(commonCodeDto.getCode().get("retailCode"));

        try {

            List<Object> serviceDogObjects = commonService.getObjectListByColumnName("Retail", "retailCode", retailCode, "Get Retail All Categories");

            response.put("response", responseDto.getOk());
            response.put("message", "Current Retail All Categories.");
            response.put("retailCode", serviceDogObjects);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingRetailByCategory", method = RequestMethod.POST)
    public ResponseEntity<?> getRetailByCategory(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String retailCatCode = String.valueOf(commonCodeDto.getCode().get("retailCatCode"));

        try {

            List<Object> retails = null;

            if (retailCatCode.equals("All Categories")) {
                retails = commonService.getAllObjects("Retail", "Get All Retails");
            } else {
                retails = commonService.getObjectListByColumnName("Retail", "retailCatCode", retailCatCode, "Get All Retails");
            }

            List list1 = new ArrayList();
            List list2 = new ArrayList();
            List list3 = new ArrayList();

            for (int i = 0; i < retails.size(); i++) {
                if (i % 3 == 0) {
                    list1.add(retails.get(i));
                } else if (i % 3 == 1) {
                    list2.add(retails.get(i));
                } else if (i % 3 == 2) {
                    list3.add(retails.get(i));
                }
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Retails.");
            response.put("list1", list1);
            response.put("list2", list2);
            response.put("list3", list3);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingAppointment", method = RequestMethod.POST)
    public ResponseEntity<?> getAppointment(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String searchDate = String.valueOf(commonCodeDto.getCode().get("searchDate"));
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd");
        Date searchDDate = null;
        try {
            searchDDate = simpleDateFormatWithTime.parse(searchDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {

            List<Object> appointments = null;

            appointments = commonService.getAllObjects("Appointment", "Get All Appointments");

            List listWaiting = new ArrayList();
            List listCheckIn = new ArrayList();
            List listCheckOut = new ArrayList();

            List petAppointments = null;

            for (Object object : appointments) {
                Appointment appointment = (Appointment) object;
                String checkinDate = simpleDateFormatWithTime.format(appointment.getCheckInDate());
                Date checkinDDate = simpleDateFormatWithTime.parse(checkinDate);
                String checkoutDate = simpleDateFormatWithTime.format(appointment.getCheckOutDate());
                Date checkoutDDate = simpleDateFormatWithTime.parse(checkoutDate);

                if (checkinDDate.before(searchDDate) && checkoutDDate.after(searchDDate)) {
                    if (appointment.getPortalStatus().equals("WAITING")) {

                        petAppointments = commonService.getObjectListByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");

                        for (Object obj : petAppointments) {
                            PetAppointment petAppointment1 = (PetAppointment) obj;
                            listWaiting.add(appointmentService.getAppointments(appointment, petAppointment1));
                        }

                    } else if (appointment.getPortalStatus().equals("CHECK_IN")) {

                        petAppointments = commonService.getObjectListByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");

                        for (Object obj : petAppointments) {
                            PetAppointment petAppointment1 = (PetAppointment) obj;
                            listCheckIn.add(appointmentService.getAppointments(appointment, petAppointment1));
                        }

                    } else if (appointment.getPortalStatus().equals("CHECK_OUT")) {

                        petAppointments = commonService.getObjectListByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");

                        for (Object obj : petAppointments) {
                            PetAppointment petAppointment1 = (PetAppointment) obj;
                            listCheckOut.add(appointmentService.getAppointments(appointment, petAppointment1));
                        }

                    }
                } else if (searchDate.equals(checkinDate)) {
                    if (appointment.getPortalStatus().equals("WAITING")) {

                        petAppointments = commonService.getObjectListByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");

                        for (Object obj : petAppointments) {
                            PetAppointment petAppointment1 = (PetAppointment) obj;
                            listWaiting.add(appointmentService.getAppointments(appointment, petAppointment1));
                        }

                    } else if (appointment.getPortalStatus().equals("CHECK_IN")) {

                        petAppointments = commonService.getObjectListByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");

                        for (Object obj : petAppointments) {
                            PetAppointment petAppointment1 = (PetAppointment) obj;
                            listCheckIn.add(appointmentService.getAppointments(appointment, petAppointment1));
                        }

                    } else if (appointment.getPortalStatus().equals("CHECK_OUT")) {

                        petAppointments = commonService.getObjectListByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");

                        for (Object obj : petAppointments) {
                            PetAppointment petAppointment1 = (PetAppointment) obj;
                            listCheckOut.add(appointmentService.getAppointments(appointment, petAppointment1));
                        }

                    }
                } else if (searchDate.equals(checkoutDate)) {
                    if (appointment.getPortalStatus().equals("WAITING")) {

                        petAppointments = commonService.getObjectListByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");

                        for (Object obj : petAppointments) {
                            PetAppointment petAppointment1 = (PetAppointment) obj;
                            listWaiting.add(appointmentService.getAppointments(appointment, petAppointment1));
                        }

                    } else if (appointment.getPortalStatus().equals("CHECK_IN")) {

                        petAppointments = commonService.getObjectListByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");

                        for (Object obj : petAppointments) {
                            PetAppointment petAppointment1 = (PetAppointment) obj;
                            listCheckIn.add(appointmentService.getAppointments(appointment, petAppointment1));
                        }

                    } else if (appointment.getPortalStatus().equals("CHECK_OUT")) {

                        petAppointments = commonService.getObjectListByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");

                        for (Object obj : petAppointments) {
                            PetAppointment petAppointment1 = (PetAppointment) obj;
                            listCheckOut.add(appointmentService.getAppointments(appointment, petAppointment1));
                        }

                    }
                }
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Retails.");
            response.put("list1", listWaiting);
            response.put("list2", listCheckIn);
            response.put("list3", listCheckOut);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/updatingAppointmentStatus", method = RequestMethod.POST)
    public ResponseEntity<?> updateAppointmentStatus(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String appCode = String.valueOf(commonCodeDto.getCode().get("appCode"));
        String portalStatus = String.valueOf(commonCodeDto.getCode().get("portalStatus"));

        try {

            Appointment appointment = (Appointment) commonService.getObjectByColumnName("Appointment", "appCode", appCode);
            appointment.setPortalStatus(portalStatus);

            Object obj = commonService.updateObject(appointment);

            response.put("response", responseDto.getOk());
            response.put("message", "Updated Status Successfully.");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingAppointmentByService", method = RequestMethod.POST)
    public ResponseEntity<?> getAppointmentByService(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        String serviceCatCode = String.valueOf(commonCodeDto.getCode().get("serviceCatCode"));
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd");

        String todayDate = simpleDateFormatWithTime.format(new Date());
        Date today = null;

        try {
            today = simpleDateFormatWithTime.parse(todayDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {

            List<Object> appointments = null;

            if ("All".equals(serviceCatCode)) {

                ArrayList<String> col = new ArrayList<>();
                ArrayList<Object> val = new ArrayList<>();

                col.add("checkInDate");
                col.add("portalStatus");

                val.add(today);
                val.add("CHECK_IN");

                appointments = commonService.searchRecordList("Appointment", col, val);

            } else {

                ArrayList<String> col = new ArrayList<>();
                ArrayList<Object> val = new ArrayList<>();

                col.add("serviceCatCode");
                col.add("checkInDate");
                col.add("portalStatus");

                val.add(serviceCatCode);
                val.add(today);
                val.add("CHECK_IN");

                appointments = commonService.searchRecordList("Appointment", col, val);

            }

            List list = new ArrayList();

            if (null != appointments) {
                for (Object object : appointments) {
                    Appointment appointment = (Appointment) object;
                    List petAppointments = commonService.getObjectListByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");

                    for (Object obj : petAppointments) {

                        PetAppointment petAppointment = (PetAppointment) obj;

                        Pet pet = (Pet) commonService.getObjectByColumnName("Pet", "petCode", petAppointment.getPetCode(), "Get All Appointments");
                        Service service = (Service) commonService.getObjectByColumnName("Service", "serviceCode", appointment.getServiceCode(), "Get All Appointments");

                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        linkedHashMap.put("petCode", pet.getPetCode());
                        linkedHashMap.put("petName", pet.getPetName());
                        linkedHashMap.put("serviceName", service.getServiceName());
                        list.add(linkedHashMap);
                    }
                }
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Appointments.");
            response.put("list", list);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/searchingEmployee", method = RequestMethod.POST)
    public ResponseEntity<?> searchEmployee(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            String empName = String.valueOf(commonCodeDto.getCode().get("empName"));
            List<Object> employees = commonService.searchRecordByName("Employee", "empName", empName);

            response.put("response", responseDto.getOk());
            response.put("message", "All employees.");
            response.put("empList", employees);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingEmployeeSchedule", method = RequestMethod.POST)
    public ResponseEntity<?> addEmployeeSchedule(@RequestBody EmployeeScheduleDto employeeScheduleDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            EmployeeSchedule employeeSchedule = new EmployeeSchedule();
            //BeanUtils.copyProperties(employeeScheduleDto, employeeSchedule);

            Object object = null;

            if (employeeScheduleDto.getIsSunday() != null && employeeScheduleDto.getIsSunday().equals("true")) {
                object = appointmentService.addEmpSchedule("SUNDAY", employeeScheduleDto);
            }
            if (employeeScheduleDto.getIsMonday() != null && employeeScheduleDto.getIsMonday().equals("true")) {
                object = appointmentService.addEmpSchedule("MONDAY", employeeScheduleDto);
            }
            if (employeeScheduleDto.getIsTuesday() != null && employeeScheduleDto.getIsTuesday().equals("true")) {
                object = appointmentService.addEmpSchedule("TUESDAY", employeeScheduleDto);
            }
            if (employeeScheduleDto.getIsWednesday() != null && employeeScheduleDto.getIsWednesday().equals("true")) {
                object = appointmentService.addEmpSchedule("WEDNESDAY", employeeScheduleDto);
            }
            if (employeeScheduleDto.getIsThursday() != null && employeeScheduleDto.getIsThursday().equals("true")) {
                object = appointmentService.addEmpSchedule("THURSDAY", employeeScheduleDto);
            }
            if (employeeScheduleDto.getIsFriday() != null && employeeScheduleDto.getIsFriday().equals("true")) {
                object = appointmentService.addEmpSchedule("FRIDAY", employeeScheduleDto);
            }
            if (employeeScheduleDto.getIsSaturday() != null && employeeScheduleDto.getIsSaturday().equals("true")) {
                object = appointmentService.addEmpSchedule("SATURDAY", employeeScheduleDto);
            }

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Add Employee Schedule Successfully");
                //response.put("petCode", employeeSchedule.getPetCode());
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Employee Schedule Not Created.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingAllScheduleByEmpCode", method = RequestMethod.POST)
    public ResponseEntity<?> getAllScheduleByEmpCode(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            String empCode = String.valueOf(commonCodeDto.getCode().get("empCode"));
            List<Object> empSchedule = commonService.searchRecordByName("EmployeeSchedule", "empCode", empCode);

            response.put("response", responseDto.getOk());
            response.put("message", "All employee schedule.");
            response.put("scheduleRecord", empSchedule);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingEmployeeByDateRange", method = RequestMethod.POST)
    public ResponseEntity<?> getEmployeeByDateRange(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            String checkInDate = String.valueOf(commonCodeDto.getCode().get("checkInDate"));
            String checkOutDate = String.valueOf(commonCodeDto.getCode().get("checkOutDate"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date1 = String.valueOf(checkInDate);
            LocalDate localDate1 = LocalDate.parse(date1, formatter);
            String date2 = String.valueOf(checkOutDate);
            Date date2New1 = convertStringToDate(date2 + " 00:00:00");
            Date date2New2 = addDaysToDate(date2New1, 1);
            String date2New3 = convertDateToString(date2New2);

            LocalDate localDate2 = LocalDate.parse(date2New3, formatter);

            List<LocalDate> localDateList = getDatesBetweenUsingJava8(localDate1, localDate2);

            List<String> dayList = new ArrayList<>();

            for (LocalDate localDate : localDateList) {
                String d = localDate.getDayOfWeek().toString();
                dayList.add(d);
            }

            List<String> listWithoutDuplicates = new ArrayList<>(
                    new HashSet<>(dayList));

            ArrayList<String> col = new ArrayList<>();
            ArrayList<Object> val = new ArrayList<>();

            for (String day : listWithoutDuplicates) {
                col.add("day");
                val.add(day);
            }

            List scheduleRecords = commonService.searchRecordByColumnsOr("EmployeeSchedule", col, val);

            List<String> empCodeList = new ArrayList<>();
            if (null != scheduleRecords) {
                for (Object object : scheduleRecords) {
                    EmployeeSchedule employeeSchedule = (EmployeeSchedule) object;
                    empCodeList.add(employeeSchedule.getEmpCode());
                }
            }

            List<String> empCodeListWithoutDuplicates = new ArrayList<>(
                    new HashSet<>(empCodeList));

            ArrayList<String> col1 = new ArrayList<>();
            ArrayList<Object> val1 = new ArrayList<>();

            for (String empCode : empCodeListWithoutDuplicates) {
                col1.add("empCode");
                val1.add(empCode);
            }

            List empRecords = commonService.searchRecordByColumnsOr("Employee", col1, val1);

            List<LinkedHashMap> employees = new ArrayList<>();

            if (null != empRecords) {
                for (Object object : empRecords) {
                    Employee emp = (Employee) object;
                    LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                    hashMap.put("code", emp.getEmpCode());
                    hashMap.put("name", emp.getEmpName());
                    hashMap.put("number", emp.getEmpNo());
                    hashMap.put("phone", emp.getEmpPhone());
                    hashMap.put("address", emp.getEmpAddress());
                    hashMap.put("nic", emp.getEmpNic());
                    employees.add(hashMap);
                }
            }

            response.put("response", responseDto.getOk());
            response.put("message", "All employee schedule.");
            response.put("employees", employees);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingFeedingReport", method = RequestMethod.POST)
    public ResponseEntity<?> getFeedingReport(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDate = String.valueOf(commonCodeDto.getCode().get("selectedDate"));
        String selectedTime = String.valueOf(commonCodeDto.getCode().get("selectedTime"));
        Date searchDDate = null;
        try {
            searchDDate = simpleDateFormatWithTime.parse(selectedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {

            List<Object> appointments = commonService.getAllObjects("Appointment", "Get All Appointments");
            List<LinkedHashMap> feedingList = new ArrayList<>();

            for (Object object : appointments) {
                Appointment appointment = (Appointment) object;
                String checkinDate = simpleDateFormatWithTime.format(appointment.getCheckInDate());
                Date checkinDDate = simpleDateFormatWithTime.parse(checkinDate);
                String checkoutDate = simpleDateFormatWithTime.format(appointment.getCheckOutDate());
                Date checkoutDDate = simpleDateFormatWithTime.parse(checkoutDate);

                if (
                        (checkinDDate.equals(searchDDate) || checkinDDate.before(searchDDate)) &&
                                (checkoutDDate.after(searchDDate) || checkoutDDate.equals(searchDDate))
                        ) {
                    LinkedHashMap<Object, Object> lhm = new LinkedHashMap<>();
                    lhm.put("roomName", appointment.getRoomCode());

                    PetAppointment petAppointment = (PetAppointment) commonService.getObjectByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");
                    Pet pet = (Pet) commonService.getObjectByColumnName("Pet", "petCode", petAppointment.getPetCode(), "Get All Appointments");

                    ArrayList<String> col = new ArrayList<>();
                    col.add("petCode");

                    ArrayList<Object> val = new ArrayList<>();
                    val.add(pet.getPetCode());

                    Dietary dietary = null;

                    if ("Morning".equals(selectedTime)) {
                        col.add("feedTimeMorning");
                        val.add("Morning");
                        dietary = (Dietary) commonService.searchRecord("Dietary", col, val, "Get All Appointments");
                        lhm.put("petName", pet.getPetName());
                        Breed breed = (Breed) commonService.getObjectByColumnName("Breed", "breedCode", pet.getBreedCode(), "Get All Appointments");
                        lhm.put("breedName", breed.getBreedName());
                        lhm.put("weight", pet.getWeight());
                        if (null != dietary) {
                            lhm.put("foodType", dietary.getFoodType());
                            lhm.put("feedAlone", dietary.getFeedAlone());
                        }
                        lhm.put("checkIn", checkinDate);
                        lhm.put("checkOut", checkoutDate);
                        feedingList.add(lhm);
                    } else if ("Afternoon".equals(selectedTime)) {
                        col.add("feedTimeAfternoon");
                        val.add("Afternoon");
                        dietary = (Dietary) commonService.searchRecord("Dietary", col, val, "Get All Appointments");
                        lhm.put("petName", pet.getPetName());
                        Breed breed = (Breed) commonService.getObjectByColumnName("Breed", "breedCode", pet.getBreedCode(), "Get All Appointments");
                        lhm.put("breedName", breed.getBreedName());
                        lhm.put("weight", pet.getWeight());
                        if (null != dietary) {
                            lhm.put("foodType", dietary.getFoodType());
                            lhm.put("feedAlone", dietary.getFeedAlone());
                        }
                        lhm.put("checkIn", checkinDate);
                        lhm.put("checkOut", checkoutDate);
                        feedingList.add(lhm);
                    } else if ("Evening".equals(selectedTime)) {
                        col.add("feedTimeEvening");
                        val.add("Evening");
                        dietary = (Dietary) commonService.searchRecord("Dietary", col, val, "Get All Appointments");
                        lhm.put("petName", pet.getPetName());
                        Breed breed = (Breed) commonService.getObjectByColumnName("Breed", "breedCode", pet.getBreedCode(), "Get All Appointments");
                        lhm.put("breedName", breed.getBreedName());
                        lhm.put("weight", pet.getWeight());
                        if (null != dietary) {
                            lhm.put("foodType", dietary.getFoodType());
                            lhm.put("feedAlone", dietary.getFeedAlone());
                        }
                        lhm.put("checkIn", checkinDate);
                        lhm.put("checkOut", checkoutDate);
                        feedingList.add(lhm);
                    } else if ("All".equals(selectedTime)) {
                        col.add("feedTimeMorning");
                        val.add("Morning");
                        col.add("feedTimeAfternoon");
                        val.add("Afternoon");
                        col.add("feedTimeEvening");
                        val.add("Evening");
                        dietary = (Dietary) commonService.searchRecord("Dietary", col, val, "Get All Appointments");
                        lhm.put("petName", pet.getPetName());
                        Breed breed = (Breed) commonService.getObjectByColumnName("Breed", "breedCode", pet.getBreedCode(), "Get All Appointments");
                        lhm.put("breedName", breed.getBreedName());
                        lhm.put("weight", pet.getWeight());
                        if (null != dietary) {
                            lhm.put("foodType", dietary.getFoodType());
                            lhm.put("feedAlone", dietary.getFeedAlone());
                        }
                        lhm.put("checkIn", checkinDate);
                        lhm.put("checkOut", checkoutDate);
                        feedingList.add(lhm);
                    }
                }
            }

            response.put("response", responseDto.getOk());
            response.put("message", "All employees.");
            response.put("feedingList", feedingList);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingMedicationReport", method = RequestMethod.POST)
    public ResponseEntity<?> getMedicationReport(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd");
        String selectedDate = String.valueOf(commonCodeDto.getCode().get("searchDate"));
        String selectedTime = String.valueOf(commonCodeDto.getCode().get("searchTime"));
        Date searchDDate = null;
        try {
            searchDDate = simpleDateFormatWithTime.parse(selectedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {

            List<Object> appointments = commonService.getAllObjects("Appointment", "Get All Appointments");
            List<LinkedHashMap> feedingList = new ArrayList<>();

            for (Object object : appointments) {
                Appointment appointment = (Appointment) object;
                String checkinDate = simpleDateFormatWithTime.format(appointment.getCheckInDate());
                Date checkinDDate = simpleDateFormatWithTime.parse(checkinDate);
                String checkoutDate = simpleDateFormatWithTime.format(appointment.getCheckOutDate());
                Date checkoutDDate = simpleDateFormatWithTime.parse(checkoutDate);

                if (
                        (checkinDDate.equals(searchDDate) || checkinDDate.before(searchDDate)) ||
                                (checkoutDDate.after(searchDDate) || checkoutDDate.equals(searchDDate))
                        ) {
                    LinkedHashMap<Object, Object> lhm = new LinkedHashMap<>();
                    lhm.put("roomName", appointment.getRoomCode());

                    PetAppointment petAppointment = (PetAppointment) commonService.getObjectByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");
                    Pet pet = (Pet) commonService.getObjectByColumnName("Pet", "petCode", petAppointment.getPetCode(), "Get All Appointments");
                    Customer customer = (Customer) commonService.getObjectByColumnName("Customer", "custCode", pet.getCustCode(), "Get All Appointments");

                    ArrayList<String> col = new ArrayList<>();
                    col.add("petCode");

                    ArrayList<Object> val = new ArrayList<>();
                    val.add(pet.getPetCode());

                    Medication medication = null;

                    if ("AM".equals(selectedTime)) {
                        col.add("medAM");
                        val.add("Yes");
                        medication = (Medication) commonService.searchRecord("Medication", col, val, "Get All Appointments");
                        if (null != medication) {
                            lhm.put("time", "AM");
                            lhm.put("medName", medication.getMedName());
                            lhm.put("dose", medication.getMedDosage());
                            lhm.put("frequency", medication.getMedFrequency());
                            lhm.put("petName", pet.getPetName());
                            lhm.put("ownerName", customer.getCustFirstName());
                            Breed breed = (Breed) commonService.getObjectByColumnName("Breed", "breedCode", pet.getBreedCode(), "Get All Appointments");
                            lhm.put("breedName", breed.getBreedName());
                            lhm.put("weight", pet.getWeight());
                            Service service = (Service) commonService.getObjectByColumnName("Service", "serviceCode", appointment.getServiceCode(), "Get All Appointments");
                            if (null != service) {
                                lhm.put("serviceName", service.getServiceName());
                            }
                            lhm.put("checkIn", checkinDate);
                            lhm.put("checkOut", checkoutDate);
                            feedingList.add(lhm);
                        }
                    } else if ("Mid".equals(selectedTime)) {
                        col.add("medMid");
                        val.add("Yes");
                        medication = (Medication) commonService.searchRecord("Medication", col, val, "Get All Appointments");
                        if (null != medication) {
                            lhm.put("time", "Mid");
                            lhm.put("medName", medication.getMedName());
                            lhm.put("dose", medication.getMedDosage());
                            lhm.put("frequency", medication.getMedFrequency());
                            lhm.put("petName", pet.getPetName());
                            lhm.put("ownerName", customer.getCustFirstName());
                            Breed breed = (Breed) commonService.getObjectByColumnName("Breed", "breedCode", pet.getBreedCode(), "Get All Appointments");
                            lhm.put("breedName", breed.getBreedName());
                            lhm.put("weight", pet.getWeight());
                            Service service = (Service) commonService.getObjectByColumnName("Service", "serviceCode", appointment.getServiceCode(), "Get All Appointments");
                            if (null != service) {
                                lhm.put("serviceName", service.getServiceName());
                            }
                            lhm.put("checkIn", checkinDate);
                            lhm.put("checkOut", checkoutDate);
                            feedingList.add(lhm);
                        }
                    } else if ("PM".equals(selectedTime)) {
                        col.add("medPM");
                        val.add("Yes");
                        medication = (Medication) commonService.searchRecord("Medication", col, val, "Get All Appointments");
                        if (null != medication) {
                            lhm.put("time", "PM");
                            lhm.put("medName", medication.getMedName());
                            lhm.put("dose", medication.getMedDosage());
                            lhm.put("frequency", medication.getMedFrequency());
                            lhm.put("petName", pet.getPetName());
                            lhm.put("ownerName", customer.getCustFirstName());
                            Breed breed = (Breed) commonService.getObjectByColumnName("Breed", "breedCode", pet.getBreedCode(), "Get All Appointments");
                            lhm.put("breedName", breed.getBreedName());
                            lhm.put("weight", pet.getWeight());
                            Service service = (Service) commonService.getObjectByColumnName("Service", "serviceCode", appointment.getServiceCode(), "Get All Appointments");
                            if (null != service) {
                                lhm.put("serviceName", service.getServiceName());
                            }
                            lhm.put("checkIn", checkinDate);
                            lhm.put("checkOut", checkoutDate);
                            feedingList.add(lhm);
                        }
                    } else if ("All".equals(selectedTime)) {
                        col.add("medAM");
                        val.add("Yes");
                        col.add("medMid");
                        val.add("Yes");
                        col.add("medPM");
                        val.add("Yes");
                        medication = (Medication) commonService.searchRecord("Medication", col, val, "Get All Appointments");
                        if (null != medication) {
                            lhm.put("time", "All");
                            lhm.put("medName", medication.getMedName());
                            lhm.put("dose", medication.getMedDosage());
                            lhm.put("frequency", medication.getMedFrequency());
                            lhm.put("petName", pet.getPetName());
                            lhm.put("ownerName", customer.getCustFirstName());
                            Breed breed = (Breed) commonService.getObjectByColumnName("Breed", "breedCode", pet.getBreedCode(), "Get All Appointments");
                            lhm.put("breedName", breed.getBreedName());
                            lhm.put("weight", pet.getWeight());
                            Service service = (Service) commonService.getObjectByColumnName("Service", "serviceCode", appointment.getServiceCode(), "Get All Appointments");
                            if (null != service) {
                                lhm.put("serviceName", service.getServiceName());
                            }
                            lhm.put("checkIn", checkinDate);
                            lhm.put("checkOut", checkoutDate);
                            feedingList.add(lhm);
                        }
                    }
                }
            }

            response.put("response", responseDto.getOk());
            response.put("message", "All employees.");
            response.put("medList", feedingList);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingCheckInReport", method = RequestMethod.POST)
    public ResponseEntity<?> getCheckInReport(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = String.valueOf(commonCodeDto.getCode().get("startDate"));
        String endDate = String.valueOf(commonCodeDto.getCode().get("endDate"));
        Date startDDate = null;
        Date endDDate = null;
        try {
            startDDate = simpleDateFormatWithTime.parse(startDate);
            endDDate = simpleDateFormatWithTime.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {

            List<Object> appointments = commonService.getAllObjects("Appointment", "Get All Appointments");
            List<LinkedHashMap> checkinList = new ArrayList<>();

            for (Object object : appointments) {
                Appointment appointment = (Appointment) object;
                String checkinDate = simpleDateFormatWithTime.format(appointment.getCheckInDate());
                Date checkinDDate = simpleDateFormatWithTime.parse(checkinDate);
                String checkoutDate = simpleDateFormatWithTime.format(appointment.getCheckOutDate());
                Date checkoutDDate = simpleDateFormatWithTime.parse(checkoutDate);

                if ((checkinDDate.after(startDDate) && checkinDDate.before(endDDate)) || checkinDDate.equals(startDDate) ||
                        checkinDDate.equals(endDDate)) {

                    LinkedHashMap<Object, Object> lhm = new LinkedHashMap<>();
                    Service service = (Service) commonService.getObjectByColumnName("Service", "serviceCode", appointment.getServiceCode(), "Get All Appointments");
                    lhm.put("serviceName", service.getServiceName());
                    PetAppointment petAppointment = (PetAppointment) commonService.getObjectByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");
                    Pet pet = (Pet) commonService.getObjectByColumnName("Pet", "petCode", petAppointment.getPetCode(), "Get All Appointments");
                    lhm.put("petName", pet.getPetName());
                    Customer customer = (Customer) commonService.getObjectByColumnName("Customer", "custCode", pet.getCustCode(), "Get All Appointments");
                    lhm.put("ownerName", customer.getCustFirstName());
                    Breed breed = (Breed) commonService.getObjectByColumnName("Breed", "breedCode", pet.getBreedCode(), "Get All Appointments");
                    lhm.put("breedName", breed.getBreedName());
                    lhm.put("roomName", appointment.getRoomCode());
                    checkinList.add(lhm);
                }
            }

            response.put("response", responseDto.getOk());
            response.put("message", "All Check In List.");
            response.put("checkinList", checkinList);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingCheckOutReport", method = RequestMethod.POST)
    public ResponseEntity<?> getCheckOutReport(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = String.valueOf(commonCodeDto.getCode().get("startDate"));
        String endDate = String.valueOf(commonCodeDto.getCode().get("endDate"));
        Date startDDate = null;
        Date endDDate = null;
        try {
            startDDate = simpleDateFormatWithTime.parse(startDate);
            endDDate = simpleDateFormatWithTime.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {

            List<Object> appointments = commonService.getAllObjects("Appointment", "Get All Appointments");
            List<LinkedHashMap> checkinList = new ArrayList<>();

            for (Object object : appointments) {
                Appointment appointment = (Appointment) object;
                String checkinDate = simpleDateFormatWithTime.format(appointment.getCheckInDate());
                Date checkinDDate = simpleDateFormatWithTime.parse(checkinDate);
                String checkoutDate = simpleDateFormatWithTime.format(appointment.getCheckOutDate());
                Date checkoutDDate = simpleDateFormatWithTime.parse(checkoutDate);

                if ((checkoutDDate.after(startDDate) && checkoutDDate.before(endDDate)) || checkoutDDate.equals(endDDate)
                        || checkoutDDate.equals(startDDate)) {

                    LinkedHashMap<Object, Object> lhm = new LinkedHashMap<>();
                    Service service = (Service) commonService.getObjectByColumnName("Service", "serviceCode", appointment.getServiceCode(), "Get All Appointments");
                    lhm.put("serviceName", service.getServiceName());
                    PetAppointment petAppointment = (PetAppointment) commonService.getObjectByColumnName("PetAppointment", "appCode", appointment.getAppCode(), "Get All Appointments");
                    Pet pet = (Pet) commonService.getObjectByColumnName("Pet", "petCode", petAppointment.getPetCode(), "Get All Appointments");
                    lhm.put("petName", pet.getPetName());
                    Customer customer = (Customer) commonService.getObjectByColumnName("Customer", "custCode", pet.getCustCode(), "Get All Appointments");
                    lhm.put("ownerName", customer.getCustFirstName());
                    Breed breed = (Breed) commonService.getObjectByColumnName("Breed", "breedCode", pet.getBreedCode(), "Get All Appointments");
                    lhm.put("breedName", breed.getBreedName());
                    lhm.put("roomName", appointment.getRoomCode());
                    checkinList.add(lhm);
                }
            }

            response.put("response", responseDto.getOk());
            response.put("message", "All Check In List.");
            response.put("checkoutList", checkinList);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingRevenueReport", method = RequestMethod.POST)
    public ResponseEntity<?> getRevenueReport(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            String empName = String.valueOf(commonCodeDto.getCode().get("empName"));
            List<Object> employees = commonService.searchRecordByName("Employee", "empName", empName);

            response.put("response", responseDto.getOk());
            response.put("message", "All employees.");
            response.put("empList", employees);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingVaccination", method = RequestMethod.GET)
    public ResponseEntity<?> getVaccination() {

        LinkedHashMap response = new LinkedHashMap();
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd");

        List<LinkedHashMap> vaccination = new ArrayList<>();

        try {

            List<Object> vaccinationObjects = commonService.getAllObjects("Vaccination", "Get Vaccination");

            for (Object object : vaccinationObjects) {
                Vaccination vaccine = (Vaccination) object;
                LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                hashMap.put("code", vaccine.getVaccCode());
                hashMap.put("name", vaccine.getVaccName());
                hashMap.put("expirationDate", simpleDateFormatWithTime.format(vaccine.getExpirationDate()));
                vaccination.add(hashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current vaccination.");
            response.put("vaccination", vaccination);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingSingleVaccination", method = RequestMethod.POST)
    public ResponseEntity<?> getSingleVaccination(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();
        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd");

        List<LinkedHashMap> vaccination = new ArrayList<>();
        List<LinkedHashMap> vaccineList = new ArrayList<>();
        String petCode = String.valueOf(commonCodeDto.getCode().get("petCode"));

        ArrayList<String> vaccArrList = new ArrayList<>();
        vaccArrList.add("Rabies");
        vaccArrList.add("Lepto");
        vaccArrList.add("Bordetella");
        vaccArrList.add("K9 Influenza");

        try {

            List<Object> vaccinationObjects = commonService.searchRecordByName("Vaccination", "petCode", petCode);
            Pet pet = (Pet) commonService.getObjectByColumnName("Pet", "petCode", petCode);

            if (null != vaccinationObjects) {
                for (Object object : vaccinationObjects) {
                    Vaccination vaccine = (Vaccination) object;
                    LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                    hashMap.put("code", vaccine.getVaccCode());
                    hashMap.put("name", vaccine.getVaccName());
                    hashMap.put("expirationDate", simpleDateFormatWithTime.format(vaccine.getExpirationDate()));
                    vaccination.add(hashMap);
                    vaccArrList.remove(vaccine.getVaccName());
                }
            }

            for (String s : vaccArrList) {
                LinkedHashMap<Object, Object> lhm = new LinkedHashMap<>();
                lhm.put("name", s);
                vaccineList.add(lhm);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current vaccination.");
            if (null != vaccinationObjects) {
                response.put("recSize", vaccinationObjects.size());
            } else {
                response.put("recSize", 0);
            }
            response.put("vaccination", vaccination);
            response.put("vaccineList", vaccineList);
            response.put("petName", pet.getPetName());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/updatingItem", method = RequestMethod.POST)
    public ResponseEntity<?> updateItem(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            response = commonService.updateObject(commonCodeDto);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/creatingCustomerRetail", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomerRetail(@RequestBody CustomerRetailDto customerRetailDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            String username = customerRetailDto.getCustCode();

            SystemUser systemUser = (SystemUser) commonService.getObjectByColumnName("SystemUser", "username", username);

            CustomerRetail customerRetail = new CustomerRetail();
            BeanUtils.copyProperties(customerRetailDto, customerRetail);

            Object objectCustomerRetail = commonService.checkTableEmpty("com.mit.pawin.entity.CustomerRetail", "Create Customer Retail");

            if (null == objectCustomerRetail) {
                customerRetail.setCustRetailId(1);
                customerRetail.setCustRetailCode("CUSTRETAIL-" + 1);
            } else {
                long custRetailId = commonService.getLastRecordId("com.mit.pawin.entity.CustomerRetail", "custRetailId", "Create Customer Retail");
                customerRetail.setCustRetailId(custRetailId + 1);
                customerRetail.setCustRetailCode("CUSTRETAIL-" + (custRetailId + 1));
            }

            customerRetail.setCustCode(systemUser.getEmpCode());
            customerRetail.setCreatedBy("super");

            Object object = commonService.addObject(customerRetail, "Customer Retail Creation");

            if (null != object) {

                response.put("response", responseDto.getOk());
                response.put("message", "Customer Retail Details added Successfully.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            } else {

                response.put("response", responseDto.getFail());
                response.put("message", "Error adding Customer Retail Details.");
                return new ResponseEntity<>(response, HttpStatus.OK);

            }

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/uploadingFile", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) {

        String message = "";
        LinkedHashMap response = new LinkedHashMap();
        try {
            storageService.save(file, username);

            response.put("response", responseDto.getOk());
            response.put("message", "File Uploaded Successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/deletingItem", method = RequestMethod.POST)
    public ResponseEntity<?> deleteItem(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();

        try {

            response = commonService.deleteObject(commonCodeDto);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingEmployeeByDate", method = RequestMethod.POST)
    public ResponseEntity<?> getEmployeeByDate(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();

        List<LinkedHashMap> employees = new ArrayList<>();

        SimpleDateFormat f = new SimpleDateFormat("EEEE");

        String selectedDate = String.valueOf(commonCodeDto.getCode().get("selectedDate"));
        Date date = convertStringDateToDate(selectedDate);
        String strDate = f.format(date).toUpperCase();

        try {

            List<Object> empSchedules = commonService.searchRecordByName("EmployeeSchedule", "day", strDate);
            //List<Object> employeeObjects = commonService.getAllObjects("Employee", "Get Employees");

            if (null != empSchedules) {
                for (Object object : empSchedules) {
                    EmployeeSchedule employeeSchedule = (EmployeeSchedule) object;
                    Employee emp = (Employee) commonService.getObjectByColumnName("Employee", "empCode", employeeSchedule.getEmpCode());
                    LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                    hashMap.put("code", emp.getEmpCode());
                    hashMap.put("name", emp.getEmpName());
                    hashMap.put("number", emp.getEmpNo());
                    hashMap.put("phone", emp.getEmpPhone());
                    hashMap.put("address", emp.getEmpAddress());
                    hashMap.put("nic", emp.getEmpNic());
                    employees.add(hashMap);
                }
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Employees.");
            response.put("employees", employees);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingAllRetailByCustomer", method = RequestMethod.POST)
    public ResponseEntity<?> getAllRetailByCustomer(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();

        String username = String.valueOf(commonCodeDto.getCode().get("username"));
        List<LinkedHashMap> cart = new ArrayList<>();

        try {

            SystemUser systemUser = (SystemUser) commonService.getObjectByColumnName("SystemUser", "username", username);

            List<Object> cusRetail = commonService.searchRecordByName("CustomerRetail", "custCode", systemUser.getEmpCode());

            double total = 0;

            if (null != cusRetail) {
                for (Object object : cusRetail) {
                    CustomerRetail customerRetail = (CustomerRetail) object;
                    Retail retail = (Retail) commonService.getObjectByColumnName("Retail", "retailCode", customerRetail.getRetailCode());
                    LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                    hashMap.put("retailName", retail.getRetailName());
                    hashMap.put("quantity", customerRetail.getQuantity());
                    hashMap.put("totalPrice", customerRetail.getTotalPrice());
                    cart.add(hashMap);

                    total += Double.parseDouble(customerRetail.getTotalPrice());
                }
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Cart.");
            response.put("cart", cart);
            response.put("totalAmount", total);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingAllCustomers", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCustomers() {

        LinkedHashMap response = new LinkedHashMap();

        List<LinkedHashMap> cusList = new ArrayList<>();

        try {

            List<Object> cusObjects = commonService.getAllObjects("Customer", "Get Customers");

            if (null != cusObjects) {
                for (Object object : cusObjects) {
                    Customer customer = (Customer) object;
                    LinkedHashMap<Object, Object> hashMap = new LinkedHashMap<>();
                    hashMap.put("name", customer.getCustFirstName() + " " + customer.getCustLastName());
                    hashMap.put("phoneNo", customer.getCustPhone());
                    hashMap.put("address", customer.getCustAddress());

                    List petObjects = commonService.searchRecordByName("Pet", "custCode", customer.getCustCode());
                    List<String> names = new ArrayList<>();
                    if (null != petObjects) {
                        for (Object obj : petObjects) {
                            Pet pet = (Pet) obj;
                            names.add(pet.getPetName());
                        }
                    }

                    hashMap.put("pets", names);

                    cusList.add(hashMap);
                }
            }

            response.put("response", responseDto.getOk());
            response.put("message", "Current Customers.");
            response.put("cusList", cusList);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/gettingStocksBySupplier", method = RequestMethod.POST)
    public ResponseEntity<?> getStocksBySupplier(@RequestBody CommonCodeDto commonCodeDto) {

        LinkedHashMap response = new LinkedHashMap();

        List<LinkedHashMap> retails = new ArrayList<>();

        try {

            String username = String.valueOf(commonCodeDto.getCode().get("username"));

            List<Object> retailObjects = commonService.searchRecordByName("SupplierRetail", "createdBy", username);

            for (Object object : retailObjects) {
                SupplierRetail retail = (SupplierRetail) object;
                LinkedHashMap hashMap = new LinkedHashMap<>();
                hashMap.put("code", retail.getSuppCode());
                hashMap.put("name", retail.getRetailName());
                hashMap.put("price", retail.getWholeSalePrice());
                hashMap.put("count", retail.getMaxCount());
                retails.add(hashMap);
            }

            response.put("response", responseDto.getOk());
            response.put("message", "All Retails.");
            response.put("stocks", retails);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            response.put("response", responseDto.getError());
            response.put("message", responseDto.getErrorMessage());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


}
