package com.mit.pawin.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mit.pawin.config.CustomJwtAuthenticationFilter;
import com.mit.pawin.controller.AuthenticationController;
import com.mit.pawin.dto.ResponseDto;
import com.mit.pawin.tree.SampleData;
import com.mit.pawin.tree.TreeNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.springframework.beans.MethodInvocationException.ERROR_CODE;

public class CommonUtil {

    public final static String FORGOT_PASSWORD_EMAIL = "<b>Hello!</b><br><br>You are receiving this email because we received a " +
            "password request<br>for your account. Please use the following password.<br><br><b>" + generatePassayPassword() + "</b><br><br>" +
            "If you did not reset your password, no further action is required.<br><br>";

    public final static String PASSWORD_EXPIRED_EMAIL1 = "<b>Hello!</b><br><br>You are receiving this email because your " +
            "password will be expired on ";

    public final static String PASSWORD_EXPIRED_EMAIL2 = ". <br>Please change your password.<br><br>";

    public final static String EMAIL_LAST_LINE = "Regards,<br>" +
            "CBA Help Desk";

    public final static String USERNAME_AND_PASSWORD_CREATION_EMAIL1 = "<b>Hello!</b><br><br>You are receiving this email because CBA-HRIS has created " +
            "username and password<br>for your account. Please use the following username and password.<br><br>";

    public final static String USERNAME_AND_PASSWORD_CREATION_EMAIL2 = ". <br>Your password will be expired on ";

    public final static String USERNAME_CREATION_EMAIL = "<b>Hello!</b><br><br>You are receiving this email because CBA-HRIS has created " +
            "username <br>for your account. Please use the following username.<br><br>";

    public final static String PASSWORD_CREATION_EMAIL = "<b>Hello!</b><br><br>You are receiving this email because CBA-HRIS has created " +
            "password <br>for your account. Please use the following password.<br><br>";

    public final static String USERNAME_STOLEN_EMAIL = "<b>Hello!</b><br><br>Someone tried to log in to your CBA-HRIS account. Pls. inform your admin and change<br>" +
            "your username and password.<br><br>";

    public static String PASSWORD_SENT_BY_EMAIL;

    @Value("${mit.pawin.web.token}")
    private String webToken;

    @Value("${mit.pawin.web.refresh.token}")
    private String refreshToken;

    @Value("${mit.pawin.web.access.time}")
    private String accessTime;

    @Value("${mit.pawin.max.inactive.time.period}")
    private String maxInactTimePerion;

    @Value("${mit.pawin.key.for.web.password}")
    private String secret;

    @Autowired
    private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

    @Autowired
    private ResponseDto responseDto;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_NIC_REGEX =
            Pattern.compile("^([0-9]{9}[X|x|V|v]|[0-9]{12})$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_PHONE_NO_REGEX =
            Pattern.compile("^(?:0)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|5|6|7|8)\\d)\\d{6}$", Pattern.CASE_INSENSITIVE);

    @Value("${mit.pawin.web.access.time}")
    private String accessTimePath;

    SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    private static final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    final static String DOUBLE_PATTERN = "[0-9]+(\\.){0,1}[0-9]*";

    /**
     * 1
     *
     * @return
     */
    public static String generatePassayPassword() {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        //log.info("Calling  " + methodNameUsingClassInstance + "()");

        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);

        PASSWORD_SENT_BY_EMAIL = password;

        return password;
    }

    /**
     * 2
     *
     * @param inputDate
     * @param expirDays
     * @return
     */
    public Date addDaysToDate(Date inputDate, int expirDays) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(inputDate);

        cal.add(Calendar.DAY_OF_MONTH, expirDays);

        Date modifiedDate = cal.getTime();

        return modifiedDate;
    }

    /**
     * 3
     *
     * @param absoluteFilePath
     */
    public void createFileUsingPath(String absoluteFilePath) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ absoluteFilePath={}}", absoluteFilePath);

        String fileSeparator = System.getProperty("file.separator");
        String newFilePath = absoluteFilePath.replace("/", fileSeparator);
        //absolute file name with path
        File file = new File(newFilePath);
        try {
            if (file.createNewFile()) {

                log.info(absoluteFilePath + " File Created");

            } else

                log.info("File " + absoluteFilePath + " already exists");

        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

        }
    }

    /**
     * 4
     *
     * @param input
     * @param fileName
     */
    public void writeMyStringToFile(String input, String fileName) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ input={}, fileName={}}", input, fileName);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(input);
        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

        } finally {
            try {
                writer.close();
            } catch (IOException e) {

                log.info("Exception {}", e.getMessage());

            }
        }
    }

    /**
     * 5
     *
     * @param fileName
     */
    public static void clearFile(String fileName) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ fileName={}}", fileName);

        FileWriter fwOb = null;
        try {
            fwOb = new FileWriter(fileName, false);
        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

        }
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        try {
            fwOb.close();
        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

        }
    }

    /**
     * 6
     *
     * @param filePath
     * @return
     */
    public String readFile(String filePath) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ filePath={}}", filePath);

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

        }

        return contentBuilder.toString();
    }

    /**
     * 7
     *
     * @param d1
     * @param d2
     * @return
     */
    public long findDiffBtwDatesInMin(Date d1, Date d2) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ d1={}, d2={}}", d1, d2);

        long difference_In_Time = d2.getTime() - d1.getTime();
        long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
        return difference_In_Minutes;
    }

    /**
     * 8
     *
     * @param sDate
     * @return
     */
    public Date convertStringToDate(String sDate) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ sDate={}}", sDate);

        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dDate = simpleDateFormatWithTime.parse(sDate);
            return dDate;
        } catch (ParseException e) {

            log.info("Exception {}", e.getMessage());

        }
        return null;
    }

    /**
     * 9
     *
     * @return
     */
    public boolean validateToken() {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        String tokenFile = webToken + customJwtAuthenticationFilter.getTokenUsername() + ".txt";
        //String refreshTokenFile = refreshToken + customJwtAuthenticationFilter.getTokenUsername() + ".txt";
        String accessTimeFile = accessTime + customJwtAuthenticationFilter.getTokenUsername() + ".txt";

        String tokenFileVal = readFile(tokenFile).replace("\n", "");
        //String refreshTokenFileVal = readFile(refreshTokenFile).replace("\n", "");
        String accessTimeFileVal = readFile(accessTimeFile).replace("\n", "");

        String token = customJwtAuthenticationFilter.getNewJwtToken();
        Date d1 = convertStringToDate(accessTimeFileVal);
        long timeDiff = findDiffBtwDatesInMin(d1, new Date());

        if (tokenFileVal.equals("")) {
            return false;
        } else if (tokenFileVal.equals(token)) {
            if (timeDiff <= Long.parseLong(maxInactTimePerion)) {
                return true;
            } else {
                return false;
            }
        } /*else if (refreshTokenFileVal.equals(token)) {
            if (timeDiff <= Long.parseLong(maxInactTimePerion)) {
                return true;
            } else {
                return false;
            }
        }*/ else {
            return false;
        }
    }

    /**
     * 10
     *
     * @param cipherText
     * @return
     */
    public LinkedHashMap decryptString(String cipherText) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ cipherText={}}", cipherText);

        String decryptedText = null;

        LinkedHashMap response = new LinkedHashMap();

        //String cipherText = "U2FsdGVkX1/WT5GYbl97Td/JybQZomHLw309WvOxGYs=";
        //String cipherText = inputDataDto.getInput1();
        byte[] decryptedData = new byte[0];

        try {

            byte[] cipherData = Base64.getDecoder().decode(cipherText);

            if (cipherData.length != 0) {
                byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);

                MessageDigest md5 = null;
                try {
                    md5 = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException e) {

                    log.info("Exception {}", e.getMessage());

                    response.put("response", responseDto.getError());
                    response.put("message", "Error occurred");
                    return response;
                }
                final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secret.getBytes(StandardCharsets.UTF_8), md5);
                SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
                IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

                byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
                Cipher aesCBC = null;
                try {
                    aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
                } catch (NoSuchAlgorithmException e) {

                    log.info("Exception {}", e.getMessage());

                    response.put("response", responseDto.getError());
                    response.put("message", "Error occurred");
                    return response;
                } catch (NoSuchPaddingException e) {

                    log.info("Exception {}", e.getMessage());

                    response.put("response", responseDto.getError());
                    response.put("message", "Error occurred");
                    return response;
                }
                try {
                    aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
                } catch (InvalidKeyException e) {

                    log.info("Exception {}", e.getMessage());

                    response.put("response", responseDto.getError());
                    response.put("message", "Error occurred");
                    return response;
                } catch (InvalidAlgorithmParameterException e) {

                    log.info("Exception {}", e.getMessage());

                    response.put("response", responseDto.getError());
                    response.put("message", "Error occurred");
                    return response;
                }

                try {
                    decryptedData = aesCBC.doFinal(encrypted);
                } catch (IllegalBlockSizeException e) {

                    log.info("Exception {}", e.getMessage());

                    response.put("response", responseDto.getError());
                    response.put("message", "Error occurred");
                    return response;
                } catch (BadPaddingException e) {

                    log.info("Exception {}", e.getMessage());

                    response.put("response", responseDto.getError());
                    response.put("message", "Error occurred");
                    return response;
                }

            }

        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

            response.put("response", responseDto.getError());
            response.put("message", "Error occurred");
            return response;
        }

        response.put("response", responseDto.getOk());
        response.put("message", "This is decrypted value.");

        decryptedText = new String(decryptedData, StandardCharsets.UTF_8);

        response.put("value", decryptedText);

        return response;
    }

    /**
     * 11
     *
     * @param keyLength
     * @param ivLength
     * @param iterations
     * @param salt
     * @param password
     * @param md
     * @return
     */
    public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ keyLength={}, ivLength={}, iterations={}}", keyLength, ivLength, iterations);

        int digestLength = md.getDigestLength();
        int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
        byte[] generatedData = new byte[requiredLength];
        int generatedLength = 0;

        try {
            md.reset();

            // Repeat process until sufficient data has been generated
            while (generatedLength < keyLength + ivLength) {

                // Digest data (last digest if available, password data, salt if available)
                if (generatedLength > 0)
                    md.update(generatedData, generatedLength - digestLength, digestLength);
                md.update(password);
                if (salt != null)
                    md.update(salt, 0, 8);
                try {
                    md.digest(generatedData, generatedLength, digestLength);
                } catch (DigestException e) {

                    log.info("Exception {}", e.getMessage());

                }

                // additional rounds
                for (int i = 1; i < iterations; i++) {
                    md.update(generatedData, generatedLength, digestLength);
                    md.digest(generatedData, generatedLength, digestLength);
                }

                generatedLength += digestLength;
            }

            // Copy key and IV into separate byte arrays
            byte[][] result = new byte[2][];
            result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
            if (ivLength > 0)
                result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

            return result;

        } catch (DigestException e) {

            log.info("Exception {}", e.getMessage());

            throw new RuntimeException(e);

        } finally {
            // Clean out temporary data
            Arrays.fill(generatedData, (byte) 0);
        }
    }

    /**
     * 12
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public LinkedHashMap handleValidationExceptions(MethodArgumentNotValidException ex) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ ex={}}", ex.getMessage());

        LinkedHashMap response = new LinkedHashMap();

        response.put("response", responseDto.getFail());
        response.put("message", "One or more input fields are not valid");

        return response;
    }

    /**
     * 13
     *
     * @param emailStr
     * @return
     */
    public static boolean validateEmail(String emailStr) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ emailStr={}}", emailStr);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    /**
     * 14
     *
     * @param nicStr
     * @return
     */
    public static boolean validateNic(String nicStr) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ nicStr={}}", nicStr);

        Matcher matcher = VALID_NIC_REGEX.matcher(nicStr);
        return matcher.find();
    }

    /**
     * 15
     *
     * @param phoneNoStr
     * @return
     */
    public static boolean validatePhoneNo(String phoneNoStr) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ phoneNoStr={}}", phoneNoStr);

        Matcher matcher = VALID_PHONE_NO_REGEX.matcher(phoneNoStr);
        return matcher.find();
    }

    /**
     * 16
     *
     * @param object
     * @param objects
     * @param index
     * @return
     */
    public Object[] insertDataToArray(Object object, Object[] objects, int index){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ index={}}", index);

        objects[++index] = object;

        return objects;

    }

    /**
     * 17
     *
     * @param object
     * @return
     */
    public boolean isNullOrEmpty(Object object){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ object={}}", object);

        if(null != object){
            if(!"".equals(object)){
                return false;
            }
        }
        return true;
    }

    /**
     * 18
     *
     * @param date
     * @return
     */
    public String convertDateToString(Date date) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ date={}}", date);

        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String sDate = simpleDateFormatWithTime.format(date);
            return sDate;
        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

        }
        return null;
    }

    /**
     * 19
     *
     * @param date
     * @return
     */
    public String convertDateToStringWithTime(Date date) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ date={}}", date);

        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String sDate = simpleDateFormatWithTime.format(date);
            return sDate;
        } catch (Exception e) {

            log.info("Exception {}", e.getMessage());

        }
        return null;
    }

    /**
     * 20
     *
     * @param path
     * @return
     */
    public boolean deleteFile(String path){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ path={}}", path);

        File file = new File(path);

        if(file.delete())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 21
     *
     * @return
     */
    public LinkedHashMap uploadedFileType(){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("profileImg", true);
        linkedHashMap.put("birthCertificate", true);
        return linkedHashMap;
    }

    /**
     * 22
     *
     * @param object
     * @return
     */
    public boolean validateObjectForXss(Object object){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ object={}}", object);

        ObjectMapper oMapper = new ObjectMapper();

        // object -> Map
        Map<String, Object> map = oMapper.convertValue(object, Map.class);

        for (Map.Entry me : map.entrySet()){
            if(checkScript(me.getValue())){
                return true;
            }
        }

        return false;
    }

    /**
     * 23
     *
     * @param input
     * @return
     */
    public boolean checkScript(Object input){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ input={}}", input);

        try {
            String regex = "(?i)<(/?script[^>]*)>";
            String output = input.toString().replaceAll(regex, "&lt;$1&lt;");

            String[] chkArr = output.split("&lt;");

            if (chkArr.length >= 2) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){

            log.info("Exception {}", e.getMessage());

            return false;
        }
    }

    /**
     * 24
     *
     */
    public void writeTimeToFile(){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        String accessTimeFile = accessTimePath + customJwtAuthenticationFilter.getLoginUsername() + ".txt";
        createFileUsingPath(accessTimeFile);
        writeMyStringToFile(simpleDateFormatWithTime.format(new Date()), accessTimeFile);
    }

    /**
     * 25
     *
     * @param filePath
     * @param data
     */
    public void writeFileWithAppend(String filePath, String data){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ filePath={}, data={}}", filePath, data);

        File file = new File(filePath);
        FileWriter fr = null;
        try {
            if(!file.exists()){
                createFileUsingPath(filePath);
            }
            fr = new FileWriter(file, true);
            fr.write(data + System.lineSeparator());
            fr.close();
        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

        }
    }

    /**
     * 26
     *
     * @return
     */
    public boolean validateRefreshToken() {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        String refreshTokenFile = refreshToken + customJwtAuthenticationFilter.getTokenUsername() + ".txt";
        String accessTimeFile = accessTime + customJwtAuthenticationFilter.getTokenUsername() + ".txt";

        String refreshTokenFileVal = readFile(refreshTokenFile).replace("\n", "");
        String accessTimeFileVal = readFile(accessTimeFile).replace("\n", "");

        String token = customJwtAuthenticationFilter.getNewJwtToken();

        Date d1 = convertStringToDate(accessTimeFileVal);
        long timeDiff = findDiffBtwDatesInMin(d1, new Date());

        if (refreshTokenFileVal.equals("")) {
            return false;
        } else if (refreshTokenFileVal.equals(token)) {
            /*if (timeDiff <= Long.parseLong(maxInactTimePerion)) {
                return true;
            } else {
                return false;
            }*/
            return true;
        } else {
            return false;
        }
    }

    public enum CredentialEmailStatus {
        USERNAME_AND_PASSWORD_CHANGED, //0
        USERNAME_CHANGED, //1
        PASSWORD_CHANGED, //2
        USERNAME_STOLE, //3
        NORMAL_PASSWORD_EXPIRATION //3
    }

    /**
     * 27
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<LocalDate> getDatesBetweenUsingJava8(LocalDate startDate, LocalDate endDate) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ startDate={}, endDate={}}", startDate, endDate);

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }

    /**
     * 28
     *
     * @param strFilePath
     */
    public void createHiddenFile(String strFilePath){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ strFilePath={}}", strFilePath);

        File file = new File(strFilePath);
        try {
            file.createNewFile();
        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

        }

        if(file.isHidden())

            log.info("File is hidden");

        else

            log.info("File is hidden");

        /*
         * Make file hidden using NIO
         */
        //get path
        Path path = Paths.get(strFilePath);

        //set hidden attribute
        try {
            Files.setAttribute(path, "dos:hidden", true, LinkOption.NOFOLLOW_LINKS);
        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

        }

        if(file.isHidden())

            log.info("File is hidden");

        else

            log.info("File is visible");

    }

    /**
     * 29
     *
     * @param fileName
     * @return
     */
    public static long countLineFast(String fileName) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ fileName={}}", fileName);

        long lines = 0;

        try (InputStream is = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if (endsWithoutNewLine) {
                ++count;
            }
            lines = count;
        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

        }

        return lines;
    }

    /**
     * 30
     *
     * @param files
     * @param mergedFile
     */
    public static void mergeFiles(File[] files, File mergedFile) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        FileWriter fstream = null;
        BufferedWriter out = null;
        try {
            fstream = new FileWriter(mergedFile, true);
            out = new BufferedWriter(fstream);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        for (File f : files) {
            System.out.println("merging: " + f.getName());
            FileInputStream fis;
            try {
                fis = new FileInputStream(f);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                String aLine;
                while ((aLine = in.readLine()) != null) {
                    out.write(aLine);
                    out.newLine();
                }
                in.close();
            } catch (IOException e) {

                log.info("Exception {}", e.getMessage());

            }
        }
        try {
            out.close();
        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

        }

    }

    public void writeObjectToJsonFile(Object object, String filePath){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" , "{ filePath={}}", filePath);

        ObjectMapper mapper = new ObjectMapper();

        File file = new File(filePath);

        try {

            // Serialize Java object info JSON file.
            mapper.writeValue(file, object);

        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

        }

    }

    public int getMonthFromDate(Date date){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        return month;
    }

    public int getDayFromDate(Date date){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int day = localDate.getDayOfMonth();
        return day;
    }

    public JSONArray readJsonFile(String filePath){
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            //Iterate over employee array
            employeeList.forEach( emp -> parseEmployeeObject() );

            return employeeList;

        } catch (FileNotFoundException e) {

            log.info("Exception {}", e.getMessage());

            return null;

        } catch (IOException e) {

            log.info("Exception {}", e.getMessage());

            return null;

        } catch (org.json.simple.parser.ParseException e) {

            log.info("Exception {}", e.getMessage());

            return null;

        }

    }

    private static void parseEmployeeObject()
    {
        //Get employee object within list
        JSONObject employeeObject = new JSONObject();

        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");
        System.out.println(firstName);

        //Get employee last name
        String lastName = (String) employeeObject.get("lastName");
        System.out.println(lastName);

        //Get employee website name
        String birthday = (String) employeeObject.get("birthday");
        System.out.println(birthday);

        //Get employee website name
        String title = (String) employeeObject.get("title");
        System.out.println(title);
    }

    public int getYearFromDate(Date date){

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()");

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        return year;
    }

    public void createJsonObject(){

        //String jsonStr = "{\"Raja\":\"Java\", \"Ravi\":\"SAP\", \"Chaitanya\":\"Python\", \"Adithya\":\"Spark\"}";
       /* String jsonStr = "{\"Raja\":{A:\"Java\"}, \"Ravi\":\"SAP\", \"Chaitanya\":\"Python\", \"Adithya\":\"Spark\"}";

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonStr);
        JsonObject obj = element.getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
        for(Map.Entry<String, JsonElement> entry: entries) {
            System.out.println(entry.getKey());
        }*/

        ArrayList<ArrayList<String>> alMain = new ArrayList<ArrayList<String>>();
        ArrayList<String> al1 = new ArrayList<>();
        al1.add("Admin");
        al1.add("~");
        al1.add("~");
        al1.add("~");

        ArrayList<String> al2 = new ArrayList<>();
        al2.add("~");
        al2.add("Manager");
        al2.add("~");
        al2.add("~");

        ArrayList<String> al3 = new ArrayList<>();
        al3.add("~");
        al3.add("~");
        al3.add("Supervisor");
        al3.add("~");

        ArrayList<String> al4 = new ArrayList<>();
        al4.add("~");
        al4.add("~");
        al4.add("~");
        al4.add("Test 1");

        ArrayList<String> al5 = new ArrayList<>();
        al5.add("~");
        al5.add("~");
        al5.add("~");
        al5.add("Test 2");

        ArrayList<String> al6 = new ArrayList<>();
        al6.add("~");
        al6.add("Service Manager");
        al6.add("~");
        al6.add("~");

        alMain.add(al1);
        alMain.add(al2);
        alMain.add(al3);
        alMain.add(al4);
        alMain.add(al5);
        alMain.add(al6);

        System.out.println(alMain);

        writeMyStringToFile(alMain.toString(), "E:/hris-cba/web/user/usrStructure.txt");

        ArrayList alMainR = (ArrayList)(Object) readFile("E:/hris-cba/web/user/usrStructure.txt").replace("\n", "");

        String s = null;

    }

    public void checkTree(){
        TreeNode<String> treeRoot = SampleData.getSet1();
        for (TreeNode<String> node : treeRoot) {
            String indent = createIndent(node.getLevel());
            System.out.println(indent + node.data);
        }
    }

    private static String createIndent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }

    public boolean validateDouble(String input){

        Pattern pattern = Pattern.compile(DOUBLE_PATTERN);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public boolean validateTime(String input){
        Pattern pattern = Pattern.compile(TIME24HOURS_PATTERN);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public ArrayList modifyArrayList(ArrayList<String> arrayList){

        ArrayList<String> retList = new ArrayList<>();

        for(int i=0; i<arrayList.size(); i++){

            if(i != arrayList.size()-1) {
                String s = arrayList.get(i) + "," + arrayList.get(i + 1);
                retList.add(s);
            }

        }

        return retList;
    }

    public List<String> getFileIntoList(String filePath){
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            result = lines.collect(Collectors.toList());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public long noOfDayCountBetweenTwoDays(String date1, String date2){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            Date firstDate = sdf.parse(date1);
            Date secondDate = sdf.parse(date2);

            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            return diff;
        } catch (ParseException e) {
            return 0;
        }
    }

    public Date convertStringDateToDate(String sDate) {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ sDate={}}", sDate);

        SimpleDateFormat simpleDateFormatWithTime = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dDate = simpleDateFormatWithTime.parse(sDate);
            return dDate;
        } catch (ParseException e) {

            log.info("Exception {}", e.getMessage());

        }
        return null;
    }
}
