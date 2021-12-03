package com.mit.pawin.scheduler;

import com.mit.pawin.dto.PrefixDto;
import com.mit.pawin.dto.UtilDto;
import com.mit.pawin.dto.ValuePassingDto;
import com.mit.pawin.entity.Customer;
import com.mit.pawin.entity.Email;
import com.mit.pawin.entity.Pet;
import com.mit.pawin.entity.Vaccination;
import com.mit.pawin.service.CommonService;
import com.mit.pawin.service.EmailService;
import com.mit.pawin.util.CommonUtil;
import com.mit.pawin.util.TransactionLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CommonScheduler extends CommonUtil {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UtilDto utilDto;

    @Value("${cba.hris.email.password.expir.days}")
    private String daysToSendPasswordExpirEmail;

    private static final Logger log = LoggerFactory.getLogger(CommonScheduler.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Value("${cba.hris.email.password.listener.folder}")
    private String sysUsrCodeFolder;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private TransactionLog transactionLog;

    SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ValuePassingDto valuePassingDto;

    @Autowired
    private PrefixDto prefixDto;

    @Value("${cba.hris.tranaction.log.key}")
    private String trnsLogKey;

    @Value("${cba.hris.dashboard.birthday}")
    private String dashboardBirthdayFile;

    @Value("${cba.hris.dashboard.newlyJoinedEmp}")
    private String dashboardNewlyJoinedEmp;

    @Value("${cba.hris.dashboard.joined.date}")
    private String newlyJoinedDate;

    @Value("${cba.hris.iclock.url}")
    private String iclockUrl;

    @Scheduled(fixedDelay = 60000)//1 min
    public void sendEmail() {

        List<Object> petObjects = commonService.getAllObjects("Pet", "Send email");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");

        List<Object> vaccObjects = commonService.getAllObjects("Vaccination", "Send email");
        for (Object object : vaccObjects) {
            Vaccination vaccination = (Vaccination) object;

            String firstDate = convertDateToString(new Date());
            String secondDate = convertDateToString(vaccination.getExpirationDate());

            long count = noOfDayCountBetweenTwoDays(firstDate, secondDate);

            if(count == 2){
                Pet pet = (Pet) commonService.getObjectByColumnName("Pet", "petCode", vaccination.getPetCode());
                Customer customer = (Customer) commonService.getObjectByColumnName("Customer", "custCode", pet.getCustCode());

                ArrayList<String> col = new ArrayList<>();
                ArrayList<Object> val = new ArrayList<>();

                col.add("petCode");
                col.add("emailType");

                val.add(pet.getPetCode());
                val.add("VACC_" + vaccination.getExpirationDate());

                Object objEmail = commonService.searchRecord("Email", col, val, "Send email");

                if (null == objEmail) {
                    Email email = new Email();

                    Object objectEmail = commonService.checkTableEmpty("com.mit.pawin.entity.Email", "Send email");
                    if (null == objectEmail) {
                        email.setEmailId(1);
                    } else {
                        long emailId = commonService.getLastRecordId("com.mit.pawin.entity.Email", "emailId", "Send email");
                        email.setEmailId(emailId + 1);
                    }

                    email.setPetCode(pet.getPetCode());
                    email.setEmailType("VACC_" + vaccination.getExpirationDate());
                    email.setCreatedBy("System");

                    Object saved = commonService.addObject(email);
                    if (null != saved) {
                        String subject = "Vaccination Notification";
                        String message = "Hi,\n" +
                                "\n" +
                                "\n" +
                                "Your Pet " + pet.getPetName() + "\'s following vaccination is about to expire, please re-vaccinate...\n" +
                                "Please refer below information:\n" +
                                "\n" +
                                "Vaccination-name: " + vaccination.getVaccName() + "\n" +
                                "Vaccination-expire on " + vaccination.getExpirationDate() + "\n" +
                                "\n" +
                                "\n" +
                                "Thank You.\n" +
                                "Regards,\n" +
                                "PawIn";
                        emailService.sendEmail(customer.getCustEmail(), message, subject);
                    }
                }
            }
        }

        for (Object object : petObjects) {
            Pet pet = (Pet) object;
            String today = simpleDateFormat.format(new Date());
            String dob = simpleDateFormat.format(pet.getDob());

            if (today.equals(dob)) {
                Customer customer = (Customer) commonService.getObjectByColumnName("Customer", "custCode", pet.getCustCode());

                ArrayList<String> col = new ArrayList<>();
                ArrayList<Object> val = new ArrayList<>();

                col.add("petCode");
                col.add("emailType");

                val.add(pet.getPetCode());
                val.add("DOB");

                Object objEmail = commonService.searchRecord("Email", col, val, "Send email");

                if (null == objEmail) {
                    Email email = new Email();

                    Object objectEmail = commonService.checkTableEmpty("com.mit.pawin.entity.Email", "Send email");
                    if (null == objectEmail) {
                        email.setEmailId(1);
                    } else {
                        long emailId = commonService.getLastRecordId("com.mit.pawin.entity.Email", "emailId", "Send email");
                        email.setEmailId(emailId + 1);
                    }

                    email.setPetCode(pet.getPetCode());
                    email.setEmailType("DOB");
                    email.setCreatedBy("System");

                    Object saved = commonService.addObject(email);
                    if (null != saved) {
                        String subject = "Happy Birthday " + pet.getPetName() + " from PawIn !";
                        String message = "Hi,\n" +
                                "\n" +
                                "Happy birthday to our adventure-loving buddy and co-explorer!\n" +
                                "We are looking forward to many more years to come of fun exploring together!\n" +
                                "\n" +
                                "\n" +
                                "Please come and board your pets.\n" +
                                "\n" +
                                "Thank You.\n" +
                                "Regards,\n" +
                                "PawIn.";
                        emailService.sendEmail(customer.getCustEmail(), message, subject);
                    }
                }
            }
        }

    }
}
