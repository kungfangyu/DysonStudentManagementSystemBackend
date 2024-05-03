package com.dysonstudentmanagement.dsm.idgenerator;

import com.dysonstudentmanagement.dsm.entity.programme.Programme;
import jakarta.persistence.NonUniqueResultException;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/*
ProgrammeIDGenerator

A custom Id generator used by the Programme entity

Original Author: Imran Matloob 24/04/2024
 */
public class ProgrammeIDGenerator implements IdentifierGenerator {

    private static final AtomicInteger PROGRAMME_NUMBER = new AtomicInteger();


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        Programme programme = (Programme) o;
        String name;
        if (programme.getName() == null) {
            name = generateRandomString(true, 9); // Generate a random string of length 11
        } else {
            name = programme.getName();
        }

        // Ensure the length of the name does not exceed 11 characters
//        if (name.length() > 12) {
//            name = name.substring(0, 12);
//        }

        // Generate the ProgrammeID using the modified name
        String[] nameWords = name.split(" ", 4);

        int nonRandomContribution = 8;
        float wordContributionToId = 8 / nameWords.length;

        Session session = (Session) sharedSessionContractImplementor.getSession();
        String proposedId = "";
        boolean idExists = true;



        for (int i = 0; i < 10; i++) {
            for (String word : nameWords) {
                int substringLength = Math.min(word.length(), 4); // Ensure substring length does not exceed word length
                proposedId = proposedId + word.substring(0, substringLength);

            }

            String formattedProgrammeNumber = String.format("%02d", PROGRAMME_NUMBER.incrementAndGet());
            boolean objectExists = session.createQuery("SELECT COUNT(p) FROM Programme p WHERE p.name = :name AND p.startDate = :startDate AND p.endDate = :endDate AND p.description = :description AND p.totalCredits = :totalCredits AND p.isGradesReleased = :isGradesReleased", Long.class)
                    .setParameter("name", programme.getName())
                    .setParameter("startDate", programme.getStartDate())
                    .setParameter("endDate", programme.getEndDate())
                    .setParameter("description", programme.getDescription())
                    .setParameter("totalCredits", programme.getTotalCredits())
                    .setParameter("isGradesReleased", programme.isGradesReleased())
                    .setMaxResults(1)
                    .getSingleResult() > 0;

            boolean programmeExists = session.createQuery("SELECT COUNT(p) FROM Programme p WHERE p.programmeID = :programmeID", Long.class)
                    .setParameter("programmeID", proposedId.toString() + formattedProgrammeNumber)
                    .setMaxResults(1)
                    .getSingleResult() > 0;


            if (!programmeExists) {

                return proposedId.toString() + formattedProgrammeNumber;
            } else {

                formattedProgrammeNumber = String.format("%02d", PROGRAMME_NUMBER.incrementAndGet());
            }
        }
        throw new NonUniqueResultException("Could not generate unique ProgrammeID.");
    }

    private String generateRandomString(boolean alphabetic, int targetStringLength) {
        int leftLimit;
        int rightLimit;
        Random random = new Random();
        StringBuilder generatedString = new StringBuilder();
        if (alphabetic) {
            leftLimit = 97;
            rightLimit = 122;
        } else {
            leftLimit = 48;
            rightLimit = 122;
        }
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            generatedString.append((char) randomLimitedInt);
        }
        return generatedString.toString();
    }
}


//package com.dysonstudentmanagement.dsm.idgenerator;
//
//import com.dysonstudentmanagement.dsm.entity.Programme.programme;
//import com.dysonstudentmanagement.dsm.repository.*;
//import jakarta.persistence.NonUniqueResultException;
//import org.hibernate.Session;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.IdentifierGenerator;
//import org.springframework.dao.DataIntegrityViolationException;
//
//import java.io.Serializable;
//import java.util.Optional;
//import java.util.Random;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class ProgrammeIDGenerator implements IdentifierGenerator {
//
//    private static final AtomicInteger PROGRAMME_NUMBER = new AtomicInteger();
//
//
//
//    @Override
//    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
//        Programme programme = (Programme) o;
//        String name;
//        if (programme.getName() == null) {
//            name = generateRandomString(true, 9); // Generate a random string of length 11
//        } else {
//            name = programme.getName();
//        }
//
//        // Ensure the length of the name does not exceed 11 characters
//        if (name.length() > 12) {
//            name = name.substring(0, 12);
//        }
//
//        // Generate the ProgrammeID using the modified name
//        String[] nameWords = name.split(" ", 4);
//
//        int nonRandomContribution = 8;
//        float wordContributionToId = 8 / nameWords.length;
//
//        Session session = (Session) sharedSessionContractImplementor.getSession();
//        String proposedId = "";
//        boolean idExists = true;
//
//
//
//        for (int i = 0; i < 10; i++) {
//            for (String word : nameWords) {
//                int substringLength = Math.min(word.length(), 4); // Ensure substring length does not exceed word length
//                proposedId = proposedId + word.substring(0, substringLength);
//
//            }
//
//            String formattedProgrammeNumber = String.format("%02d", PROGRAMME_NUMBER.incrementAndGet());
//            boolean objectExists = session.createQuery("SELECT COUNT(p) FROM Programme p WHERE p.name = :name AND p.startDate = :startDate AND p.endDate = :endDate AND p.description = :description AND p.totalCredits = :totalCredits AND p.isGradesReleased = :isGradesReleased", Long.class)
//                    .setParameter("name", programme.getName())
//                    .setParameter("startDate", programme.getStartDate())
//                    .setParameter("endDate", programme.getEndDate())
//                    .setParameter("description", programme.getDescription())
//                    .setParameter("totalCredits", programme.getTotalCredits())
//                    .setParameter("isGradesReleased", programme.isGradesReleased())
//                    .setMaxResults(1)
//                    .getSingleResult() > 0;
//
//            boolean programmeExists = session.createQuery("SELECT COUNT(p) FROM Programme p WHERE p.programmeID = :programmeID", Long.class)
//                    .setParameter("programmeID", proposedId.toString() + formattedProgrammeNumber)
//                    .setMaxResults(1)
//                    .getSingleResult() > 0;
//
//
//            if (!programmeExists) {
//
//                return proposedId.toString() + formattedProgrammeNumber;
//            } else {
//
//                formattedProgrammeNumber = String.format("%02d", PROGRAMME_NUMBER.incrementAndGet());
//            }
//        }
//        throw new NonUniqueResultException("Could not generate unique ProgrammeID.");
//    }
//
//    private String generateRandomString(boolean alphabetic, int targetStringLength) {
//        int leftLimit;
//        int rightLimit;
//        Random random = new Random();
//        StringBuilder generatedString = new StringBuilder();
//        if (alphabetic) {
//            leftLimit = 97;
//            rightLimit = 122;
//        } else {
//            leftLimit = 48;
//            rightLimit = 122;
//        }
//        for (int i = 0; i < targetStringLength; i++) {
//            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
//            generatedString.append((char) randomLimitedInt);
//        }
//        return generatedString.toString();
//    }
//}