package com.dysonstudentmanagement.dsm.idgenerator;

import com.dysonstudentmanagement.dsm.entity.user.UserPrimaryData;

import jakarta.persistence.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;


import java.io.Serializable;
import java.util.Random;


/*
UserIDGenerator

A custom Id generator used by the UserPrimaryData entity. Sets a userID that is 8 chars long, using 2 characters from the first name,
2 characters from the last name, and 4 random characters.

Original Author: Billy Peters 21/04/2024
 */
public class UserIDGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        UserPrimaryData user = (UserPrimaryData) o;
        String first = user.getFirstName();
        String last = user.getLastName();
//        if (first == null) {
//            first = generateRandomString(true, 10);
//        }
//        if (last == null) {
//            last = generateRandomString(true, 10);
//        }
        first = first.replace(" ","");
        last = last.replace(" ","");
        String initialID;
        boolean idExists = true;

        Session session = (Session) sharedSessionContractImplementor.getSession();

        for (int i = 1; i < first.length(); i++){
            for( int j = 1; j < last.length();j++) {
                initialID = (first.substring(0, 1) + first.charAt(i) + last.charAt(0) + last.charAt(j));
                for (int count = 0; count < 1000; count++) {
                    initialID = (initialID + generateRandomString(true, 4)).toUpperCase();
                    idExists = session.createQuery("SELECT COUNT(u) FROM UserPrimaryData u WHERE u.userID = :id", Long.class).setParameter("id", initialID).setMaxResults(1).getSingleResult() > 0;

                    if (!idExists) {
                        return initialID;
                    }
                }
            }
        }
        throw new NonUniqueResultException("Could not generate unique ID for user");
    }



    private String generateRandomString(boolean alphabetic, int targetStringLength){
        int leftLimit;
        int rightLimit;
        if(alphabetic){
            leftLimit = 97;
            rightLimit = 122;
        } else{
            leftLimit = 48;
            rightLimit = 122;
        }
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return(generatedString);
    }
}